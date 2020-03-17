package com.springboot.template;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class SpringContext implements ApplicationContextAware {

    private SpringContext() {}

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        storeContext(applicationContext);   
    }

    public static ApplicationContext getContext() {
        return context;
    }
    
    private static void storeContext(ApplicationContext applicationContext) {
    	context = applicationContext;
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return getContext().getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return getContext().getBean(requiredType);
    }

    public static <T> List<T> getBeans(Class<T> clazz) {
        return new ArrayList<>(context.getBeansOfType(clazz).values());
    }

    public static <T> List<T> getBeans(Class<T> clazz, Class<?>... generics) {
        return Arrays.stream(context.getBeanNamesForType(ResolvableType.forClassWithGenerics(clazz, generics)))
                .map(beanName -> context.getBean(beanName, clazz))
                .collect(Collectors.toList());
    }

}
