package com.example.planai_front;

public class GetFriendItem {
    private int imageResource;
    private String text;
    private String buttonText;

    private String buttonText2;

    public GetFriendItem(int imageResource, String text, String buttonText, String buttonText2) {
        this.imageResource = imageResource;
        this.text = text;
        this.buttonText = buttonText;
        this.buttonText2 = buttonText2;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }

    public String getButtonText() {
        return buttonText;
    }

    public String getButtonText2() {
        return buttonText2;
    }

}