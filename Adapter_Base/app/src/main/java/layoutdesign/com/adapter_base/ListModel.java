package layoutdesign.com.adapter_base;

/**
 * Created by Pujan on 30-01-2018.
 */

class ListModel {
    private int image;
    private String lang;
    public ListModel(Integer image, String lang) {
        this.image = image;
        this.lang = lang;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}

