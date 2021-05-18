package lt.vu.persistence;

import lt.vu.entities.Pc;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PCDAO implements IPCDAO {
    @Inject
    private EntityManager em;
    @Override
    public List<Pc> loadAll() {
        return em.createNamedQuery("PC.findAll", Pc.class).getResultList();
    }
    @Override
    public void persist(Pc pc){
        this.em.persist(pc);
    }
    @Override
    public Pc findOne(Integer id) {
        return em.find(Pc.class, id);
    }
    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Pc update(Pc pc) {
        pc = em.merge(pc);
        em.flush();
        return pc;
    }
}
