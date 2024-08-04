package GUI;

import Observe.Observable;
import Observe.Observer;
import game.entities.MobileEntity;
import utilities.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompetitionGUI {
    protected static String IMAGE_PATH = "/Users/firasabusada/Desktop/SW/שנה ב סמ ב/תכנות מונחה עצמים מתקדם/HwJava/HW2/icons/";
    protected static JLayeredPane centerPanel;
    private static JLabel backgroundLabel;
    static JPanel rightPanel;
    private static int width = 1000;
    private static List<CompetitorLabel> competitorLabels = new ArrayList<>();
    private Timer timer;
    private static boolean gameStarted = false;
    private static boolean gameFinished = true;

    public CompetitionGUI() {
        JFrame frame = new JFrame("Competition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(new Dimension(getWidth(), BuildArenaPanel.getLength())); // Set initial size

        // Right Panel with JScrollPane
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setSize(new Dimension(250, 700));
        BuildArenaPanel buildArenaPanel = new BuildArenaPanel();
        CreateCompetitionPanel createCompetitionPanel = new CreateCompetitionPanel();
        AddCompetitorPanel addCompetitorPanel = new AddCompetitorPanel();

        // Adding Observer
        buildArenaPanel.addObserver(createCompetitionPanel);
        createCompetitionPanel.addObserver(addCompetitorPanel);

        // Adding panels to right panel
        rightPanel.add(buildArenaPanel.getPanel());
        rightPanel.add(createCompetitionPanel.getPanel());
        rightPanel.add(addCompetitorPanel.getPanel());

        JScrollPane scrollPane = new JScrollPane(rightPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane, BorderLayout.EAST);

        // Center Panel
        centerPanel = new JLayeredPane();
        centerPanel.setLayout(null); // Use null layout to control positions
        centerPanel.setPreferredSize(new Dimension(getWidth(), BuildArenaPanel.getLength()));

        // Background Label
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, getWidth(), BuildArenaPanel.getLength());
        centerPanel.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void SetWidth(int width) {
        CompetitionGUI.width = width;
    }

    public static int getWidth() {
        return width;
    }

    public static void setBackgroundImage(ImageIcon icon) {
        Image resizedImg = icon.getImage().getScaledInstance(centerPanel.getWidth(), centerPanel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        backgroundLabel.setIcon(resizedIcon);
        backgroundLabel.setBounds(0, 0, centerPanel.getWidth(), centerPanel.getHeight());
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public static void addCompetitorIcon(MobileEntity competitor, ImageIcon icon) {
        Image resizedImage = icon.getImage().getScaledInstance(65,65,Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel competitorLabel = new JLabel(resizedIcon);
        Point initialPosition = competitor.getLocation();
        competitorLabel.setBounds((int) initialPosition.getX(), (int) initialPosition.getY(), 65, 65);
        centerPanel.add(competitorLabel, JLayeredPane.PALETTE_LAYER);
        centerPanel.setLayer(competitorLabel, JLayeredPane.PALETTE_LAYER);
        competitorLabels.add(new CompetitorLabel(resizedIcon, competitorLabel, competitor));
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }

    public void startUpdating() {
        timer = new Timer(30, e -> updateCompetitorPositions());
        timer.start();
    }

    private void updateCompetitorPositions() {
        for (CompetitorLabel competitorLabel : competitorLabels) {
            Point location = competitorLabel.getMobileEntity().getLocation();
            competitorLabel.getLabel().setBounds((int) location.getX(), (int) location.getY(), 65, 65);
        }
        centerPanel.repaint();
    }

    public static void main(String[] args) {
        CompetitionGUI gui = new CompetitionGUI();
        gui.startUpdating();
    }

    private static class CompetitorLabel implements Observer {
        private final ImageIcon icon;
        private final JLabel label;
        private  MobileEntity mobileEntity;

        public CompetitorLabel(ImageIcon icon, JLabel label, MobileEntity mobileEntity) {
            this.icon = icon;
            this.label = label;
            this.mobileEntity = mobileEntity;
        }

        public JLabel getLabel() {
            return label;
        }

        public MobileEntity getMobileEntity() {
            return mobileEntity;
        }

        @Override
        public void update(Observable obs, Object arg) {
            if (obs instanceof MobileEntity){
                this.mobileEntity = (MobileEntity)arg;
            }
        }
    }
}
