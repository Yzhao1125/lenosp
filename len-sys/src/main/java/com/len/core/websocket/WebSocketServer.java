package com.len.core.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 建立设备管理界面与数据库的连接
 */
@ServerEndpoint("/websocket")
@Component
public class WebSocketServer {

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
  //  public static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<String, WebSocketServer>(16);

    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session =session;
        System.out.println("有连接加入");
        webSocketSet.put(webSocketSet.size()+"",this);
//        ThreadOne thread =null;
//        thread = new ThreadOne(session);
//        thread.start();
    }

    @OnClose
    public void onClose(){
        System.out.println("closed");
    }

    @OnError
    public void onError(Throwable error){
        System.out.println("error");
    }


    @OnMessage
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
