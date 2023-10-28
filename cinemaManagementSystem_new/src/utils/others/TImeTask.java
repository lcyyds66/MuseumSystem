package utils.others;

import java.util.Date;
import java.util.TimerTask;

public class TImeTask extends TimerTask {
    @Override
    public void run() {
        Date date = new Date();
        System.out.println(date.getTime());
    }
}
