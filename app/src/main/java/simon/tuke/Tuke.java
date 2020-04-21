package simon.tuke;
import android.os.AsyncTask;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.security.auth.callback.Callback;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;

public class Tuke
{

	private static DiskCache disk;
	private static MemoryCache memory;
	private static OnException error;
	private static boolean custommemory;
	public static void init(Context in)
	{
		init("TUKE", in.getNoBackupFilesDir().toString());
	}

    public static void init(String name, String path)
	{
		disk = new DiskCache(name, path);
		memory = new MemoryCache();
	}
	public static void setException(OnException in)
	{
		error = in;
	}
	public static void setCustomStrategy(boolean memoryStrategy)
	{
		custommemory = memoryStrategy;
	}

	private static String keytonew(String key)
	{
		return key.replaceAll(File.separator, "|");
	}
	public static <T extends Serializable> void write(String key, T in)
	{
		write(custommemory, key, in);
	}

	public static <T extends Serializable> void write(boolean ismemory, String key, T in)
	{
		key = keytonew(key);
		try
		{
			disk.write(key, in);
			if (ismemory)
				memory.put(key, in);
		}
		catch (IOException e)
		{
			if (error != null)
				error.error(e);
		}
	}
	public static void write(String key, Bitmap in)
	{
		write(custommemory, key, in);
	}
	public static void write(boolean ismemory, String key, Bitmap bit)
	{
		key = keytonew(key);
		try
		{
			disk.saveBitmap(key, bit);
			if (ismemory)
				memory.put(key, bit);
		}
		catch (IOException e)
		{
			if (error != null)
				error.error(e);
		}

	}
	public static Bitmap getBitmap( String key, Bitmap def)
	{
		return getBitmap(custommemory,key,def);
	}
	public static Bitmap getBitmap(boolean ismemory, String key, Bitmap def)
	{
		key = keytonew(key);
		Bitmap a= memory.get(key);
		if (a != null)
			return a;
		else
		{
			a=disk.getBitmap(key);
			if (a== null)
				return def;
			else
			{
				if (ismemory)
					memory.put(key, a);
				return a;
			}
		}
	}
	public static <T extends Serializable> T get(String key, T def)
	{
		return get(custommemory,key,def);
	}
	public static <T extends Serializable> T get(boolean ismemory,String key,T def)  {
		try{
			String mkey=keytonew(key);
			T a=memory.get(mkey);

			if(a!=null)
				return a;
			else{
				T b=disk.get(mkey);
				if(b!=null){
					if(ismemory)
						memory.put(mkey,b);
					return b;
				}
			}
		}catch(IOException e){
			if(error!=null)
				error.error(e);
		
		}catch(ClassNotFoundException e){
			if(error!=null)
				error.error(e);
			
		}
		return def;
	}
	public static <T extends Serializable> T get(boolean ismemory, String key)
	{
		return get(ismemory, key, null);
	}
	public static void clearMemory()
	{
		memory.removeAll();
	}
	public static void clearMemory(String key)
	{
		key = keytonew(key);
		memory.delete(key);
	}
	public static void clearDisk(String key)
	{
		key = keytonew(key);
		disk.delete(key);
	}
	public static void clearDisk()
	{
		clearMemory();
		disk.delete();
	}
	public interface Callback
	{
        void apply();
    }
	public interface OnException
	{
		void error(Exception e);
	}
	public static  void putBitmapAsync( final String key, final Bitmap value, final Callback callback)
	{
		putBitmapAsync(custommemory,key,value,callback);
	}
	public static  void putBitmapAsync(final boolean ismemory, final String key, final Bitmap value, final Callback callback)
	{
        new AsyncTask<Void, Integer, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params)
			{
				
                Tuke.write(ismemory, key, value);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean success)
			{
                super.onPreExecute();
                if (callback != null)
				 callback.apply();
                
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }
}
