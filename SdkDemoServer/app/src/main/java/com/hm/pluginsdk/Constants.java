package com.hm.pluginsdk;

import android.graphics.Color;

/**
 * Created by lenovo on 2016/6/14.
 */
public class Constants {

    //TODO:正式上线时改为false
    public static boolean IS_ERROR = true;
    public static boolean IS_INFO = true;
    public static boolean IS_DEBUG = true;
    public static boolean IS_2FILE = true;
    public static boolean IS_URL = true;
    public static boolean IS_2SD = true;
    //TODO:每次提测版本号增加,同时build.gradle也增加
    public static final String SDK_VERSION = "2.6.1";
    public static final String IJK_VERSION = "e6b11d361691b3a0887b64c6da0c29b2d792e84f";
    public static final String ENV_TYPE = "Formal";
    public static boolean GET_INPUT = true;
    public static boolean GET_QR_CODE_DATA = true;
    public static boolean CONFIG_IS_COMPATIBLE_IPV6 = false;// 108获取是否兼容IPV6
    public static float SCREEN_BRIGHTNESS = 0f;
    public static final String ACCESS_URL = "";
    public static final String COUNTLY_URL = "";
    public static int VIDEO_URL_TIMER_INTVAL = 60;  //获取流地址超时
    public static final int VIDEO_URL_TIMER_MAX = 300;  //获取流地址超时
    public static final int VIDEO_URL_TIMER_MIN = 30;  //获取流地址超时
    public static long FIRST_FRAME_TIMEOUT = 8500; //收到第一帧超时 ms
    public static int REFRESH_STOKEN_MAX_TIMES = 5;   //刷新SToken最大次数限制
    public static int REFRESH_STOKEN_DELAY = 2;   //刷新SToken延迟
    public static int REFRESH_STOKEN_VALID_TIME = 30;   //对sdk的sToken有效时间,有效期外刷新sToken，有效期内重连长连接
    public static int WS_RETRY_MAX_TIMES = 5;   //WebSocket在sToken有效期内最大重连次数
    public static int TRACK_BALL_TASK_TIMER = 150;  //摇杆回位定时器时间
    public static final int TRACK_BALL_TASK_TIMER_MAX = 500;  //摇杆回位定时器时间
    public static final int TRACK_BALL_TASK_TIMER_MIN = 20;  //摇杆回位定时器时间
    public static float KEYBOARD_RX = 0.05f;  //键盘方向键x半径
    public static float KEYBOARD_RY = 0.09f;  //键盘方向键y半径
    public static long DEFAULT_CID_CACHE_INTERVAL = 2 * 60 * 60L;  //默认cid缓存时间2h,单位s(超时重连不携带CID)
    public static long NEVER_CID_CACHE_INTERVAL = 0L;  //cid缓存时间永不过期
    public static int CONFIG_IME_TYPE = 0; // 0:实例键盘,Rom搜狗输入法,rom端弹出;1:本地键盘,Rom海马输入法,rom虚拟弹出
    public static int CONFIG_IME_TYPE_NATIVE = 1; // 本地键盘参数
    public static final String IME_ORIENTATION_LANDSCAPE = "0";  //键盘方向横屏
    public static final String IME_ORIENTATION_PORTRAIT = "3";  //键盘方向竖屏
    public static final int SDCARD_SPACE_ENOUGH = 50;// 磁盘剩余空间 预留大小，单位：MB
    /**
     * Socket 重连sleep 时间,单位毫秒
     */
    public static final int SOCKET_RETRY_SLEEP_TIME_MS = 30;
    /**
     * Socket 重连失败msg
     */
    public static final String SOCKET_RETRY_FAILED_MSG_FORMAT = "第 %s 次重连失败";

    public static boolean CONFIG_RTC_CONNECT_FAILURE_TO_RTMP_SWITCH = false; // 是否开启rtc失败切rtmp
    public static int CONFIG_RTC_RETRY_COUNT = 5; // rtc连接失败重连次数

    /**
     * 延迟信息的Debug信息
     */
    public static final String DEBUG_DELAY = "DebugDelayReport";
    /**
     * 内网接口，请求Nginx服务（仅支持IPV4网络），获取ipv4公网地址
     * 支持Jenkins打包时替换
     */
    public static final String HMCP_GET_IPV4_URL = "http://172.16.30.147:8808/getIp";
    /**
     * 是否延迟采集上报，默认为false，由服务器配置
     * 设置为true表示开启采集pingpong间隔为"20,10;10,20;0,60"，上报间隔为15S
     */
    public static boolean isDebugFrameDelay = false;

    public static final String SERVER_PROTOCOL = "3.0.10";
    //TV相关代码被删掉了，TVUIManager.init的参数TelevisionVideoView有关
//    public static final int ANDROID_TYPE = BuildConfig.FLAVOR.equals(BuildConfig.PRODUCT_TV) ? 8 : 2;
    public static final int ANDROID_TYPE = 2;

    public static final int LANDSCAPE_ORIENTATION = 0;
    public static final int PORTRAIT_ORIENTATION = 1;

    public static final int K_SIZE = 1000;

    public static final String WEBSOCKET_SUB_PROTOCOL = "haima-cloud-protocol";

    public static final boolean ENABLE_GAMEPAD_DETECTED = false;

    /**
     * 提供乒乓延迟测试开关
     */
    public static boolean ENABLE_PING_DEBUG = false;

    public static final String STREAM_TYPE_RTMP = "RTMP";
    public static final String STREAM_TYPE_RTC = "WEBRTC";

    public static final String WS_MESSAGE_TYPE_INTENT = "intent";
    public static final String WS_MESSAGE_TYPE_GPS = "gps";

    /**
     * 咪咕Demo，测试新机房专用, 指定节点ID 和实例ID
     */
    public static int DEMO_TEST_INTERFACE_ID;// 节点ID（必传）
    public static int DEMO_TEST_INSTANCE_ID;// 实例ID（非必传）

    /**
     * response code 常量定义
     */
    public static final int RESPONSE_OK_CODE = 0;
    public static final int ACCESS_CONNECTED_NORMAL_SUCCESS = 1;
    public static final int ACCESS_HEART_BEAT_SUCCESS = 2;
    public static final int DEVICE_ID_NOT_EXIST_CODE = 10201;
    public static final int DEVICE_ID_ILLEGAL_CODE = 10216;
    public static final int USER_TOKEN_INVALID = 10212;
    public static final int DEVICE_INFO_NOT_EXIST_CODE = 20106;
    public static final int STATE_CHANGE_REASON_CODE = 20126;
    public static final int CLEAN_CID_CACHE_AND_EXIT_GAME = 20303;
    public static final int RESPONSE_FAIL_CODE = -1;
    public static final int RESPONSE_ACCESS_FAIL = RESPONSE_FAIL_CODE;
    public static final int RECONNECTION_ERROR = 999;
    public static final int ERROR_JSON_CODE = -1000;
    public static final int ERROR_DATA_EMPTY_CODE = -1001;
    public static final int ERROR_NETWORK_UNAVAILABLE_CODE = -1002;
    public static final int ERROR_TIMEOUT_CODE = -1003;
    public static final int ERROR_SERVER_CODE = -1004;
    public static final int RESPONESE_STATE_CHANGE_REASON = 3;
    public static final int RESPONESE_STATE_SWITCH_STREAM_TYPE = 4;

    /**
     * post action 常量定义
     */
    public static final int GET_CONFIGURE_ACTION = 108; //停止实例
    public static final int GET_DEVICE_ID_ACTION = 104; //获取did
    public static final int GET_CLOUD_ID_ACTION = 102; //获取cid
    public static final int GET_SPECIAL_INFO_ACTION = 110; //获取测速地址
    public static final int START_CLOUD_SERVICE_ACTION = 201; //获取实例
    public static final int STOP_CLOUD_SERVICE_ACTION = 202; //停止实例
    public static final int GET_SAVE_GAME_ACTION = 112; //获取存档状态
    public static final int START_CLOUD_SERVICE_ACTION_V2 = 211; //获取实例
    public static final int CHECK_PLAYING_GAME_ACTION = 217; //获取正在运行游戏实例
    public static final int GET_GAME_ARCHIVE = 113; //获取存档状态
    public static final int UPDATA_GAME_UID = 214; //获取存档状态
    public static final int RELEASE_CID = 218; //释放CID
    public static final int START_LIVING = 219; //开始直播
    public static final int STOP_LIVING = 223; //停止直播
    public static final int GET_PIN_CODE = 220; //获取授权码
    public static final int GET_CONTRON = 221; //获取控制权
    public static final int SWITCH_STREAM_TYPE_REQUEST = 226; //rtc失败切rtmp

