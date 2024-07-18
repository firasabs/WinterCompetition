/**
 * @author Firas Abu Sada , Yazan Wattad
 * @id 208339218, 325104149
 */
package game.arena;
import game.entities.IMobileEntity;
import game.enums.SnowSurface;
import game.enums.WeatherCondition;
/**
* {@code WinterArena } class represnts a winter Arena
 */
public class WinterArena implements IArena {
    Double length;
    SnowSurface surface;
    WeatherCondition condition;

    public WinterArena(double len, SnowSurface sur, WeatherCondition cond) {
        this.length = len;
        this.surface = sur;
        this.condition = cond;
    }

    public void setLength(Double length) {
        this.length = length;
    }
    public void setSurface(SnowSurface surface) {
        this.surface = surface;
    }
    public SnowSurface getSurface() {
        return surface;
    }

    public void setCondition(WeatherCondition cond) {
        this.condition = cond;
    }
    public WeatherCondition getCondition() {
        return condition;
    }
    @Override
    public  double getFriction(){
        return surface.friction;
    }
    /**
     * @func is finshed , checks if the MobileEntity passes the finish line by comparing the Point X with the arena length
     */
    @Override
    public boolean isFinished(IMobileEntity me) {
        return me.getLoction().getX() >= length;
    }
    @Override
    public double getLength() {
        return length;
    }
    public String toString(){
        return "Winter arena";
    }

}
