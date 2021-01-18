import java.util.ArrayList;
import java.util.Collections;

public class BronKerboschAlgorithm{
    
    public static int lowerBound(ArrayList<Node> R, ArrayList<Node> P, ArrayList<Node> X, ArrayList<Node> allNodes){
        maximumClique = 0;
        Tomita(R, P, X, allNodes);
        return maximumClique;
    }
    private static int maximumClique = 0;
    private static ArrayList<ArrayList<Node>> maximalCliques = new ArrayList<ArrayList<Node>>();
    public static void Tomita(ArrayList<Node> R, ArrayList<Node> P, ArrayList<Node> X, ArrayList<Node> allNodes){

        if(P.size()==0&&X.size()==0){
            maximalCliques.add(R);
            if(R.size()>maximumClique){
                maximumClique=R.size();
                return;
            }
            return;
        }
        ArrayList<Node> PUnionX = new ArrayList<Node>();
        PUnionX.addAll(P);
        for(int i = 0; i<X.size(); i++){
            if(!PUnionX.contains(X.get(i))){
                PUnionX.add(X.get(i));
            }
        }
        int indexBiggest=-1;
        int biggestSize = -1;
        for(int i = 0; i<PUnionX.size(); i++){
            if(PUnionX.get(i).getEdge().size()>biggestSize){
                biggestSize=PUnionX.get(i).getEdge().size();
                indexBiggest = i;
            }
        }
        Node n = PUnionX.get(indexBiggest);

        ArrayList<Node> PTestCopy = new ArrayList<Node>();
        for(int j = 0; j<P.size(); j++){
            PTestCopy.add(P.get(j));
        }
        ArrayList<Integer> toBeRemoved = new ArrayList<Integer>();
        for(int i = 0; i< PTestCopy.size(); i++){
            if(containsEdge(PTestCopy.get(i).getName(), n)){
                toBeRemoved.add(i);
            }
        }
        Collections.sort(toBeRemoved);
        Collections.reverse(toBeRemoved);
        for(int i = 0; i<toBeRemoved.size(); i++){
            int index = toBeRemoved.get(i);
            PTestCopy.remove(index);
        }
        for(Node v : PTestCopy){
            ArrayList<Node> RCopy = new ArrayList<Node>();
            for(int i = 0; i<R.size(); i++){
                RCopy.add(R.get(i));
            }

            ArrayList<Node> PCopy = new ArrayList<Node>();
            for(int i = 0; i<P.size(); i++){
                PCopy.add(P.get(i));
            }

            ArrayList<Node> XCopy = new ArrayList<Node>();
            for(int i = 0; i<X.size(); i++){
                XCopy.add(X.get(i));
            }
            ArrayList<Node> neighbours = new ArrayList<Node>();
            for(int i = 0; i<v.getEdge().size(); i++){
                neighbours.add(allNodes.get(v.getEdge().get(i)));
            }
            RCopy.add(v);
            PCopy.retainAll(neighbours);
            XCopy.retainAll(neighbours);
            Tomita(RCopy, PCopy, XCopy, allNodes);

            P.remove(v);
            X.add(v);
        }
    }
    public static boolean containsEdge(int vertex, Node n){
        if(n.getEdge().contains(vertex)){
            return true;
        }
        else return false;
    }
}