    // Android Platform AES pre-13 key
    public static final String ANDROID_PLATFORM_AES_KEY = "and0123456789012";
    public static final String ANDROID_PLATFORM_AES_PREFIX_KEY = "";
    public static final int ANDROID_PLATFORM_AES_RANDOM_KEY_LENGTH = 16;
    public static boolean USE_AES_ENCRYPT = true;
    /**
     * http connect 配置
     */
    public static final int RECONNECT_COUNT = 1; // 失败后，超时重连的次数
    public static final int CONNECT_TIME_OUT = 10 * 1000; // 失败后，连接超时时间
    public static final float DEFAULT_BACKOFF_MULT = 1.0f;
    public static String RETRY_CONFIG;// 非108/211/201/202接口 自定义重试,超时时间

    public static final int CUSTOM_RETRY_RECONNECT_COUNT = 1; // 211/201 自定义重试,失败重连的次数
    public static final int CUSTOM_RETRY_TIME_OUT = 10 * 1000; // 211/201 自定义重试,超时时间
    public static String CUSTOM_RETRY_CONFIG; // 108/211/201/202接口 自定义重试,超时时间

    public static final int MAX_ERROR_RETRY_COUNT = 10;// 网络请求失败最大重试次数，默认10次
    public static final int ERROR_RETRY_DELAY = 1;// 网络请求失败 默认每次延时1s发起下次请求
    public static String CUSTOM_ERROR_RETRY_CONFIG; // 108/211/201/202接口 自定义网络请求Error重试规则 如，"2,2,2"代表3次，每延时2s发起下次请求

    /**
     * message 相关常量
     */
    public static final String TAG_TO_MESSAGE_SDK = "m_";
    public static final String TAG_MESSAGE_FROM_ANDROID_SDK = "A";
    public static final int RETRY_SEND_MESSAGE_COUNT = 3;
    public static final int RETRY_TASK_MAX_COUNT = 100;
    public static final int SCHEDULE_RESEND_TIME = 2 * 1000;
    /**
     * wsMessage 相关常量
     */
    public static final int RETRY_SEND_WSMESSAGE_COUNT = 3;
    public static final int WS_SCHEDULE_RESEND_TIME = 2 * 1000;

    public static final String FRAME_DELAY_TIME = "delay_time";
    public static final String REVOLVE_TIME = "revolveTime";

    /**
     * 运营日志相关
     * http://wiki-int.haima.me:8090/pages/viewpage.action?pageId=21661195
     */
    public static final String COUNTYLY_EVENT_NAME = "HMCPAndroidEvent";

    public static final String COUNTYLY_CLOUD_PLAY_IS_RUN = "10012"; //云玩开始启动
    public static final String COUNTYLY_INPUT_TIME_OUT = "10033"; //每帧延时达到阈值上报ping，pong时间,每阈值A(目前默认1分钟)判断是否超过阈值B(100ms)才报
    public static final String COUNTYLY_USER_ENQUEUE_NO = "10039"; //用户选择不入队

    public static final String COUNTYLY_PING_PONG_RECORD = "10050"; //播流期间每固定周期ping，pong时间,每阈值(目前默认1分钟)即报,包含扩展参数，数据为roundtrip时间，单位为毫秒
    public static final String COUNTYLY_TRACE_ROUTE = "10051"; //每帧延时达到阈值，traceroute上报, ping_pong超过阈值(目前默认300ms)上报一次，

    public static final String COUNTYLY_SWITCH_SCENE_AUTO = "12011";      //开始自动切换.包含扩展参数，数据为当前码流Id、目标码流Id，帧延迟检测周期(秒)，延迟检测冷却时间(秒)，延迟检测阀值(百分比)，延迟检测结果(百分比)以 , 分隔
    public static final String COUNTYLY_SWITCH_SCENE_AUTO_SUCCESS = "12012";      //自动切换成功.包含扩展参数，数据为当前码流Id、目标码流Id、切换耗时（单位毫秒），以 , 分隔
    public static final String COUNTYLY_SWITCH_SCENE_AUTO_FAIL = "12013";      //自动切换失败. 包含扩展参数，数据为当前码流Id、目标码流Id、失败原因，以 , 分隔
    public static final String COUNTYLY_SWITCH_SCENE_MANUAL = "12014";      //开始手动切换.包含扩展参数，数据为当前码流Id、目标码流Id，以 , 分隔
    public static final String COUNTYLY_SWITCH_SCENE_MANUAL_SUCCESS = "12015";      //手动切换成功.包含扩展参数，数据为当前码流Id、目标码流Id、切换耗时（单位毫秒），以 , 分隔
    public static final String COUNTYLY_SWITCH_SCENE_MANUAL_FAIL = "12016";      //手动切换失败.包含扩展参数，数据为当前码流Id、目标码流Id、失败原因，以 , 分隔

    public static final String COUNTYLY_NETWORK_WIFI_TO_MOBILE = "12017";      //检测到wifi进入移动网络环境
    public static final String COUNTYLY_NETWORK_MOBILE_TO_WIFI = "12018";      //检测到移动网络环境进入wifi环境
    public static final String COUNTYLY_USER_CHOOSE_CONTINUE = "12019";      //用户选择继续
    public static final String COUNTYLY_FLOWER_LOADING_SUCCESS = "12020";      //加载菊花成功
    public static final String COUNTYLY_FLOWER_LOADING_FAIL = "12021";      //菊花加载失败
    public static final String COUNTYLY_VIDEO_TITLE_LOADING_START = "12024";      //片头开始预加载
    public static final String COUNTYLY_VIDEO_TITLE_LOADING_SUCCESS = "12025";      //片头预加载成功
    public static final String COUNTYLY_VIDEO_TITLE_LOADING_FAIL = "12026";      //片头预加载失败
    public static final String COUNTYLY_VIDEO_TITLE_PLAY_START = "12027";      //开始播放片头
    public static final String COUNTYLY_VIDEO_TITLE_PLAY_SUCCESS = "12028";      //片头播放成功
    public static final String COUNTYLY_VIDEO_TITLE_PLAY_FAIL = "12029";      //片头播放失败
    public static final String COUNTYLY_TESTING_NETWORK_SPEED_START = "12030";      //开始测速
    public static final String COUNTYLY_TESTING_NETWORK_SPEED_COMPLETE = "12031";      //测速完成,包含扩展参数，数据为测速结果，单位为 bytes/second
    public static final String COUNTYLY_TESTING_NETWORK_SPEED_FAIL = "12032";      //测速失败
    public static final String COUNTYLY_AUDIO_STREAM_CONNECT_SUCCESS = "12033";      //流链接成功（音频部分）
    public static final String COUNTYLY_VIDEO_STREAM_CONNECT_SUCCESS = "12034";      //流链接成功（视频部分）
    public static final String COUNTYLY_AUDIO_STREAM_CONNECT_FAIL = "12035";      //流链接失败（音频部分）
    public static final String COUNTYLY_VIDEO_STREAM_CONNECT_FAIL = "12036";      //流链接失败（视频部分）
    public static final String COUNTYLY_RETRY_CONFIG_ERROR = "12099";//网络重试108接口配置错误

    public static final String COUNTYLY_FRAME_DECODE_TIME_PERIOD_AVERAGE = "12037";      //播流期间每固定周期每帧解码平均耗时,包含扩展数据，数据为固定周期每帧解码平均耗时，单位为毫秒
    public static final String COUNTYLY_FRAME_DECODE_TIME_TOO_LONG_TIME = "12038";      //每帧解码延时达到阈值，上报解码耗时,包含扩展数据，数据为解码耗时，单位为毫秒

    public static final String COUNTLY_FRAME_CURRENT_BITRATE = "12039";             //播流启动时，当前码率上报.包含扩展参数，数据为码率ID
    public static final String COUNTLY_APP_FOREGROUND = "12040";                     //播流应用进入前台, 包含扩展参数，数据为cid
    public static final String COUNTLY_APP_BACKGROUND = "12041";                     //播流应用进入后台, 包含扩展参数，数据为cid
    public static final String COUNTLY_FRAME_DELAY_INFO = "12042";                   //每固定周期帧延迟结果上报.包含扩展参数，平均帧延迟(毫秒),最大帧延迟(毫秒),最大帧延迟时间戳(毫秒),最小帧延迟(毫秒),最小帧延迟时间戳(毫秒)，以,分隔

    public static final String COUNTLY_NEED_UNZIP_LIB = "12060";                     //是否解压so库
    public static final String COUNTLY_UNZIP_LIB_RESULT = "12061";                   //解压结果
    public static final String COUNTLY_LOAD_LIB_RESULT = "12062";                   //加载so库结果
    public static final String COUNTLY_NEED_COPY_LIB = "12065";                   //拷贝so库
    public static final String COUNTLY_COPY_LIB_RESULT = "12064";                   //拷贝so库结果

