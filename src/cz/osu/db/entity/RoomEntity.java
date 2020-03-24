package cz.osu.db.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ROOM", schema = "SA", catalog = "")
public class RoomEntity {
    private int id;
    private String name;
    private double temp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 30)
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
        RoomEntity entity = (RoomEntity) o;
        return id == entity.id &&
                Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Basic
    @Column(name = "TEMP", nullable = false, precision = 0)
    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
