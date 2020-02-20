package cz.osu.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class Repository<T> {
    private static final String PU_NAME = "NewPersistenceUnit";
    private static EntityManagerFactory emf = null;
    private EntityManager em = null;

    protected EntityManager getEm(){
        if(emf ==null){
            try{
                emf = javax.persistence.Persistence.createEntityManagerFactory(PU_NAME);
            }catch (Exception ex){
                throw new cz.osu.db.RepositoryException("Failed to create entity-manager-factory.", ex);
            }
        }
        if(em == null){
            try{
                em = emf.createEntityManager();
            }catch (Exception ex){
                throw new RepositoryException("Failed to create entity-manager.", ex);
            }
        }
        return this.em;
    }

    public void create(T entity){
        try {
            getEm().getTransaction().begin();
            getEm().persist(entity);
            getEm().getTransaction().commit();
        }catch (Exception ex){
            getEm().getTransaction().rollback();
            throw new RepositoryException("Failed to create entity.", ex);
        }
    }

}
