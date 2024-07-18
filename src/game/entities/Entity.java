/**
 * @author Firas Abu Sada , Yazan Wattad
 * @id 208339218, 325104149
 */
package game.entities;
import utilities.Point;

/**
 * The {@code entities} class represent static entities.

 */
public abstract class Entity {
    protected Point location;

    Entity(){
        this.location=new Point(0,0);
    }
    Entity(Point location){
        this.location = location;
    }
    public Point getLocation() {
        return location;
    }
    public String toString(){
        return "location: "+super.toString();
    }
}
