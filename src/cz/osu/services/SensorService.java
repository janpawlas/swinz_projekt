package cz.osu.services;

import cz.osu.db.SensorRepository;
import cz.osu.db.entity.SensorEntity;

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
}
