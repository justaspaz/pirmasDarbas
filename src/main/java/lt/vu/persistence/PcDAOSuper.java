package lt.vu.persistence;

import lt.vu.entities.Pc;

class PcDAOSpecial extends PCDAO{
    @Override
    public Pc update(Pc pc) {
        System.out.println("[Special]");
        pc.setPcName(pc.getPcName() + "special");
        return super.update(pc);
    }
}
