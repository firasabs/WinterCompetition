package game.entities.sportsman;
import game.enums.Discipline;
import game.enums.Gender;
/**
 * {@code Skier} class represent winter sport man
 *
 */
public class Skier extends WinterSportsman{
    public Skier(String Name, double Age, Gender gend, double acceleration,double maxSpeed, Discipline dis) {
        super(Name, Age, gend, acceleration,maxSpeed, dis);
    }
    public String toString(){
        return "Skier" + " " + super.toString();
    }
}
