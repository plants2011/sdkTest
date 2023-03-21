package com.hm.pluginsdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import com.hm.pluginsdk.beans.CloudFile;
import com.hm.pluginsdk.beans.Control;
import com.hm.pluginsdk.beans.FPoint;
import com.hm.pluginsdk.beans.Message;
import com.hm.pluginsdk.beans.ResolutionInfo;
import com.hm.pluginsdk.beans.RtcSeiData;
import com.hm.pluginsdk.beans.RtcVideoDelayInfo;
import com.hm.pluginsdk.beans.RtmpVideoDelayInfo;
import com.hm.pluginsdk.beans.UserInfo;
import com.hm.pluginsdk.beans.VideoDelayInfo;
import com.hm.pluginsdk.business.TransferHelper;
import com.hm.pluginsdk.enums.CloudOperation;
import com.hm.pluginsdk.enums.CloudPlayerKeyboardStatus;
import com.hm.pluginsdk.enums.ELivingCapabilityStatus;
import com.hm.pluginsdk.enums.KeyType;
import com.hm.pluginsdk.enums.MessageType;
import com.hm.pluginsdk.enums.NetWorkState;
import com.hm.pluginsdk.enums.ScreenOrientation;
import com.hm.pluginsdk.enums.WsMessageType;
import com.hm.pluginsdk.listeners.CloudOperationListener;
import com.hm.pluginsdk.listeners.HmFrameCallback;
import com.hm.pluginsdk.listeners.HmStreamerIPCallback;
import com.hm.pluginsdk.listeners.HmcpPlayerListener;
import com.hm.pluginsdk.listeners.ISeiListener;
import com.hm.pluginsdk.listeners.OnCloudImageListListener;
import com.hm.pluginsdk.listeners.OnContronListener;
import com.hm.pluginsdk.listeners.OnLivingListener;
import com.hm.pluginsdk.listeners.OnSendMessageListener;
import com.hm.pluginsdk.listeners.OnSendWsMessageListener;
import com.hm.pluginsdk.listeners.OnUpdataGameUIDListener;
import com.hm.pluginsdk.widgets.HmcpIJKVideoViewListenerIml;
import com.hm.pluginsdk.widgets.IHmcpIJKVideoView;

import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * author : suyong
 * date   : 2021/7/15 17:50
 * desc   :
 * version: 1.0
 */
public class HmcpVideoView {

    public static final int OPERATION_NETWORK_ERROR = 999;
    public static final int OPERATION_QUEUING = 1000;
    public static final int OPERATION_INTERVAL_TIME = 1;
    public static final int OPERATION_KICKED = 2;
    public static final int OPERATION_HMCP_ERROR = 3;
    public static final int OPERATION_GAME_OVER = 4;
    public static final int OPERATION_STREAM_URL = 5;
    public static final int OPERATION_QUEUE = 6;
    public static final int OPERATION_REFUSE_QUEUE = 7;
    public static final int OPERATION_INSTANCE_CRASH = 8;
    public static final int OPERATION_WAITING = 9;
    public static final int OPERATION_INSTANCE_INITIATING = 10;
    public static final int OPERATION_REFRESH_STOKEN = 11;
    public static final int OPERATION_SWITCHING_RESOLUTION = 12;
    public static final int OPERATION_RESUME_SAAS_SERVER = 13;       //恢复服务
    public static final int OPERATION_READY_PAUSE_SAAS_SERVER = 14;  //5分钟后停止服务
    public static final int OPERATION_PAUSE_SAAS_SERVER = 15;        //即将停止服务，针对停服前5分钟内进入的用户b
    public static final int OPERATION_PAUSED_SAAS_SERVER = 16;       //停止服务
    public static final int OPERATION_GAME_CRASH = 18;                  //游戏crash或者返回退出
    public static final int OPERATION_GAME_RESTART = 19;                //游戏进后台
    public static final int OPERATION_OPEN_MORE_SAME_GAME = 21;       //停止服务
    public static final int OPERATION_FORCED_OFFLINE = 22;       //停止服务
    public static final int OPERATION_GAME_ARCHIVING = 32;       //正在存档中
    public static final int OPERATION_GAME_TIME_COUNT_DOWN = 35;    //游戏时长提示通知
    public static final int OPERATION_GAME_TIME_UPDATE = 36;    //游戏内更新时长
    public static final int OPERATION_GAME_TIME_HIGHLIGHT = 37;     //游戏可玩时长
    public static final int OPERATION_NO_INPUT_TIMEOUT = 40;       //提前通知用户长时间误操作
    public static final int OPERATION_DEBUG_SWITCH = 100;
    public static final int OPERATION_TOAST_NO_INPUT = 50;
    public static final int OPERATION_TOAST_TIMEOUT = 99;
    public static final int OPERATION_NET_TRACE_ROUTE = 45;  //开始trace功能
    public static final int OPERATION_STATE_CHANGE_REASON = 46;  //服务端关闭cid

    public static final int BITMAP_PURE_COLOR = 1;//纯色
    public static final int BITMAP_PURE_TRANSPARENCY_COLOR = 2;//纯透明色
    public static final int BITMAP_NORMAL_COLOR = 3;//正常播流页面
    public static final int BITMAP_PURE_BLACK_COLOR = 4;//黑色


    //-----------------------游戏手柄相关-----------------------

