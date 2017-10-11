package datatypes.events;

import java.util.Date;

/**
 * Created by Sudarshan on 5/20/2016.
 */
public class Tutorial extends MAOEvent{
    private int eventPoints;

    public Tutorial(Date eventDate, String eventName) {
        super(eventDate, eventName);
        eventPoints = EventPoints.tutorial;
    }
}
