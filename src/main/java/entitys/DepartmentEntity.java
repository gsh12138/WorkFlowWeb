package entitys;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;

/**
 * Created by tech on 2017/12/21.
 */
@Entity
@Table(name = "department", schema = "oasystemdev", catalog = "")
public class DepartmentEntity {
    private int id;
    private String departmentid;
    private String departmentname;
    private Integer departmentrole;
    private String charge;
    private String superior;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "departmentid")
    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    @Basic
    @Column(name = "departmentname")
    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    @Basic
    @Column(name = "departmentrole")
    public Integer getDepartmentrole() {
        return departmentrole;
    }

    public void setDepartmentrole(Integer departmentrole) {
        this.departmentrole = departmentrole;
    }

    @Basic
    @Column(name = "charge")
    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    @Basic
    @Column(name = "superior")
    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DepartmentEntity that = (DepartmentEntity) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(departmentid, that.departmentid)
                .append(departmentname, that.departmentname)
                .append(departmentrole, that.departmentrole)
                .append(charge, that.charge)
                .append(superior,that.superior)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(departmentid)
                .append(departmentname)
                .append(departmentrole)
                .append(charge)
                .append(superior)
                .toHashCode();
    }
}
