/**
 * @author Firas Abu Sada , Yazan Wattad
 * @id 208339218, 325104149
 */
package game;
import game.Competition.Competition;
import game.Competition.Competitor;
import java.util.List;

/**
 * The {@code GameEngine} singleton class gets a competition and activate the required functions .
 * @instance object as singleton template.
 * can be any number from 0 to 800.
 */
public class GameEngine {
    private static GameEngine instance = null;
    /**
     * Constructs a new point with the specified coordinates.
     *private constructor ( singleton )
     *
     */
    private GameEngine(){
    }
    public static   GameEngine getInstance(){
        if(instance == null){
            instance = new GameEngine();
        }
        return instance;
    }
    //function get competition and starts a game.
    public void startRace(Competition competition) {
        int steps=1;
        while (competition.hasActiveCompetitors()) {
            competition.playTurn();
            steps++;
        }
        System.out.println("Race finished in "+ steps + " steps");
        printResults(competition);
    }
    // function prints competition result
    private void printResults(Competition competition) {
        System.out.println("Race results:");
        int position = 1;
        List<Competitor> podium = competition.getFinishedCompetitors();
        for(int i=0; i < competition.getMaxCompetitors();i++){
            System.out.println(position + "." + podium.get(i));
            position++;
        }
    }

}


