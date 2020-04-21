package simon.tuke;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.io.Serializable;
import android.os.Parcelable;

public class MemoryCache {
	private static ConcurrentMap<String,Object> hash;
	public MemoryCache(){
		hash=new ConcurrentHashMap<String,Object>();
	}
	public static void put(String key,Object obj){
		delete(key);
		hash.put(key,obj);
	}
	public static <T> T get(String key){
		if(hash.containsKey(key))
			return (T)hash.get(key);
		else
			return null;
	}
	public static void delete(String key){
		if(hash.containsKey(key))
		hash.remove(key);
	}
	public static void removeAll(){
		hash.clear();
	}
}
