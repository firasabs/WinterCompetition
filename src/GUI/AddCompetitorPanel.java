package GUI;

import javax.swing.*;
import java.awt.*;

public class AddCompetitorPanel {
    private JPanel panel;

    public AddCompetitorPanel() {
        panel = new JPanel(new GridBagLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
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

    }

    public  JPanel getPanel() {
        return panel;
    }
}
