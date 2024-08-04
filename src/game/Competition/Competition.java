/**
 * @author Firas Abu Sada , Yazan Wattad
 * @id 208339218, 325104149
 */
package game.Competition;
import Observe.Observable;
import Observe.Observer;
import game.arena.IArena;
import game.entities.MobileEntity;
import game.enums.Discipline;
import game.enums.Gender;
import utilities.ValidationUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Competition} class represents a generic competition.
 * @Param arena ,maxCompetitor, activeCompetitors, finishedCompetitor.
 * all the parameters belong to one competition
 */
public abstract class Competition implements Observer {
    private  IArena arena;
    private final int maxCompetitors; // positive number
    protected List<Competitor> activeCompetitors;
    protected List<Competitor> finishedCompetitors;
    private double X=0;
    public Competition(IArena arena, int maxCompetitors) {
        if (maxCompetitors <= 0) {
            throw new IllegalArgumentException("Max competitors must be a positive number.");
        }
        this.arena=arena;
        this.maxCompetitors = maxCompetitors;
        this.activeCompetitors = new ArrayList<>();
        this.finishedCompetitors = new ArrayList<>();
    }
    public void setArena(IArena arena){
        this.arena = arena;
    }

    public IArena getArena(){
        return arena;
    }

    protected abstract boolean isValidCompetitor(Competitor competitor);

    public void addCompetitor(Competitor competitor) {
        ValidationUtils.assertNotNull(competitor);
        if (activeCompetitors.size() >= maxCompetitors) {
            throw new IllegalStateException(arena + " is full max = " + maxCompetitors);
        }
        if (!isValidCompetitor(competitor)) {
            throw new IllegalArgumentException("Invalid competitor " + competitor);
        } else {
            competitor.initRace(X);
            X+=65;
            activeCompetitors.add(competitor);
        }
    }
    /**
     * This method runs the active competitors that haven't finished the race yet.
     * @parm i traverse activeCompetitor array list
     * while competitor not finished the race he will move on step using @func move()
     * when he pass the finish line he will be added to finishedCompetitior list and get removed from active list
     */
    public void startCompetition() {
        for (Competitor competitor : activeCompetitors) {
            Thread thread = new Thread(competitor);
            thread.start();
        }
    }
    /**
     * @method hasActiveCompetitiors This method runs the active competitors that haven't finished the race yet.
     */
    public boolean hasActiveCompetitors() {
        return !activeCompetitors.isEmpty();
    }

    public ArrayList<Competitor> getActiveCompetitors() {
        return new ArrayList<>(activeCompetitors);
    }

    public ArrayList<Competitor> getFinishedCompetitors() {

        return new ArrayList<>(finishedCompetitors);
    }
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof MobileEntity) synchronized (this){
            Competitor competitor = (Competitor) arg;
                if (arena.isFinished(competitor)) {
                    finishedCompetitors.add(competitor);
                    activeCompetitors.remove(competitor);
                }

        }
    }

    public abstract Gender getGender();
    public abstract Discipline getDiscipline();
}




