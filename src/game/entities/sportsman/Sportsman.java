package game.entities.sportsman;
import game.entities.MobileEntity;
import game.enums.Gender;
import game.enums.League;
import utilities.Point;

/**
 * The {@code SportsMAN} ABSTRACT CLASS extends MobileEntity
 * class represent sport man (a MobileEntity)
 * @prameters aram Name, age, gender
 *
 **/
public abstract class Sportsman extends MobileEntity {
    private String name;
    private double age;
    private Gender gender;
    /**
     * Constructs a new point with the specified coordinates.
     *@param Name  name sportsman name
     *@param Age represents thr competitor age (must be positive)
     * @throws IllegalArgumentException if name, age and gender not as required
     */
    public  Sportsman(String Name, double Age, Gender gend, double acceleration,double maxSpeed) {
        super(acceleration,maxSpeed);
        setName(Name);
        setAge(Age);
        setGender(gend);
    }
    /**
     * get and set functions for every object.
     **/
    // getters and setters
    public  String getName(){
        return name;
    }
    public double getAge() {
        return age;
    }
    public Gender getGender() {
        return gender;
    }
    public void setName(String name) {
        if(name!=null && (!name.equals(" "))){
            this.name = name;
        }else {
            throw new IllegalArgumentException("name must be a characters not empty or null");
        }
    }
    public void setAge(double Age) {
        if(0<Age){
            this.age = Age;
        }else {
            throw new IllegalArgumentException("Age must be positive");
        }
    }
    public void setGender(Gender gend){
        if(gend!=null){
            this.gender = gend;
        }else {
            throw new IllegalArgumentException("not defined gender");
        }
    }
    public String toString(){
        return  getName();
    }
    /**
     * @method move, that method moves the sportsman on X-axis
     *first the func calc the current speed (friction * accelerationAFTERbonus)
     * and it moves the sportman by adding the current speed to the last location
     * finaly it updates the location of the object.
     */
    public void move(double friction) {
        double maxSpeed = getMaxSpeed();
        double newAcceleration;
        double currSpeed=getSpeed();
        newAcceleration = getAcceleration()+ League.calcAccelerationBonus(getAge());
        currSpeed+= friction * newAcceleration;
        if(currSpeed>maxSpeed){
            currSpeed=maxSpeed;
        }
        setSpeed(currSpeed);
        double newX = getLocation().getX()+ currSpeed;
        double newY = getLocation().getY();
        getLoction().setX(newX);
        getLoction().setY(newY);
    }
}
