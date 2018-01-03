package entitys;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by tech on 2017/12/21.
 */
@Entity
@Table(name = "Gtestbiaodan", schema = "dbo", catalog = "TJZH")
public class GtestbiaodanEntity {
    private String id;
    private Date tbrq;
    private String tbbm;
    private String tbrgh;
    private Date cxrq;
    private String ycgx;
    private String zrbm;
    private String btzr;
    private String wlmc;
    private String wlxh;
    private String ph;
    private String sb;
    private String sbbh;
    private String gsbgp;
    private String zt;
    private String ycfl;
    private String ycms;
    private String zrrgh;
    private String gjc;
    private String yyfx;
    private String zgjg;
    private String gsbg;
    private Date wcrq;
    private String xgqr;
    private Integer bdzt;
    private Date gbrq;
    private Integer fouce;
    private Integer zsjl;
    private Date zfrq;
    private Date xyrq;
    private Integer gbzt;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tbrq")
    public Date getTbrq() {
        return tbrq;
    }

    public void setTbrq(Date tbrq) {
        this.tbrq = tbrq;
    }

    @Basic
    @Column(name = "tbbm")
    public String getTbbm() {
        return tbbm;
    }

    public void setTbbm(String tbbm) {
        this.tbbm = tbbm;
    }

    @Basic
    @Column(name = "tbrgh")
    public String getTbrgh() {
        return tbrgh;
    }

    public void setTbrgh(String tbrgh) {
        this.tbrgh = tbrgh;
    }

    @Basic
    @Column(name = "cxrq")
    public Date getCxrq() {
        return cxrq;
    }

    public void setCxrq(Date cxrq) {
        this.cxrq = cxrq;
    }

    @Basic
    @Column(name = "ycgx")
    public String getYcgx() {
        return ycgx;
    }

    public void setYcgx(String ycgx) {
        this.ycgx = ycgx;
    }

    @Basic
    @Column(name = "zrbm")
    public String getZrbm() {
        return zrbm;
    }

    public void setZrbm(String zrbm) {
        this.zrbm = zrbm;
    }

    @Basic
    @Column(name = "btzr")
    public String getBtzr() {
        return btzr;
    }

    public void setBtzr(String btzr) {
        this.btzr = btzr;
    }

    @Basic
    @Column(name = "wlmc")
    public String getWlmc() {
        return wlmc;
    }

    public void setWlmc(String wlmc) {
        this.wlmc = wlmc;
    }

    @Basic
    @Column(name = "wlxh")
    public String getWlxh() {
        return wlxh;
    }

    public void setWlxh(String wlxh) {
        this.wlxh = wlxh;
    }

