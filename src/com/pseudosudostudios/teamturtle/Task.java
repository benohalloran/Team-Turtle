package com.pseudosudostudios.teamturtle;

import java.util.List;

public class Task {
	public static final List<Task> masterTaskList = new UniqueLinkedList();
	String name, notes, course, due;

	public Task(String name, String notes, String course, String due) {
		if (name != null)
			this.name = name.trim();
		if (notes != null)
			this.notes = notes.trim();
		if (course != null)
			this.course = course.trim();
		if (due != null)
			this.due = due.trim();
		masterTaskList.add(0, this); //Append at the front of the list, 
	}

	public Task() {
		this(null, null, null, null);
	}

	@Override
	public String toString() {
		return "Task [name=" + name + ", notes=" + notes + ", course=" + course
				+ ", due=" + due + "]";
	}

}