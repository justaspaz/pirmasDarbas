package lt.vu.services;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.security.SecureRandom;
@ApplicationScoped
public class PCRepairNumberGenerator {
    private SecureRandom secureRandom = new SecureRandom();

    public Integer genearteReapirNumber() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        Integer generatedNumber = 10000 + secureRandom.nextInt(20000);
        return generatedNumber;
    }
}
