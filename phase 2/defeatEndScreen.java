import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class defeatEndScreen {
    public static void defeatScreen(int hintsGiven, int amountOfVertices, int amountOfEdges, int chromaticNumber) {
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(null);

        frame.setContentPane(new JLabel(new ImageIcon("Images/1__o9Z6bf_hnHlHcuEz5RUoA.jpeg")));

        Color panelAbove = new Color(5, 13, 32);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setBounds(0, 0, 1000, 150);
        panel.setLayout(new GridLayout(1, 2));
        panel.setBackground(panelAbove);

        Font f3 = new Font(Font.DIALOG, Font.BOLD, 40);
        Font f4 = new Font(Font.DIALOG, Font.BOLD, 15);
        Font f6 = new Font(Font.DIALOG, Font.BOLD, 25);

        JLabel victory = new JLabel();
        victory.setBounds(850, 50, 200, 40);
        victory.setFont(f3);
        victory.setText("                                   Defeat!");
        victory.setForeground(new Color(255, 255, 255));
        panel.add(victory);

        JPanel statisticsPanel = new JPanel();
        frame.add(statisticsPanel);
        statisticsPanel.setBounds(0, 200, 350, 525);
        statisticsPanel.setBackground(new Color(151, 34, 37));
        statisticsPanel.setLayout(null);

        JPanel congrats = new JPanel();
        frame.add(congrats);
        congrats.setBounds(400, 400, 400, 75);
        congrats.setOpaque(true);
        congrats.setBackground(new Color(20, 68, 108));
        congrats.setLayout(null);

        JLabel congratulations = new JLabel();
        congrats.add(congratulations);
        congratulations.setFont(f6);
        congratulations.setForeground(new Color(255, 255, 255));
        congratulations.setText("You lost, better luck next time!");
        congratulations.setBounds(10, 10, 400, 50);

        JLabel sideLabel1 = new JLabel("Chromatic number / upperbound: "+chromaticNumber);
        statisticsPanel.add(sideLabel1);
        sideLabel1.setBounds(10, 80, 300, 50);
        sideLabel1.setFont(f4);
        sideLabel1.setOpaque(true);
        sideLabel1.setBackground(new Color(151, 0, 37));
        sideLabel1.setForeground(new Color(0, 0, 0));

        JLabel main_Label = new JLabel("Statistics");
        main_Label.setBounds(133, 2, 70, 40);
        main_Label.setForeground(new Color(0, 0, 0));
        main_Label.setFont(f4);
        main_Label.setOpaque(true);
        main_Label.setBackground(new Color(131, 200, 242));
        statisticsPanel.add(main_Label);

        JLabel hints = new JLabel();
        hints.setText("amount of hints given: " + hintsGiven);
        hints.setForeground(new Color(0, 0, 0));
        hints.setOpaque(true);
        hints.setBackground(new Color(151, 0, 37));
        statisticsPanel.add(hints);
        hints.setBounds(10, 225, 300, 50);
        hints.setFont(f4);

        JLabel gameMode = new JLabel();
        gameMode.setText("gamemode chosen:  " + gamemode());
        gameMode.setForeground(new Color(0, 0, 0));
        gameMode.setOpaque(true);
        gameMode.setBackground(new Color(151, 0, 37));
        statisticsPanel.add(gameMode);
        gameMode.setBounds(10, 300, 300, 50);
        gameMode.setFont(f4);

        JLabel vertices = new JLabel();
        vertices.setText("amount of vertices chosen:  "+amountOfVertices);
        vertices.setForeground(new Color(0, 0, 0));
        vertices.setOpaque(true);
        vertices.setBackground(new Color(151, 0, 37));
        statisticsPanel.add(vertices);
        vertices.setBounds(10, 375, 300, 50);
        vertices.setFont(f4);

        JLabel edges = new JLabel();
        edges.setText("amount of edges chosen:  "+amountOfEdges);
        edges.setForeground(new Color(0, 0, 0));
        edges.setOpaque(true);
        edges.setBackground(new Color(151, 0, 37));
        statisticsPanel.add(edges);
        edges.setBounds(10, 150, 300, 50);
        edges.setFont(f4);

        frame.setSize(999, 999);
        frame.setSize(1000, 1000);

        JPanel buttonexitpanel = new JPanel();
        frame.add(buttonexitpanel);
        buttonexitpanel.setBounds(500, 900, 125, 50);
        buttonexitpanel.setOpaque(true);
        buttonexitpanel.setBackground(new Color(20, 79, 97));
        buttonexitpanel.setLayout(null);

        JButton exit = new JButton();
        buttonexitpanel.add(exit);
        exit.setFont(f4);
        exit.setBounds(0, 0, 120, 50);
        exit.setText("exit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    public static String gamemode() {
        if(StartWindow.flagGameMode==1){
            return "the bitter end";
        }
        else if(StartWindow.flagGameMode==2){
            return "Upperbound";
        }
        else{
            return "Random order";
        }
    }
}
