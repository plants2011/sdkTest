package com.hm.pluginsdk;

import android.content.Context;
import android.util.Log;

import com.hm.pluginsdk.utils.ZipUtils;

import java.io.File;
import java.io.IOException;

import dalvik.system.DexClassLoader;

/**
 * 插件管理工具类
 */
public class PluginManager {

    private static final String TAG = PluginManager.class.getSimpleName();

    /**
     * 加载插件的DexClassLoader
     */
    private static DexClassLoader dexClassLoader;

    /**
     * PluginManager实例初始化
     */
    private static class PluginManagerInstance {
        private static final PluginManager instance = new PluginManager();
    }

    /**
     * 构造函数
     */
    private PluginManager() {
    }

    /**
     * 获取PluginManager实例
     *
     * @return
     */
    public static PluginManager getInstance() {
        return PluginManagerInstance.instance;
    }


    /**
     * 安装插件包
     *
     * @param context 上下文
     * @param apk  插件
     * @param callback 回调
     */
    public void install(Context context, File apk, PluginInitCallback callback) {
        if (!apk.exists()) {
            callback.onInit(PluginInitResult.PLUGIN_FILE_NOTEXIT);
            return;
        }
        try {
            ZipUtils.upZipFile(apk, apk.getParent());
            callback.onInit(PluginInitResult.PLUGIN_FILE_INIT_SUCCESS);
        } catch (IOException e) {
            callback.onInit(PluginInitResult.PLUGIN_FILE_UNZIPFAIL);
            e.printStackTrace();
            Log.e(TAG, "here "+ e.getMessage());
        }
    }

    public boolean loadAarJar(Context context, File jar) {
        if (jar == null) {
            return false;
        }

        String libPath = File.separator + "jni";
        switch (android.os.Build.CPU_ABI) {
            case "arm64-v8a":
                libPath = libPath + File.separator + "arm64-v8a" + File.separator;
                break;
            case "armeabi-v7a":
                libPath = libPath + File.separator + "armeabi-v7a" + File.separator;
                break;
            default:
                Log.e(TAG, "loadPlugin: miss " + android.os.Build.CPU_ABI );
        }
        String path = jar.getParent() + libPath;
        File soFile = new File(path);
        // Lib目录下的So库文件，So库文件必须存在
        File[] libs = soFile.listFiles();
        if (jar.exists() && soFile.exists() && libs != null && libs.length > 0) {
            dexClassLoader = initDexClassLoader(jar.getAbsolutePath(), jar.getParent(), soFile.getAbsolutePath(), context.getClassLoader());

            return true;
        } else {
            return false;
        }
    }

    /**
     * 加载插件包
     *
     * @param context
     * @param apk     apk插件包文件
     * @return true:加载成功 false:加载失败
     */
    public boolean loadPlugin(Context context, File apk) {
        if (apk == null) {
            return false;
        }

        String libPath = File.separator + "lib";
        switch (android.os.Build.CPU_ABI) {
            case "arm64-v8a":
                libPath = libPath + File.separator + "arm64-v8a" + File.separator;
                break;
            case "armeabi-v7a":
                libPath = libPath + File.separator + "armeabi-v7a" + File.separator;
                break;
            default:
                Log.e(TAG, "loadPlugin: miss " + android.os.Build.CPU_ABI );
        }
        String path = apk.getParent() + libPath;
        File soFile = new File(path);
        // Lib目录下的So库文件，So库文件必须存在
        File[] libs = soFile.listFiles();
        if (apk.exists() && soFile.exists() && libs != null && libs.length > 0) {
            dexClassLoader = initDexClassLoader(apk.getAbsolutePath(), apk.getParent(), soFile.getAbsolutePath(), context.getClassLoader());

            // 插件加载成功后需要初始化ResourceManager以方便插件中资源的管理
            initPluginResource(context, apk.getAbsolutePath());
            return true;
        } else {
            return false;
        }
    }

    /**
     * 初始化DexClassLoader，如果已初始化，则不需要重新创建，避免出现多个DexClassLoader多次加载so库的问题
     *
     * @param dexPath
     * @param optimizedDirectory
     * @param librarySearchPath
     * @param parent
     * @return
     */
    private static DexClassLoader initDexClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        if (dexClassLoader != null) {
            return dexClassLoader;
        } else {
            dexClassLoader = new DexClassLoader(dexPath, optimizedDirectory, librarySearchPath, parent);
            return dexClassLoader;
        }
    }

    /**
     * 获取DexClassLoader
     *
     * @return
     */
    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    /**
     * 通过DexClassLoader加载指定ClassName的Class
     *
     * @param clsName
     * @return
     */
    public Class loadClass(String clsName) {
        Class dexClazz = null;
        try {
            dexClazz = dexClassLoader.loadClass(clsName);
        } catch (Exception e) {
            Log.i("SIMON", e.getMessage());
        }
        return dexClazz;
    }

