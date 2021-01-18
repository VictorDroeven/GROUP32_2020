
import java.util.*;

public class Graph {
	int graphLength;
	ArrayList<ArrayList<Integer>> adjacencyList;
	public Graph(int graphLength) {
		this.graphLength = graphLength;
		adjacencyList = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < graphLength; i++){
			adjacencyList.add(new ArrayList<Integer>());
		}
	}
	public Graph(ArrayList<ArrayList<Integer>> adjacencyList, int vertex){
		this.adjacencyList = adjacencyList;
		this.graphLength= vertex;
	}
	public void addEdge(int i, int j) {
		adjacencyList.get(i).add(j);
		adjacencyList.get(j).add(i);
	}
	public boolean hasEdge(int i, int j) {
		return adjacencyList.get(i).contains(j);
	}
	public int getGraphLength() {
		return graphLength;
	}
	public static Graph graphConverter(int vertex, ColEdge[] edge, int length) {
		Graph g = new Graph(vertex);
		int k = 0;
		while(k<length) {
			g.addEdge(edge[k].u-1, edge[k].v-1);
			k++;
		}
		return g;
	}
}