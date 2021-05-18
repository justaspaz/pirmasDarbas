package lt.vu.usecases;
import lt.vu.services.PCRepairNumberGenerator;
import lt.vu.interceptors.LoggedInvocation;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
@SessionScoped
@Named
public class GeneratePcRepairNumber implements Serializable{
    @Inject
    private PCRepairNumberGenerator pcNumberGenerator;

    private CompletableFuture<Integer> pcNumberGenerationTask = null;

    @LoggedInvocation
    public String generateNewPcNumber() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        pcNumberGenerationTask = CompletableFuture.supplyAsync(() -> pcNumberGenerator.genearteReapirNumber());

        return "pc?faces-redirect=true&pcId=" + requestParameters.get("pcId");
    }

    public boolean isPcNumberGenerationRunning() {
        return pcNumberGenerationTask != null && !pcNumberGenerationTask.isDone();
    }

    public String getPcGenerationStatus() throws ExecutionException, InterruptedException {
        if (pcNumberGenerationTask == null) {
            return null;
        } else if (isPcNumberGenerationRunning()) {
            return "Pc number generation in progress";
        }
        return "Suggested pc number: " + pcNumberGenerationTask.get();
    }
}
