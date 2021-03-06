package lt.vu.services;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.io.Serializable;
import java.security.SecureRandom;
@ApplicationScoped
@Default
public class PCRepairNumberGenerator implements IPcNumberGebarator{
    private SecureRandom secureRandom = new SecureRandom();
    @Override
    public Integer genearteReapirNumber() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        Integer generatedNumber = 10000 + secureRandom.nextInt(20000);
        return generatedNumber;
    }
}
