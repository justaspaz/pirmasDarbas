package lt.vu.usecases;
import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.PcmodulesMapper;
import lt.vu.mybatis.model.Pcmodules;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PcmodulesMyBatis {
    @Inject
    private PcmodulesMapper pcmodulesMapper;

    @Getter
    private List<Pcmodules> allModules;

    @Getter
    @Setter
    private Pcmodules moduleToCreate = new Pcmodules();

    @PostConstruct
    public void init() {
        this.loadAllModules();
    }

    private void loadAllModules() {
        this.allModules = pcmodulesMapper.selectAll();
    }

    @Transactional
    public String createModules() {
        pcmodulesMapper.insert(moduleToCreate);
        return "/myBatis/index?faces-redirect=true";
    }
}
