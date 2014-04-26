package com.pseudosudostudios.teamturtle;

import java.util.LinkedList;

public class UniqueLinkedList extends LinkedList<Task> {

	private static final long serialVersionUID = -7219076007629800530L;

	@Override
	public void add(int location, Task object) {
		if (!contains(object))
			super.add(location, object);
	}

	@Override
	public boolean add(Task object) {
		if (!contains(object))
			return super.add(object);
		return true;
	}
}
