package com.hxc.cms.aspect;

import com.hxc.cms.enums.ResultEnum;
import com.hxc.cms.service.user.UserService;
import com.hxc.cms.utils.ResultUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 *  Token 校验
 * @date  2018/7/30
 * @author  黄细初
 */
@Aspect // 该注解标示该类为切面类，切面是由通知和切点组成的
@Component // 注册到Spring容器
public class TokenAspect {
    
    /** "令牌"属性名称 */
    public static final String TOKEN_ATTRIBUTE_NAME = "token";
    
    private final static Logger logger = LoggerFactory.getLogger(TokenAspect.class);
    
    @Pointcut("@annotation(com.hxc.cms.annotation.CheckLogin)")
    public void checkToken(){}
    
    @Autowired
    private UserService userService;
    
    
    /**
     * Token 检查
     * @return
     * @throws Throwable
     */
    @Around("checkToken()")
    public Object around(ProceedingJoinPoint pjp)throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info(String.format("%s 许可检查,前置通知", TOKEN_ATTRIBUTE_NAME));
        String methodName = pjp.getSignature().getName();
        Object target = pjp.getTarget();
        Method method = getMethodByClassAndName(target.getClass(), methodName);//得到拦截的方法
        logger.info(String.format("%s 拦截方法：%s", TOKEN_ATTRIBUTE_NAME, method));
        String token = request.getHeader(TOKEN_ATTRIBUTE_NAME);
        logger.info(String.format("%s 许可检查 ：%s", TOKEN_ATTRIBUTE_NAME, token));
        try {
            logger.info("进入userService.checkToken");
            boolean isOkToken = userService.checkToken(token);
            logger.info("userService.checkToken返回isOkToken：" + isOkToken);
            if (!isOkToken) {
                logger.error(String.format("%s：%s,验证不通过!", TOKEN_ATTRIBUTE_NAME, token));
                return ResultUtil.error(ResultEnum.TOKEN_ERROR_MESSAGE);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResultUtil.error(ResultEnum.TOKEN_EXCEPTION_MESSAGE);
        }
        return pjp.proceed();
    }
    
    /**
     * 根据类和方法名得到方法
     */
    public <T> Method getMethodByClassAndName(Class<T> c, String methodName) {
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }
}
