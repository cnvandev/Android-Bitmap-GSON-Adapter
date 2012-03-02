package com.imlikeaninja.android.utilities;

import java.lang.reflect.Type;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * This class provides an adapter to serialize and deserialize Android Drawable Resources
 * to JSON using the GSON serialization library. It's pretty simple and brute-force right now,
 * mostly in the deserialization process, and only because I'm lazy and really don't care enough
 * to try to get a proper way to do it.
 *  
 * @author Chris Vandevelde
 *
 */
public class NamedBitmapDeserializer implements JsonDeserializer<NamedBitmap>, JsonSerializer<NamedBitmap> {
	Resources resources;
	Context androidContext;
	
	public NamedBitmapDeserializer(Context context) {
		// Store the environment variables we'll need to do the serialization/deserialization.
		resources = context.getResources();
		androidContext = context;
	}
	
	@Override
	public NamedBitmap deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		int imageIdentifier = resources.getIdentifier(element.getAsString(), "drawable", androidContext.getPackageName());
		return new NamedBitmap(element.getAsString(), BitmapFactory.decodeResource(resources, imageIdentifier));
	}

	@Override
	public JsonElement serialize(NamedBitmap bitmap, Type type, JsonSerializationContext context) {
		return new JsonPrimitive(bitmap.getName());
	}
}