package edu.udacity.java.nano.chat;

import org.springframework.stereotype.Component;
import org.json.*;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint(value="/chat/{username}")
public class WebSocketChatServer {
    private Session session;
    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String jsonString)
            throws IOException {
        // TODO: add send message method.
        // For each online session, send message in json format
        // convert the message to a json string first
        try {
            //(Session sess: onlineSessions.values()) { if (sess.isOpen()) { sess.getBasicRemote().sendObject(msg); } }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {
        this.session = session;
        // add session to onlineSessions map
        onlineSessions.put(username, session);
        // construct a new message containing a online session size
        Message message = new Message();
        String userName = username;
        message.setUsername(username);
        message.setMessage("Connected!");
        String prevCount = message.getOnlineCount();
        // TODO: Increase count by 1
        //sendMessageToAll(message);
        System.out.println( username + " just joined the chat!");
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr, @PathParam("username") String username) throws IOException, JSONException {
        // TODO: add send message.
        // create a new message using the jsonStr parameter
        Message message = new Message();
        message.setUsername(username);
        System.out.println("entered on_message");
        JSONObject objJson = new JSONObject(jsonStr);
        String userName = objJson.getString("username");
        String msg = objJson.getString("msg");
        message.setMessage("{type");
        // onlineSessions.size()
        //sendMessageToAll(jsonString);

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
