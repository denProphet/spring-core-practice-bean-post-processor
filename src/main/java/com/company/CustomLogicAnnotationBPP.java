package com.company;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Proxy;

public class CustomLogicAnnotationBPP implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAnnotationPresent(CustomLogic.class))
        System.out.println("CustomLogic :: Before init");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> cl = bean.getClass();
        if(cl.isAnnotationPresent(CustomLogic.class)){

            return Proxy.newProxyInstance(cl.getClassLoader(),cl.getInterfaces(),
                    (proxy, method, args) -> {
                        System.out.println(" CustomLogic :: After init (in proxy)");
                        return method.invoke(bean,args);
                    });
        }
        return bean;
    }
}
