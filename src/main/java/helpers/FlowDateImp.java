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
        List<FlowDetail> flowDetailList = new ArrayList<FlowDetail>(flowDetails);
        return new FullFlowImp(flow,flowDetailList);
    }

    public FullFlow findFullFlowByBid(String bid) {

        MainFlowEntity flow = mainFlowRepository.findByBid(bid);
        List<FlowDetailEntity> flowDetails = detailRepository.findByFlowId(flow.getFlowId());
        List<FlowDetail> flowDetailList = new ArrayList<FlowDetail>(flowDetails);
        return new FullFlowImp(flow,flowDetailList);
    }

    public List<FlowTemplates> findFlowTemplates(String flowClazzID) {
        List<FlowTemplates> templates = new ArrayList<FlowTemplates>(
                templatesRepository.findByFormClazzId(flowClazzID));
        return templates;
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

    public List<FlowDetail> creatDetail(MainFlow flow) {
        List<FlowTemplates> templatesEntities = this.findFlowTemplates(flow.getFlowClazzId());
        List<FlowDetail> flowDetailEntities = new ArrayList<FlowDetail>();
        FlowDetailEntity fristStep = new FlowDetailEntity();
        fristStep.setFlowId(flow.getFlowId());
        fristStep.setFlowStepNumber(1);
        fristStep.setHandDate(flow.getStartDate());
        fristStep.setHandller(flow.getStarter());
        flowDetailEntities.add(fristStep);

        for (FlowTemplates template:templatesEntities
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
