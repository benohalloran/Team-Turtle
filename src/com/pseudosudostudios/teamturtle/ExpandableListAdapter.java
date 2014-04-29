/****************************
 * deStress
 * Updated 4/28/14 by Ben O'Halloran
 ***************************/
package com.pseudosudostudios.teamturtle;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/** Expandable ListView Adapter for the task list */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context context;

	public ExpandableListAdapter(Context c) {
		this.context = c;
		String nowstr = "Apr %d 14";
		String[] courses = c.getResources().getStringArray(
				R.array.default_courses);
		String[] titles = c.getResources().getStringArray(
				R.array.default_tasks_name);
		String[] descr = c.getResources().getStringArray(
				R.array.default_descriptions);
		for (int i = 0; Task.masterTaskList.size() < 4; i++) {
			Task.masterTaskList.add(new Task(titles[i], descr[i], courses[i],
					String.format(nowstr, (18 + i))));
		}
	}

	public void addTask(Task task) {
		Task.masterTaskList.add(task);
		notifyDataSetChanged();
	}

	@Override
	public Task getChild(int groupPosition, int childPosititon) {
		return Task.masterTaskList.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final Task task = getChild(groupPosition, childPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.assignment_details,
					null);
		}
		final TextView notes, due, course;
		notes = (TextView) convertView.findViewById(R.id.assig_details_notes);
		due = (TextView) convertView.findViewById(R.id.assig_details_due);
		course = (TextView) convertView.findViewById(R.id.assig_details_course);

		if (task.notes != null)
			notes.setText(task.notes);
		if (task.due != null)
			due.setText(task.due);
		if (task.course != null)
			course.setText(task.course);

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Task getGroup(int groupPosition) {
		return Task.masterTaskList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return Task.masterTaskList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		Task data = getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.header_view, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.header_text);
		lblListHeader.setText(data.name);

		if (data.done)
			lblListHeader.setPaintFlags(lblListHeader.getPaintFlags()
					| Paint.STRIKE_THRU_TEXT_FLAG);
		else
			lblListHeader.setPaintFlags(lblListHeader.getPaintFlags()
					& ~Paint.STRIKE_THRU_TEXT_FLAG);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}
}
