
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class DrawGraph extends JFrame {
	public final static int width = 20;
	public final static int height = 20;
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	private ArrayList<Color> color;
	private Color c = Color.BLACK;
	private Color[] nodeColor;
	private Graph3 graph3;
	private JLabel label;
	private JLabel counterLabel;
	private static int ax = -1;
	private static int ay = -1;
	private Timer timer;
	private int second;
	private GraphViewer graphviewer;
	private int[] selection;
	private int[] order;
	private JLabel warnings;
	private JLabel hint;
	private ColEdge[] coledge;
	private int hintsGiven = 0;
	private int chromaticNumber;
	public final static Color[] colorAvailable ={Color.WHITE,Color.BLUE,Color.GREEN,Color.YELLOW,Color.RED,Color.ORANGE,Color.PINK,Color.CYAN, Color.MAGENTA, Color.LIGHT_GRAY};

	public DrawGraph(int vertex, int edge, int flag, Graph3 g){
		setSize(1000,1000);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				ax = e.getX();
				ay = e.getY();
				changeColor();
				checkAmountOfColorsUsed();
			}
		});
		
		
		graph3 = g;
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		color = new ArrayList<Color>();
		nodeColor = new Color[vertex];
		JPanel panel = new JPanel();
		panel.setBounds(300,0, 700, 40);
		panel.setLayout(new FlowLayout());
		getContentPane().add(panel);
		
		
		
		label = new JLabel("amount of colors used: 0");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("TimesNewRoman",Font.BOLD, 12));
		panel.add(label);
		
		
		counterLabel = new JLabel("time: "+ StartWindow.timer);
		counterLabel.setForeground(Color.BLACK);
		counterLabel.setFont(new Font("TimesNewRoman", Font.BOLD, 12));
		if(StartWindow.flagGameMode == 2){
			panel.add(counterLabel);
		}
		
		
		JButton btnNewButton = new JButton("reroll graph");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				if(flag == 1) {
					RandomGraph.graphGenerator(vertex, edge);
				}
				else {
					SpecificGraph.graphViewer(g, vertex, edge, coledge);
				}
			}
		});
		
		
		JButton btnNewButton2 = new JButton("give up");
		btnNewButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defeatEndScreen.defeatScreen(hintsGiven, nodes.size(),edges.size(),chromaticNumber);
				dispose();
			}
		});

		hint = new JLabel("");
		hint.setForeground(Color.GREEN);
		panel.add(hint);

		JButton btnNewButton3 = new JButton("hint");
		btnNewButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hintresponse chosenIndex = hints.generateHint(nodeColor, color, g, coledge, edges, nodes);
				nodes.get(chosenIndex.index).hint=true;
				nodes.get(chosenIndex.index).hintColor = chosenIndex.givenColor;
				
				repaint();
				hintsGiven++;
			}
		});
		
		panel.add(btnNewButton);
		panel.add(btnNewButton3);
		panel.add(btnNewButton2);
		

		if(StartWindow.flagGameMode==3){
			JButton returnButton = new JButton("return one step");
			returnButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int randomposition = 0;
					for(int i = 0; i<order.length; i++){
						if(order[i] == -1){
							if(i == 0){
								randomposition = i;
								break;
							}
							else {
								randomposition = i-1;
								break;
							}
						}
						if(i == order.length-1){
							randomposition=i;
							break;
						}
					}
					if(randomposition>0){
						nodes.get(order[randomposition]).randomColor=Color.BLACK;
						nodes.get(order[randomposition]).chosen = false;
						selection[order[randomposition]]=0;
						order[randomposition]=-1;
						randomposition--;
						nodes.get(order[randomposition]).randomColor = Color.ORANGE;
						nodes.get(order[randomposition]).chosen = true;
						nodes.get(order[randomposition]).c=Color.BLACK;
						graphviewer.setNodes(nodes);
						graphviewer.repaint();
					}
				}
			});
			panel.add(returnButton);
		}

		graphviewer = new GraphViewer(nodes, edges, width, height);
		add(graphviewer);


		JPanel colorchooser = new JPanel();
		colorchooser.setBounds(0, 0, 300, 40);
		colorchooser.setLayout(new GridLayout(2,5));


		JButton red = new JButton();
		red.setBorderPainted(false);
		red.setBackground(Color.RED);
		red.setOpaque(true);
		red.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = Color.RED;
			}
		});
		

		JButton blue = new JButton();
		blue.setBorderPainted(false);
		blue.setBackground(Color.BLUE);
		blue.setOpaque(true);
		blue.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = Color.BLUE;
			}
		});
		

		JButton yellow = new JButton();
		yellow.setBorderPainted(false);
		yellow.setBackground(Color.YELLOW);
		yellow.setOpaque(true);
		yellow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = Color.YELLOW;
			}
		});
		

		JButton green = new JButton();
		green.setBorderPainted(false);
		green.setBackground(Color.GREEN);
		green.setOpaque(true);
		green.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = Color.GREEN;
			}
		});
		

		JButton orange = new JButton();
		orange.setBorderPainted(false);
		orange.setBackground(Color.ORANGE);
		orange.setOpaque(true);
		orange.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = Color.ORANGE;
			}
		});
		

		JButton pink = new JButton();
		pink.setBorderPainted(false);
		pink.setBackground(Color.PINK);
		pink.setOpaque(true);
		pink.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = Color.PINK;
			}
		});
		

		JButton white = new JButton();
		white.setBorderPainted(false);
		white.setBackground(Color.WHITE);
		white.setOpaque(true);
		white.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = Color.WHITE;
			}
		});
		

		JButton cyan = new JButton();
		cyan.setBorderPainted(false);
		cyan.setBackground(Color.CYAN);
		cyan.setOpaque(true);
		cyan.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = Color.CYAN;
			}
		});
		

		JButton magenta = new JButton();
		magenta.setBorderPainted(false);
		magenta.setBackground(Color.MAGENTA);
		magenta.setOpaque(true);
		magenta.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = Color.MAGENTA;
			}
		});
		

		JButton light_gray = new JButton();
		light_gray.setBorderPainted(false);
		light_gray.setBackground(Color.LIGHT_GRAY);
		light_gray.setOpaque(true);
		light_gray.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c = Color.LIGHT_GRAY;
			}
		});
		

		colorchooser.add(red);
		colorchooser.add(blue);
		colorchooser.add(yellow);
		colorchooser.add(green);
		colorchooser.add(orange);
		colorchooser.add(pink);
		colorchooser.add(white);
		colorchooser.add(cyan);
		colorchooser.add(magenta);
		colorchooser.add(light_gray);

		add(colorchooser);


		warnings = new JLabel("");
		warnings.setForeground(Color.RED);
		panel.add(warnings);

	
		

		setVisible(true);
	}

	public void addNode(int ax, int ay){
		nodes.add(new Node(ax,ay));
	}

	public void addEdge(int aa, int ab){
		edges.add(new Edge(aa,ab));
	}

	public void addColor(int ax, int ay, Color d) {
		for(Node n : nodes) {
			if((n.x-width/2)==ax&&(n.y-width/2)==ay) {
				n.c = d;
			}
		}
		graphviewer.setNodes(nodes);
		graphviewer.repaint();
	}

	public void changeColor() {
		int a[][];
		if(RandomGraph.getFlag() == 0) {
			a = RandomGraph.getCords();
		}
		else {
			a = SpecificGraph.getCords();
		}
		int bx = -1;
		int by = -1;
		if(ax != -1 || ay != -1) {
			if(a[ay][ax]==1) {
				for(int i = ay-(width/2+5); i<ay+(width/2+5); i++) {
					for(int j = ax-(height/2+5); j<ax+(height/2+5); j++) {
						if(a[i][j]==2) {
							bx = j;
							by = i;
						}
					}
				}
			}
			if(bx!=-1&&by!=-1) {
				if(StartWindow.flagGameMode == 1){
					if(!ToBitterEnd.isFinished(nodes, color.size())){
						if(ToBitterEnd.able2Color(graph3,bx-width/2,by-height/2,c, nodes)){
							warnings.setText("");
							addColor(bx-width/2, by-height/2, c);
							checkAmountOfColorsUsed();
							if(ToBitterEnd.isFinished(nodes, color.size())){
								victoryEndScreen.victoryScreen(hintsGiven, nodes.size(),edges.size(),chromaticNumber);
								dispose();
							}


						}
						else{
							warnings.setText("Can't paint this node");
						}
					}
				}
				else if(StartWindow.flagGameMode==2) {
					if(!UpperBound.isFinished(nodes, color.size())){
						if(UpperBound.able2Color(graph3,bx-width/2,by-height/2,c, nodes)){
							warnings.setText("");
							addColor(bx-width/2, by-height/2, c);
							checkAmountOfColorsUsed();
							if(UpperBound.isFinished(nodes, color.size())){
								timer.stop();
								victoryEndScreen.victoryScreen(hintsGiven, nodes.size(),edges.size(),chromaticNumber);
								dispose();
							}
						}
						else{
							warnings.setText("Can't paint this node");
						}
					}
				}
				else if(StartWindow.flagGameMode==3) {
					for(Node n : nodes){
						if(bx == n.x &&by==n.y){
							if(n.chosen){
								if(able2Color(graph3,bx-width/2,by-height/2,c, nodes)){
									addColor(bx-width/2, by-height/2, c);
									n.chosen = false;
									n.randomColor = Color.BLACK;
									randomNodeSelection();
									checkAmountOfColorsUsed();
								}
							}
						}
					}
				}
			}
		}
	}

	public void checkAmountOfColorsUsed() {
		int position = 0;
		for (Node n : nodes) {
			nodeColor[position]=n.c;
			position++;
		}
		color.clear();
		for(int i =0; i<nodeColor.length; i++) {
			if(nodeColor[i] != Color.BLACK && !color.contains(nodeColor[i])) {
				color.add(nodeColor[i]);
			}
		}
		label.setText("amount of colors used: "+color.size());
	}

	public void countdownTimer() {
    	timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				second--;
				counterLabel.setText("time: "+ second);	
				if(second ==0) {
					defeatEndScreen.defeatScreen(hintsGiven, nodes.size(),edges.size(),chromaticNumber);
					dispose();
				}
			}
    	});
    	
	}
	
    public void setTimerLength(int a) {
    	second = a;
	}
	
    public void start() {
    	timer.start();
	}

	public ArrayList<Node> getNodes(){
		return nodes;
	}
	
	public ArrayList<Edge> getEdges(){
		return edges;
	}
	public void createRandomEmptyArray(){
		selection = new int[nodes.size()];
		order = new int[nodes.size()];
		Arrays.fill(order, -1);
		Arrays.fill(selection, 0);
	}
	public void randomNodeSelection(){ 
		if(!checkIfFinished()){
			int amount = nodes.size();
			int random; 
			do{
				random = (int) (Math.random()*amount);
			} while(selection[random]!=0);
			for(int i = 0; i<order.length; i++){
				if(order[i]==-1){
					order[i]=random;
					break;
				}
			}
			selection[random]=1;
			nodes.get(random).randomColor = Color.ORANGE;
			nodes.get(random).chosen = true;
			graphviewer.repaint();
		}
		else{
			victoryEndScreen.victoryScreen(hintsGiven, nodes.size(),edges.size(),chromaticNumber);
			dispose();
		}
	}
	public boolean checkIfFinished(){
		for(int i = 0; i<selection.length;i++){
			if(selection[i]==0){
				return false;
			}
		}
		return true;
	}
	public boolean able2Color(Graph3 graph, int x, int y, Color c, ArrayList<Node> nodes){
		if(c == Color.BLACK){
			warnings.setText("choose a color");
			return false;
		}
		warnings.setText("");
        for(int i = 0; i < graph.getGraphLength(); i++) {
            if ((nodes.get(i).x - DrawGraph.width/2) == x && (nodes.get(i).y - DrawGraph.height/2) == y) {
                for(int j = 0; j < graph.getGraphLength(); j++){
                    if(i != j){
                        if(graph.hasEdge(i,j) && c == nodes.get(j).c){
							warnings.setText("Can't paint this node");
                            return false;
                        }
                    }
                }
            }
		}
		warnings.setText("");
        return true;
	}
	public void setColEdge(ColEdge[] a){
		coledge = a;
	}
	public void setChromaticNumber(int c){
		chromaticNumber=c;
	}
}

class Node{
	int x;
	int y;
	Color c = Color.BLACK;
	Color randomColor = Color.BLACK;
	boolean chosen = false;
	boolean hint = false;
	Color hintColor;
	public Node(int ax,int ay){
		x = ax;
		y = ay;
	}
}

class Edge{
	int a;
	int b;
	public Edge(int aa, int ab){
		a = aa;
		b = ab;
	}
}