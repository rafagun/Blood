package Blood.db.ui;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;


//import java.sql.Connection;
import Blood.db.jdbc.*;
import Blood.db.pojos.Hospital;
public class Interface {
public static void main(String[] args) throws SQLException 
{

InputStreamReader inputStreamReader = null;
BufferedReader bufferedReader = null;
DB_Hospital f = null;
try {
inputStreamReader = new InputStreamReader(System.in);
bufferedReader = new BufferedReader(inputStreamReader);
//bucle infinito para mostrar nuestro menu hasta que pulsemos la opcion
while (true){
//mostramos por pantalla nuestro menu
System.out.println("Introduce 1 to connect ");
System.out.println("Introduce 2 to create ");
System.out.println("Introduce 3 to insert ");
System.out.println("Introduce 4 to show");
System.out.println("Introduce 5 to delete");
System.out.println("Introduce 6 to select");
System.out.println("Introduce 7 to drop");
System.out.println("Introduce 8 to update");
System.out.println("Introduce 9 to exit");


int opcion = Integer.parseInt(bufferedReader.readLine());
switch (opcion){

case 1: 
f= new DB_Hospital();
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

case 4:
BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
System.out.println("Introduce the name of the hospital that you want to search");
String nameHospKB = reader1.readLine();
Hospital hospital = f.SQLSearch(nameHospKB);
System.out.println("name:" + hospital.getName()+ ""+"location:"+ hospital.getLocation()+ ""+ "range:" +hospital.getRange());
break;

case 5: 
	BufferedReader readerDelete = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Introduce the name of the hospital that you want to delete");
	String nHospital = readerDelete.readLine();
	f.SQLDelete(nHospital);
	break;
 
case 6:
	ArrayList<Hospital> lista= new ArrayList<>();
	lista = f.SQLSelect();
	for (Hospital hosp: lista){
		System.out.println("name:" +hosp.getName()+ "location:"+hosp.getLocation()+ "range:" +hosp.getRange());
	}
	break;
	
case 7: 
	f.SQLDrop();
	break;

case 8: 
	BufferedReader readerUpdate = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Insert the ID of the hospital you want to change");
		//int idNumber = Integer.parseInt(readerUpdate.readLine());
		int id = Integer.parseInt(readerUpdate.readLine());
		Hospital hosp= new Hospital();
		System.out.println("Enter the new name of the hospital or press Inter");
		String name= readerUpdate.readLine();
		if (name.equals(null)){
		hosp.setName(name);	
		}
		System.out.println("2.Enter the new location of the hospital or press Inter");
		String location= readerUpdate.readLine();
		if(location.equals(null)){
			hosp.setLocation(location);
		}
		System.out.println("3.Enter the new range of the hospital or press Inter");
		String r= readerUpdate.readLine();
		if(r.equals(null)){
			int range= Integer.parseInt(r);
			hosp.setRange(range);
		}
		f.SQLUpdate(hosp);
		break;
	
case 9:	//salir del programa
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

	   