package com.hm.qa.sdkdemoserver.stub.loader;

import com.hm.qa.sdkdemoserver.stub.Log;

import java.io.File;

import dalvik.system.DexClassLoader;

public class ClazzLoader {
    private static String dexPath = "/data/local/tmp/saas-sdk.aar";
    private static DexClassLoader dexLoader = null;

    private ClazzLoader(){}

    public static DexClassLoader getDexLoader(ClassLoader parent){
        if(dexLoader == null){
            File dexFile = new File(dexPath);
            if(dexFile.exists() == false){
                Log.e("没有找到对应的dex文件");
                return null;
            }
            return new DexClassLoader(dexPath, null, null, parent);
        }
        return dexLoader;
    }
    public Class getClass(String clazzPath) throws ClassNotFoundException {
        Class libClass = dexLoader.loadClass(clazzPath);
        return libClass;
    }
    public Object getClassObject(String clazzPath) throws Exception {
        Class clz = this.getClass(clazzPath);
        Object dexObj = (Object) clz.newInstance();
        return dexObj;
    }
}
