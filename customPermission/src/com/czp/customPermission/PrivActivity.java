package com.czp.customPermission;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.TextureView;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PrivActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout view = new LinearLayout(this);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		view.setOrientation(LinearLayout.HORIZONTAL);
		TextView tv = new TextView(this);
		TextPaint paint = tv.getPaint();
		paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//		paint.setFlags(Paint.UNDERLINE_TEXT_FLAG);
		tv.setText("我被访问了");
		view.addView(tv);
		setContentView(view);
	}
}
