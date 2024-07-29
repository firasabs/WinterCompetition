package GUI;
import GUI.Observe.ArenaObserver;
import game.Competition.Competition;
import game.Competition.Competitor;
import game.GameEngine;
import game.arena.IArena;
import game.entities.sportsman.Sportsman;
import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
/**
 * @code in AddCompetitorPanel i wiil crete the panel of add competitor including adding competitors to the competition
 * and starting the competition
 */
public class AddCompetitorPanel {
    private JPanel panel;
    private Competitor competitor;
    private Competition currentCompetition;
    public AddCompetitorPanel(CreateCompetitionPanel CompetitionPanel) {
        /**
         * for dynamic class loading i will use hashMap for mapping the constructors that i will use for creating
         * the competitors to its correct class type by the class reference then implementing it in buttonlistner add competitor
         */
        final Map<String, String> competitorClassType= new HashMap<String, String>();
        competitorClassType.put("SKI", "game.entities.sportsman.Skier");
        competitorClassType.put("Snowboard", "game.entities.sportsman.Snowboarder");
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
        JTextField name = new JTextField("", 10);
        panel.add(name, gbc);
        gbc.gridy = 2;
        panel.add(new JLabel("Age"), gbc);
        gbc.gridy = 3;
        JTextField age = new JTextField("", 10);
        panel.add(age, gbc);
        gbc.gridy = 4;
        panel.add(new JLabel("Max Speed"), gbc);
        gbc.gridy = 5;
        JTextField maxSpeed = new JTextField("", 10);
        panel.add(maxSpeed, gbc);

        gbc.gridy = 6;
        panel.add(new JLabel("Acceleration"), gbc);
        gbc.gridy = 7;
        JTextField acceleration = new JTextField("", 10);
        panel.add(acceleration, gbc);

        gbc.gridy = 8;
        JButton addCompetitorButton = new JButton("Add Competitor");
        addCompetitorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(addCompetitorButton, gbc);
        addCompetitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentCompetition = CompetitionPanel.getCompetition();
                String ClassName =  competitorClassType.get(CompetitionPanel.getCompetitionType());
                if (ClassName != null) {
                try {
                    String Name = name.getText();
                    double Age = Double.parseDouble(age.getText());
                    double MaxSpeed = Double.parseDouble(maxSpeed.getText());
                    double Acceleration = Double.parseDouble(acceleration.getText());
                    Class<?> competitiorClass = Class.forName(ClassName);
                    Constructor<?> constructor = competitiorClass.getConstructor(String.class, double.class, Gender.class, double.class, double.class, Discipline.class);
                    competitor = (Competitor) constructor.newInstance(Name, Age, CompetitionPanel.getSelectedGender(), Acceleration, MaxSpeed, CompetitionPanel.getSelectedDiscipline());
                    currentCompetition.addCompetitor(competitor);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(addCompetitorButton, "  Competitor dosent fit the competition" + ex.getMessage(),
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                }

            }
        });
        gbc.gridy = 9;
        JButton startCompetitionButton = new JButton("Start competition");
        startCompetitionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(startCompetitionButton, gbc);
        startCompetitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentCompetition.getActiveCompetitors()!= null){
                GameEngine.getInstance().startRace(currentCompetition);
                }
            }
            });

        gbc.gridy = 10;
        JButton showInfoButton = new JButton("Show info");
        showInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(showInfoButton, gbc);
        showInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(showInfoButton, "Name :" + name.getText() + "\nage: " + age.getText() +
                                "\nmaxSpeed: " + maxSpeed.getText()+ "\naccelration : " + acceleration.getText(),
                        "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public  JPanel getPanel() {
        return panel;
    }

}