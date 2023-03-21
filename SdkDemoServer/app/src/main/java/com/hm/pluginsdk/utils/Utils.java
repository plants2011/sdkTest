package com.hm.pluginsdk.utils;

import android.text.TextUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * 通用工具类
 */
public class Utils {

    /**
     * 从HashMap删除某一个元素
     * 通过使用迭代器自己封装的remove()方法，最后一步多了一个操作expectedModCount = modCount
     * 从而避免了java.util.ConcurrentModificationException
     *
     * @param key
     * @param map
     */
    public static void removeFromMap(String key, Map<String, ? extends Object> map) {
        if (map != null && map.containsKey(key)) {
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                if (TextUtils.equals(key, iterator.next())) {
                    iterator.remove();
                    map.remove(key);
                    break;
                }
            }
        }
    }
}
