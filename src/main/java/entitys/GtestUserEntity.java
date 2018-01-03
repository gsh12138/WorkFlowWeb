package entitys;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * Created by tech on 2017/12/21.
 */
@Entity
@Table(name = "GtestUser", schema = "dbo", catalog = "TJZH")
public class GtestUserEntity {
    private int userid;
    private String username;
    private String password;
    private String shuliang;
    private String bumen;
    private String tel;
    private String dengluma;
    private String zhiwei;
    private String userxm;

    @Id
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "shuliang")
    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang;
    }

    @Basic
    @Column(name = "bumen")
    public String getBumen() {
        return bumen;
    }

    public void setBumen(String bumen) {
        this.bumen = bumen;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "dengluma")
    public String getDengluma() {
        return dengluma;
    }

    public void setDengluma(String dengluma) {
        this.dengluma = dengluma;
    }

    @Basic
    @Column(name = "zhiwei")
    public String getZhiwei() {
        return zhiwei;
    }

    public void setZhiwei(String zhiwei) {
        this.zhiwei = zhiwei;
    }

    @Basic
    @Column(name = "userxm")
    public String getUserxm() {
        return userxm;
    }

    public void setUserxm(String userxm) {
        this.userxm = userxm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GtestUserEntity that = (GtestUserEntity) o;

        return new EqualsBuilder()
                .append(userid, that.userid)
                .append(username, that.username)
                .append(password, that.password)
                .append(shuliang, that.shuliang)
                .append(bumen, that.bumen)
                .append(tel, that.tel)
                .append(dengluma, that.dengluma)
                .append(zhiwei, that.zhiwei)
                .append(userxm, that.userxm)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userid)
                .append(username)
                .append(password)
                .append(shuliang)
                .append(bumen)
                .append(tel)
                .append(dengluma)
                .append(zhiwei)
                .append(userxm)
                .toHashCode();
    }
}
