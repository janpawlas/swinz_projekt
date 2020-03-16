import cz.osu.services.DataService;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;

public class DataParser {
    private String sensors_url = "http://localhost:5000/api/sensors/";
    DataService dataService;

    public DataParser() {
        this.dataService = new DataService();
    }

    public void pushOverallStat() throws IOException {
        JSONArray json = new JSONArray(IOUtils.toString(new URL(sensors_url), StandardCharsets.UTF_8));
        for(Object o: json){
            if (o instanceof JSONObject) {
                parseThenPush((JSONObject) o);
            }
        }
    }

    public void parseThenPush(JSONObject o) {
        Integer sensor = Integer.parseInt(o.getString("id"));
        Timestamp time = new Timestamp(new Date().getTime());
        Double value = Double.parseDouble(o.getString("data"));
        dataService.create(sensor, time, value);
    }
}
