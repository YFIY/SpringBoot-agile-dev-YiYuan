package com.yiyuan.utils;

import java.lang.reflect.Field;

public class ObjectUtils {

    //对象与对象属性拷贝  source的属性数据放入target的属性中   为null的属性不执行拷贝
    public static void beanCopy(Object source, Object target) throws Exception {
        if (source == null || target == null) {
            throw new Exception("param is null.");
        }
        Field sourceField[] = source.getClass().getDeclaredFields();
        Field targetField[] = target.getClass().getDeclaredFields();
        if (sourceField == null || sourceField.length == 0) {
            throw new Exception("Source bean no properties.");
        }
        if (targetField == null || targetField.length == 0) {
            throw new Exception("Target bean no properties.");
        }
        for (Field tf : targetField) {
            tf.setAccessible(true);
            for (Field sf : sourceField) {
                sf.setAccessible(true);
                String tfType = tf.getType().getName();
                String sfType = sf.getType().getName();
                if (tf.getName().equals(sf.getName()) && tfType.equals(sfType) && sf.get(source) != null) {
                    tf.set(target, sf.get(source));
                    break;
                }
            }
        }
    }

}
