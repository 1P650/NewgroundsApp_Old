package jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems;

import android.graphics.drawable.Drawable;

public class AudioItem {
    private Drawable AudioIcon;
    private String Title;
    private String Author;


    private String Type;

    public AudioItem(Drawable audioIcon, String title, String author, String type) {
        AudioIcon = audioIcon;
        Title = title;
        Author = "by " + author;
        Type = type;
    }

    public Drawable getAudioIcon() {
        return AudioIcon;
    }

    public void setAudioIcon(Drawable audioIcon) {
        AudioIcon = audioIcon;
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


    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