    //add on 2017-5-16
    public static final String COUNTLY_DELAY_LESS_MINIMUM = "12046";
    public static final String COUNTLY_GET_DID = "12063";
    public static final String COUNTLY_GET_DID_SUCCESS = "12078";
    public static final String COUNTLY_GET_CONFIG = "12084";
    public static final String COUNTLY_GET_CONFIG_SUCCESS = "12080";
    public static final String COUNTLY_GET_CONFIG_FAIL = "12081";
    public static final String COUNTLY_GET_CID = "12082";
    public static final String COUNTLY_GET_CID_SUCCESS = "12072";
    public static final String COUNTLY_GET_CID_FAIL = "12073";
    public static final String COUNTLY_CONNECT_SAAS_WS = "12088";
    public static final String COUNTLY_CONNECT_SAAS_WS_SUCCESS = "12089";
    public static final String COUNTLY_CONNECT_SAAS_WS_FAIL = "12090";
    public static final String COUNTLY_CONNECT_SAAS_WS_DISCONNECT = "12050";
    public static final String COUNTLY_GET_CLOUD_SERVICE = "12083";
    public static final String COUNTLY_GET_CLOUD_SERVICE_SUCCESS = "12074";
    public static final String COUNTLY_GET_CLOUD_SERVICE_FAIL = "12075";
    public static final String COUNTLY_GET_CLOUD_SERVICE_FAIL_SWITCH_RESOLUTION = "12092";
    public static final String COUNTLY_GET_CLOUD_SERVICE_V2 = "12120";
    public static final String COUNTLY_GET_CLOUD_SERVICE_V2_SUCCESS = "12121";
    public static final String COUNTLY_GET_CLOUD_SERVICE_V2_FAIL = "12122";
    public static final String COUNTLY_GET_RESERVED_INSTANCE = "12093";//开始调用检查是否有驻留实例方法
    public static final String COUNTLY_GET_RESERVED_INSTANCE_SUCCESS = "12094";//调用检查是否有驻留实例方法成功
    public static final String COUNTLY_GET_RESERVED_INSTANCE_FAIL = "12095";//调用检查是否有驻留实例方法失败
    public static final String COUNTLY_GET_IPV4 = "12130";//开始调用 获取ipv4方法
    public static final String COUNTLY_GET_IPV4_SUCCESS = "12131";//获取ipv4成功
    public static final String COUNTLY_GET_IPV4_FAIL = "12132";//获取ipv4失败
    public static final String COUNTLY_GET_STREAM_URL = "12044";
    public static final String COUNTLY_CONNECT_INPUT_WS = "12085";
    public static final String COUNTYLY_CONNECT_INPUT_WS_SUCCESS = "10021"; //操作连接成功用户数
    public static final String COUNTYLY_CONNECT_INPUT_WS_FAIL = "10022"; //操作连接失败用户数
    public static final String COUNTLY_CONNECT_INPUT_WS_SERVER_URL_EMPTY = "12400";
    public static final String COUNTLY_CONNECT_INPUT_WS_SERVER = "12401";
    public static final String COUNTYLY_CONNECT_INPUT_WS_SERVER_SUCCESS = "12402";
    public static final String COUNTYLY_CONNECT_INPUT_WS_SERVER_DISCONNECT = "12403";
    public static final String COUNTYLY_REONNECT_INPUT_WS_SERVER = "12404";
    public static final String COUNTYLY_REONNECT_INPUT_WS_SERVER_NETWOED_DISCONNECT = "12305";
    public static final String COUNTYLY_CONNECT_INPUT_WS_SERVER_MORE_COUNT = "12306";
    public static final String COUNTYLY_CONNECT_INPUT_WS_DISCONNECT = "12049"; //操作连接失败用户数
    public static final String COUNTYLY_CONNECT_AUDIO_STREAM = "12086";
    public static final String COUNTYLY_CONNECT_VIDEO_STREAM = "12087";
    public static final String COUNTYLY_FIRST_FRAME_ARRIVAL = "12045";
    public static final String COUNTYLY_RTC_FIRST_FRAME_RENDER = COUNTYLY_FIRST_FRAME_ARRIVAL;
    public static final String COUNTYLY_STOP_CLOUD_SERVER = "12091";
    public static final String COUNTYLY_STOP_CLOUD_SERVER_SUCCESS = "12076";
    public static final String COUNTYLY_STOP_CLOUD_SERVER_FAIL = "12077";
    public static final String COUNTYLY_STOP_GAME = "12053";
    public static final String COUNTYLY_SAAS_WS_MESSAGE = "12054";
    public static final String COUNTYLY_4G = "12055";
    public static final String COUNTYLY_STOP_GAME_ERROR = "13053";//结束云游errorCode上报
    public static final String COUNTYLY_RELEASE_CID = "12201";// 操作释放CID
    public static final String COUNTYLY_RELEASE_CID_SUCCESS = "12202";//释放CID成功
    public static final String COUNTYLY_RELEASE_CID_FAIL = "12203";//释放CID失败

    public static final String COUNTYLY_ERROR = "12200";
    public static final String COUNTLY_LIFE_CYCLE_EVENT = "12301";

//    public static final String COUNTYLY_PLAYER_STATUS = "12299";//已废弃

    public static final String COUNTYLY_GAMEPAD_STATUS = "13001";//手柄插拔状态：startGamePad有手柄，stopGamePad无手柄

    // IJKPlayer初始化 RTMP建立连接 过程上报
    public static final String COUNTYLY_IJK_NATIVE_START = "12140";//12140 Native初始化 _native_init
    public static final String COUNTYLY_IJK_MP_CREATE = "12141";//12141 创建Native IjkMediaPlayer 成功
    public static final String COUNTYLY_IJK_SET_SOURCE = "12142";//12142 设置流地完成 _setDataSourceAndHeaders :url
    public static final String COUNTYLY_IJK_PREPARE_ASYNC_START = "12143";//12143 Native层开始加载解码 开始
    public static final String COUNTYLY_IJK_PREPARE_ASYNC_END = "12144";//12144 Native层开始加载解码 结束
    public static final String COUNTYLY_IJK_FRAMEQUEUE_INIT = "12145";//12145 初始化帧队列 完成
    public static final String COUNTYLY_IJK_PACKETQUEUE_INIT = "12146";//12146 初始化数据包队列 完成
    public static final String COUNTYLY_IJK_CLOCK_INIT = "12147";//12147 初始化时针 完成
    public static final String COUNTYLY_IJK_REFRESH_THREAD = "12148";//12148 创创建视频刷新的线程 完成
    public static final String COUNTYLY_IJK_READ_THREAD = "12149";//12149 创建视频读取的线程 完成
    public static final String COUNTYLY_IJK_DECODER_INIT = "12150";//12150 初始化视频解码器
    public static final String COUNTYLY_IJK_IJKMP_START = "12151";//12151 开始播放 ijkmp_start

    public static final String COUNTYLY_FFMPEG_OPEN_INPUT = "12160";//12160 FFmpeg开启输入流，并且读取header 开始 avformat_open_input
    public static final String COUNTYLY_FFMPEG_IO_OPEN = "12161";//12161 协议读写 初始化URLContext与AVIOContext 开始 io_open_default
    public static final String COUNTYLY_RTMP_OPEN = "12162";//12162 开启 RTMP连接并验证流是否可以播放 rtmp_open
    public static final String COUNTYLY_RTMP_TCP_CONNECTION = "12163";//12163 开启tcp Socket连接 开始:
    public static final String COUNTYLY_RTMP_TCP_CONNECTION_SUCCESS = "12164";//12164 开启tcp Socket连接 成功:tcp://220.194.80.121:44743
    public static final String COUNTYLY_RTMP_HANDSHAKE = "12165";//12165 rtmp握手 开始
    public static final String COUNTYLY_RTMP_HANDSHAKE_SUCCESS = "12166";//12166 rtmp握手 成功
    public static final String COUNTYLY_RTMP_CONNECT_SERVER = "12167";//12167 建立网络Connect连接，发送到Server 开始 gen_connect
    public static final String COUNTYLY_RTMP_CONNECT_SERVER_SUCCESS = "12168";//12168 建立网络Connect连接，发送到Server 成功 gen_connect
    public static final String COUNTYLY_RTMP_CREATE_STREAM = "12169";//12169 建立网络流 createStream 发送
    public static final String COUNTYLY_RTMP_CREATE_STREAM_SUCCESS = "12170";//12170 建立网络流 createStream 成功
    public static final String COUNTYLY_RTMP_GEN_PLAY = "12171";//12171 发送播放命令 play 开始
    public static final String COUNTYLY_RTMP_GEN_PLAY_SUCCESS = "12172";//12172 发送播放命令 play 成功
    public static final String COUNTYLY_FFMPEG_IO_OPEN_SUCCESS = "12173";//12173 协议读写 初始化URLContext与AVIOContext 完成 ffio_open_whitelist 结束
    public static final String COUNTYLY_FFMPEG_OPEN_INPUT_SUCCESS = "12174";//12174 FFmpeg开启输入流，并读取header 结束 avformat_open_input


