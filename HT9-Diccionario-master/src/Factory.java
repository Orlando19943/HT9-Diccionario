import java.util.HashMap;
import java.util.Map;

/**
 * 
 */

/**
 * @author Orlando
 *
 */
public class Factory <K,V> {
	public Map<K,V> getMap (int tipo){
		switch (tipo){
			case 1: //HashMap
				return new HashMap<K,V>();
			case 2:
				return new splayTree();
		}
		return null;
		
	}

}
