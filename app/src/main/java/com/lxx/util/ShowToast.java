package com.lxx.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ShowToast {
	/**
	 * 
	 * @param content
	 *            显示提示信息
	 */
	public static synchronized void showNewsToast(Context context,
			String content) {
		if (content != null) {
			Toast toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} else {
			return;
		}
	}

}
