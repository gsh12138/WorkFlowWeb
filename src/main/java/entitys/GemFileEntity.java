package entitys;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by tech on 2017/12/21.
 */
@Entity
@Table(name = "GEM_FILE", schema = "TSDB", catalog = "")
public class GemFileEntity {
    private String gem01;
    private String gem02;
    private String gem03;
    private String gem04;
    private String gem05;
    private String gem06;
    private String gem07;
    private String gem08;
    private String gemacti;
    private String gemuser;
    private String gemgrup;
    private String gemmodu;
    private Time gemdate;
    private String gem09;
    private String gem10;
    private String gem11;
    private String gemorig;
    private String gemoriu;

    @Id
    @Column(name = "GEM01")
    public String getGem01() {
        return gem01;
    }

    public void setGem01(String gem01) {
        this.gem01 = gem01;
    }

    @Basic
    @Column(name = "GEM02")
    public String getGem02() {
        return gem02;
    }

    public void setGem02(String gem02) {
        this.gem02 = gem02;
    }

    @Basic
    @Column(name = "GEM03")
    public String getGem03() {
        return gem03;
    }

    public void setGem03(String gem03) {
        this.gem03 = gem03;
    }

    @Basic
    @Column(name = "GEM04")
    public String getGem04() {
        return gem04;
    }

    public void setGem04(String gem04) {
        this.gem04 = gem04;
    }

    @Basic
    @Column(name = "GEM05")
    public String getGem05() {
        return gem05;
    }

    public void setGem05(String gem05) {
        this.gem05 = gem05;
    }

    @Basic
    @Column(name = "GEM06")
    public String getGem06() {
        return gem06;
    }

    public void setGem06(String gem06) {
        this.gem06 = gem06;
    }

    @Basic
    @Column(name = "GEM07")
    public String getGem07() {
        return gem07;
    }

    public void setGem07(String gem07) {
        this.gem07 = gem07;
    }

    @Basic
    @Column(name = "GEM08")
    public String getGem08() {
        return gem08;
    }

    public void setGem08(String gem08) {
        this.gem08 = gem08;
    }

    @Basic
    @Column(name = "GEMACTI")
    public String getGemacti() {
        return gemacti;
    }

    public void setGemacti(String gemacti) {
        this.gemacti = gemacti;
    }

    @Basic
    @Column(name = "GEMUSER")
    public String getGemuser() {
        return gemuser;
    }

    public void setGemuser(String gemuser) {
        this.gemuser = gemuser;
    }

    @Basic
    @Column(name = "GEMGRUP")
    public String getGemgrup() {
        return gemgrup;
    }

    public void setGemgrup(String gemgrup) {
        this.gemgrup = gemgrup;
    }

    @Basic
    @Column(name = "GEMMODU")
    public String getGemmodu() {
        return gemmodu;
    }

    public void setGemmodu(String gemmodu) {
        this.gemmodu = gemmodu;
    }

    @Basic
    @Column(name = "GEMDATE")
    public Time getGemdate() {
        return gemdate;
    }

    public void setGemdate(Time gemdate) {
        this.gemdate = gemdate;
    }

    @Basic
    @Column(name = "GEM09")
    public String getGem09() {
        return gem09;
    }

    public void setGem09(String gem09) {
        this.gem09 = gem09;
    }

    @Basic
    @Column(name = "GEM10")
    public String getGem10() {
        return gem10;
    }

    public void setGem10(String gem10) {
        this.gem10 = gem10;
    }

    @Basic
    @Column(name = "GEM11")
    public String getGem11() {
        return gem11;
    }

    public void setGem11(String gem11) {
        this.gem11 = gem11;
    }

    @Basic
    @Column(name = "GEMORIG")
    public String getGemorig() {
        return gemorig;
    }

    public void setGemorig(String gemorig) {
        this.gemorig = gemorig;
    }

    @Basic
    @Column(name = "GEMORIU")
    public String getGemoriu() {
        return gemoriu;
    }

    public void setGemoriu(String gemoriu) {
        this.gemoriu = gemoriu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GemFileEntity that = (GemFileEntity) o;

        return new EqualsBuilder()
                .append(gem01, that.gem01)
                .append(gem02, that.gem02)
                .append(gem03, that.gem03)
                .append(gem04, that.gem04)
                .append(gem05, that.gem05)
                .append(gem06, that.gem06)
                .append(gem07, that.gem07)
                .append(gem08, that.gem08)
                .append(gemacti, that.gemacti)
                .append(gemuser, that.gemuser)
                .append(gemgrup, that.gemgrup)
                .append(gemmodu, that.gemmodu)
                .append(gemdate, that.gemdate)
                .append(gem09, that.gem09)
                .append(gem10, that.gem10)
                .append(gem11, that.gem11)
                .append(gemorig, that.gemorig)
                .append(gemoriu, that.gemoriu)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(gem01)
                .append(gem02)
                .append(gem03)
                .append(gem04)
                .append(gem05)
                .append(gem06)
                .append(gem07)
                .append(gem08)
                .append(gemacti)
                .append(gemuser)
                .append(gemgrup)
                .append(gemmodu)
                .append(gemdate)
                .append(gem09)
                .append(gem10)
                .append(gem11)
                .append(gemorig)
                .append(gemoriu)
                .toHashCode();
    }
}
