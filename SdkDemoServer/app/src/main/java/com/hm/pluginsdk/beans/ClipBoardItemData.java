package com.hm.pluginsdk.beans;


/**
 * 剪切板数据条目
 */
public class ClipBoardItemData {

    public static final String TYPE_TEXT_PLAIN = "text/plain";
    public static final String TYPE_TEXT_HTML = "text/html";
    public static final String TYPE_TEXT_URL_LIST = "text/url-list";

    public String itemType;
    public String itemData;


}