    public static final int STATE_DEFAULT = 0;
    public static final int STATE_CONNECT_PREPARED = 1;
    public static final int STATE_CLOUD_SERVICE_PREPARED = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_SHOW_PROMPT = 4;
    public static final int STATE_GAME_OVER = 5;
    /**
     * app通过bundle传入的KEY
     */
    public static final String USER_ID = "userid";
    public static final String TIPS_MSG = "message";
    public static final String ORIENTATION = "orientation";
    public static final String PLAY_TIME = "playTime";
    public static final String PRIORITY = "priority";
    public static final String APP_ID = "appId";
    public static final String APP_NAME = "appName";
    public static final String APP_CHANNEL = "appChannel";
    public static final String C_TOKEN = "cToken";
    public static final String EXTRA_ID = "extraId";
    public static final String INTERNET_SPEED = "internetSpeed";
    public static final String PAY_STR = "payStr";
    public static final String BITE_RATE = "bitRate";
    public static final String ARCHIVED = "archived";
    public static final String PAY_PROTO_DATA = "protoData";
    public static final String FPS_PERIOD = "fpsPeriod";
    public static final String BAND_WIDTH_PERIOD = "bandWidthPeriod";
    public static final String BAND_WIDTH_PEAK = "bandWidthPeak";
    public static final String DECODE_TIME_PERIOD = "decodeTimePeriod";
    public static final String VIEW_RESOLUTION_WIDTH = "viewResolutionWidth";
    public static final String VIEW_RESOLUTION_HEIGHT = "viewResolutionHeight";
    public static final String IS_SHOW_TIME = "isShowTime";//显示operation 37权限
    public static final String NO_INPUT_LIMIT_TIME = "noInputLimitTime";//无操作限制时长
    public static final String C_ID = "cid";// 用户决定是否传入217接口获取到的cid
    public static final String ALLOW_COMPATIBLE_IPV6 = "allowCompatibleIpv6";// 用户决定是否允许兼容IPV6
    public static final String VERTICAL_BACKGROUND = "verticalBackground";
    public static final String STREAM_TYPE = "streamType";
    public static final String CID_CACHE_INTERVAL = "cidCacheInterval"; //超时重连不携带CID时长，单位s
    public static final String DECODE_TYPE = "decodeType";//只对rtmp流类型生效
    public static final String ARCHIVE_FROM_USER_ID = "archiveFromUserId";//只对rtmp流类型生效
    public static final String ARCHIVE_FROM_BID = "archiveFromBid";//只对rtmp流类型生效
    /**
     * 是否是自定义播放器的key，value为boolean
     */
    public static final String IS_CUSTOM_PLAYER = "isCustomPlayer";//是否是自定义播放器

    public static final String DEMO_TEST_INTERFACE_ID = "demoTestInterfaceId";
    public static final String DEMO_TEST_INSTANCE_ID = "demoTesInstanceId";

    public static final int MAX_CONNECT_WS_SERVER_COUNT = 5;
    public static final String CLIENT_ISP = "clientISP";//运营商名称
    public static final String CLIENT_PROVINCE = "clientProvince";//省份
    public static final String CLIENT_CITY = "clientCity";//城市

    public static final int DECODE_HARDWARE = 1;//rtmp硬解码
    public static final int DECODE_SOFTWARE = 0;//rtmp软解码

    public static final String COMPONENT_TYPE = "componentType"; //0：activity，1：Service，2：broadcast
    public static final String COMPONENT_ACTION = "action";
    public static final String COMPONENT_NAME = "componentName";
    public static final String COMPONENT_EXTRA_DATA = "extraData";
    public static final String USER_DEVICE_INFO = "userDeviceInfo";

    public static final String GPS_LONGITUDE = "longitude";
    public static final String GPS_LATITUDE = "latitude";
    public static final String GPS_ALTITUDE = "altitude";
    public static final String GPS_SPEED = "speed";
    public static final String GPS_COURSE = "course";

    public static final int COMPONENT_TYPE_ACTIVITY = 0;
    public static final int COMPONENT_TYPE_SERVICE = 1;
    public static final int COMPONENT_TYPE_BROADCAST = 2;

    /**
     * 帧延迟数据sei
     */
    public static final String SEI = "sei";
    /**
     * 帧延迟数据sei 开启
     */
    public static final int SEI_ON = 1;
    /**
     * 帧延迟数据sei 关闭
     */
    public static final int SEI_OFF = 0;

    /**
     * MSG
     */
    public static final int MSG_HMCP_REPLAY = 0;
    public static final int MSG_HMCP_STOP = 1;
    public static final int MSG_HMCP_GAMEOVER = 2;
    public static final int MSG_HMCP_REFRESH_STOKEN = 3;
    public static final int CLICK_BUTTON_REPLAY = 4;
    public static final int CLICK_BUTTON_NOINPUT = 6;
    public static final int TIMER_RUN = 222;
    public static final int MSG_SET_SCREEN_BRIGHTNESS = 8;
    public static final int FIRST_FRAME_ARRIVAL = 7;


    //设备类型
    public static final int INPUT_DEVICE_NONE = 0;
    public static final int INPUT_DEVICE_REMOTE_CONTROL = 1;//遥控器
    public static final int INPUT_DEVICE_KEY_MOUSE = 2;//键鼠
    public static final int INPUT_DEVICE_GAMEPAD = 3;//手柄
    public static final int INPUT_DEVICE_VIRTUAL_GAMEPAD = 4;//虚拟手柄-虚拟touch

    private static final String TAG = HmcpVideoView.class.getSimpleName();
    private Class dexClazz;
    private Context mContext;
    private Object mVideoView;

    /**
     * 插件包中的HmcpVideoView，对应SaasSDK中的BaseVideoView
     *
     * @param context
     */
    public HmcpVideoView(Context context) {
        this.mContext = context;
    }

    /**
     * 获取View对象
     *
     * @return
     */
    public View getRealView() {
        Object instance = null;
        Class[] p2 = {Context.class};
        dexClazz = ReflectConfig.HmcpVideoView.getReflectClass();
        try {
            if (dexClazz != null) {
                Constructor c2 = dexClazz.getConstructor(p2);
                instance = c2.newInstance(mContext);
            }
        }catch (Exception e) {
            Log.e(TAG, "getRealView: Exception", e);
        }
        mVideoView = instance;
        return (View) mVideoView;
    }

    /**
     * 设置用户账户信息
     *
     * @param userInfo
     */
    public void setUserInfo(UserInfo userInfo) {
        Object uInfo = null;
        try {
            uInfo = RefInvoke.convertObject(userInfo, ReflectConfig.UserInfo.getReflectClass());
        } catch (Exception e) {
            Log.e(TAG, "setUserInfo: Exception", e);
        }

        Object[] oArray = {uInfo};
        Class<?>[] paraClass = {ReflectConfig.UserInfo.getReflectClass()};
        RefInvoke.invokeInstanceMethod(mVideoView, "setUserInfo", paraClass, oArray);
    }

