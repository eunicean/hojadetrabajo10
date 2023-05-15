import java.util.ArrayList;

public class Vertex {
    private String City;
    private ArrayList<Connection> neighbors;

    public Vertex(String city) {
        this.City = city;
        this.neighbors = new ArrayList<>();
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public ArrayList<Connection> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(ArrayList<Connection> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbor(Connection connection) {
        neighbors.add(connection);
    }

    public void removeNeighbor(Connection connection) {
        neighbors.remove(connection);
    }
}