    /**
     * 添加云手机上报
     */
    public static final String COUNTYLY_GET_CLOUDPHONEINFO = "13002";//开始getCloudPhoneInfo请求
    public static final String COUNTYLY_GET_CLOUDPHONEINFO_SUCCESS = "13005";//getCloudPhoneInfo请求成功
    public static final String COUNTYLY_GET_CLOUDPHONEINFO_FAIL = "13006";//getCloudPhoneInfo请求失败
    public static final String COUNTYLY_GET_CONNECT = "13003";//开始connect请求
    public static final String COUNTYLY_GET_CONNECT_SUCCESS = "13007";//connect请求成功
    public static final String COUNTYLY_GET_CONNECT_FAIL = "13008";//connect请求失败
    public static final String COUNTYLY_GET_REFRESH = "13004";//开始refresh请求
    public static final String COUNTYLY_GET_REFRESH_SUCCESS = "13009";//refresh请求成功
    public static final String COUNTYLY_GET_REFRESH_FAIL = "13010";//refresh请求失败
    public static final String COUNTYLY_GET_DISCONNECT = "13011";//开始disconnect请求
    public static final String COUNTYLY_GET_DISCONNECT_SUCCESS = "13012";//disconnect请求成功
    public static final String COUNTYLY_GET_DISCONNECT_FAIL = "13013";//disconnect请求失败
    public static final String COUNTYLY_GET_RESOLUTION = "13014";//开始resolution请求
    public static final String COUNTYLY_GET_RESOLUTION_SUCCESS = "13015";//resolution请求成功
    public static final String COUNTYLY_GET_RESOLUTION_FAIL = "13016";//resolution请求失败

    public static final String COUNTYLY_GET_START_LIVING = "13301";//requestStartLiving请求直播
    public static final String COUNTYLY_GET_START_LIVING_SUCCESS = "13302";//requestStartLiving请求直播成功
    public static final String COUNTYLY_GET_START_LIVING_FAIL = "13303";//requestStartLiving请求直播失败
    public static final String COUNTYLY_GET_STOP_LIVING = "13304";//requestStopLiving请求停止直播
    public static final String COUNTYLY_GET_STOP_LIVING_SUCCESS = "13305";//requestStopLiving请求停止成功
    public static final String COUNTYLY_GET_STOP_LIVING_FAIL = "13306";//requestStopLiving请求停止失败
    public static final String COUNTYLY_GET_PIN_CODE = "13307";//requestPinCode请求授权码
    public static final String COUNTYLY_GET_PIN_CODE_SUCCESS = "13308";//requestPinCode请求授权码成功
    public static final String COUNTYLY_GET_PIN_CODE_FAIL = "13309";//requestPinCode请求授权码失败
    public static final String COUNTYLY_GET_CONTRON = "13310";//requestContron请求控制权
    public static final String COUNTYLY_GET_CONTRON_SUCCESS = "13311";//requestContron请求控制权成功
    public static final String COUNTYLY_GET_CONTRON_FAIL = "13312";//requestContron请求控制权失败
    public static final String COUNTYLY_HAVE_CONTRON_SUCCESS = "13313";//控制权获得成功
    public static final String COUNTYLY_HAVE_CONTRON_FAIL = "13314";//控制权获得失败
    public static final String COUNTYLY_LOST_CONTRON_FAIL = "13315";//失去控制权
    public static final String COUNTYLY_GAME_EXIT = "13316";//游戏退出
    public static final String COUNTYLY_STREAM_DATA_ERROR = "13317";//流地址格式错误
    public static final String COUNTYLY_EXCHANGE_CONTRON_SUCCESS = "13321";//控制权互换成功
    public static final String COUNTYLY_EXCHANGE_CONTRON_FAIL = "13322";//控制权互换失败
    public static final String COUNTYLY_EXCHANGE_CONTRON_RESTORE_SUCCESS = "13323";//控制权互换还原成功
    public static final String COUNTYLY_EXCHANGE_CONTRON_RESTORE_FAIL = "13324";//控制权互换还原失败

    public static final String COUNTLY_GET_SWITCH_STREAM_TYPE = "13800"; //返回流类型不一致
    public static final String COUNTLY_GET_SWITCH_STREAM_INFO = "13801"; //226请求成功
    public static final String COUNTLY_GET_SWITCH_STREAM_INFO_SUCCESS = "13802"; //获取成功
    public static final String COUNTLY_GET_SWITCH_STREAM_INFO_FAIL = "13803"; //获取失败

    //WEBRTC
    public static final String COUNTYLY_RTC_START_CONNECT = "13215";//WEBRTC开始连接
    public static final String COUNTYLY_RTC_CONNECT_SUCCESS = "13216";//WEBRTC连接成功
    public static final String COUNTYLY_RTC_CONNECT_FAIL = "13217";//WEBRTC连接失败
    public static final String COUNTYLY_RTC_SELF_RECONNECT_SUCCESS = "13218";//WEBRTC自身重连成功
    public static final String COUNTYLY_RTC_RECONNECT_FAIL = "13219";//WEBRTC重连失败
    public static final String COUNTYLY_RTC_SIGNALING_TIME_OUT = "13220";//WEBRTC信令服务链接超时
    public static final String COUNTYLY_RTC_SENT_JOIN = "13221";//WEBRTC发送join
    public static final String COUNTYLY_RTC_RECEIVE_OFFER = "13222";//WEBRTC收到offer
    public static final String COUNTYLY_RTC_CREATE_ANSWER = "13223";//WEBRTC创建answer
    public static final String COUNTYLY_RTC_SENT_ANSWER = "13224";//WEBRTC发送answer
    public static final String COUNTYLY_RTC_RECEIVE_CANDIDATE = "13225";//WEBRTC收到candidate
    public static final String COUNTYLY_RTC_SENT_CANDIDATE = "13226";//WEBRTC发送candidate
    public static final String COUNTYLY_RTC_SIGNALING_CONNECTED_AND_SENT_JOIN_AGAIN = "13227";//WEBRTC信令服务器已经连接再次发送join
    public static final String COUNTYLY_RTC_ICE_CONNECTION_FAILED = "13228";//WEBRTC的client端IceConnectiontate处于failed状态
    public static final String COUNTYLY_RTC_PEER_ICE_CONNECTION_STATE_DISCONNECTED = "13229";//WEBRTC的client端PeericeConnectionState=disconnected
    public static final String COUNTYLY_RTC_CLEAR_CONNECT_TIME_OUT_TIMER = "13230";//WEBRTC清除连接超时定时器
    public static final String COUNTYLY_RTC_TRY_RECONNECTING = "13231";//WEBRTC重连
    public static final String COUNTYLY_RTC_CLOSE = "13232";//WEBRTC关闭close
    public static final String COUNTYLY_RTC_SIGNAL_CONNECT_SUCCESS = "13241";//WEBRTC信令服务器连接成功
    public static final String COUNTYLY_RTC_SIGNAL_DISCONNECTED = "13242";//WEBRTC信令服务器连接断开
    public static final String COUNTYLY_RTC_SIGNAL_STATUS = "13245";//WEBRTC信令服务状态
    public static final String COUNTYLY_RTC_CODEC_INFO = "13246";//WEBRTC解码格式和解码器
    public static final String COUNTYLY_RTC_FIRST_FRAME_RECVED = "13248"; //WEBRTC第一帧接收完成
    public static final String COUNTYLY_RTC_FIRST_FRAME_DECODED = "13249"; //WEBRTC第一帧解码完成

    public static final String COUNTYLY_RTC_PEER_CONN_DISCONNECT = "13450"; //RTC中一个connection断开连接,只记录事件,不做处理
    public static final String COUNTYLY_RTC_PEER_CONN_RE_CONNECTED = "13451"; //RTC的一个连接断开后,rtc内部自动重连成功,只记录事件,不做处理
    public static final String COUNTYLY_RTC_RECV_DUPLICATE_OFFER = "13452"; //Client在已连接成功的情况下收到Streamer的OFFER，大概率是Streamer Crash或者切换分辩率

    public static final String COUNTYLY_PING_DELAY_INFO = "13501";//ping功能延迟数据上报
    public static final String COUNTYLY_WEBSOCKET_HEART_BEAT_TIME_OUT_COUNT = "13502";//websocket心跳延迟累计
    public static final String COUNTYLY_WEBSOCKET_HEART_BEAT_FAIL = "13503";//websocket心跳失败，心跳超时次数达到阈值
    public static final String COUNTYLY_CALL_BACK_STATUS_TO_APP = "13504";//回调app状态值status
    public static final String COUNTYLY_CALL_BACK_SCENE_TO_APP = "13505";//回调app场景Scene
    public static final String COUNTYLY_NO_NET_WORK = "13506";//无网络

    // Add for dns manager and websocket info
    public static final String COUNTLY_DNS_CONFIG = "12500";
    public static final String COUNTLY_DNS_RESET = "12501";
    public static final String COUNTLY_DNS_GET_START = "12502";
    public static final String COUNTLY_DNS_GET_IPV4_SUCCEED = "12503";
    public static final String COUNTLY_DNS_GET_IPV6_SUCCEED = "12504";
    public static final String COUNTLY_DNS_GET_FAILED = "12505";

