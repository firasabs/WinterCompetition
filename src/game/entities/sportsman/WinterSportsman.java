package game.entities.sportsman;
import GUI.CreateCompetitionPanel;
import Observe.Observer;
import game.Competition.Competitor;
import game.arena.WinterArena;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
import utilities.Point;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
/**
 * {@code winterSportsman} class represents sportman that belong to winter sport,
 * @Param discipline ski or snow
 */
public class WinterSportsman extends Sportsman implements Competitor,Runnable {
    private final Discipline discipline;
    private List<Observer> observers = new ArrayList<>();
    public WinterSportsman(String Name, double Age, Gender gend, double acceleration, double maxSpeed, Discipline dis) {
        super(Name, Age, gend, acceleration, maxSpeed);
        this.discipline = dis;
    }

    /**
     * overrided func it has to relocate the copetitor at the start line
     */
    @Override
    public void initRace(double X) {
        this.setLocation(new Point(X, 0));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + getName();
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    @Override
    public void run() {
        while (!getCompetition().getArena().isFinished(this)){
            this.move(getCompetition().getArena().getFriction());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ImageIcon getIcon() {
        return null;
    }

}
