package com.pseudosudostudios.teamturtle;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class ButtonAdapter extends BaseAdapter {
	private Context context;
	Button[] buttons;

	public ButtonAdapter(Context c) {
		context = c;
		buttons = new Button[16];
	}

	@Override
	public int getCount() {
		return buttons.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Button b;
		if (convertView == null) {
			if (buttons[position] != null)
				return buttons[position];
			b = new Button(context);
			b.setId(position);
			b.setText(position + 1 + "");
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
				b.setText("|<");
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
		} else {
			b = (Button) convertView;
		}
		return buttons[position] = b;
	}

}
