package cz.osu.db.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SENSOR", schema = "SA")
public class SensorEntity {
    private int id;
    private String type;
    private Integer room;
    private RoomEntity roomByRoom;

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
    @Column(name = "TYPE", nullable = true, length = 30)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "ROOM", nullable = true)
    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorEntity that = (SensorEntity) o;
        return id == that.id &&
                Objects.equals(type, that.type) &&
                Objects.equals(room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, room);
    }

    @ManyToOne
    @JoinColumn(name = "ROOM", referencedColumnName = "ID")
    public RoomEntity getRoomByRoom() {
        return roomByRoom;
    }

    public void setRoomByRoom(RoomEntity roomByRoom) {
        this.roomByRoom = roomByRoom;
    }
}
