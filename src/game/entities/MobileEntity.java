package game.entities;
import Observe.Observer;
import game.Competition.Competition;
import utilities.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code MobileEntity} class represent mobile entity.
 *
 */
public class MobileEntity extends Entity implements IMobileEntity {
    private final double maxSpeed;
    private final double acceleration;
    private double speed;
    private Competition competition;
    private List<Observer> observers = new ArrayList<>();

    public MobileEntity(double initialSpeed, double acceleration, double maxSpeed) {
        super(new Point());
        this.setSpeed(initialSpeed);
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
    }

    /**
     * @method move, that method moves the sportsman on X-axis
     * first the func calc the current speed (friction * accelerationAFTERbonus)
     * and it moves the sportman by adding the current speed to the last location
     * finaly it updates the location of the object.
     */
    @Override
    public void move(double friction) {
        this.setSpeed(Math.min(this.maxSpeed, this.speed + this.getAcceleration() * (1 - friction)));
        Point newLocation = this.getLocation().offset(this.speed, this.getLocation().getX());
        this.setLocation(newLocation);
        notifyObservers();
    }
    public double getSpeed() {
        return speed;
    }
    public double getMaxSpeed(){
        return maxSpeed;
    }


    /**
     * runs update function for other observers
     */
    @Override
    public void notifyObservers(){
        for (Observer o : observers){
            o.update(this,this);
        }
    }
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }


    public double getAcceleration() {
        return acceleration;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public Competition getCompetition(){
        return competition;
    }

}

