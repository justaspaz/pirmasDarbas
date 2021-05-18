package lt.vu.usecases;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Pc;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.IPCDAO;
import lt.vu.persistence.PCDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
@LoggedInvocation
@Model
public class Pcs {

    @Inject
    private IPCDAO pcDAO;

    @Getter
    @Setter
    private Pc pcToCreate = new Pc();

    @Getter
    private List<Pc> allPC;

    @PostConstruct
    public void init(){
        loadAllPCs();
    }

    @Transactional
    public String createPC(){
        this.pcDAO.persist(pcToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllPCs(){
        this.allPC = pcDAO.loadAll();
    }
}
