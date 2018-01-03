package Flow;

import java.util.Date;
import java.util.List;

/**
 * Created by tech on 2017/12/25.
 * 流程驱动器抽象方法
 */
public abstract class AbstractFlowProcessor implements FlowProcessor {

    private  FullFlow fullFlow;

    public AbstractFlowProcessor(){}





    public AbstractFlowProcessor(FullFlow fullFlow){
        this.fullFlow = fullFlow;
    }




    private boolean isPassed(){
        return this.fullFlow.getFlow().getState() == FlowState.PASS;
    }



    public FullFlow agree(String result) {

        if(isPassed()){
            return fullFlow;
        }
        FlowDetail handlingStep = fullFlow.getHandlingStep();
        handlingStep.setHandDate(new Date());
        handlingStep.setHandResult(result);
        if(fullFlow.getNextStep()==null){
            fullFlow.getFlow().setState(FlowState.PASS);
            return fullFlow;
        }
        FlowDetail netxStep = fullFlow.getNextStep();
        fullFlow.getFlow().setFlowStepNumber(netxStep.getFlowStepNumber());
        fullFlow.getFlow().setHanding(netxStep.getHandller());
        return fullFlow;
    }

    public FullFlow repules(String result) {

        if(isPassed()){
            return fullFlow;
        }
        FlowDetail handlingSetp = fullFlow.getHandlingStep();
        handlingSetp.setHandDate(new Date());
        handlingSetp.setHandResult(result);
        fullFlow.getFlow().setState(FlowState.REPULED);
        return fullFlow;
    }

    public FullFlow retreat(String result) {

        if(isPassed()){
            return fullFlow;
        }
        FlowDetail handlingSetp = fullFlow.getHandlingStep();
        handlingSetp.setHandDate(new Date());
        handlingSetp.setHandResult(result);
        fullFlow.getFlow().setState(FlowState.RETREATED);
        FlowDetail lastSetp = fullFlow.getLastSetp();
        fullFlow.getFlow().setFlowStepNumber(lastSetp.getFlowStepNumber());
        fullFlow.getFlow().setHanding(lastSetp.getHandller());
        return fullFlow;
    }






}
