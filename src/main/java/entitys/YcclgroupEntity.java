package entitys;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ycclgroup", schema = "oasystemdev", catalog = "")
public class YcclgroupEntity {
    private int id;
    private String groupid;
    private String groupname;
    private Date creatdate;
    private String creater;
    private String charger;

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
    @Column(name = "groupname")
    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Basic
    @Column(name = "creatdate")
    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    @Basic
    @Column(name = "creater")
    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    @Basic
    @Column(name = "charger")
    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YcclgroupEntity that = (YcclgroupEntity) o;

        if (id != that.id) return false;
        if (groupid != null ? !groupid.equals(that.groupid) : that.groupid != null) return false;
        if (groupname != null ? !groupname.equals(that.groupname) : that.groupname != null) return false;
        if (creatdate != null ? !creatdate.equals(that.creatdate) : that.creatdate != null) return false;
        if (creater != null ? !creater.equals(that.creater) : that.creater != null) return false;
        if (charger != null ? !charger.equals(that.charger) : that.charger != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (groupid != null ? groupid.hashCode() : 0);
        result = 31 * result + (groupname != null ? groupname.hashCode() : 0);
        result = 31 * result + (creatdate != null ? creatdate.hashCode() : 0);
        result = 31 * result + (creater != null ? creater.hashCode() : 0);
        result = 31 * result + (charger != null ? charger.hashCode() : 0);
        return result;
    }
}
