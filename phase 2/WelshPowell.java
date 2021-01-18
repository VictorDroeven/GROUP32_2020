

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WelshPowell {
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
		return 	coloredIDs.size(); // returns the chromatic(!) number
	}
}
