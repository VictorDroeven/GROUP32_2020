import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class BipartiteGraph {
	 enum Color{
        PINK, BLUE, PURPLE
    }
    private static int vertices;
    private static ArrayList<ArrayList<Integer>> adjacencyList;       

    public static boolean isBipartite(Graph graph) {
        vertices = graph.getGraphLength();
        adjacencyList = graph.adjacencyList;
        //check if graph is empty
        if (vertices == 0)
            return true;

        //initialize colors for all vertices
        Color colors[] = new Color[vertices];
        //color all the vertices with white color
        for (int i = 0; i < colors.length; i++) {
            colors[i] = Color.PINK;
        }

        Queue<Integer> queue = new LinkedList<>();
        //start coloring vertices , this code will handle the disconnected graph as well
        //color the first vertex with BLUE
        for (int begin = 0; begin < vertices; begin++) {

            if (colors[begin] == Color.PINK) {
                colors[begin] = Color.BLUE;

                //add it to queue for BFS

                queue.add(begin);

                while (!queue.isEmpty()) {
                    int v = queue.remove();
                    for (int i = 0; i < adjacencyList.get(v).size(); i++) {
                        int dest = adjacencyList.get(v).get(i);

                        if (colors[dest] == Color.PINK) {
                            //color is with the alternate color of vertex v

                            if (colors[v] == Color.BLUE) {
                                //if begin is in BLUE, make vertex dest PURPLE
                                colors[dest] = Color.PURPLE;
                            } else if (colors[v] == Color.PURPLE) {
                                //if begin is in BLUE, make vertex dest PURPLE
                                colors[dest] = Color.BLUE;
                            }
                            //add vertex dest to queue
                            queue.add(dest);
                        } else if (colors[v] == colors[dest]) {
                            //means vertex v and dest are in same color, so graph is not bipartite
                            return false;
                        }
                    }
                }
            }
        }
        // if here means graph is successfully colored in 2 color, red and PURPLE
        //graph is bipartite
        return true;
    }
    
}