//    /**
//     * 获取插件apk包版本信息
//     *
//     * @param context
//     * @param apk
//     * @return
//     */
//    private PluginVersionInfo getPluginVersionInfo(Context context, File apk) {
//        if (context == null || apk == null) {
//            return null;
//        }
//
//        PackageManager pm = context.getPackageManager();
//
//        // 插件安装包的版本信息
//        PackageInfo pkgInfo = pm.getPackageArchiveInfo(apk.getAbsolutePath(), PackageManager.GET_META_DATA);
//        PluginVersionInfo versionInfo = null;
//        if (pkgInfo != null) {
//            versionInfo = new PluginVersionInfo();
//            versionInfo.pluginVersionCode = pkgInfo.versionCode;
//            versionInfo.pluginVersionName = pkgInfo.versionName;
//            versionInfo.supportMinSDKVersion = pkgInfo.applicationInfo.metaData.getInt("support.pluginSdk.MinVersion", 0);
//            versionInfo.supportMaxSDKVersion = pkgInfo.applicationInfo.metaData.getInt("support.pluginSdk.MaxVersion", Integer.MAX_VALUE);
//            // FBI Warning!!! 此处不要用getString,因为versioncode系统自动默认转为int，getString会导致取不到数字类型的value
//            String unsupportVersions = String.valueOf(pkgInfo.applicationInfo.metaData.get("unsupport.pluginSdk.Version"));
//            if (!TextUtils.isEmpty(unsupportVersions)) {
//                versionInfo.unSupportSDKVersionCodes = unsupportVersions.split(",");
//            }
//        }
//
//        return versionInfo;
//    }
//
//    /**
//     * 校验插件版本是否和当前的PluginSDK版本匹配
//     *
//     * @param context
//     * @param apk
//     * @return
//     */
//    public boolean isPluginVersionMatched(Context context, File apk) {
//        PluginVersionInfo versionInfo = getPluginVersionInfo(context, apk);
//        if (versionInfo != null) {
//            boolean isUnSupportVersion = false;
//            if (versionInfo.unSupportSDKVersionCodes != null && versionInfo.unSupportSDKVersionCodes.length > 0) {
//                for (String version : versionInfo.unSupportSDKVersionCodes) {
//                    if (String.valueOf(BuildConfig.VERSION_CODE).equals(version)) {
//                        isUnSupportVersion = true;
//                        break;
//                    }
//                }
//            }
//            return (BuildConfig.VERSION_CODE <= versionInfo.supportMaxSDKVersion
//                    && BuildConfig.VERSION_CODE >= versionInfo.supportMinSDKVersion
//                    && !isUnSupportVersion);
//        } else {
//            return true;
//        }
//    }
//
//    /**
//     * 批量比较saasSDK和PluginSDK中的类是否匹配
//     *
//     * @return
//     */
//    private boolean comparePluginClassWithSDK() {
//        ReflectConfig[] enumConstants = ReflectConfig.values();
//        for (ReflectConfig item : enumConstants) {
//            // 只比对JavaBean
//            if (isJavaBean(item.getReflectClassName())) {
//                if (RefInvoke.compareClassFields(item.getReflectClass(), item.getType())) {
//                    continue;
//                } else {
//                    Log.e(TAG, "comparePluginClassWithSDK: " + item.getType().getName() + " is not match class:" + item.getReflectClassName());
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    /**
//     * 是否是JavaBean类型
//     *
//     * @param className
//     * @return
//     */
//    private boolean isJavaBean(String className) {
//        return !TextUtils.isEmpty(className) && className.startsWith("com.haima.hmcp.beans");
//    }
//
    /**
     * ResourceManager初始化
     *
     * @param context
     * @param apkPath
     */
    private void initPluginResource(Context context, String apkPath) {
        try {
            Object[] oArray = {context, apkPath};
            Class<?>[] paraClass = {Context.class, String.class};
            ReflectHelper.invokeMethod(ReflectConfig.ResourceManager.getReflectClassName(),
                    "init",
                    oArray,
                    paraClass);
        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        }
    }
}
