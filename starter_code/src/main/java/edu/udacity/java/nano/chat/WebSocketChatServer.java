package edu.udacity.java.nano.chat;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint(value="/chat/{username}")
public class WebSocketChatServer {
    //private Session session;
    //private static Set<WebSocketChatServer> chatEndpoints = new CopyOnWriteArraySet<>();
    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(Message message)
            throws IOException {
        //TODO: add send message method.
        /*chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().
                            sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        }); */
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    //@OnOpen
    /*public void onOpen(Session session) {
        //TODO: add on open connection.
    } */

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {
        //this.session = session;
        //chatEndpoints.add(this);
        onlineSessions.put(username, session);
        /*Message message = new Message();
        String userName = username;
        message.setUsername(username);
        message.setMessage("Connected!");
        String prevCount = message.getOnlineCount();
        // TODO: Increase count by 1
        sendMessageToAll(message); */
        System.out.println( username + " just joined the chat!");
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr, @PathParam("username") String username) {
        //TODO: add send message.
        //message.setFrom(username);
        //broadcast(message);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        //TODO: add close connection.
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
