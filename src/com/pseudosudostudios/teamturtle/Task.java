/****************************
 * deStress
 * Updated 4/28/14 by Ben O'Halloran
 ***************************/
package com.pseudosudostudios.teamturtle;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Holds data related to the task. Also keeps a master list of all tasks
 * entered into the application that have not been deleted.
 */
public class Task {
	public static final List<Task> masterTaskList = new UniqueLinkedList();
	String name, notes, course, due;
	boolean done;

	public Task(String name, String notes, String course, String due) {
		if (name != null)
			this.name = name.trim();
		if (notes != null)
			this.notes = notes.trim();
		if (course != null)
			this.course = course.trim();
		if (due != null)
			this.due = due.trim();
		done = false;
		masterTaskList.add(0, this); // Append at the front of the list
	}

	public Task(JSONObject jsonObject) throws JSONException {
		this(jsonObject.getString("name"), jsonObject.getString("notes"),
				jsonObject.getString("course"), jsonObject.getString("due"));
		this.done = jsonObject.getBoolean("done");
	}

	public JSONObject toJSONObject() throws JSONException {
		return new JSONObject().put("name", name).put("notes", notes)
				.put("course", course).put("due", due).put("done", done);
	}

	@Override
	public String toString() {
		return "Task [name=" + name + ", notes=" + notes + ", course=" + course
				+ ", due=" + due + ", done=" + done + "]";
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + (done ? 1231 : 1237);
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
		if (done != other.done)
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

	public static Task findTaskByHash(int hash) {
		for (Task t : masterTaskList)
			if (t.hashCode() == hash)
				return t;
		return null;
	}

}