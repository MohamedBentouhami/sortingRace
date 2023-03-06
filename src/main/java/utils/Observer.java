
package utils;

/**
 * Allows to update the Observable to notify it of any changes.
 *
 * @author mohamed
 */

public interface Observer {

    /**
     * This method is called whenever the observed object has changed
     *
     * @param args
     */
    public void update(Object args);


}
