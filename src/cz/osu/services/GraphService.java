package cz.osu.services;

import cz.osu.db.DataRepository;
import cz.osu.db.SensorRepository;
import cz.osu.db.entity.SensorEntity;

import java.awt.*;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
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

    public List<Double> getPowerConsumptionPerYear(){
        List<SensorEntity> elSensors = getElSensors();
        List<Double> consumptionPerYear = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            double summary = 0;
            for (SensorEntity entity : elSensors) {
                summary += dataService.getPowerConsumptionPerMonth(entity.getId(), i, this.year);
            }
            consumptionPerYear.add(summary);
        }
        return consumptionPerYear;
    }


    private List<SensorEntity> getElSensors(){
        List<SensorEntity> allSensors = sensorService.getList();
        List<SensorEntity> elSensors = new ArrayList<>();
        for (SensorEntity sensor : allSensors) {
            if (sensor.getType().equals("Electricity Sensor"))
                elSensors.add(sensor);
        }
        return elSensors;
    }

    public List<SensorEntity> getLightSensorsForRoom(int roomId){
        List<SensorEntity> allSensors = sensorService.getList();
        List<SensorEntity> lightSensors = new ArrayList<>();
        for (SensorEntity sensor : allSensors) {
            if (sensor.getType().equals("Light Sensor") && sensor.getRoom() == roomId)
                lightSensors.add(sensor);
        }
        return lightSensors;
    }
    public List<Double> getLightsOnPerYearPerSensor(int roomId){
        List<SensorEntity> sensors = getLightSensorsForRoom(roomId);
        List<Double> lights = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            double value = 0.0;
            Month month = Month.of(i+1);
            double lengthOfMonth = month.length(false);
            for (SensorEntity sensor : sensors) {
                value += dataService.getLightsOnPerMonth(sensor.getId(), i, this.year);
            }
            lights.add(value / lengthOfMonth);
        }
        System.out.println(lights);
        return lights;
    }

}
