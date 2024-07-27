package GUI;
import GUI.BuildArenaPanel;
import game.Competition.*;
import game.arena.IArena;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * have to check if it should extends from BuildArenaPanel
 */
public class CreateCompetitionPanel{
    private JPanel panel;
    private IArena arena;
    private Competition competition;
    public CreateCompetitionPanel(IArena arena) {
        this.arena = arena;
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("<html><font color='blue'><u>CREATE COMPETITION</u></font></html>"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Choose Competition"), gbc);
        gbc.gridy = 1;
        JComboBox<String> chooseCompetitionCombo  =  new JComboBox<>(new String[]{"SKI", "SnowBoard"});
        panel.add(chooseCompetitionCombo, gbc);

        gbc.gridy = 2;
        panel.add(new JLabel("Max Competitors number"), gbc);

        gbc.gridy = 3;
        JTextField maxCompetitorsNumber = new JTextField("10", 10);
        panel.add(maxCompetitorsNumber, gbc);

        gbc.gridy = 4;
        panel.add(new JLabel("Discipline"), gbc);
        gbc.gridy = 5;
        JComboBox<String> DisciplineComboBox = new JComboBox<>(new String[]{"SLALOM", "GIANT SLALOM", "DOWNHILL", "FREESTYLE"});
        panel.add(DisciplineComboBox, gbc);
        gbc.gridy = 6;
        panel.add(new JLabel("League"), gbc);
        gbc.gridy = 7;
        JComboBox<String> LeagueComboBox = new JComboBox<>(new String[]{"Junior", "Senior", "Adult"});
        gbc.gridy = 8;
        panel.add(new JLabel("Gender"), gbc);
        gbc.gridy = 9;
        JComboBox<String> GenderComboBox = new JComboBox<>(new String[]{"Male", "Female"});
        panel.add(GenderComboBox, gbc);

        gbc.gridy = 10;
        JButton createCompetitionButton = new JButton("Create Competition");
        createCompetitionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(createCompetitionButton, gbc);
        createCompetitionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int maxCompetitors = Integer.parseInt(maxCompetitorsNumber.getText());
                    if (arena == null) {
                        JOptionPane.showMessageDialog(createCompetitionButton, "Please build an arena first!",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String CompetitionType = chooseCompetitionCombo.getSelectedItem().toString();
                    Discipline SelectedDiscipline = (Discipline) DisciplineComboBox.getSelectedItem();
                    League selectedLeague = (League) LeagueComboBox.getSelectedItem();
                    Gender selctedGender = (Gender) GenderComboBox.getSelectedItem();
                    if(CompetitionType == "SKI"){
                        competition = new SkiCompetition(arena, maxCompetitors, SelectedDiscipline, selectedLeague,selctedGender);
                    }else {
                        competition = new SnowboardCompetition(arena, maxCompetitors, SelectedDiscipline, selectedLeague,selctedGender);
                    }

                    JOptionPane.showMessageDialog(createCompetitionButton, "competition have been created!",
                            "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(createCompetitionButton, "Please enter a valid number",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getPanel(){
        return panel;
    }
}
