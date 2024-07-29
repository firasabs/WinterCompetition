package GUI.Observe;
import game.arena.IArena;

/**
 * @code ArenaObserver is an interface for class who extends on the creation of arena
 * Obseerver have to get notify and update arena
 */
public interface ArenaObserver {
    void updateArena(IArena arena);
}
