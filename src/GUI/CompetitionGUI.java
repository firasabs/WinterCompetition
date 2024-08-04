package GUI;

import Observe.Observable;
import Observe.Observer;
import game.Competition.Competition;
import game.Competition.Competitor;
import game.entities.MobileEntity;
import game.entities.sportsman.WinterSportsman;
import utilities.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompetitionGUI implements Runnable{
    protected static String IMAGE_PATH = "/Users/firasabusada/Desktop/SW/שנה ב סמ ב/תכנות מונחה עצמים מתקדם/HwJava/HW2/icons/";
    private JFrame frame;
    protected static JLayeredPane centerPanel;
    private static JLabel backgroundLabel;
    static JPanel rightPanel;
    private static int width = 1000;
    private static List<CompetitorLabel> competitorLabels = new ArrayList<>();
    private Competition currCompetition;
    private Timer timer; //timer for updating icon position every 30 millie second
    public static boolean gameStarted = false;
    private static boolean gameFinished = true;

    public CompetitionGUI() {
        frame = new JFrame("Competition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(new Dimension(getWidth(), BuildArenaPanel.getLength())); // Set initial size

        // Right Panel with JScrollPane
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setSize(new Dimension(250, BuildArenaPanel.getLength()));
        BuildArenaPanel buildArenaPanel = new BuildArenaPanel();
        CreateCompetitionPanel createCompetitionPanel = new CreateCompetitionPanel();
        AddCompetitorPanel addCompetitorPanel = new AddCompetitorPanel();

        // Adding Observer
        buildArenaPanel.addObserver(createCompetitionPanel);
        buildArenaPanel.addObserver(addCompetitorPanel);
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
        timer = new Timer(30, e -> updateCompetitorPositions());
        timer.start();
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
        Image resizedImg = icon.getImage().getScaledInstance(centerPanel.getWidth(), BuildArenaPanel.getLength(), Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        backgroundLabel.setIcon(resizedIcon);
        backgroundLabel.setBounds(0, 0, centerPanel.getWidth(), BuildArenaPanel.getLength());
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    /**
     *
     * @param competitor , takes an added competitor from AddCompetitorPanel
     * @param icon , icon will be uploaded from the competitors class depends on the competition  and there gander
     */
    public static void addCompetitorIcon(MobileEntity competitor, ImageIcon icon) {
        Image resizedImage = icon.getImage().getScaledInstance(65,65,Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel competitorLabel = new JLabel(resizedIcon);
        Point initialPosition = competitor.getLocation();
        competitorLabel.setBounds((int) initialPosition.getX(), (int) initialPosition.getY(), 65, 65);
        centerPanel.add(competitorLabel, JLayeredPane.PALETTE_LAYER);
        centerPanel.setLayer(competitorLabel, JLayeredPane.PALETTE_LAYER);
        CompetitorLabel Iconlabel = new CompetitorLabel(resizedIcon, competitorLabel, competitor.getLocation());
        competitorLabels.add(Iconlabel);
        centerPanel.revalidate();
        centerPanel.repaint();
        competitor.addObserver(Iconlabel);
    }
    // Timer to update competitor icons every 30 milliseconds

    public  boolean isGameStarted() {
        return gameStarted;
    }

    private void updateCompetitorPositions() {
        for (CompetitorLabel competitorLabel : competitorLabels) {
            Point location = competitorLabel.getLocation();
            if(location.getY()>=BuildArenaPanel.getLength()){
                competitorLabel.getLabel().setBounds((int) location.getX(),  backgroundLabel.getHeight()-65, 65, 65);
            }else {
                competitorLabel.getLabel().setBounds((int) location.getX(), (int) location.getY(), 65, 65);
            }
        }
        centerPanel.repaint();
    }

    public static void main(String[] args) {
        CompetitionGUI gui = new CompetitionGUI();
    }
    /**
     * @Class CompetitorLabel , represents competitor icon
     * implements @Observer to be updated by MobileEntity to get the new location of the competitor
     */
    private static class CompetitorLabel implements Observer {
        private final ImageIcon icon;
        private final JLabel label;
        private  Point location;

        public CompetitorLabel(ImageIcon icon, JLabel label, Point location) {
            this.icon = icon;
            this.label = label;
            this.location = location;
        }

        public JLabel getLabel() {
            return label;
        }

        public Point getLocation() {
            return location;
        }

        @Override
        public void update(Observable obs, Object arg) {
            if (obs instanceof MobileEntity){
                MobileEntity entity = (MobileEntity)arg;
                this.location=  entity.getLocation();
            }
        }
    }
    @Override
    public void run() {
        while (currCompetition.hasActiveCompetitors()){
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            try {
                updateCompetitorPositions();
            }catch(Exception e){}
        }
        updateCompetitorPositions();
        gameFinished = true;
    }
}
