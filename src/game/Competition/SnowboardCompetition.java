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
    public SnowboardCompetition(IArena arena, int maxCopetitiors, Discipline discipline, League league, Gender gender){
        super((WinterArena) arena,maxCopetitiors,discipline,league,gender);
    }
    /**
     * @func isVaildCompetitor checks if the competitior can play  this competiton type
     */
    @Override
    public boolean isValidCompetitor(Competitor competitor){
        if(competitor instanceof Snowboarder){
            Snowboarder snowboarder = (Snowboarder) competitor;
            if(this.league.isInLeague(snowboarder.getAge())&&this.gender==snowboarder.getGender()){
                return true;
            }
        }
        return false;
    }
}
