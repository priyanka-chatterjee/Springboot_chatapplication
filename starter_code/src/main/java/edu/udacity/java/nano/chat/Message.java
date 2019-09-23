package edu.udacity.java.nano.chat;

import javax.validation.constraints.NotNull;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.
    private String username;
    private String message;
    private String type;
    private String onlineCount;

    public Message() {}
    public Message(String username, String message, String type, String onlineCount) {
        this.username = username;
        this.message = message;
        this.type = type;
        this.onlineCount = onlineCount;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getType() {
        return message;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getOnlineCount() {
        return onlineCount;
    }
    public void setOnlineCount(String onlineCount) {
        this.onlineCount = onlineCount;
    }

}
