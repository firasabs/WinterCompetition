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
    public SkiCompetition(IArena arena, int maxCopetitiors, Discipline discipline, League league, Gender gender){
        super((WinterArena) arena,maxCopetitiors,discipline,league,gender);
    }
    /**
     * @func isVaildCompetitor checks if the competitior can play  this competiton type
     */
    @Override
    public boolean isValidCompetitor(Competitor competitor){
        if(competitor instanceof Skier){
            Skier skier = (Skier) competitor;
            return this.league.isInLeague(skier.getAge()) && this.gender == skier.getGender();
        }
        return false;
    }



}
