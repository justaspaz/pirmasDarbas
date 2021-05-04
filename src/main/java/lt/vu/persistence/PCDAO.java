package lt.vu.persistence;

import lt.vu.entities.Pc;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PCDAO {
    @Inject
    private EntityManager em;

    public List<Pc> loadAll() {
        return em.createNamedQuery("PC.findAll", Pc.class).getResultList();
    }
    public void persist(Pc pc){
        this.em.persist(pc);
    }

    public Pc findOne(Integer id) {
        return em.find(Pc.class, id);
    }
}
