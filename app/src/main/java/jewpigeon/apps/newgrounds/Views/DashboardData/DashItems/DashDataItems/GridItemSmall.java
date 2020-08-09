package jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems;

import android.graphics.drawable.Drawable;


public class GridItemSmall {

    private Drawable image;
    private String author;

    public GridItemSmall(Drawable image, String author) {
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
