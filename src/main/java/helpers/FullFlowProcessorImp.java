package helpers;

import Flow.*;
import entitys.FlowDetailEntity;
import entitys.MainFlowEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        List<FlowDetail> flowDetails = flowDate.creatDetail(mainFlowEntity);
        return new FullFlowImp(mainFlowEntity,flowDetails);



    }

    public static FullFlow buildNoTemplateFlow(String bid,String starter,String handler,String flowClazz){
        MainFlowEntity mainFlowEntity = new MainFlowEntity();
        mainFlowEntity.setFlowId(IdMaker.makeFlowId(flowClazz));
        mainFlowEntity.setBid(bid);
        mainFlowEntity.setFlowClazzId(flowClazz);
        mainFlowEntity.setFlowStepNumber(2);
        mainFlowEntity.setHanding(handler);
        mainFlowEntity.setStartDate(new Date());
        mainFlowEntity.setStarter(starter);
        mainFlowEntity.setState(FlowState.HANDING);

        List<FlowDetail> flowDetails = new ArrayList<FlowDetail>();
        FlowDetailEntity fristStep = new FlowDetailEntity();
        fristStep.setFlowId(mainFlowEntity.getFlowId());
        fristStep.setFlowStepNumber(1);
        fristStep.setHandDate(mainFlowEntity.getStartDate());
        fristStep.setHandller(mainFlowEntity.getStarter());
        flowDetails.add(fristStep);

        FlowDetailEntity handlingStep = new FlowDetailEntity();
        handlingStep.setFlowId(mainFlowEntity.getFlowId());
        handlingStep.setFlowStepNumber(2);
        handlingStep.setHandller(handler);
        flowDetails.add(handlingStep);

        return new FullFlowImp(mainFlowEntity,flowDetails);
    }
}