    public static final String COUNTLY_DNS_RESOLVE_START = "12506";
    public static final String COUNTLY_DNS_RESOLVE_DONE = "12507";

    public static final String COUNTLY_DNS_ENABLE_IPV6 = "12508";
    public static final String COUNTLY_DNS_DISABLE_IPV6 = "12509";

    public static final String COUNTLY_DNS_NETWORK_FAILED = "12510";

    public static final String COUNTLY_DNS_WEBSOCKET_RESLOVED = "12511";

    public static final String COUNTLY_DNS_RTMP_RESLOVED = "12512";

    public static final String COUNTLY_DNS_ERROR = "12513";

    public static final String COUNTLY_WEBSOCKET_CONNECT_SUCCEED_INFO = "12520";
    public static final String COUNTLY_WEBSOCKET_CONNECT_FAILED_INFO = "12521";

    /* 图片上传 */
    public static final String COUNTLY_SHARE = "12621";                 //rom侧分享通知sdk
    public static final String COUNTLY_OPEN_CAMERA = "12622";           //rom侧打开摄像头通知sdk
    public static final String COUNTLY_OPEN_GALLERY = "12623";          //rom侧打开相册通知sdk
    public static final String COUNTLY_REMOTE_SCREEN_CAP = "12624";     //rom侧截图保存通知sdk
    public static final String COUNTLY_REMOTE_IMAGE_LIST_REQUEST = "12625"; //获取rom侧图片列表请求
    public static final String COUNTLY_REMOTE_IMAGE_LIST_RESPONSE = "12626";//获取rom侧图片列表结果

    public static final String COUNTLY_UPLOAD_START = "12601";          //开始上传
    public static final String COUNTLY_UPLOAD_FILE_SCUCCESS = "12602";  //文件上传成功
    public static final String COUNTLY_UPLOAD_STOP = "12603";           //上传停止
    public static final String COUNTLY_UPLOAD_CNACEL = "12604";         //取消上传
    public static final String COUNTLY_UPLOAD_ERROR = "12605";          //上传错误
    public static final String COUNTLY_UPLOAD_FINISH = "12606";         //上传完成
    public static final String COUNTLY_UPLOAD_STATUS = "12607";         //上传状态

    public static final String COUNTLY_DOWNLOAD_START = "12611";          //开始下载
    public static final String COUNTLY_DOWNLOAD_FILE_SCUCCESS = "12612";  //文件下载成功
    public static final String COUNTLY_DOWNLOAD_STOP = "12613";           //下载停止
    public static final String COUNTLY_DOWNLOAD_CNACEL = "12614";         //取消下载
    public static final String COUNTLY_DOWNLOAD_ERROR = "12615";          //下载错误
    public static final String COUNTLY_DOWNLOAD_FINISH = "12616";         //下载完成
    public static final String COUNTLY_DOWNLOAD_STATUS = "12617";         //下载状态

    /* WS数据 Intent拦截 GPS */
    public static final String COUNTLY_WS_MSG_SEND = "12901";
    public static final String COUNTLY_WS_MSG_SEND_SUCCESS = "12902";
    public static final String COUNTLY_WS_MSG_SEND_FAILURE = "12903";
    public static final String COUNTLY_WS_MSG_RECEIVE = "12904";

    public static final int WS_CODE_RECEIVED = 1000;
    public static final String WS_MSG_RECEIVED = "received intent data";

    public static final String WS_MESSAGE_TYPE_CLIPBOARD = "clipboard";
    public static final String WS_MSG_KEY_TYPE = "type";
    public static final String WS_MSG_KEY_CID = "cid";
    public static final String WS_MSG_KEY_MID = "mid";
    public static final String WS_MSG_KEY_BID = "bid";
    public static final String WS_MSG_KEY_DATA = "data";
    public static final String WS_MSG_KEY_CODE = "code";
    public static final String WS_MSG_KEY_MESSAGE = "message";

    /* 咪咕sei数据上报 */
    public static final String COUNTLY_MIGU_SEI = "12801";         //上报sei数据每次回调的集合中第一条数据

    /* 测速 */
    public static final String COUNTLY_GET_SPECIAL_INFO = "13701";          //获取测速信息
    public static final String COUNTLY_GET_SPECIAL_INFO_SUCCESS = "13702";  //获取测速信息成功
    public static final String COUNTLY_GET_SPECIAL_INFO_FAIL = "13703";     //获取测速信息失败
    /**
     * 测试状态
     * start，开始
     * prepare，准备
     * running，运行中
     * stop，停止。app停止：stop,1；sdk停止：stop,0,time out(停止的场景信息或者停止的原因)
     * finish，结束
     * error，错误。eg：error,time out(错误信息)
     */
    public static final String COUNTLY_SPEED_TEST_STATUS = "13704";         //测试状态，start、prepare、running、stop、finish、error

    //200000019 新增埋点
//    public static final String COUNTLY_CONNECT_SAAS_WS_CREATE_READER_STATE = "14001";//创建READER
//    public static final String COUNTLY_CONNECT_SAAS_WS_CREATE_WRITER_STATE = "14002";//创建WRITER
//    public static final String COUNTLY_CONNECT_SAAS_WS_HAND_SHAKE = "14003";//发送HandShake
    public static final String COUNTLY_CONNECT_SAAS_WS_HEART_BEAT = "14004";//发送心跳
//    public static final String COUNTLY_CONNECT_SAAS_WS_HMCP_ERROR = "14005";//下发Operation3
//    public static final String COUNTLY_ON_DISCONNECT_SAAS_ERROR = "14006";//长连接断开原因

    //100201003 新增埋点
//    public static final String COUNTLY_CONNECT_SAAS_SET_SERVERKEY = "14011";
//    public static final String COUNTLY_CONNECT_SAAS_AES_ERROR = "14012";

    //100xxxxxx  200xxxxxx 新增请求报文的埋点
//    public static final String COUNTLY_ERROR_CODE_REQUEST = "14000";

    public static final String COUNTYLY_DELAY_STATUS = "16003";//延迟信息上报

    public static final String COUNTYLY_APP_CLOUD_GAME_FINISH = "15000";//APP判定云游戏结束上报

    //trace功能增加埋点
    public static final String COUNTLY_NET_PING_BAIDU = "14021";//ping空口增加埋点

    /**
     * tips相关
     */
    public static final String TIPS_PROMPT_WAIT_MSG = "prompt_wait_message";
    public static final String TIPS_PROMPT_QUEUEING = "prompt_queueing";
    public static final String TIPS_PROMPT_HMCP_ERROR = "prompt_hmcp_error";
    public static final String TIPS_PROMPT_GAME_OVER = "prompt_game_over";
    public static final String TIPS_PROMPT_NETWORK_UNAVAILABLE = "prompt_network_unavailable";
    public static final String TIPS_TOAST_PLAY_TIME = "toast_play_time";
    public static final String TIPS_TOAST_GAME_OVER = "toast_game_over";
    public static final String TIPS_TOAST_COUNTDOWN = "toast_countdown";
    public static final String TIPS_FORCED_OFFLINE_ACTION = "tips_forced_offline_action";

    public static final String TIPS_TOAST_WAIT_TOOMANY = "toast_wait_tooMany";
    public static final String TIPS_TOAST_WAIT_CHOOSE = "toast_wait_choose";
    public static final String TIPS_TOAST_WAIT_TOOLONG = "toast_wait_tooLong";
    public static final String TIPS_TOAST_NO_INPUT = "toast_no_input";
    // public static final String TIPS_TOAST_CRASH_REAPPLY = "toast_crash_reapply";
    public static final String TIPS_TOAST_INSTANCE_APPLY = "toast_instance_apply";
    // public static final String TIPS_TOAST_INSTANCE_READY = "toast_instance_ready";

