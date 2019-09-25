package edu.udacity.java.nano.chat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;


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
    private static JsonObject convertStringToJSON(String jsonStr) {
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
        return jsonObject;
    }
    private static void sendMessageToAll(Message message)
            throws IOException {
        // TODO: add send message method.
        // For each online session, send message in json format
        // convert the message to a json string first
        try {
            for (Map.Entry<String, Session> userSession: onlineSessions.entrySet()) {
                JsonObject jsonObject = convertStringToJSON(message.getMessage());
                Session sessionId = userSession.getValue();
                if (sessionId != null) {
                    // broadcast json object
                    sessionId.getBasicRemote().sendText(userSession.getKey() + " : " + jsonObject);
                }
            }
        } catch (Exception ex) {

        }
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
    public void onMessage(Session session, String jsonStr, @PathParam("username") String username) throws IOException {
        // TODO: add send message.
        // create a new message using the jsonStr parameter
        Message message = new Message();
        message.setUsername(username);
        message.setMessage("Connected!");
        sendMessageToAll(message);
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
