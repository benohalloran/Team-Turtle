package com.pseudosudostudios.teamturtle;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ExpandableListView;

public class RightExpandableListView extends ExpandableListView {

	public RightExpandableListView(Context context) {
		super(context);
	}

	public RightExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RightExpandableListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		updateIndicator();
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		super.onWindowFocusChanged(hasWindowFocus);
		updateIndicator();
	}

	public void updateIndicator() {
		int px = 40;
		int dp = (int) convertDpToPixel(px, getContext());
		int myLeft = getWidth() - dp;
		int myRight = getWidth();
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
			setIndicatorBounds(myLeft, myRight);
		} else {
			setIndicatorBoundsRelative(myLeft, myRight);
		}
	}

	public static float convertDpToPixel(float dp, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return px;
	}

	public static float convertPixelsToDp(float px, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return dp;
	}

}
