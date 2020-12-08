

import java.awt.Color;
import java.util.ArrayList;

public class UpperBound {
	private static int cr;
    
    public static boolean able2Color(Graph3 graph, int x, int y, Color c, ArrayList<Node> nodes){

        for(int i = 0; i < graph.getGraphLength(); i++) {
            if ((nodes.get(i).x - DrawGraph.width/2) == x && (nodes.get(i).y - DrawGraph.height/2) == y) {
                for(int j = 0; j < graph.getGraphLength(); j++){
                    if(i != j){
                        if(graph.hasEdge(i,j) && c == nodes.get(j).c){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public static boolean isFinished(ArrayList<Node> nodes, int cls){
        if(cls <= cr){
            for(Node i : nodes){
                if(i.c == Color.BLACK){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
	public static ColEdge[] setUpperBound(Graph3 graph, int edges, int vertex) {
		ColEdge[] d = null;
		d = new ColEdge[edges];
		int k = 0;
		label:
		for(int i = 0; i<vertex; i++) {
			for(int j = 0; j<vertex; j++) {
				if(i!=j) {
					if(graph.hasEdge(i, j)&& check(i,j,d,k)) {
						d[k] = new ColEdge();
						d[k].u = i;
						d[k].v = j;
						k++;
						if(k==edges) {
							break label;
						}
					}
				}
			}
		}
		
		cr = WelshPowell.welshPowellAlgorithm(d, edges);
		System.out.println("Welsh-Powell algorithm is: "+cr);
		return d;
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
}

