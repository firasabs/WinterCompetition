/**
 * @author Firas Abu Sada , Yazan Wattad
 * @id 208339218, 325104149
 */
package game;
import game.Competition.Competition;
import game.Competition.Competitor;
import utilities.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code GameEngine} singleton class gets a competition and activate the required functions .
 * @instance object as singleton template.
 * can be any number from 0 to 800.
 */
public class GameEngine {
    private static GameEngine instance = null;
    private List<Thread> CompetitorThreads ;
    /**
     * Constructs a new point with the specified coordinates.
     *private constructor ( singleton )
     *
     */
    private GameEngine(){
        this.CompetitorThreads = new ArrayList<>();
    }
    public static   GameEngine getInstance(){
        if(instance == null){
            instance = new GameEngine();
        }
        return instance;
    }
    //function get competition and starts a game.
   /* public void startRace(Competition competition) {
        for (Competitor competitor : competition.getActiveCompetitors()) {
            Thread thread = new Thread((Runnable) competitor);
            CompetitorThreads.add(thread);
            thread.start();
        }
    }*/
    public void startRace(Competition competition) {
        ValidationUtils.assertNotNull(competition);
        int step;
        for(step = 0 ; competition.hasActiveCompetitors() ; step++){
            competition.playTurn();
        }
        System.out.println("race finished in " + step + " steps");
        printResults(competition);
    }
    /**
     * print the game results
     */
    private void printResults(Competition competition){
        System.out.println("Race results:");
        int place = 1;
        for(Competitor skier : competition.getFinishedCompetitors()){
            System.out.println(place + ". " + skier);
            place++;
        }
    }
}