    @Basic
    @Column(name = "ph")
    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    @Basic
    @Column(name = "sb")
    public String getSb() {
        return sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    @Basic
    @Column(name = "sbbh")
    public String getSbbh() {
        return sbbh;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    @Basic
    @Column(name = "gsbgp")
    public String getGsbgp() {
        return gsbgp;
    }

    public void setGsbgp(String gsbgp) {
        this.gsbgp = gsbgp;
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
    @Column(name = "ycfl")
    public String getYcfl() {
        return ycfl;
    }

    public void setYcfl(String ycfl) {
        this.ycfl = ycfl;
    }

    @Basic
    @Column(name = "ycms")
    public String getYcms() {
        return ycms;
    }

    public void setYcms(String ycms) {
        this.ycms = ycms;
    }

    @Basic
    @Column(name = "zrrgh")
    public String getZrrgh() {
        return zrrgh;
    }

    public void setZrrgh(String zrrgh) {
        this.zrrgh = zrrgh;
    }

    @Basic
    @Column(name = "gjc")
    public String getGjc() {
        return gjc;
    }

    public void setGjc(String gjc) {
        this.gjc = gjc;
    }

    @Basic
    @Column(name = "yyfx")
    public String getYyfx() {
        return yyfx;
    }

    public void setYyfx(String yyfx) {
        this.yyfx = yyfx;
    }

    @Basic
    @Column(name = "zgjg")
    public String getZgjg() {
        return zgjg;
    }

    public void setZgjg(String zgjg) {
        this.zgjg = zgjg;
    }

    @Basic
    @Column(name = "gsbg")
    public String getGsbg() {
        return gsbg;
    }

    public void setGsbg(String gsbg) {
        this.gsbg = gsbg;
    }

    @Basic
    @Column(name = "wcrq")
    public Date getWcrq() {
        return wcrq;
    }

    public void setWcrq(Date wcrq) {
        this.wcrq = wcrq;
    }

    @Basic
    @Column(name = "xgqr")
    public String getXgqr() {
        return xgqr;
    }

    public void setXgqr(String xgqr) {
        this.xgqr = xgqr;
    }

    @Basic
    @Column(name = "bdzt")
    public Integer getBdzt() {
        return bdzt;
    }

    public void setBdzt(Integer bdzt) {
        this.bdzt = bdzt;
    }

    @Basic
    @Column(name = "gbrq")
    public Date getGbrq() {
        return gbrq;
    }

    public void setGbrq(Date gbrq) {
        this.gbrq = gbrq;
    }

    @Basic
    @Column(name = "fouce")
    public Integer getFouce() {
        return fouce;
    }

    public void setFouce(Integer fouce) {
        this.fouce = fouce;
    }

    @Basic
    @Column(name = "zsjl")
    public Integer getZsjl() {
        return zsjl;
    }

    public void setZsjl(Integer zsjl) {
        this.zsjl = zsjl;
    }

    @Basic
    @Column(name = "zfrq")
    public Date getZfrq() {
        return zfrq;
    }

    public void setZfrq(Date zfrq) {
        this.zfrq = zfrq;
    }

    @Basic
    @Column(name = "xyrq")
    public Date getXyrq() {
        return xyrq;
    }

    public void setXyrq(Date xyrq) {
        this.xyrq = xyrq;
    }

    @Basic
    @Column(name = "gbzt")
    public Integer getGbzt() {
        return gbzt;
    }

    public void setGbzt(Integer gbzt) {
        this.gbzt = gbzt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GtestbiaodanEntity that = (GtestbiaodanEntity) o;

        return new org.apache.commons.lang.builder.EqualsBuilder()
                .append(id, that.id)
                .append(tbrq, that.tbrq)
                .append(tbbm, that.tbbm)
                .append(tbrgh, that.tbrgh)
                .append(cxrq, that.cxrq)
                .append(ycgx, that.ycgx)
                .append(zrbm, that.zrbm)
                .append(btzr, that.btzr)
                .append(wlmc, that.wlmc)
                .append(wlxh, that.wlxh)
                .append(ph, that.ph)
                .append(sb, that.sb)
                .append(sbbh, that.sbbh)
                .append(gsbgp, that.gsbgp)
                .append(zt, that.zt)
                .append(ycfl, that.ycfl)
                .append(ycms, that.ycms)
                .append(zrrgh, that.zrrgh)
                .append(gjc, that.gjc)
                .append(yyfx, that.yyfx)
                .append(zgjg, that.zgjg)
                .append(gsbg, that.gsbg)
                .append(wcrq, that.wcrq)
                .append(xgqr, that.xgqr)
                .append(bdzt, that.bdzt)
                .append(gbrq, that.gbrq)
                .append(fouce, that.fouce)
                .append(zsjl, that.zsjl)
                .append(zfrq, that.zfrq)
                .append(xyrq, that.xyrq)
                .append(gbzt, that.gbzt)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang.builder.HashCodeBuilder(17, 37)
                .append(id)
                .append(tbrq)
                .append(tbbm)
                .append(tbrgh)
                .append(cxrq)
                .append(ycgx)
                .append(zrbm)
                .append(btzr)
                .append(wlmc)
                .append(wlxh)
                .append(ph)
                .append(sb)
                .append(sbbh)
                .append(gsbgp)
                .append(zt)
                .append(ycfl)
                .append(ycms)
                .append(zrrgh)
                .append(gjc)
                .append(yyfx)
                .append(zgjg)
                .append(gsbg)
                .append(wcrq)
                .append(xgqr)
                .append(bdzt)
                .append(gbrq)
                .append(fouce)
                .append(zsjl)
                .append(zfrq)
                .append(xyrq)
                .append(gbzt)
                .toHashCode();
    }
}
