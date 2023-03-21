package com.hm.pluginsdk;

import android.util.Log;

import com.hm.pluginsdk.beans.VideoDelayInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 反射相关的Invoke工具类
 */
public class RefInvoke {
    private static final String TAG = "RefInvoke";

    /**
     * 根据类名创建对象
     *
     * @param className
     * @return
     */
    public static Object createObject(String className) {
        Class[] pareTyples = new Class[]{};
        Object[] pareVaules = new Object[]{};

        try {
            Class r = Class.forName(className);
            return createObject(r, pareTyples, pareVaules);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    /**
     * 根据类参数创建对象
     *
     * @param clazz
     * @return
     */
    public static Object createObject(Class clazz) {
        Class[] pareTyple = new Class[]{};
        Object[] pareVaules = new Object[]{};

        return createObject(clazz, pareTyple, pareVaules);
    }

    /**
     * 根据类相关参数创建对象
     *
     * @param className
     * @param pareTyple
     * @param pareVaule
     * @return
     */
    public static Object createObject(String className, Class pareTyple, Object pareVaule) {
        Class[] pareTyples = new Class[]{pareTyple};
        Object[] pareVaules = new Object[]{pareVaule};

        try {
            Class r = Class.forName(className);
            return createObject(r, pareTyples, pareVaules);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    /**
     * 根据类参数创建对象
     *
     * @param clazz
     * @param pareTyple
     * @param pareVaule
     * @return
     */
    public static Object createObject(Class clazz, Class pareTyple, Object pareVaule) {
        Class[] pareTyples = new Class[]{pareTyple};
        Object[] pareVaules = new Object[]{pareVaule};

        return createObject(clazz, pareTyples, pareVaules);
    }

    /**
     * 根据类参数创建对象
     *
     * @param className
     * @param pareTyples
     * @param pareVaules
     * @return
     */
    public static Object createObject(String className, Class[] pareTyples, Object[] pareVaules) {
        try {
            Class r = Class.forName(className);
            return createObject(r, pareTyples, pareVaules);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    /**
     * 根据类参数创建对象
     *
     * @param clazz
     * @param pareTyples
     * @param pareVaules
     * @return
     */
    public static Object createObject(Class clazz, Class[] pareTyples, Object[] pareVaules) {
        try {
            Constructor ctor = clazz.getDeclaredConstructor(pareTyples);
            ctor.setAccessible(true);
            return ctor.newInstance(pareVaules);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }


    /**
     * 反射调用指定类对象的方法
     *
     * @param obj
     * @param methodName
     * @param pareTyples
     * @param pareVaules
     * @return
     */
    public static Object invokeInstanceMethod(Object obj, String methodName, Class[] pareTyples, Object[] pareVaules) {
        if (obj == null) {
            Log.e(TAG, "invokeInstanceMethod: obj == null, from ", new Exception());
            return null;
        }

        try {
            //调用一个private方法
            Method method = obj.getClass().getDeclaredMethod(methodName, pareTyples); //在指定类中获取指定的方法
            method.setAccessible(true);
            return method.invoke(obj, pareVaules);

        } catch (NoSuchMethodException e) {
            try {
                Class<?> superclass = obj.getClass().getSuperclass();
                if (superclass != null) {
                    Method method = superclass.getDeclaredMethod(methodName, pareTyples); //在指定类中获取指定的方法
                    method.setAccessible(true);
                    return method.invoke(obj, pareVaules);
                }
            } catch (Exception ex) {
                e.printStackTrace();
                Log.e(TAG, "invokeInstanceMethod: ", ex);
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "invokeInstanceMethod: ", e);
        }

        return null;
    }

    /**
     * 反射调用指定类对象的方法
     *
     * @param obj
     * @param methodName
     * @param pareTyple
     * @param pareVaule
     * @return
     */
    public static Object invokeInstanceMethod(Object obj, String methodName, Class pareTyple, Object pareVaule) {
        Class[] pareTyples = {pareTyple};
        Object[] pareVaules = {pareVaule};

        return invokeInstanceMethod(obj, methodName, pareTyples, pareVaules);
    }

    /**
     * 反射调用指定类对象的方法
     *
     * @param obj
     * @param methodName
     * @return
     */
    public static Object invokeInstanceMethod(Object obj, String methodName) {
        Class[] pareTyples = new Class[]{};
        Object[] pareVaules = new Object[]{};

        return invokeInstanceMethod(obj, methodName, pareTyples, pareVaules);
    }


    /**
     * 反射调用指定类的静态方法
     *
     * @param className
     * @param method_name
     * @return
     */
    public static Object invokeStaticMethod(String className, String method_name) {
        Class[] pareTyples = new Class[]{};
        Object[] pareVaules = new Object[]{};

        return invokeStaticMethod(className, method_name, pareTyples, pareVaules);
    }

    /**
     * 反射调用指定类的静态方法
     *
     * @param className
     * @param method_name
     * @param pareTyple
     * @param pareVaule
     * @return
     */
    public static Object invokeStaticMethod(String className, String method_name, Class pareTyple, Object pareVaule) {
        Class[] pareTyples = new Class[]{pareTyple};
        Object[] pareVaules = new Object[]{pareVaule};

        return invokeStaticMethod(className, method_name, pareTyples, pareVaules);
    }

    /**
     * 反射调用指定类的静态方法
     *
     * @param className
     * @param method_name
     * @param pareTyples
     * @param pareVaules
     * @return
     */
    public static Object invokeStaticMethod(String className, String method_name, Class[] pareTyples, Object[] pareVaules) {
        try {
            Class obj_class = Class.forName(className);
            return invokeStaticMethod(obj_class, method_name, pareTyples, pareVaules);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    /**
     * 反射调用指定类的静态方法
     *
     * @param clazz
     * @param method_name
     * @return
     */
    public static Object invokeStaticMethod(Class clazz, String method_name) {
        Class[] pareTyples = new Class[]{};
        Object[] pareVaules = new Object[]{};

        return invokeStaticMethod(clazz, method_name, pareTyples, pareVaules);
    }

    /**
     * 反射调用指定类的静态方法
     *
     * @param clazz
     * @param method_name
     * @param classType
     * @param pareVaule
     * @return
     */
    public static Object invokeStaticMethod(Class clazz, String method_name, Class classType, Object pareVaule) {
        Class[] classTypes = new Class[]{classType};
        Object[] pareVaules = new Object[]{pareVaule};

        return invokeStaticMethod(clazz, method_name, classTypes, pareVaules);
    }

    /**
     * 反射调用指定类的静态方法
     *
     * @param clazz
     * @param method_name
     * @param pareTyples
     * @param pareVaules
     * @return
     */
    public static Object invokeStaticMethod(Class clazz, String method_name, Class[] pareTyples, Object[] pareVaules) {
        try {
            Method method = clazz.getDeclaredMethod(method_name, pareTyples);
            method.setAccessible(true);
            return method.invoke(null, pareVaules);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    /**
     * 获取指定对象的字段值
     *
     * @param obj
     * @param filedName
     * @return
     */
    public static Object getFieldObject(Object obj, String filedName) {
        return getFieldObject(obj.getClass(), obj, filedName);
    }

    /**
     * 获取指定对象的字段值
     *
     * @param className
     * @param obj
     * @param filedName
     * @return
     */
    public static Object getFieldObject(String className, Object obj, String filedName) {
        try {
            Class obj_class = Class.forName(className);
            return getFieldObject(obj_class, obj, filedName);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    /**
     * 获取指定对象的字段值
     *
     * @param clazz
     * @param obj
     * @param filedName
     * @return
     */
    public static Object getFieldObject(Class clazz, Object obj, String filedName) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return null;
    }

    /**
     * 获取指定对象的字段值
     *
     * @param obj
     * @param filedName
     * @param filedVaule
     */
    public static void setFieldObject(Object obj, String filedName, Object filedVaule) {
        setFieldObject(obj.getClass(), obj, filedName, filedVaule);
    }

    /**
     * 设置指定对象的字段值
     *
     * @param clazz
     * @param obj
     * @param filedName
     * @param filedVaule
     */
    public static void setFieldObject(Class clazz, Object obj, String filedName, Object filedVaule) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            field.set(obj, filedVaule);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * 设置指定对象的字段值
     *
     * @param className
     * @param obj
     * @param filedName
     * @param filedVaule
     */
    public static void setFieldObject(String className, Object obj, String filedName, Object filedVaule) {
        try {
            Class obj_class = Class.forName(className);
            setFieldObject(obj_class, obj, filedName, filedVaule);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
    }


    /**
     * 获取指定类的静态字段值
     *
     * @param className
     * @param filedName
     * @return
     */
    public static Object getStaticFieldObject(String className, String filedName) {
        return getFieldObject(className, null, filedName);
    }

    /**
     * 获取指定类的静态字段值
     *
     * @param clazz
     * @param filedName
     * @return
     */
    public static Object getStaticFieldObject(Class clazz, String filedName) {
        return getFieldObject(clazz, null, filedName);
    }

    /**
     * 设置指定类的静态字段值
     *
     * @param classname
     * @param filedName
     * @param filedVaule
     */
    public static void setStaticFieldObject(String classname, String filedName, Object filedVaule) {
        setFieldObject(classname, null, filedName, filedVaule);
    }

    /**
     * 设置指定类的静态字段值
     *
     * @param clazz
     * @param filedName
     * @param filedVaule
     */
    public static void setStaticFieldObject(Class clazz, String filedName, Object filedVaule) {
        setFieldObject(clazz, null, filedName, filedVaule);
    }

    /**
     * 转换对象类型
     *
     * @param src
     * @param targetType
     * @return
     */
    protected static <T extends Object> T convertObject(Object src, Class<T> targetType) {
        T target = null;
        try {
            target = targetType.newInstance();
            copyBeanByName(src, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 复制两个对象的属性值，前提是字段名称和类型均一致该字段才能复制成功
     *
     * @param src
     * @param target
     */
    protected static void copyBeanByName(Object src, Object target) {
        if (src == null || target == null) {
            return;
        }
        try {
            Map<String, Field> srcFieldMap = getAssignableFieldsMap(src);
            if (target instanceof VideoDelayInfo){
                srcFieldMap.putAll(getAssignableFieldsMap(src.getClass().getSuperclass()));
            }
            Map<String, Field> targetFieldMap = getAssignableFieldsMap(target);
            if (target instanceof VideoDelayInfo){
                srcFieldMap.putAll(getAssignableFieldsMap(target.getClass().getSuperclass()));
            }
            for (String srcFieldName : srcFieldMap.keySet()) {
                Field srcField = srcFieldMap.get(srcFieldName);
                if (srcField == null) {
                    continue;
                }
                // 变量名需要相同
                if (!targetFieldMap.keySet().contains(srcFieldName)) {
                    continue;
                }
                Field targetField = targetFieldMap.get(srcFieldName);
                if (targetField == null) {
                    continue;
                }
                // 类型需要相同
                if (!srcField.getType().equals(targetField.getType())) {
                    continue;
                }

                targetField.set(target, srcField.get(src));
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return;
    }

    /**
     * 复制List列表
     *
     * @param src
     * @param targetType
     * @return
     */
    protected static List convertListByType(List<? extends Object> src, Class<?> targetType) {
        List result = new ArrayList();
        if (src == null || src.size() < 1) {
            return result;
        }
        try {
            for (Object item : src) {
                Object targetItem = createObject(targetType);
                copyBeanByName(item, targetItem);
                result.add(targetItem);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * 获取JavaBean的所有可赋值属性字段
     *
     * @param obj
     * @return
     */
    private static Map<String, Field> getAssignableFieldsMap(Object obj) {
        if (obj == null) {
            return new HashMap<String, Field>();
        }
        Map<String, Field> fieldMap = new HashMap<String, Field>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            // 过滤不需要拷贝的属性
            if (Modifier.isStatic(field.getModifiers())
                    || Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }

    /**
     * 获取JavaBean的所有属性字段
     *
     * @param targetClass
     * @return
     */
    private static Map<String, Field> getAllFieldsMap(Class targetClass) {
        if (targetClass == null) {
            return new HashMap<String, Field>();
        }
        Map<String, Field> fieldMap = new HashMap<String, Field>();
        for (Field field : targetClass.getDeclaredFields()) {
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }


    /**
     * 枚举类型转换
     *
     * @param source
     * @param targetClass
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T extends Enum<?>, S extends Enum<?>> T convertEnum(S source, Class<T> targetClass) {
        if (source instanceof Enum) {
            String sourceEnum = ((Enum<?>) source).name();
            try {
                return getEnumObject(sourceEnum, targetClass);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;

    }

    /**
     * 获取枚举成员
     *
     * @param value
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T extends Enum<?>> T getEnumObject(String value, Class<T> clazz) {
        if (!clazz.isEnum()) {
            return null;
        }
        try {
            T[] enumConstants = clazz.getEnumConstants();
            for (T ec : enumConstants) {
                if (((Enum<?>) ec).name().equals(value)) {
                    return ec;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 比较两个类的字段数量和类型
     *
     * @param class1
     * @param class2
     * @return
     */
    protected static boolean compareClassFields(Class class1, Class class2) {
        if (class1 == null || class2 == null) {
            return true;
        }
        try {
            Map<String, Field> srcFieldMap = getAllFieldsMap(class1);
            Map<String, Field> targetFieldMap = getAllFieldsMap(class2);
            // 字段个数不等
            if (srcFieldMap.size() != targetFieldMap.size()) {
                Log.e(TAG, "Classes not match!!! Class:<" + class1.getName() + "> has " + srcFieldMap.size() + " fields, Class:<" + class2.getName() + "> has" + targetFieldMap.size() + " fields!");
                return false;
            }
            for (String srcFieldName : srcFieldMap.keySet()) {
                Field srcField = srcFieldMap.get(srcFieldName);
                // 变量名需要相同
                if (!targetFieldMap.keySet().contains(srcFieldName)) {
                    Log.e(TAG, "Classes not match!!! Class:<" + class2.getName() + "> doesn't has " + srcFieldName + " field!");
                    return false;
                }
                Field targetField = targetFieldMap.get(srcFieldName);
                if (targetField == null) {
                    Log.e(TAG, "Classes not match!!! Class:<" + class2.getName() + "> doesn't has " + srcFieldName + " field!");
                    return false;
                }
                // 类型需要相同
                if (!srcField.getType().equals(targetField.getType())) {
                    Log.e(TAG, "Classes not match!!! Class:<" + class2.getName() + ">,FieldName=" + targetField.getName() + ",Type = " + targetField.getType() + ", not match with " + "Class:" + class1.getName() + ", FieldName:" + srcField.getName() + ", FieldType: " + srcField.getType());
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return true;
    }
}