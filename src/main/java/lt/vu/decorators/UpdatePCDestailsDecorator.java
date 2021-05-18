package lt.vu.decorators;

import lt.vu.usecases.IUpdatePcDetails;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;


@Decorator
public class UpdatePCDestailsDecorator implements IUpdatePcDetails {
    @Inject
    @Delegate
    @Any
    IUpdatePcDetails updatePcDetails;


    @Override
    public String updatePcName() throws InterruptedException {
        String result = updatePcDetails.updatePcName();
        return result + "&decorated=yes";
    }
}
