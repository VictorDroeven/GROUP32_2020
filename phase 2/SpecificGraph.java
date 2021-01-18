

public class SpecificGraph {
	private static int[][] coordination = new int[1000][1000];
	public static Graph3 graph3;
	public static void graphViewer(Graph3 graph,int vertex, int edges, ColEdge[] e) {
		RandomGraph.changeFlag(1);
		DrawGraph frame = new DrawGraph(vertex, edges, 2, graph);

		int[][] coordinates = new int[1000][1000];
		for(int i = 0; i<1000; i++) {
			for(int j = 0; j<1000; j++) {
				coordinates[i][j]=0;
				coordination[i][j]=0;
			}
		}
		int a = vertex;
		while(a>0){
			//if()
			int aX = (int)(Math.random()*860)+80;
			int aY = (int)(Math.random()*860)+80;
			if(coordinates[aY][aX]==1) {
				continue;
			}
			frame.addNode(aX,aY);
			for(int i = (aY-50/2); i<((aY-50/2)+50); i++) {
				for(int j = (aX-50/2); j<((aX-50/2)+50); j++) {
					coordinates[i][j] = 1;
				}
			}
			for(int i = aY-7; i<aY+7; i++) {
				for(int j = aX-7; j<aX+7; j++) {
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
			frame.setColEdge(e);
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
			UpperBound.setUpperBound(graph, edges, vertex);
			frame.setChromaticNumber(WelshPowell.welshPowellAlgorithm(e, e.length));
			frame.setColEdge(e);
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
			frame.setColEdge(e);
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
}
