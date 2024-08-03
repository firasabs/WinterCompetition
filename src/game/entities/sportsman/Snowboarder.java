package game.entities.sportsman;
import GUI.CompetitionGUI;
import game.Competition.Competitor;
import game.enums.Discipline;
import game.enums.Gender;

import javax.swing.*;
import java.awt.*;

/**
 * {@code Snowborder} class represent winter sport man
 *
 */
public class Snowboarder extends WinterSportsman implements Competitor {
    private ImageIcon icon;
    public Snowboarder(String Name, double Age, Gender gender, double acceleration,double maxSpeed, Discipline dis) {
        super(Name, Age, gender, acceleration, maxSpeed, dis);
        this.icon=new ImageIcon("/Users/firasabusada/Desktop/SW/שנה ב סמ ב/תכנות מונחה עצמים מתקדם/HwJava/HW2/icons/Snowboard"+gender+".png");
    }
    //ImageIcon icon = new ImageIcon(new ImageIcon(CompetitionGUI.IMAGE_PATH + currentCompetition.getCompetitionType() + currentCompetition.getGender() + ".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
    public ImageIcon getIcon(){
        return icon;
    }
}

