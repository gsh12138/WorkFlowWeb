package helpers;

import Flow.*;
import entitys.MainFlowEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by tech on 2017/12/25.
 * 流程驱动器实现
 */
@Component
public class FullFlowProcessorImp extends AbstractFlowProcessor{


    private static FlowDateInterface flowDate;

    public FullFlowProcessorImp(){}

    public FullFlowProcessorImp(FullFlow fullFlow) {
        super(fullFlow);
    }

    @Autowired
    public  void setFlowDate(FlowDateInterface flowDate) {
        FullFlowProcessorImp.flowDate = flowDate;
    }

    public static FullFlow buildFullFlow(String bid, String starter, String flowClazz){

        List<? extends FlowTemplates> flowTemplates = flowDate.findFlowTemplates(flowClazz);
        FlowTemplates template = null;
        for (FlowTemplates templates:flowTemplates
                ) {
            if(templates.getFlowStepNumber()==2){
                template=templates;
                break;
            }
        }

        MainFlowEntity mainFlowEntity = new MainFlowEntity();
        mainFlowEntity.setFlowId(IdMaker.makeFlowId(flowClazz));
        mainFlowEntity.setBid(bid);
        mainFlowEntity.setFlowClazzId(flowClazz);
        mainFlowEntity.setFlowStepNumber(2);
        mainFlowEntity.setHanding(template != null ? template.getHandller() : null);
        mainFlowEntity.setStartDate(new Date());
        mainFlowEntity.setStarter(starter);
        mainFlowEntity.setState(FlowState.HANDING);

        List<? extends FlowDetail> flowDetails = flowDate.creatDetail(mainFlowEntity);
        return new FullFlowImp(mainFlowEntity,flowDetails);



    }
}
