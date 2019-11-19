package com.hty.iotprivate.vali_aspc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

@Component
@Aspect
@Slf4j
public class AuthAspect {

    @Pointcut("@annotation(api)")
    public void logPointCut(AuthCheck api){

    }

    @Before(value = "logPointCut(api)",argNames = "joinPoint,api")
    public void before(JoinPoint joinPoint,AuthCheck api){

        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());
//        Object[] orgs = joinPoint.getArgs();
//        for (int i = 0; i < orgs.length; i++) {
//            log.info(orgs[i].toString());
//        }

        if ("A".equals(api.value())){
            String kind = joinPoint.getKind();
            Signature signature = joinPoint.getSignature();
            log.info("kind:{},signature:{}",kind,signature);
            log.info("A");

        }else if("B".equals(api.value())){

        }

    }
}
