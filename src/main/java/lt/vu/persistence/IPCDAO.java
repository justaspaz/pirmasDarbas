package lt.vu.persistence;

import lt.vu.entities.Pc;

import java.util.List;

public interface IPCDAO {
    List<Pc> loadAll();

    Pc findOne(Integer pcId);

    void persist(Pc pcToCreate);

    Pc update(Pc pc);
}
