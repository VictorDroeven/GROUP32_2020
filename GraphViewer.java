import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.*;

@SuppressWarnings("serial")
public class GraphViewer extends JPanel {
    public static int width;
    public static int height;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    
    public GraphViewer(ArrayList<Node> aNodes, ArrayList<Edge> aEdges, int awidth, int aheight){
        nodes = aNodes;
        edges = aEdges;
        width = awidth;
        height = aheight;
        setBounds(0, 40, 1000, 1000);
        setOpaque(true);
        setBackground(Color.GRAY);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
		for (Edge e : edges) {
		    g.drawLine(nodes.get(e.a).x, nodes.get(e.a).y-68,
			     nodes.get(e.b).x, nodes.get(e.b).y-68);
		}

		for (Node n : nodes) {
	    	g.setColor(n.c);
            g.fillOval(n.x-width/2, n.y-height/2-68, width, height);
            g.drawOval(n.x-width/2, n.y-height/2-68, width, height);
        }
        
        for(Node n : nodes){
            if(n.chosen){
                circle a = new circle(n, width, height);
                Graphics2D g2 = (Graphics2D) g;
                a.draw(g2);
                break;
            }
        }
        for(Node n : nodes){
            if(n.hint){
                if(n.c!=Color.BLACK){
                    n.hint=false;
                }
                else{
                    hintCircle a = new hintCircle(n, width, height);
                    Graphics2D g2 = (Graphics2D) g;
                    a.draw(g2);
                    break;
                }
            }
        }
    }
    public void setNodes(ArrayList<Node> a){
        nodes = a;
    }
}
class circle{
    private Node n;
    private int width;
    private int height;
    public circle(Node an, int awidth, int aheight){
        n = an;
        width = awidth;
        height = aheight;
    }
    public void draw(Graphics2D g2){
        Ellipse2D.Double a = new Ellipse2D.Double(n.x-width/2-2, n.y-height/2-70, width+4, height+4);
        g2.setColor(n.randomColor);
        g2.setStroke(new BasicStroke(4));
        g2.draw(a);
    }
}
class hintCircle{
    private Node n;
    private int width;
    private int height;
    public hintCircle(Node an, int awidth, int aheight){
        n = an;
        width = awidth;
        height = aheight;
    }
    public void draw(Graphics2D g2){
        Ellipse2D.Double a = new Ellipse2D.Double(n.x-width/2-2, n.y-height/2-70, width+4, height+4);
        g2.setColor(n.hintColor);
        g2.setStroke(new BasicStroke(4));
        g2.draw(a);
    }
}