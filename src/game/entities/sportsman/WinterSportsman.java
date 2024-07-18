package game.entities.sportsman;
import game.Competition.Competitor;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import utilities.Point;
/**
 * {@code winterSportsman} class represents sportman that belong to winter sport,
 * @Param discipline ski or snow
 */
public class WinterSportsman extends Sportsman implements Competitor {
    Discipline discipline;
    public WinterSportsman(String Name, double Age, Gender gend, double acceleration,double maxSpeed, Discipline dis) {
        super(Name, Age, gend, acceleration,maxSpeed);
        this.discipline = dis;
    }
    public Discipline getDiscipline() {
        return discipline;
    }
    public void setDiscipline(Discipline dis) {
        if(dis!=null ){
            this.discipline = dis;
        }else {
            throw new IllegalArgumentException("uncorrected discipline");
        }
    }
    /**
     * overrided func it has to relocate the copetitor at the start line
     */
    @Override
    public void initRace() {
        Point Location = new Point(0,0);
        getLoction().setX(Location.getX());
        getLoction().setY(Location.getY());
    }
    public String toString(){
        return super.toString();
    }
    /**
     * @func move, overrided to move the competitor, it calls the super func to move them.
     *
     */
    @Override
    public void move(double friction) {
        super.move(friction);
    }

}
