package GUI;
import GUI.Observe.ArenaObserver;
import game.Competition.*;
import game.arena.IArena;
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
public class CreateCompetitionPanel implements ArenaObserver {
    private JPanel panel;
    private Competition competition;
    private IArena arena;
    private String competitionType;
    private Discipline selectedDiscipline;
    private Gender selectedGender;

    /**
     * @code CreateCompetitionPanel for creating competition as the user choose
     * in this  code the instance of competition will be created by using dynamic class loading
     */
    public CreateCompetitionPanel(){
        /**
         * using hashMap for classes names and path
         */
         final Map<String, String> competitionClassType = new HashMap<String, String> ();
            competitionClassType.put("SKI", "game.Competition.SkiCompetition");
            competitionClassType.put("SnowBoard", "game.Competition.SnowboardCompetition");

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
        JComboBox<Discipline> DisciplineComboBox = new JComboBox<>(Discipline.values());
        panel.add(DisciplineComboBox, gbc);
        gbc.gridy = 6;
        panel.add(new JLabel("League"), gbc);
        gbc.gridy = 7;
        JComboBox<League> LeagueComboBox = new JComboBox<>(League.values());
        panel.add(LeagueComboBox, gbc);
        gbc.gridy = 8;
        panel.add(new JLabel("Gender"), gbc);
        gbc.gridy = 9;
        JComboBox<Gender> GenderComboBox = new JComboBox<>(Gender.values());
        panel.add(GenderComboBox, gbc);
        gbc.gridy = 10;
        JButton createCompetitionButton = new JButton("Create Competition");
        createCompetitionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(createCompetitionButton, gbc);
        createCompetitionButton.addActionListener(new ActionListener() {
            /**
             * after pressing on create competition in that section an instance will try to be crated
             * @param e the event to be processed
             *        errors will be caught depends on the type error
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int maxCompetitors = Integer.parseInt(maxCompetitorsNumber.getText());
                    if (arena == null) {
                        JOptionPane.showMessageDialog(createCompetitionButton, "Please build an arena first!",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    competitionType = chooseCompetitionCombo.getSelectedItem().toString();
                    selectedDiscipline = (Discipline) DisciplineComboBox.getSelectedItem();
                    League selectedLeague = (League) LeagueComboBox.getSelectedItem();
                    selectedGender = (Gender) GenderComboBox.getSelectedItem();
                    String ClassName = competitionClassType.get(competitionType);
                    if(ClassName != null){
                        try{
                            Class<?> competitionClass = Class.forName(ClassName);
                            Constructor<?> constructor = competitionClass.getConstructor(IArena.class, int.class, Discipline.class, League.class, Gender.class);
                            competition = (Competition) constructor.newInstance(arena, maxCompetitors, selectedDiscipline, selectedLeague, selectedGender);
                    JOptionPane.showMessageDialog(createCompetitionButton, "competition have been created!",
                            "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(createCompetitionButton, "Error creating competition: " + ex.getMessage(),
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(createCompetitionButton, "Unknown competition type!",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(createCompetitionButton, "Please enter a valid number",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
        });

    }

    public Competition getCompetition() {
        return competition;

    }

    public String getCompetitionType(){
        return competitionType;
    }
    public Discipline getSelectedDiscipline(){
        return selectedDiscipline;
    }
    public  Gender getSelectedGender(){
        return selectedGender;
    }

    public JPanel getPanel(){
        return panel;
    }

    @Override
    public void updateArena(IArena arena) {
            this.arena = arena;
    }
}
