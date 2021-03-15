package com.example.servicecuntian.aop;

import com.example.servicecuntian.exception.BusinessException;
import com.example.servicecuntian.service.PurchaseService;
import com.example.servicecuntian.util.EmailUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    @Autowired
    private PurchaseService purchaseService;

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
        Object[] obj = joinPoint.getArgs();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        if (obj.length == 3) {
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
    @AfterReturning(value = ExpGetResultDataPonit, returning = "result")
    public void afterAdvice(JoinPoint joinPoint, Map<String, Object> result) {
        System.out.println("AOP start");
        verificationTime(result);
    }

    /**
     * 验证数据
     * 1、时间是否对的上
     * 2、是否为空
     */
    private void verificationTime(Map<String, Object> result) {
        List<Object> request_body = (List<Object>) result.get("request_body");
        if (request_body != null) {
            String dateMark = purchaseService.getDateMark();
            boolean b = isNow(dateMark);
            if (!b) {
                result.put("resultCode", "900");   //数据不是最新的，当天kylin数据未更新
                emailUtil.sendTemplateEmail(emails, "kylin数据不是最新，请检查！", "kylin数据不是最新");
            }
        } else {
            result.put("resultCode", "204");    //数据为空
            emailUtil.sendTemplateEmail(emails, "kylin数据为空，请检查！", "kylin数据为空");
        }
    }

    /**
     * 判断时间是否是当天
     *
     * @param date
     * @return
     */
    private static boolean isNow(String date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);
        return nowDay.equals(date);
    }

    /***
     * 判断字符串是否是yyyyMMdd格式
     * @param mes 字符串
     * @return boolean 是否是日期格式
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
}
