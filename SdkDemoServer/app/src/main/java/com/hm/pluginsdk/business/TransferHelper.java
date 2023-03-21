package com.hm.pluginsdk.business;


import android.util.Log;

import com.hm.pluginsdk.RefInvoke;
import com.hm.pluginsdk.ReflectCallBack;
import com.hm.pluginsdk.ReflectConfig;
import com.hm.pluginsdk.ReflectHelper;

import java.lang.reflect.Method;

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2022/4/19
 * 描述: TransferHelper 插件化对外提供的对象
 * 历史: history<br/>
 * ================================================
 */
public class TransferHelper {


    private static final String TAG = TransferHelper.class.getSimpleName();
    /**
     * saas-sdk中真实的对象，反射设置
     */
    private Object relTransferHelper;

    /**
     * 私有构造，不允许外部直接创建对象
     */
    private TransferHelper() {
    }


    public void connect() {
        RefInvoke.invokeInstanceMethod(relTransferHelper, "connect");
    }


    public boolean isConnected() {
        Object result = RefInvoke.invokeInstanceMethod(relTransferHelper, "isConnected");
        if (result instanceof Boolean) {
            return (boolean) result;
        } else {
            return false;
        }
    }

    public int send(byte[] buffer) {
        Object result = RefInvoke.invokeInstanceMethod(relTransferHelper, "send", byte[].class, buffer);
        if (result instanceof Integer) {
            return (int) result;
        } else {
            return -1;
        }
    }


    public void disconnect() {
        RefInvoke.invokeInstanceMethod(relTransferHelper, "disconnect");
    }


    public void setCallback(Callback callback) {
        try {
            Object[] oArray = {new Object()};
            Class<?>[] paraClass = {ReflectConfig.TransferHelperCallback.getReflectClass()};

            ReflectHelper.invokeContainsInterfaceMethod(relTransferHelper,
                    "setCallback",
                    oArray,
                    paraClass,
                    ReflectConfig.TransferHelperCallback.getReflectClassName(),
                    new ReflectCallBack() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            switch (method.getName()) {
                                case "onConnect":
                                    callback.onConnect((boolean) args[0]);
                                    break;
                                case "onClose":
                                    callback.onClose();
                                    break;
                                case "onBinaryMessage":
                                    callback.onBinaryMessage((byte[]) args[0]);
                                    break;
                                case "onTextMessage":
                                    callback.onTextMessage((String) args[0]);
                                    break;
                                default:
                            }
                            return new Object();
                        }
                    });
        } catch (Exception e) {
            Log.e(TAG, "setCallback exception", e);
        }
    }

    public interface Callback {

        void onConnect(boolean isReconnect);

        void onClose();

        void onBinaryMessage(byte[] payload);

        void onTextMessage(String payload);
    }


}