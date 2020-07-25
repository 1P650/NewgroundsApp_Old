package jewpigeon.apps.newgrounds.Fundamental;

import org.greenrobot.eventbus.EventBus;

public class NG_Bus {
    private static EventBus NG_EventBus;

    public static EventBus get() {
        if (NG_EventBus == null) NG_EventBus = EventBus.getDefault();
        return NG_EventBus;
    }
}
