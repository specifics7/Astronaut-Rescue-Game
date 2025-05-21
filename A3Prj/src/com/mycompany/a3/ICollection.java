package com.mycompany.a3;


public interface ICollection 
{
	// Add new object to collection list
	public void add(GameObject object);
	
	// Get an Iterator over the collection
	IIterator getIterator();
}
