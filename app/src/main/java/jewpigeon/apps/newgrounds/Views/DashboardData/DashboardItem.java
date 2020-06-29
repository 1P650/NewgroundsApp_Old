package jewpigeon.apps.newgrounds.Views.DashboardData;

import android.graphics.drawable.Drawable;


public class DashboardItem {

    private Drawable image;
    private String title;
    private String author;

    public DashboardItem(Drawable image, String title, String author) {
        this.image = image;
        this.title = title;
        this.author = author;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
