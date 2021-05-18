package lt.vu.rest;

import lt.vu.entities.Pc;
import lt.vu.entities.Repair;

import java.util.stream.Collectors;
public class Mapper {
    public static PcDto convertToGunDto(Pc pc) {
        if (pc != null) {
            PcDto pcDto = new PcDto();
            pcDto.setPcName(pc.getPcName());
            pcDto.setBrand(pc.getBrand());
            pcDto.setRepairs(
                    pc.getRepair().stream()
                            .map(Mapper::convertToRepairDto)
                            .collect(Collectors.toList())
            );
            return pcDto;
        }
        return null;
    }

    public static RepairsDto convertToRepairDto(Repair repair) {
        if (repair != null) {
            RepairsDto repairDto = new RepairsDto();
            repairDto.setCost(repair.getCost());
            repairDto.setDesciption(repair.getDescription());
            return repairDto;
        }
        return null;
    }
}
