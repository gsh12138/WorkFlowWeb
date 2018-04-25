package entitys;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "knowledgehanding", schema = "oasystemdev", catalog = "")
public class KnowledgehandingEntity implements HaveUpdateFileEntity {
    private int id;
    private String bid;
    private Date submitdate;
    private String submiter;
    private String zt;
    private String content;
    private String updatefile;
    private String updatefilename;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bid")
    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "submitdate")
    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    @Basic
    @Column(name = "submiter")
    public String getSubmiter() {
        return submiter;
    }

    public void setSubmiter(String submiter) {
        this.submiter = submiter;
    }

    @Basic
    @Column(name = "zt")
    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "updatefile")
    public String getUpdatefile() {
        return updatefile;
    }

    public void setUpdatefile(String updatefile) {
        this.updatefile = updatefile;
    }

    @Basic
    @Column(name = "updatefilename")
    public String getUpdatefilename() {
        return updatefilename;
    }

    public void setUpdatefilename(String updatefilename) {
        this.updatefilename = updatefilename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnowledgehandingEntity that = (KnowledgehandingEntity) o;

        if (id != that.id) return false;
        if (bid != null ? !bid.equals(that.bid) : that.bid != null) return false;
        if (submitdate != null ? !submitdate.equals(that.submitdate) : that.submitdate != null) return false;
        if (submiter != null ? !submiter.equals(that.submiter) : that.submiter != null) return false;
        if (zt != null ? !zt.equals(that.zt) : that.zt != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (updatefile != null ? !updatefile.equals(that.updatefile) : that.updatefile != null) return false;
        if (updatefilename != null ? !updatefilename.equals(that.updatefilename) : that.updatefilename != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (submitdate != null ? submitdate.hashCode() : 0);
        result = 31 * result + (submiter != null ? submiter.hashCode() : 0);
        result = 31 * result + (zt != null ? zt.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (updatefile != null ? updatefile.hashCode() : 0);
        result = 31 * result + (updatefilename != null ? updatefilename.hashCode() : 0);
        return result;
    }
}
