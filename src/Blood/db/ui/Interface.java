package Blood.db.ui;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import java.sql.Connection;
import Blood.db.jdbc.*;
import Blood.db.pojos.Cells;
import Blood.db.pojos.Hospital;
import Blood.db.pojos.Illnes;
import Blood.db.pojos.Molecules;
import Blood.db.pojos.Nurse;
import Blood.db.pojos.Patient;
import Blood.db.pojos.Symptoms;
public class Interface extends GeneralMethods {
	static void menu1(){
		
	System.out.println("Introduce 1 to Hospital ");
	System.out.println("Introduce 2 to Nurse ");
	System.out.println("Introduce 3 to Patient ");
	System.out.println("Introduce 4 to Cells");
	System.out.println("Introduce 5 to Molecules");
	System.out.println("Introduce 6 to Symptoms");
	System.out.println("Introduce 7 to Illness");
	System.out.println("Introduce 8 to Exit");
	
	
	}
	
	
	
	static void menu2(){
	System.out.println("Introduce 1 to create table ");
	System.out.println("Introduce 2 to insert ");
	System.out.println("Introduce 3 to show");
	System.out.println("Introduce 4 to delete");
	System.out.println("Introduce 5 to select");
	System.out.println("Introduce 6 to drop");
	System.out.println("Introduce 7 to update");
	System.out.println("Introduce 8 to exit");
	
	}
	
public static void main(String[] args) throws SQLException 
{

InputStreamReader inputStreamReader = null;
BufferedReader bufferedReader = null;

try {
inputStreamReader = new InputStreamReader(System.in);
bufferedReader = new BufferedReader(inputStreamReader);
//bucle infinito para mostrar nuestro menu hasta que pulsemos la opcion

int selection=0;
int opcion=0;
	
	GeneralMethods.SQLConnect();
	
while (true){
//mostramos por pantalla nuestro menu
menu1();
	
	selection=Integer.parseInt(bufferedReader.readLine());
	opcion=0;
if (selection==1){
	
DB_Hospital db_Hospital = new DB_Hospital();
	
	while (opcion!=8){
		System.out.println("Introduzca que opcion quiere");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

	case 1: 
	db_Hospital.SQLCreate();
	System.out.println("The table hospital has been created");
	break;

	case 2: 
	
	System.out.println("Introduce the name of the hospital");
	String nameHosp = bufferedReader.readLine();
	System.out.println("Introduce the location of the hospital");
	String locationHosp = bufferedReader.readLine();
	System.out.println("Introduce the range of the hospital");
	String rangeHospital = bufferedReader.readLine();
	int rangeHosp = Integer.parseInt(rangeHospital);
	Hospital hospitalInterface = new Hospital (nameHosp,locationHosp,rangeHosp);
	db_Hospital.SQLInsert(hospitalInterface);
	System.out.println("The information has been insert correctly");
	break;

	case 3:
	
		System.out.println("Introduce the name of the hospital you want to search:");
		String nameSearch = bufferedReader.readLine();
		List<Hospital> hospitals = db_Hospital.SQLSearch(nameSearch);
		for (Hospital hospital: hospitals){
			System.out.println(hospital);
		}

		break;

	case 4: 
		
		System.out.println("Insert the name of the hospital you want to delete");
		String hospDelete = bufferedReader.readLine();
		List<Hospital> list = db_Hospital.SQLSearch(hospDelete);
		Iterator<Hospital> ite = list.iterator();
		for(int i=0; ite.hasNext(); i++){
			System.out.println(i+".-"+ite.next());
		}
		int option1 = Integer.parseInt(bufferedReader.readLine());
		db_Hospital.SQLDelete(list.get(option1));
		System.out.println("The hospital has been removed");
		break;
	 
	case 5:
		ArrayList<Hospital> lista= new ArrayList<>();
		lista = db_Hospital.SQLSelect();
		for (Hospital hosp: lista){
			System.out.println("name:" +hosp.getName()+"          "+"location:"+hosp.getLocation()+"         "+ "range:" +hosp.getRange());
		}
		break;
		// hay que borrar todo, no solo el contenido ya que al mostrarlo una vez borrado, muestra null no un error de que no existe
		
	case 6: 
		db_Hospital.SQLDrop();
		System.out.println("the table has dropped");
		break;

	case 7: 
	
		
		System.out.println("Insert the name of the hospital you want to change");
		String hospNameUpdate = bufferedReader.readLine();
		List<Hospital> hospUpdate = db_Hospital.SQLSearch(hospNameUpdate);
		Iterator<Hospital> it = hospUpdate.iterator();
		for(int i=0; it.hasNext(); i++){
			System.out.println(i+".-"+it.next());
		}
		int option = Integer.parseInt(bufferedReader.readLine());
		
		
		
		System.out.println("Enter the new name or press enter");
		String newName = bufferedReader.readLine();
		if (newName.equals("")){
			newName = hospUpdate.get(option).getName();
		}
		else{
		hospUpdate.get(option).setName(newName);
		}
		System.out.println("Enter the new location or press enter");
		String newLocation = bufferedReader.readLine();
		if (newLocation.equals("")){
			newLocation = hospUpdate.get(option).getLocation();
		}
		else {hospUpdate.get(option).setLocation(newLocation);}
		
		System.out.println("Input the new range or press enter");
		String linea =bufferedReader.readLine(); //cuando pongo espacio en blanco para que deje el mismo range falla
		int newRange = Integer.parseInt(linea);
		if (linea.equals("")){
			
			newRange = hospUpdate.get(option).getRange();
		}
		else{
		hospUpdate.get(option).setRange(newRange);
		}
		db_Hospital.SQLUpdate(hospUpdate.get(option));
		
	case 8:	//salir del programa
		
	break;
	}
	}
}

 else if (selection == 2){//nurse
	DB_Nurse db_Nurse = new DB_Nurse();
	
	while (opcion!=8){
		System.out.println("Introduce the option you want");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

	case 1: 
		db_Nurse.SQLCreate();
		System.out.println("The table has been created correctly");
	break;

	case 2: 
		System.out.println("Introduce the name of the nurse");
		String nurseName = bufferedReader.readLine();
		System.out.println("Introduce the direction of the photo with its extension");
		String nursePhoto = bufferedReader.readLine();
		byte[] photo = nursePhoto.getBytes();
		Nurse nurseInterface = new Nurse (nurseName,photo);
		db_Nurse.SQLInsert(nurseInterface);
		System.out.println("the information has been added");
		break;

	case 3: 
		System.out.println("Introduce the name of the nurse you want to search:");
		String nameSearch = bufferedReader.readLine();
		Nurse nurse = db_Nurse.SQLSearch(nameSearch);
		System.out.println("name:" + nurse.getName()+ "     "+"photo:"+ nurse.getPhoto());

		break;
	
	case 4://queda hacer todo lo de hospital pero aqui.
		System.out.println("Introduce the name of the nurse that you want to delete");
		String nNurse = bufferedReader.readLine();
		/*db_Nurse.SQLDelete(nNurse);*/
		System.out.println("That nurse has been deleted");
		break;
	 
	

	case 5: 
		
		ArrayList<Nurse> lista= new ArrayList<>();
		lista = db_Nurse.SQLSelect();
		for (Nurse nurse1: lista){
			System.out.println("name:" +nurse1.getName()+ "         "+"photo:"+nurse1.getPhoto());
		}
		break;
	 
	case 6:
		
		db_Nurse.SQLDrop();
		System.out.println("The table has been dropped");
		break;
	case 7: 
	
    /**case 8: 
		System.out.println("Insert the name of the nurse you want to change");
		String nurseNameUpdate = bufferedReader.readLine();
		Nurse nurseUpdate = db_Nurse.SQLSearch(nurseNameUpdate);
		System.out.println("Enter the new name or press enter");
		String newName = bufferedReader.readLine();
		if (newName.equals("")){
			newName = nurseUpdate.getName();
		}
		else{
		nurseUpdate.setName(newName);
		}
		System.out.println("Enter the new photo or press enter");
		String newPhoto = bufferedReader.readLine();
		if (newPhoto.equals("")){
			newPhoto = nurseUpdate.getPhoto();
		}
		else {nurseUpdate.setPhoto(newPhoto);}
		
		
		db_Nurse.SQLUpdate(nurseUpdate, nurseNameUpdate);
		
		**/
	case 8: //salir del programa
		
	break;
	}
}
	
	
}
else if (selection == 3){//patient
	DB_Patient dbPatient = new DB_Patient();
	while (opcion!=8){
		System.out.println("Introduzca que opcion quiere");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

	case 1: 
	dbPatient.SQLCreate();
	break;

	case 2: 
	
	System.out.println("Introduce the name of the patient");
	String namePatient = bufferedReader.readLine();
	System.out.println("Introduce the blood type");
	String bloodPatient = bufferedReader.readLine();
	System.out.println("Introduce the age of the patient");
	String agePatient = bufferedReader.readLine();
	int age = Integer.parseInt(agePatient);
	System.out.println("Introduce the gender");
	String gender = bufferedReader.readLine();
	System.out.println("Introduce if the patient is smoker or not");
	String smokerpatient = bufferedReader.readLine();
	Boolean smoker = Boolean.parseBoolean(smokerpatient);
	Patient patient= new Patient (namePatient , age , bloodPatient, gender , smoker);
	dbPatient.SQLInsert(patient);
	break;

	case 3:
	
		System.out.println("Introduce the name of the patient you want to search:");
		String nameSearch = bufferedReader.readLine();
		List<Patient> patients = dbPatient.SQLSearch(nameSearch);
		for (Patient patient1: patients){
			System.out.println(patient1);
		}
		break;

	case 4: 

		
		
		System.out.println("Introduce the name of the patient that you want to delete");
		String patientname = bufferedReader.readLine();
		List<Patient> list= dbPatient.SQLSearch(patientname);
		Iterator<Patient> ite = list.iterator();
		for(int i=0; ite.hasNext(); i++){
			System.out.println(i+".-"+ite.next());
		}
		System.out.println("Introduzca su respectivo id");
		int id = Integer.parseInt(bufferedReader.readLine());
		dbPatient.SQLDelete(list.get(id));
		System.out.println("The hospital has been removed");
		 
		break;

	case 5:
		ArrayList<Patient> lista= new ArrayList<>();
		lista = dbPatient.SQLSelect();
		for (Patient patient11: lista){
			System.out.println("name:" +patient11.getName()+"       "+ "age:"+patient11.getAge()+"        "+ "blood type:" +patient11.getBlood()+" "+"gender:" +patient11.getGender()+""+"smoker:" +patient11.getSmoker());
		}
		break;
		
	case 6: 
		dbPatient.SQLDrop();
		break;

	case 7: /** 	System.out.println("Insert the name of the hospital you want to change");
		String hospNameUpdate = bufferedReader.readLine();
		List<Hospital> hospUpdate = db_Hospital.SQLSearch(hospNameUpdate);
		Iterator<Hospital> it = hospUpdate.iterator();
		for(int i=0; it.hasNext(); i++){
			System.out.println(i+".-"+it.next());
		}
		int option = Integer.parseInt(bufferedReader.readLine());
		
		
		
		System.out.println("Enter the new name or press enter");
		String newName = bufferedReader.readLine();
		if (newName.equals("")){
			newName = hospUpdate.get(option).getName();
		}
		else{
		hospUpdate.get(option).setName(newName);
		}
		System.out.println("Enter the new location or press enter");
		String newLocation = bufferedReader.readLine();
		if (newLocation.equals("")){
			newLocation = hospUpdate.get(option).getLocation();
		}
		else {hospUpdate.get(option).setLocation(newLocation);}
		
		System.out.println("Input the new range or press enter");
		String linea =bufferedReader.readLine(); //cuando pongo espacio en blanco para que deje el mismo range falla
		int newRange = Integer.parseInt(linea);
		if (linea.equals("")){
			
			newRange = hospUpdate.get(option).getRange();
		}
		else{
		hospUpdate.get(option).setRange(newRange);
		}
		db_Hospital.SQLUpdate(hospUpdate.get(option));
		
	
	**/
		/**System.out.println("Insert the name of the patient you want to change");
		String patientNameUpdate = bufferedReader.readLine();
		List<Patient> patientUpdate = dbPatient.SQLSearch(patientNameUpdate);
		Iterator<Patient> it = patientUpdate.iterator();
		for(int i=0; it.hasNext(); i++){
			System.out.println(i+".-"+it.next());
		}
		int option = Integer.parseInt(bufferedReader.readLine());
		
		
		
		System.out.println("Enter the new name or press enter");
		String newName = bufferedReader.readLine();
		if (newName.equals("")){
			newName = hospUpdate.get(option).getName();
		}
		else{
		hospUpdate.get(option).setName(newName);
		}
		System.out.println("Enter the new location or press enter");
		String newLocation = bufferedReader.readLine();
		if (newLocation.equals("")){
			newLocation = hospUpdate.get(option).getLocation();
		}
		else {hospUpdate.get(option).setLocation(newLocation);}
		
		System.out.println("Input the new range or press enter");
		String linea =bufferedReader.readLine(); //cuando pongo espacio en blanco para que deje el mismo range falla
		int newRange = Integer.parseInt(linea);
		if (linea.equals("")){
			
			newRange = hospUpdate.get(option).getRange();
		}
		else{
		hospUpdate.get(option).setRange(newRange);
		}
		db_Hospital.SQLUpdate(hospUpdate.get(option), hospUpdate.get(option).getId());
**/
	
	case 8:	//salir del programa
	
	break;
	}
}
}
else if (selection == 4){//cells
	DB_Cells db_cells = new DB_Cells();
	while (opcion!=8){
		System.out.println("Introduzca que opcion quiere");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

	case 1: 
	db_cells= new DB_Cells();
	db_cells.SQLConnect();
	break;


	case 2: 
	db_cells.SQLCreate();
	break;

	case 3: 
	System.out.println("Introduce the type of the cell");
	String cellType = bufferedReader.readLine();
	System.out.println("Introduce the minimum normal level of the cell");
	float minLevel =Float.parseFloat( bufferedReader.readLine());
	System.out.println("Introduce the maximum level of the cell");
	float maxLevel =Float.parseFloat(bufferedReader.readLine());

	Cells cells  = new Cells (cellType, minLevel, maxLevel);
	db_cells.SQLInsert(cells);
	System.out.println("cells are correctly intsert");
	break;

	case 4:
		System.out.println("Introduce the name of the cell you want to search by:");
		String nameSearch = bufferedReader.readLine();
		Cells cell = db_cells.SQLSearch(nameSearch);
		System.out.println("type:" + cell.getType()+ "         "+"lowl:"+ cell.getLowL()+ "         "+ "highL:" +cell.getHighL());

		break;

	case 5: 
		System.out.println("cells cannot be deleted");
		break;
	 
	case 6:
		// hay que borrar todo, no solo el contenido ya que al mostrarlo una vez borrado, muestra null no un error de que no existe
		ArrayList<Cells> lista= new ArrayList<>();
		lista = db_cells.SQLSelect();
		for (Cells cell1: lista){
			System.out.println("type:" + cell1.getType()+ "     "+"low level of the cell:"+ cell1.getLowL()+ "      "+ "high level of the cells:" +cell1.getHighL());
		}
		break;
		
	case 7: 
		db_cells.SQLDrop();
		System.out.println("the tables has been dropped");
		break;

	case 8:
		System.out.println("cells cannot be modified");
		break;
		
	case 9:	//salir del programa
	
	break;
	}
	}
	}
