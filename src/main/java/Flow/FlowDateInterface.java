package Flow;

import java.util.List;

/**
 * Created by tech on 2017/12/25.
 */
public interface FlowDateInterface {

    FullFlow findFullFlow(String flowId);
    List<? extends FlowTemplates> findFlowTemplates(String flowClazzID);
    int insertFullFlow(FullFlow fullFlow);
    int updateFullFlow(FullFlow fullFlow);
    List<? extends FlowDetail> creatDetail(MainFlow flow);

}
