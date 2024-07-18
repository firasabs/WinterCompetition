package game.Competition;
import game.arena.WinterArena;
import game.enums.Discipline;
import game.enums.Gender;
import game.enums.League;
/**
 * {@code WinterCompetition} class represent competition in winter conditions
 * @Param discipline, league, gender
 */
public abstract class WinterCompetition extends Competition{
    Discipline discipline;
    League league;
    Gender gender;
    public WinterCompetition(WinterArena arena, int maxCompetitors, Discipline discipline, League league, Gender gender) {
        super(arena , maxCompetitors);
        this.discipline = discipline;
        this.league = league;
        this.gender = gender;
    }
}

