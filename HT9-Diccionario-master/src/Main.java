import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author olivverde
 * @author Orlando
 * @author Codigo para leer archivos sacado de: https://blog.openalfa.com/como-leer-un-fichero-de-texto-linea-a-linea-en-java
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException  {
		// TODO Auto-generated method stub
		int n1 = 0; //Contador para la posicion de la primera coma
		int n2 = 0; //Contador para la separacon de las palabras
		int n3 = 0; //Contador para guardar desde la linea donde empiezan las palabras
		String palabraS = ""; //String para guardar por pedazos la palabra a traducir
		String texto = ""; //String para guardar el texto ya traducido
		Scanner teclado = new Scanner (System.in);
		//Cambiar esto para que le de al usuario la opcion de ingresar el nombre de los documentos (cuando ya este listo el programa)
		System.out.println("Introduzca el nombre del primer documento (Spanish.txt)"); 
		String documento;	
		String documento2;
		String palabra = " "; //String que guarda la palabra escrita en ingles
		String traduccion; //String que guarda la traduccion
		documento = teclado.nextLine();
		System.out.println("Introduzca el nombre del segundo documento (texto.txt)"); 
		documento2 = teclado.nextLine();
		//----------------------------Aqui deberia ir el Factory---------------------------------
		Factory <String,String> factory = new Factory<String, String>();
		System.out.println("1. HashMap 2.SplayTree"); 
		int tipo = teclado.nextInt();
		Map <String,String> mapa = factory.getMap(tipo);	
		//------------------Leer el archivo y ordenar los pacientes por prioridad----------------------
	    try {
	      FileReader fr = new FileReader("Spanish.txt");
	      BufferedReader br = new BufferedReader(fr);
	      String linea;	      
	      while((linea = br.readLine()) != null) {	
	    	  n1 = 0;
	    	  n2 = 0;
	    	  palabra = "";
	    	  traduccion = "";
	    	  for (int i = 0; i<linea.length(); i++) {
	    		  //Obtengo la posicion del primer tab (la separacion no son espacios, son tabs (ーー;) ), para empezar a copiar desde ahi la traduccion
	    		  if (linea.substring(i,i+1).equals("\t")){
	    			 n1 = i; 
	    		  }
	    		//Obtengo la posicion hasta donde termina la primera traduccion
	    		  else if (linea.substring(i, i+1).equals(",")||linea.substring(i, i+1).equals(";")||linea.substring(i, i+1).equals(":")){
	    			  n2 = i;		    			  
	    			  break;
	    		  }
	    	  }
	    	  if (n3 >= 11) {
	    		  palabra = linea.substring(0,n1); 
	    	  }
	    	  if (n2!=0 && n3 >= 11) {
	    		  traduccion = linea.substring(n1+1,n2);
	    	  }else if (n2 == 0 && n3 >= 11) {
	    		  traduccion = linea.substring(n1+1,linea.length());
	    	  }
	    	  n3++;
	    	  mapa.put(palabra, traduccion);
	    	  //Borrar despues
	    	  //System.out.println("Palabra en ingles: " + palabra);
	    	  //System.out.println("Palabra traducida: " + traduccion);
	    	  //System.out.println("Linea: " + n3);
	      }
	      	        
	      fr.close();
	    }
	    catch(Exception e) {
	      System.out.println("Excepcion leyendo fichero "+ documento + ": " + e);
	    }
	   //--------------------------------------Fin del lector de primer archivo e inicio de la leida del segundo archivo--------------------------
	    try {
		      FileReader fr = new FileReader("texto.txt");
		      BufferedReader br = new BufferedReader(new FileReader("texto.txt"));
		      String linea;	      
		      while((linea = br.readLine()) != null) {	
		    	  n1 = 0;
		    	  n2 = 0;
		    	  palabra = "";
		    	  palabra = linea;
		    	  
		    	  //Borrar despues
		    	  //System.out.println("Palabra en ingles: " + palabra);
		    	  //System.out.println("Palabra traducida: " + traduccion);
		    	  //System.out.println("Linea: " + n3);
		      }
		      	        
		      fr.close();
		    }
		    catch(Exception e) {
		      System.out.println("Excepcion leyendo fichero "+ documento2 + ": " + e);
		    }
	    
	  //--------------------------------------Fin del lector del segundo archivo--------------------------
	    System.out.println("Palabra: " + palabra);
	    for (int i = 0; i<palabra.length();i++) {
	    	if (!palabra.substring(i,i+1).equals(" ")) {
	    		palabraS = palabraS + palabra.substring(i, i+1);
	    	}else {
	    		if (mapa.containsKey(palabraS)) {
	    			texto = texto + mapa.get(palabraS) + " ";
	    		}else {
	    			
	    			texto = texto + "*" + palabraS + "* ";
	    		}
	    		palabraS = "";
	    	}
	    }
	    if (mapa.containsKey(palabraS)) {
			texto = texto + mapa.get(palabraS) + " ";
		}else {
			
			texto = texto + "*" + palabraS + "* ";
		}
	    System.out.println("Palabra traducida" + texto);
	}

}
