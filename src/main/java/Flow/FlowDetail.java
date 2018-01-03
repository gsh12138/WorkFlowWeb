package Flow;

import java.util.Date;
import java.util.List;

/**
 * Created by tech on 2017/12/25.
 */
public interface FlowDetail {

    String getFlowId();
    int getFlowStepNumber();
    String getHandller();
    Date getHandDate();
    String getHandResult();
    void setHandResult(String result);
    void setHandDate(Date handDate);


}
