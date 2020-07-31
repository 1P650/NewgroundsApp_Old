package jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems;

import android.graphics.drawable.Drawable;


public class DashGridItemSmall {

    private Drawable image;
    private String author;

    public DashGridItemSmall(Drawable image, String author) {
        this.image = image;
        this.author = "by " + author;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
