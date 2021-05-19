package lt.vu.persistence;

import lt.vu.entities.Pc;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class PCDAO implements IPCDAO {
    public void setEm(EntityManager em) {
        this.em = em;
    }
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
        try {
            pc = em.merge(pc);
            Thread.sleep(2000);
            em.flush();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pc;
    }
}
