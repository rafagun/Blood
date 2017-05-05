package Blood.db.ui;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;


//import java.sql.Connection;
import Blood.db.jdbc.*;
import Blood.db.pojos.Cells;
import Blood.db.pojos.Hospital;
import Blood.db.pojos.Illnes;
import Blood.db.pojos.Molecules;
import Blood.db.pojos.Nurse;
import Blood.db.pojos.Patient;
import Blood.db.pojos.Symptoms;
public class Interface {
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
		
	System.out.println("Introduce 1 to connect ");
	System.out.println("Introduce 2 to create ");
	System.out.println("Introduce 3 to insert ");
	System.out.println("Introduce 4 to show");
	System.out.println("Introduce 5 to delete");
	System.out.println("Introduce 6 to select");
	System.out.println("Introduce 7 to drop");
	System.out.println("Introduce 8 to update");
	System.out.println("Introduce 9 to exit");
	
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

while (true){
//mostramos por pantalla nuestro menu
menu1();

	selection=Integer.parseInt(bufferedReader.readLine());
	opcion=0;
if (selection==1){
	
DB_Hospital db_Hospital = new DB_Hospital();
	
	while (opcion!=9){
		System.out.println("Introduzca que opcion quiere");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

	case 1: 
	db_Hospital= new DB_Hospital();
	db_Hospital.SQLConnect();
	break;


	case 2: 
	db_Hospital.SQLCreate();
	System.out.println("The table hospital has been created");
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
	db_Hospital.SQLInsert(hospitalInterface);
	System.out.println("The information has been insert correctly");
	break;

	case 4:
	BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("If you want to search by name, introduce the name of the hospital you want to search by:");
		String nameSearch = reader1.readLine();
		Hospital hospital = db_Hospital.SQLSearch(nameSearch);
		System.out.println("name:" + hospital.getName()+ "       "+"location:"+ hospital.getLocation()+ "         "+ "range:" +hospital.getRange());

		break;

	case 5: 
		BufferedReader readerDelete = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduce the name of the hospital that you want to delete");
		String nHospital = readerDelete.readLine();
		db_Hospital.SQLDelete(nHospital);
		System.out.println("The hospital has been removed");
		break;
	 
	case 6:
		ArrayList<Hospital> lista= new ArrayList<>();
		lista = db_Hospital.SQLSelect();
		for (Hospital hosp: lista){
			System.out.println("name:" +hosp.getName()+"          "+"location:"+hosp.getLocation()+"         "+ "range:" +hosp.getRange());
		}
		break;
		// hay que borrar todo, no solo el contenido ya que al mostrarlo una vez borrado, muestra null no un error de que no existe
		
	case 7: 
		db_Hospital.SQLDrop();
		System.out.println("the table has dropped");
		break;

	case 8: 
	
		BufferedReader readerUpdate = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Insert the name of the hospital you want to change");
		String hospNameUpdate = readerUpdate.readLine();
		Hospital hospUpdate = db_Hospital.SQLSearch(hospNameUpdate);
		System.out.println("Enter the new name or press enter");
		String newName = readerUpdate.readLine();
		if (newName.equals("")){
			newName = hospUpdate.getName();
		}
		else{
		hospUpdate.setName(newName);
		}
		System.out.println("Enter the new location or press enter");
		String newLocation = readerUpdate.readLine();
		if (newLocation.equals("")){
			newLocation = hospUpdate.getLocation();
		}
		else {hospUpdate.setLocation(newLocation);}
		
		System.out.println("Input the new range or press enter");
		Integer newRange = Integer.parseInt(readerUpdate.readLine()); //cuando pongo espacio en blanco para que deje el mismo range falla
		if (newRange.equals("")){
			newRange = hospUpdate.getRange();
		}
		else{
		hospUpdate.setRange(newRange);
		}
		db_Hospital.SQLUpdate(hospUpdate, hospNameUpdate);
		
	case 9:	//salir del programa
		
	break;
	}
	}
}

