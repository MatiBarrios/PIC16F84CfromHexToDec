package Traductor;
import java.io.*;
public class traductor {
	public static void main(String args[]) {
		ConversorText conv=new ConversorText();
		int lastX=0, cont=0;
		String nombreFichero="prueba.txt",nombreEscrito="prueba2.txt", texto2="";
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
	           //Crear un objeto BufferedReader al que se le pasa 
	           //   un objeto FileReader con el nombre del fichero
	           br = new BufferedReader(new FileReader(nombreFichero));
	           bw = new BufferedWriter(new FileWriter(nombreEscrito));
	           //Leer la primera línea, guardando en un String
	           String texto = br.readLine();
	           //Repetir mientras no se llegue al final del fichero
	           while(texto != null)
	           {
	               //Hacer lo que sea con la línea leída
	        	   texto=texto.toUpperCase();
	               //marcamos la ultima x para comenzar a leer
	               for(int x=0;x<texto.length();x++) {if (texto.charAt(x)=='X') {lastX=x+1;}}
	               //de donde marcamos quitamos todos los espacios y lo metemos en texto2 para pasarlo
	               for(int x=lastX;x<texto.length();x++) {if (texto.charAt(x)!=' ') {texto2=texto2+texto.charAt(x);}}
	               texto2=conv.convertir(texto2);
	               bw.write(cont+"       "+texto2+"\n");
	               
	               texto2="";
	               cont++;
	               //conv.convertir(texto);
	               //Leer la siguiente línea
	               texto = br.readLine();
	           }
	        }
	        catch (FileNotFoundException e) {
	            System.out.println("Error: Fichero no encontrado");
	            System.out.println(e.getMessage());
	        }
	        catch(Exception e) {
	            System.out.println("Error de lectura del fichero");
	            System.out.println(e.getMessage());
	        }
	        finally {
	            try {
	                if(br != null) {
	                    br.close();
	                    bw.write(cont+"       "+"END");
	                	bw.close();
	                	System.out.println("El fichero fue transpuesto a lenguaje de pic con exito!");
	                	}
	            }
	            catch (Exception e) {
	                System.out.println("Error al cerrar el fichero");
	                System.out.println(e.getMessage());
	            }
	        }
	}
}
