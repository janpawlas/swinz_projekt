package cz.osu.services;

import cz.osu.db.DataRepository;
import cz.osu.db.entity.DataEntity;

import java.sql.Timestamp;
import java.util.List;

public class DataService {
    public DataRepository dataRepository;

    public DataService() {
        dataRepository = new DataRepository();
    }

    public DataEntity create(Integer sensor, Timestamp time, Double value){
        DataEntity entity = new DataEntity();
        entity.setSensor(sensor);
        entity.setTime(time);
        entity.setValue(value);
        dataRepository.create(entity);
        return entity;
    }
    public List<DataEntity> getListBySensorId(int sensorId){
        return dataRepository.getListBySensorId(sensorId);
    }
}
