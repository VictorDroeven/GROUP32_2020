/**
* @author Group 32
*/
import java.io.*;
import java.util.*;
	class ColEdge
			{
			int u;
			int v;
			}
			
	public class ChromaticNumber
		{
		
		public final static boolean DEBUG = false;
		
		public final static String COMMENT = "//";
		
		public static void main( String args[] ){
			/**
			* the for loop makes sure that the main method goes through all the
			* files that it gets inputed through the terminal window.
			*/
			long start = System.currentTimeMillis();
			if( args.length < 1 )
					{
						System.out.println("Error! No filename specified.");
						System.exit(0);
					}
			for(int loops = 0; loops<args.length; loops++){				
				
				String inputfile = args[loops];
			
				boolean seen[] = null;
			
				//! n is the number of vertices in the graph
				int n = -1;
			
				//! m is the number of edges in the graph
				int m = -1;
			
				//! e will contain the edges of the graph
				ColEdge e[] = null;
			
				try { 
					FileReader fr = new FileReader(inputfile);
					BufferedReader br = new BufferedReader(fr);

					String record = new String();
					
					//! THe first few lines of the file are allowed to be comments, staring with a // symbol.
					//! These comments are only allowed at the top of the file.
					
					//! -----------------------------------------
					while ((record = br.readLine()) != null)
						{
							if( record.startsWith("//") ) continue;
							break; // Saw a line that did not start with a comment -- time to start reading the data in!
					}
	
					if( record.startsWith("VERTICES = ") )
					{
						n = Integer.parseInt( record.substring(11) );					
						if(DEBUG) System.out.println(COMMENT + " Number of vertices = "+n);
					}

					seen = new boolean[n+1];	
						
					record = br.readLine();
					
					if( record.startsWith("EDGES = ") )
					{
						m = Integer.parseInt( record.substring(8) );					
						if(DEBUG) System.out.println(COMMENT + " Expected number of edges = "+m);
					}

					e = new ColEdge[m];	
													
					for( int d=0; d<m; d++)
					{
						if(DEBUG) System.out.println(COMMENT + " Reading edge "+(d+1));
						record = br.readLine();
						String data[] = record.split(" ");
						if( data.length != 2 )
							{
							System.out.println("Error! Malformed edge line: "+record);
							System.exit(0);
						}
						e[d] = new ColEdge();
						
						e[d].u = Integer.parseInt(data[0]);
						e[d].v = Integer.parseInt(data[1]);

						seen[ e[d].u ] = true;
						seen[ e[d].v ] = true;
						
						if(DEBUG) System.out.println(COMMENT + " Edge: "+ e[d].u +" "+e[d].v);
				
					}
									
					String surplus = br.readLine();
					if( surplus != null )
					{
						if( surplus.length() >= 2 ) if(DEBUG) System.out.println(COMMENT + " Warning: there appeared to be data in your file after the last edge: '"+surplus+"'");						
					}
					
				}
				catch (IOException ex)
				{ 
					// catch possible io errors from readLine()
					System.out.println("Error! Problem reading file "+inputfile);
					System.exit(0);
				}

				for( int x=1; x<=n; x++ )
				{
					if( seen[x] == false )
					{
						if(DEBUG) System.out.println(COMMENT + " Warning: vertex "+x+" didn't appear in any edge : it will be considered a disconnected vertex on its own.");
					}
				}
				//! At this point e[0] will be the first edge, with e[0].u referring to one endpoint and e[0].v to the other
				//! e[1] will be the second edge...
				//! (and so on)
				//! e[m-1] will be the last edge
				//! 
				//! there will be n vertices in the graph, numbered 1 to n
					
				//! INSERT YOUR CODE HERE!
				/*
				this short part of the program creates a graph variable using the graph class.
				Then fills it with all the edges given to it by the first part of the main method.
				Finally it prints out a statement with the greedyColoringAlgorithm method in it.
				*/
				Graph graph1 = new Graph(n);
				for(int i = 0; i<m; i++){
					graph1.addEdge((e[i].u-1), (e[i].v-1));
				}
				System.out.println("Welsh-Powell algorithm: graph "+(loops+1)+" is: "+welshPowellAlgorithm(e,m));					
				System.out.println("Simple greedy Algorithm: graph "+(loops+1)+" is: "+greedyColoringAlgorithm(graph1, n));
				System.out.println();
			}
			long end = System.currentTimeMillis()-start;
			System.out.print("It took " + end + " milliseconds.");
		}
		
		/*
		this method receives the graph we have created in the main
		method and the amount of vertices present in the graph.
	 
		the variable AmountOfColors is what will be returned after the method is done calculating, it is the amount of colors used.
		the variable ColorsArray; this array will store what color each vertex has.
		the variable AvailableColors: this array will be used to check which color is available to color a certain vertex.
		 
		The ColorsArray array is filled with -1 to indicate that no vertex has got a color yet.
		
		the availableColors array is filled with true since if there 
		is no edge that has already been colored, all colors are available.
		
		the first for loop decided which vertex you are looking at.
		the while loop then check for the color of all of its neighbours.
		then the next for loop looks for the first available color.
		that color is than assigned to the vertex in the ColorsArray.
		and finally, if the color(integer) is bigger than the current biggest
		color (integer) it replaces the current biggest.
		this repeats itself for every single vertex.
		then the method ends by returning the highest color(integer) plus 1 since the coloring starts at integer 0.
		*/
		public static int greedyColoringAlgorithm(Graph graph1, int n){
			int AmountOfColors = 0;
			int ColorsArray[] = new int[n];	 
			boolean availableColors[] = new boolean[n];
			
			Arrays.fill(ColorsArray, -1);
			Arrays.fill(availableColors, true);
			
			ColorsArray[0] = 0; 
			for(int i = 1; i<n; i++){
				Iterator<Integer> it = graph1.neighbours(i).iterator();
				while(it.hasNext()){
					int j = it.next();
					if(ColorsArray[j] != -1){
						availableColors[ColorsArray[j]] = false;
					}
				}
				int j;
				for(j = 0; j < n; j++){
					if(availableColors[j]){
						break;
					}
				}
				ColorsArray[i] = j;
				Arrays.fill(availableColors, true);
				if(j+1> AmountOfColors){
					AmountOfColors = j+1;
				}
			}
			return AmountOfColors;
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
	
	/*
	this class creates an arraylist of given length n.
	the length n indicates the amount of vertices in the graph.
	then for every vertices of the graph. It places the edges
	that the vertex has.
	
	this class also contains a addEdge method
	this method is the one that puts the edges in the correct
	correct position in the arraylist. but since the arraylist
	starts at index 0 and not index 1, the vertex 1 = at index 0
	so you always subtract 1 from the actual vertex to find its
	position in the arraylist.
	
	the neighbours method simply returns all the edges it
	has got saved in that spot in the arraylist.
	
	the variable graphLength is the amount of vertices and thus the length that the adjacencyList will have.
	the variable adjacencyList this is the representation of the vertices and edges in the form of an Arraylist.
	*/
	class Graph {
			int graphLength;
			List<Integer>[] adjacencyList;
			public Graph(int graphLength) {
				this.graphLength = graphLength;
				adjacencyList = (List<Integer>[]) new List[graphLength];
				for(int i = 0; i < graphLength; i++){
					adjacencyList[i] = new ArrayList<Integer>();
				}
			}
			public void addEdge(int i, int j) {
				adjacencyList[i].add(j);
				adjacencyList[j].add(i);
			}
			public List<Integer> neighbours(int vertex) {
				return adjacencyList[vertex];
			}
		}
		
