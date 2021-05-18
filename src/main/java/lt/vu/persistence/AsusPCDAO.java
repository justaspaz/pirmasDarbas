package lt.vu.persistence;

import lt.vu.entities.Pc;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
@Alternative
public class AsusPCDAO extends PCDAO implements IPCDAO{
    @Inject
    EntityManager em;

    @Override
    public List<Pc> loadAll() {
        return em
                .createQuery("select pc from Pc pc where pc.brand='asus'", Pc.class)
                .getResultList();
    }

}
