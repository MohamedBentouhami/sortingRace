package model.repository;

import model.dto.Dto;

import java.sql.SQLException;
import java.util.List;

public interface Repository<K, T extends Dto<K>> {

    /**
     * Add an element to the repository.If the element exists, the repository
     * updates this element.
     *
     * @param item the element to add.
     * @return the element's key, usefull when the key is auto-generated.
     */
    K add(T item) throws SQLException;

    /**
     * Removes the element of the specific key.
     *
     * @param key key of the element to removes.
     */
    void remove(K key) ;

    T get(K key);

    /**
     * Returns all the elements of the repository.
     *
     * @return all the elements of the repository.
     */
    List<T> getAll();


    /**
     * Returns true if the element exist in the repository and false otherwise.
     * An element is found by this key.
     *
     * @param key key of the element.
     * @return true if the element exist in the repository and false otherwise.
     */
    boolean contains(K key) ;


}
