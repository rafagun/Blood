package Blood.db.ui;
//prueba del pull

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import Blood.db.jpa.GeneralMethods;
import Blood.db.jpa.JPAHospital;
import Blood.db.pojosJpa.Hospital;
import Blood.db.pojosJpa.Nurse;



public class menu extends GeneralMethods {
	
	static void menu1(){
		
	System.out.println("Introduce 1 to Hospital ");
	System.out.println("Introduce 2 to Nurse ");
	System.out.println("Introduce 3 to Patient ");
	System.out.println("Introduce 4 to Cells");
	System.out.println("Introduce 5 to Molecules");
	System.out.println("Introduce 6 to Illnes");
	System.out.println("Introduce 7 to Sympthoms");
	System.out.println("Introduce 8 to Exit");
	
	
	}
	
	
	static void menu2(){
	System.out.println("Introduce 1 to Insert ");
	System.out.println("Introduce 2 to delete ");
	System.out.println("Introduce 3 to show");
	System.out.println("Introduce 4 to update");
	System.out.println("Introduce 5 to select");
	/*System.out.println("Introduce 6 to drop");
	System.out.println("Introduce 7 to update");
	System.out.println("Introduce 8 to exit");*/
	
	}
	
	public static void main(String [] args){
		try{
		GeneralMethods.StartMethod();
	menu1();	
	InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	BufferedReader console = new BufferedReader(inputStreamReader);
	
	int option =Integer.parseInt( console.readLine());
	if (option == 1){
		JPAHospital jpa_Hospital = new JPAHospital();
		System.out.println("Choose an option");
		menu2();
		int selection = Integer.parseInt(console.readLine());
		if (selection ==1){//Insert
			Hospital hospital = new Hospital();
			System.out.println("Introduce a name");
			hospital.setName(console.readLine());
			System.out.println("Introduce a location");
			hospital.setLocation(console.readLine());
			System.out.print("Introduce a range");
			hospital.setRange(Integer.parseInt(console.readLine()));
			jpa_Hospital.SQLInsert(hospital);
			
		}
		else if(selection == 2){//delete
			System.out.println("Introduce the name of the hospital that you want to delete");
			List<Hospital> hospitals = jpa_Hospital.SQLSearch(console.readLine());
			Iterator it = hospitals.iterator();
			 
			for(int i=1; it.hasNext(); i++){
				System.out.println(i+".-"+it.toString());
			}
			System.out.println("Introduzca el hospital que desea seleccionar");
			int op =Integer.parseInt(console.readLine());
			jpa_Hospital.SQLDelete(hospitals.get(op));// the hospital we were looking for
			
				
		}
		else if (selection == 3){//read
			System.out.println("Introduce the name of the hospital that you want to delete");
			List<Hospital> hospitals = jpa_Hospital.SQLSearch(console.readLine());
			Iterator<Hospital> it = hospitals.iterator();
			for(int i=1; it.hasNext(); i++){
				System.out.println(i+".-"+it.toString());
			}
		}
		else if (selection == 4){//update
			System.out.println("Introduce the name of the hospital that you want to delete");
			List<Hospital> hospitals = jpa_Hospital.SQLSearch(console.readLine());
			Iterator it = hospitals.iterator();
			 
			for(int i=1; it.hasNext(); i++){
				System.out.println(i+".-"+it.toString());
			}
			
			System.out.println("Introduzca el hospital que desea seleccionar");
			int op =Integer.parseInt(console.readLine());
			Hospital newHosp = new Hospital();
			
			System.out.println("Introduce the changes you want to make in the name or press enter");
			String name = console.readLine();
			if (name.equals("")) newHosp.setName(hospitals.get(op).getName());
			else newHosp.setName(name);
			
			System.out.println("Introduce the changes you want to make in the Location or press enter");
			
			String location = console.readLine();
			if (location.equals("")) newHosp.setLocation(hospitals.get(op).getLocation());
			else newHosp.setLocation(location);
			
			System.out.println("Introduce the changes you want to make in the name or press enter");
			String lineaRange = console.readLine();
			if (lineaRange.equals(""))  newHosp.setRange(hospitals.get(op).getRange());
			else{
				newHosp.setRange(Integer.parseInt(lineaRange));
			}
			
			jpa_Hospital.SQLUpdate(hospitals.get(op), newHosp);
		}
		
		else if (selection == 5){//
			
			
		}
	}
	else if (option==2){//nurse
		menu2();
		Nurse nurse = new Nurse();
		System.out.println("Choose an option");
		int selection = Integer.parseInt(console.readLine());
		if (selection == 1) {
			
			System.out.println("Introduce a name");
			nurse.setName(console.readLine());
			System.out.println("Introduce the direction of the photo with its extension");
			nurse.setPhoto(console.readLine());
			System.out.println("Introduce his/her hospital");
			
			
		}
	}
	
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	

}
