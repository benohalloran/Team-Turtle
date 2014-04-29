/****************************
 * deStress
 * Updated 4/28/14 by Ben O'Halloran
 ***************************/
package com.pseudosudostudios.teamturtle;

import java.util.LinkedList;

/**
 * A linked list such that there are no duplicates. When a when a duplicate is
 * attempted to be added, the old match is removed and set to the head of the
 * list (index 0)
 */
public class UniqueLinkedList extends LinkedList<Task> {

	private static final long serialVersionUID = -7219076007629800530L;

	@Override
	public void add(int location, Task object) {
		if (!contains(object))
			super.add(location, object);
		else {
			Task t = super.remove(indexOf(object));
			add(0, t); // This is why I'm using a linked List
		}
	}

	@Override
	public boolean add(Task object) {
		if (!contains(object))
			return super.add(object);
		else {
			Task t = super.remove(indexOf(object));
			add(0, t);
			return false;
		}
	}
}
