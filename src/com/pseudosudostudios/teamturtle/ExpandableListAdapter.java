package com.pseudosudostudios.teamturtle;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<Task> tasks; // header titles

	// child data in format of header title, child title

	public ExpandableListAdapter(Context c, List<Task> tasks) {
		this.context = c;
		this.tasks = tasks;
	}

	public void addTask(Task task) {
		// TODO Auto-generated method stub
		tasks.add(task);
		notifyDataSetChanged();
	}

	@Override
	public Task getChild(int groupPosition, int childPosititon) {
		return tasks.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final Task task = getChild(groupPosition, childPosition);
		System.out.println(task == null);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.assignment_details,
					null);
		}
		TextView name, notes, due, course;

		name = (TextView) convertView.findViewById(R.id.assig_details_name);
		name.setText(task.name);

		if (task.notes != null) {
			notes = (TextView) convertView
					.findViewById(R.id.assig_details_notes);
			notes.setText(task.notes);
		}

		if (task.due != null) {
			due = (TextView) convertView.findViewById(R.id.assig_details_due);
			due.setText(task.due);
		}

		course = (TextView) convertView.findViewById(R.id.assig_details_course);
		course.setText(task.course);

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.tasks.get(groupPosition).name;
	}

	@Override
	public int getGroupCount() {
		return this.tasks.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.header_view, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.header_text);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
