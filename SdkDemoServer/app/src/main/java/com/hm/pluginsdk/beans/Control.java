package com.hm.pluginsdk.beans;


import com.hm.pluginsdk.enums.ScreenOrientation;
import com.hm.pluginsdk.enums.Source;

/**
 * ================================================
 *
 * @author : NeWolf
 * @version : 1.0
 * @date :  2022/5/30
 * 描述: Control.java 控制权转移参数
 * 历史: version 1.1: 增加 source字段，咪咕录音音源，分为APP处理和SDK处理
 * ================================================
 */
public class Control {

    public String cid = "";
    public String pinCode = "";
    public String accessKeyID = "";
    public boolean isIPV6;
    public String clientISP = "";
    public String clientProvince = "";
    public String clientCity = "";
    public ScreenOrientation orientation = ScreenOrientation.LANDSCAPE;
    public boolean cameraOpenPermissionCheck = false;
    public Source source = Source.SDK;
}