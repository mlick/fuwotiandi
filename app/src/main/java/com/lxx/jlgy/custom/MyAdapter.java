package com.lxx.jlgy.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private Context Maincontext;
	private ArrayList<Map<String, String>> lt;
	
	public  MyAdapter(Context context, ArrayList<Map<String, String>> lt) {
		this.Maincontext = context;
		this.lt = lt;
	}
	
/*	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Typeface typeface = Typeface.createFromAsset(Maincontext.getAssets(), "fonts/huakangwawati.ttf");
		System.out.println("come here!");
		for(int i = 0; i < parent.getChildCount(); i++){
			View view = parent.getChildAt(i);
			if (view instanceof TextView) {
				((TextView) view).setTypeface(typeface);
			}
		}
		return super.getView(position, convertView, parent);
	}
*/

	@Override
	public int getCount() {
		return lt.size();
		
	}

	@Override
	public Object getItem(int arg0) {
		return lt.get(arg0);
		
	}

	@Override
	public long getItemId(int arg0) {
		return lt.get(arg0).hashCode();
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		return null;
	}

}
