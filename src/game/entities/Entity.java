/**
 * @author Firas Abu Sada , Yazan Wattad
 * @id 208339218, 325104149
 */
package game.entities;
import Observe.Observable;
import utilities.Point;
import utilities.ValidationUtils;

/**
 * The {@code entities} class represent static entities.

 */
public abstract class Entity implements Observable {
    private Point location;

    public Entity(){
        this(new Point());
    }
    public Entity(Point location){
        this.location = location;
    }
    public Point getLocation() {
        return location;
    }
    /**
     * @param location the new location of the entity
     * @throws IllegalArgumentException if argument is null
     */
    public void setLocation(Point location) {
        ValidationUtils.assertNotNull(location);
        this.location = location;
    }
}