    public static final String TIPS_CHANGE_WIFI_TO_4G = "tips_change_wifi_to_4g";
    public static final String TOAST_SAAS_READY_TO_STOP = "toast_saas_ready_to_stop";
    public static final String TIPS_SAAS_TO_STOP = "tips_saas_to_stop";
    public static final String TIPS_SAAS_ALREAY_STOPPED = "tips_saas_already_stopped";
    //public static final String TIPS_SAAS_SERVICE_RESUME = "tips_saas_service_resume";
    public static final String TIPS_PROMPT_WAIT_MAN_TIME = "prompt_wait_man_time";
    public static final String TOAST_OPEN_SAME_GAME = "toast_open_same_game";
    public static final String FLAG_SHOWPLAYINGTIME = "flagShowPlayingTime";
    public static final String LOADING_BG_COLOR_VALUE = "loading_bg_color_value";
    public static final String TOAST_SPEED_LOWER_BITRATE = "toast_speed_lower_bitrate";
    public static final String DATA_POINT_URL = "data_point_url";
    public static final String DATA_POINT_APPKEY = "data_point_appkey";
    public static final String TIPS_PROMPT_NO_INPUT = "toast_reminder_no_input";
    public static final String CHECK_ERROR_SWITCH = "check_error_switch";
    public static final String CHECK_VIDEOVIEW_TYPE = "check_videoview_type";
    public static final String CHECK_QR_CODE_DATA = "check_QR_code_data";
    public static final String STREAMER_URL_TIMEOUT = "streamer_url_timeout";
    public static final String TRACK_BALL_TIME = "trackball_time";
    public static final String AUTO_SCREEN_BRIGHTNESS = "auto_screen_brightness";
    public static final String KEYMAP_STICK_RADIUS_X = "keymap_stick_radius_x";
    public static final String KEYMAP_STICK_RADIUS_Y = "keymap_stick_radius_y";
    public static final String IS_COMPATIBLE_IPV6 = "is_compatible_ipv6";//108控制是否支持ipv6
    public static final String WEBRTC_FRAME_WAIT_TIME = "webrtc_frame_wait_time";//连接webrtc超时
    public static final String WEBRTC_SIGNAL_TIME_OUT = "webrtc_socket_timeout";//signal超时
    public static final String WEBRTC_SIGNAL_RECONNECTION_DELAY_MAX = "webrtc_reconnection_delayMax";//signal重连延迟
    public static final String WEBRTC_SIGNAL_RECONNECTION_ATTEMPTS = "webrtc_reconnection_attempts";//signal重连最大次数
    public static final String IME_TYPE = "ime_type";//108控制键盘类型 0:原远程键盘 1:本地键盘
    public static final String FIRST_FRAME_TIMEOUT_MS = "first_frame_timeout_ms";//第一帧超时
    public static final String REFRESH_STOKEN_TIMES = "refresh_stoken_times";//刷sToken次数限制
    public static final String RETRY_ERRORCODE_INFO = "retryErrorcodeInfo";//同步返回错误码重试配置
    public static final String CONFIG_RETRY_CONFIG = "retryConfig";// 非108/211/201/202接口 自定义重试,超时时间配置
    public static final String CONFIG_CUSTOM_RETRY_CONFIG = "customRetryConfig";// 108/211/201/202接口 自定义重试,超时时间配置
    public static final String CONFIG_CUSTOM_ERROR_RETRY_CONFIG = "customErrorRetryConfig";// 108/211/201/202接口 自定义失败，重试延时时间 2,2,2表示重试3次，每次延时2s
    public static final String SDK_STOKEN_VALID_TIME = "sdk_stoken_valid_time";// 对于sdk sToken的有效时间 默认30s （实际对rom是120s）
    public static final String WEBSOCKET_RETRY_MAX_TIMES = "websocket_retry_max_times";// WebSocket在sToken有效期内最大重连次数
    public static final String H264_SEI_INTERVAL = "h264SeiDataReportInterval";//sei数据回调间隔
    public static final String H264_SEI_ENABLE = "h264SeiDataReportEnable";//sei数据开关，saas侧的配置

    // Add for dns manager and websocket info
    public static final String DNS_GET_TIMEOUT_CFG_NAME = "dns_get_timeout";
    public static final String DNS_CHECK_INTERVAL_CFG_NAME = "dns_check_interval";
    public static final String DNS_RESET_IPV6_FLAG_TIME_CFG_NAME = "dns_reset_ipv6_flag_time";
    public static final String DNS_GRAY_ENABLE_PERCENT_CFG_NAME = "dns_gray_enable_percent";
    /**
     * IPV4 优先配置：默认为false。
     */
    public static final String DNS_IPV4_PRIORITY_CFG_NAME = "dns_ipv4_priority";

    public static final String CONN_CHECK_GRAY_ENABLE_PERCENT = "conn_check_gray_enable_percent";
    public static final String CONN_CHECK_HOST = "conn_check_host";
    public static final String CONN_CHECK_PORT = "conn_check_port";

    //ping配置
    public static final String PING_GRAY_ENABLE_PERCENT_NAME = "ping_gray_enable_percent";
    public static final String PING_INTERVAL_NAME = "ping_interval";
    public static final String PING_COUNT_NAME = "ping_count";//ping指定次数后停止ping，详见 ping -c count
    public static final String PING_HOST_NAME = "ping_host";

    //文件上传、下载
    public static final String FILE_CACHE_SIZE = "file_cache_size";//文件缓冲区大小，单位MB
    public static final String FILE_SEGMENT_COEFFICIENT = "file_segment_coefficient";//传输文件段大小系数
    public static final String STOKEN_VALID_TIME = "stoken_valid_time";//长连接stoken有效时间
    public static final String CLOUD_ROM_ACK_TIMEOUT = "cloud_rom_ack_timeout";//rom侧ACK回应超时

    //rtc灰度和rtc连接失败切rtmp
    public static final String RTC_CONNECT_FAILURE_TO_RTMP_SWITCH = "rtc_connect_failure_to_rtmp_switch";
    public static final String RTC_CONNECT_RETRY_COUNT = "rtc_connect_retry_count";
    public static final String RTC_IS_CHANGE_STREAM_TYPE = "is_change_stream_type";

    public static final String ERRORCODE_SDK_PREFIX = "100";
    public static final String ERRORCODE_DID_PREFIX = ERRORCODE_SDK_PREFIX + "104";
    public static final String ERRORCODE_DID_001 = ERRORCODE_DID_PREFIX + "001"; //网络连接失败
    public static final String ERRORCODE_DID_002 = ERRORCODE_DID_PREFIX + "002"; //请求超时
    public static final String ERRORCODE_DID_003 = ERRORCODE_DID_PREFIX + "003"; //返回数据为空
    public static final String ERRORCODE_DID_004 = ERRORCODE_DID_PREFIX + "004"; //返回数据格式错误
    public static final String ERRORCODE_DID_005 = ERRORCODE_DID_PREFIX + "005"; //返回 did 无效
    public static final String ERRORCODE_DID_006 = ERRORCODE_DID_PREFIX + "006"; //返回 countly 参数无效

    public static final String ERRORCODE_CONFIG_PREFIX = ERRORCODE_SDK_PREFIX + "108";
    public static final String ERRORCODE_CONFIG_001 = ERRORCODE_CONFIG_PREFIX + "001"; //网络连接失败
    public static final String ERRORCODE_CONFIG_002 = ERRORCODE_CONFIG_PREFIX + "002"; //请求超时
    public static final String ERRORCODE_CONFIG_003 = ERRORCODE_CONFIG_PREFIX + "003"; //返回数据为空
    public static final String ERRORCODE_CONFIG_004 = ERRORCODE_CONFIG_PREFIX + "004"; //返回数据格式错误

    public static final String ERRORCODE_SPECIAL_INFO_PREFIX = ERRORCODE_SDK_PREFIX + GET_SPECIAL_INFO_ACTION;
    public static final String ERRORCODE_SPECIAL_INFO_001 = ERRORCODE_SPECIAL_INFO_PREFIX + "001"; //网络连接失败
    public static final String ERRORCODE_SPECIAL_INFO_002 = ERRORCODE_SPECIAL_INFO_PREFIX + "002"; //请求超时
    public static final String ERRORCODE_SPECIAL_INFO_003 = ERRORCODE_SPECIAL_INFO_PREFIX + "003"; //返回数据为空
    public static final String ERRORCODE_SPECIAL_INFO_004 = ERRORCODE_SPECIAL_INFO_PREFIX + "004"; //返回数据格式错误

    public static final String ERRORCODE_CID_PREFIX = ERRORCODE_SDK_PREFIX + "102";
    public static final String ERRORCODE_CID_001 = ERRORCODE_CID_PREFIX + "001"; //网络连接失败
    public static final String ERRORCODE_CID_002 = ERRORCODE_CID_PREFIX + "002"; //请求超时
    public static final String ERRORCODE_CID_003 = ERRORCODE_CID_PREFIX + "003"; //返回数据为空
    public static final String ERRORCODE_CID_004 = ERRORCODE_CID_PREFIX + "004"; //返回数据格式错误
    public static final String ERRORCODE_CID_005 = ERRORCODE_CID_PREFIX + "005"; //返回的 cid 参数无效
    public static final String ERRORCODE_CID_006 = ERRORCODE_CID_PREFIX + "006"; //返回 saas-ws 参数错误

    public static final String ERRORCODE_SERVICE_PREFIX = ERRORCODE_SDK_PREFIX + "201";
    public static final String ERRORCODE_SERVICE_001 = ERRORCODE_SERVICE_PREFIX + "001"; //网络连接失败
    public static final String ERRORCODE_SERVICE_002 = ERRORCODE_SERVICE_PREFIX + "002"; //请求超时
    public static final String ERRORCODE_SERVICE_003 = ERRORCODE_SERVICE_PREFIX + "003"; //返回数据为空
    public static final String ERRORCODE_SERVICE_004 = ERRORCODE_SERVICE_PREFIX + "004"; //返回数据格式错误
    public static final String ERRORCODE_SERVICE_005 = ERRORCODE_SERVICE_PREFIX + "005"; //cid 参数无效

