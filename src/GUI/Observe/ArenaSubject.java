package GUI.Observe;

import java.util.Observer;

/**
 * @code ArenaSubject observable to notify the observers when the arena created in realTime while the app running
 */
public interface ArenaSubject {
    void addObserver(ArenaObserver observer);
    void removeObserver(ArenaObserver observer);
    void notifyObservers();
}
