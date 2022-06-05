package com.company;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class MarkedBPP implements BeanPostProcessor {
    Map<String, Class> getOriginalClassByBeanNameForAnnotatedClasses = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> originalClass = bean.getClass();
        System.out.println("MarkedBPP :: BeforeInit");
        if (originalClass.isAnnotationPresent(Marked.class))
            getOriginalClassByBeanNameForAnnotatedClasses.put(beanName, originalClass);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = getOriginalClassByBeanNameForAnnotatedClasses.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("MarkedBPP :: AfterInit (in proxy) (beforeOriginalMethod)");
                            Object returnedValue = method.invoke(bean, args);
                            System.out.println("MarkedBPP :: AfterInit (in proxy) (afterOriginalMethod)");
                            return returnedValue;
                        }
                    });
        }
        return bean;
    }
}
