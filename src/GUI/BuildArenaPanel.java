package GUI;
import Observe.Observer;
import Observe.Observable;
import game.arena.IArena;
import game.arena.WinterArena;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BuildArenaPanel implements Observable{
    private JPanel panel;
    private static int length = 700;
    private IArena Arena;
    private List<Observer> observers;
    private boolean builtArena = false;
    private static WeatherCondition selectedWeather;
    /**
     *
     * for positing components in panel i use GridBagLayout using the constain for placing to avoid resizing issues.
     *
     */
    public BuildArenaPanel() {
        observers = new ArrayList<>();
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
        JComboBox<SnowSurface> surfaceComboBox = new JComboBox<>(SnowSurface.values());
        panel.add(surfaceComboBox, gbc);
        gbc.gridy = 4;
        panel.add(new JLabel("Weather Condition"), gbc);
        gbc.gridy = 5;
        JComboBox<WeatherCondition> weatherComboBox = new JComboBox<>(WeatherCondition.values());
        /**
         * @ActionListener to get the selection from the combobox and use it to set background image via loadimages function
         */
        weatherComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedWeather =  weatherComboBox.getSelectedItem().toString();
                selectedWeather+= ".jpg";
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
                        selectedWeather = (WeatherCondition) weatherComboBox.getSelectedItem();

                        // Create the arena and set it to the competition
                        Arena = new WinterArena(length, selectedSurface, selectedWeather);
                        notifyObservers();
                        new ArenaPanel();
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
    public static WeatherCondition getSelectedWeather() {
        return selectedWeather;
    }

    public JPanel getPanel() {
        return panel;
    }
    public IArena getArena() {
        return Arena;
    }
    public static int getLength() {
        return length;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        builtArena = true;
        for(Observer observer : observers) {
            observer.update(this,Arena);
        }
    }

}