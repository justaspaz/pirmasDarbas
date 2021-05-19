package lt.vu.usecases;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Pc;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PCDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@LoggedInvocation
@Model
public class UpdatePcDetails implements Serializable, IUpdatePcDetails  {

    @Getter
    @Setter
    private Pc pc;

    @Inject
    private PCDAO pcDAO;
    @PostConstruct
    private void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int pcId = Integer.parseInt(requestParameters.get("pcId"));
        this.pc = pcDAO.findOne(pcId);
    }
    @Override
    @Transactional
    @LoggedInvocation
    public String updatePcName() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        try {
            pcDAO.update(this.pc);
        } catch (OptimisticLockException e) {
            System.out.println("------------------------------------------------");
            return "pc?faces-redirect=true&pcId=" + this.pc.getId() + "&error=optimistic-lock-exception";
        }
        return "pc?faces-redirect=true&pcId=" + this.pc.getId();
    }
        @Transactional
        public String updatePcNumber() {
            pcDAO.update(this.pc);
            return "pc?faces-redirect=true&pcId=" + this.pc.getId();
        }
}
