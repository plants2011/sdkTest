package com.hm.qa.sdkdemoserver.utils;


import android.util.Log;

import com.hm.qa.sdkdemoserver.BuildConfig;
import com.hm.qa.sdkdemoserver.Constant;
import com.hm.qa.sdkdemoserver.inst.bean.ReportInfo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * jacoco上报运行数据的工具类
 * @author shensulu
 */
public class JacocoUtil {
    private static String reportJson = null;

    /**
     * 初始化要上报给服务端的数据,包含jira-id、构建类型等等
     *
     * @return json数据
     */
    public static String initData() {
        if (reportJson == null || reportJson.isEmpty()) {
            ReportInfo reportInfo = new ReportInfo();
            reportInfo.setApp(Constant.mChannelID);
            reportInfo.setJiraID(Constant.JACOCO_REPORT_JIRA_ID);
            reportInfo.setBuildVersion(Constant.JACOCO_REPORT_GIT_COMMIT_ID);
            reportInfo.setBuildType(Constant.JACOCO_REPORT_BUILD_TYPE);
            reportInfo.setJenkinsTask(Constant.JACOCO_REPORT_JENKINS_BUILD);
            reportInfo.setRemark(Constant.JACOCO_REPORT_REMARK);
            reportInfo.setChannel(Constant.JACOCO_REPORT_CHANNEL);
            reportJson = JsonUtil.toJsonString(reportInfo);
            Log.d("JacocoUtil-json:", reportJson);
        }
        return reportJson;
    }

    /**
     * 上报覆盖率数据到服务器
     *
     * @param address ip地址
     * @param port 端口
     * @return
     */
    public static String execUpload(String address, int port) {
        if (!ipCheck(address)) {
            return "ip地址格式错误";
        }
        Socket socket = new Socket();
        DataOutputStream out = null;
        try {
            SocketAddress server = new InetSocketAddress(address, port);
            socket.connect(server, 3000);
            out = new DataOutputStream(socket.getOutputStream());
            Object agent = Class.forName("org.jacoco.agent.rt.RT")
                    .getMethod("getAgent")
                    .invoke(null);
            byte[] executionData = (byte[]) agent.getClass().getMethod("getExecutionData", boolean.class).invoke(agent, false);
            byte[] reportInfo = JacocoUtil.initData().getBytes();
            out.write(reportInfo, 0, reportInfo.length);   //上报构建信息
            out.write(System.getProperty("line.separator").getBytes()); //换行
            out.write(executionData, 0, executionData.length); //上报覆盖率数据
            out.flush();
            return "上传数据成功";
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            return "覆盖率只能在debug包内收集:" + cnfe;
        } catch (Exception e) {
            e.printStackTrace();
            return "上报数据异常：" + e;
        } finally {
            if (out != null) {
                try {
                    out.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 判断IP地址的合法性，这里采用了正则表达式的方法来判断
     * return true，合法
     */
    public static boolean ipCheck(String text) {
        if (text != null && !text.isEmpty()) {
            // 定义正则表达式
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\." +
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." +
                    "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            // 判断ip地址是否与正则表达式匹配
            if (text.matches(regex)) {
                // 返回判断信息
                return true;
            } else {
                // 返回判断信息
                return false;
            }
        }
        return false;
    }
}
