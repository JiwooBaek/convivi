package model;

import java.util.HashMap;
import java.util.Map;

public class ChatModel {
    public String host;
    public String roomNumber;

    public Map<String, User> users = new HashMap<>();
    public Map<String, Comment> comments = new HashMap<>();


    public static class User {
        public String uid;
    }
    public static class Comment {
        public String uid;
        public String message;
    }
}
