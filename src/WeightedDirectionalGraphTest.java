/**import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//Initial JTests for the directional graph class.
public class WeightedDirectionalGraphTest {

    private WeightedDirectionalGraph graph;

    @BeforeEach
    public void setUp() {
        graph = new WeightedDirectionalGraph();
        // Add vertices and connections for testing
        Vertex v1 = new Vertex("City1");
        Vertex v2 = new Vertex("City2");
        graph.addVertex("City1", v1);
        graph.addVertex("City2", v2);
        graph.addConnection("City1", "City2", 10, 20, 30, 40);
    }

    @Test
    public void testAddVertex() {
        Vertex v3 = new Vertex("City3");
        graph.addVertex("City3", v3);

        assertNotNull(graph.getVertex("City3"));
    }

    @Test
    public void testAddConnection() {
        Vertex startVertex = graph.getVertex("City1");
        Vertex endVertex = graph.getVertex("City2");

        assertNotNull(startVertex);
        assertNotNull(endVertex);
        assertEquals(1, startVertex.getNeighbors().size());
        assertEquals(0, endVertex.getNeighbors().size());
    }

    @Test
    public void testRemoveConnection() {
        graph.removeConnection("City1", "City2");

        Vertex startVertex = graph.getVertex("City1");
        Vertex endVertex = graph.getVertex("City2");

        assertNotNull(startVertex);
        assertNotNull(endVertex);
        assertEquals(0, startVertex.getNeighbors().size());
        assertEquals(0, endVertex.getNeighbors().size());
    }

    @Test
    public void testChangeConnectionWeight() {
        graph.changeConnectionWight("City1", "City2", "Rain");

        Vertex startVertex = graph.getVertex("City1");
        assertNotNull(startVertex);
        assertEquals(1, startVertex.getNeighbors().size());

        Connection connection = startVertex.getNeighbors().get(0);
        assertEquals(20, connection.getCurrentTravelTime());
    }
}*/