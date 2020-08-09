package jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import java.util.Date;

public class ForumItem {

    public ForumItem(Drawable status, Drawable mood, String topic, String topicStarter, Date topicStartDate, int replies) {
        this.status = status;
        this.mood = mood;
        this.topic = topic;
        this.topicStarter = topicStarter;
        this.topicStartDate = topicStartDate;
        this.replies = replies;
    }


    private Drawable status;
    private Drawable mood;
    private String topic;

    private String topicStarter;
    private Date topicStartDate;
    private int replies;


    public Drawable getStatus() {
        return status;
    }

    public void setStatus(Drawable status) {
        this.status = status;
    }

    public Drawable getMood() {
        return mood;
    }

    public void setMood(Drawable mood) {
        this.mood = mood;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopicStarter() {
        return topicStarter;
    }

    public void setTopicStarter(String topicStarter) {
        this.topicStarter = topicStarter;
    }

    public Date getTopicStartDate() {
        return topicStartDate;
    }

    public void setTopicStartDate(Date topicStartDate) {
        this.topicStartDate = topicStartDate;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }




}
