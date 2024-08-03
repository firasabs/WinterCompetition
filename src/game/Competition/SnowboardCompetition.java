package game.Competition;
import game.arena.IArena;
import game.arena.WinterArena;
import game.entities.sportsman.Snowboarder;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;

/**
 * {@code SnowboardCompetition} class represents competition type
 */
public class SnowboardCompetition extends WinterCompetition {
    /**
     * Ctor for the competition
     * @param arena Winter arena
     * @param maxCompetitors max competitors in the competition
     * @param discipline discipline
     * @param league age league
     * @param gender gender
     */
    public SnowboardCompetition(IArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender){
        super(arena,maxCompetitors,discipline,league,gender);
    }
    /**
     * @func isVaildCompetitor checks if the competitor can play  this competition type
     */
    @Override
    public boolean isValidCompetitor(Competitor competitor){
        return competitor instanceof Snowboarder && super.isValidCompetitor(competitor);
    }
}
