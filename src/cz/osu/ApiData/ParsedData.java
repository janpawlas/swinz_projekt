package cz.osu.ApiData;

public class ParsedData {
    private int id;
    private String name;
    private int roomId;
    private double data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public ParsedData(int id, String name, int roomId, double data) {
        this.id = id;
        this.name = name;
        this.roomId = roomId;
        this.data = data;
    }
}
