package com.hm.pluginsdk;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 反射相关的工具类
 */
public class ReflectHelper {
    /**
     * 执行某对象的方法
     *
     * @param className      类名
     * @param methodName     方法名
     * @param oArray         方法的参数数组
     * @param paramTypeArray 方法的参数类型数组
     * @author liudb
     */
    public static Object invokeMethod(String className, String methodName, Object[] oArray, Class<?>[] paramTypeArray)
            throws Exception {
        Object[] obj = {};
        Class<?>[] cla = {};
        Object owner = invokeStaticMethod(className, "getInstance", obj, cla);
        Class<?> ownerClass = owner.getClass();
        Method method = ownerClass.getMethod(methodName, paramTypeArray);

        return method.invoke(owner, oArray);
    }

    /**
     * 执行包含接口的方法
     *
     * @param    methodName        方法名
     * @param    oArray            具体参数
     * @param    paramTypeArray        构造参数类型
     * @param    interfaceName    接口名
     * @author liudb
     */
    public static Object invokeContainsInterfaceMethod(Object owner, String methodName,
                                                       Object[] oArray, Class<?>[] paramTypeArray, String interfaceName, ReflectCallBack callBack) throws Exception {
        Object[] obj = {};
        Class<?>[] cla = {};
        //Object owner = invokeStaticMethod(className, "getInstances", obj, cla);
        Class<?>[] argsClass = new Class[oArray.length];
        Method[] ms = owner.getClass().getDeclaredMethods();
        if (ReflectConfig.HmcpVideoView.getReflectClass().equals(owner.getClass())){
            Method[] imlMethods = Objects.requireNonNull(owner.getClass().getSuperclass()).getDeclaredMethods();
            int length = ms.length;
            Method[] all = new Method[length + imlMethods.length];
            System.arraycopy(ms, 0, all, 0, length);
            System.arraycopy(imlMethods, 0, all, length, imlMethods.length);
            ms = all;

        }
        for (int i = 0, j = oArray.length; i < j; i++) {
            argsClass[i] = oArray[i].getClass();
        }
        Method method = findMethod(ms, methodName, paramTypeArray, interfaceName);
        Class<?> clazz = PluginManager.getInstance().getDexClassLoader().loadClass(interfaceName);
        //因为接口的最后一个参数是回调函数，所以要设置监测回调
        oArray[oArray.length - 1] = Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                //payType 是一个判断支付类型的参数，可以不设置
                callBack);
        return method.invoke(owner, oArray);
    }

    /**
     * 执行某个类的静态方法
     *
     * @param    className        类名
     * @param    methodName        方法名
     * @param    oArray            方法参数
     * @param    paramTypeArray        构造参数类型
     * @author liudb
     */
    public static Object invokeStaticMethod(String className, String methodName,
                                     Object[] oArray, Class<?>[] paramTypeArray) throws Exception {
        Class<?> ownerClass = PluginManager.getInstance().getDexClassLoader().loadClass(className);
        Method method = ownerClass.getMethod(methodName, paramTypeArray);
        return method.invoke(ownerClass, oArray);
    }

    /**
     * 执行包含接口的静态方法
     *
     * @param    className        类名
     * @param    methodName        方法名
     * @param    oArray            具体参数
     * @param    paramTypeArray        构造参数类型
     * @param    interfaceName    接口名
     * @author liudb
     */
    public Object invokeContainsInterfaceStaticMethod(String className, String methodName,
                                                      Object[] oArray, Class<?>[] paramTypeArray, String interfaceName, ReflectCallBack callBack) throws Exception {
        //Class<?> ownerClass = PluginManager.getInstance().getDexClassLoader().loadClass(className);
        Class<?> ownerClass = PluginManager.getInstance().getDexClassLoader().loadClass(className);

        Class<?>[] argsClass = new Class[oArray.length];
        Method[] ms = ownerClass.getDeclaredMethods();
        for (int i = 0, j = oArray.length; i < j; i++) {
            argsClass[i] = oArray[i].getClass();
        }
        Method method = findMethod(ms, methodName, paramTypeArray, interfaceName);
        Class<?> clazz = PluginManager.getInstance().getDexClassLoader().loadClass(interfaceName);
        //因为接口的最后一个参数是回调函数，所以要设置监测回调
        oArray[oArray.length - 1] = Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                callBack);
        return method.invoke(ownerClass, oArray);
    }

    /**
     * 获得一个实例
     *
     * @param    className        类的全名
     * @param    args            构造参数
     * @author liudb
     */
    public Object newInstance(String className, Object[] args) throws Exception {
        Class<?> newoneClass = PluginManager.getInstance().getDexClassLoader().loadClass(className);

        Class<?>[] argsClass = new Class[args.length];

        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }

        Constructor<?> cons = newoneClass.getConstructor(argsClass);

        return cons.newInstance(args);

    }

    /**
     * 查找需要的方法
     *
     * @param        metody            方法数组
     * @param        methodName        方法名
     * @param        paramsTypes        参数类型
     * @param        interfaceName    回调接口名
     * @author liudb
     */
    public static Method findMethod(Method[] metody, String methodName, Class<?>[] paramsTypes, String interfaceName) throws Exception {
        List<Method> sameNames = new ArrayList<Method>();
        // filter other names
        for (Method meth : metody) {
            if (meth.getName().equals(methodName)) {
                sameNames.add(meth);
            }
        }
        // lets find best candidate
        if (sameNames.isEmpty()) {
            return null;
        } else {
            // filter other count of parameters
            List<Method> sameCountOfParameters = new ArrayList<Method>();
            for (Method meth : sameNames) {
                if (meth.getParameterTypes().length == paramsTypes.length) {
                    sameCountOfParameters.add(meth);
                }
            }
            if (sameCountOfParameters.isEmpty()) {
                return null;
            } else {
                for (Method meth : sameCountOfParameters) {
                    // first one, which is suitable is the best
                    Class<?>[] params = meth.getParameterTypes();
                    boolean good = true;
                    for (int i = 0; i < params.length - 1 && good; i++) {
                        if (params[i].getName().equalsIgnoreCase(paramsTypes[i].getName()) && params[params.length - 1].getName().contains(interfaceName)) {
                            good = true;
                            continue;
                        }
                        good = false;
                    }
                    if (good) {
                        // othre_callBack = params[params.length - 1];
                        return meth;
                    }
                }
            }
        }
        return null;
    }
}
