package Blood.db.ui;
import java.io.*;


import java.sql.SQLException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBException;

//import java.sql.Connection;
import Blood.db.jdbc.*;
import Blood.db.jpa.FunctionsDB;
import Blood.db.jpa.JPAHospital;
import Blood.db.jpa.JPANurse;
import Blood.db.jpa.eManager;
import Blood.db.pojos.*;

import Blood.db.xml.XML_Manager;
import Blood.db.xml.Xml2Html;

public class Menu {
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
	
	
	static void inicilizeTables(){
		try{
		FunctionsDB<Hospital> hosp = new DB_Hospital();
		hosp.SQLCreate();
		}
		catch(TableCreationException e ){
			e.tableExists();

		}
		try{
		FunctionsDB<Nurse> nur = new DB_Nurse();
		nur.SQLCreate();
		}
		catch(TableCreationException e ){
			e.tableExists();
			}
		try{
		FunctionsDB<Cells> cells = new DB_Cells();
		cells.SQLCreate();
		}
		catch(TableCreationException e ){
			e.tableExists();
			}
		try{
		FunctionsDB<Molecules> molecules = new DB_Molecules();
		molecules.SQLCreate();
		}		
		catch(TableCreationException e ){
			e.tableExists();
			 			}
		try{
		FunctionsDB<Symptoms> syn = new DB_Symptoms();
		syn.SQLCreate();
		}
		catch(TableCreationException e ){
			 e.tableExists();
			}
		try{
		FunctionsDB<Illnes> ill = new DB_Illness();
		ill.SQLCreate();
		}
		catch(TableCreationException e ){
			 e.tableExists();
			}
		try{
		FunctionsDB<Patient> pat = new DB_Patient();
		pat.SQLCreate();
		}
		catch(TableCreationException e ){
			e.tableExists();
			}
			
		
	}
	static void menu2(){

		System.out.println("Introduce 1 to insert ");
		System.out.println("Introduce 2 to show");
		System.out.println("Introduce 3 to delete");
		System.out.println("Introduce 4 to select");
		System.out.println("Introduce 5 to update");
		System.out.println("Introduce 6 to exit");
	}
	static void menuHosp(){
		System.out.println("Introduce 1 to insert ");
		System.out.println("Introduce 2 to show");
		System.out.println("Introduce 3 to delete");
		System.out.println("Introduce 4 to select");
		System.out.println("Introduce 5 to update");
		System.out.println("Introduce 6 to create a htlm file:");
		System.out.println("Introduce 7 to exit");
	}
	
