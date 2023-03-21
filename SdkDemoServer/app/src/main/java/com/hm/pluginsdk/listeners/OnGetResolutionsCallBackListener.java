package com.hm.pluginsdk.listeners;


import com.hm.pluginsdk.beans.ResolutionInfo;

import java.util.List;

/**
 * 获取分辨率列表回调
 */

public interface OnGetResolutionsCallBackListener {

    void success(List<ResolutionInfo> mResolutionList);

    void fail(String msg);
}