 else if (selection == 2){//nurse
	DB_Nurse db_Nurse = new DB_Nurse();
	
	while (opcion!=9){
		System.out.println("Introduzca que opcion quiere");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

	case 1: 
	db_Nurse= new DB_Nurse();
	db_Nurse.SQLConnect();
	break;


	case 2: 
		db_Nurse.SQLCreate();
		System.out.println("The table has been created correctly");
	break;

	case 3: 
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Introduce the name of the nurse");
	String nurseName = reader.readLine();
	System.out.println("Introduce the photo of the nurse");
	String nursePhoto = reader.readLine();
	byte[] photo = nursePhoto.getBytes();
	Nurse nurseInterface = new Nurse (nurseName,photo);
	db_Nurse.SQLInsert(nurseInterface);
	System.out.println("the information has been added");
	break;

	case 4:
	BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduce the name of the nurse you want to search:");
		String nameSearch = reader1.readLine();
		Nurse nurse = db_Nurse.SQLSearch(nameSearch);
		System.out.println("name:" + nurse.getName()+ "     "+"photo:"+ nurse.getPhoto());

		break;

	case 5: 
		BufferedReader readerDelete = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduce the name of the nurse that you want to delete");
		String nNurse = readerDelete.readLine();
		db_Nurse.SQLDelete(nNurse);
		System.out.println("That nurse has been deleted");
		break;
	 
	case 6:
		ArrayList<Nurse> lista= new ArrayList<>();
		lista = db_Nurse.SQLSelect();
		for (Nurse nurse1: lista){
			System.out.println("name:" +nurse1.getName()+ "         "+"photo:"+nurse1.getPhoto());
		}
		break;
		
	case 7: 
		db_Nurse.SQLDrop();
		System.out.println("The table has been dropped");
		break;

    /**case 8: 
    	BufferedReader readerUpdate = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Insert the name of the nurse you want to change");
		String nurseNameUpdate = readerUpdate.readLine();
		Nurse nurseUpdate = db_Nurse.SQLSearch(nurseNameUpdate);
		System.out.println("Enter the new name or press enter");
		String newName = readerUpdate.readLine();
		if (newName.equals("")){
			newName = nurseUpdate.getName();
		}
		else{
		nurseUpdate.setName(newName);
		}
		System.out.println("Enter the new photo or press enter");
		String newPhoto = readerUpdate.readLine();
		if (newPhoto.equals("")){
			newPhoto = nurseUpdate.getPhoto();
		}
		else {nurseUpdate.setPhoto(newPhoto);}
		
		
		db_Nurse.SQLUpdate(nurseUpdate, nurseNameUpdate);
		
		**/
		
	case 9:	//salir del programa
		
	break;
	}
}
	
	
}
else if (selection == 3){//patient
	DB_Patient dbPatient = new DB_Patient();
	while (opcion!=9){
		System.out.println("Introduzca que opcion quiere");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

	case 1: 

	dbPatient.SQLConnect();
	break;


	case 2: 
	dbPatient.SQLCreate();
	break;

	case 3: 
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Introduce the name of the patient");
	String namePatient = reader.readLine();
	System.out.println("Introduce the blood type");
	String bloodPatient = reader.readLine();
	System.out.println("Introduce the age of the patient");
	String agePatient = reader.readLine();
	int age = Integer.parseInt(agePatient);
	System.out.println("Introduce the gender");
	String gender = reader.readLine();
	System.out.println("Introduce if the patient is smoker or not");
	String smokerpatient = reader.readLine();
	Boolean smoker = Boolean.parseBoolean(smokerpatient);
	Patient patient= new Patient (namePatient , age , bloodPatient, gender , smoker);
	dbPatient.SQLInsert(patient);
	break;

	case 4:
	BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("If you want to search by name, introduce the name of the hospital you want to search by:");
		String nameSearch = reader1.readLine();
		Patient patient1 = dbPatient.SQLSearch(nameSearch);
		System.out.println("name:" + patient1.getName()+ ""+"age"+ patient1.getAge()+ ""+ "range:" +patient1.getBlood()+""+"smoker:"+patient1.getSmoker()+""+"gender:"+patient1.getGender());

		break;

	case 5: 
		BufferedReader readerDelete = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduce the name of the hospital that you want to delete");
		String patientname = readerDelete.readLine();
		dbPatient.SQLDelete(patientname);
		break;
	 
	case 6:
		ArrayList<Patient> lista= new ArrayList<>();
		lista = dbPatient.SQLSelect();
		for (Patient patient11: lista){
			System.out.println("name:" +patient11.getName()+""+ "age:"+patient11.getAge()+""+ "blood type:" +patient11.getBlood()+" "+"blood type:" +patient11.getSmoker()+""+"blood type:" +patient11.getGender());
		}
		break;
		
	case 7: 
		dbPatient.SQLDrop();
		break;

	case 8: 
		BufferedReader readerUpdate = new BufferedReader(new InputStreamReader(System.in));
		Patient patient11= new Patient();
			System.out.println("Insert the ID of the patient ypu want to change");
			int id = Integer.parseInt(readerUpdate.readLine());
			System.out.println("Enter the new name of the hospital or press Inter");
			String name= readerUpdate.readLine();
			if (name.equals(null)){
			patient11.setName(name);	
			}
			System.out.println("2.Enter the new blood type or press Inter");
			String bloodtype= readerUpdate.readLine();
			if(bloodtype.equals(null)){
				patient11.setBlood(bloodtype);
			}
			System.out.println("3.Enter the new age of the patient or press Inter");
			String r= readerUpdate.readLine();
			if(r.equals(null)){
				int age1= Integer.parseInt(r);
				patient11.setAge(age1);
			}
			System.out.println("2.Change if it's smoker or not or press Inter");
			String smoker1= readerUpdate.readLine();
			Boolean smok = Boolean.parseBoolean(smoker1);
			if(smoker1.equals(null)){
				patient11.setSmoker(smok);
			}
			System.out.println("2.Enter the new gender or press Inter");
			String gender1= readerUpdate.readLine();
			if(bloodtype.equals(null)){
				patient11.setGender(gender1);
			}
			dbPatient.SQLUpdate(patient11);
			break;
		
	case 9:	//salir del programa
	
	break;
	}
}
}
else if (selection == 4){//cells
	DB_Cells db_cells = new DB_Cells();
	while (opcion!=9){
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
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Introduce the type of the cell");
	String cellType = reader.readLine();
	System.out.println("Introduce the minimum normal level of the cell");
	float minLevel =Float.parseFloat( reader.readLine());
	System.out.println("Introduce the maximum level of the cell");
	float maxLevel =Float.parseFloat(reader.readLine());

	Cells cells  = new Cells (cellType, minLevel, maxLevel);
	db_cells.SQLInsert(cells);
	System.out.println("cells are correctly intsert");
	break;

	case 4:
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduce the name of the cell you want to search by:");
		String nameSearch = reader1.readLine();
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
	while (opcion!=9){
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
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Introduce the type of the molecule");
	String moleculesType = reader.readLine();
	System.out.println("Introduce the minimum normal level of the cell");
	float minLevel =Float.parseFloat( reader.readLine());
	System.out.println("Introduce the maximum level of the cell");
	float maxLevel =Float.parseFloat(reader.readLine());

	Molecules molecules  = new Molecules (moleculesType, minLevel, maxLevel);
	f.SQLInsert(molecules);
	System.out.println("molecules correctly insert");
	break;

	case 4:
	BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduce the type of the molecule you want to search by:");
		String typeSearch = reader1.readLine();
		
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
			System.out.println("type:" + molecules1.getType()+ "     "+"low level of the molecules:"+ molecules1.getLowLevels()+ "       "+ "high level of the molecules:" +molecules1.getHighLevels());
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
	DB_Symptoms f = new DB_Symptoms();
	while (opcion!=9){
		System.out.println("Introduzca que opcion quiere");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

	case 1: 
	f.SQLConnect();
	break;


	case 2: 
	f.SQLCreate();
	System.out.println("the table has been created correctly");
	break;

	case 3: 
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Introduce the type of the symptom");
	String symptomsType = reader.readLine();
	System.out.println("Introduce the severity of the symptom");
	String symptomsSeverity = reader.readLine();
	Symptoms symptomsInterface  = new Symptoms(symptomsType,symptomsSeverity);
	f.SQLInsert(symptomsInterface);
	System.out.println("the information has been insert");
	break;

	case 4:
	BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduce the type of the symptom you want to search:");
		String typeSearch = reader1.readLine();
		
		Symptoms symptoms = f.SQLSearch(typeSearch);
		System.out.println("type:" + symptoms.getType()+ "        "+"severity:"+ symptoms.getSeverity());
		break;

	case 5: 
		System.out.println("Symptoms cannot be deleted");
		break;
	 
	case 6:
		ArrayList<Symptoms> lista= new ArrayList<>();
		lista = f.SQLSelect();
		for (Symptoms symptoms1: lista){
			System.out.println("type:" + symptoms1.getType()+ "       "+"severity:"+ symptoms1.getSeverity());
		}
		break;
		
	case 7: 
		f.SQLDrop();
		break;

	case 8:
		System.out.println("Symptoms cannot be modified");
		break;
		
	case 9:	//salir del programa
	f.SQLDisconnect();
	break;
	}
	}
}
else if (selection == 7){//illness
	DB_Illness f = new DB_Illness();
	while (opcion!=9){
		System.out.println("Introduzca que opcion quiere");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

	case 1: 
	f.SQLConnect();
	break;


	case 2: 
	f.SQLCreate();
	System.out.println("tables are created correctly");
	break;

	case 3: 
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Introduce the name of the illness");
	String illnessName = reader.readLine();
	System.out.println("Introduce the type of the illness");
	String illnessType =reader.readLine();
	System.out.println("Introduce if it is chronic or not");
	String illnessChronic = reader.readLine();
	Boolean chronic = Boolean.parseBoolean(illnessChronic);
	Illnes illnessInterface  = new Illnes(illnessName, illnessType, chronic);
	f.SQLInsert(illnessInterface);
	System.out.println("the information is already insert");
	break;

	case 4:
	BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduce the name of the illness you want to search:");
		String nameSearch = reader1.readLine();
		Illnes illness = f.SQLSearch(nameSearch);
		System.out.println("name:" + illness.getName()+ "      "+"type"+ illness.getType()+ "      "+ "chronic:" +illness.getChronic());

		break;

	case 5: 
		System.out.println("Illnesses cannot be deleted");
		break;
	 
	case 6:
		ArrayList<Illnes> lista= new ArrayList<>();
		lista = f.SQLSelect();
		for (Illnes illness1: lista){
			System.out.println("name:" + illness1.getName()+ "     "+"type:"+ illness1.getType()+ "       "+ "chronic:" +illness1.getChronic());
		}
		break;
		
	case 7: 
		f.SQLDrop();
		System.out.println("table has been removed");
		break;

	case 8:
		System.out.println("Illnesses cannot be modified");
		break;
		
	case 9:	//salir del programa
	break;
	}
}
}
else if (selection == 8){//exitt

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