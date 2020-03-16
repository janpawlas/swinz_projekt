import java.io.IOException;
import java.util.TimerTask;

public class DBRecorder extends TimerTask {
    DataParser dataParser;

    public DBRecorder() {
        dataParser = new DataParser();
    }

    @Override
    public void run() {
        try {
            dataParser.pushOverallStat();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



/*
Timer timer = new Timer();
timer.schedule(new DBRecorder(), 0, 5000);
 */