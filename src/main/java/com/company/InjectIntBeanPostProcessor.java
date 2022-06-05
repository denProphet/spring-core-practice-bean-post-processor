package com.company;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

public class InjectIntBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field: fields) {
            InjectRandomInt injectRandomInt = field.getAnnotation(InjectRandomInt.class);
            if (injectRandomInt!=null){
                int max = injectRandomInt.max();
                int min = injectRandomInt.min();
                Random random = new Random();
                int randNum = min + random.nextInt(max-min);
                field.setAccessible(true);
                ReflectionUtils.setField(field,bean,randNum);

            }

        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
