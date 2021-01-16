package com.example.servicecuntian.config;



import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;


import java.io.PrintWriter;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;
import java.util.Properties;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

@Slf4j
public class KylinDataSource implements DataSource {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    private LinkedList<Connection> connectionPoolList = new LinkedList<>();

    private long maxWaitTime;

    public KylinDataSource(KylinProperties kylinProperties) {
        try {
            this.maxWaitTime = kylinProperties.getMaxWaitTime();
            Driver driverManager = (Driver) Class.forName(kylinProperties.getDriverClassName()).newInstance();
            Properties info = new Properties();
            info.put("user", kylinProperties.getUsername());
            info.put("password", kylinProperties.getPassword());
            for (int i = 0; i < kylinProperties.getPoolSize(); i++) {
                Connection connection = driverManager.connect(kylinProperties.getJdbcUrl(), info);
                connectionPoolList.add(ConnectionProxy.getProxy(connection, connectionPoolList));
            }
            logger.info("PrestoDataSource has initialized {} size connection pool", connectionPoolList.size());
        } catch (Exception e) {
            logger.error("kylinDataSource initialize error, ex: ", e);
        }
    }

    
    public Connection getConnection() throws SQLException {
        synchronized (connectionPoolList) {
            if (connectionPoolList.size() <= 0) {
                try {
                    connectionPoolList.wait(maxWaitTime);
                } catch (InterruptedException e) {
                    throw new SQLException("getConnection timeout..." + e.getMessage());
                }
            }

            return connectionPoolList.removeFirst();
        }
    }

    
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

   
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

   
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }


    static class ConnectionProxy implements InvocationHandler {

        private Object obj;
        private LinkedList<Connection> pool;
        private String DEFAULT_CLOSE_METHOD = "close";

        private ConnectionProxy(Object obj, LinkedList<Connection> pool) {
            this.obj = obj;
            this.pool = pool;
        }

        public static Connection getProxy(Object o, LinkedList<Connection> pool) {
            Object proxed = Proxy.newProxyInstance(o.getClass().getClassLoader(),
                    new Class[]{Connection.class}, new ConnectionProxy(o, pool));
            return (Connection) proxed;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals(DEFAULT_CLOSE_METHOD)) {
                synchronized (pool) {
                    pool.add((Connection) proxy);
                    pool.notify();
                }
                return null;
            } else {
                return method.invoke(obj, args);
            }
        }


    }
}
