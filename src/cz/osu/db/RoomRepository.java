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
    public void update (RoomEntity entity){
        try {
            getEm().getTransaction().begin();
            getEm().merge(entity);
            getEm().getTransaction().commit();
        }catch (Exception ex){
            getEm().getTransaction().rollback();
            throw new RepositoryException("Failed to update entity id=" + entity.getId(), ex);
        }
    }
}
