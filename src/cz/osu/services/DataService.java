package cz.osu.services;

import cz.osu.db.DataRepository;
import cz.osu.db.entity.DataEntity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

public class DataService {
    private DataRepository dataRepository;

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

    public double getPowerConsumptionPerMonth(int sensorId, int month, int year){
        List<DataEntity> data = getListBySensorId(sensorId);
        double ret = 0.0;
        Calendar cal = Calendar.getInstance();
        for (DataEntity entity : data) {
            cal.setTimeInMillis(entity.getTime().getTime());
            if (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) == month){
                ret += entity.getValue();
            }
        }
        return ret;
    }
    public double getLightsOnPerMonth(int sensorId, int month, int year){
        List<DataEntity> data = getListBySensorId(sensorId);
        double ret = 0.0;
        Calendar cal = Calendar.getInstance();
        for (DataEntity entity : data) {
            cal.setTimeInMillis(entity.getTime().getTime());
            if (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) == month){
                if (entity.getValue() == 1){
                    ret += 5;
                }
            }
        }
        return ret;
    }
}
