
package utils;

/**
 * Maintains a list of observer and notifies them whenever an internal stage
 * changed
 *
 * @author mohamed
 */
public interface Observable {

    /**
     * Adds an observer to the list.
     *
     * @param obs The observer to be added;
     */
    void addOberver(Observer obs);

    /**
     * Removes the observer to the list.
     *
     * @param obs The observer to be removed.
     */
    public void removeObserver(Observer obs);

    /**
     * Notifies all observers by calling their 'update' method.
     *
     * @param notify
     */
    public void notifyObservers(String notify);


}
