


public class RandomGraph {
	private static int[][] coordination = new int[1000][1000];
	private static int flag1 = 0;
	public static Graph3 graph3;
	public static void graphGenerator(int vertex, int edges) {
		int edges2 = edges;
		Graph3 graph = new Graph3(vertex);
		int[][] graph_2 = new int[vertex][vertex];
		for(int i = 0; i < vertex; i++) {
			for(int j = 0; j<vertex; j++) {
				graph_2[i][j] = 0;
			}
		}
		label1:
		for(int i = 0; i<vertex; i++) {
			for(int j=0; j<vertex; j++) {
				if(i!=j) {
					if(!graph.hasEdge(i,j)) {
						int chance = (int)(Math.random()*300);
						if(chance < 25) {
							graph.addEdge(i,j);
							graph_2[i][j] = 1;
							graph_2[j][i] = 1;
							edges--;
							if(edges == 0) {
								break label1;
							}
						}
					}
				}
			}
			if(i==vertex-1 && edges>0) {
				i = 0;
			}
		}
		
		
		DrawGraph frame = new DrawGraph(vertex, edges2, 1, graph);

		int[][] coordinates = new int[1000][1000];
		for(int i = 0; i<1000; i++) {
			for(int j = 0; j<1000; j++) {
				coordinates[i][j]=0;
				coordination[i][j]=0;
			}
		}

		
		int a = vertex;
		while(a>0){
			int aX = (int)(Math.random()*860)+80;
			int aY = (int)(Math.random()*860)+80;
			if(coordinates[aY][aX]==1 || coordinates[aY][aX]==2) {
				continue;
			}
			frame.addNode(aX,aY);
			for(int i = (aY-50/2); i<((aY-50/2)+50); i++) {
				for(int j = (aX-50/2); j<((aX-50/2)+50); j++) {
					coordinates[i][j] = 1;
				}
			}
			for(int i = aY-((DrawGraph.width/2)+2); i<aY+((DrawGraph.width/2)+2); i++) {
				for(int j = aX-((DrawGraph.height/2)+2); j<aX+((DrawGraph.height/2)+2); j++) {
					if(i==aY && j==aX) {
						coordination[i][j] = 2;
					}
					else coordination[i][j]=1;
				}
			}
			a--;
		}
		int[][] graph_3 = new int[vertex][vertex];
		for(int i = 0; i < vertex; i++) {
			for(int j = 0; j<vertex; j++) {
				graph_3[i][j] = 0;
			}
		}
		for(int i = 0; i< vertex; i++) {
			for(int j = 0; j<vertex; j++) {
				if(i!=j) {
					if(graph_3[i][j]!=1) {
						if(graph.hasEdge(i, j)) {
							frame.addEdge(i, j);
							graph_3[i][j]= 1;
							graph_3[j][i]= 1;
						}
					}
				}
			}
		}
		if(StartWindow.flagGameMode == 1) {
			long time = System.currentTimeMillis();
			graph3 = graph;
			ToBitterEnd.setCR(graph); 
			ToBitterEnd.setTime(time);
			for(int i =0; i<graph.getGraphLength();i++){
				if(BackTrackingAlgo.backTrackingAlgorithm(graph, i, graph.getGraphLength())){
					frame.setChromaticNumber(i);
					break;
				}
			}
		}
		else if(StartWindow.flagGameMode == 2) {
			graph3 = graph;
			frame.setTimerLength(StartWindow.timer);
			ColEdge[] e = UpperBound.setUpperBound(graph, edges2, vertex);
			frame.setColEdge(e);
			frame.setChromaticNumber(WelshPowell.welshPowellAlgorithm(e, e.length));
			frame.countdownTimer();
			frame.start();
		}
		else if(StartWindow.flagGameMode==3) {
			for(int i =0; i<graph.getGraphLength();i++){
				if(BackTrackingAlgo.backTrackingAlgorithm(graph, i, graph.getGraphLength())){
					frame.setChromaticNumber(i);
					break;
				}
			}
			frame.createRandomEmptyArray();
			frame.randomNodeSelection();
		}
	}
	public static boolean check(int i, int j, ColEdge[] d, int current) {
		if(current>0 && i>j) {
			for(int k = 0; k<current; k++) {
				if((d[k].u==j&&d[k].v==i)) {
					return false;
				}
			}
		}
		return true;
	}
	public static int[][] getCords(){
		return coordination;
	}
	public static int getFlag() {
		return flag1;
	}
	public static void changeFlag(int x) {
		flag1 = x;
	}
}