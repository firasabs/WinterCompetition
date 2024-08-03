package GUI;
import Observe.Observable;
import Observe.Observer;
import game.Competition.Competition;
import game.Competition.Competitor;
import game.Competition.WinterCompetition;
import game.entities.sportsman.WinterSportsman;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class CompetitionGUI implements Observer {
    protected static String IMAGE_PATH = "/Users/firasabusada/Desktop/SW/שנה ב סמ ב/תכנות מונחה עצמים מתקדם/HwJava/HW2/icons/";
    protected static JPanel centerPanel;
    private static JLabel backgroundLabel;
    static JPanel rightPanel;
    private static int width = 1000;
    private static boolean gameStarted = false;
    private static boolean gamefinshed = true;
    private Competition currcompetition;
    private static Timer timer;
    private static List<CompetitorLabel> competitorLabels = new ArrayList<>();

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
        /**
         * adding Observer
         */
        buildArenaPanel.addObserver(createCompetitionPanel);
        createCompetitionPanel.addObserver(addCompetitorPanel);
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

    public static void SetWidth(int width) {
        CompetitionGUI.width = width;
    }

    public static int getWidth() {
        return width;
    }
    public static void setBackgroundImage(ImageIcon icon) {
        JLabel backgroundLabel = new JLabel(icon);
        centerPanel.add(backgroundLabel);
        centerPanel.revalidate();
        centerPanel.repaint();
    }
    public static boolean isGameStarted() {
        return gameStarted;
    }
    public static void addCompetitorIcon(Competitor competitor) {
        ImageIcon icon = competitor.getIcon();
        Point initialPosition = new Point((int) competitor.getLocation().getX(), (int) competitor.getLocation().getY());
        JLabel competitorLabel = new JLabel(icon);
        competitorLabel.setBounds((int) initialPosition.getX(), (int) initialPosition.getY(), 65, 65);
        centerPanel.add(competitorLabel);
        competitorLabels.add(new CompetitorLabel(icon, competitorLabel, initialPosition));
        centerPanel.revalidate();
        centerPanel.repaint();
    }


    public static void main(String[] args) {
        CompetitionGUI gui = new CompetitionGUI();
    }

    @Override
    public void update(Observable obs, Object arg) {
        if(obs instanceof Competition) {
            currcompetition = (Competition) obs;
        }
    }
    private void updateCompetitorPositions() {
        for (CompetitorLabel competitorLabel : competitorLabels) {
            Point location = competitorLabel.getInitialPosition();
            competitorLabel.getLabel().setBounds((int) location.getX(), (int) location.getY(), 65, 65);
        }
        centerPanel.repaint();
    }
    public void startUpdating() {
        timer = new Timer(30, e -> updateCompetitorPositions());
        timer.start();
    }
    private static class CompetitorLabel {
        private final ImageIcon icon;
        private final JLabel label;
        private Point initialPosition;

        public CompetitorLabel(ImageIcon icon, JLabel label, Point initialPosition) {
            this.icon = icon;
            this.label = label;
            this.initialPosition = initialPosition;
        }

        public ImageIcon getIcon() {
            return icon;
        }

        public JLabel getLabel() {
            return label;
        }

        public Point getInitialPosition() {
            return initialPosition;
        }
    }
}

