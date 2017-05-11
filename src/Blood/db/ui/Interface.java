package Blood.db.ui;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import java.sql.Connection;
import Blood.db.jdbc.*;
import Blood.db.jpa.GeneralMethods;
import Blood.db.jpa.JPAHospital;
import Blood.db.pojos.Cells;
import Blood.db.pojos.Hospital;
import Blood.db.pojos.Illnes;
import Blood.db.pojos.Molecules;
import Blood.db.pojos.Nurse;
import Blood.db.pojos.Patient;
import Blood.db.pojos.Symptoms;
public class Interface extends GeneralMethods implements GeneralMethodsJdbc {
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
	
	static void menu3(){
		System.out.println("Introduce the manager you want to work with");
		System.out.println("1.- JDBC manager");
		System.out.println("2.-JPA manager");
		System.out.println("3.-Exit");
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
		menu3();
		int sel= Integer.parseInt(bufferedReader.readLine());
		if (sel==1){
		
	GeneralMethodsJdbc.SQLConnect();
	
while (true){
//mostramos por pantalla nuestro menu
menu1();
	
	selection=Integer.parseInt(bufferedReader.readLine());
	opcion=0;
if (selection==1){
	
DB_Hospital db_Hospital = new DB_Hospital();
	
	while (opcion!=8){
		System.out.println("Introduce the option you want:");
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
		List<Hospital> lista;
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
		String Filephoto = bufferedReader.readLine();
		File photo= new File ("./photos/"+Filephoto);
		InputStream streamblob= new FileInputStream(photo);
		byte[] bytesblob= new byte [streamblob.available()];
		Nurse nurseInsert = new Nurse (nurseName,bytesblob);
		db_Nurse.SQLInsert(nurseInsert);
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
		System.out.println("Introduce the option you want: ");
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
	Patient patient= new Patient (namePatient , age , bloodPatient, smoker ,gender );
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

	case 7: 
		System.out.println("Insert the name of the patient you want to change");
		String patNameUpdate = bufferedReader.readLine();
		List<Patient> patUpdate = dbPatient.SQLSearch(patNameUpdate);
		Iterator<Patient> it = patUpdate.iterator();
		for(int i=0; it.hasNext(); i++){
			System.out.println(i+".-"+it.next());
		}
		System.out.println("Introduzca su correspondiente id");
		int id1 = Integer.parseInt(bufferedReader.readLine());
		
		
		
		System.out.println("Enter the new name or press enter");
		String newName = bufferedReader.readLine();
		if (newName.equals("")){
			newName = patUpdate.get(id1).getName();
		}
		else{
		patUpdate.get(id1).setName(newName);
		}
		System.out.println("Enter the new blood type or press enter");
		String newBlood = bufferedReader.readLine();
		if (newBlood.equals("")){
			newBlood = patUpdate.get(id1).getBlood();
		}
		else {patUpdate.get(id1).setBlood(newBlood);}
		
		System.out.println("Input the new age or press enter");
		String linea =bufferedReader.readLine(); //cuando pongo espacio en blanco para que deje el mismo range falla
		int newage = Integer.parseInt(linea);
		if (linea.equals("")){
			
			newage = patUpdate.get(id1).getAge();
		}
		else{
		patUpdate.get(id1).setAge(newage);
		}
		System.out.println("Enter the new gender or press enter");
		String newgender = bufferedReader.readLine();
		if (newgender.equals("")){
			newgender = patUpdate.get(id1).getGender();
		}
		else {patUpdate.get(id1).setGender(newgender);}
		System.out.println("Change if it is smoker or press enter");
		String newSmoker = bufferedReader.readLine();
		if (newSmoker.equals("")){
			smoker= Boolean.parseBoolean(newSmoker);
			smoker = patUpdate.get(id1).getSmoker();
		}
		else {patUpdate.get(id1).setSmoker(Boolean.parseBoolean(newSmoker));}
		dbPatient.SQLUpdate(patUpdate.get(id1));
	
	case 8:	//salir del programa
	
	break;
	}
}
}
else if (selection == 4){//cells
	DB_Cells db_cells = new DB_Cells();
	
	while (opcion!=8){
		System.out.println("Introduce the option you want: ");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

	case 1: 
	db_cells.SQLCreate();
	System.out.println("The table cells has been created");
	break;
	
	case 2:
	System.out.println("Introduce the type of the cell: ");
	String nameCell = bufferedReader.readLine();
	System.out.println("Introduce the low level of the cell: ");
	Float lowL = Float.parseFloat(bufferedReader.readLine());
	System.out.println("Introduce the high level of the cell: ");
	Float highL = Float.parseFloat(bufferedReader.readLine());
	Cells cellsInterface = new Cells(nameCell,lowL,highL);
	db_cells.SQLInsert(cellsInterface);
	System.out.println("The information has been introduced propertly");
	break;

	case 3: 
		System.out.println("Introduce the name of the cell you want to search:");
		String nameSearch = bufferedReader.readLine();
		List<Cells> cells = db_cells.SQLSearch(nameSearch);
		for (Cells cell: cells){
			System.out.println(cell);
		}

		break;

	case 4:
		System.out.println("Insert the name of the cell you want to delete: ");
		String cellDelete = bufferedReader.readLine();
		List<Cells> list = db_cells.SQLSearch(cellDelete);
		Iterator<Cells> ite = list.iterator();
		for(int i=0; ite.hasNext(); i++){
			System.out.println(i+".-"+ite.next());
		}
		int option1 = Integer.parseInt(bufferedReader.readLine());
		db_cells.SQLDelete(list.get(option1));
		System.out.println("The cell has been removed");
		break;

	case 5: 
		ArrayList<Cells> listCells = new ArrayList<>();
		listCells = db_cells.SQLSelect();
		for(Cells cell : listCells){
			System.out.println(cell);
		}
		break;
		
		
	case 6: 
		db_cells.SQLDrop();
		System.out.println("The table has been dropped");
		break;
	
	case 7: 
		System.out.println("Insert the name of the cell you want to change");
		String cellTypeUpdate = bufferedReader.readLine();
		List<Cells> cellsUpdate = db_cells.SQLSearch(cellTypeUpdate);
		Iterator<Cells> it = cellsUpdate.iterator();
		for(int i=0; it.hasNext(); i++){
			System.out.println(i+".-"+it.next());
		}
		int option = Integer.parseInt(bufferedReader.readLine());
		
		
		
		System.out.println("Enter the new name or press enter");
		String newName = bufferedReader.readLine();
		if (newName.equals("")){
			newName = cellsUpdate.get(option).getType();
		}
		else{
		cellsUpdate.get(option).setType(newName);
		}
		System.out.println("Enter the new low level or press enter");
		String newLevel = bufferedReader.readLine();
		Float newLowLevel = Float.parseFloat(newLevel);
		if (newLevel.equals("")){
			
			newLowLevel = cellsUpdate.get(option).getLowL();
		}
		else {cellsUpdate.get(option).setLowL(newLowLevel);}
		
		System.out.println("Enter the new high level or press enter");
		String newLevel2 = bufferedReader.readLine();
		Float newHighLevel = Float.parseFloat(newLevel2);
		if (newLevel.equals("")){
			
			newHighLevel = cellsUpdate.get(option).getHighL();
		}
		else {cellsUpdate.get(option).setHighL(newHighLevel);}
		
		db_cells.SQLUpdate(cellsUpdate.get(option));

	case 8:
		break;
	}
	}
	}
else if (selection == 5){//molecules
	DB_Molecules db_molecules = new DB_Molecules();
	while (opcion!=8){
		System.out.println("Introduce the option you want: ");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){
	
	case 1: 
		
		db_molecules.SQLCreate();
		System.out.println("The table molecules has been created");
		break;
		
		case 2:
		System.out.println("Introduce the type of the molecule: ");
		String nameMolecule = bufferedReader.readLine();
		System.out.println("Introduce the low level of the molecule: ");
		Integer lowL = Integer.parseInt(bufferedReader.readLine());
		System.out.println("Introduce the high level of the molecule: ");
		Integer highL = Integer.parseInt(bufferedReader.readLine());
		Molecules moleculesInterface = new Molecules(nameMolecule,highL,lowL);
		db_molecules.SQLInsert(moleculesInterface);
		System.out.println("The information has been introduced propertly");
		break;

		case 3: 
			System.out.println("Introduce the name of the molecule you want to search:");
			String nameSearch = bufferedReader.readLine();
			List<Molecules> molecules = db_molecules.SQLSearch(nameSearch);
			for (Molecules molecule: molecules){
				System.out.println(molecule);
			}
			break;

		case 4:
			System.out.println("Insert the name of the molecule you want to delete: ");
			String moleculeDelete = bufferedReader.readLine();
			List<Molecules> list = db_molecules.SQLSearch(moleculeDelete);
			Iterator<Molecules> ite = list.iterator();
			for(int i=0; ite.hasNext(); i++){
				System.out.println(i+".-"+ite.next());
			}
			int option1 = Integer.parseInt(bufferedReader.readLine());
			db_molecules.SQLDelete(list.get(option1));
			System.out.println("The molecule has been removed");
			break;

		case 5: 
			ArrayList<Molecules> listMolecules = new ArrayList<>();
			listMolecules = db_molecules.SQLSelect();
			for(Molecules molecule : listMolecules){
				System.out.println(molecule);
			}
			break;
			
			
		case 6: 
			db_molecules.SQLDrop();
			System.out.println("The table has been dropped");
			break;
		
		case 7: 
			System.out.println("Insert the name of the molecule you want to change");
			String moleculeTypeUpdate = bufferedReader.readLine();
			List<Molecules> moleculesUpdate = db_molecules.SQLSearch(moleculeTypeUpdate);
			Iterator<Molecules> it = moleculesUpdate.iterator();
			for(int i=0; it.hasNext(); i++){
				System.out.println(i+".-"+it.next());
			}
			int option = Integer.parseInt(bufferedReader.readLine());
			
			
			
			System.out.println("Enter the new name or press enter");
			String newName = bufferedReader.readLine();
			if (newName.equals("")){
				newName = moleculesUpdate.get(option).getType();
			}
			else{
			moleculesUpdate.get(option).setType(newName);
			}
			System.out.println("Enter the new low level or press enter");
			String newLevel = bufferedReader.readLine();
			Float newLowLevel = Float.parseFloat(newLevel);
			if (newLevel.equals("")){
				
				newLowLevel = moleculesUpdate.get(option).getLowLevels();
			}
			else {moleculesUpdate.get(option).setLowLevels(newLowLevel);}
			
			System.out.println("Enter the new high level or press enter");
			String newLevel2 = bufferedReader.readLine();
			float newHighLevel = Float.parseFloat (newLevel2);
			if (newLevel.equals("")){
				
				newHighLevel = moleculesUpdate.get(option).getHighLevels();
			}
			else {moleculesUpdate.get(option).setHighLevels(newHighLevel);}
			
			db_molecules.SQLUpdate(moleculesUpdate.get(option));

		case 8:
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
	GeneralMethodsJdbc.SQLDisconnect();
	break;
}

}
		}
		else if(sel==2){
			try{
				int option=0;
				while(option!=8){
				GeneralMethods.StartMethod();
			menu1();	
			System.out.println("Introduce an option:");
			
			 option =Integer.parseInt( bufferedReader.readLine());
			if (option == 1){
				JPAHospital jpa_Hospital = new JPAHospital();
				
				menu2();
				System.out.println("Choose an option");
				int sele = Integer.parseInt(bufferedReader.readLine());
				while(sele!=8)
				{
					if (sele ==1){//Insert
						Hospital hospital = new Hospital();
						System.out.println("Introduce a name");
						hospital.setName(bufferedReader.readLine());
						System.out.println("Introduce a location");
						hospital.setLocation(bufferedReader.readLine());
						System.out.print("Introduce a range");
						hospital.setRange(Integer.parseInt(bufferedReader.readLine()));
						jpa_Hospital.SQLInsert(hospital);
						
					}
					else if(sele == 2){//delete
						System.out.println("Introduce the name of the hospital that you want to delete");
						List<Hospital> hospitals = jpa_Hospital.SQLSearch(bufferedReader.readLine());
						Iterator it = hospitals.iterator();
						 
						for(int i=1; it.hasNext(); i++){
							System.out.println(i+".-"+it.toString());
						}
						System.out.println("Introduzca el hospital que desea seleccionar");
						int op =Integer.parseInt(bufferedReader.readLine());
						jpa_Hospital.SQLDelete(hospitals.get(op));// the hospital we were looking for
						
							
					}
					else if (sele == 3){//read
						System.out.println("Introduce the name of the hospital that you want to delete");
						List<Hospital> hospitals = jpa_Hospital.SQLSearch(bufferedReader.readLine());
						Iterator<Hospital> it = hospitals.iterator();
						for(int i=1; it.hasNext(); i++){
							System.out.println(i+".-"+it.toString());
						}
					}
					else if (sele == 4){//update
						System.out.println("Introduce the name of the hospital that you want to delete");
						List<Hospital> hospitals = jpa_Hospital.SQLSearch(bufferedReader.readLine());
						Iterator it = hospitals.iterator();
						 
						for(int i=1; it.hasNext(); i++){
							System.out.println(i+".-"+it.toString());
						}
						
						System.out.println("Introduzca el hospital que desea seleccionar");
						int op =Integer.parseInt(bufferedReader.readLine());
						Hospital newHosp = new Hospital();
						
						System.out.println("Introduce the changes you want to make in the name or press enter");
						String name = bufferedReader.readLine();
						if (name.equals("")) newHosp.setName(hospitals.get(op).getName());
						else newHosp.setName(name);
						
						System.out.println("Introduce the changes you want to make in the Location or press enter");
						
						String location = bufferedReader.readLine();
						if (location.equals("")) newHosp.setLocation(hospitals.get(op).getLocation());
						else newHosp.setLocation(location);
						
						System.out.println("Introduce the changes you want to make in the name or press enter");
						String lineaRange = bufferedReader.readLine();
						if (lineaRange.equals(""))  newHosp.setRange(hospitals.get(op).getRange());
						else{
							newHosp.setRange(Integer.parseInt(lineaRange));
						}
						
						jpa_Hospital.SQLUpdate(hospitals.get(op), newHosp);
					}
					
					else if (sele == 5){//
						
						
					}
				
				else if (option==2){//nurse
					menu2();
					Nurse nurse = new Nurse();
					System.out.println("Choose an option");
					 sele = Integer.parseInt(bufferedReader.readLine());
					if (sele == 1) {
						
						System.out.println("Introduce a name");
						nurse.setName(bufferedReader.readLine());
						System.out.println("Introduce the direction of the photo with its extension");
						nurse.setPhoto(bufferedReader.readLine());
						System.out.println("Introduce his/her hospital");
						
						
					}
				}
				}
				}
			
				}
			}
				catch(Exception ex){
					ex.printStackTrace();
				}
			
			
		//aqui jpa	
		}
		else if (sel==3){
			System.exit(0);
		}
	
	
}
	
	
}
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