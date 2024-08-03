package Observe;

/**
 * @code ArenaSubject observable to notify the observers when the arena created in realTime while the app running
 */
public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
