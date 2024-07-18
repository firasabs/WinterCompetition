/**
 * @author Firas Abu Sada , Yazan Wattad
 * @id 208339218, 325104149
 */
package game.Competition;
import game.arena.IArena;
import java.util.ArrayList;
import java.util.List;
/**
 * The {@code Competition} class represents a generic competition.
 * @Param arena ,maxCompetitor, activeCompetitors, finishedCompetitor.
 * all the parameters belong to one competition
 */
public abstract class Competition {
    private final IArena arena;
    private final int maxCompetitors; // positive number
    protected List<Competitor> activeCompetitors;
    protected List<Competitor> finishedCompetitors;
    public Competition(IArena arena, int maxCompetitors) {
        if (maxCompetitors <= 0) {
            throw new IllegalArgumentException("Max competitors must be a positive number.");
        }
        this.arena = arena;
        this.maxCompetitors = maxCompetitors;
        this.activeCompetitors = new ArrayList<>();
        this.finishedCompetitors = new ArrayList<>();
    }

    public abstract boolean isValidCompetitor(Competitor competitor);

    public void addCompetitor(Competitor competitor) {
        if (activeCompetitors.size() >= maxCompetitors) {
            throw new IllegalStateException(arena +" is full max = " + maxCompetitors );
        }
        if (!isValidCompetitor(competitor)) {
            throw new IllegalArgumentException("Invalid competitor " + competitor.toString());
        }
            competitor.initRace();
            activeCompetitors.add(competitor);
        }
        public IArena getArena(){
        return arena;
        }
    /**
     * This method runs the active competitors that haven't finished the race yet.
     * use for with @parm i traverse activeCompetitor array list
     * while competitor not finished the race he will move on step using @func move()
     * when he pass the finish line he will be added to finishedCompetitior list and get removed from active list
     */
    public void playTurn() {
        List<Competitor> active = getActiveCompetitors();
        List<Competitor> finish = getFinishedCompetitors();
        double Friction = arena.getFriction();
            for(int i=0; i < active.size(); i++){
                Competitor competitor = active.get(i);
                if (arena.isFinished(competitor)) {
                    finish.add(competitor);
                    active.remove(competitor);
                } else {
                    competitor.move(Friction);
                }
            }

        setactiveCompetitors(active);
        setFinishedCompetitors(finish);
    }
    /**
     * @meth hasActiveCompetitiors This method runs the active competitors that haven't finished the race yet.
     */
    public boolean hasActiveCompetitors() {
        return !activeCompetitors.isEmpty();
    }


    public int getMaxCompetitors() {
        return maxCompetitors;
    }

    public List<Competitor> getActiveCompetitors() {
        return new ArrayList<>(activeCompetitors);
    }

    public List<Competitor> getFinishedCompetitors() {

        return finishedCompetitors;
    }
    public void setactiveCompetitors(List<Competitor> competitors) {
        this.activeCompetitors = competitors;
    }
    public void setFinishedCompetitors(List<Competitor> competitors) {
        this.finishedCompetitors = competitors;
    }
}




