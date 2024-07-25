package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CompetitionGUI {

    private static JPanel centerPanel = new JPanel();
    static final String IMAGE_PATH = "/Users/firasabusada/Desktop/SW/שנה ב סמ ב/תכנות מונחה עצמים מתקדם/HwJava/HW2/icons/";
    private static JLabel backgroundLabel;
    private static int Length;
    public static void main(String[] args) {
        // Frame
        JFrame frame = new JFrame("Competition");
        frame.setSize(new Dimension(1000, Length=700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        // Right Panel with JScrollPane
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setSize(new Dimension(300, 700));

        JPanel buildArenaPanel = createBuildArenaPanel();
        JPanel createCompetitionPanel = createCompetitionPanel();
        JPanel addCompetitorPanel = createAddCompetitorPanel();

        rightPanel.add(buildArenaPanel);
        rightPanel.add(createCompetitionPanel);
        rightPanel.add(addCompetitorPanel);
        /**
         * Scroll for the right panel
         */
        JScrollPane scrollPane = new JScrollPane(rightPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane, BorderLayout.EAST);

        // Center Panel
        backgroundLabel = new JLabel();
        backgroundLabel.setBackground(Color.LIGHT_GRAY);
        centerPanel.add(backgroundLabel, BorderLayout.CENTER);
        frame.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     *
     * for positing components in panel i use GridBagLayout using the consytain for placing to avoid resizing isseus.
     *
     */
    private static JPanel createBuildArenaPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("<html><font color='blue'><u>BUILD ARENA</u></font></html>"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Arena Length"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        JTextField Alength = new JTextField("700",10);
        panel.add(Alength, gbc);
        Length=Integer.parseInt(Alength.getText());
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Snow Surface"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        JComboBox surfaceCombobox= new JComboBox<>(new String[]{"POWDER", "CRUD", "ICE"});
        surfaceCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSurface = (String) surfaceCombobox.getSelectedItem();
            }
        });
        panel.add(surfaceCombobox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Weather Condition"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        JComboBox<String> weatherComboBox = new JComboBox<>(new String[]{"SUNNY", "CLOUDY", "STORMY"});
        /**
         * @ActionListener to get the selection from the combobox and use it to set background image via loadimages function
         */
        weatherComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedWeather = (String) weatherComboBox.getSelectedItem();
                selectedWeather+= ".jpg";
                loadImages(selectedWeather);
            }
        });
        panel.add(weatherComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JButton buildArenaButton = new JButton("Build Arena");
        buildArenaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(buildArenaButton, gbc);
        /**
         * adding action listner to build arena button it also checks if the length correct.
         */
        buildArenaButton.addActionListener(e -> {
            try {
                Length = Integer.parseInt(Alength.getText());
                if (Length < 700 || Length > 900) {
                    JOptionPane.showMessageDialog(buildArenaButton, "Invalid length!!, length must be between 700 and 900",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Perform the build arena action
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(buildArenaButton, "Please enter a valid number",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });
        return panel;
    }
    // Load image from icons file
    private static void loadImages(String image) {
        String imageName= IMAGE_PATH + image;
        Image pic ;
        // Get the images and set it to backgroundLabel
            try {
                pic = ImageIO.read(new File(imageName));
                if(pic != null) {
                    Image resizedImg = pic.getScaledInstance(centerPanel.getWidth(), centerPanel.getHeight(), Image.SCALE_SMOOTH);
                    backgroundLabel.setIcon(new ImageIcon(resizedImg));
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

    }

    private static JPanel createCompetitionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("<html><font color='blue'><u>CREATE COMPETITION</u></font></html>"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx =0;
        gbc.gridy = 0;
        panel.add(new JLabel("Choose Competition"), gbc);
        gbc.gridy = 1;
        panel.add(new JComboBox<>(new String[]{"SKI", "SnowBoard"}), gbc);

        gbc.gridy = 2;
        panel.add(new JLabel("Max Competitors number"), gbc);
        gbc.gridy = 3;
        panel.add(new JTextField("10", 10), gbc);

        gbc.gridy = 4;
        panel.add(new JLabel("Discipline"), gbc);
        gbc.gridy = 5;
        panel.add(new JComboBox<>(new String[]{"Junior", "Senior", "Adult"}), gbc);

        gbc.gridy = 6;
        panel.add(new JLabel("League"), gbc);
        gbc.gridy = 7;
        panel.add(new JComboBox<>(new String[]{"SLALOM", "GIANT SLALOM", "DOWNHILL", "FREESTYLE"}), gbc);

        gbc.gridy = 8;
        panel.add(new JLabel("Gender"), gbc);
        gbc.gridy = 9;
        panel.add(new JComboBox<>(new String[]{"Male", "Female"}), gbc);

        gbc.gridy = 10;
        JButton createCompetitionButton = new JButton("Create Competition");
        createCompetitionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(createCompetitionButton, gbc);

        return panel;
    }

    private static JPanel createAddCompetitorPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("<html><font color='blue'><u>ADD COMPETITOR</u></font></html>"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name"), gbc);
        gbc.gridy = 1;
        panel.add(new JTextField(10), gbc);

        gbc.gridy = 2;
        panel.add(new JLabel("Age"), gbc);
        gbc.gridy = 3;
        panel.add(new JTextField(10), gbc);

        gbc.gridy = 4;
        panel.add(new JLabel("Max Speed"), gbc);
        gbc.gridy = 5;
        panel.add(new JTextField(10), gbc);

        gbc.gridy = 6;
        panel.add(new JLabel("Acceleration"), gbc);
        gbc.gridy = 7;
        panel.add(new JTextField(10), gbc);

        gbc.gridy = 8;
        JButton addCompetitorButton = new JButton("Add Competitor");
        addCompetitorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(addCompetitorButton, gbc);

        gbc.gridy = 9;
        JButton startCompetitionButton = new JButton("Start competition");
        startCompetitionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(startCompetitionButton, gbc);

        gbc.gridy = 10;
        JButton showInfoButton = new JButton("Show info");
        showInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(showInfoButton, gbc);

        return panel;
    }

}
