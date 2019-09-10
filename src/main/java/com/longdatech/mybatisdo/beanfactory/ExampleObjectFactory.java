package com.longdatech.mybatisdo.beanfactory;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class ExampleObjectFactory extends DefaultObjectFactory {

    public <T> T create(Class<T> type) {
        System.out.println("自定义类工厂1");
        return super.create(type);
    }

    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        System.out.println("自定义类工厂2");
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    public void setProperties(Properties properties) {
        super.setProperties(properties);
    }

    public <T> boolean isCollection(Class<T> type) {
        return Collection.class.isAssignableFrom(type);
    }

}