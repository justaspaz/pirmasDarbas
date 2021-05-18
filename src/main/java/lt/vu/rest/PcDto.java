package lt.vu.rest;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Repair;

import java.util.List;
@Getter
@Setter
public class PcDto {
    private String pcName;;
    private String brand;
    private List<RepairsDto> repairs;
}
