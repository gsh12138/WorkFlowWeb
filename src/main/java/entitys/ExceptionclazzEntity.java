package entitys;

import javax.persistence.*;

@Entity
@Table(name = "exceptionclazz", schema = "oasystemdev", catalog = "")
public class ExceptionclazzEntity {
    private int id;
    private String clazzid;
    private String name;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "clazzid")
    public String getClazzid() {
        return clazzid;
    }

    public void setClazzid(String clazzid) {
        this.clazzid = clazzid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExceptionclazzEntity that = (ExceptionclazzEntity) o;

        if (id != that.id) return false;
        if (clazzid != null ? !clazzid.equals(that.clazzid) : that.clazzid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (clazzid != null ? clazzid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
