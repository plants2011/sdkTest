package com.hm.pluginsdk;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.hm.pluginsdk.beans.ChannelInfo;
import com.hm.pluginsdk.beans.ResolutionInfo;
import com.hm.pluginsdk.beans.UserInfo;
import com.hm.pluginsdk.listeners.OnGameIsAliveListener;
import com.hm.pluginsdk.listeners.OnGetResolutionsCallBackListener;
import com.hm.pluginsdk.listeners.OnIdcQueryCallBackListener;
import com.hm.pluginsdk.listeners.OnInitCallBackListener;
import com.hm.pluginsdk.listeners.OnSaveGameCallBackListener;
import com.hm.pluginsdk.listeners.OnSpeedTestCallBackListener;

import java.lang.reflect.Method;
import java.util.List;

/**
 * author : suyong
 * date   : 2021/7/14 17:46
 * desc   :
 * version: 1.0
 */
public class HmcpManager {

    public static boolean INIT_SUCCESS = false;
    public static String ACCESS_KEY_ID = "HMCP_ACCESS_KEY_ID";
    public static String CHANNEL_ID = "HMCP_CHANNEL_ID";
    public static String BUNDLE_ACCESS_URL = "ACCESS_URL";
    public static String BUNDLE_COUNTLY_URL = "COUNTLY_URL";
    public static String BUNDLE_HMCP_SAAS_AUTH_URL = "HMCP_SAAS_AUTH_URL";
    public static String BUNDLE_CERTIFICATE = "BUNDLE_CERTIFICATE";
    public static final String GAME_BID = "GAME_BID";

    public final String TAG = this.getClass().getSimpleName();
    Object objInstance;

    /**
     * HmcpManager实例初始化
     */
    private static class HmcpManagerAdpInstance {
        private static final HmcpManager instance = new HmcpManager();
    }

    /**
     * 获取HmcpManager实例
     *
     * @return
     */
    public static HmcpManager getInstance() {
        return HmcpManagerAdpInstance.instance;
    }

    /**
     * 构造函数
     */
    private HmcpManager() {
    }

