package lt.vu.usecases;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Pc;
import lt.vu.entities.Repair;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PCDAO;
import lt.vu.persistence.RepairDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@LoggedInvocation
@Model
public class RepairsForPC implements Serializable {
    @Inject
    private PCDAO pcDAO;

    @Inject
    private RepairDAO reapirDAO;

    @Getter
    @Setter
    private Pc pc;

    @Getter @Setter
    private Repair repairToCreate = new Repair();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int pcId = Integer.parseInt(requestParameters.get("pcId"));
        this.pc = pcDAO.findOne(pcId);
    }

    @Transactional
    @LoggedInvocation
    public String createRepair() {
        repairToCreate.setPc(this.pc);
        reapirDAO.persist(repairToCreate);
        return "pc?faces-redirect=true&pcId=" + this.pc.getId();
    }
    @Transactional
    @LoggedInvocation
    public String updatePcName() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        try {
            pcDAO.update(this.pc);
        } catch (OptimisticLockException e) {
            return "pc?faces-redirect=true&pcId=" + this.pc.getId() + "&error=optimistic-lock-exception";
        }
        return "pc?faces-redirect=true&pcId=" + this.pc.getId();
    }
}
