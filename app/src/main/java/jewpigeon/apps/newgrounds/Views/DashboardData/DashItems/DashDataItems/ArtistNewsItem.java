package jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems;

import android.graphics.drawable.Drawable;

public class ArtistNewsItem {

    private Drawable UserIcon;
    private String Username;
    private String Title;

    public ArtistNewsItem(Drawable userIcon, String username, String title) {
        UserIcon = userIcon;
        Username = username;
        Title = title;
    }

    public Drawable getUserIcon() {
        return UserIcon;
    }

    public void setUserIcon(Drawable userIcon) {
        UserIcon = userIcon;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }



}
