package game.entities.sportsman;
import game.enums.Discipline;
import game.enums.Gender;

import javax.swing.*;

/**
 * {@code Skier} class represent winter sport man
 *
 */
public class Skier extends WinterSportsman{
    private ImageIcon icon;
    public Skier(String Name, double Age, Gender gend, double acceleration,double maxSpeed, Discipline dis) {
        super(Name, Age, gend, acceleration,maxSpeed, dis);
        this.icon=new ImageIcon("/Users/firasabusada/Desktop/SW/שנה ב סמ ב/תכנות מונחה עצמים מתקדם/HwJava/HW2/icons/Ski"+gend+".png");
        //icon = new ImageIcon(new ImageIcon(CompetitionGUI.IMAGE_PATH + currentCompetition.getCompetitionType() + gend + ".png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
    }
    public ImageIcon getIcon(){
        return icon;
    }

}
