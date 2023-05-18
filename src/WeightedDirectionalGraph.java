import java.util.*;
import java.util.HashMap;

public class WeightedDirectionalGraph{

    private HashMap<String,Vertex> vertices;
    private String[][] AdjacencyMatrix;
    private String[][] TourMatrix;
    //To do:
    //Method to make and show the adjacencyMatrix(they should be called in the main class after the file is read)
    //Method to make and show the matrix and tourMatrix (they should be called in the main class after the file is read)
    //Method to find the shortest rout between 2 cities
    //Method to find the center of the graph

    public WeightedDirectionalGraph(){
        vertices = new HashMap<String, Vertex>();
    }

    public void InitializeMatrices(){
        TourMatrix = new String[vertices.size()][vertices.size()];
    }

    public Vertex getVertex(String cityName) {
        return vertices.get(cityName);
    }

    public void addVertex(String cityName, Vertex cityVertex) {
        if (!vertices.containsKey(cityName)) {
            vertices.put(cityName, cityVertex);
        }
    }

    public void addConnection(String start, String end, int normalTime, int rainTime, int snowTime, int stormTime) {
        Vertex startVertex = vertices.get(start);
        Vertex endVertex = vertices.get(end);

        if (startVertex != null && endVertex != null) {
            Connection connection = new Connection(start, end, normalTime, rainTime, snowTime, stormTime);
            startVertex.addNeighbor(connection);

        } else {
            System.out.println("Could not make the connection");
        }
    }

    public void removeConnection(String start, String end){
        Vertex startVertex = vertices.get(start);
        Vertex endVertex = vertices.get(end);

        if(startVertex != null && endVertex != null){
            ArrayList<Connection> vertexNeighbors = startVertex.getNeighbors();
            Connection connectionToDelete = null;

            for (Connection currentConnection : vertexNeighbors) {
                if(currentConnection.getEndCity().equals(end)){
                    connectionToDelete = currentConnection;
                    break;
                }
            }

            if (connectionToDelete != null){
                startVertex.removeNeighbor(connectionToDelete);
            }

            else{
                System.out.println("Connection not found");
            }
        }
    }

    public void changeConnectionWight(String startCity, String endCity, String condition){
        Vertex startVertex = vertices.get(startCity);
        Vertex endVertex = vertices.get(endCity);

        if(startVertex != null && endVertex != null){
            ArrayList<Connection> vertexConnections = startVertex.getNeighbors();

            for (Connection currentConnection: vertexConnections){
                if(currentConnection.getEndCity().equals(endCity)){
                    currentConnection.updateCurrentTravelTime(condition);
                    break;
                }
            }
        }
    }

    public void doAdjacencyMatrix(int option){
        AdjacencyMatrix = new String[vertices.size()+1][vertices.size()+1];
        int i=1;
        for (String city : vertices.keySet()){
            AdjacencyMatrix[0][i] = city;
            AdjacencyMatrix[i][0] = city;
            i++;
        }
    }

}
