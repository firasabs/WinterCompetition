package GUI;
import Observe.Observable;
import Observe.Observer;
import game.Competition.Competition;
import game.Competition.Competitor;
import game.GameEngine;
import game.entities.sportsman.WinterSportsman;
import game.enums.Discipline;
import game.enums.Gender;
import utilities.ValidationUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * @code in AddCompetitorPanel i will create the panel of add competitor including adding competitors to the competition
 * and starting the competition
 */
public class AddCompetitorPanel implements Observer {
    private JPanel panel;
    private Competitor competitor;
    private Competition currentCompetition;
    private int competitorsNumber = 0;
    private boolean isArenaBuilt = false;
    private boolean isCompetitionCreated = false;
    public AddCompetitorPanel() {
        /**
         * for dynamic class loading i will use hashMap for mapping the constructors that i will use for creating
         * the competitors to its correct class type by the class reference then implementing it in buttonlistner add competitor
         */
        final Map<String, String> competitorClassType= new HashMap<String, String>();
        competitorClassType.put("SkiCompetition","game.entities.sportsman.Skier");
        competitorClassType.put("SnowboardCompetiotion", "game.entities.sportsman.Snowboarder");
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
                    if (!isArenaBuilt) {
                        JOptionPane.showMessageDialog(addCompetitorButton, "Please build an arena first!", "ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (currentCompetition==null) {
                        JOptionPane.showMessageDialog(addCompetitorButton, "Please create a competition first!", "ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String ClassName = competitorClassType.get(currentCompetition.getClass().getSimpleName());
                try {
                    String Name = name.getText();
                    ValidationUtils.assertNotNullOrEmptyString(Name);
                    double Age = Double.parseDouble(age.getText());
                    double MaxSpeed = Double.parseDouble(maxSpeed.getText());
                    double Acceleration = Double.parseDouble(acceleration.getText());

                    Class<?> competitiorClass = Class.forName(ClassName);
                    Constructor<?> constructor = competitiorClass.getConstructor(String.class, double.class, Gender.class, double.class, double.class, Discipline.class);
                    competitor = (Competitor) constructor.newInstance(Name, Age, currentCompetition.getGender(), Acceleration, MaxSpeed, currentCompetition.getDiscipline());
                    currentCompetition.addCompetitor(competitor);
                    competitorsNumber++;
                    //adding AddCompetitorPanel to Observable Competitor to update the icons of the competitors based on there move
                    JOptionPane.showMessageDialog(addCompetitorButton, "competitor "+ competitorsNumber +" have been added",
                            "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    CompetitionGUI.addCompetitorIcon(competitor);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(addCompetitorButton, "  Competitor dosent fit the competition" + ex.getMessage(),
                            "ERROR", JOptionPane.ERROR_MESSAGE);
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
                /*
                // update the width if the competitors cant fit the current frame
                if (((Competition)currentCompetition).getActiveCompetitors().size()*65 > CompetitionGUI.getWidth()){
                    CompetitionGUI.centerPanel.setBounds(((Competition)currentCompetition).getActiveCompetitors().size()*65,0,CompetitionGUI.getWidth(),BuildArenaPanel.getLength());
                }*/
                GameEngine.getInstance().startRace(currentCompetition);
                new Thread(() -> {
                    for (Competitor competitor : currentCompetition.getActiveCompetitors()) {
                        new Thread((Runnable) competitor).start();
                    }
                }).start();
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
    @Override
    public void update(Observable obs, Object arg) {
     if(obs instanceof CreateCompetitionPanel) {
             isArenaBuilt = true;
             isCompetitionCreated = true;
            this.currentCompetition = (Competition) arg;
        }
    }

}