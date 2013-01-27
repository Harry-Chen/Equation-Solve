package com.harry.equsolve;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText textA, textB, textC;
	TextView textResult;
	final String TAG = "Equation Solve";
	String equation, txtResult;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textA = (EditText) findViewById(R.id.num_a);
		textB = (EditText) findViewById(R.id.num_b);
		textC = (EditText) findViewById(R.id.num_c);
		textResult = (TextView) findViewById(R.id.text_show_result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menu) {
		switch (menu.getItemId()) {
		case R.id.menu_exit:
			finish();
			break;
		}
		return true;
	}

	public void solve(View view) {
		if (textA.getText().toString().equals("")) {
			int b = Integer
					.parseInt((textB.getText().toString().equals("")) ? "0"
							: textB.getText().toString());
			int c = Integer
					.parseInt((textC.getText().toString().equals("")) ? "0"
							: textC.getText().toString());
			Log.v(TAG, "ONE,b=" + b + ",c=" + c);
			oneTime(b, c);

		} else {
			int a = Integer.parseInt(textA.getText().toString());
			int b = Integer
					.parseInt((textB.getText().toString().equals("")) ? "0"
							: textB.getText().toString());
			int c = Integer
					.parseInt((textC.getText().toString().equals("")) ? "0"
							: textC.getText().toString());
			Log.v(TAG, "TWO,a=" + a + ",b=" + b + ",c=" + c);
			if (a == 0) {
				oneTime(b, c);
			} else {
				twoTimes(a, b, c);
			}

		}
	}

	public void oneTime(int b, int c) {
		equation = ((Math.abs(b) == 1) ? ((b > 0) ? "" : "-") : b) + "x"
				+ (c > 0 ? "+" + c : "") + ((c < 0) ? c : "") + "=0";
		if (b == 0) {
			Toast.makeText(this, R.string.b_error, Toast.LENGTH_LONG).show();
			textResult.setText(equation);
			return;
		}
		double result = -(double) c / b;
		txtResult = "有一个根:x=-" + c + "/" + b + "≈" + result;
		textResult.setText(equation + "\n" + txtResult);
	}

	public void twoTimes(int a, int b, int c) {

		equation = ((Math.abs(a) == 1) ? ((a > 0) ? "" : "-") : a)
				+ "x²"
				+ ((b == 0) ? "" : ((Math.abs(b) == 1) ? ((b > 0) ? "+" : "-")
						: (b < 0) ? b : "+" + b) + "x")
				+ (c > 0 ? "+" + c : "") + ((c < 0) ? c : "") + "=0";
		int delta = b * b - 4 * a * c;
		Log.v(TAG, "Delta=" + delta);
		if (delta < 0) {
			Toast.makeText(this, R.string.delta_error, Toast.LENGTH_LONG)
					.show();
			textResult.setText(equation);
			return;
		} else if (delta == 0) {
			double x = (-b) / (2 * a);
			txtResult = "有两个相等的实数根:x1=x2=" + x;
		} else {
			double sqrt = Math.sqrt(delta);
			double x1 = (-b + sqrt) / (2 * a), x2 = (-b - sqrt) / (2 * a);
			txtResult = "有两个不相等的实数根:\nx1=" + "(" + (-b) + "+√" + delta + ")"
					+ "/" + ((a < 0) ? "(" + a * 2 + ")" : a * 2) + "≈" + x1
					+ "\nx2=" + "(" + (-b) + "-√" + delta + ")" + "/"
					+ ((a < 0) ? "(" + a * 2 + ")" : a * 2) + "≈" + x2;

		}
		textResult.setText(equation + "\n" + txtResult);
	}

	public void reset(View view) {
		textA.setText("");
		textB.setText("");
		textC.setText("");
		textResult.setText(R.string.to_show_result);
	}
}
