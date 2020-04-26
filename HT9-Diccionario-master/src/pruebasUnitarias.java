import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author Orlando
 * @author olivverde
 *
 */
class pruebasUnitarias {
	Map <String, String> mapa = new splayTree<String,String>();
	Map <String, String> mapa2 = new HashMap<String,String>();
	@Test
	void putGetTest1() {
		String palabra = "Soy una prueba";
		String clave = "Soy una clave";
		mapa.put(clave, palabra);
		assertEquals (mapa.get(clave),palabra);	
		
	}
	@Test
	void containsKeyTest1() {
		String palabra = "Soy una prueba x2";
		String clave = "Soy una clave x2";
		mapa.put(clave, palabra);
		assertEquals (mapa.containsKey(clave),true);		
	}
	@Test
	void putGetTest2() {
		String palabra = "Soy una prueba";
		String clave = "Soy una clave";
		mapa2.put(clave, palabra);
		assertEquals (mapa2.get(clave),palabra);	
		
	}
	@Test
	void containsKeyTest2() {
		String palabra = "Soy una prueba x2";
		String clave = "Soy una clave x2";
		mapa2.put(clave, palabra);
		assertEquals (mapa2.containsKey(clave),true);		
	}

}
