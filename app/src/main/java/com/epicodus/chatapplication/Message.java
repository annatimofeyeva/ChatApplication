package com.epicodus.chatapplication;


//@Parcel
public class Message {
    String content;
    String key;



    public Message() {}

    public Message(String content, String key) {

        this.content = content;
        this.key = key;
    }

    public String getContent() {

        return content;
    }
}
