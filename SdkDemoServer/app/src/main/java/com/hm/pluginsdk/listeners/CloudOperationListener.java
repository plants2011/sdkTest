package com.hm.pluginsdk.listeners;

import com.hm.pluginsdk.beans.CloudFile;
import com.hm.pluginsdk.enums.CloudOperation;

/**
 * 云操作回调监听Listener
 */
public interface CloudOperationListener {

    /**
     * 单个文件上的传或者下载完成回调
     *
     * @param operation 任务类型，上传、下载
     * @param file      上传、下载完成的文件
     */
    void onSuccess(CloudOperation operation, CloudFile file);

    /**
     * 所有文件的上传或者下载完成回调
     *
     * @param operation 任务类型，上传、下载
     */
    void onFinish(CloudOperation operation);

    /**
     * 任务非主动取消，而造成任务停止的回调，譬如网络中断场景，导致任务停止
     *
     * @param operation 任务类型，上传、下载
     * @param message   消息，json格式
     */
    void onStop(CloudOperation operation, String message);

    /**
     * 任务状态回调，譬如上传任务运行中，再次调用上传接口
     * @param operation 任务类型，上传、下载
     * @param message 状态消息，，json格式
     */
//    void onStatus(CloudOperation operation, int type, String message);

    /**
     * 错误回调
     *
     * @param operation 任务类型，上传、下载
     * @param message   错误消息，json格式
     */
    void onError(CloudOperation operation, String message);

    /**
     * 任务取消成功回调
     *
     * @param operation
     */
    void onCancel(CloudOperation operation);
}
