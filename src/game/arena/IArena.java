/**
 * @author Firas Abu Sada , Yazan Wattad
 * @id 208339218, 325104149
 */
package game.arena;
import game.entities.IMobileEntity;

public interface IArena {
    double getFriction();
    boolean isFinished(IMobileEntity me);
    double getLength();
}
