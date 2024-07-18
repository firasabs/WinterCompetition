package game.entities;
import utilities.Point;
/**
 * The {@code MobileEntity} class represent mobile entity.
 *
 */
public abstract class MobileEntity extends Entity implements IMobileEntity {
    double maxSpeed;
    double acceleration;
    double speed;

    public MobileEntity(double acceleration,double maxSpeed) {
        super();
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
        speed = 0;
    }

    @Override
    public abstract void move(double friction);

    public Point getLoction() {
        return location;
    }
    //public abstract void setLocation(double x, double y);

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getSpeed() {
        return speed;
    }

    protected void setSpeed(double speed) {
        this.speed = speed;
    }


    public String toString() {
        return "maxSpeed: " + maxSpeed + ", acceleration: " + acceleration + ", speed: " + speed;
    }
}
