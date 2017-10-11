package datatypes.events;

import java.util.Date;

/**
 * Created by Sudarshan on 5/20/2016.
 */
public class Social extends MAOEvent{
    private int eventPoints;

    public Social(Date eventDate, String eventName) {
        super(eventDate, eventName);
        eventPoints = EventPoints.social;
    }
}
