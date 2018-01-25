package Flow;

import java.util.List;

/**
 * Created by tech on 2017/12/25.
 */
public interface FlowDateInterface {

    FullFlow findFullFlow(String flowId);
    List<FlowTemplates> findFlowTemplates(String flowClazzID);
    int insertFullFlow(FullFlow fullFlow);
    int updateFullFlow(FullFlow fullFlow);
    List<FlowDetail> creatDetail(MainFlow flow);
    FullFlow findFullFlowByBid(String bid);

}
