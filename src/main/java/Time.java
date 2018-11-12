import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time {
    private int timeMilliseconds = 0;

    public Time(int timeMilliseconds) {
        this.timeMilliseconds = timeMilliseconds;
    }

    public String getTime() {
        Calendar t = Calendar.getInstance();
        t.setTime(new Date(timeMilliseconds));
        t.set(Calendar.HOUR_OF_DAY, t.get(Calendar.HOUR_OF_DAY)-17);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        return dateFormat.format(t.getTime());
    }
}
