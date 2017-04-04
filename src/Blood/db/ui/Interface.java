package Blood.db.ui;
import java.io.*;
import java.sql.SQLException;

//import java.sql.Connection;
import Blood.db.jdbc.*;
import Blood.db.pojos.Hospital;
public class Interface {
	 public static void main(String[] args) throws SQLException 
	    {
	       
	 InputStreamReader inputStreamReader = null;
	 BufferedReader bufferedReader = null;
	 DB_Manager f = null;
	 try {
	 inputStreamReader = new InputStreamReader(System.in);
	 bufferedReader = new BufferedReader(inputStreamReader);
	 //bucle infinito para mostrar nuestro menu hasta que pulsemos la opcion
	 while (true){
	     //mostramos por pantalla nuestro menu
     System.out.println("Introduzca 1 para conectar ");
	 System.out.println("Introduzca 2 crear ");
	 System.out.println("Introduzca 3 insertar ");
	 System.out.println("Introduzca 4 para mostrar");
	 System.out.println("Introduzca 5 para salir ");

	 
int opcion = Integer.parseInt(bufferedReader.readLine());
switch (opcion){

case 1: 
	f= new DB_Manager();
	f.SQLConnect();
	break;


case 2: 
     f.SQLCreate();
    break;
    
case 3: 
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Introduce the name of the hospital");
	String nameHosp = reader.readLine();
	System.out.println("Introduce the location of the hospital");
	String locationHosp = reader.readLine();
	System.out.println("Introduce the range of the hospital");
	String rangeHospital = reader.readLine();
	int rangeHosp = Integer.parseInt(rangeHospital);
	Hospital hospitalInterface = new Hospital (nameHosp,locationHosp,rangeHosp);
	f.SQLInsert(hospitalInterface);
	
    break;
case 5:	//salir del programa
	f.SQLDisconnect();
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
	 

	   