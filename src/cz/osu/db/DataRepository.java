package cz.osu.db;

import cz.osu.db.entity.DataEntity;

import javax.persistence.TypedQuery;
import java.util.List;

public class DataRepository extends Repository<DataEntity> {

    public List<DataEntity> getListBySensorId(int sensorId){
        TypedQuery<DataEntity> q = getEm().createQuery("select p from DataEntity p where p.sensor = "+ sensorId, DataEntity.class);
        List<DataEntity> ret = q.getResultList();
        return ret;
    }
}
