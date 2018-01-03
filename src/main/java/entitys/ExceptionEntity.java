package entitys;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by tech on 2017/12/22.
 */
@Entity
@Table(name = "exception", schema = "oasystemdev", catalog = "")
public class ExceptionEntity {
    private int id;
    private String bid;
    private Date creatdate;
    private String creatdep;
    private String creater;
    private Date happendate;
    private String handleteam;
    private String theme;
    private String clazz;
    private String content;
    private String other;
    private Boolean knowledege;
    private Boolean fracas;
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
    @Column(name = "creatdate")
    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    @Basic
    @Column(name = "creatdep")
    public String getCreatdep() {
        return creatdep;
    }

    public void setCreatdep(String creatdep) {
        this.creatdep = creatdep;
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
    @Column(name = "happendate")
    public Date getHappendate() {
        return happendate;
    }

    public void setHappendate(Date happendate) {
        this.happendate = happendate;
    }

    @Basic
    @Column(name = "handleteam")
    public String getHandleteam() {
        return handleteam;
    }

    public void setHandleteam(String handleteam) {
        this.handleteam = handleteam;
    }

    @Basic
    @Column(name = "theme")
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Basic
    @Column(name = "class")
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
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
    @Column(name = "other")
    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Basic
    @Column(name = "knowledege")
    public Boolean getKnowledege() {
        return knowledege;
    }

    public void setKnowledege(Boolean knowledege) {
        this.knowledege = knowledege;
    }

    @Basic
    @Column(name = "fracas")
    public Boolean getFracas() {
        return fracas;
    }

    public void setFracas(Boolean fracas) {
        this.fracas = fracas;
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

        ExceptionEntity that = (ExceptionEntity) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(bid, that.bid)
                .append(creatdate, that.creatdate)
                .append(creatdep, that.creatdep)
                .append(creater, that.creater)
                .append(happendate, that.happendate)
                .append(handleteam, that.handleteam)
                .append(theme, that.theme)
                .append(clazz, that.clazz)
                .append(content, that.content)
                .append(other, that.other)
                .append(knowledege, that.knowledege)
                .append(fracas, that.fracas)
                .append(updatefile, that.updatefile)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(bid)
                .append(creatdate)
                .append(creatdep)
                .append(creater)
                .append(happendate)
                .append(handleteam)
                .append(theme)
                .append(clazz)
                .append(content)
                .append(other)
                .append(knowledege)
                .append(fracas)
                .append(updatefile)
                .toHashCode();
    }
}
