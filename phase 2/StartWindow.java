import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class StartWindow {

	private JFrame frame;
	public static int flagGameMode = -1;
	public static int flagGraphSelector = -1;
	private JTextField textField;
	private JTextField textField_1;
	public static int vertex;
	public static int edge;
    private String[] args;
    public static int vertices;
	public static ColEdge[] edges;
	public static int length = 0;
	public static int timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow window = new StartWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartWindow() throws IOException, FontFormatException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException, FontFormatException {
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("CabinSketch-Bold.ttf");
		Font font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(25f);
		Font font1 = font.deriveFont(70f);
		Font font2 = font.deriveFont(35f);
		Font font3 = font.deriveFont(50f);
		Font font4 = font.deriveFont(20f);
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel_main = new JPanel();
		panel_main.setBounds(200, 200, 600, 600);
		frame.getContentPane().add(panel_main);
		panel_main.setLayout(new CardLayout(0, 0));
		
	
	
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(200, 200, 600, 600);
		panel_main.add(panel, "panel");
		panel.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setToolTipText("");
		panel1.setBackground(Color.BLACK);
		panel1.setBounds(200, 200, 600, 600);
		panel_main.add(panel1, "panel1");
		panel1.setVisible(false);
		panel1.setLayout(null);
		
		JPanel panel2 = new JPanel();
		panel2.setToolTipText("");
		panel2.setBackground(Color.BLACK);
		panel2.setBounds(200, 200, 600, 600);
		panel_main.add(panel2, "panel2");
		panel2.setVisible(false);
		panel2.setLayout(null);
		
		JPanel panel3 = new JPanel();
		panel3.setToolTipText("");
		panel3.setBackground(Color.BLACK);
		panel3.setBounds(200, 200, 600, 600);
		panel_main.add(panel3, "panel3");
		panel3.setVisible(true);
		panel3.setLayout(null);
		
		JPanel panel4 = new JPanel();
		panel4.setToolTipText("");
		panel4.setBackground(Color.BLACK);
		panel4.setBounds(200, 200, 600, 600);
		panel_main.add(panel4, "panel4");
		panel4.setVisible(false);
		panel4.setLayout(null);

		JPanel panel5 = new JPanel();
		panel5.setToolTipText("");
		panel5.setBackground(Color.BLACK);
		panel5.setBounds(200, 200, 600, 600);
		panel_main.add(panel5, "panel5");
		panel5.setVisible(false);
		panel5.setLayout(null);

		panel_main.removeAll();
		panel_main.add(panel); 
		panel_main.repaint();
		panel_main.revalidate();
		
		JLabel lblNewLabel = new JLabel("  GRAPH COLORING");
		lblNewLabel.setFont(font1);
		lblNewLabel.setBounds(0, 83, 700, 100);
		lblNewLabel.setForeground(Color.ORANGE);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Select Game Mode");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_main.removeAll();
				panel_main.add(panel1);
				panel_main.repaint();
				panel_main.revalidate();
			}
		});
		btnNewButton.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseEntered(java.awt.event.MouseEvent evt){
				btnNewButton.setForeground(Color.PINK);
			}
			public void mouseExited(java.awt.event.MouseEvent evt){
				btnNewButton.setForeground(Color.CYAN);
			}});
		btnNewButton.setBounds(50, 200, 500, 100);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setFont(font2);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Select Graph Type");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_main.removeAll();
				panel_main.add(panel2);
				panel_main.repaint();
				panel_main.revalidate();
			}
		});
		btnNewButton_1.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseEntered(java.awt.event.MouseEvent evt){
				btnNewButton_1.setForeground(Color.PINK);
			}
			public void mouseExited(java.awt.event.MouseEvent evt){
				btnNewButton_1.setForeground(Color.CYAN);
			}});
		btnNewButton_1.setBounds(50, 312, 500, 100);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setForeground(Color.CYAN);
		btnNewButton_1.setFont(font2);
		panel.add(btnNewButton_1);
		
        JButton btnNewButton_2 = new JButton("Start Game");
        btnNewButton_2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                if(flagGameMode==1){
                    if(flagGraphSelector==1){
                        RandomGraph.graphGenerator(vertex, edge);
                    }
                    else if(flagGraphSelector==2){
                        ChromaticNumberBackTrackingAlgo.calculate(args);
						Graph3 g = Graph3.graphConverter(vertices, edges, length);
						SpecificGraph.graphViewer(g, vertices, length, edges);
                    }
                }
                else if(flagGameMode==2){
                    if(flagGraphSelector==1){
                        RandomGraph.graphGenerator(vertex, edge);
                    }
                    else if(flagGraphSelector==2){
                        ChromaticNumberWelshPowell.welshPowellReadIn(args);
						Graph3 g = Graph3.graphConverter(vertices, edges, length);
						SpecificGraph.graphViewer(g, vertices, length, edges);
                    }
                }
                else if(flagGameMode == 3){
                    if(flagGraphSelector==1){
                        RandomGraph.graphGenerator(vertex, edge);
                    }
                    else if(flagGraphSelector==2){
                        ReadGraph.calculate(args);
						Graph3 g = Graph3.graphConverter(vertices, edges, length);
						SpecificGraph.graphViewer(g, vertices, length, edges);
                    }
                }
            }
        });
		btnNewButton_2.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseEntered(java.awt.event.MouseEvent evt){
				btnNewButton_2.setForeground(Color.PINK);
			}
			public void mouseExited(java.awt.event.MouseEvent evt){
				btnNewButton_2.setForeground(Color.CYAN);
			}});
		btnNewButton_2.setBounds(50, 424, 500, 100);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setForeground(Color.CYAN);
		btnNewButton_2.setFont(font2);
		btnNewButton_2.setEnabled(false);
		panel.add(btnNewButton_2); 
		
		
		
		JLabel lblNewLabel_1 = new JLabel("SELECT GAME MODE");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(font3);
		lblNewLabel_1.setBounds(65, 83, 649, 50);
		panel1.add(lblNewLabel_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setToolTipText("select game mode");
		comboBox.setBounds(50, 262, 500, 35);
		panel1.add(comboBox);

		comboBox.setFont(font2);
		comboBox.setBackground(Color.BLACK);
		comboBox.setForeground(Color.CYAN);
		comboBox.addItem("To the bitter end");
		comboBox.addItem("Upper bound within time frame");
		comboBox.addItem("Random order");
		
		JButton btnNewButton_3 = new JButton("Confirm Game Mode");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = (String) comboBox.getSelectedItem();
				if(a.equals("To the bitter end")) {
					flagGameMode = 1;
					if(flagGameMode !=-1 && flagGraphSelector != -1) {
						btnNewButton_2.setEnabled(true);
					}
					panel_main.removeAll();
					panel_main.add(panel);
					panel_main.repaint();
					panel_main.revalidate();
				}
				else if(a.equals("Upper bound within time frame")) {
					flagGameMode = 2;
					if(flagGameMode !=-1 && flagGraphSelector != -1) {
						btnNewButton_2.setEnabled(true);
					}
					panel_main.removeAll();
					panel_main.add(panel5);
					panel_main.repaint();
					panel_main.revalidate();
				}
				else if(a.equals("Random order")) {
					flagGameMode = 3;
					if(flagGameMode !=-1 && flagGraphSelector != -1) {
						btnNewButton_2.setEnabled(true);
					}
					panel_main.removeAll();
					panel_main.add(panel);
					panel_main.repaint();
					panel_main.revalidate();
				}
				if(flagGameMode !=-1 && flagGraphSelector != -1) {
					btnNewButton_2.setEnabled(true);
				}
			}
		});
		btnNewButton_3.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseEntered(java.awt.event.MouseEvent evt){
				btnNewButton_3.setForeground(Color.PINK);
			}
			public void mouseExited(java.awt.event.MouseEvent evt){
				btnNewButton_3.setForeground(Color.CYAN);
			}});
		btnNewButton_3.setBounds(100, 331, 500, 100);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setForeground(Color.CYAN);
		btnNewButton_3.setFont(font2);
		btnNewButton_3.setHorizontalAlignment(SwingConstants.LEFT);
		panel1.add(btnNewButton_3);
		
		JLabel lblNewLabel_2 = new JLabel("SELECT GRAPH TYPE");
		lblNewLabel_2.setForeground(Color.ORANGE);
		lblNewLabel_2.setFont(font3);
		lblNewLabel_2.setBounds(85, 83, 649, 50);
		panel2.add(lblNewLabel_2);
		
		JComboBox<String> comboBox1 = new JComboBox<String>();
		comboBox1.setToolTipText("select game mode");
		comboBox1.setBounds(50, 262, 540, 35);
		panel2.add(comboBox1);

		comboBox1.setFont(font2);
		comboBox1.setBackground(Color.BLACK);
		comboBox1.setForeground(Color.CYAN);
		comboBox1.addItem("Randomly generated with input");
		comboBox1.addItem("Read in graph");
		
		JButton btnNewButton_4 = new JButton("Confirm Graph");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = (String) comboBox1.getSelectedItem();
				
				if(a.equals("Randomly generated with input")) {
					panel_main.removeAll();
					panel_main.add(panel3);
					panel_main.repaint();
					panel_main.revalidate();
				}
				else if(a.equals("Read in graph")) {
					panel_main.removeAll();
					panel_main.add(panel4);
					panel_main.repaint();
					panel_main.revalidate();
				}
			}
		});
		btnNewButton_4.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseEntered(java.awt.event.MouseEvent evt){
				btnNewButton_4.setForeground(Color.PINK);
			}
			public void mouseExited(java.awt.event.MouseEvent evt){
				btnNewButton_4.setForeground(Color.CYAN);
			}});
		btnNewButton_4.setBounds(50, 331, 500, 100);
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.setForeground(Color.CYAN);
		btnNewButton_4.setFont(font2);
		panel2.add(btnNewButton_4);
		
		JLabel lblNewLabel_3 = new JLabel("INPUT THE AMOUNT OF VERTICES AND EDGES");
		lblNewLabel_3.setForeground(Color.ORANGE);
		lblNewLabel_3.setFont(font3);
		lblNewLabel_3.setBounds(70, 83, 649, 50);
		lblNewLabel_3.setVisible(false);
		panel3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("VERTICES:");
		lblNewLabel_4.setForeground(Color.CYAN);
		lblNewLabel_4.setFont(font2);
		lblNewLabel_4.setBounds(84, 228, 200, 40);
		panel3.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setForeground(Color.ORANGE);
		textField.setBackground(Color.BLACK);
		textField.setFont(font2);
		textField.setBounds(300, 223, 130, 40);
		panel3.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("EDGES:");
		lblNewLabel_5.setForeground(Color.CYAN);
		lblNewLabel_5.setFont(font2);
		lblNewLabel_5.setBounds(84, 288, 150, 40);
		panel3.add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.ORANGE);
		textField_1.setBackground(Color.BLACK);
		textField_1.setFont(font2);
		textField_1.setBounds(300, 283, 130, 40);
		panel3.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("   WRONG INPUT");
		lblNewLabel_6.setFont(font4);
		lblNewLabel_6.setBounds(220, 480, 180, 114);
		panel3.add(lblNewLabel_6);
		lblNewLabel_6.setVisible(false);
		
		JButton btnNewButton_5 = new JButton("Confirm Graph");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int a = Integer.parseInt(textField.getText());
					int b = Integer.parseInt(textField_1.getText());
					if(b > (a*a)) {
						lblNewLabel_6.setVisible(true);
						lblNewLabel_6.setText("   TOO MANY EDGES");
						lblNewLabel_6.setForeground(Color.PINK);
					}
					else {
						vertex = a;
						edge = b;
						flagGraphSelector = 1;
						if(flagGameMode !=-1 && flagGraphSelector != -1) {
							btnNewButton_2.setEnabled(true);
						}
						panel_main.removeAll();
						panel_main.add(panel);
						panel_main.repaint();
						panel_main.revalidate();
					}
					
				}
				catch(NumberFormatException f) {
					lblNewLabel_6.setVisible(true);
					lblNewLabel_6.setText("   WRONG INPUT");
					lblNewLabel_6.setForeground(Color.PINK);
				}
			}
		});
		btnNewButton_5.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseEntered(java.awt.event.MouseEvent evt){
				btnNewButton_5.setForeground(Color.PINK);
			}
			public void mouseExited(java.awt.event.MouseEvent evt){
				btnNewButton_5.setForeground(Color.CYAN);
			}});
		btnNewButton_5.setBounds(50, 400, 500, 100);
		btnNewButton_5.setBorderPainted(false);
		btnNewButton_5.setContentAreaFilled(false);
		btnNewButton_5.setFocusPainted(false);
		btnNewButton_5.setForeground(Color.CYAN);
		btnNewButton_5.setFont(font2);
		panel3.add(btnNewButton_5);
		
		
		JLabel lblNewLabel_7 = new JLabel("            CHOOSE YOUR GRAPH FILE");
		lblNewLabel_7.setFont(font2);
		lblNewLabel_7.setBounds(0, 83, 700, 100);
		lblNewLabel_7.setForeground(Color.ORANGE);
		panel4.add(lblNewLabel_7);
		
		JButton btnNewButton_6 = new JButton("FILE BROWSER");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(".");
				int response;
				String path;
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				response = fc.showOpenDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {
					path = fc.getSelectedFile().toString();
					args = new String[1];
					args[0] = path;
					flagGraphSelector = 2;
					if(flagGameMode !=-1 && flagGraphSelector != -1) {
						btnNewButton_2.setEnabled(true);
					}
					panel_main.removeAll();
					panel_main.add(panel);
					panel_main.repaint();
					panel_main.revalidate();
				}
			}
		});
		btnNewButton_6.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseEntered(java.awt.event.MouseEvent evt){
				btnNewButton_6.setForeground(Color.PINK);
			}
			public void mouseExited(java.awt.event.MouseEvent evt){
				btnNewButton_6.setForeground(Color.CYAN);
			}});
		btnNewButton_6.setBounds(200, 200, 200, 100);
		btnNewButton_6.setBorderPainted(false);
		btnNewButton_6.setContentAreaFilled(false);
		btnNewButton_6.setFocusPainted(false);
		btnNewButton_6.setForeground(Color.CYAN);
		btnNewButton_6.setFont(font4);
		panel4.add(btnNewButton_6);

		JLabel lblNewLabel_8 = new JLabel("                CHOOSE TIME FRAME");
		lblNewLabel_8.setFont(font2);
		lblNewLabel_8.setBounds(0, 83, 700, 100);
		lblNewLabel_8.setForeground(Color.ORANGE);
		panel5.add(lblNewLabel_8);

		JButton btnNewButton_7 = new JButton("1 minute");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer = 60;
				panel_main.removeAll();
				panel_main.add(panel);
				panel_main.repaint();
				panel_main.revalidate();
			}
		});
		btnNewButton_7.setBounds(50, 200, 150, 100);
		btnNewButton_7.setBorderPainted(true);
		btnNewButton_7.setContentAreaFilled(false);
		btnNewButton_7.setFocusPainted(false);
		btnNewButton_7.setForeground(Color.CYAN);
		btnNewButton_7.setFont(font4);
		panel5.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("5 minutes");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer = 300;
				panel_main.removeAll();
				panel_main.add(panel);
				panel_main.repaint();
				panel_main.revalidate();
			}
		});
		btnNewButton_8.setBounds(200, 200, 150, 100);
		btnNewButton_8.setBorderPainted(true);
		btnNewButton_8.setContentAreaFilled(false);
		btnNewButton_8.setFocusPainted(false);
		btnNewButton_8.setForeground(Color.CYAN);
		btnNewButton_8.setFont(font4);
		panel5.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("10 minute");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer = 600;
				panel_main.removeAll();
				panel_main.add(panel);
				panel_main.repaint();
				panel_main.revalidate();
			}
		});
		btnNewButton_9.setBounds(350, 200, 150, 100);
		btnNewButton_9.setBorderPainted(true);
		btnNewButton_9.setContentAreaFilled(false);
		btnNewButton_9.setFocusPainted(false);
		btnNewButton_9.setForeground(Color.CYAN);
		btnNewButton_9.setFont(font4);
		panel5.add(btnNewButton_9);

		JLabel timerLabel = new JLabel("Custom time frame (sec):");
		timerLabel.setBounds(20, 400, 500, 26);
		timerLabel.setForeground(Color.CYAN);
		timerLabel.setFont(font2);
		panel5.add(timerLabel);


		JTextField textField_2 = new JTextField();
		textField_2.setBounds(410, 400, 100, 40);
		textField_2.setForeground(Color.ORANGE);
		textField_2.setBackground(Color.BLACK);
		textField_2.setFont(font2);
		panel5.add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton_10 = new JButton("Confirm");
		btnNewButton_10.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					timer = Integer.parseInt(textField_2.getText());
					panel_main.removeAll();
					panel_main.add(panel);
					panel_main.repaint();
					panel_main.revalidate();
				} catch(NumberFormatException f){

				}
			}
		});
		btnNewButton_10.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseEntered(java.awt.event.MouseEvent evt){
				btnNewButton_10.setForeground(Color.PINK);
			}
			public void mouseExited(java.awt.event.MouseEvent evt){
				btnNewButton_10.setForeground(Color.CYAN);
			}});
		btnNewButton_10.setBounds(300, 500, 300, 30);
		btnNewButton_10.setBorderPainted(false);
		btnNewButton_10.setContentAreaFilled(false);
		btnNewButton_10.setFocusPainted(false);
		btnNewButton_10.setForeground(Color.CYAN);
		btnNewButton_10.setFont(font2);
		panel5.add(btnNewButton_10);
		
		
		frame.setBounds(0, 0, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
