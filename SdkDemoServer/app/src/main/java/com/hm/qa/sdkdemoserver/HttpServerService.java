package com.hm.qa.sdkdemoserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.hm.qa.sdkdemoserver.stub.AutomatorHttpServer;
import com.hm.qa.sdkdemoserver.stub.Log;
import com.hm.qa.sdkdemoserver.service.SdkServiceImpl;

import java.io.IOException;

public class HttpServerService extends Service implements Runnable {
    private AutomatorHttpServer server = new AutomatorHttpServer(9001);

    public HttpServerService() {
    }

    @Override
    public void onCreate(){
        server.route("/jsonrpc/hm_sdk", new JsonRpcServer(new ObjectMapper(), new SdkServiceImpl(), SdkServiceImpl.class));
        new Thread(this).start();
        Toast.makeText(getApplicationContext(), "启动服务成功/jsonrpc/hm_sdk", Toast.LENGTH_SHORT);
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void run() {
        try {
            Log.i("启动服务成功， /jsonrpc/hm_sdk");

            server.start();
        } catch (IOException e) {
            Log.e("FAILED, "+e.getMessage());
            return;
        }
    }
}