package com.pseudosudostudios.teamturtle;

import java.util.LinkedList;

public class Task {
	public static final LinkedList<Task> masterTaskList = new LinkedList<Task>();
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
		masterTaskList.add(this);
	}

	public Task() {
		this(null, null, null, null);
	}

	public Task(int i) {
		this("name: " + i, "notes: " + i, "course: " + i, "due: " + i);
	}

	@Override
	public String toString() {
		return "Task [name=" + name + ", notes=" + notes + ", course=" + course
				+ ", due=" + due + "]";
	}
}