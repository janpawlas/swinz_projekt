package cz.osu.services;

import cz.osu.db.DataRepository;
import cz.osu.db.entity.DataEntity;

import java.util.List;

public class DataService {
    DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {
        this.dataRepository = new DataRepository();
    }
    public List<DataEntity> getListBySensorId(int sensorId){
        return dataRepository.getListBySensorId(sensorId);
    }
}
