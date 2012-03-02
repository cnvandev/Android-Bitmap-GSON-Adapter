# Android Bitmap GSON Adapter #

This is a GSON adapter I came up with to serialize and deserialize Android bitmap drawable files to GSON. Right now it's a little clunky - you have to wrap your Bitmaps in a NamedBitmap which holds the name of the file - and it needs to be the same as the filename sans extension (as in what you'd use to request it from Android by name) - but I'll come up with a better idea. For now, it works.

## How To Use ##

The first thing to do is to adapt your code to use NamedBitmaps, and to set those names to equal the filenames. I know, I know, it sucks,
but the Bitmap .equals() method below Gingerbread (or so) uses the memory address, which makes it difficult to compare two bitmaps that have been loaded seperately. To load the actual Bitmap from a NamedBitmap, just call getBitmap() on it - it's not *too* difficult.

To use the Adapter in your GSON conversion process, you need to register it as a Type Adapter like so:

```java
NamedBitmapDeserializer bitmapAdapter = new NamedBitmapDeserializer(context);
GsonBuilder gsonBuilder = new GsonBuilder();
gsonBuilder.registerTypeAdapter(NamedBitmap.class, bitmapAdapter);
Gson gson = gsonBuilder.create();
```

Then, you just serialize it like this

```java
gson.toJson(yourObject);
```

and deserialize it like this

```java
gson.fromJson(reader, YourObject.class);
```

Ta da! Suggestions on how to make this not suck (besides "Use [sameAs(Bitmap)](http://developer.android.com/reference/android/graphics/Bitmap.html#sameAs%28android.graphics.Bitmap%29)!" Yeah buddy, I *get it*) are more than welcome.
