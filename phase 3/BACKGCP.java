import java.util.ArrayList;

public class BACKGCP {
    private static int chosenVertex;
    public static int backGCP(G g, int k, int UB, int lowerbound){
        if(lowerbound==UB){
            return UB;
        }
        if(g.getVertex()==0){
            return k;
        }
        if(!check(g,UB)){
            return UB;
        }
        chosenVertex = dsatur(g, UB);
        for(int i = 0; i<g.getVertexColors().get(chosenVertex).size() && g.getVertexColors().get(chosenVertex).get(i)<UB;i++){
            for(int j = 0; j<g.getVertex(); j++){
                if(g.hasEdge(chosenVertex, j)){
                    g.removeColor(j, g.getVertexColors().get(chosenVertex).get(i));
                }
            }
            for(int j = 0; j<g.getVertexColors().size(); j++){
            }
            for(int j = 0; j<g.getVertexColors().size(); j++){
            }
            UB = backGCP(gRemoveV(g, chosenVertex), max(k,g.getVertexColors().get(chosenVertex).get(i)), UB, lowerbound);
            if(lowerbound==UB){
                return UB;
            }
            for(int j = 0; j<g.getVertex(); j++){
                if(g.hasEdge(chosenVertex, j)){
                    g.addColor(j, i);
                }
            }
        }
        
        
        return UB;
    }
    public static boolean check(G g, int UB){
        for(int i = 0; i<g.getVertexColors().size(); i++){
            boolean a = false;
            for(int j = 0; j<UB; j++){
                if(g.getVertexColors().get(i).contains(j)){
                    a=true;
                    break;
                }
            }
            if(!a){
                return false;
            }
        }
        return true;
    }
    public static int dsatur(G g, int UB){
        int result = -1;
        int smallestSize = UB;
        for(int i =0; i<g.getVertexColors().size(); i++){
            int tempsize = 0;
            for(int j = 0; j<g.getVertexColors().get(i).size();j++){
                if(g.getVertexColors().get(i).get(j)<UB){
                    tempsize++;
                }
                else{
                    break;
                }
            }
            if(tempsize<smallestSize){
                result = i;
                smallestSize = tempsize;
            }
            else if(tempsize==smallestSize){
                int tempResult = maxEdges(g, result, i);
                if(tempResult==-1){
                    lowestColor(g, result, i);
                }
                else{
                    result = tempResult;
                }
            }
        }

        return result;
    }
    public static int max(int a, int b){
        if(a>b){
            return a;
        }
        else if(a<b){
            return b;
        }
        else{
            return a;
        }

    }
    public static int maxEdges(G g, int a, int b){
        if(g.getAdjacencyLists().get(a).size()>g.getAdjacencyLists().get(b).size()){
            return a;
        }
        else if(g.getAdjacencyLists().get(a).size()<g.getAdjacencyLists().get(b).size()){
            return b;
        }
        else return -1;
    }
    public static int lowestColor(G g, int a, int b){
        if(g.getVertexColors().get(a).get(0)>g.getVertexColors().get(b).get(0)){
            return b;
        }
        if(g.getVertexColors().get(a).get(0)<g.getVertexColors().get(b).get(0)){
            return a;
        }
        else return a;
    }
    public static G gRemoveV(G g, int v){
        int vertex = g.getAdjacencyLists().size();
        ArrayList<ArrayList<Integer>> listcopy = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < g.getAdjacencyLists().size(); i++){
			listcopy.add(new ArrayList<Integer>());
		}
        for(int i = 0; i<g.getAdjacencyLists().size(); i++){
            for(int j = 0; j<g.getAdjacencyLists().get(i).size(); j++){
                listcopy.get(i).add(g.getAdjacencyLists().get(i).get(j));
            }
        }
        Graph tempgraph = new Graph(listcopy, vertex);

        ArrayList<ArrayList<Integer>> vertexColorsCopy = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i< g.getVertexColors().size(); i++){
            vertexColorsCopy.add(new ArrayList<Integer>());
        }
        for(int i = 0; i<g.getVertexColors().size(); i++){
            for(int j = 0; j<g.getVertexColors().get(i).size();j++){
                vertexColorsCopy.get(i).add(g.getVertexColors().get(i).get(j));
            }
        }

        G G2 = new G(vertex, tempgraph, vertexColorsCopy);
        G2.removeVertex(v);
        return G2;
    }
}
