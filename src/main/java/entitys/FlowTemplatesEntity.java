package entitys;

import Flow.FlowTemplates;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tech on 2017/12/25.
 */
@Entity
@Table(name = "flowtemplates", schema = "oasystemdev")
public class FlowTemplatesEntity implements FlowTemplates {

    @Column(name = "id")
    @Id
    private int id;
    @Column(name = "formclazzid")
    private String formClazzId;
    @Column(name = "flowstepnumber")
    private int flowStepNumber;
    @Column(name = "handller")
    private String handller;
    @Column(name = "handlerclazz")
    private String handlerClazz;

    public FlowTemplatesEntity() {
    }

    public String getFormClazzId() {
        return formClazzId;
    }

    public void setFormClazzId(String formClazzId) {
        this.formClazzId = formClazzId;
    }

    public int getFlowStepNumber() {
        return flowStepNumber;
    }

    public void setFlowStepNumber(int flowStepNumber) {
        this.flowStepNumber = flowStepNumber;
    }

    public String getHandller() {
        return handller;
    }

    public void setHandller(String handller) {
        this.handller = handller;
    }

    public String getHandlerClazz() {
        return handlerClazz;
    }

    public void setHandlerClazz(String handlerClazz) {
        this.handlerClazz = handlerClazz;
    }
}