    public static final String ERRORCODE_STOP_SERVICE_PREFIX = ERRORCODE_SDK_PREFIX + "202";
    public static final String ERRORCODE_STOP_SERVICE_001 = ERRORCODE_STOP_SERVICE_PREFIX + "001"; //网络连接失败
    public static final String ERRORCODE_STOP_SERVICE_002 = ERRORCODE_STOP_SERVICE_PREFIX + "002"; //请求超时
    public static final String ERRORCODE_STOP_SERVICE_003 = ERRORCODE_STOP_SERVICE_PREFIX + "003"; //返回数据为空
    public static final String ERRORCODE_STOP_SERVICE_004 = ERRORCODE_STOP_SERVICE_PREFIX + "004"; //返回数据格式错误

    public static final String ERRORCODE_SERVICE2_PREFIX = ERRORCODE_SDK_PREFIX + "211";
    public static final String ERRORCODE_SERVICE2_001 = ERRORCODE_SERVICE2_PREFIX + "001"; //网络连接失败
    public static final String ERRORCODE_SERVICE2_002 = ERRORCODE_SERVICE2_PREFIX + "002"; //请求超时
    public static final String ERRORCODE_SERVICE2_003 = ERRORCODE_SERVICE2_PREFIX + "003"; //返回数据为空
    public static final String ERRORCODE_SERVICE2_004 = ERRORCODE_SERVICE2_PREFIX + "004"; //返回数据格式错误
    public static final String ERRORCODE_SERVICE2_005 = ERRORCODE_SERVICE2_PREFIX + "005"; //返回的 cid 参数无效
    public static final String ERRORCODE_SERVICE2_006 = ERRORCODE_SERVICE2_PREFIX + "006"; //返回 saas-ws 参数错误
    public static final String ERRORCODE_SERVICE2_007 = ERRORCODE_SERVICE2_PREFIX + "007"; //返回 instance 连接参数错误

    public static final String ERRORCODE_CONTROL_PREFIX = ERRORCODE_SDK_PREFIX + "221";
    public static final String ERRORCODE_CONTROL_001 = ERRORCODE_CONTROL_PREFIX + "001"; //网络连接失败
    public static final String ERRORCODE_CONTROL_002 = ERRORCODE_CONTROL_PREFIX + "002"; //请求超时
    public static final String ERRORCODE_CONTROL_003 = ERRORCODE_CONTROL_PREFIX + "003"; //返回数据为空
    public static final String ERRORCODE_CONTROL_004 = ERRORCODE_CONTROL_PREFIX + "004"; //返回数据格式错误
    public static final String ERRORCODE_CONTROL_005 = ERRORCODE_CONTROL_PREFIX + "005"; //返回的 cid 参数无效
    public static final String ERRORCODE_CONTROL_006 = ERRORCODE_CONTROL_PREFIX + "006"; //返回 saas-ws 参数错误

    public static final String ERRORCODE_OTHER_PREFIX = ERRORCODE_SDK_PREFIX + "099";
    public static final String ERRORCODE_OTHER_001 = ERRORCODE_OTHER_PREFIX + "001"; //saas-ws 连接失败
    public static final String ERRORCODE_OTHER_002 = ERRORCODE_OTHER_PREFIX + "002"; //instance 连接参数错误
    public static final String ERRORCODE_OTHER_003 = ERRORCODE_OTHER_PREFIX + "003"; //测速结果低于服务下限
    public static final String ERRORCODE_OTHER_004 = ERRORCODE_OTHER_PREFIX + "004"; //SDK纪录游戏到时
    public static final String ERRORCODE_OTHER_009 = ERRORCODE_OTHER_PREFIX + "009"; //获取流地址超时
    public static final String ERRORCODE_OTHER_010 = ERRORCODE_OTHER_PREFIX + "010"; //刷新stoken超过次数上限
    public static final String ERRORCODE_OTHER_100 = ERRORCODE_OTHER_PREFIX + "100"; //user info为空
    public static final String ERRORCODE_OTHER_011 = ERRORCODE_OTHER_PREFIX + "011"; //检测到网络断开

    public static final String ERRORCODE_OTHER_099 = ERRORCODE_OTHER_PREFIX + "099"; //获取流地址超时,乒乓不正常


    public static final String ERRORCODE_APP_TIME_OUT = ERRORCODE_SDK_PREFIX + "999";
    public static final String ERRORCODE_APP_TIME_OUT_001 = ERRORCODE_APP_TIME_OUT + "001";//211、201 未返回成功
    public static final String ERRORCODE_APP_TIME_OUT_002 = ERRORCODE_APP_TIME_OUT + "002";//211、201成功 Access长连接失败
    public static final String ERRORCODE_APP_TIME_OUT_003 = ERRORCODE_APP_TIME_OUT + "003";//211、201成功 Access长连接成功 乒乓状态异常
    public static final String ERRORCODE_APP_TIME_OUT_004 = ERRORCODE_APP_TIME_OUT + "004";//211、201成功 Access长连接成功 乒乓状态正常
    public static final String ERRORCODE_APP_TIME_OUT_005 = ERRORCODE_APP_TIME_OUT + "005";//video成功，没收到第一帧
    public static final String ERRORCODE_APP_TIME_OUT_006 = ERRORCODE_APP_TIME_OUT + "006";//video失败，audio成功
    public static final String ERRORCODE_APP_TIME_OUT_007 = ERRORCODE_APP_TIME_OUT + "007";//video失败，audio失败，input成功
    public static final String ERRORCODE_APP_TIME_OUT_008 = ERRORCODE_APP_TIME_OUT + "008";//video失败，audio失败，input失败
    public static final String ERRORCODE_APP_TIME_OUT_009 = ERRORCODE_APP_TIME_OUT + "009";//流类型错误，saas返回流类型与sdk流类型不匹配

    public static final String ERRORCODE_WEBRTC = ERRORCODE_SDK_PREFIX + "666";
    public static final String ERRORCODE_WEBRTC_001 = ERRORCODE_WEBRTC + "001";//没流地址211、201 未返回成功
    public static final String ERRORCODE_WEBRTC_002 = ERRORCODE_WEBRTC + "002";//没流地址211、201成功 Access长连接失败
    public static final String ERRORCODE_WEBRTC_003 = ERRORCODE_WEBRTC + "003";//没流地址211、201成功 Access长连接成功 乒乓状态异常
    public static final String ERRORCODE_WEBRTC_004 = ERRORCODE_WEBRTC + "004";//没流地址211、201成功 Access长连接成功 乒乓状态正常
    public static final String ERRORCODE_WEBRTC_005 = ERRORCODE_WEBRTC + "005";//有流地址，signal连接失败
    public static final String ERRORCODE_WEBRTC_006 = ERRORCODE_WEBRTC + "006";//有流地址，未收到offer
    public static final String ERRORCODE_WEBRTC_007 = ERRORCODE_WEBRTC + "007";//有流地址，未发送answer
    public static final String ERRORCODE_WEBRTC_008 = ERRORCODE_WEBRTC + "008";//有流地址，未发送candidate
    public static final String ERRORCODE_WEBRTC_009 = ERRORCODE_WEBRTC + "009";//有流地址，未收到candidate
    public static final String ERRORCODE_WEBRTC_010 = ERRORCODE_WEBRTC + "010";//有流地址，webrtc连接超时
    public static final String ERRORCODE_WEBRTC_011 = ERRORCODE_WEBRTC + "011";//有流地址，webrtc连接成功，input成功
    public static final String ERRORCODE_WEBRTC_012 = ERRORCODE_WEBRTC + "012"; //有流地址，webrtc连接成功，input成功，没收到第一帧
    public static final String ERRORCODE_WEBRTC_013 = ERRORCODE_WEBRTC + "013";//有流地址，webrtc连接成功，input失败
    public static final String ERRORCODE_WEBRTC_014 = ERRORCODE_WEBRTC + "014"; //有流地址，webrtc连接成功，input失败，没收到第一帧
    public static final String ERRORCODE_WEBRTC_015 = ERRORCODE_WEBRTC + "015";//有流地址，webrtc连接失败，input成功
    public static final String ERRORCODE_WEBRTC_016 = ERRORCODE_WEBRTC + "016";//有流地址，webrtc连接失败，input失败
    public static final String ERRORCODE_WEBRTC_017 = ERRORCODE_WEBRTC + "017";//有流地址，webrtc连接失败，input失败
    public static final String ERRORCODE_WEBRTC_018 = ERRORCODE_WEBRTC + "018";//webrtc crash


