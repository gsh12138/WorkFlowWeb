package entitys;


import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;
@Entity
@Table(name = "knowledge", schema = "oasystemdev")
public class KnowledgeEntity implements HaveUpdateFileEntity {
    private int id;
    private String bid;
    private Date happendate;
    private Date submitdate;
    private String submiter;
    private String xgbm;
    private String[] xgbmuse;
    private String ycfl;
    private String zt;
    private String gjc;
    private String cygjc;
    private String tsbm;
    private String[] tsbmuse;
    private String ycms;
    private String updatefile;
    private String updatefilename;

    public KnowledgeEntity() {
    }




    public KnowledgeEntity(Date happendate, String[] xgbmuse, String ycfl, String zt, String gjc, String cygjc, String tsbm, String ycms) {
        this.happendate = happendate;
        this.xgbmuse = xgbmuse;
        this.ycfl = ycfl;
        this.zt = zt;
        this.gjc = gjc;
        this.cygjc = cygjc;
        this.tsbm = tsbm;
        this.ycms = ycms;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    private void setid(int id){
        this.id=id;
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
    @Column(name = "happendate")
    public Date getHappendate() {
        return happendate;
    }

    public void setHappendate(Date happendate) {
        this.happendate = happendate;
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
    @Column(name = "xgbm")
    public String getXgbm(){
        return xgbm;
    }

    public void setXgbm(String xgbm){
        this.xgbm=xgbm;
    }

    @Transient
    public String[] getXgbmuse() {
        if(xgbmuse==null){
            this.xgbmuse=xgbm.split("|");
        }
        return this.xgbmuse;
    }

    public void setXgbmuse(String[] xgbmuse) {
        this.xgbmuse = xgbmuse;

        this.xgbm=this.bmtrans(xgbmuse);
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
    @Column(name = "zt")
    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
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
    @Column(name = "cygjc")
    public String getCygjc() {
        return cygjc;
    }

    public void setCygjc(String cygjc) {
        this.cygjc = cygjc;
    }

    @Basic
    @Column(name = "tsbm")
    public String getTsbm() {
        return tsbm;
    }

    public void setTsbm(String tsbm) {
        this.tsbm = tsbm;
    }

    @Transient
    public String[] getTsbmuse() {
        if(this.tsbm==null){
            this.tsbmuse=tsbm.split("|");
        }
        return this.tsbmuse;
    }

    public void setTsbmuse(String[] tsbmuse) {
        this.tsbmuse = tsbmuse;

        this.tsbm=this.bmtrans(tsbmuse);
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
    @Column(name = "updatefile")
    public String getUpdatefile() {
        return updatefile;
    }

    public void setUpdatefile(String updatefile) {
        this.updatefile = updatefile;
    }

    @Basic
    @Column(name="updatefilename")
    public String getUpdatefilename() {
        return updatefilename;
    }

    public void setUpdatefilename(String updatefilename) {
        this.updatefilename = updatefilename;
    }

    private String bmtrans(String[] trans){
        StringBuilder sb = new StringBuilder();
        int t = 0;
        for (String s:trans
                ) {
            if(t==trans.length-1){
                sb.append(s);
            }else {
                sb.append(s);
                sb.append("|");
                t+=1;
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "KnowledgeEntity{" +
                "happendate=" + happendate +
                ", xgbm=" + xgbm +
                ", ycfl='" + ycfl + '\'' +
                ", zt='" + zt + '\'' +
                ", gjc='" + gjc + '\'' +
                ", cygjc='" + cygjc + '\'' +
                ", tsbm='" + tsbm + '\'' +
                ", ycms='" + ycms + '\'' +
                '}';
    }
}
