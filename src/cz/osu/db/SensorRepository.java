package cz.osu.db;

import cz.osu.db.entity.SensorEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class SensorRepository extends Repository<SensorEntity> {
    public SensorEntity getByID(int id){
        SensorEntity ret = getEm().find(SensorEntity.class, id);
        return ret;
    }
    public List<SensorEntity> getList(){
        TypedQuery<SensorEntity> q = getEm().createQuery("select p from SensorEntity p", SensorEntity.class);
        List<SensorEntity> ret = q.getResultList();
        return ret;
    }
}
