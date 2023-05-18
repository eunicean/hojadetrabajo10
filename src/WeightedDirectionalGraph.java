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

    /**
     * Method to do the initial adjancency matriz
     * @param option int to determinate which climate is going to be used
     */
    public void doAdjacencyMatrix(int option){
        //initial preparations for the matrix
        AdjacencyMatrix = new String[vertices.size()+1][vertices.size()+1];
        TourMatrix = new String[vertices.size()+1][vertices.size()+1];
        for(int c=1;c<vertices.size()+1;c++){
            for(int f=1;f<vertices.size()+1;f++){
                AdjacencyMatrix[c][f] = "inf";
            }
        }
        int i=1;
        for (String city : vertices.keySet()){
            AdjacencyMatrix[0][i] = city;
            AdjacencyMatrix[i][0] = city;
            AdjacencyMatrix[i][i] = "0";
            i++;
        }

        int j=1;
        for(String city: vertices.keySet()){
            TourMatrix[0][j] = city;
            TourMatrix[j][0] = city;
            for(int k=1; k< vertices.size()+1;k++){
                TourMatrix[k][j] = city;
            }
        }

        //assigns initial values to adjacency matrix
        for(int x=1;x<vertices.size()+1;x++){
            ArrayList<Connection> relations = vertices.get(AdjacencyMatrix[x][0]).getNeighbors();
            int contNeighbors=0;
            for(int y=1; y<vertices.size()+1;y++){
                if(AdjacencyMatrix[0][y].equals(relations.get(contNeighbors))){
                    AdjacencyMatrix[x][y] = Integer.toString(relations.get(contNeighbors).getCurrentTravelTime()) ;
                }
            }
            contNeighbors++;
        }


    }

    /**
     * Method that does all the process corresponding to the Floyd Warshall algorithm
     */
    public void FloydAlgorithmProcess(){
        int numVertices = vertices.size();
        int[][] dist = new int[numVertices][numVertices];
        //creates a dist matrix that will only work the floyd algorithm
        for (int i = 1; i < numVertices+1; i++) {
            for (int j = 1; j < numVertices+1; j++) {
                if(AdjacencyMatrix[i][j].equals("ing")){
                    dist[i][j] = -1;
                }
                else {
                    dist[i][j] = Integer.parseInt(AdjacencyMatrix[i][j]);
                }
            }
        }
        //does the floyd algorithm and changes the values in tourMatrix
        int nameCity = 0;
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        nameCity = k;
                        TourMatrix[i+1][j+1] = AdjacencyMatrix[0][k+1];
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        //updates AdjacencyMatrix
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                AdjacencyMatrix[i][j] = Integer.toString(dist[i][j]);
            }
        }
    }

    /**
     * method that finds the shortest route
     * @param city1 string with the name of the city from where is being departed
     * @param city2 string with the name of the city to where is being traveled
     * @return string with the distance of the shortest route and all the cities that pass through
     */
    public String shortestRoute(String city1,String city2){
        String r = "";
        for(int i=0;i<AdjacencyMatrix[0].length; i++){
            for(int j=0;j<AdjacencyMatrix[0].length; j++){
                if (AdjacencyMatrix[0][i].equals(city2) && AdjacencyMatrix[j][0].equals(city1)){
                    r = "Shortest Route: " + AdjacencyMatrix[j][i] + "\nTour: [ " + getRoute(j,i);
                    break;
                }
            }
        }

        return r;
    }

    /**
     * method to do recursive process to get all teh cities between two
     * @param city1 int that indicate the position os the first city
     * @param city2 int that indicate the position os the second city, where we want to go
     * @return with the name of the city that is passed
     */
    public String getRoute(int city1,int city2){
        String r = "";

        r += TourMatrix[city1][city2] + " - ";
        if(!TourMatrix[city1][city2].equals(TourMatrix[0][city2])){
            String city = TourMatrix[city1][city2];
            int cityPos = 0;
            for(int i = 0 ; i < TourMatrix[0].length ; i++){
                if (TourMatrix[0][i].equals(city)){
                    cityPos = i;
                }
            }
            r+=getRoute(city1,cityPos);
        }
        else {
            r += " ]";
        }
        return r;
    }

    public void printAdjacencyMatrix(){
        for(String[] row:AdjacencyMatrix){
            System.out.println(Arrays.toString(row));
        }
    }
    public void printTourMatrix(){
        for(String[] row:TourMatrix){
            System.out.println(Arrays.toString(row));
        }
    }
}
