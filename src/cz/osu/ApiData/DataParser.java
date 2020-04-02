package cz.osu.ApiData;

import cz.osu.services.DataService;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataParser {
    private String sensors_url = "http://localhost:5000/api/sensors/";
    DataService dataService = new DataService();

    public DataParser() {}

    public void pushOverallStat() throws IOException {
        JSONArray json = new JSONArray(IOUtils.toString(new URL(sensors_url), StandardCharsets.UTF_8));
        for(Object o: json){
            if (o instanceof JSONObject) {
                parseThenPush((JSONObject) o);
            }
        }
    }

    public void parseThenPush(JSONObject o) {
        int sensor = o.getInt("id");
        Timestamp time = new Timestamp(new Date().getTime());
        double value = o.getDouble("data");
        dataService.create(sensor, time, value);
    }

    public ArrayList<ParsedData> getAllData() throws IOException {
        ArrayList<ParsedData> list = new ArrayList<>();
        JSONArray json = new JSONArray(IOUtils.toString(new URL(sensors_url), StandardCharsets.UTF_8));
        for(Object o: json){
            if (o instanceof JSONObject) {
                list.add(new ParsedData(
                        ((JSONObject) o).getInt("id"),
                        ((JSONObject) o).getString("name"),
                        ((JSONObject) o).getInt("room"),
                        ((JSONObject) o).getDouble("data")));
            }
        }
        return list;
    }

    public ParsedData getTempByRoom(int roomId){
        try {
            List<ParsedData> list = getAllData();
            for (ParsedData data : list) {
                if (data.getRoomId() == roomId && data.getName().equals("Temperature Sensor")){
                    return data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ParsedData getPowerByRoom(int roomId){
        try {
            List<ParsedData> list = getAllData();
            for (ParsedData data : list) {
                if (data.getRoomId() == roomId && data.getName().equals("Electricity Sensor")){
                    return data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getHeat(double roomTemp, double currentTemp){
        if (currentTemp >= roomTemp)
            return "OFF";
        else
            return "ON";
    }
}

