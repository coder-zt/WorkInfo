package com.working.other;

public class MessageEvent {
    public static final String upload_data_success = "upload_data_success";
    public static final String upload_file_success = "upload_file_success";
    private String message;
    private String data;

    public MessageEvent(String message) {
        this.message = message;
    }

    public MessageEvent(String message, String data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }
}
