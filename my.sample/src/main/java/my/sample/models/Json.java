package my.sample.models;



import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * A utility class for converting objects
 * to and from JSON.
 * 
 * @author CDavies
 *
 */
public final class Json {
	
	private static final Gson parser = new Gson();

	/**
	 * Converts the JSON string to the specified list.  This
	 * method converts both single and array type JSON
	 * strings.  The return type will always be a list of the
	 * specified type even for non-array JSON strings.
	 * 
	 * @param <T>  The return type.
	 * @param json  The JSON string to convert.
	 * @param tr  The type reference object.
	 * @return  A list of converted objects.  Returns a list of
	 *                one object for non-array JSON strings.  Returns
	 *                an empty list if the specified JSON string is null
	 *                or empty.
	 */
	@SuppressWarnings({ "serial", "unchecked" })
	public static final <T> List<T> fromJson(final String json, TypeReference<List<T>> tr){
		
		if (json==null || json.isEmpty()){
			return Collections.emptyList();
		}
		
		if (json.startsWith("[")){
			return parser.fromJson(json, tr.getType());
		} else {
			final ParameterizedType pt = (ParameterizedType) ((ParameterizedType)tr.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			return new ArrayList<T>(){{
				add(parser.fromJson(json, (Class<T>)pt.getActualTypeArguments()[0]));
			}};
		}
		
	}
	
	/**
	 * Converts the specified object to a JSON string.
	 * 
	 * @param o  The object to convert.
	 * @return  Returns null if the specified object is null.
	 */
	public static final String toJson(Object o){
		if (o==null){
			return null;
		}
		return parser.toJson(o);
	}

	/**
	 * A class that provides the ability to retain generics type
	 * information.  See {@link  http://gafter.blogspot.com/2006/12/super-type-tokens.html}
	 * 
	 * @author CDavies
	 *
	 * @param <T>  The expected return type.
	 */
	public static abstract class TypeReference<T> extends TypeToken<T>{
		public TypeReference(){
			super();
		}
	}
	
}

