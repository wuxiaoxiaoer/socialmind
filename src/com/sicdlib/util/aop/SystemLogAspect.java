package com.sicdlib.util.aop;


import com.sicdlib.entity.CleanLogManagerEntity;
import com.sicdlib.entity.UserEntity;
import com.sicdlib.util.UUIDUtil.UUIDUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;


@Aspect
@Component
public class SystemLogAspect{
    private static Log logger = LogFactory.getLog(SystemLogAspect.class);
    //注入Service用于把日志保存数据库
    //??   @Autoware??
    @Autowired
    private AopLogService aopLogService;
    //本地异常日志记录对象
//    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //手动添加构造函数
    public SystemLogAspect(){
        System.out.println("进入了切面");
    }
    //Controller层切点
    @Pointcut("@annotation(com.sicdlib.util.aop.ForController)")
    public void controllerAspect(){
        System.out.println("这是controller的切入点");
    }
    //Service层切点
    @Pointcut("@annotation(com.sicdlib.util.aop.ForService)")
    public void serviceAspect(){
        System.out.println("这是service的切入点");
    }

    @AfterReturning("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        /*
        System.out.println("进入了before");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        CleanLogManagerEntity cleanLog = new CleanLogManagerEntity();
        //生成UUID获得日志记录的id
        String cleanLogID =UUIDUtil.getUUID();
//        cleanLog.setCleanLogId(cleanLogID);
        //得到用户对象
        UserEntity user = (UserEntity) session.getAttribute("commonUser");
        //读取session中的用户id
        String userId = user.getUserId();
//        cleanLog.setUserId(userId);
        //请求的IP
        String ip = request.getRemoteAddr();
//        String ip = this.getIP(request);
//        aopLogService.inputLog(cleanLog);

        try{

            System.out.println("=====前置通知开始=====");
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            System.out.println("方法描述:" + (joinPoint));
            System.out.println("请求人:" + user.getUserName());
            System.out.println("请求IP:" + ip);
            System.out.println("描述为:"+SystemLogAspect.getControllerMthodDescription(joinPoint));

            System.out.println("=====前置通知结束=====");
        } catch (Exception e){
            //记录本地异常日志
//            logger.error("==前置通知异常==");
//            logger.error("异常信息:{}", e.getMessage());
            System.out.println("=====前置通知异常=====");
        }*/
    }
    //test
//    @AfterReturning("serviceAspect()")
//    public  void doBefore2(JoinPoint joinPoint) {
//        System.out.println("return return");
//    }

    @Around("serviceAspect()")
    public void cleanLog(ProceedingJoinPoint joinPoint){
        System.out.println("get into cleanlog!");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        CleanLogManagerEntity cleanLog = new CleanLogManagerEntity();
        //生成UUID获得日志记录的id
        String cleanLogID =UUIDUtil.getUUID();
        cleanLog.setCleanLogId(cleanLogID);
        //得到用户对象
        UserEntity user = (UserEntity) session.getAttribute("commonUser");
        //读取session中的用户id
        String userId = user.getUserId();
        cleanLog.setUserId(userId);
        //请求的IP
        String IP = request.getRemoteAddr();
        cleanLog.setIP(IP);
//        String ip = this.getIP(request);
        //get current time

        //获取参数
        Object[] args = joinPoint.getArgs();
        String tableName=(String) args[0];
        String columnName=(String) args[1];
        String cleanStategyId=(String) args[2];
        String userDefineOldValue=(String) args[3];
        String userDefineNewValue=(String) args[4];
        cleanLog.setTableName(tableName);
        cleanLog.setCulumnName(columnName);
        cleanLog.setCleanStategyId(cleanStategyId);
        cleanLog.setUserDefineOldValue(userDefineOldValue);
        cleanLog.setUserDefineNewValue(userDefineNewValue);
        //获取开始的时间
        long startTime = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        SimpleDateFormat timeDf = new SimpleDateFormat("mm:ss");//设置日期格式
        cleanLog.setCleanTime(df.format(startTime));
//        System.out.println("方法开始时间"+df.format(startTime));
        Object obj = null;
        //执行函数
        try {
            obj = joinPoint.proceed(args);
        } catch (Throwable e) {
            logger.error("统计某方法执行耗时环绕通知出错", e);
        }
        long endTime = System.currentTimeMillis();
//        System.out.println("方法结束时间："+df.format(endTime));
        //加1000是因为发现每次少1秒
        long diffTime = endTime - startTime+1000;
//        System.out.println("时间区间"+timeDf.format(diffTime));
        cleanLog.setResponseTime(timeDf.format(diffTime));
//        System.out.println(String.valueOf(obj));
        cleanLog.setResultState(String.valueOf(obj));
        //获取方法名  ---默认都是doClean
//        String cleanStategyName=joinPoint.getSignature().getName();
        aopLogService.inputLog(cleanLog);
    }


    /**
     * 异常通知 用于拦截service层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println("go into exception!");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        CleanLogManagerEntity cleanLog = new CleanLogManagerEntity();
        //生成UUID获得日志记录的id
        String cleanLogID =UUIDUtil.getUUID();
        cleanLog.setCleanLogId(cleanLogID);
        //得到用户对象
        UserEntity user = (UserEntity) session.getAttribute("commonUser");
        //读取session中的用户id
        String userId = user.getUserId();
        cleanLog.setUserId(userId);
        //请求的IP
        String IP = request.getRemoteAddr();
        cleanLog.setIP(IP);
//        String ip = this.getIP(request);
        //get current time

        //获取参数
        Object[] args = joinPoint.getArgs();
        String tableName=(String) args[0];
        String columnName=(String) args[1];
        String cleanStategyId=(String) args[2];
        String userDefineOldValue=(String) args[3];
        String userDefineNewValue=(String) args[4];
        cleanLog.setTableName(tableName);
        cleanLog.setCulumnName(columnName);
        cleanLog.setCleanStategyId(cleanStategyId);
        cleanLog.setUserDefineOldValue(userDefineOldValue);
        cleanLog.setUserDefineNewValue(userDefineNewValue);
        //获取开始的时间
        long startTime = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        cleanLog.setCleanTime(df.format(startTime));
        //添加异常代码
        cleanLog.setErrorCode(e.getClass().getName());
        cleanLog.setErrorMessage(e.getMessage());
        try{
            //保存数据库
            aopLogService.inputLog(cleanLog);
            System.out.println("=====异常通知结束=====");
        }  catch (Exception ex) {
            //记录本地异常日志
            logger.error("==异常通知异常==");
            logger.error("异常信息:{}", ex);
        }

    }
    /**
     * 获取注解中对方法的描述信息 用于controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ForController.class).description();
                    break;
                }
            }
        }
        return description;
    }
    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ForService.class).description();
                    break;
                }
            }
        }
        return description;
    }


    //获取请求IP
    public String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
