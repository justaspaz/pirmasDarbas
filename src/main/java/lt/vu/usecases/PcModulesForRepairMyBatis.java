package lt.vu.usecases;
import lombok.Getter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.mybatis.dao.PcmodulesMapper;
import lt.vu.mybatis.dao.RepairMapper;
import lt.vu.mybatis.model.Pcmodules;
import lt.vu.mybatis.model.Repair;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Model
public class PcModulesForRepairMyBatis {
    @Inject
    private RepairMapper repairMapper;

    @Inject
    private PcmodulesMapper pcModulesMapper;

    @Getter
    private Repair repair;

    @Getter
    private List<Pcmodules> module = new ArrayList<>();

    @Getter
    private List<Pcmodules> modulesRepair;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int repairId = Integer.parseInt(requestParameters.get("repairId"));
        this.repair = repairMapper.selectByPrimaryKey(repairId);
        this.modulesRepair = pcModulesMapper.selectByRepairId(repairId);
        List<Pcmodules> allModules = pcModulesMapper.selectAll();
        for (Pcmodules m: allModules) {
            if(!modulesRepair.contains(m)){
                this.module.add(m);
            }
        }
    }

    @Transactional
    @LoggedInvocation
    public String assignModuleToRepair(int moduleId) {
        pcModulesMapper.insertPartToJob(moduleId, this.repair.getId());
        return "repair?faces-redirect=true&repairId=" + this.repair.getId();
    }
}
