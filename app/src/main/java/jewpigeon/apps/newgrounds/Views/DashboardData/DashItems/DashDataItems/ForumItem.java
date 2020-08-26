package jewpigeon.apps.newgrounds.Views.DashboardData.DashItems.DashDataItems;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import java.util.Date;

public class ForumItem {

    public ForumItem(Drawable mood, String topic, String topicStarter, Date topicStartDate) {
        this.mood = mood;
        this.topic = topic;
        this.topicStarter = topicStarter;
        this.topicStartDate = topicStartDate;
    }


    private Drawable mood;
    private String topic;

    private String topicStarter;
    private Date topicStartDate;


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




}
