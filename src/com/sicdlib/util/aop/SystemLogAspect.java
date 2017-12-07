package com.sicdlib.util.aop;


import com.sicdlib.entity.CleanLogManagerEntity;
import com.sicdlib.entity.UserEntity;
import com.sicdlib.util.UUIDUtil.UUIDUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;


@Aspect
@Component
public class SystemLogAspect{
    //注入Service用于把日志保存数据库
    //??   @Autoware??
//    @Resource
//    private LogService logService;
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

    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("进入了before");
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();
//
//        CleanLogManagerEntity cleanLog = new CleanLogManagerEntity();
//        //生成UUID获得日志记录的id
//        String cleanLogID =UUIDUtil.getUUID();
//        cleanLog.setCleanLogId(cleanLogID);
//        //得到用户对象
//        UserEntity user = (UserEntity) request.getSession().getAttribute("commonUser");
//        //读取session中的用户id
//        String userId = user.getUserId();
//        cleanLog.setUserId(userId);
        //请求的IP
//        String ip = request.getRemoteAddr();
//        try{
//            //*========控制台输出=========*//
//            System.out.println("=====前置通知开始=====");
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            System.out.println("方法描述:" + getControllerMethodDescription(joinPoint));
//            System.out.println("请求人:" + user.getName());
//            System.out.println("请求IP:" + ip);
            //*========数据库日志=========*//
//            Log log = SpringContextHolder.getBean("logxx");
//            log.setDescription(getControllerMethodDescription(joinPoint));
//            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            log.setType("0");
//            log.setRequestIp(ip);
//            log.setExceptionCode( null);
//            log.setExceptionDetail( null);
//            log.setParams( null);
//            log.setCreateBy(user);
//            log.setCreateDate(DateUtil.getCurrentDate());
            //保存数据库
//            logService.add(log);

//            System.out.println("=====前置通知结束=====");
//        } catch (Exception e){
//            //记录本地异常日志
//            logger.error("==前置通知异常==");
//            logger.error("异常信息:{}", e.getMessage());
//            System.out.println("=====前置通知异常=====");
//        }
    }

    @After("serviceAspect()")
    public void cleanLog(JoinPoint joinPoint){
        System.out.println("service is over!");
    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public  static String getServiceMthodDescription(JoinPoint joinPoint)
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
}
