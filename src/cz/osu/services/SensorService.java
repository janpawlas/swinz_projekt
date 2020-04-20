package cz.osu.services;

import cz.osu.db.SensorRepository;
import cz.osu.db.entity.DataEntity;
import cz.osu.db.entity.SensorEntity;

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
        int days = 0;
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        List<SensorEntity> sensors = getHeatSensors();
        //List<DataEntity> dataEntities = new ArrayList<>();
        for (SensorEntity sensor : sensors) {
            //dataEntities.addAll(dataService.getListBySensorId(sensor.getId()));

        }

        return 0;
    }

    public List<SensorEntity> getHeatSensors(){

        return null;
    }
}
