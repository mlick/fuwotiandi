package com.lxx.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class TransfromPicSize {
	
	/**
	 * 转换图片大小
	 * @param width 要转换的宽度 
	 * @param height 要转换的高度
	 * @param bitmap 要转换的图片
	 * @return 返回新的图片
	 */
	public static Bitmap TransBigPic(int width, int height,Bitmap bitmap) {
		int picWidth = bitmap.getWidth();
		int picHeight = bitmap.getHeight();
		
		float scaleWidth = (float)width / picWidth;
		float scaleHeight = (float)height / picHeight;
		
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		 
		return Bitmap.createBitmap(bitmap,0,0,picWidth,picHeight,matrix,true);
	}
	
	/*public static Bitmap TransBigPic2(int widthis,Bitmap bitmap) {
		int picWidth = bitmap.getWidth();
		int picHeight = bitmap.getHeight();
		float scaleWidth = 0;
		float scaleHeight = 0;
		System.out.println("width  " + width);
		System.out.println("picWidth  " + picWidth);
		System.out.println("picHeigh  " + picHeight);
		if (picHeight > picWidth) {
			float size = picHeight / picWidth;
			System.out.println("size  " + size);
			scaleWidth = (float)width / picWidth;
			scaleHeight = (float)width*size / picHeight;
		}else {
			scaleWidth = (float)width / picWidth;
			scaleHeight = (float)width / picHeight;
		}
		
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidthis, scaleHeight);
		
		return Bitmap.createBitmap(bitmap,0,0,picWidthis,picHeight,matrix,true);
	}*/
}