	static void menupat(){

	System.out.println("Introduce 1 to insert ");
	System.out.println("Introduce 2 to show");
	System.out.println("Introduce 3 to delete");
	System.out.println("Introduce 4 to select");
	System.out.println("Introduce 5 to update");
	System.out.println("Introduce 6 to assign relationships in the classes that correspond:");
	System.out.println("Introduce 7 to exit");

	}
	static void menuSymp(){
		System.out.println("Introduce 1 to insert ");
		System.out.println("Introduce 2 to show");
		System.out.println("Introduce 3 to delete");
		System.out.println("Introduce 4 to select");
		System.out.println("Introduce 5 to update");
		System.out.println("Introduce 6 to pass from java to xml:");
		System.out.println("Introduce 7 to pass from xml to java:");
		System.out.println("Introduce 8 to assign relationships: ");
		System.out.println("Introduce 9 to exit");

	}
public static void main(String[] args) throws SQLException, JAXBException 
{

InputStreamReader inputStreamReader = null;
BufferedReader bufferedReader = null;

try {
inputStreamReader = new InputStreamReader(System.in);
bufferedReader = new BufferedReader(inputStreamReader);


int selection=0;
int opcion=0;
	while (true){
		
		
	Connect.SQLConnect();
   eManager.StartMethod();
	
while (true){

menu1();
	
	selection=Integer.parseInt(bufferedReader.readLine());
	inicilizeTables();
	opcion=0;
if (selection==1){
	
FunctionsDB<Hospital> JPAHospital = new JPAHospital();
	
	while (opcion!=7){
		System.out.println("Introduce the option you want:");
		menuHosp();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){


	case 1: 
		Hospital hospital = new Hospital();
		System.out.println("Introduce a name");
		hospital.setName(bufferedReader.readLine());
		System.out.println("Introduce a location");
		hospital.setLocation(bufferedReader.readLine());
		System.out.print("Introduce a range");
		hospital.setRange(Integer.parseInt(bufferedReader.readLine()));
		JPAHospital.SQLInsert(hospital);
	break;

	case 2:
	{
		System.out.println("Introduce the name of the hospital that you want to search");
		List<Hospital> hospitals = JPAHospital.SQLSearch(bufferedReader.readLine());
		Iterator<Hospital> it = hospitals.iterator();
		for(int i=0; it.hasNext(); i++){
			System.out.println(i+".-"+it.next());
		}}
break;
	case 3: 
	{
		System.out.println("Introduce the name of the hospital that you want to delete");
		List<Hospital> hospitalss = JPAHospital.SQLSearch(bufferedReader.readLine());
		Iterator it = hospitalss.iterator();
		 
		for(int i=0; it.hasNext(); i++){
			System.out.println(i+".-"+it.next());
		}
		System.out.println("Introduce the hospital you want to select");
		int op =Integer.parseInt(bufferedReader.readLine());
		JPAHospital.SQLDelete(hospitalss.get(op));// the hospital we were looking for
	} break;
	case 4:
		List<Hospital> lista;
		lista = JPAHospital.SQLSelect();
		for (Hospital hosp: lista){
			System.out.println("name:" +hosp.getName()+"          "+"location:"+hosp.getLocation()+"         "+ "range:" +hosp.getRange());
		}
		break;

	case 5: 
	{

		
		System.out.println("Introduce the name of the hospital that you want to change");
		List<Hospital> hospitals = JPAHospital.SQLSearch(bufferedReader.readLine());
		Iterator it = hospitals.iterator();
		 
		for(int i=0; it.hasNext(); i++){
			System.out.println(i+".-"+it.next());
		}
		
		System.out.println("Introduce the hospital that want to change");
		int op =Integer.parseInt(bufferedReader.readLine());
		Hospital newHosp = new Hospital();
		
		System.out.println("Introduce the changes you want to make in the name or press enter");
		String name1 = bufferedReader.readLine();
		if (name1.equals("")) newHosp.setName(hospitals.get(op).getName());
		else newHosp.setName(name1);
		
		System.out.println("Introduce the changes you want to make in the Location or press enter");
		
		String location = bufferedReader.readLine();
		if (location.equals("")) newHosp.setLocation(hospitals.get(op).getLocation());
		else newHosp.setLocation(location);
		
		System.out.println("Introduce the changes you want to make in the range or press enter");
		String lineaRange = bufferedReader.readLine();
		if (lineaRange.equals(""))  newHosp.setRange(hospitals.get(op).getRange());
		else{
			newHosp.setRange(Integer.parseInt(lineaRange));
		}
		newHosp.setId(hospitals.get(op).getId());
		System.out.println(newHosp);
		System.out.println(""+hospitals.get(op));
		JPAHospital.SQLUpdate(hospitals.get(op), newHosp);
	} break;
	case 8:
	{
		Xml2Html xml2html = new Xml2Html();
		xml2html.simpleTransform();
		break;
	}
	case 7:	

		
	break;
	}
	}
}

 else if (selection == 2){//nurse
	NurseInterface db_Nurse = new DB_Nurse();
	
	while (opcion!=6){
		System.out.println("Introduce the option you want");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){



	case 1: 
		System.out.println("Introduce the name of the nurse");
		String nurseName = bufferedReader.readLine();
		System.out.println("Introduce the direction of the photo with its extension");
		String Filephoto = bufferedReader.readLine();
		File photo= new File ("./photos/"+Filephoto);
		InputStream streamblob= new FileInputStream(photo);
		byte[] bytesblob= new byte [streamblob.available()];
		Nurse nurseInsert = new Nurse (nurseName,bytesblob);
		System.out.println("Introduce the name of the hospital when the nurse work");
		String namehosp= bufferedReader.readLine();
		FunctionsDB<Hospital> db_hospital= new DB_Hospital();
		List<Hospital> list = db_hospital.SQLSearch(namehosp);
		Iterator<Hospital> ite = list.iterator();
		for(int i=0; ite.hasNext(); i++){
			System.out.println(i+".-"+ite.next());
		}
		System.out.println("Introduce the hospital");
		int idhosp = Integer.parseInt(bufferedReader.readLine());
		db_Nurse.SQLInsert(nurseInsert);
		db_Nurse.SQLnurseHosp(nurseInsert.getId(), list.get(idhosp).getId());
		System.out.println("The information has been added");
		break;

	case 2: 
		System.out.println("Introduce the name of the nurse you want to search:");
		String nameSearch = bufferedReader.readLine();
		List<Nurse> nurses = db_Nurse.SQLSearch(nameSearch);
		for (Nurse nurse: nurses){
			System.out.println(nurse);
		}

		break;
	

	
	case 3:
		System.out.println("Insert the name of the Nurse you want to delete");
		String nurseDelete = bufferedReader.readLine();
		List<Nurse> list1 = db_Nurse.SQLSearch(nurseDelete);
		Iterator<Nurse> ite1 = list1.iterator();
		for(int i=0; ite1.hasNext(); i++){
			System.out.println(i+".-"+ite1.next());
		}
		int option1 = Integer.parseInt(bufferedReader.readLine());
		db_Nurse.SQLDelete(list1.get(option1));
		System.out.println("The nurse has been removed");
		break;
		
		
		
	 
	

	case 4: 
		
		List<Nurse> lista= new ArrayList<>();
		lista = db_Nurse.SQLSelect();
		for (Nurse nurse1: lista){
			System.out.println("name:" +nurse1.getName()+ "         "+"photo:"+nurse1.getPhoto());
		}
		break;
	 
	
	case 5: 
		System.out.println("Nurses cannot change any of these attributes");
		break;

		
	case 6: 
		
	break;
	}
}
	
	
}
else if (selection == 3){//patient
	PatientInterface dbPatient = new DB_Patient();
	while (opcion!=7){
		System.out.println("Introduce the option you want: ");
		menupat();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){



	case 1: 
		
		System.out.println("Introduce the name of the patient");
		String namePatient = bufferedReader.readLine();
		System.out.println("Introduce the blood type");
		String bloodPatient = bufferedReader.readLine();
		System.out.println("Introduce the age of the patient: ");
		Integer agePatient = Integer.parseInt(bufferedReader.readLine());
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//LocalDate age = LocalDate.parse(agePatient, formatter);
		System.out.println("Introduce the gender");
		String gender = bufferedReader.readLine();
		System.out.println("Introduce if the patient is smoker or not");
		String smokerpatient = bufferedReader.readLine();
		Boolean smoker = Boolean.parseBoolean(smokerpatient);
		Patient patient= new Patient (namePatient , agePatient , bloodPatient, gender, smoker );
		dbPatient.SQLInsert(patient);
		break;



	case 2:
	
		System.out.println("Introduce the name of the patient you want to search:");
		String nameSearch = bufferedReader.readLine();
		List<Patient> patients = dbPatient.SQLSearch(nameSearch);
		for (Patient patient1: patients){
			System.out.println(patient1);
		}
		break;

	case 3: 

		System.out.println("Insert the name of the patient you want to delete");
		String patientDelete = bufferedReader.readLine();
		List<Patient> list1 = dbPatient.SQLSearch(patientDelete);
		Iterator<Patient> ite1 = list1.iterator();
		for(int i=0; ite1.hasNext(); i++){
			System.out.println(i+".-"+ite1.next());
		}
		int option1 = Integer.parseInt(bufferedReader.readLine());
		dbPatient.SQLDelete(list1.get(option1));
		System.out.println("The patient has been removed");
		break;
	
	case 4:
		List<Patient> lista= new ArrayList<>();
		lista = dbPatient.SQLSelect();
		for (Patient patient11: lista){
			System.out.println("name:" +patient11.getName()+"       "+ "age:"+patient11.getAge()+"        "+ "blood type:" +patient11.getBlood()+"         "+"gender:" +patient11.getGender()+"          "+"smoker:" +patient11.getSmoker());
		}
		break;
		
	

	case 5: 
		System.out.println("Insert the name of the patient you want to change");
		String patNameUpdate = bufferedReader.readLine();
		List<Patient> patUpdate = dbPatient.SQLSearch(patNameUpdate);
		Iterator<Patient> it = patUpdate.iterator();
		for(int i=0; it.hasNext(); i++){
			System.out.println(i+".-"+it.next());
		}
		System.out.println("Introduce the patient");
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
		String newAge = bufferedReader.readLine(); //cuando pongo espacio en blanco para que deje el mismo range falla
		//DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (newAge.equals("")){
		    int age = Integer.parseInt(newAge);
			age = patUpdate.get(id1).getAge();
		}
		else{
			patUpdate.get(id1).setAge(Integer.parseInt(newAge));
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
	

	case 6 : 
		int op=0;
		while(op!=6){
			System.out.println("Introduce 1 if you want the relation nurse-patient,"
				+ "2 for the relation patient-illnes , 3 for the relation patient-cells , 4 for the relation patient-molecules"
				+ "5 for the relation patient-symptoms y 6 exit");
		 op= Integer.parseInt(bufferedReader.readLine());
		switch(op){
		case 1: {
			FunctionsDB<Nurse> db_Nurse = new DB_Nurse();
			System.out.println("Introduce the name of the nurse");
			String name= bufferedReader.readLine();
			List<Nurse> nurs=db_Nurse.SQLSearch(name);
			Iterator<Nurse> it3=nurs.iterator();
			for(int i=0; it3.hasNext(); i++){
				System.out.println(i+".-"+it3.next());
			}
			System.out.println("Introduce the nurse");
			int idnurse = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Introduce the patient's name");
			String namepat= bufferedReader.readLine();
			List<Patient> pat = dbPatient.SQLSearch(namepat);
			Iterator<Patient> it2 = pat.iterator();
			for(int i=0; it2.hasNext(); i++){
				System.out.println(i+".-"+it2.next());
			}
			System.out.println("Introduce the patient ");
			int idpat = Integer.parseInt(bufferedReader.readLine());
			dbPatient.SQLRelationNP(nurs.get(idnurse).getId(),pat.get(idpat).getId());
			
			
		} break;
		 case 2: {
			FunctionsDB<Illnes> db_illnes = new DB_Illness();
			System.out.println("Introduce the name of the illness");
			String name= bufferedReader.readLine();
			List<Illnes> ills=db_illnes.SQLSearch(name);
			Iterator<Illnes> it3=ills.iterator();
			for(int i=0; it3.hasNext(); i++){
				System.out.println(i+".-"+it3.next());
			}
			System.out.println("Introduce the illnes");
			int idill = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Introduce the name of the patient");
			String namepat= bufferedReader.readLine();
			List<Patient> pat = dbPatient.SQLSearch(namepat);
			Iterator<Patient> it2 = pat.iterator();
			for(int i=0; it2.hasNext(); i++){
				System.out.println(i+".-"+it2.next());
			}
			System.out.println("Introduce the patient");
			int idpat = Integer.parseInt(bufferedReader.readLine());
			dbPatient.SQLRelationPI(ills.get(idill).getId(), pat.get(idpat).getId());
		} break;
		case 3 :
		{
			FunctionsDB<Cells> db_cells = new DB_Cells();
			System.out.println("Introduce the name of the cell");
			String name= bufferedReader.readLine();
			List<Cells> cells=db_cells.SQLSearch(name);
			Iterator<Cells> it3=cells.iterator();
			for(int i=0; it3.hasNext(); i++){
				System.out.println(i+".-"+it3.next());
			}
			System.out.println("Introduce the cell");
			int idcells = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Introduce the name of the patient");
			String namepat= bufferedReader.readLine();
			List<Patient> pat = dbPatient.SQLSearch(namepat);
			Iterator<Patient> it2 = pat.iterator();
			for(int i=0; it2.hasNext(); i++){
				System.out.println(i+".-"+it2.next());
			}
			System.out.println("Introduce the patient");
			int idpat = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Introduce the level of the cell");
			int level = Integer.parseInt(bufferedReader.readLine());
			dbPatient.SQLRelationPC(cells.get(idcells).getId(), pat.get(idpat).getId(), level);
		} break;
		
		case 4 : {
			FunctionsDB<Molecules> db_molecules = new DB_Molecules();
			System.out.println("Introduce the molecules to the one who want to assign un patient");
			String name= bufferedReader.readLine();
			List<Molecules> mols=db_molecules.SQLSearch(name);
			Iterator<Molecules> it3=mols.iterator();
			for(int i=0; it3.hasNext(); i++){
				System.out.println(i+".-"+it3.next());
			}
			System.out.println("Introduce the molecule");
			int idmol = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Introduce the patient you want to assign");
			String namepat= bufferedReader.readLine();
			List<Patient> pat = dbPatient.SQLSearch(namepat);
			Iterator<Patient> it2 = pat.iterator();
			for(int i=0; it2.hasNext(); i++){
				System.out.println(i+".-"+it2.next());
			}
			System.out.println("Introduce the patient");
			int idpat = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Introduce the level");
			int level = Integer.parseInt(bufferedReader.readLine());
			dbPatient.SQLRelationPM(idmol, idpat, level);
		} break;
		case 5:
		{
			FunctionsDB db_symptoms = new DB_Symptoms();
			System.out.println("Introduce the name of the sympthom");
			String name= bufferedReader.readLine();
			List<Symptoms> symps=db_symptoms.SQLSearch(name);
			Iterator<Symptoms> it3=symps.iterator();
			for(int i=0; it3.hasNext(); i++){
				System.out.println(i+".-"+it3.next());
			}
			System.out.println("Introduce the sympthom");
			int idsymp = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Introduce the name of the patient");
			String namepat= bufferedReader.readLine();
			List<Patient> pat = dbPatient.SQLSearch(namepat);
			Iterator<Patient> it2 = pat.iterator();
			for(int i=0; it2.hasNext(); i++){
				System.out.println(i+".-"+it2.next());
			}
			System.out.println("Introduce the patient");
			int idpat = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Introduce the level of risk for the patient");
			String level = bufferedReader.readLine();
			System.out.println("Introduce the place of the sympthom");
			String place = bufferedReader.readLine();
			dbPatient.SQLRelationPS(symps.get(idsymp).getId(), pat.get(idpat).getId(), level , place);
		}
		}
		}
		
		break;
	case 7:	
		
	break;
	}
}
}
else if (selection == 4){//cells
	FunctionsDB<Cells> db_cells = new DB_Cells();
	
	while (opcion!=6){
		System.out.println("Introduce the option you want: ");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){


	
	case 1:
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

	case 2: 
		System.out.println("Introduce the name of the cell you want to search:");
		String nameSearch = bufferedReader.readLine();
		List<Cells> cells = db_cells.SQLSearch(nameSearch);
		for (Cells cell: cells){
			System.out.println(cell);
		}

		break;

	case 3:
	
		System.out.println("The cell cannot been removed");
		break;

	case 4: 
		List<Cells> listCells = new ArrayList<>();
		listCells = db_cells.SQLSelect();
		for(Cells cell : listCells){
			System.out.println(cell);
		}
		break;
		
		
	
	case 5: 
		System.out.println("Cells cannot be update");
		break;
	case 6:
		break;
	}
	}
	}
else if (selection == 5){//molecules
	FunctionsDB<Molecules> db_molecules = new DB_Molecules();
	while (opcion!=6){
		System.out.println("Introduce the option you want: ");
		menu2();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){

		
		case 1:
		System.out.println("Introduce the type of the molecule: ");
		String nameMolecule = bufferedReader.readLine();
		System.out.println("Introduce the low level of the molecule: ");
		int lowL =  Integer.parseInt(bufferedReader.readLine());
		System.out.println("Introduce the high level of the molecule: ");
		int highL = Integer.parseInt(bufferedReader.readLine());
		Molecules moleculesInterface = new Molecules(nameMolecule,lowL,highL);
		db_molecules.SQLInsert(moleculesInterface);
		System.out.println("The information has been introduced propertly");
		break;

		case 2: 
			System.out.println("Introduce the name of the molecule you want to search:");
			String nameSearch = bufferedReader.readLine();
			List<Molecules> molecules = db_molecules.SQLSearch(nameSearch);
			for (Molecules molecule: molecules){
				System.out.println(molecule);
			}
			break;

		case 3:
			
			System.out.println("molecules cannot be removed");
			break;

		case 4: 
			List<Molecules> listMolecules = new ArrayList<>();
			listMolecules = db_molecules.SQLSelect();
			for(Molecules molecule : listMolecules){
				System.out.println(molecule);
			}
			break;
			

		case 5: 
			System.out.println("molecules cannot be changed");
			break;
		case 6:
			break;
	
	}
	}
}
else if (selection == 6){//Sympthomps
	FunctionsDB<Symptoms> db_symptoms = new DB_Symptoms();
	while (opcion!=8){
		System.out.println("Introduce the option you want");
		menuSymp();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){



	case 1: 
	System.out.println("Introduce the type of the symptom");
	String symptomsType = bufferedReader.readLine();
	System.out.println("Introduce the severity of the symptom");
	String symptomsSeverity = bufferedReader.readLine();
	Symptoms symptomsInterface  = new Symptoms(symptomsType,symptomsSeverity);
	db_symptoms.SQLInsert(symptomsInterface);
	System.out.println("the information has been insert");
	break;

	case 2:
		System.out.println("Introduce the type of the symptom you want to search:");
		String typeSearch = bufferedReader.readLine();
		
		List<Symptoms> symptoms = db_symptoms.SQLSearch(typeSearch);
		for (Symptoms symptom: symptoms){
			System.out.println(symptom);
		}
		break;
		
	case 3: 
		System.out.println("Symptoms cannot be deleted");
		break;
	 
	case 4: 
		List<Symptoms> lista= new ArrayList<>();
		lista = db_symptoms.SQLSelect();
		for (Symptoms symptoms1: lista){
			System.out.println(symptoms1);
		}
		break;


	case 5:
		System.out.println("Symptoms cannot be modified");
		break;
		
	case 6:
		List<Symptoms> newList = new ArrayList<Symptoms> ();
		DB_Symptoms symptomsJava2Xml = new DB_Symptoms();
		newList = symptomsJava2Xml.SQLSelect();
		XML_Manager xmlManager = new XML_Manager ();
		xmlManager.Java2XmlListofSymptoms(newList);
		break;
		
	case 7: 
		XML_Manager xmlManager1 = new XML_Manager ();
		ListofSymptoms listSymptoms = xmlManager1.Xml2JavaListofSymptoms();
		
		for(Symptoms symptom : listSymptoms.getListofSymptoms()){
			System.out.println(symptom);
		}
		break;
	case 8:
		IllnessInterface dbIllnes = new DB_Illness();
		System.out.println("Introduce the name of the illnes:");
		String name= bufferedReader.readLine();
		List<Illnes> illnes = dbIllnes.SQLSearch(name);
		Iterator<Illnes> it3= illnes.iterator();
		for(int i=0; it3.hasNext(); i++){
			System.out.println(i+".-"+it3.next());
		}
		System.out.println("Introduce the illnes: ");
		int idIllnes = Integer.parseInt(bufferedReader.readLine());
		System.out.println("Introduce the symptom: ");
		String nameSymptom= bufferedReader.readLine();
		DB_Symptoms dbSymptoms = new DB_Symptoms ();
		List<Symptoms> symp = dbSymptoms.SQLSearch(nameSymptom);
		Iterator<Symptoms> it2 = symp.iterator();
		for(int i=0; it2.hasNext(); i++){
			System.out.println(i+".-"+it2.next());
		}
		System.out.println("Introduce the symptom:");
		int idSymp = Integer.parseInt(bufferedReader.readLine());
		
		System.out.println("Introduce the place where the desease is produced:");
		String place  = bufferedReader.readLine();
		dbIllnes.SQLRelationIS(illnes.get(idIllnes).getId(),symp.get(idSymp).getId(),place);
		break;

	case 9:
		break;
		
	}
	}
}
else if (selection == 7){//illness
	IllnessInterface db_illness = new DB_Illness();
	while (opcion!=7){
		System.out.println("Introduce the option you want");
		menupat();
	opcion=Integer.parseInt(bufferedReader.readLine());
	switch (opcion){


	case 1: 
	System.out.println("Introduce the name of the illness");
	String illnessName = bufferedReader.readLine();
	System.out.println("Introduce the type of the illness");
	String illnessType =bufferedReader.readLine();
	System.out.println("Introduce if it is chronic or not");
	String illnessChronic = bufferedReader.readLine();
	Boolean chronic = Boolean.parseBoolean(illnessChronic);
	Illnes illness  = new Illnes(illnessName, illnessType, chronic);
	db_illness.SQLInsert(illness);
	System.out.println("the information is already insert");
	break;

	case 2:
		System.out.println("Introduce the name of the illness you want to search:");
		String nameSearch = bufferedReader.readLine();
		List<Illnes> illnesses = db_illness.SQLSearch(nameSearch);
		for(Illnes illnes: illnesses){
			System.out.println(illnes);
		}
		

		break;

	case 3: 
		System.out.println("Illnesses cannot be deleted");
		break;
	 
	case 4:
		List<Illnes> lista= new ArrayList<>();
		lista = db_illness.SQLSelect();
		for (Illnes illness1: lista){
			System.out.println(illness1);
		}
		break;
		

	case 5:
		System.out.println("Illnesses cannot be modified");
		break;
	
	case 6 :
		IllnessInterface dbIllnes  = new DB_Illness();
		System.out.println("Introduce the option you want:");
		System.out.println("1-.Relation Illnes-Molecules");
		System.out.println("2-.Relation Illnes-Cells");
		int optionRelat= Integer.parseInt(bufferedReader.readLine());
		switch(optionRelat){
		case 1: {
			System.out.println("Introduce the name of the molecule:");
			String name = bufferedReader.readLine();
			FunctionsDB<Molecules> dbmolecules = new DB_Molecules ();
			List<Molecules> molecules = dbmolecules.SQLSearch(name);
			Iterator<Molecules> it3 = molecules.iterator();
			for(int i=0; it3.hasNext(); i++){
				System.out.println(i+".-"+it3.next());
			}
			System.out.println("Introduce the molecule: ");
			int idMolecule = Integer.parseInt(bufferedReader.readLine());
			
			System.out.println("Introduce the illnes name:");
			String nameIllnes= bufferedReader.readLine();
			List<Illnes> ill = dbIllnes.SQLSearch(nameIllnes);
			Iterator<Illnes> it2 = ill.iterator();
			for(int i=0; it2.hasNext(); i++){
				System.out.println(i+".-"+it2.next());
			}
			System.out.println("Introduce the illnes: ");
			int idIllnes = Integer.parseInt(bufferedReader.readLine());
			
			System.out.println("Introduce the level of the molecule: ");
			int level = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Introduce the level (low-high) of the molecule: ");
			String lowHigh = bufferedReader.readLine();
			dbIllnes.SQLRelationIM(molecules.get(idMolecule).getId(),ill.get(idIllnes).getId(), level, lowHigh);
			} 
			break;
		 case 2: {
				System.out.println("Introduce the name of the cell:");
				String name = bufferedReader.readLine();
				DB_Cells dbCells = new DB_Cells ();
				List<Cells> cells = dbCells.SQLSearch(name);
				Iterator<Cells> it3 = cells.iterator();
				for(int i=0; it3.hasNext(); i++){
					System.out.println(i+".-"+it3.next());
				}
				System.out.println("Introduce the the cell: ");
				int idCell = Integer.parseInt(bufferedReader.readLine());
				System.out.println("Introduce the illnes name:");
				String nameIllnes= bufferedReader.readLine();
				List<Illnes> ill = dbIllnes.SQLSearch(nameIllnes);
				Iterator<Illnes> it2 = ill.iterator();
				for(int i=0; it2.hasNext(); i++){
					System.out.println(i+".-"+it2.next());
				}
				System.out.println("Introduce the illnes: ");
				int idIllnes = Integer.parseInt(bufferedReader.readLine());
				
				System.out.println("Introduce the level of the cell: ");
				int level = Integer.parseInt(bufferedReader.readLine());
				System.out.println("Introduce the level (low-high) of the cell: ");
				String lowHigh = bufferedReader.readLine();
				dbIllnes.SQLRelationIC(cells.get(idCell).getId(),ill.get(idIllnes).getId(), level, lowHigh);		
			}
		break;
		
		}
		
	case 7:	
	break;
	
	}
}
}
else if (selection == 8){//exit
	Connect.SQLDisconnect();
	System.exit(0);
	break;
}

}
		}
	
			
			
				
			}
				catch(Exception ex){
					ex.printStackTrace();
				}
			
			
	
	
	


	
 finally {
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
}}

}
}