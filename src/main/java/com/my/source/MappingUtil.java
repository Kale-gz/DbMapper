package com.my.source;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MappingUtil {

    private final List<String> mapping;

    public MappingUtil() {
        mapping = List.of("attr1,field10,attr2,field12", "attr1,field18,attr2,field1");
    }

    public MappingUtil(List<String> mapping) {
        this.mapping = mapping;
    }

    public List<MappedObject> map(ObjectToMap source) throws ClassNotFoundException, NoSuchMethodException {
        List<MappedObject> mappedObjects = new ArrayList<>();
        Class<?> clazz = Class.forName("com.my.source.MappedObject");
        Constructor<?> cons = clazz.getConstructor();

        mapping.forEach(x -> {
            String[] params = x.split(",");

            try {
                Method getterField1 = ObjectToMap.class.getMethod("get" + StringUtils.capitalize(params[1]));
                Method getterField2 = ObjectToMap.class.getMethod("get" + StringUtils.capitalize(params[3]));
                Method setterAttr1 = clazz.getMethod("set" + StringUtils.capitalize(params[0]), Object.class);
                Method setterAttr2 = clazz.getMethod("set" + StringUtils.capitalize(params[2]), Object.class);

                MappedObject object = (MappedObject) cons.newInstance();
                setterAttr1.invoke(object, getterField1.invoke(source));
                setterAttr2.invoke(object, getterField2.invoke(source));
                mappedObjects.add(object);
            } catch (NoSuchMethodException | InvocationTargetException |  InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return mappedObjects;
    }
}
