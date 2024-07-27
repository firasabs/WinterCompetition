package GUI;
import game.Competition.Competition;
import game.arena.IArena;
import game.arena.WinterArena;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import static GUI.CompetitionGUI.centerPanel;

public class BuildArenaPanel {
    private JPanel panel;
    private static int length = 700;
    protected IArena Arena;
    /**
     *
     * for positing components in panel i use GridBagLayout using the constain for placing to avoid resizing issues.
     *
     */
    public BuildArenaPanel() {
        panel = new JPanel(new GridBagLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("<html><font color='blue'><u>BUILD ARENA</u></font></html>"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel arenaLengthLabel = new JLabel("Arena Length");
        arenaLengthLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(arenaLengthLabel, gbc);
        gbc.gridy = 1;
        JTextField Alength = new JTextField("700", 10);
        panel.add(Alength, gbc);
        gbc.gridy = 2;
        panel.add(new JLabel("Snow Surface"), gbc);
        gbc.gridy = 3;
        JComboBox<String> surfaceComboBox = new JComboBox<>(new String[]{"POWDER", "CRUD", "ICE"});
        panel.add(surfaceComboBox, gbc);
        gbc.gridy = 4;
        panel.add(new JLabel("Weather Condition"), gbc);
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
                loadAndSetBackgroundImage(selectedWeather);
            }
        });
        panel.add(weatherComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        JButton buildArenaButton = new JButton("Build Arena");
        buildArenaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(buildArenaButton, gbc);

        buildArenaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    length = Integer.parseInt(Alength.getText());
                    if (length < 700 || length > 900) {
                        JOptionPane.showMessageDialog(buildArenaButton, "Invalid length!!, length must be between 700 and 900",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        SnowSurface selectedSurface = (SnowSurface) surfaceComboBox.getSelectedItem();
                        WeatherCondition selectedWeather = (WeatherCondition) weatherComboBox.getSelectedItem();

                        // Create the arena and set it to the competition
                        IArena arena = new WinterArena(length, selectedSurface, selectedWeather);
                        //competition.setArena(arena);

                        // Load background image based on weather condition
                        final String imageName = selectedWeather + ".jpg";
                        loadAndSetBackgroundImage(imageName);

                        JOptionPane.showMessageDialog(buildArenaButton, "Arena built successfully!",
                                "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(buildArenaButton, "Please enter a valid number",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
    public IArena getArena() {
        return Arena;
    }

    private void loadAndSetBackgroundImage(String imageName) {
        String imagePath = CompetitionGUI.IMAGE_PATH+imageName;
        try {
            Image img = ImageIO.read(new File(imagePath));
            Image resizedImg = img.getScaledInstance(centerPanel.getWidth(), centerPanel.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImg);
            CompetitionGUI.setBackgroundImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int getLength() {
        return length;
    }
}
