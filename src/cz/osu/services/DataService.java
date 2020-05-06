package cz.osu.services;

import cz.osu.db.DataRepository;
import cz.osu.db.RoomRepository;
import cz.osu.db.entity.DataEntity;
import cz.osu.db.entity.SensorEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        return ret; //udělat průměr
    }

    public double lightsOnForLastWeek(int sensorId){
        List<DataEntity> data = getListBySensorId(sensorId);
        double ret = 0;

        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        Date start = c.getTime();
        c.add(Calendar.DATE, 6);
        Date end = c.getTime();
        System.out.println(start + " - " + end);

        Calendar cal = Calendar.getInstance();
        for (DataEntity entity : data) {
            cal.setTimeInMillis(entity.getTime().getTime());
            Date entityTime = cal.getTime();
            if (entityTime.after(start) && entityTime.before(end)){
                ret += 5;
            }
        }
        return ret;
    }

    public boolean isHeatOnAt(int sensorId, Timestamp timestamp){
        List<DataEntity> data = getListBySensorId(sensorId);
        boolean ret = false;
        double firstValue = 0;
        Calendar cal = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        for (DataEntity entity : data) {
            cal.setTimeInMillis(entity.getTime().getTime());
            if (cal.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && cal.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) && cal.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)){
                if(firstValue == 0) firstValue = entity.getValue();
                if (entity.getValue() > firstValue){
                    ret = true;
                }
            }
        }
        return ret;
    }
}
