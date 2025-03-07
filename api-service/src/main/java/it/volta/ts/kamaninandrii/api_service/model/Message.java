package it.volta.ts.kamaninandrii.api_service.model;

public class Message {
    private String content;


    public Message() {}

    public Message(String content) {
        this.content = content;

    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                '}';
    }
}
