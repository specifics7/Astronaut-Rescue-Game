package com.mycompany.a3;

public interface IIterator
{
    // Checks if there are more elements in the collection.
    // return true if there is at least one more element, false otherwise.
    boolean hasNext();

    // Returns the next element in the collection.
    // return the next GameObject in the collection.
    GameObject getNext();
    
    // Removes from the collection the last element returned by the getNext method. 
    void remove();
}
