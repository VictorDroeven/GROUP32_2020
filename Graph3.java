
import java.util.*;

public class Graph3 {
	int[] Cv;
	int graphLength;
	List<Integer>[] adjacencyList;
	@SuppressWarnings("unchecked")
	public Graph3(int graphLength) {
		this.graphLength = graphLength;
		this.Cv = new int[graphLength];
		adjacencyList = (List<Integer>[]) new List[graphLength];
		for(int i = 0; i < graphLength; i++){
			adjacencyList[i] = new ArrayList<Integer>();
		}
	}
	public void addEdge(int i, int j) {
		adjacencyList[i].add(j);
		adjacencyList[j].add(i);
	}
	public boolean hasEdge(int i, int j) {
		return adjacencyList[i].contains(j);
	}
	public int getGraphLength() {
		return graphLength;
	}
	public static Graph3 graphConverter(int vertex, ColEdge[] edge, int length) {
		Graph3 g = new Graph3(vertex);
		int k = 0;
		while(k<length) {
			g.addEdge(edge[k].u-1, edge[k].v-1);
			k++;
		}
		return g;
	}
}