package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.security.SecureRandom;
@ApplicationScoped
@Alternative
public class PCLowGenarator implements IPcNumberGebarator{
    private SecureRandom secureRandom = new SecureRandom();
    @Override
    public Integer genearteReapirNumber() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        Integer generatedNumber = 10 + secureRandom.nextInt(100);
        return generatedNumber;
    }
}
