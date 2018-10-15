package system.model;

import java.io.Serializable;

public class OneAnswer implements Serializable {

    public OneAnswer(String text, String image) {
        this.text = text;
        this.image = image;
    }

    private String text, image;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
