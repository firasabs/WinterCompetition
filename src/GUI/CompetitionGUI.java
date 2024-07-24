
package GUI;
import javax.swing.*;
import java.awt.*;
public class CompetitionGUI {
    public static void main(String[] args) {
        /**
         * Frame
         */

        JFrame frame = new JFrame("Competition");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        /**
         * rightPanel
         */

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1));
        rightPanel.setPreferredSize(new Dimension(200, 700));
        JPanel BuildArenaPanel = new JPanel();
        JPanel CreateCompetitionPanel = new JPanel();
        createBuildArenaPanel(BuildArenaPanel);
        createCompetition(CreateCompetitionPanel);
        rightPanel.add(BuildArenaPanel);
        rightPanel.add(CreateCompetitionPanel);
        frame.add(rightPanel, BorderLayout.EAST);
        /**
         * centerPanel
         */

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.LIGHT_GRAY);
        frame.add(centerPanel, BorderLayout.CENTER);


    }

    private static void createBuildArenaPanel(JPanel panel) {
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(150, 160));
        panel.setBorder(BorderFactory.createTitledBorder("<html><font color='blue'><u>BUILD ARENA</u></font></html>"));
        JLabel ArenaLengthLabel = new JLabel("Arena Length");
        panel.add(ArenaLengthLabel);
        ArenaLengthLabel.setBounds(10, 20, 100, 25);
        JTextField ArenaLengthField = new JTextField("700");
        ArenaLengthField.setBounds(10, 40, 120, 25);
        panel.add(ArenaLengthField);
        /**
         * Snow Surface Label , combo Box
         */

        JLabel SnowLabel = new JLabel("Snow Surface");
        panel.add(SnowLabel);
        JComboBox<String> snowSurfaceComboBox = new JComboBox<>(new String[]{"POWDER", "CRUD", "ICE"});
        SnowLabel.setBounds(10, 60, 100, 25);
        panel.add(snowSurfaceComboBox);
        snowSurfaceComboBox.setBounds(10, 80, 120, 25);
        /**
         * Wather condation label + checkBox
         */

        JLabel WatherLabel = new JLabel("Wather Condition");
        panel.add(WatherLabel);
        JComboBox<String> WatherComboBox = new JComboBox<>(new String[]{"SUNNY", "CLOUDY", "STORMY"});
        WatherLabel.setBounds(10, 100, 100, 25);
        panel.add(WatherComboBox);
        WatherComboBox.setBounds(10, 120, 120, 25);
        JButton buildArenaButton = new JButton("Build Arena");
        panel.add(buildArenaButton);
        buildArenaButton.setBounds(10, 150, 120, 25);

    }
    private static void createCompetition(JPanel panel) {
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(150, 400));
        panel.setBorder(BorderFactory.createTitledBorder("<html><font color='blue'><u>CREATE COMPETITION</u></font></html>"));
        /**
         * Choose Competiotn label + Check Box
         */

        JLabel chosenCompetitionLabel = new JLabel("choose Competition");
        panel.add(chosenCompetitionLabel);
        JComboBox<String> chosenCompetitionComboBox = new JComboBox<>(new String[]{"SKI", "SnowBoard"});
        chosenCompetitionLabel.setBounds(10, 20, 130, 15);
        panel.add(chosenCompetitionComboBox);
        chosenCompetitionComboBox.setBounds(10, 40, 120, 25);
        // max Competitors
        JLabel maxCompLabel = new JLabel("Max Competitors number");
        maxCompLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        panel.add(maxCompLabel);
        maxCompLabel.setBounds(10, 60, 130, 25);
        JTextField maxCompField = new JTextField("10");

        maxCompField.setBounds(10, 80, 120, 25);
        panel.add(maxCompField);
        /**
         * discipline + check box
         */

        JLabel DisciplineLabel = new JLabel("Discipline");
        panel.add(DisciplineLabel);
        JComboBox<String> DisciplineComboBox = new JComboBox<>(new String[]{"Junior", "Senior", "Adult"});
        DisciplineLabel.setBounds(10, 100, 100, 25);
        panel.add(DisciplineComboBox);
        DisciplineComboBox.setBounds(10, 120, 120, 25);
        /**
         * League label + combo box
         */

        JLabel LeagueLabel = new JLabel("League");
        panel.add(LeagueLabel);
        JComboBox<String> LeagueComboBox = new JComboBox<>(new String[]{"SLALOM", "GIANT SLALOM", "DOWNHILL","FREESTYLE"});
        LeagueLabel.setBounds(10, 140, 100, 25);
        panel.add(LeagueComboBox);
        LeagueComboBox.setBounds(10, 160, 120, 25);
        /**
         * Gender + checkBox
         */

        JLabel GenderLabel = new JLabel("Gender");
        panel.add(GenderLabel);
        JComboBox<String> GenderComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        GenderLabel.setBounds(10, 180, 100, 25);
        panel.add(GenderComboBox);
        GenderComboBox.setBounds(10, 200, 120, 25);

        JButton CreateButton = new JButton("Create Competition");
        panel.add(CreateButton);
        CreateButton.setBounds(40, 240, 150, 25);

    }
}

