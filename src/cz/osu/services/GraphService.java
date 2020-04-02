package cz.osu.services;

import cz.osu.db.DataRepository;
import cz.osu.db.SensorRepository;
import cz.osu.db.entity.SensorEntity;

import java.awt.*;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.List;

public class GraphService {
    private DataService dataService;
    private SensorService sensorService;
    private int year;
    public GraphService() {
        this.dataService = new DataService();
        this.sensorService = new SensorService();
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.year = localDate.getYear();
    }
    public Map<String, Double> getPowerConsumption(){
        Map<String, Double> ret = new HashMap<>();
        Map<Integer, String> months = setMonths();
        List<SensorEntity> allSensors = sensorService.getList();
        List<SensorEntity> elSensors = new ArrayList<>();
        for (SensorEntity sensor : allSensors) {
            if (sensor.getType().equals("Electricity Sensor"))
                elSensors.add(sensor);
        }
        for (Map.Entry<Integer, String> entry : months.entrySet()) {
            double power = 0.0;
            for (SensorEntity sensor : elSensors) {
                power += dataService.getPowerConsumptionPerMonth(sensor.getId(), entry.getKey(), this.year);
            }
            ret.put(entry.getValue(), power);
        }
        return ret;
    }

    private Map<Integer, String> setMonths() {
        Map<Integer, String> months = new HashMap<>();
        DateFormatSymbols dfs = DateFormatSymbols.getInstance(Locale.ENGLISH);
        String[] monthStrings = dfs.getMonths();
        for (int i = 0; i < 12 ; i++) {
            months.put(i, monthStrings[i]);
        }
        return months;
    }
}
