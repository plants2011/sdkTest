package com.hm.qa.sdkdemoserver.inst;

import com.hm.qa.sdkdemoserver.inst.bean.WaitResult;

import java.util.HashMap;
import java.util.Map;

public class WaitingInstance {
    public final static Integer waitTimeout = 20*1000;
    private static Boolean waiting = false;
    private static WaitResult waitResult = null;

    public static Map getWaitTimeoutError(){
        Map ret = new HashMap();
        ret.put("result", false);
        ret.put("message", "wait time out");
        return ret;
    }
    public static void waitForCompleted() throws InterruptedException {
        Long now = System.currentTimeMillis();
        while(waiting == false && (System.currentTimeMillis() - now) < waitTimeout){
            Thread.sleep(200);
        }
    }
    public static WaitResult waitForWaitResultCompleted() throws InterruptedException {
        return waitForWaitResultCompleted(waitTimeout);
    }
    public static WaitResult waitForWaitResultCompleted(Integer timeout) throws InterruptedException {
        Long now = System.currentTimeMillis();
        while(getWaitResult() == null && (System.currentTimeMillis() - now) < waitTimeout){
            Thread.sleep(200);
        }
        WaitResult waitingResult = getWaitResult();
        if(waitingResult == null){
            waitingResult = new WaitResult();
            waitingResult.setResult(false);
            waitingResult.setMessage("wait timeout");
        }
        return waitingResult;
    }

    public static Boolean getWaiting() {
        return waiting;
    }

    public static void setWaiting(Boolean waiting) {
        WaitingInstance.waiting = waiting;
    }

    public static WaitResult getWaitResult() {
        return waitResult;
    }

    public static void setWaitResult(WaitResult waitResult) {
        WaitingInstance.waitResult = waitResult;
    }
}
