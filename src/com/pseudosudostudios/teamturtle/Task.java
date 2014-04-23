package com.pseudosudostudios.teamturtle;

import java.util.LinkedList;
import java.util.List;

public class Task {
	public static final List<Task> masterTaskList = new LinkedList<Task>();
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
		if (!masterTaskList.contains(this))
			masterTaskList.add(this);
	}

	public Task() {
		this(null, null, null, null);
	}

	@Override
	public String toString() {
		return "Task [name=" + name + ", notes=" + notes + ", course=" + course
				+ ", due=" + due + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((due == null) ? 0 : due.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (due == null) {
			if (other.due != null)
				return false;
		} else if (!due.equals(other.due))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		return true;
	}

}