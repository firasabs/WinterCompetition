package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
public class CompetitionGUI {

    protected static String IMAGE_PATH = "/Users/firasabusada/Desktop/SW/שנה ב סמ ב/תכנות מונחה עצמים מתקדם/HwJava/HW2/icons/";
    protected static JPanel centerPanel;
    private static JLabel backgroundLabel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Competition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(new Dimension(1000, BuildArenaPanel.getLength())); // Set initial size

        // Right Panel with JScrollPane
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setSize(new Dimension(250, 700));
        BuildArenaPanel buildArenaPanel = new BuildArenaPanel();
        CreateCompetitionPanel createCompetitionPanel = new CreateCompetitionPanel(buildArenaPanel.getArena());
        AddCompetitorPanel addCompetitorPanel = new AddCompetitorPanel();
        // adding panels to right panel
        rightPanel.add(buildArenaPanel.getPanel());
        rightPanel.add(createCompetitionPanel.getPanel());
        rightPanel.add(addCompetitorPanel.getPanel());

        JScrollPane scrollPane = new JScrollPane(rightPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane, BorderLayout.EAST);

        // Center Panel
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        backgroundLabel = new JLabel();
        backgroundLabel.setBackground(Color.LIGHT_GRAY);
        centerPanel.add(backgroundLabel);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void setBackgroundImage(ImageIcon icon) {
        backgroundLabel.setIcon(icon);
        centerPanel.repaint();
    }
}
