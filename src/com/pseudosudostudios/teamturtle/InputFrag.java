package com.pseudosudostudios.teamturtle;

import java.util.LinkedList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class InputFrag extends Fragment implements OnClickListener {
	TextView out;
	LinkedList<String> outStrings;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.input_layout, null);
		outStrings = new LinkedList<String>();
		out = (TextView) root.findViewById(R.id.main_output);
		GridView grid = (GridView) root
				.findViewById(R.id.main_grid_view_buttons);
		grid.setAdapter(new ButtonAdapter(this));
		grid.invalidate();
		return root;
	}

	@Override
	public void onClick(View v) {
		if (v instanceof Button) {
			Button b = (Button) v;
			if (v.getId() == -2) {
				if (outStrings.size() > 0)
					outStrings.remove(outStrings.size() - 1);
			} else if (b.getText().toString().equals("Go")) {
				startActivity(new Intent(getActivity(), TimeChunk.class));
			} else
				outStrings.add(b.getText().toString());
			StringBuffer buff = new StringBuffer();
			for (String s : outStrings)
				buff.append(s + " ");
			out.setText(buff);
		}
	}
}
