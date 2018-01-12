package entitys;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ycclgroupmember", schema = "oasystemdev", catalog = "")
public class YcclgroupmemberEntity {
    private int id;
    private String groupid;
    private String memberid;
    private String membername;
    private Date joindate;
    private String authority;
    private String state;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "groupid")
    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    @Basic
    @Column(name = "memberid")
    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    @Basic
    @Column(name = "membername")
    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    @Basic
    @Column(name = "joindate")
    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    @Basic
    @Column(name = "authority")
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YcclgroupmemberEntity that = (YcclgroupmemberEntity) o;

        if (id != that.id) return false;
        if (groupid != null ? !groupid.equals(that.groupid) : that.groupid != null) return false;
        if (memberid != null ? !memberid.equals(that.memberid) : that.memberid != null) return false;
        if (membername != null ? !membername.equals(that.membername) : that.membername != null) return false;
        if (joindate != null ? !joindate.equals(that.joindate) : that.joindate != null) return false;
        if (authority != null ? !authority.equals(that.authority) : that.authority != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (groupid != null ? groupid.hashCode() : 0);
        result = 31 * result + (memberid != null ? memberid.hashCode() : 0);
        result = 31 * result + (membername != null ? membername.hashCode() : 0);
        result = 31 * result + (joindate != null ? joindate.hashCode() : 0);
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }


}
