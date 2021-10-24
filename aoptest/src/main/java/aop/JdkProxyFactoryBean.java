package aop;

import Aspert.DynamicAspect;
import service.UserService;
import service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyFactoryBean {
    public static <T> T getInstance(final Class<T> targetClass, final Object serviceClass, final String methodName) {
        final DynamicAspect aspect = new DynamicAspect();
        Object obj = Proxy.newProxyInstance(targetClass.getClassLoader(), new Class[]{targetClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object invoke = null;
                if (method.getName().equals(methodName)) {
                    aspect.before();
                    invoke = method.invoke(serviceClass, args);
                    aspect.after();
                }
                return invoke;
            }
        });
        return (T) obj;
    }

    public static void main(String[] args) {
        final UserServiceImpl userService = new UserServiceImpl();
        userService.run();
        final UserService run = JdkProxyFactoryBean.getInstance(UserService.class, new UserServiceImpl(), "run");
        run.run();
    }
}
