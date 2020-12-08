

public class BackTrackingAlgo {

	// this method creates an array containing the color assigned to each vertex. and returns a boolean that say whether the given amount of colors is enough to color the entire graph.
	public static boolean backTrackingAlgorithm(Graph3 graph, int number, int graphLength) {
		int[] colors = new int[graphLength];
		for(int i = 0; i<colors.length; i++) {
			colors[i] = 0;
		}
		if(backTracker(graph, number, 0, colors)) {
			return true;
		}
		return false;
	}
	// main part of the algorithm, uses recursion to go down a search tree to find a possible combination of colors with the given max amount of colors.
	public static boolean backTracker(Graph3 graph, int number, int position, int[] colors) {
		if(position==colors.length) {
			return true;
		}
		for(int i=1; i<=number; i++) {
			if(possible(graph, i, position, colors)) {
				colors[position] = i;
				if(backTracker(graph, number, position+1, colors)) {
					return true;
				}
				colors[position]=0;
			}
		}
		return false;
	}
	// this method checks if the color chosen for a vertex is available in the sense that none of the vertices it has edges with, has that color already.
	public static boolean possible(Graph3 graph, int color, int position, int[] colors) {
		for(int i = 0; i<colors.length; i++) {
			if (graph.hasEdge(position, i)) {
				if(colors[i]== color) {
					return false;
				}
			}
		}
		return true;
	}
}