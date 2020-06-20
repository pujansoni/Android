package firebaseauth.android.com.recyclerviewproject;

/**
 * Created by Pujan on 19-02-2018.
 */

public class ListItem {
    private String head;
    private String desc;
    private String imageUrl;

    public ListItem(String head, String desc, String imageUrl) {
        this.head = head;
        this.desc = desc;
        this.imageUrl = imageUrl;
    }

    //here we are only using the getters and not the setters because we will only getting the data and displaying it, but not setting the data
    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
