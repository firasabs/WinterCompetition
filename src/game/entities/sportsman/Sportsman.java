package game.entities.sportsman;
import game.entities.MobileEntity;
import game.enums.Gender;
import game.enums.League;
import utilities.Point;
import utilities.ValidationUtils;

/**
 * The {@code SportsMAN}  CLASS extends MobileEntity
 * class represent sport man (a MobileEntity)
 * @prameters aram Name, age, gender
 *
 **/
public class Sportsman extends MobileEntity {
    private final String name;
    private final double age;
    private final Gender gender;
    /**
     * Constructs a new point with the specified coordinates.
     *@param Name  name sportsman name
     *@param Age represents thr competitor age (must be positive)
     * @throws IllegalArgumentException if name, age and gender not as required
     */
    public  Sportsman(String Name, double Age, Gender gend, double acceleration,double maxSpeed) {
        super(0,acceleration,maxSpeed);
        ValidationUtils.assertNotNullOrEmptyString(Name);
        ValidationUtils.assertPositive(Age);
        ValidationUtils.assertNotNull(gend);
        this.name = Name;
        this.age = Age;
        this.gender = gend;
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
    }/*
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
    }*/



}
