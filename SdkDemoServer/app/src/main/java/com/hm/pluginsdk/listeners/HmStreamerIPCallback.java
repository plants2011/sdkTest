package com.hm.pluginsdk.listeners;

import org.json.JSONObject;

/**
 * 当前用户连接的推流IP信息回调
 * 推流IP信息包括IP类型和IP地址，支持IPv4和IPv6，一次返回两个IP以及类型，
 * 主IP即当前使用的IP，例如ipv4:222.208.103.17:9122（主），ipv6:[2001:0db8:3c4d:0015:0000:0000:1a2f:1a2b]:5678（备）；
 */
public interface HmStreamerIPCallback {
    void onConnectionIPChanged(JSONObject ipList);
}
