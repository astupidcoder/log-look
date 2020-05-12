package com.taiji.handle;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.InputStream;

/**
 * @author liuyj
 * @Title: LogWebSocketHandle
 * @create 2019-10-18 16:56
 * @ProjectName WS_Service
 * @Description: TODO
 */

@ServerEndpoint("/log")
public class LogWebSocketHandle {

    private Process process;
    private InputStream inputStream;

    @OnOpen
    public void onOpen(Session session){
        try {
   //tail -f /usr/local/apache-tomcat-8.5.45/logs/catalina.out
           process=  Runtime.getRuntime().exec("tail -f /usr/local/apache-tomcat-8.5.45/logs/catalina.out");
           inputStream=process.getInputStream();

            TailLogThread tailLogThread=new TailLogThread(inputStream,session);
            tailLogThread.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @OnClose
    public void onClose(){
        try {

            if(inputStream!=null){
                inputStream.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (process!=null){
            process.destroy();
        }
    }


    @OnError
    public void onError(Throwable thr) {
        thr.printStackTrace();
    }
}
