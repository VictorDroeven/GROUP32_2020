import java.util.ArrayList;

public class Node {
    private int name;
    private ArrayList<Integer> edge;
    public Node(int aName, ArrayList<Integer> aEdge){
        edge = aEdge;
        name = aName;
    }
    public boolean hasEdge(int a){
        if(edge.contains(a)){
            return true;
        }
        else return false;
    }
    public int getName(){
        return name;
    }
    public ArrayList<Integer> getEdge(){
        return edge;
    }
    public static ArrayList<Node> NodeCreator(Graph g){
        ArrayList<Node> P = new ArrayList<Node>();

        for(int i = 0; i<g.getGraphLength(); i++){
            P.add(new Node(i, g.adjacencyList.get(i)));
        }

        return P;
    }
}
