package lt.vu.usecases;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.PcMapper;
import lt.vu.mybatis.dao.RepairMapper;
import lt.vu.mybatis.model.Pc;
import lt.vu.mybatis.model.Repair;

@Model
public class RepairForPCMyBatis {
    @Inject
    private PcMapper pcMapper;

    @Inject
    private RepairMapper repairMapper;

    @Getter
    private Pc pc;

    @Getter
    private List<Repair> repair;

    @Getter @Setter
    private Repair repairToCreate = new Repair();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int pcId = Integer.parseInt(requestParameters.get("pcId"));
        this.pc = pcMapper.selectByPrimaryKey(pcId);
        this.repair = repairMapper.selectByPcId(pcId);
    }

    @Transactional
    @LoggedInvocation
    public String createRepair() {
        repairToCreate.setPcId(this.pc.getId());
        repairMapper.insert(repairToCreate);
        return "pc?faces-redirect=true&pcId=" + this.pc.getId();
    }
}
