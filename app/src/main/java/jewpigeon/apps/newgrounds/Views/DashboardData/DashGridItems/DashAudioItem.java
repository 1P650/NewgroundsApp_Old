package jewpigeon.apps.newgrounds.Views.DashboardData.DashGridItems;

import android.graphics.drawable.Drawable;

public class DashAudioItem {
    private Drawable AudioIcon;
    private String Title;
    private String Author;

    public DashAudioItem(Drawable audioIcon, String title, String author) {
        AudioIcon = audioIcon;
        Title = title;
        Author = author;
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


}
