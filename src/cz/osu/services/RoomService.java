package cz.osu.services;

import cz.osu.db.RoomRepository;
import cz.osu.db.entity.RoomEntity;
import cz.osu.db.entity.SensorEntity;

import java.util.List;

public class RoomService {
    private RoomRepository roomRepository;

    public RoomService() {
        roomRepository = new RoomRepository();
    }
    public RoomEntity create(String name){
        RoomEntity entity = new RoomEntity();
        entity.setName(name);
        roomRepository.create(entity);
        return entity;
    }
    public List<RoomEntity> getList(){
        return roomRepository.getList();
    }
    public RoomEntity getById(int id){
        return roomRepository.getByID(id);
    }
    public RoomEntity updateTemp(int roomId, double temp){
        RoomEntity entity = roomRepository.getByID(roomId);
        entity.setTemp(temp);
        roomRepository.update(entity);
        return entity;
    }
    public double getLightsOnForLastWeekPerRoom(int roomId){
        double ret = 0.0;
        DataService dataService = new DataService();
        GraphService graphService = new GraphService();
        List<SensorEntity> sensors = graphService.getLightSensorsForRoom(roomId);
        for (SensorEntity sensor : sensors) {
            ret += dataService.lightsOnForLastWeek(sensor.getId());
        }
        return Math.round(ret / 7.0);
    }
}
