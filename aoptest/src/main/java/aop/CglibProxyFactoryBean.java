package aop;

import Aspert.DynamicAspect;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import service.ProductService;

import java.lang.reflect.Method;

public class CglibProxyFactoryBean<T> implements MethodInterceptor {

    private DynamicAspect dynamicAspect;
    private T targetObject;
    private String methodName;

    public CglibProxyFactoryBean(DynamicAspect dynamicAspect, T targetObject, String methodName) {
        this.dynamicAspect = dynamicAspect;
        this.targetObject = targetObject;
        this.methodName = methodName;
    }

    public T getInstance() {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        return (T)enhancer.create();
    }
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object obj = null;
        if (method.getName().equals(methodName)) {
            dynamicAspect.before();
            obj = method.invoke(targetObject);
            dynamicAspect.after();
        }
        return obj;
    }

    public static void main(String[] args) {
        final CglibProxyFactoryBean<ProductService> cglibProxyFactoryBean = new CglibProxyFactoryBean<ProductService>(new DynamicAspect(), new ProductService(), "run");
        final ProductService productService = cglibProxyFactoryBean.getInstance();
        productService.run();
    }
}
