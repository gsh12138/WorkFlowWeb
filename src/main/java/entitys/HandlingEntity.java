package entitys;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by tech on 2017/12/22.
 */
@Entity
@Table(name = "handling", schema = "oasystemdev", catalog = "")
public class HandlingEntity {
    private int id;
    private String bid;
    private String keyword;
    private String reason;
    private String result;
    private Date finishdate;
    private String handler;
    private String updatefile;

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
    @Column(name = "keyword")
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Basic
    @Column(name = "finishdate")
    public Date getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(Date finishdate) {
        this.finishdate = finishdate;
    }

    @Basic
    @Column(name = "handler")
    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    @Basic
    @Column(name = "updatefile")
    public String getUpdatefile() {
        return updatefile;
    }

    public void setUpdatefile(String updatefile) {
        this.updatefile = updatefile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        HandlingEntity that = (HandlingEntity) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(bid, that.bid)
                .append(keyword, that.keyword)
                .append(reason, that.reason)
                .append(result, that.result)
                .append(finishdate, that.finishdate)
                .append(handler, that.handler)
                .append(updatefile, that.updatefile)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(bid)
                .append(keyword)
                .append(reason)
                .append(result)
                .append(finishdate)
                .append(handler)
                .append(updatefile)
                .toHashCode();
    }
}
