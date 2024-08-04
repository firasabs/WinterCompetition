package game.Competition;
import Observe.Observable;
import Observe.Observer;
import game.entities.IMobileEntity;

import javax.swing.*;

/**
 * {@code Competitor } interFACE represents a mobileEntity of type competitor
 */
public interface Competitor extends IMobileEntity,Runnable {
     void initRace(double x);
     void run();
     ImageIcon getIcon();
     void addObserver(Observer observer);

}

