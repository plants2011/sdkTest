package com.hm.pluginsdk.beans;

/**
 * 云端文件信息实体bean
 */
public class CloudFile {

    final public static int IMAGE_TYPE_DEFAULT = 0;// 默认图片类型
    final public static int IMAGE_TYPE_QRCODE = 1;// 二维码类型

    protected String name;
    protected String path;
    protected int imageType;// 0 图片 1 二维码

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getImageType() {
        return imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

}
