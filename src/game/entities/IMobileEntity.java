package game.entities;

import utilities.Point;

/**
 * The {@code IMobileEntity} interface represent mobile object.
 *
 *
 */
public interface IMobileEntity {
     /**
      * move function from Entity interface
      *
      *
      */
     void move(double friction);
     /**
      * getLoction function from Entity interface
      *
      */
     Point getLoction();
}
