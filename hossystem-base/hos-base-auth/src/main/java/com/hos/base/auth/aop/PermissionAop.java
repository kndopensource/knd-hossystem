package com.hos.base.auth.aop;

import com.hos.base.auth.annotion.Permission;
import com.hos.base.auth.exception.PermissionException;
import com.hos.base.auth.service.AuthService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * 权限检查的aop
 *
 * @author 众神
 * @date 2017-07-13 21:05
 */
@Aspect
@Order(200)
public class PermissionAop {

    @Autowired
    private AuthService authService;

    @Pointcut(value = "@annotation(com.hos.base.auth.annotion.Permission)")
    private void cutPermission() {

    }

    @Around("cutPermission()")
    public Object doPermission(ProceedingJoinPoint point) throws Throwable {
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        Permission permission = method.getAnnotation(Permission.class);
        String[] permissions = permission.value();
        if (permissions.length == 0) {

            //检查全体角色
            boolean result = authService.checkAll();
            if (result) {
                return point.proceed();
            } else {
                throw new PermissionException();
            }

        } else {

            //检查指定角色
            boolean result = authService.check(permissions);
            if (result) {
                return point.proceed();
            } else {
                throw new PermissionException();
            }
        }
    }

}
