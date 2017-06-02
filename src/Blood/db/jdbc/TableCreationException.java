package Blood.db.jdbc;

public class TableCreationException extends java.sql.SQLException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int i;

	public TableCreationException(int i){
	this.i=i;
	}
	
 public void tableExists(){
	if (i==0){
	System.out.println("Table hospital already created");
	}
	if (i==1){
	System.out.println("Table nurses already created");
	}
	if (i==2){
	System.out.println("Table patient already created");
	}
	if (i==3){
	System.out.println("Table cells already created");
	}
	if (i==4){
	System.out.println("Table molecules already created");
	}
	if (i==5){
	System.out.println("Table sympthoms already created");
	}
	if (i==6){
	System.out.println("Table illness already created\n");
	}
	
	}

}
