package com.pseudosudostudios.teamturtle;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class ButtonAdapter extends BaseAdapter {
	public static final int DELETE = -2;
	private Context context;
	private OnClickListener listener;
	Button[] buttons;

	public ButtonAdapter(TimeInputFrag in) {
		context = in.getActivity();
		listener = in;
		buttons = new Button[16];
	}

	@Override
	public int getCount() {
		return buttons.length;
	}

	@Override
	public Object getItem(int position) {
		return buttons[position];
	}

	@Override
	public long getItemId(int position) {
		return buttons[position].getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (buttons[position] != null)
			return buttons[position];
		Button b;
		if (convertView == null) {
			if (buttons[position] != null)
				return buttons[position];
			b = new Button(context);
			b.setTextColor(context.getResources().getColor(R.color.button_txt));

			/*
			 * b.setBackgroundColor(context.getResources().getColor(
			 * R.color.button_bg));
			 */
		/*	b.setLayoutParams(new GridView.LayoutParams(
					GridView.LayoutParams.WRAP_CONTENT,
					GridView.LayoutParams.WRAP_CONTENT));*/
			b.setHeight(1);
			b.setGravity(Gravity.CENTER);
			b.setText(position + 1 + "");
			//b.setPadding(0, 100, 0, 100);
			if (position >= 4)
				b.setText(position + "");
			if (position >= 7)
				b.setText(position - 1 + "");
			switch (position) {
			case 3:
				b.setText("Hrs");
				break;
			case 7:
				b.setText("Min");
				break;
			case 11:
				b.setText("Back");
				break;
			case 12:
				b.setText("/");
				break;
			case 13:
				b.setText("0");
				break;
			case 14:
				b.setText(".");
				break;
			case 15:
				b.setText("Go");
				break;
			default:
				break;
			}
			try {
				b.setId(Integer.parseInt(b.getText().toString()));
			} catch (NumberFormatException e) {
				if (position == 11)
					b.setId(DELETE);
				else
					b.setId(-1);
			}
			b.setOnClickListener(listener);
		} else {
			b = (Button) convertView;
		}
		return buttons[position] = b;
	}
}
