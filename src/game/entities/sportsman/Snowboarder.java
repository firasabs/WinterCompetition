package game.entities.sportsman;

import game.Competition.Competitor;
import game.enums.Discipline;
import game.enums.Gender;
/**
 * {@code Snowborder} class represent winter sport man
 *
 */
public class Snowboarder extends WinterSportsman implements Competitor {
    public Snowboarder(String Name, double Age, Gender gender, double acceleration,double maxSpeed, Discipline dis) {
        super(Name, Age, gender, acceleration, maxSpeed, dis);

    }
    public String toString(){
        return "Snowboarder"+" "+super.toString();
    }

}
