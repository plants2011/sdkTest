package com.hm.pluginsdk.widgets;


import com.hm.pluginsdk.RefInvoke;

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2022-04-23
 * 描述: HmcpIJKVideoViewListenerIml为 HmcpIJKVideoViewListener的实现类，内部要持有saas-sdk的监听对象
 * 历史: history<br/>
 * ================================================
 */
public class HmcpIJKVideoViewListenerIml implements HmcpIJKVideoViewListener {
    //反射赋值
    private Object relListener;

    @Override
    public void onFirstFrameArrival() {
        RefInvoke.invokeInstanceMethod(relListener, "onFirstFrameArrival");
    }

    @Override
    public void onError() {
        RefInvoke.invokeInstanceMethod(relListener, "onError");
    }

    @Override
    public void onPlayerRelease() {
        RefInvoke.invokeInstanceMethod(relListener, "onPlayerRelease");
    }

    @Override
    public void onPlayerStop() {
        RefInvoke.invokeInstanceMethod(relListener, "onPlayerStop");
    }
}
