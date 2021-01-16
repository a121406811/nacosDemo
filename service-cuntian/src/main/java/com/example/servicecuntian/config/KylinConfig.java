package com.example.servicecuntian.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class KylinConfig {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
    @Bean(name = "kylinProperties")
    @ConfigurationProperties(prefix = "spring.datasource.kylin")
    public KylinProperties creatKylinProperties() {
        return new KylinProperties();
    }

    @Bean(name = "kylinDataSource")
    public DataSource KylinDataSource(@Qualifier("kylinProperties") KylinProperties kylinProperties) {
        log.info("-------------------- kylin init ---------------------");
        return new KylinDataSource(kylinProperties);
    }

    @Bean(name = "kylinTemplate")
    public JdbcTemplate prestoJdbcTemplate(@Qualifier("kylinDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
