package com.example.jv_khadijeh;

public class Search {
   public int image;
    public String title , deiscribtion;

    public Search(int image, String title, String deiscribtion) {
        this.image = image;
        this.title = title;
        this.deiscribtion = deiscribtion;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeiscribtion() {
        return deiscribtion;
    }

    public void setDeiscribtion(String deiscribtion) {
        this.deiscribtion = deiscribtion;
    }
}
