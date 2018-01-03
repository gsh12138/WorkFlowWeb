package entitys;

import Flow.FlowState;
import Flow.MainFlow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by tech on 2017/12/25.
 * 流程主要信息
 */
@Entity
@Table(name = "mainflow", schema = "oasystemdev")
public class MainFlowEntity implements MainFlow,Comparable<MainFlowEntity> {


    @Column(name = "id")
    @Id
    private int id;
    @Column(name = "flowclazzid")
    private String flowClazzId;
    @Column(name = "flowid")
    private String flowId;
    @Column(name = "bid")
    private String bid;
    @Column(name = "starter")
    private String starter;
    @Column(name = "startdate")
    private Date startDate;
    @Column(name = "flowstepnumber")
    private int flowStepNumber;
    @Column(name = "handing")
    private String handing;
    @Column(name = "state")
    private String state;



    public MainFlowEntity() {
    }

    public String getFlowClazzId() {
        return flowClazzId;
    }

    public void setFlowClazzId(String flowClazzId) {
        this.flowClazzId = flowClazzId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getFlowStepNumber() {
        return flowStepNumber;
    }

    public void setFlowStepNumber(int flowSetpNumber) {
        this.flowStepNumber = flowSetpNumber;
    }

    public String getHanding() {
        return handing;
    }

    public void setHanding(String handing) {
        this.handing = handing;
    }

    public FlowState getState() {
        return FlowState.valueOf(state);
    }

    public void setState(FlowState state) {
        this.state = state.toString();

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MainFlowEntity obj = (MainFlowEntity)o;

        return this.flowId.equals(obj.getFlowId());

    }

    public int compareTo(MainFlowEntity o) {

        String thisprf = (this.flowId.split("-"))[1];
        String otherprf = (o.getFlowId().split("-"))[1];
        String thissuf = (this.flowId.split("-"))[0];
        String othersuf = (o.getFlowId().split("-"))[0];

        int thisdate = Integer.valueOf(thissuf.substring(0,7));
        int otherdate = Integer.valueOf(othersuf.substring(0,7));

        int thisNum = Integer.valueOf(thissuf.substring(8));
        int otherNum = Integer.valueOf(othersuf.substring(8));

        if(!thisprf.equals(otherprf)){
            return thisprf.compareTo(otherprf);
        }

        if(thisdate!=otherdate){
            return thisdate-otherdate;
        }

        return thisNum-otherNum;


    }
}
