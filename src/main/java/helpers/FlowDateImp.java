package helpers;

import Flow.*;
import entitys.FlowDetailEntity;
import entitys.FlowTemplatesEntity;
import entitys.MainFlowEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositorys.mySqlRepositorys.FlowDetailRepository;
import repositorys.mySqlRepositorys.FlowTemplatesRepository;
import repositorys.mySqlRepositorys.MainFlowRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tech on 2017/12/25.
 * 流程数据操作器实现
 */
@Component
public class FlowDateImp implements FlowDateInterface {

    private final
    FlowDetailRepository detailRepository;
    private final
    FlowTemplatesRepository templatesRepository;
    private final
    MainFlowRepository mainFlowRepository;

    @Autowired
    public FlowDateImp(FlowDetailRepository detailRepository, FlowTemplatesRepository templatesRepository, MainFlowRepository mainFlowRepository) {
        this.detailRepository = detailRepository;
        this.templatesRepository = templatesRepository;
        this.mainFlowRepository = mainFlowRepository;
    }


    public FullFlow findFullFlow(String flowId) {

        MainFlowEntity flow = mainFlowRepository.findByFlowId(flowId);
        List<FlowDetailEntity> flowDetails = detailRepository.findByFlowId(flowId);
        return new FullFlowImp(flow,flowDetails);
    }

    public List<FlowTemplatesEntity> findFlowTemplates(String flowClazzID) {
        return templatesRepository.findByFormClazzId(flowClazzID);
    }

    public int insertFullFlow(FullFlow fullFlow) {
        mainFlowRepository.save((MainFlowEntity) fullFlow.getFlow());
        for (FlowDetail entity:fullFlow.getFlowDetail()
             ) {
            detailRepository.save((FlowDetailEntity) entity);
        }

        return 1;
    }

    public int updateFullFlow(FullFlow fullFlow) {
        mainFlowRepository.save((MainFlowEntity)fullFlow.getFlow());
        for (FlowDetail entity:fullFlow.getFlowDetail()
                ) {
            detailRepository.save((FlowDetailEntity) entity);
        }
        return 0;
    }

    public List<FlowDetailEntity> creatDetail(MainFlow flow) {
        List<FlowTemplatesEntity> templatesEntities = this.findFlowTemplates(flow.getFlowClazzId());
        List<FlowDetailEntity> flowDetailEntities = new ArrayList<FlowDetailEntity>();
        FlowDetailEntity fristStep = new FlowDetailEntity();
        fristStep.setFlowId(flow.getFlowId());
        fristStep.setFlowStepNumber(1);
        fristStep.setHandDate(flow.getStartDate());
        fristStep.setHandller(flow.getStarter());
        flowDetailEntities.add(fristStep);

        for (FlowTemplatesEntity template:templatesEntities
             ) {
            FlowDetailEntity flowDetailEntity = new FlowDetailEntity();
            flowDetailEntity.setFlowId(flow.getFlowId());
            flowDetailEntity.setFlowStepNumber(template.getFlowStepNumber());
            flowDetailEntity.setHandller(template.getHandller());
            flowDetailEntities.add(flowDetailEntity);
        }

        return flowDetailEntities;
    }
}
