import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Filereader {
    public WeightedDirectionalGraph generateGraphFromFile() {
        String myFile = "src/logistica.txt";
        WeightedDirectionalGraph graph = new WeightedDirectionalGraph();

        try (BufferedReader reader = new BufferedReader(new FileReader(myFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineParts = line.split(" ");


                Vertex city1Vertex = new Vertex(lineParts[0]);
                Vertex city2Vertex = new Vertex(lineParts[1]);
                int normalTime = Integer.parseInt(lineParts[2]);
                int rainTime = Integer.parseInt(lineParts[3]);
                int snowTime = Integer.parseInt(lineParts[4]);
                int stormTime = Integer.parseInt(lineParts[5]);

                graph.addVertex(city1Vertex.getCity(), city1Vertex);
                graph.addVertex(city2Vertex.getCity(), city2Vertex);

                graph.addConnection(city1Vertex.getCity(), city2Vertex.getCity(), normalTime, rainTime, snowTime, stormTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Graph Generated");
        return graph;
    }
}