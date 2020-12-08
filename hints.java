import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class hints {
    private static ArrayList<Hintresponse> a;
    private static int[] finalAnswer;
    private static int amountOfNodes;
    private static int[] finalAnswerWelshPowell;
    public static Hintresponse generateHint(Color[] nodeColors, ArrayList<Color> color, Graph3 g, ColEdge[] e, ArrayList<Edge> edges, ArrayList<Node> nodes){
        int[] graphColors = convertToUseable(nodeColors, color);
        amountOfNodes = nodes.size();
        a = new ArrayList<Hintresponse>();
        a.clear();
        if(StartWindow.flagGameMode==1){
            for(int i =0; i<nodeColors.length;i++){
                if(backTrackingAlgorithm(g, i, graphColors)){
                    for(int j = 0; j<graphColors.length;j++){
                        if(graphColors[j]!=finalAnswer[j]){
                            int amountOfEdges = 0;
                            for(Edge edge : edges){
                                if(edge.a == j){
                                    amountOfEdges++;
                                }
                                else if (edge.b == j){
                                    amountOfEdges++;
                                }
                            }
                            a.add(new Hintresponse(j, nodeColors[j], amountOfEdges));
                        }
                    }
                    int indexBiggest=-1;
                    for(int k = 0; k<a.size();k++){
                        if(indexBiggest==-1){
                            indexBiggest=k;
                        }
                        else if(a.get(k).amountOfEdges> a.get(indexBiggest).amountOfEdges){
                            indexBiggest = k;
                        }
                    }
                    ArrayList<Color> newColors = new ArrayList<Color>();
                    for(int l = 0; l<DrawGraph.colorAvailable.length;l++){
                        newColors.add(DrawGraph.colorAvailable[l]);
                    }
                    for(int m = 0; m<color.size();m++){
                        for(int l = 0; l<newColors.size(); l++){
                            if(newColors.get(l).equals(color.get(m))){
                                newColors.remove(l);
                            }
                        }
                    }
                    if(finalAnswer[a.get(indexBiggest).index]>color.size()){  
                        a.get(indexBiggest).givenColor = newColors.get(0);
                        return a.get(indexBiggest);
                    }
                    else{
                        a.get(indexBiggest).givenColor = color.get(finalAnswer[a.get(indexBiggest).index]-1);
                        return a.get(indexBiggest);
                    }
                }
            }
        }
        else if(StartWindow.flagGameMode==2){
            welshPowellAlgorithm(e, e.length);
            for(int j = 0; j<graphColors.length;j++){
                if(graphColors[j]!=finalAnswerWelshPowell[j]){
                    int amountOfEdges = 0;
                    for(Edge edge : edges){
                        if(edge.a == j){
                            amountOfEdges++;
                        }
                        else if (edge.b == j){
                            amountOfEdges++;
                        }
                    }
                    a.add(new Hintresponse(j, nodeColors[j], amountOfEdges));
                }
            }
            int indexBiggest=-1;
            for(int k = 0; k<a.size();k++){
                if(indexBiggest==-1){
                    indexBiggest=k;
                }
                else if(a.get(k).amountOfEdges> a.get(indexBiggest).amountOfEdges){
                    indexBiggest = k;
                }
            }
            ArrayList<Color> newColors = new ArrayList<Color>();
            for(int l = 0; l<DrawGraph.colorAvailable.length;l++){
                newColors.add(DrawGraph.colorAvailable[l]);
            }
            for(int m = 0; m<color.size();m++){
                for(int l = 0; l<newColors.size(); l++){
                    if(newColors.get(l).equals(color.get(m))){
                        newColors.remove(l);
                    }
                }
            }
            if(finalAnswerWelshPowell[a.get(indexBiggest).index]>color.size()){  
                a.get(indexBiggest).givenColor = newColors.get(0);
                return a.get(indexBiggest);
            }
            else{
                a.get(indexBiggest).givenColor = color.get(finalAnswerWelshPowell[a.get(indexBiggest).index]-1);
                return a.get(indexBiggest);
            }
            
        }
        else if(StartWindow.flagGameMode==3){
            for(int i =0; i<nodeColors.length;i++){
                if(backTrackingAlgorithm(g, i, graphColors)){
                    for(int j = 0; j<graphColors.length;j++){
                        if(graphColors[j]!=finalAnswer[j]){
                            int amountOfEdges = 0;
                            for(Edge edge : edges){
                                if(edge.a == j){
                                    amountOfEdges++;
                                }
                                else if (edge.b == j){
                                    amountOfEdges++;
                                }
                            }
                            a.add(new Hintresponse(j, nodeColors[j], amountOfEdges));
                        }
                    }
                    int indexChosen = 0;
                    for(Node n : nodes){
                        if(n.chosen){
                            break;
                        }
                        indexChosen++;
                    }
                    int indexcheck = -1;
                    for(int l = 0; l<a.size();l++){
                        if(a.get(l).index==indexChosen){
                            indexcheck=l;
                        }
                    }
                    ArrayList<Color> newColors = new ArrayList<Color>();
                    for(int l = 0; l<DrawGraph.colorAvailable.length;l++){
                        newColors.add(DrawGraph.colorAvailable[l]);
                    }
                    for(int m = 0; m<color.size();m++){
                        for(int l = 0; l<newColors.size(); l++){
                            if(newColors.get(l).equals(color.get(m))){
                                newColors.remove(l);
                            }
                        }
                    }
                    if(finalAnswer[a.get(indexcheck).index]>color.size()){  
                        a.get(indexcheck).givenColor = newColors.get(0);
                        return a.get(indexcheck);
                    }
                    else{
                        a.get(indexcheck).givenColor = color.get(finalAnswer[a.get(indexcheck).index]-1);
                        return a.get(indexcheck);
                    }
                }
            }
        }
        return null;
    }
    public static int[] convertToUseable(Color[] nodeColors, ArrayList<Color> color){
        int[] a = new int[nodeColors.length];
        for(int i = 0; i<nodeColors.length;i++){
            if(nodeColors[i]==Color.BLACK){
               a[i]=0;
            }
            else{
                for(int j = 0; j<color.size();j++){
                    if(nodeColors[i].equals(color.get(j))){
                        a[i]=j+1;
                        break;
                    }
                }
            }
        }
        return a;
    }

    // this method creates an array containing the color assigned to each vertex. and returns a boolean that say whether the given amount of colors is enough to color the entire graph.
	public static boolean backTrackingAlgorithm(Graph3 graph, int number, int[] base) {
        int[] colors = new int[base.length];
        for(int i = 0; i<colors.length;i++){
            colors[i]=base[i];
        }
		if(backTracker(graph, number, 0, colors)) {
			return true;
		}
		return false;
	}
	// main part of the algorithm, uses recursion to go down a search tree to find a possible combination of colors with the given max amount of colors.
	public static boolean backTracker(Graph3 graph, int number, int position, int[] colors) {
		if(position==colors.length) {
            finalAnswer = colors;
			return true;
		}
		for(int i=1; i<=number; i++) {
            if(colors[position]!=0){
                if(backTracker(graph, number, position+1, colors)){
                    return true;
                }
            }
            else{
			    if(possible(graph, i, position, colors)) {
				    colors[position] = i;
				    if(backTracker(graph, number, position+1, colors)) {
					    return true;
    				}
	    			colors[position]=0;
                }
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
    
    public static int welshPowellAlgorithm(ColEdge[] e, int m) {
		HashMap<Integer,Integer> map = new HashMap<>(); // using hashmap to store the ID of every vertex as the key and their degree as the value
		for (int i=0; i<m; i++) {
			if(map.containsKey(e[i].u)){               // check for every edge if one endpoint value(vertex ID) is already stored as a key in the map
				map.put(e[i].u, map.get(e[i].u) + 1);  // if it is, add one to the value of that key to count its degree
			}
			else{
				map.put(e[i].u,1);   // if the vertex ID is not stored as key, store it and insert 1 as its value
			}

			if(map.containsKey(e[i].v)){               // do the same for the other endpoint value for every edge
				map.put(e[i].v, map.get(e[i].v) + 1);
			}
			else {
				map.put(e[i].v, 1);
			}
		}
		LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>(); // creating a linked hash map to store the sorted key:value pairs
		map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEachOrdered(x -> sortedMap.put(x.getKey(),x.getValue()));
		// since hashmap doesn't maintain a particular order for the stored keys and values
		// we first sort the hash map in descending order and insert the key:value pairs to the linked hash map

		@SuppressWarnings("rawtypes")
		ArrayList<ArrayList> coloredIDs = new ArrayList<>(); // creating an array list inside an array list to store every color with their vertices inside
		ArrayList<Integer> alreadyColoredVertices = new ArrayList<>();  // creating an array list to store vertices that are colored
		for(Object i : sortedMap.keySet().toArray()){ // for every vertex in an array of vertices starting from the highest degree
			if(!alreadyColoredVertices.contains(i)){  // this condition is for the first vertex of a new color            // check if the vertex is not colored
				ArrayList<Integer> newColor = new ArrayList<>(); 										// if not create an array list for a new color
				newColor.add((int)i);                        								// add the uncolored vertex to the array for that color
				alreadyColoredVertices.add((int)i);          						// add the vertex to already colored vertices

				for(Object j: sortedMap.keySet().toArray()){     // run a nested for loop with the same statement
					boolean isAdjacent = false;  // boolean variable to store our adjacency info
					if(!alreadyColoredVertices.contains(j)) {    // check if the vertex is not colored
						for(Integer k : newColor){               										// if not check for every vertex of the same color
							for (ColEdge ce : e) {               										// and for every edge in our graph
								if ((ce.u == k && ce.v == (int) j) || (ce.v == k && ce.u == (int) j)) { // if the current uncolored vertex is adjacent to any of the vertices with the same color
									isAdjacent = true; 													// set isAdjacent to true
									break;																// and break the loop because there's no need to check the other edges
								}
							}
							if(isAdjacent){  // if isAdjacent is true we will not color the vertex and skip it
								break;
							}
						}
						if(!isAdjacent){                             // check if the vertex is not adjacent
							alreadyColoredVertices.add((int)j);  // then add the vertex to already colored vertices to only process the uncolored vertices in the next loops
							newColor.add((int)j);    // and color the vertex with the same color
						}
					}
				}
				coloredIDs.add(newColor);  // add the array list of vertices with the same color to coloredIDs
			}
        }
        finalAnswerWelshPowell = new int[amountOfNodes];
        for(int i = 0; i<amountOfNodes; i++){
            label:
            for(int j = 0; j<coloredIDs.size();j++){
                for(int k = 0; k<coloredIDs.get(j).size();k++){
                    if((int)coloredIDs.get(j).get(k)==i){
                        finalAnswerWelshPowell[i]=j+1;
                        break label;
                    }
                }
            }   
        }
        for(int i = 0; i<finalAnswerWelshPowell.length;i++){
            if(finalAnswerWelshPowell[i]==0){
                finalAnswerWelshPowell[i]=1;
            }
        }
		return 	coloredIDs.size(); // returns the chromatic(!) number
	}
}
class Hintresponse{
    int index;
    Color givenColor;
    int amountOfEdges;
    String choice;
    public Hintresponse(int aIndex, Color aColor, int aamountofedges){
        index =aIndex;
        givenColor = aColor;
        amountOfEdges = aamountofedges;
    }
}