package game.Competition;
import game.arena.IArena;
import game.enums.Discipline;
import game.entities.sportsman.WinterSportsman;
import game.enums.Gender;
import game.enums.League;


/**
 * {@code WinterCompetition} class represent competition in winter conditions
 * @Param discipline, league, gender
 */
public class WinterCompetition extends Competition{
    private final Discipline discipline;
    private final League league ;
    private final Gender gender;

    public WinterCompetition(IArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender) {
        super(arena , maxCompetitors);
        this.discipline = discipline;
        this.league = league;
        this.gender = gender;
    }
    @Override
    public Gender getGender(){
        return gender;
    }
    @Override
    public Discipline getDiscipline(){
        return discipline;
    }

    @Override
    protected boolean isValidCompetitor(Competitor competitor){
        if(competitor instanceof WinterSportsman){
            WinterSportsman winterSportsman = (WinterSportsman) competitor;
            return discipline.equals(winterSportsman.getDiscipline()) &&
                    league.isInLeague(winterSportsman.getAge()) &&
                    gender.equals(winterSportsman.getGender());
        }
        return false;
    }
}

