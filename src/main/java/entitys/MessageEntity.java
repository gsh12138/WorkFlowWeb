package entitys;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message", schema = "oasystemdev", catalog = "")
public class MessageEntity {
    private int id;
    private String sender;
    private String receiver;
    private String content;
    private String agreeurl;
    private String refuseurl;
    private String linkurl;
    private String type;
    private Date senddate;
    private Boolean isnew;
    private java.sql.Date readdate;

    public void setSenddate(java.sql.Date senddate) {
        this.senddate = senddate;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sender")
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Basic
    @Column(name = "receiver")
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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
    @Column(name = "agreeurl")
    public String getAgreeurl() {
        return agreeurl;
    }

    public void setAgreeurl(String agreeurl) {
        this.agreeurl = agreeurl;
    }

    @Basic
    @Column(name = "refuseurl")
    public String getRefuseurl() {
        return refuseurl;
    }

    public void setRefuseurl(String refuseurl) {
        this.refuseurl = refuseurl;
    }

    @Basic
    @Column(name = "linkurl")
    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "senddate")
    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (id != that.id) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        if (receiver != null ? !receiver.equals(that.receiver) : that.receiver != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (agreeurl != null ? !agreeurl.equals(that.agreeurl) : that.agreeurl != null) return false;
        if (refuseurl != null ? !refuseurl.equals(that.refuseurl) : that.refuseurl != null) return false;
        if (linkurl != null ? !linkurl.equals(that.linkurl) : that.linkurl != null) return false;
        return (type != null ? type.equals(that.type) : that.type == null) && (senddate != null ? senddate.equals(that.senddate) : that.senddate == null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (agreeurl != null ? agreeurl.hashCode() : 0);
        result = 31 * result + (refuseurl != null ? refuseurl.hashCode() : 0);
        result = 31 * result + (linkurl != null ? linkurl.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (senddate != null ? senddate.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "isnew")
    public Boolean getIsnew() {
        return isnew;
    }

    public void setIsnew(Boolean isnew) {
        this.isnew = isnew;
    }

    @Basic
    @Column(name = "readdate")
    public java.sql.Date getReaddate() {
        return readdate;
    }

    public void setReaddate(java.sql.Date readdate) {
        this.readdate = readdate;
    }
}
