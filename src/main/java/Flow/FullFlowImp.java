package Flow;

import java.util.List;

/**
 * Created by tech on 2017/12/25.
 */
public class FullFlowImp implements FullFlow {

    private MainFlow flow;
    private List<? extends FlowDetail> flowDetail;
    private FlowDetail handlingStep;
    private FlowDetail nextStep;
    private FlowDetail lastSetp;

    public FullFlowImp(MainFlow flow, List<? extends FlowDetail> flowDetail) {
        this.flow = flow;
        this.flowDetail = flowDetail;
        this.nextStep=findNextStep();
        this.lastSetp=findLastStep();
        this.handlingStep=findHandlingStep();
    }

    public MainFlow getFlow() {
        return flow;
    }

    public List<? extends FlowDetail> getFlowDetail() {
        return flowDetail;
    }

    public FlowDetail getHandlingStep() {
        return handlingStep;
    }

    public FlowDetail getNextStep() {
        return nextStep;
    }

    public FlowDetail getLastSetp() {
        return lastSetp;
    }


    private FlowDetail findNextStep(){
        for (FlowDetail detail:flowDetail
             ) {
            if(detail.getFlowStepNumber()-flow.getFlowStepNumber()==1){
                return detail;
            }

        }
        return null;
    }

    private FlowDetail findLastStep(){
        for (FlowDetail detail:flowDetail
                ) {
            if(flow.getFlowStepNumber()-detail.getFlowStepNumber()==1){
                return detail;
            }

        }
        return null;
    }

    private FlowDetail findHandlingStep(){
        for (FlowDetail detail:flowDetail
                ) {
            if(flow.getFlowStepNumber()==detail.getFlowStepNumber()){
                return detail;
            }

        }
        return null;
    }
}