    public static final String ERRORCODE_ERROR = ERRORCODE_SDK_PREFIX + "000";
    public static final String ERRORCODE_ERROR_000001 = ERRORCODE_ERROR + "001";
    public static final String ERRORCODE_ERROR_000002 = ERRORCODE_ERROR + "002";
    public static final String ERRORCODE_ERROR_000003 = ERRORCODE_ERROR + "003";
    public static final String ERRORCODE_ERROR_000004 = ERRORCODE_ERROR + "004";
    /**
     * 请求参数出错
     */
    public static final String ERRORCODE_ERROR_000005 = ERRORCODE_ERROR + "005";

    public static final String TIPS_SPECIAL_TAG = "\\|";

    public static final String PING = "ping:";
    public static final String PONG = "pong:";
    public static final String PING2 = "ping2:";
    public static final String PONG2 = "pong2:";
    public static final String RESET_INPUT_TIMER = "resetInputTimer";

    public static final String PING_DELAY_TIME = "ping_delay_time";
    public static final String PING_INTERVAL_TIME = "ping_interval_time";
    public static final String TRACE_DELAY_TIME = "trace_delay_time";
    public static final String PING2_INTERVAL_TIMES = "ping2_interval_times";
    public static final String DEFAULT_PING2_INTERVAL_TIMES = "20,10;10,20;0,60";
    public static final String REPORT_FRAME_DELAY_INTERVAL_TIMES = "report_delay_interval_times";
    public static final String WEBSOCKET_HEART_BEAT_INTERVAL = "web_socket_ping_interval";
    public static final String WEBSOCKET_HEART_BEAT_TIME_OUT_COUNT_MAX = "web_socket_ping_time_out_count_max";

    public static final String SHOW_ESTIMETE_TIME = "showEstimateTime";       //是否显示预估时间
    //    public static final String AUTO_ADJUST_DEFINITION = "autoAdjustDefinition";   //是否自动调整清晰度
    public static final String FLAG_AUTO_SWITCH_BITRATE = "flagAutoSwitchBitRate"; //是否自动调整码率
    public static final String FRAME_DELAY_INFO_INTERVAL = "frameDelayInfo_interval"; //帧平均延时上报和计算周期
    public static final String FEATURE_ENABLE = "1";
    public static final String FEATURE_DISABLE = "0";

    public static final String SWITCH_BR_DURATION = "switchBR_duration";
    public static final String SWITCH_BR_DETECT_INTERVAL = "detect_interval";
    public static final String SWITCH_BR_PLAY_TIME_DELAY = "frameDelayInfo_triggerDelayTimeSinceLastCheck";
    public static final String SWITCH_BR_FROZENTIME = "switchBR_frozentime";
    public static final String SWITCHBR_QUOTIETY = "switchBR_quotiety";
    public static final String PEAKRATE_COEF = "bw_check_peakRateCoefficient";
    public static final String STD_COEF = "bw_check_standardDeviationCoefficient";
    public static final String SKIP_NUMBER = "bw_check_skipNumber";
    public static final String SPEED_TEST_URL = "bw_check_small_file_url";
    public static final String SPEED_TEST_URL_LARGE = "bw_check_large_file_url";
    public static final String SPEED_TEST_DURATION = "bw_check_duration";
    public static final String SPEED_COEFFICIENT = "speed_coefficient";
    public static final String FRAME_DELAYINFO_IS_REPORT_DETAILINFO = "frameDelayInfo_isReportDetailInfo";
    public static final String FRAME_DELAY_START_DELAYTIME = "frameDelayStartDelayTime";
    public static final String BUTTON_MAP_CONTENT = "key_map_content_tv";
    public static final String KEYBOARD_MAP_CONTENT = "key_map_content";

    // public static final String TOAST_SUGGEST_DOWNLOAD = "toast_suggest_download";
    public static final String TOAST_SUGGEST_CHANGE_RATE = "toast_suggest_change_rate";
    public static final String TOAST_ADJUSTMENT_RATE = "toast_adjustment_rate";
    public static final String TOAST_DOING_CHANGE_RATE = "toast_doing_change_rate";
    public static final String TOAST_CURRENT_CHANGE_RATE = "toast_current_change_rate";
    public static final String TOAST_SWITCH_FAIL = "toast_switch_fail";

    public static String ADVERT_PATH = null;
    public static boolean PLAY_PREPARE = false;
    public static final int LEVEL_SDK = Color.GREEN;
    public static int LOADING_BG = Color.BLACK;
    public static String DATA_PATH = null;
    public static String MINIMUM_BITRATE = "";

    public static final String SHARED_PREF_LAUNCH_TIMES = "launchTimes";

    public static final int STATUS_TIPS_CHANGE_WIFI_TO_4G = 5;
    public static final int STATUS_TIPS_CHANGE_4G_TO_WIFI = 4;
    public static final int STATUS_NETWORK_UNAVAILABLE = 6;
    public static final int STATUS_WAIT_CHOOSE = 7;
    public static final int STATUS_OPERATION_STREAM_URL = 14;
    public static final int STATUS_OPERATION_REFUSE_QUEUE = 10;
    public static final int STATUS_PLAY_INTERNAL = 1;
    public static final int STATUS_SWITCH_RESOLUTION = 20;
    public static final int STATUS_SWITCH_RESOLUTION_ERROR = 21;
    public static final int STATUS_AUTO_SWITCH_RESOLUTION = 25;
    public static final int STATUS_START_PLAY = 2;
    public static final int STATUS_STOP_PLAY = 3;

    public static final int STATUS_START_RECONNECTION = 8;
    public static final int STATUS_CONNECTION_ERROR = 9;
    public static final int STATUS_MEDIAPLAYER_ERROR = 22;
    public static final int STATUS_TIME_OUT = 23;
    public static final int STATUS_TOAST_NO_INPUT = 11;
    public static final int STATUS_TREFRESH_STOKEN_MAX_TIMES = 12;
    public static final int STATUS_OPERATION_INTERVAL_TIME = 13;
    public static final int STATUS_OPERATION_GAME_OVER = 15;
    public static final int STATUS_OPERATION_WAITING = 16;
    public static final int STATUS_OPERATION_READY_PAUSE_SAAS_SERVER = 17;
    public static final int STATUS_OPERATION_PAUSE_SAAS_SERVER = 18;
    public static final int STATUS_OPERATION_PAUSED_SAAS_SERVER = 19;
    public static final int STATUS_OPERATION_FORCED_OFFLINE = 24;
    public static final int STATUS_SPEED_LOWER_BITRATE = 26;
    public static final int STATUS_OPERATION_OPEN_MORE_SAME_GAME = 27;
    public static final int STATUS_GAME_OVER = 28;
    public static final int STATUS_OPERATION_HMCP_ERROR = 29;
    public static final int STATUS_OPERATION_GAME_TIME_COUNT_DOWN = 30;
    public static final int STATUS_OPERATION_GAME_TIME_UPDATE = 31;
    public static final int STATUS_OPERATION_GAME_TIME_HIGHLIGHT = 32;
    public static final int STATUS_RECEIVE_META_INFOS = 33;
    public static final int STATUS_PLAY_FOR_TEST = 34;
    public static final int STATUS_PAUSE_PLAY = 35;
    public static final int STATUS_OPERATION_NO_INPUT_TIME_REMIND = 36;
    public static final int STATUS_SHOW_SETTING_VIEW = 101;
    public static final int STATUS_FIRST_FRAME_ARRIVAL = 102;
    public static final int STATUS_CLOUDPHONE_ERROR = 100;
    public static final int STATUS_GET_CONTRON_ERROR = 40;
    public static final int STATUS_STRAM_TYPE_ERROR = 41;
    public static final int STATUS_OPERATION_STATE_CHANGE_REASON = 42;
    public static final int STATUS_SWITCH_RESOLUTION_LIST = 43;
    public static final int STATUS_IDCINFO = 44;


    //TODO: Move follown code to HmRTCGlobalConfig
    public static int wertcConnectTimeout = 10 * 1000;          //默认webrtc连接超时
    public static int webrtcSignalTimeout = 2000;               //默认webrtc signal连接超时
    public static int webrtcSignalReconnectionDelayMax = 1000;  //默认webrtc signal重连延迟
    public static int webrtcSignalReconnectionAttempts = 5;     //默认webrtc signal重连次数

    //sei相关
    public static final String H264_SEI_DATA_ENABLE = "h264_sei_data_enable";//sei数据开关键值
    public static final int SEI_REPORT_INTERVAL_MIN = 1;
    public static final int SEI_REPORT_INTERVAL_MAX = 5;
    public static final int SEI_ON = 1;//sei开启
    public static final int SEI_OFF = 0;//sei关闭

    /**
     * 每次打开摄像头，都向app申请权限,在 play的时候通过bundle传入 boolean 值
     */
    public static final String OPEN_CAMERA_PERMISSION_CHECK = "openCameraPermissionCheck";//每次打开摄像头，都向app申请权限
}