    /**
     * 游戏退后台
     */
    public void pauseGame() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        RefInvoke.invokeInstanceMethod(mVideoView, "pauseGame", paraClass, oArray);
    }

    /**
     * 重连接
     */
    public void reconnection() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        RefInvoke.invokeInstanceMethod(mVideoView, "reconnection", paraClass, oArray);
    }

    /**
     * 设置配置信息
     *
     * @param config
     */
    public void setConfigInfo(String config) {
        Object[] oArray = {config};
        Class<?>[] paraClass = {String.class};
        RefInvoke.invokeInstanceMethod(mVideoView, "setConfigInfo", paraClass, oArray);
    }

    /**
     * 播放
     */
    public void play() {
        Log.i(TAG, "PLAY ");
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        RefInvoke.invokeInstanceMethod(mVideoView, "play", paraClass, oArray);
    }

    /**
     * 播放
     *
     * @param bundle
     */
    public void play(Bundle bundle) {
        if (bundle == null) {
            Log.i(TAG, "play: bundle ==null");
        } else {
            Log.i(TAG, "PLAY bundle " + bundle.toString());
        }
        // Bundle参数类型转换
        filterBundleParams(bundle);
        Object[] oArray = {bundle};
        Class<?>[] paraClass = {Bundle.class};
        RefInvoke.invokeInstanceMethod(mVideoView, "play", paraClass, oArray);
    }

    /**
     * Bundle传参非简单数据类型需要做类型转换。
     * 目前为止只有横竖屏为枚举，其他参数均为简单数据类型
     *
     * @param bundle
     */
    private void filterBundleParams(Bundle bundle) {
        // 此处Bundle传参只有横竖屏这个参数是枚举类型，需要做类型转换。其他参数均为简单数据类型
        if (bundle.containsKey(HmcpVideoView.ORIENTATION)) {
            Class<? extends Enum> orientationClass = (Class<? extends Enum>) ReflectConfig.ScreenOrientation.getReflectClass();
            bundle.putSerializable(HmcpVideoView.ORIENTATION, RefInvoke.convertEnum((ScreenOrientation) bundle.getSerializable(HmcpVideoView.ORIENTATION), orientationClass));
        }
    }

    /**
     * 设置HmcpPlayerListener回调接口
     *
     * @param listener
     */
    public void setHmcpPlayerListener(HmcpPlayerListener listener) {
        Object[] oArray = {new Object()};
        Class<?> callBackClass = ReflectConfig.HmcpPlayerListener.getReflectClass();
        Class<?>[] paraClass = {callBackClass};
        try {
            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "setHmcpPlayerListener",
                    oArray,
                    paraClass,
                    ReflectConfig.HmcpPlayerListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            super.invoke(proxy, method, args);
                            StringBuilder printLog = new StringBuilder();
                            printLog.append(" args");
                            if (args != null && args.length > 0) {
                                printLog.append(" args length ");
                                printLog.append(args.length);
                                for (int i = 0; i < args.length; i++) {
                                    if (args[i] == null) {
                                        Log.i(TAG, "invoke: args[i] == null,continue");
                                        continue;
                                    }
                                    printLog.append(" args");
                                    printLog.append(i);
                                    printLog.append(" ");
                                    printLog.append(args[i].toString());
                                }
                            }
                            Log.i(TAG, "callback invoke method " + method.getName() + printLog.toString());
                            switch (method.getName()) {
                                case "onError":
                                    listener.onError((int) args[0], (String) args[1]);
                                    break;
                                case "onSuccess":
                                    listener.onSuccess();
                                    break;
                                case "onExitQueue":
                                    listener.onExitQueue();
                                    break;
                                case "onMessage":
                                    listener.onMessage(RefInvoke.convertObject(args[0], Message.class));
                                    break;
                                case "onSceneChanged":
                                    listener.onSceneChanged((String) args[0]);
                                    break;
                                case "onNetworkChanged":
                                    listener.onNetworkChanged(RefInvoke.convertEnum((Enum<?>) args[0], NetWorkState.class));
                                    break;
                                case "onPlayStatus":
                                    listener.onPlayStatus((int) args[0], (long) args[1], (String) args[2]);
                                    break;
                                case "HmcpPlayerStatusCallback":
                                    listener.HmcpPlayerStatusCallback((String) args[0]);
                                    break;
                                case "onPlayerError":
                                    listener.onPlayerError((String) args[0], (String) args[1]);
                                    break;
                                case "onInputMessage":
                                    listener.onInputMessage((String) args[0]);
                                    break;
                                case "onInputDevice":
                                    listener.onInputDevice((int) args[0], (int) args[1]);
                                    break;
                                case "onPermissionNotGranted":
                                    listener.onPermissionNotGranted((String) args[0]);
                                    break;
                                case "onCloudDeviceStatus":
                                    listener.onCloudDeviceStatus((String) args[0]);
                                    break;
                                case "onInterceptIntent":
                                    listener.onInterceptIntent((String) args[0]);
                                    break;
                                case "onCloudPlayerKeyboardStatusChanged":
                                    listener.onCloudPlayerKeyboardStatusChanged(RefInvoke.convertEnum((Enum<?>) args[0], CloudPlayerKeyboardStatus.class));
                                    break;
                                default:
                                    Log.e(TAG, "invoke: default " + method.getName() );
                                    break;
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "setHmcpPlayerListener: Exception", e);
        }
    }

    /**
     * onStart回调
     */
    public void onStart() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Log.i(TAG, "onStart ");
        RefInvoke.invokeInstanceMethod(mVideoView, "onStart", paraClass, oArray);
    }

    /**
     * onRestart回调
     *
     * @param time
     */
    public void onRestart(int time) {
        Object[] oArray = {time};
        Class<?>[] paraClass = {int.class};
        Log.i(TAG, "onRestart ");
        RefInvoke.invokeInstanceMethod(mVideoView, "onRestart", paraClass, oArray);
    }

    /**
     * onResume回调
     */
    public void onResume() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Log.i(TAG, "onResume ");
        RefInvoke.invokeInstanceMethod(mVideoView, "onResume", paraClass, oArray);
    }

    /**
     * onPause回调
     */
    public void onPause() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Log.i(TAG, "onPause ");
        RefInvoke.invokeInstanceMethod(mVideoView, "onPause", paraClass, oArray);
    }

    /**
     * onStop回调
     */
    public void onStop() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Log.i(TAG, "onStop ");
        RefInvoke.invokeInstanceMethod(mVideoView, "onStop", paraClass, oArray);
    }

    /**
     * onDestroy回调
     */
    public void onDestroy() {
        Log.i(TAG, "onDestroy ");
        RefInvoke.invokeInstanceMethod(mVideoView, "onDestroy");
    }

    /**
     * 游戏重启
     *
     * @param time
     */
    public void restartGame(int time) {
        Object[] oArray = {time};
        Class<?>[] paraClass = {int.class};
        Log.i(TAG, "restartGame time = " + time);
        RefInvoke.invokeInstanceMethod(mVideoView, "restartGame", paraClass, oArray);
    }

    /**
     * 重新申请游戏
     */
    public void startPlay() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Log.i(TAG, "startPlay ");
        RefInvoke.invokeInstanceMethod(mVideoView, "startPlay", paraClass, oArray);
    }

    /**
     * 重新申请游戏
     *
     * @param isAhead 是否插队开始游戏
     */
    public void startPlay(boolean isAhead) {
        Object[] oArray = {isAhead};
        Class<?>[] paraClass = {boolean.class};
        Log.i(TAG, "startPlay isAhead" + isAhead);
        RefInvoke.invokeInstanceMethod(mVideoView, "startPlay", paraClass, oArray);
    }

    /*
    添加控件
     */
    public void addView(View child) {
        ((ViewGroup) mVideoView).addView(child);
    }

    /**
     * 进入排队队列
     */
    public void entryQueue() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Log.i(TAG, "entryQueue ");
        RefInvoke.invokeInstanceMethod(mVideoView, "entryQueue", paraClass, oArray);
    }

    /**
     * 退出排队
     */
    public void exitQueue() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Log.i(TAG, "exitQueue ");
        RefInvoke.invokeInstanceMethod(mVideoView, "exitQueue", paraClass, oArray);
    }

    /**
     * 更新GameUID
     *
     * @param bundle
     * @param listener
     */
    public void updateGameUID(final Bundle bundle, final OnUpdataGameUIDListener listener) {
        Object[] oArray = {bundle, new Object()};
        Class<?>[] paraClass = {Bundle.class, ReflectConfig.OnUpdataGameUIDListener.getReflectClass()};

        try {
            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "updateGameUID",
                    oArray,
                    paraClass,
                    ReflectConfig.OnUpdataGameUIDListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            super.invoke(proxy, method, args);
                            if (method.getName().equals("success")) {
                                listener.success((boolean) args[0]);
                            } else {
                                listener.fail((String) args[0]);
                            }
                            Log.i(TAG, "Method NAME " + method.getName());
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "updateGameUID: Exception", e);
        }
    }

    /**
     * 释放VideoView
     */
    public void release() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Log.i(TAG, "release");
        RefInvoke.invokeInstanceMethod(mVideoView, "release", paraClass, oArray);
    }

    /**
     * 设置静音
     *
     * @param isMute
     */
    public void setAudioMute(boolean isMute) {
        Object[] oArray = {isMute};
        Class<?>[] paraClass = {boolean.class};
        Log.i(TAG, "setAudioMute");
        RefInvoke.invokeInstanceMethod(mVideoView, "setAudioMute", paraClass, oArray);
    }

    /**
     * 是否打开音频流
     *
     * @return true 有音
     */
    public boolean isOnSound() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Object isOnSound = RefInvoke.invokeInstanceMethod(mVideoView, "isOnSound", paraClass, oArray);
        Log.i(TAG, "isOnSound = " + isOnSound);
        if (isOnSound == null) {
            return false;
        }
        return (boolean) isOnSound;
    }

    /**
     * 获取分辨率列表
     *
     * @return
     */
    public List<ResolutionInfo> getResolutionList() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        List<ResolutionInfo> info = null;
        try {
            Object getResolutionList = RefInvoke.invokeInstanceMethod(mVideoView, "getResolutionList", paraClass, oArray);
            Log.i(TAG, "getResolutionInfo " + getResolutionList);
            info = (List<ResolutionInfo>) RefInvoke.convertListByType((List<?>) getResolutionList, ResolutionInfo.class);
        } catch (Exception e) {
            Log.e(TAG, "getResolutionList: Exception", e);
        }
        return info;
    }


    /**
     * 切换码率
     *
     * @param level
     * @param resolution
     * @param rate
     */
    public void onSwitchResolution(int level, ResolutionInfo resolution, int rate) {
        try {
            Class<?> targetClass = ReflectConfig.ResolutionInfo.getReflectClass();
            Object resolutionParam = RefInvoke.convertObject(resolution, targetClass);

            Object[] oArray = {level, resolutionParam, rate};
            Log.i(TAG, "onSwitchResolution : level = " + level + " resolution = " + resolution + " rate = " + rate);
            Class<?>[] paraClass = {int.class, targetClass, int.class};
            RefInvoke.invokeInstanceMethod(mVideoView, "onSwitchResolution", paraClass, oArray);
        } catch (Exception e) {
            Log.e(TAG, "onSwitchResolution: Exception", e);
        }
    }

    /**
     * 发送消息
     *
     * @param payload
     * @param type
     * @param listener
     */
    public void sendMessage(String payload, MessageType type, OnSendMessageListener listener) {
        Class<?> listenerCls = ReflectConfig.OnSendMessageListener.getReflectClass();
        Class<? extends Enum> messageTypeCls = (Class<? extends Enum>) ReflectConfig.MessageType.getReflectClass();

        Object messageTypeParam = RefInvoke.convertEnum(type, messageTypeCls);

        Object[] oArray = {payload, messageTypeParam, new Object()};
        Class<?>[] paraClass = {String.class, messageTypeCls, listenerCls};

        try {
            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "sendMessage",
                    oArray,
                    paraClass,
                    ReflectConfig.OnSendMessageListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            super.invoke(proxy, method, args);
                            listener.result((boolean) args[0], (String) args[1]);
                            Log.i(TAG, "Method NAME " + method.getName());
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "sendMessage: Exception", e);
        }
    }

    /**
     * 获取播放器延时信息
     *
     * @return
     */
    public VideoDelayInfo getClockDiffVideoLatencyInfo() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        VideoDelayInfo info = new VideoDelayInfo();
        try {
            Object result = RefInvoke.invokeInstanceMethod(mVideoView, "getClockDiffVideoLatencyInfo", paraClass, oArray);
            info = convertVideoDelayInfo(result);
            if (result == null) {
                Log.i(TAG, "getClockDiffVideoLatencyInfo result == null,return default VideoDelayInfo");
            } else {
                Log.i(TAG, "getClockDiffVideoLatencyInfo " + info.toReportString());
            }
        } catch (Exception e) {
            Log.e(TAG, "getClockDiffVideoLatencyInfo: Exception ", e);
        }
        return info;
    }

    /**
     * VideoDelayInfo类型的对象转换
     *
     * @param src
     * @return
     */
    private VideoDelayInfo convertVideoDelayInfo(Object src) {
        try {
            VideoDelayInfo info = new VideoDelayInfo();

            if (src == null) {
                Log.d(TAG, "convertVideoDelayInfo: src == null ,return default VideoDelayInfo");
                return info;
            }

            if (TextUtils.equals(src.getClass().getName(), ReflectConfig.RtcVideoDelayInfo.getReflectClassName())) {
                RtcVideoDelayInfo rtcVideoDelayInfo = RefInvoke.convertObject(src, RtcVideoDelayInfo.class);
                Field relRtcVideoDelayInfoField = rtcVideoDelayInfo.getClass().getDeclaredField("relRtcVideoDelayInfo");
                relRtcVideoDelayInfoField.setAccessible(true);
                relRtcVideoDelayInfoField.set(rtcVideoDelayInfo,src);
                info = rtcVideoDelayInfo;
            } else if (TextUtils.equals(src.getClass().getName(), ReflectConfig.RtmpVideoDelayInfo.getReflectClassName())) {
                RtmpVideoDelayInfo rtmpVideoDelayInfo = RefInvoke.convertObject(src, RtmpVideoDelayInfo.class);
                Field relRtmpVideoDelayInfoField = rtmpVideoDelayInfo.getClass().getDeclaredField("relRtmpVideoDelayInfo");
                relRtmpVideoDelayInfoField.setAccessible(true);
                relRtmpVideoDelayInfoField.set(rtmpVideoDelayInfo,src);
                info = rtmpVideoDelayInfo;
            } else {
                info = RefInvoke.convertObject(src, VideoDelayInfo.class);
            }

            return info;
        } catch (Exception e) {
            Log.e(TAG, "convertVideoDelayInfo: Exception ", e);
        }
        return new VideoDelayInfo();
    }

    /**
     * 获取延迟接口1
     *
     * @return 0 默认值
     */
    public int getVideoLatency() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        int delay = 0;
        try {
            delay = (int) RefInvoke.invokeInstanceMethod(mVideoView,
                    "getVideoLatency", paraClass, oArray);
        } catch (Exception e) {
            Log.e(TAG, "getVideoLatency: Exception", e);
        }
        return delay;
    }

    /**
     * 获取Input地址
     *
     * @return
     */
    public String getInputUrl() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        String url = (String) RefInvoke.invokeInstanceMethod(mVideoView,
                "getInputUrl", paraClass, oArray);
        return url;
    }

    /**
     * 获取虚拟手柄二维码数据
     *
     * @return
     */
    public String getQRCodeData() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        String url = (String) RefInvoke.invokeInstanceMethod(mVideoView,
                "getQRCodeData", paraClass, oArray);
        return url;
    }

    /**
     * 对应用层开放的返回游戏的接口
     * 针对有些第三方登陆多级 无法返回的情况
     */
    public void backToGame() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        RefInvoke.invokeInstanceMethod(mVideoView,
                "backToGame", paraClass, oArray);
    }

    /**
     * 获取用户最后一次指令时间戳
     *
     * @return 时间戳正常调用，-1为未开始计时，0长链接出现异常
     */
    public long getLastUserOperationTimestamp() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        long time = 0;
        try {
            time = (long) RefInvoke.invokeInstanceMethod(mVideoView,
                    "getLastUserOperationTimestamp", paraClass, oArray);
        } catch (Exception e) {
            Log.e(TAG, "getLastUserOperationTimestamp: Exception", e);
        }
        return time;
    }

    /**
     * 重置无操作超时计时器
     *
     * @param needUpdateTimestamp true 更新用户最后一次操作时间戳
     *                            false 不更新
     * @return 1正常调用 ，0长链接出现异常
     */
    public int resetInputTimer(boolean needUpdateTimestamp) {
        Object[] oArray = {needUpdateTimestamp};
        Class<?>[] paraClass = {boolean.class};
        final int failResult = 0;
        try {
            return (int) RefInvoke.invokeInstanceMethod(mVideoView,
                    "resetInputTimer", paraClass, oArray);
        } catch (Exception e) {
            Log.e(TAG, "resetInputTimer: Exception", e);
        }
        return failResult;
    }

    /**
     * 重置无操作超时计时器
     * 默认更新用户最后一次操作时间戳
     *
     * @return 1正常调用 ，0长链接出现异常
     */
    public int resetInputTimer() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        final int failResult = 0;
        try {
            return (int) RefInvoke.invokeInstanceMethod(mVideoView,
                    "resetInputTimer", paraClass, oArray);
        } catch (Exception e) {
            Log.e(TAG, "resetInputTimer: Exception ", e);
        }
        return failResult;
    }

    /**
     * 获取当前截屏
     *
     * @return 截屏图片
     */
    public Bitmap getShortcut() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Bitmap ret = (Bitmap) RefInvoke.invokeInstanceMethod(mVideoView,
                "getShortcut", paraClass, oArray);
        return ret;
    }

    /**
     * 当前截屏的bitmap对象是否黑屏
     *
     * @param bitmap
     * @param percent
     * @return
     */
    public int checkBitmap(Bitmap bitmap, float percent) {
        Object[] oArray = {bitmap, percent};
        Class<?>[] paraClass = {Bitmap.class, float.class};
        int ret = (int) RefInvoke.invokeInstanceMethod(mVideoView,
                "checkBitmap", paraClass, oArray);
        return ret;
    }

    /**
     * 获取视频流地址
     *
     * @return
     */
    public String getStreamUrl() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        String ret = (String) RefInvoke.invokeInstanceMethod(mVideoView,
                "getStreamUrl", paraClass, oArray);
        return ret;
    }

    /**
     * 云游戏结束信息上报接口
     *
     * @param finishCode
     * @param cid
     * @param pkgName
     * @param appChannel
     * @param appVersion
     * @param imei
     * @param deviceId
     * @param gameId
     */
    public void reportFinishInfo(int finishCode, String cid, String pkgName, String appChannel,
                                 String appVersion, String imei, String deviceId, String gameId) {
        Object[] oArray = {finishCode, cid, pkgName, appChannel, appVersion, imei, deviceId, gameId};
        Class<?>[] paraClass = {int.class, String.class, String.class, String.class, String.class,
                String.class, String.class, String.class};
        RefInvoke.invokeInstanceMethod(mVideoView,
                "reportFinishInfo", paraClass, oArray);
    }

    /**
     * 获取云游戏状态码接口
     *
     * @return
     */
    public String getCloudPlayStatusCode() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        String ret = (String) RefInvoke.invokeInstanceMethod(mVideoView,
                "getCloudPlayStatusCode", paraClass, oArray);
        return ret;
    }

    /**
     * 对应用层开放的游戏直播接口
     *
     * @param livingId  直播ID
     * @param livingUrl 直播地址
     * @param listener  结果回调
     */
    public void startLiving(String livingId, String livingUrl, OnLivingListener listener) {
        try {
            Object[] oArray = {livingId, livingUrl, new Object()};
            Class<?>[] paraClass = {String.class, String.class, ReflectConfig.OnLivingListener.getReflectClass()};

            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "startLiving", oArray, paraClass, ReflectConfig.OnLivingListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            super.invoke(proxy, method, args);
                            if ("start".equals(method.getName())) {
                                listener.start((boolean) args[0], (String) args[1]);
                            } else if ("stop".equals(method.getName())) {
                                listener.stop((boolean) args[0], (String) args[1]);
                            }
                            return null;
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对应用层开放的停止游戏直播接口
     *
     * @param livingId 直播地址
     * @param listener 结果回调
     */
    public void stopLiving(String livingId, OnLivingListener listener) {
        try {
            Object[] oArray = {livingId, new Object()};
            Class<?>[] paraClass = {String.class, ReflectConfig.OnLivingListener.getReflectClass()};

            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "stopLiving", oArray, paraClass, ReflectConfig.OnLivingListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            super.invoke(proxy, method, args);
                            if ("start".equals(method.getName())) {
                                listener.start((boolean) args[0], (String) args[1]);
                            } else if ("stop".equals(method.getName())) {
                                listener.stop((boolean) args[0], (String) args[1]);
                            }
                            return null;
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对应用层开放的获取授权码接口
     *
     * @param listener 结果回调
     */
    public void getPinCode(OnContronListener listener) {
        try {
            Object[] oArray = {new Object()};
            Class<?>[] paraClass = {ReflectConfig.OnContronListener.getReflectClass()};

            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "getPinCode", oArray, paraClass, ReflectConfig.OnContronListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            super.invoke(proxy, method, args);
                            if ("pinCodeResult".equals(method.getName())) {
                                listener.pinCodeResult((boolean) args[0], (String) args[1], (String) args[2], (String) args[3]);
                            } else if ("contronResult".equals(method.getName())) {
                                listener.contronResult((boolean) args[0], (String) args[1]);
                            } else if ("contronLost".equals(method.getName())) {
                                listener.contronLost();
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取控制权
     *
     * @param streamType  流类型
     * @param controlPara 获取控制权的参数封装
     * @param listener    结果回调
     */
    public void contronPlay(int streamType, Control controlPara, OnContronListener listener) {
        try {
            Class<?> controlCls = ReflectConfig.Control.getReflectClass();
            Object controlValue = RefInvoke.convertObject(controlPara, controlCls);

            Object[] oArray = {streamType, controlValue, new Object()};
            Class<?>[] paraClass = {int.class, controlCls, ReflectConfig.OnContronListener.getReflectClass()};
            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "contronPlay", oArray, paraClass, ReflectConfig.OnContronListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            super.invoke(proxy, method, args);
                            if ("pinCodeResult".equals(method.getName())) {
                                listener.pinCodeResult((boolean) args[0], (String) args[1], (String) args[2], (String) args[3]);
                            } else if ("contronResult".equals(method.getName())) {
                                listener.contronResult((boolean) args[0], (String) args[1]);
                            } else if ("contronLost".equals(method.getName())) {
                                listener.contronLost();
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "contronPlay: Exception ", e);
        }
    }


    /**
     * 是否支持直播
     *
     * @return
     */
    public ELivingCapabilityStatus getLivingCapabilityStatus() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        Enum<?> result = (Enum<?>) RefInvoke.invokeInstanceMethod(mVideoView,
                "getLivingCapabilityStatus", paraClass, oArray);
        return RefInvoke.convertEnum(result, ELivingCapabilityStatus.class);
    }

    /**
     * 开始录音
     */
    public void startRecord() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        RefInvoke.invokeInstanceMethod(mVideoView,
                "startRecord", paraClass, oArray);
    }

    /**
     * 设置可存档最小游戏时间并退出游戏
     *
     * @param activity
     * @param time
     */
    public void stopAndDismiss(Activity activity, int time) {
        Object[] oArray = {activity, time};
        Class<?>[] paraClass = {Activity.class, int.class};
        RefInvoke.invokeInstanceMethod(mVideoView,
                "stopAndDismiss", paraClass, oArray);
    }

    /**
     * 上传文件
     *
     * @param file
     */
    public void upload(CloudFile file) {
        try {
            Class<?> cloudFileCls = ReflectConfig.CloudFile.getReflectClass();
            Object param = RefInvoke.convertObject(file, cloudFileCls);
            Object[] oArray = {param};
            Class<?>[] paraClass = {cloudFileCls};
            RefInvoke.invokeInstanceMethod(mVideoView,
                    "upload", paraClass, oArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件
     *
     * @param file
     */
    public void download(CloudFile file) {
        try {
            Class<?> cloudFileCls = ReflectConfig.CloudFile.getReflectClass();
            Object param = RefInvoke.convertObject(file, cloudFileCls);
            Object[] oArray = {param};
            Class<?>[] paraClass = {cloudFileCls};
            RefInvoke.invokeInstanceMethod(mVideoView,
                    "download", paraClass, oArray);
        } catch (Exception e) {
            Log.e(TAG, "download: Exception", e);
        }
    }

    /**
     * 取消上传
     */
    public void cancelUpload() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        RefInvoke.invokeInstanceMethod(mVideoView,
                "cancelUpload", paraClass, oArray);
    }

    /**
     * 取消下载
     */
    public void cancelDownload() {
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        RefInvoke.invokeInstanceMethod(mVideoView,
                "cancelDownload", paraClass, oArray);
    }

    /**
     * 请求图片列表
     *
     * @param limit
     * @param offset
     * @param listener
     */
    public void requestCloudImageList(int limit, int offset, OnCloudImageListListener listener) {
        try {
            Object[] oArray = {limit, offset, new Object()};
            Class<?>[] paraClass = {int.class, int.class, ReflectConfig.OnCloudImageListListener.getReflectClass()};

            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "requestCloudImageList",
                    oArray,
                    paraClass,
                    ReflectConfig.OnCloudImageListListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if ("onSuccess".equals(method.getName())) {
                                listener.onSuccess((String) args[0]);
                            } else {
                                listener.onError((String) args[0]);
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "requestCloudImageList: Exception", e);
        }
    }

    /**
     * 设置CloudOperationListener回调接口
     *
     * @param listener
     */
    public void setCloudOperationListener(CloudOperationListener listener) {
        try {
            Class<?> callbackCls = ReflectConfig.CloudOperationListener.getReflectClass();
            Object[] oArray = {new Object()};
            Class<?>[] paraClass = {callbackCls};

            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "setCloudOperationListener",
                    oArray,
                    paraClass,
                    ReflectConfig.CloudOperationListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            super.invoke(proxy, method, args);
                            if ("onSuccess".equals(method.getName())) {
                                CloudFile result = RefInvoke.convertObject(args[1], CloudFile.class);
                                listener.onSuccess(RefInvoke.convertEnum((Enum<?>) args[0], CloudOperation.class), result);
                            } else if ("onCancel".equals(method.getName())) {
                                listener.onCancel(RefInvoke.convertEnum((Enum<?>) args[0], CloudOperation.class));
                            } else if ("onFinish".equals(method.getName())) {
                                listener.onFinish(RefInvoke.convertEnum((Enum<?>) args[0], CloudOperation.class));
                            } else if ("onStop".equals(method.getName())) {
                                listener.onStop(RefInvoke.convertEnum((Enum<?>) args[0], CloudOperation.class), (String) args[1]);
                            } else if ("onError".equals(method.getName())) {
                                listener.onError((RefInvoke.convertEnum((Enum<?>) args[0], CloudOperation.class)), (String) args[1]);
                            }
                            return null;
                        }
                    });

        } catch (Exception e) {
            Log.e(TAG, "setCloudOperationListener: Exception", e);
        }
    }

    /**
     * 对应用层开放的向WsServer发送消息的接口
     *
     * @param payload  message body
     * @param listener message listener
     */
    public void sendWsMessage(String payload, WsMessageType type, OnSendWsMessageListener listener) {
        try {
            Class<? extends Enum> messageTypeCls = (Class<? extends Enum>) ReflectConfig.WsMessageType.getReflectClass();
            Enum<?> paramMessageType = RefInvoke.convertEnum(type, messageTypeCls);
            Object[] oArray = {payload, paramMessageType, new Object()};
            Class<?>[] paraClass = {String.class, messageTypeCls, ReflectConfig.OnSendWsMessageListener.getReflectClass()};

            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "sendWsMessage",
                    oArray,
                    paraClass,
                    ReflectConfig.OnSendWsMessageListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if ("sendWsMessageSuccess".equals(method.getName())) {
                                listener.sendWsMessageSuccess();
                            } else {
                                listener.sendWsMessageFail((String) args[0]);
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "sendWsMessage: Exception", e);
        }
    }

    /**
     * 输入文本
     *
     * @param payload
     * @return
     */
    public boolean inputText(String payload) {
        Object[] oArray = {payload};
        Class<?>[] paraClass = {String.class};
        Object isSend = RefInvoke.invokeInstanceMethod(mVideoView,
                "inputText", paraClass, oArray);
        Log.i(TAG, "inputText: isSend = " + isSend);
        if (isSend == null) {
            return false;
        }
        return (boolean) isSend;
    }

    /**
     * 处理权限申请相关的回调
     *
     * @param permission
     * @param isGranted
     */
    public void handlePermissionResult(String permission, boolean isGranted) {
        Object[] oArray = {permission, isGranted};
        Class<?>[] paraClass = {String.class, boolean.class};
        RefInvoke.invokeInstanceMethod(mVideoView,
                "handlePermissionResult", paraClass, oArray);
    }

    /**
     * 针对初始化VideoView和实际使用不是同一个activity页面，设置使用时的activity，在有需要旋转页面时，通过activity进行旋转
     *
     * @param context 使用VideoView播流页的activity
     */
    public void setAttachContext(Context context) {
        Object[] oArray = {context};
        Class<?>[] paraClass = {Context.class};
        RefInvoke.invokeInstanceMethod(mVideoView,
                "setAttachContext", paraClass, oArray);
    }



    /**
     * 隐藏本地软键盘
     */
    public void hideKeyboard(){
        Object[] oArray = {};
        Class<?>[] paraClass = {};
        RefInvoke.invokeInstanceMethod(mVideoView,
                "hideKeyboard", paraClass, oArray);
    }

    /**
     * 发送返回键事件到实例
     * @param keyType KeyType
     */
    public void sendKeyEvent(KeyType keyType){
        Class<? extends Enum> keyTypeCls = (Class<? extends Enum>) ReflectConfig.KeyType.getReflectClass();
        Enum<?> paramMessageType = RefInvoke.convertEnum(keyType, keyTypeCls);
        Object[] oArray = {paramMessageType};
        Class<?>[] paraClass = {keyTypeCls};
        RefInvoke.invokeInstanceMethod(mVideoView,
                "sendKeyEvent", paraClass, oArray);
    }


    /**
     * app开启sdk的帧延迟数据回调功能后，sdk会周期性(譬如1秒、2秒)回调app帧延迟数据。以下帧延迟数据简称sei。
     *
     * sei数据开启条件如下：
     *
     * - app设置SDK开启sei开关。
     *
     * - saas侧配置sei数据回调间隔时间，具体配置请联系项目对接人。
     *
     * - saas侧配置sei开关，具体配置请联系项目对接人。
     *
     * - saas侧配置sei开关策略，具体配置请联系项目对接人。
     *
     * @param listener ISeiListener listener
     */
    public void setSeiListener(ISeiListener listener) {
        try {
            Object[] oArray = {new Object()};
            Class<?>[] paraClass = {ReflectConfig.ISeiListener.getReflectClass()};

            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "setSeiListener",
                    oArray,
                    paraClass,
                    ReflectConfig.ISeiListener.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if ("onData".equals(method.getName())) {
                                listener.onData((List<RtcSeiData>)RefInvoke.convertListByType((List<? extends Object>) args[0],RtcSeiData.class));
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "setSeiListener exception", e);
        }
    }

    /**
     * 与云游戏传输数据接口 TransferHelper对象，通过对象方法进行操作
     * @return
     */
    public TransferHelper getTransferDelegate() {
        Object transferHelperObj = RefInvoke.invokeInstanceMethod(mVideoView, "getTransferDelegate");
        Log.d(TAG, "getTransferDelegate: transferHelperObj = " + transferHelperObj);
        if (transferHelperObj == null) {
            return null;
        } else {
            try {
                Class<TransferHelper> transferHelperClass = TransferHelper.class;
                Constructor<TransferHelper> declaredConstructor = transferHelperClass.getDeclaredConstructor();
                declaredConstructor.setAccessible(true);
                TransferHelper transferHelper = declaredConstructor.newInstance();
                Field relTransferHelperField = transferHelper.getClass().getDeclaredField("relTransferHelper");
                relTransferHelperField.setAccessible(true);
                relTransferHelperField.set(transferHelper, transferHelperObj);
                return transferHelper;
            } catch (Exception e) {
                Log.e(TAG, "getTransferDelegate exception", e);
                return null;
            }
        }
    }

    /**
     * 获取每帧数据接口
     * <p>
     * 当前只提供每帧渲染完成的帧id、时间戳数据，数据通过子线程回调方式提供，非实时、间隔性回调数据。
     *
     * @param callback HmFrameCallback 回调
     */
    public void setHmFrameCallback(HmFrameCallback callback) {
        try {
            Object[] oArray = {new Object()};
            Class<?>[] paraClass = {ReflectConfig.HmFrameCallback.getReflectClass()};

            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "setHmFrameCallback",
                    oArray,
                    paraClass,
                    ReflectConfig.HmFrameCallback.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if ("onFrame".equals(method.getName())) {
                                callback.onFrame((String) args[0]);
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "setHmFrameCallback exception", e);
        }
    }

    /**
     * 获取游戏推流IP信息接口
     * <p>
     * 在游戏建立连接后以及断网重连等推流IP变动的场景下，回调最新的推流IP给APP，支持IPv4和IPv6
     *
     * @param callback
     */
    public void setHmStreamerIPCallback(HmStreamerIPCallback callback) {
        try {
            Object[] oArray = {new Object()};
            Class<?>[] paraClass = {ReflectConfig.HmStreamerIPCallback.getReflectClass()};

            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "setHmStreamerIPCallback",
                    oArray,
                    paraClass,
                    ReflectConfig.HmStreamerIPCallback.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if ("onConnectionIPChanged".equals(method.getName())) {
                                callback.onConnectionIPChanged((JSONObject) args[0]);
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "setHmFrameCallback exception", e);
        }
    }

    /**
     * 自定义播放器接口
     * 在一些接入场景中，需要自己实现播放器的渲染，比如渲染到AR设备上面，这个时候就需要使用到自定义播放器。自定义播放器通过isCustomPlayer控制，设置为true代表是自定义播放器。详见[申请服务](#申请服务)。
     *
     * 目前自定义播放器只支持RTMP，且不支持灰度功能（RTMP转成RTC）。
     *
     * 自定义播放器需要实现IHmcpIJKVideoView，以及通过设置HmcpIJKVideoViewListener回调播放器状态给SDK。
     *
     * @param iHmcpIJKVideoView
     */
    public void setCustomVideoView(IHmcpIJKVideoView iHmcpIJKVideoView){
        try {
            Object[] oArray = {new Object()};
            Class<?>[] paraClass = {ReflectConfig.IHmcpIJKVideoView.getReflectClass()};

            ReflectHelper.invokeContainsInterfaceMethod(mVideoView,
                    "setCustomVideoView",
                    oArray,
                    paraClass,
                    ReflectConfig.IHmcpIJKVideoView.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            switch (method.getName()) {
                                case "release":
                                    iHmcpIJKVideoView.release();
                                    break;
                                case "setHmcpIJKVideoViewListener":
                                    HmcpIJKVideoViewListenerIml listenerIml = new HmcpIJKVideoViewListenerIml();
                                    Field relListener = listenerIml.getClass().getDeclaredField("relListener");
                                    relListener.setAccessible(true);
                                    relListener.set(listenerIml,args[0]);
                                    iHmcpIJKVideoView.setHmcpIJKVideoViewListener(listenerIml);
                                    break;
                                case "stopPlay":
                                    iHmcpIJKVideoView.stopPlay();
                                    break;
                                case "startPlay":
                                    iHmcpIJKVideoView.startPlay((String)args[0],(String) args[1]);
                                    break;
                                case "isPlaying":
                                    return iHmcpIJKVideoView.isPlaying();

                                default:
                            }
                            return null;
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "setHmFrameCallback exception", e);
        }
    }

    /**
     * 发送动作事件给云游戏接口
     *
     * @param eventType 事件类型 1 down; <br />2 up;  <br />3 move;
     * @param point     发送动作事件给云游戏接口
     */
    public void sendMotionEvent(int eventType, FPoint point) {
        try {
            Class<?> cloudFileCls = ReflectConfig.FPoint.getReflectClass();
            Object param = RefInvoke.convertObject(point, cloudFileCls);
            Object[] oArray = {eventType, param};
            Class<?>[] paraClass = {int.class, cloudFileCls};
            RefInvoke.invokeInstanceMethod(mVideoView,
                    "sendMotionEvent", paraClass, oArray);
        } catch (Exception e) {
            Log.e(TAG, "sendMotionEvent: Exception", e);
        }
    }

    /**
     * 获取视频流域名地址，可能为null，咪咕用于ping网络
     * @return 视频流域名地址
     */
    public String getStreamingDomain() {
        return (String) RefInvoke.invokeInstanceMethod(mVideoView,
                "getStreamingDomain");
    }

}