package lt.vu.persistence;

import lt.vu.entities.Repair;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class RepairDAO {

    @Inject
    private EntityManager em;

    public void persist(Repair repair){
        this.em.persist(repair);
    }

    public Repair findOne(Integer id){
        return em.find(Repair.class, id);
    }

    public Repair update(Repair repair){
        return em.merge(repair);
    }

}