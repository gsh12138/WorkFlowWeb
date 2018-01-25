package entitys;

import Flow.FlowDetail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by tech on 2017/12/25.
 * 流程详细信息
 */
@Entity
@Table(name = "flowdetail", schema = "oasystemdev")
public class FlowDetailEntity implements FlowDetail, Comparable<FlowDetailEntity> {

    @Column(name = "id")
    @Id
    private int id;
    @Column(name = "flowid")
    private String flowId;
    @Column(name = "flowstepnumber")
    private int flowStepNumber;
    @Column(name = "handller")
    private String handller;
    @Column(name = "handdate")
    private Date handDate;
    @Column(name = "handresult")
    private String handResult;
    @Column(name="handcontent")
    private String handcontent;

    public FlowDetailEntity() {
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
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

    public Date getHandDate() {
        return handDate;
    }

    public void setHandDate(Date handDate) {
        this.handDate = handDate;
    }

    public String getHandResult() {
        return handResult;
    }

    public void setHandResult(String handResult) {
        this.handResult = handResult;
    }

    public String getHandcontent() {
        return handcontent;
    }

    public void setHandcontent(String handcontent) {
        this.handcontent = handcontent;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){return true;}
        if (obj == null || getClass() != obj.getClass()) return false;
        FlowDetailEntity ob = (FlowDetailEntity)obj;
        return this.flowId.equals(ob.getFlowId()) && this.flowStepNumber == ob.getFlowStepNumber();

    }

    public int compareTo(FlowDetailEntity o) {

        if(this.flowId.equals(o.getFlowId())){
            return this.flowStepNumber-o.getFlowStepNumber();
        }

        return this.flowId.compareTo(o.getFlowId());
    }
}
