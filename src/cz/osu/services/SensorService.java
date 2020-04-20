package cz.osu.services;

import cz.osu.db.SensorRepository;
import cz.osu.db.entity.DataEntity;
import cz.osu.db.entity.SensorEntity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SensorService {
    private SensorRepository sensorRepository;

    public SensorService() {
        sensorRepository = new SensorRepository();
    }
    public SensorEntity create(String type, int roomId){
        SensorEntity entity = new SensorEntity();
        entity.setRoom(roomId);
        entity.setType(type);
        sensorRepository.create(entity);
        return entity;
    }
    public List<SensorEntity> getList(){
        return sensorRepository.getList();
    }
    public SensorEntity getById(int id){
        return sensorRepository.getByID(id);
    }

    public int getDaysWithHeatOnThisYear(){
        DataService dataService = new DataService();
        int daysWithHeatOn = 0;
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        List<SensorEntity> sensors = getHeatSensors();
        List<DataEntity> dataEntities = new ArrayList<>();
        for (SensorEntity sensor : sensors) {
            dataEntities.addAll(dataService.getListBySensorId(sensor.getId()));
        }
        List<Timestamp> dates = new ArrayList<>();
        dates = getDates(dataEntities);
        for (Timestamp timestamp : dates) {
            for (SensorEntity sensor : sensors) {
                if (dataService.isHeatOnAt(sensor.getId(), timestamp)){
                    daysWithHeatOn += 1;
                    break;
                }
            }

        }
        return daysWithHeatOn;
    }

    private List<Timestamp> getDates(List<DataEntity> dataEntities) {
        List<Timestamp> dates = new ArrayList<>();
        for (DataEntity entity : dataEntities) {
            boolean pom = false;
            for (Timestamp date : dates) {
                if (date.getMonth() == entity.getTime().getMonth() && date.getYear() == entity.getTime().getYear() && date.getDay() == entity.getTime().getDay()){
                    pom = true;
                    break;
                }
            }
            if (!pom){
                dates.add(entity.getTime());
            }
        }
        return dates;
    }


    private List<SensorEntity> getHeatSensors(){
        List<SensorEntity> allSensors = getList();
        List<SensorEntity> heatSensors = new ArrayList<>();
        for (SensorEntity sensor : allSensors) {
            if (sensor.getType().equals("Temperature Sensor"))
                heatSensors.add(sensor);
        }
        return heatSensors;
    }
}
