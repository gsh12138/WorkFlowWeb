package Flow;

import java.util.List;

/**
 * Created by tech on 2017/12/25.
 */
public interface FullFlow {
    MainFlow getFlow();
    List<? extends FlowDetail> getFlowDetail();
    FlowDetail getHandlingStep();
    FlowDetail getNextStep();
    FlowDetail getLastSetp();

}
