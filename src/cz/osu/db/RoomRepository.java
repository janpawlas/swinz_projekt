package cz.osu.db;

import cz.osu.db.entity.RoomEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class RoomRepository extends Repository<RoomEntity> {

    public RoomEntity getByID(int id){
        RoomEntity ret = getEm().find(RoomEntity.class, id);
        return ret;
    }
    public List<RoomEntity> getList(){
        TypedQuery<RoomEntity> q = getEm().createQuery("select p from RoomEntity p", RoomEntity.class);
        List<RoomEntity> ret = q.getResultList();
        return ret;
    }
}
