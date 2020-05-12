package com.taiji.handle;

import javax.websocket.Session;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author liuyj
 * @Title: TailLogThread
 * @create 2019-10-18 17:01
 * @ProjectName WS_Service
 * @Description: TODO
 */
public class TailLogThread extends Thread{

    private BufferedReader reader;
    private Session session;
    public TailLogThread(InputStream in, Session session){

        this.reader=new BufferedReader(new InputStreamReader(in));
        this.session=session;
    }

    @Override
    public void run() {
        String line;
        try {

            while((line=reader.readLine())!=null){
                session.getBasicRemote().sendText(line+"<br>");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

