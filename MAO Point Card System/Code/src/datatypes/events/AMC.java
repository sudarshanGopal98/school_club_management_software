package datatypes.events;

import java.util.Date;

/**
 * Created by Sudarshan on 5/20/2016.
 */
public class AMC extends MAOEvent {
    private int eventPoints;

    public AMC(Date eventDate, String eventName) {
        super(eventDate, eventName);
        eventPoints = EventPoints.amc;
    }
}
