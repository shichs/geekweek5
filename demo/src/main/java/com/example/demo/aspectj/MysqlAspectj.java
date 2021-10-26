package com.example.demo.aspectj;

import com.example.demo.utils.MySqlUtils;
import com.zaxxer.hikari.HikariDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;

@Aspect
@Component
public class MysqlAspectj {
    @Resource
    private HikariDataSource hikariDataSource;

    @Pointcut("execution(* com.example.demo.dao.ProductDao.*(..))")
    public void mysqlMethod(){
    }

    @Around("mysqlMethod()")
    public Object transactional(ProceedingJoinPoint joinPoint) {
//        Connection connection = MySqlUtils.getConnection();
        Connection connection = null;
        Object o = null;
        try {
            connection = hikariDataSource.getConnection();
            MySqlUtils.setConnectionThreadLocal(connection);
            connection.setAutoCommit(false);
            o = joinPoint.proceed();
            String name = joinPoint.getSignature().getName();
            if (!name.startsWith("select")) {
                connection.commit();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return o;
    }
}
