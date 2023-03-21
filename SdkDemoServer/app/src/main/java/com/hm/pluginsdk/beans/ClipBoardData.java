package com.hm.pluginsdk.beans;


import java.util.List;

/**
 * 剪切板数据实体类
 */
public class ClipBoardData {

    public static final int CLIPBOARD_DATA_SIZE = 1 * 1024;
    public static final String CLIPBOARD_DATA_CODE = "paste";
    public static final int CLIPBOARD_DATA_TYPE_PLAIN = 0;
    public static final int CLIPBOARD_DATA_TYPE_BASE64 = 1;

    /**
     * paste表示传入
     */
    public String code;
    /**
     * 剪贴板数据
     */
    public List<ClipBoardItemData> data;
    /**
     * 0明文，1base64化数据
     */
    public int dataType;

    public static boolean isDataValid(ClipBoardData clipBoardData) {
        if (clipBoardData == null) {
            return false;
        }

        if (!checkDataSize(clipBoardData.data)) {
            return false;
        }

        return true;
    }

    public static boolean checkDataSize(List<ClipBoardItemData> data) {
        if (data == null) {
            return false;
        }

        for (ClipBoardItemData clipBoardItemData : data) {

            if (clipBoardItemData != null &&
                    clipBoardItemData.itemData != null &&
                    clipBoardItemData.itemData.length() > CLIPBOARD_DATA_SIZE) {
                return false;
            }
        }

        return true;
    }
}
