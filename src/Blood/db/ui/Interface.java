package Blood.db.ui;
import java.io.*;
public class Interface {
	 public static void main(String[] args) 
	    {
	       
	 InputStreamReader inputStreamReader = null;
	 BufferedReader bufferedReader = null;
	 File fichero=null;
	 try {
	 inputStreamReader = new InputStreamReader(System.in);
	 bufferedReader = new BufferedReader(inputStreamReader);
	 //bucle infinito para mostrar nuestro menu hasta que pulsemos la opcion9
	 while (true){
	     //mostramos por pantalla nuestro menu
     System.out.println("Introduzca 1 para crear una tabla ");
	 System.out.println("Introduzca 2 insertar ");
	 System.out.println("Introduzca 3 mostrar ");
	 System.out.println("Introduzca 4 para salir ");
	 
int opcion = Integer.parseInt(bufferedReader.readLine());
switch (opcion){

case 1:

	break;
case 2: 
     
    break;
case 3: 


    break;
case 4:	//salir del programa
	System.exit(0);



}
}}
	 catch (IOException ex)
	 {
	     //entrara en el catch y se lanzara una excepcion en el caso de que no se haya leido correctamente
	     System.out.println("Se ha producido un error");
	     ex.printStackTrace();
	 } finally {
	     //cerramos todas las variables que contengan algo 
	     if (bufferedReader!=null){
	         try{ 
	             bufferedReader.close();
	         } catch (IOException ex ){
	             //volvera a lanzar una excepcion si no es posible 
	             ex.printStackTrace();
	         }
	     }
	      if (inputStreamReader!=null){
	         try{ 
	             inputStreamReader.close();
	         } catch (IOException ex ){
	             ex.printStackTrace();
	         }
	     }
	     
	 }
	    }}
	 
	   