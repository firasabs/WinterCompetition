package game.Competition;
import game.arena.IArena;
import game.arena.WinterArena;
import game.entities.sportsman.Skier;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
/**
 * {@code SkiCompetition} class represents competition type
 */
public class SkiCompetition extends WinterCompetition {
    public SkiCompetition(IArena arena, int maxCompetitiors, Discipline discipline, League league, Gender gender){
        super(arena, maxCompetitiors,discipline,league,gender);
    }
    /**
     * @func isVaildCompetitor checks if the competitior can play  this competiton type
     */
    @Override
    public boolean isValidCompetitor(Competitor competitor){
        return competitor instanceof Skier && super.isValidCompetitor(competitor);
    }

}
