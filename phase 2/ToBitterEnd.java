

import java.awt.*;
import java.util.ArrayList;

public class ToBitterEnd{
    private static int cr;
    private static long time;
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
        if(cls == cr){
            for(Node i : nodes){
                if(i.c == Color.BLACK){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void setCR(Graph3 graph3){

        for(int i = 0; i <= graph3.getGraphLength(); i++){
            if(BackTrackingAlgo.backTrackingAlgorithm(graph3, i, graph3.getGraphLength())){
                cr = i;
                break;
            }
        }
        System.out.println("Solution Found : " + cr);

    }

    public static void setTime(long t){
        time = t;
    }

    public static long getInitialTime(){
        return time;
    }
}
