package jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems;

import android.graphics.drawable.Drawable;

public class DashArtItem {
    private Drawable ArtIcon;
    private String Title;
    private String Author;

    public DashArtItem(Drawable artIcon, String title, String author) {
        ArtIcon = artIcon;
        Title = title;
        Author = "by " + author;
    }

    public Drawable getArtIcon() {
        return ArtIcon;
    }

    public void setArtIcon(Drawable artIcon) {
        ArtIcon = artIcon;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }
}
