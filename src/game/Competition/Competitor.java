package game.Competition;
import game.entities.IMobileEntity;
import utilities.Point;
/**
 * {@code Competitor } interFACE represents a mobileEntity of type competitor
 */
public interface Competitor extends IMobileEntity {
    public  void move(double friction);
    public  Point getLoction();
    public void initRace();
}

