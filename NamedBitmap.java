package com.imlikeaninja.android.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class NamedBitmap {
	private String name;
	private Bitmap bitmap;
	
	public NamedBitmap(String newName, Bitmap newBitmap) {
		name = newName;
		bitmap = newBitmap;
	}
	
	public NamedBitmap(Context context, String newName) {
		int drawableIdentifier = context.getResources().getIdentifier(newName, "drawable", context.getPackageName());
		bitmap = BitmapFactory.decodeResource(context.getResources(), drawableIdentifier);
		name = newName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public int getWidth() {
		return bitmap.getWidth();
	}
	
	public int getHeight() {
		return bitmap.getHeight();
	}
}
