package datatypes.events;

import java.util.Date;

/**
 * Created by Sudarshan on 5/20/2016.
 */
public class MathContest extends MAOEvent {
    private int eventPoints;

    public MathContest(Date eventDate, String eventName) {
        super(eventDate, eventName);
        eventPoints = EventPoints.mathContest;
    }
}
