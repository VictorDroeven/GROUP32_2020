import java.util.*;

public class G {
    private ArrayList<ArrayList<Integer>> adjacencyList;
    private int vertex;
    private ArrayList<ArrayList<Integer>> vertexColorsArrayList;
    private Graph graph;
    public G(int vertex, Graph graph){
        this.vertex=vertex;
        this.graph = graph;
        // contains all the sets of vertex colors available
        vertexColorsArrayList = new ArrayList<ArrayList<Integer>>();
        adjacencyList = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < graph.adjacencyList.size(); i++){
			adjacencyList.add(new ArrayList<Integer>());
		}
        
        for(int i = 0; i<graph.adjacencyList.size(); i++){
            for(int j =0; j<graph.adjacencyList.get(i).size();j++){
                this.adjacencyList.get(i).add(graph.adjacencyList.get(i).get(j));
            }
        }
        for(int i = 0; i<vertex; i++){
            ArrayList<Integer> x = new ArrayList<Integer>();
            for(int j = 1; j<=vertex; j++){
                x.add(j);
            }
            vertexColorsArrayList.add(x);
        }
    }
    public G(int vertex, Graph graph, ArrayList<ArrayList<Integer>> vertexcolors){
        this.vertex = vertex;
        vertexColorsArrayList = new ArrayList<ArrayList<Integer>>();
        adjacencyList = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i < graph.adjacencyList.size(); i++){
			adjacencyList.add(new ArrayList<Integer>());
		}
        
        for(int i = 0; i<graph.adjacencyList.size(); i++){
            for(int j =0; j<graph.adjacencyList.get(i).size();j++){
                this.adjacencyList.get(i).add(graph.adjacencyList.get(i).get(j));
            }
        }
        
        this.vertexColorsArrayList = vertexcolors;

    }
    public void removeVertex(int ToBeRemoved){
        for(int i = 0; i<adjacencyList.size(); i++){
            if(adjacencyList.get(i).contains(ToBeRemoved)){
                for(int j = 0; j<adjacencyList.get(i).size(); j++){
                    if(adjacencyList.get(i).get(j)==ToBeRemoved){
                        adjacencyList.get(i).remove(j);
                        break;
                    }
                }
            }
        }
        adjacencyList.remove(ToBeRemoved);
        for(int i = 0; i<adjacencyList.size(); i++){
            for(int j = 0; j<adjacencyList.get(i).size(); j++){
                if(adjacencyList.get(i).get(j)>ToBeRemoved){
                    adjacencyList.get(i).set(j,adjacencyList.get(i).get(j)-1);
                }
            }
        }
        vertexColorsArrayList.remove(ToBeRemoved);
        vertex = vertex-1;
    }
    public int getVertex(){
        return vertex;
    }
    public ArrayList<ArrayList<Integer>> getVertexColors(){
        return vertexColorsArrayList;
    }
    public boolean hasEdge(int i, int j) {
		return adjacencyList.get(i).contains(j);
    }
    public void removeColor(int vertex, int color){
        if(vertexColorsArrayList.get(vertex).contains(color)){
            for(int j = 0; j<vertexColorsArrayList.get(vertex).size(); j++){
                if(vertexColorsArrayList.get(vertex).get(j)==color){
                    vertexColorsArrayList.get(vertex).remove(j);
                }
            }
        }
    }
    public void addColor(int vertex, int color){
        vertexColorsArrayList.get(vertex).add(color);
    }
    public Graph getGraph(){
        return graph;
    }
    public ArrayList<ArrayList<Integer>> getAdjacencyLists(){
        return adjacencyList;
    }
}
