package com.example.planai_front;

public class FriendlistItem {
        private int imageResource;
        private String text;
        private String buttonText;

        public FriendlistItem(int imageResource, String text, String buttonText) {
            this.imageResource = imageResource;
            this.text = text;
            this.buttonText = buttonText;
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

}
