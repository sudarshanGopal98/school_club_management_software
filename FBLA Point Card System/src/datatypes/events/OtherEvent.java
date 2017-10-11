package datatypes.events;

import java.util.Date;

/**
 * Created by Sudarshan on 5/20/2016.
 */
public class OtherEvent extends MAOEvent {
    private int eventPoints;

    public OtherEvent(Date eventDate, String eventName, int eventPoints) {
        super(eventDate, eventName);
        eventPoints = eventPoints;
    }

    public int getEventPoints() {
        return eventPoints;
    }
}