else if (selection == 5){//molecules
	DB_Molecules f = new DB_Molecules();
	while (opcion!=8){
		System.out.println("Introduzca que opcion quiere");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

	case 1: 
	f.SQLConnect();
	break;


	case 2: 
	f.SQLCreate();
	break;

	case 3: 
	System.out.println("Introduce the type of the molecule");
	String moleculesType = bufferedReader.readLine();
	System.out.println("Introduce the minimum normal level of the cell");
	float minLevel =Float.parseFloat( bufferedReader.readLine());
	System.out.println("Introduce the maximum level of the cell");
	float maxLevel =Float.parseFloat(bufferedReader.readLine());

	Molecules molecules  = new Molecules (moleculesType, minLevel, maxLevel);
	f.SQLInsert(molecules);
	System.out.println("molecules correctly insert");
	break;

	case 4:
		System.out.println("Introduce the type of the molecule you want to search by:");
		String typeSearch = bufferedReader.readLine();
		
		Molecules moleculesInterface = f.SQLSearch(typeSearch);
		System.out.println("type:" + moleculesInterface.getType()+ "     "+"low level of the cell:"+ moleculesInterface.getLowLevels()+ "      "+ "high level of the cells:" +moleculesInterface.getHighLevels());

		break;

	case 5: 
		System.out.println("Molecules cannot be deleted");
		break;
	 
	case 6:
		ArrayList<Molecules> lista= new ArrayList<>();
		lista = f.SQLSelect();
		for (Molecules molecules1: lista){
			System.out.println(molecules1);
		}
		break;
		
	case 7: 
		f.SQLDrop();
		System.out.println("Table molecules has been deleted");
		break;

	case 8:
		System.out.println("molecules cannot be modified");
		break;
		
	case 9:	//salir del programa
	
	break;
	}
	}
}
else if (selection == 6){//Sympthomps
	DB_Symptoms db_symptoms = new DB_Symptoms();
	while (opcion!=8){
		System.out.println("Introduzca que opcion quiere");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

	case 1: try {
		db_symptoms.SQLCreate();
	System.out.println("the table has been created correctly");
	}
	catch (Exception e) {
		e.printStackTrace();
		System.out.println("Esta tabla ya existe");
		}	
	break;

	case 2: 
	System.out.println("Introduce the type of the symptom");
	String symptomsType = bufferedReader.readLine();
	System.out.println("Introduce the severity of the symptom");
	String symptomsSeverity = bufferedReader.readLine();
	Symptoms symptomsInterface  = new Symptoms(symptomsType,symptomsSeverity);
	db_symptoms.SQLInsert(symptomsInterface);
	System.out.println("the information has been insert");
	break;

	case 3:
		System.out.println("Introduce the type of the symptom you want to search:");
		String typeSearch = bufferedReader.readLine();
		
		List<Symptoms> symptoms = db_symptoms.SQLSearch(typeSearch);
		for (Symptoms symptom: symptoms){
			System.out.println(symptom);
		}
		break;
		


	case 4: 
		System.out.println("Symptoms cannot be deleted");
		break;
	 
	case 5: 
		ArrayList<Symptoms> lista= new ArrayList<>();
		lista = db_symptoms.SQLSelect();
		for (Symptoms symptoms1: lista){
			System.out.println(symptoms1);
		}
		break;
		
	case 6: 
		db_symptoms.SQLDrop();
		break;

	case 7:
		System.out.println("Symptoms cannot be modified");
		break;
		
	case 8:	//salir del programa
	break;
	}
	}
}
else if (selection == 7){//illness
	DB_Illness db_illness = new DB_Illness();
	while (opcion!=8){
		System.out.println("Introduzca que opcion quiere");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

	case 1: 
	db_illness.SQLCreate();
	System.out.println("tables are created correctly");
	break;

	case 2: 
	System.out.println("Introduce the name of the illness");
	String illnessName = bufferedReader.readLine();
	System.out.println("Introduce the type of the illness");
	String illnessType =bufferedReader.readLine();
	System.out.println("Introduce if it is chronic or not");
	String illnessChronic = bufferedReader.readLine();
	//solo inserta false
	Boolean chronic = Boolean.parseBoolean(illnessChronic);
	Illnes illnessInterface  = new Illnes(illnessName, illnessType, chronic);
	db_illness.SQLInsert(illnessInterface);
	System.out.println("the information is already insert");
	break;

	case 3:
		System.out.println("Introduce the name of the illness you want to search:");
		String nameSearch = bufferedReader.readLine();
		List<Illnes> illness = db_illness.SQLSearch(nameSearch);
		for(Illnes illnes: illness){
			System.out.println(illnes);
		}
		

		break;

	case 4: 
		System.out.println("Illnesses cannot be deleted");
		break;
	 
	case 5:
		ArrayList<Illnes> lista= new ArrayList<>();
		lista = db_illness.SQLSelect();
		for (Illnes illness1: lista){
			System.out.println(illness1);
		}
		break;
		
	case 6: 
		db_illness.SQLDrop();
		System.out.println("table has been removed");
		break;

	case 7:
		System.out.println("Illnesses cannot be modified");
		break;
		
	case 8:	//salir del programa
	break;
	}
}
}
else if (selection == 8){//exitt
	GeneralMethods.SQLDisconnect();
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

//HOLA	   	   