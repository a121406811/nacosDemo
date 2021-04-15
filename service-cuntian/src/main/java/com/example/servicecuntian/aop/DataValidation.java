package com.example.servicecuntian.aop;

import com.example.servicecuntian.exception.BusinessException;
import com.example.servicecuntian.util.EmailUtil;
import com.example.servicecuntian.util.GetIP;
import com.example.servicecuntian.util.SpringContextUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
@Component
public class DataValidation {

    private final String ExpGetResultDataPonit = "execution(* com.example.servicecuntian.controller.*.*(..))";

    private static final Logger logger = LoggerFactory.getLogger(DataValidation.class);
    private static final Logger countLogger = LoggerFactory.getLogger("count");

    @Autowired
    private EmailUtil emailUtil;

    @Value("${kylinEmail}")
    private List<String> emails;

    /**
     * 切入点
     * 匹配com.example.servicecuntian.controller包及其子包下的所有类的所有方法
     */
    @Pointcut(ExpGetResultDataPonit)
    public void pointCut() {

    }

    /**
     * 前置通知，目标方法调用前被调用
     * 验证输入参数格式
     */
    @Before("pointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("----------- 前置方法调用 -----------");
        // 获取方法参数
        Object[] obj = joinPoint.getArgs();
        String str = "";
        for (Object s : obj) {
            str = str + s.toString() + ",";
        }
        // 打印count日志
        String methodName = joinPoint.getSignature().getName();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        countLogger.info(methodName + ";" + GetIP.getIpAddr(request) + ";" + str);
        // 检查参数格式
        if (obj.length == 3) {
//            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            String dateFrom = (String) obj[0];
            String dateTo = (String) obj[1];
            if (!isRqFormat(dateFrom) || !isRqFormat(dateTo)) {
                throw new BusinessException("400", "请求参数格式有误");
            }
        }
    }

    /**
     * 最终通知，目标方法执行完之后执行
     */
    @AfterReturning(value = ExpGetResultDataPonit, returning = "result")   //result为切入方法的返回参数
    public void afterAdvice(JoinPoint joinPoint, Map<String, Object> result) {
//        System.out.println("AOP start");
        // 获取切入方法输入参数
        Object[] obj = joinPoint.getArgs();
        verificationTime(joinPoint.getSignature().getName(), obj, result);
    }

    /**
     * 验证数据
     * 1、时间是否为当天
     * 2、是否为空
     */
    private void verificationTime(String methodName, Object[] obj, Map<String, Object> result) {
        Integer totalDataCount = (Integer) result.get("totalDataCount");
        String msg = "";
        Integer lastRecordNum = (Integer) (obj.length == 1 ? obj[0] : obj[2]);
        // 查询该表总条数
        int count = (int) getCount(methodName);
        // 检查整个表数据是否为空
        if (count == 0) {
            result.put("resultCode", "204");    //数据为空
            msg = msg + "kylin中的" + methodName + "数据为空，请检查！";
            if (lastRecordNum == 0) {
                logger.error(msg);
                emailUtil.sendTemplateEmail(emails, msg, null);
            }
        } else {
            // 检查时间是否为当天
            String dateMark = (String) getLatestDateMark(methodName);
            boolean b = isNow(dateMark);
            if (!b) {
                result.put("resultCode", "900");   //数据不是最新的，当天kylin数据未更新
                msg = msg + "kylin中的" + methodName + "数据最新更新时间为：" + dateMark + "，当前时间为："
                        + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "；请检查！";
                // 每个表完整获取一次数据时，发送一次邮件
                if (lastRecordNum == 0) {
                    logger.error(msg);
                    emailUtil.sendTemplateEmail(emails, msg, null);
                }
            }
        }

    }

    /**
     * 判断时间是否是当天
     */
    private static boolean isNow(String date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        //获取今天的日期
        String nowDay = sf.format(now);
        return nowDay.equals(date);
    }

    /***
     * 判断字符串是否是yyyyMMdd格式
     */
    private static boolean isRqFormat(String mes) {
        String format = "([0-9]{4})(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(mes);
        if (matcher.matches()) {
            pattern = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})");
            matcher = pattern.matcher(mes);
            if (matcher.matches()) {
                int y = Integer.valueOf(matcher.group(1));
                int m = Integer.valueOf(matcher.group(2));
                int d = Integer.valueOf(matcher.group(3));
                if (d > 28) {
                    Calendar c = Calendar.getInstance();
                    c.set(y, m - 1, 1);//每个月的最大天数
                    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    return (lastDay >= d);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 通过反射调用service层的getLatestDateMark方法
     */
    private Object getLatestDateMark(String methodName) {
        String className = "com.example.servicecuntian.service." + methodName.substring(0, 1).toUpperCase() + methodName.substring(1) + "Service";
        Object obj = null;
        try {
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getMethod("getLatestDateMark");
            obj = method.invoke(SpringContextUtil.getBean(clazz));
            System.out.println(obj);
        } catch (InvocationTargetException e) {
            logger.error(methodName + "表调用getLatestDateMark时失败，反射调用对象方法失败");
            logger.error(e.getMessage());
        } catch (NoSuchMethodException e) {
            logger.error(methodName + "表调用getLatestDateMark时失败，反射调用对象方法不存在");
            logger.error(e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error(methodName + "表调用getLatestDateMark时失败，反射调用对象方法格式错误");
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(methodName + "表调用getLatestDateMark时失败，反射类不存在");
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(methodName + "表调用getLatestDateMark时失败，反射错误");
            logger.error(e.getMessage());
        }
        return obj;
    }

    /**
     * 通过反射调用service层的getCount方法
     */
    private Object getCount(String methodName) {
        String className = "com.example.servicecuntian.service." + methodName.substring(0, 1).toUpperCase() + methodName.substring(1) + "Service";
        Object obj = null;
        try {
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getMethod("getCount");
            obj = method.invoke(SpringContextUtil.getBean(clazz));
            System.out.println(obj);
        } catch (InvocationTargetException e) {
            logger.error(methodName + "表调用getLatestDateMark时失败，反射调用对象方法失败");
            logger.error(e.getMessage());
        } catch (NoSuchMethodException e) {
            logger.error(methodName + "表调用getLatestDateMark时失败，反射调用对象方法不存在");
            logger.error(e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error(methodName + "表调用getLatestDateMark时失败，反射调用对象方法格式错误");
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(methodName + "表调用getLatestDateMark时失败，反射类不存在");
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(methodName + "表调用getLatestDateMark时失败，反射错误");
            logger.error(e.getMessage());
        }
        return obj;
    }
}
