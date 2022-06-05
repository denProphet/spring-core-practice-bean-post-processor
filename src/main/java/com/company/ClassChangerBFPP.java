package com.company;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class ClassChangerBFPP implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] names = beanFactory.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
            try {
                Class<?> beanClass = Class.forName(beanDefinition.getBeanClassName());
                ClassChanged an = beanClass.getAnnotation(ClassChanged.class);
                if(an!=null) beanDefinition.setBeanClassName(an.newClass().getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
