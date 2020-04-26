import java.util.HashMap;
import java.util.Map;

/**
 * 
 */

/**
 * @author Orlando
 * @author olivverde
 * 
 * Class's purpose: Factory pattern in to order to call HashMap & SplayTree
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
