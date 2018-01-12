package entitys;

import javax.persistence.*;

@Entity
@Table(name = "testonetomanymany", schema = "oasystemdev")
public class TestonetomanymanyEntity {
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "subname")
    private String subname;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestonetomanymanyEntity that = (TestonetomanymanyEntity) o;

        if (id != that.id) return false;
        return subname != null ? subname.equals(that.subname) : that.subname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (subname != null ? subname.hashCode() : 0);
        return result;
    }
}
