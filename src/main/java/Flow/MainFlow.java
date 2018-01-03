package Flow;

import java.util.Date;

/**
 * Created by tech on 2017/12/25.
 */
public interface MainFlow {

    String getFlowClazzId();
    String getFlowId();
    String getBid();
    String getStarter();
    Date getStartDate();
    int getFlowStepNumber();
    String getHanding();
    FlowState getState();
    void setHanding(String handing);
    void setFlowStepNumber(int stepNumber);
    void setState(FlowState state);


}
