package cz.osu.services;

import cz.osu.db.RoomRepository;
import cz.osu.db.entity.RoomEntity;

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
}
