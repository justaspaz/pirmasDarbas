package lt.vu.usecases;
import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.model.Pc;
import lt.vu.mybatis.dao.PcMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PcsMyBatis {
    @Inject
    private PcMapper pcMapper;

    @Getter
    private List<Pc> allPCs;

    @Getter @Setter
    private Pc pcToCreate = new Pc();

    @PostConstruct
    public void init() {
        this.loadAllPC();
    }

    private void loadAllPC() {
        this.allPCs = pcMapper.selectAll();
    }

    @Transactional
    public String createPC() {
        pcMapper.insert(pcToCreate);
        return "/myBatis/index?faces-redirect=true";
    }
}