    /**
     * HmcpManager初始化
     *
     * @param bundle
     * @param context
     * @param callBack
     */
    public void init(Bundle bundle, Context context, OnInitCallBackListener callBack) {
        try {
            Class dexClazz = ReflectConfig.HmcpManager.getReflectClass();
            Class callBackProxy = ReflectConfig.OnInitCallBackListener.getReflectClass();
            if (dexClazz == null) {
                if (callBack != null) {
                    callBack.fail("plugin init fail!");
                }
            } else {
                objInstance = RefInvoke.invokeStaticMethod(dexClazz, "getInstance");
                Object[] oArray = {bundle, context, new Object()};
                Class<?>[] paraClass = {Bundle.class, Context.class, callBackProxy};

                ReflectHelper.invokeContainsInterfaceMethod(objInstance,
                        "init",
                        oArray,
                        paraClass,
                        ReflectConfig.OnInitCallBackListener.getReflectClassName(),
                        new ReflectCallBack() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                if (method.getName().equals("success")) {
                                    callBack.success();
                                } else {
                                    callBack.fail((String) args[0]);
                                }
                                return null;
                            }
                        }
                );
            }
        } catch (Exception e) {
            Log.e(TAG, "init: Exception", e);
            if (callBack != null) {
                callBack.fail("plugin init fail! " + e.getMessage());
            }
        }
    }

    /**
     * HmcpManager初始化
     *
     * @param context
     * @param callBack
     */
    public void init(Context context, OnInitCallBackListener callBack) {
        try {
            Class dexClazz = ReflectConfig.HmcpManager.getReflectClass();
            Class callBackProxy = ReflectConfig.OnInitCallBackListener.getReflectClass();
            if (dexClazz == null) {
                if (callBack != null) {
                    callBack.fail("plugin init fail!");
                }
            } else {
                objInstance = RefInvoke.invokeStaticMethod(dexClazz, "getInstance");
                Object[] oArray = {context, new Object()};
                Class<?>[] paraClass = {Context.class, callBackProxy};
                ReflectHelper.invokeContainsInterfaceMethod(objInstance,
                        "init",
                        oArray,
                        paraClass,
                        ReflectConfig.OnInitCallBackListener.getReflectClassName(),
                        new ReflectCallBack() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                if (method.getName().equals("success")) {
                                    callBack.success();
                                } else {
                                    callBack.fail((String) args[0]);
                                }
                                return null;
                            }
                        }
                );

            }
        } catch (Exception e) {
            Log.e(TAG, "init: Exception", e);
            if (callBack != null) {
                callBack.fail("plugin init fail! " + e.getMessage());
            }
        }

    }

    /**
     * 设置uri接口
     *
     * @param bundle
     * @return
     */
    public boolean setServiceUrl(Bundle bundle) {
        boolean retv = false;
        if (objInstance == null) {
            initInstance();
        }
        if (objInstance == null) {
            return false;
        }
        Object[] oArray = {bundle};
        Class<?>[] paraClass = {Bundle.class};
        retv = (boolean) RefInvoke.invokeInstanceMethod(objInstance,
                "setServiceUrl", paraClass, oArray);
        return retv;
    }

    /**
     * HmcpManager初始化
     */
    private void initInstance() {
        try {
            Class dexClazz = ReflectConfig.HmcpManager.getReflectClass();
            if (dexClazz != null) {
                objInstance = RefInvoke.invokeStaticMethod(dexClazz, "getInstance");
            }
        } catch (Exception e) {
            Log.e(TAG, "initInstance: Exception", e);
        }
    }

    /**
     * 获取CID
     *
     * @return
     */
    public String getCloudId() {
        String ret = null;
        try {
            ret = (String) RefInvoke.invokeInstanceMethod(objInstance, "getCloudId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 实例存档
     *
     * @param packageName
     * @param userInfo
     * @param listener
     */
    public void gameArchived(String packageName, UserInfo userInfo,
                             final OnSaveGameCallBackListener listener) {
        gameArchived(packageName, userInfo, "", listener);
    }

    /**
     *
     * @param packageName 游戏包名
     * @param userInfo    用户信息
     * @param gameBID     游戏bid，适用于切bid不初始化
     * @param listener    结果回调
     */
    public void gameArchived(String packageName, UserInfo userInfo, final String gameBID,
                             final OnSaveGameCallBackListener listener) {
        try {
            Class<?> userInfoCls = ReflectConfig.UserInfo.getReflectClass();
            Object uInfo = userInfoCls.newInstance();
            RefInvoke.copyBeanByName(userInfo, uInfo);
            Object[] oArray = {packageName, uInfo, gameBID, new Object()};
            Class<?>[] paraClass = {String.class, userInfoCls, String.class, ReflectConfig.OnSaveGameCallBackListener.getReflectClass()};
            ReflectHelper.invokeContainsInterfaceMethod(objInstance,
                    "gameArchived",
                    oArray,
                    paraClass,
                    ReflectConfig.OnSaveGameCallBackListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if (method.getName().equals("success")) {
                                listener.success((boolean) args[0]);
                            } else {
                                listener.fail((String) args[0]);
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "gameArchived: Exception", e);
        }
    }

    /**
     * 实例存档状态
     *
     * @param packageName
     * @param userInfo
     * @param listener
     */
    public void getGameArchiveStatus(String packageName, UserInfo userInfo,
                                     final OnSaveGameCallBackListener listener) {
        getGameArchiveStatus(packageName, userInfo, "", listener);
    }

    /**
     *
     * @param packageName 游戏包名
     * @param userInfo    用户信息
     * @param gameBID     游戏bid，适用于切bid不初始化
     * @param listener    结果回调
     */
    public void getGameArchiveStatus(String packageName, UserInfo userInfo, final String gameBID,
                                     final OnSaveGameCallBackListener listener) {
        try {
            Class<?> userInfoCls = ReflectConfig.UserInfo.getReflectClass();
            Object uInfo = userInfoCls.newInstance();
            RefInvoke.copyBeanByName(userInfo, uInfo);

            Class<?> callBackClass = ReflectConfig.OnSaveGameCallBackListener.getReflectClass();
            Object[] oArray = {packageName, uInfo, gameBID, new Object()};
            Class<?>[] paraClass = {String.class, userInfoCls, String.class, callBackClass};
            ReflectHelper.invokeContainsInterfaceMethod(objInstance,
                    "getGameArchiveStatus",
                    oArray,
                    paraClass,
                    ReflectConfig.OnSaveGameCallBackListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if (method.getName().equals("success")) {
                                listener.success((boolean) args[0]);
                            } else {
                                listener.fail((String) args[0]);
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "getGameArchiveStatus: Exception", e);
        }
    }

    /**
     * 获取Saas-Sdk版本
     *
     * @return
     */
    public String getSDKVersion() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Log.i(TAG, "getSDKVersion ");
        return (String) RefInvoke.invokeInstanceMethod(objInstance, "getSDKVersion",
                paraClass, oArray);
    }

    /**
     *
     * @param isIpv6         是否是Ipv6
     * @param duration       检测速度所用时间，单位为s
     * @param clientISP      运营商名称
     * @param clientProvince 当前定位省份
     * @param clientCity     当前定位城市
     * @param listener       结果回调
     */
    public void testSpeed(boolean isIpv6, final int duration, final String clientISP,
                          final String clientProvince, final String clientCity,
                          final OnSpeedTestCallBackListener listener) {
        testSpeed(isIpv6, duration, clientISP, clientProvince, clientCity, "", listener);
    }

    /**
     * 兼容IPV6环境测速,对用户提供
     */
    public void testSpeed(boolean isIpv6, final int duration, final String clientISP,
                          final String clientProvince, final String clientCity, final String gameBID,
                          final OnSpeedTestCallBackListener listener) {
        try {
            Object[] oArray = {isIpv6, duration, clientISP, clientProvince, clientCity, gameBID, new Object()};
            Class<?>[] paraClass = {boolean.class, int.class, String.class, String.class, String.class, String.class,
                    ReflectConfig.OnSpeedTestCallBackListener.getReflectClass()};
            ReflectHelper.invokeContainsInterfaceMethod(objInstance,
                    "testSpeed",
                    oArray, paraClass, ReflectConfig.OnSpeedTestCallBackListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if (method.getName().equals("onComplete")) {
                                listener.onComplete((boolean) args[0], (int) args[1], (int) args[2]);
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "testSpeed: Exception", e);
        }
    }

    /**
     * 获取分辨率列表
     *
     * @param packageName 游戏包名
     * @param mAppChannel AppChannel
     * @param listener    回调监听
     */
    public void getResolutionInfos(String packageName, String mAppChannel, final OnGetResolutionsCallBackListener listener) {
        getResolutionInfos(packageName, mAppChannel, "", listener);
    }

    /**
     * 获取分辨率列表
     *
     * @param packageName 游戏包名
     * @param mAppChannel AppChannel
     * @param listener    回调监听
     */
    public void getResolutionInfos(String packageName, String mAppChannel, final String gameBID, final OnGetResolutionsCallBackListener listener) {
        try {
            Object[] oArray = {packageName, mAppChannel, gameBID, new Object()};
            Class<?>[] paraClass = {String.class, String.class, String.class, ReflectConfig.OnGetResolutionsCallBackListener.getReflectClass()};
            ReflectHelper.invokeContainsInterfaceMethod(objInstance,
                    "getResolutionInfos", oArray, paraClass, ReflectConfig.OnGetResolutionsCallBackListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) {
                            if (method.getName().equals("success")) {
                                listener.success((List<ResolutionInfo>) (RefInvoke.convertListByType((List<? extends Object>) args[0], ResolutionInfo.class)));
                            } else {
                                listener.fail((String) args[0]);
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "getResolutionInfos: Exception", e);
        }
    }

    /**
     * 查询用户是否有正在进行，实例未被释放的游戏
     *
     * @param userInfo 用户信息
     * @param listener 回调监听
     */
    public void checkPlayingGame(UserInfo userInfo, final OnGameIsAliveListener listener) {
        checkPlayingGame(userInfo, "", listener);
    }


    /**
     * 查询用户是否有正在进行，实例未被释放的游戏
     *
     * @param userInfo 用户信息
     * @param gameBID  游戏bid，适用于切bid不更新
     * @param listener 回调监听
     */
    public void checkPlayingGame(UserInfo userInfo, final String gameBID, final OnGameIsAliveListener listener) {
        try {
            Object uInfo = RefInvoke.convertObject(userInfo, ReflectConfig.UserInfo.getReflectClass());
            Object[] oArray = {uInfo, gameBID, new Object()};
            Class<?>[] paraClass = {ReflectConfig.UserInfo.getReflectClass(), String.class, ReflectConfig.OnGameIsAliveListener.getReflectClass()};

            ReflectHelper.invokeContainsInterfaceMethod(objInstance,
                    "checkPlayingGame", oArray, paraClass, ReflectConfig.OnGameIsAliveListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) {
                            if ("success".equals(method.getName())) {
                                listener.success((List<ChannelInfo>) (RefInvoke.convertListByType((List<? extends Object>) args[0], ChannelInfo.class)));
                            } else {
                                listener.fail((String) args[0]);
                            }
                            return null;
                        }
                    });

        } catch (Exception e) {
            Log.e(TAG, "checkPlayingGame: Exception", e);
        }
    }

    /**
     * 释放CID，不推荐使用
     *
     * @param packageName 游戏包名，传入要释放的对应游戏包名
     * @param cloudId     要释放的cid
     * @param cToken      用来校验参数的有效性
     * @param appChannel  如果存在多款游戏同包名的情况，可以通过appChannel区分
     * @param userInfo2   用户登录信息
     * @param listener    结果回调
     */

    public void setReleaseCid(String packageName, String cloudId, String cToken, String appChannel, UserInfo userInfo2, final OnSaveGameCallBackListener listener) {
        setReleaseCid(packageName, cloudId, cToken, appChannel, userInfo2, "", listener);
    }

    /**
     * 释放CID，不推荐使用
     *
     * @param packageName 游戏包名，传入要释放的对应游戏包名
     * @param cloudId     要释放的cid
     * @param cToken      用来校验参数的有效性
     * @param appChannel  如果存在多款游戏同包名的情况，可以通过appChannel区分
     * @param userInfo2   用户登录信息
     * @param gameBID     真实游戏的bid
     * @param listener    结果回调
     */

    public void setReleaseCid(String packageName, String cloudId, String cToken, String appChannel, UserInfo userInfo2, final String gameBID, final OnSaveGameCallBackListener listener) {
        try {
            Class<?> callBackClass = ReflectConfig.OnSaveGameCallBackListener.getReflectClass();

            Object uInfo = RefInvoke.convertObject(userInfo2, ReflectConfig.UserInfo2.getReflectClass());
            Object[] oArray = {packageName, cloudId, cToken, appChannel, uInfo, gameBID, new Object()};
            Class<?>[] paraClass = {String.class, String.class, String.class, String.class,
                    ReflectConfig.UserInfo2.getReflectClass(), String.class, callBackClass};

            ReflectHelper.invokeContainsInterfaceMethod(objInstance,
                    "setReleaseCid",
                    oArray,
                    paraClass,
                    ReflectConfig.OnSaveGameCallBackListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) {
                            if ("success".equals(method.getName())) {
                                listener.success((boolean) args[0]);
                            } else {
                                listener.fail((String) args[0]);
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "setReleaseCid: Exception", e);
        }
    }


    /**
     * 获取SaasAuthURL
     *
     * @return
     */
    public String getHmcpSaasAuthUrl() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Log.i(TAG, "getHmcpSaasAuthUrl ");
        return (String) RefInvoke.invokeInstanceMethod(objInstance, "getHmcpSaasAuthUrl",
                paraClass, oArray);
    }


    /**
     * 获取分辨率数据
     *
     * @return
     */
    public List<ResolutionInfo> getResolutionDatas() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Log.i(TAG, "getResolutionDatas ");
        List result = (List) RefInvoke.invokeInstanceMethod(objInstance, "getResolutionDatas",
                paraClass, oArray);
        return RefInvoke.convertListByType(result, ResolutionInfo.class);
    }

    /**
     * idc查询接口
     * @param isIpv6 isIpv6
     * @param clientISP 运营商
     * @param clientProvince 省份
     * @param clientCity 城市
     * @param listener 监听
     */
    public void idcQuery(boolean isIpv6, final String clientISP, final String clientProvince,
                         final String clientCity, final OnIdcQueryCallBackListener listener) {
        idcQuery(isIpv6, clientISP, clientProvince, clientCity, "", listener);
    }

    /**
     * idc查询接口
     * @param isIpv6 isIpv6
     * @param clientISP 运营商
     * @param clientProvince  省份
     * @param clientCity 城市
     * @param gameBID bid,支持初始化后切换bid后查询
     * @param listener 监听
     */
    public void idcQuery(boolean isIpv6, final String clientISP, final String clientProvince,
                         final String clientCity, String gameBID, final OnIdcQueryCallBackListener listener) {
        try {
            Class<?> callBackClass = ReflectConfig.OnIdcQueryCallBackListener.getReflectClass();

            Object[] oArray = {isIpv6, clientISP, clientProvince, clientCity, gameBID, new Object()};
            Class<?>[] paraClass = {boolean.class, String.class, String.class, String.class, String.class, callBackClass};

            ReflectHelper.invokeContainsInterfaceMethod(objInstance,
                    "idcQuery",
                    oArray,
                    paraClass,
                    ReflectConfig.OnIdcQueryCallBackListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) {
                            if ("success".equals(method.getName())) {
                                listener.success((String) args[0],(String) args[1]);
                            } else {
                                listener.fail((String) args[0]);
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "idcQuery: Exception", e);
        }
    }
}