package Blood.db.jdbc;
import Blood.db.jpa.FunctionsDB;
import Blood.db.pojos.Patient;

public interface PatientInterface extends FunctionsDB<Patient> {
	public void SQLRelationNP(int nurse, int patient);
	public void SQLRelationPC(int cell, int patient, int level);
	public void SQLRelationPM(int molecules, int patient , int level);
	public void SQLRelationPS(int sympthom, int patient, String level, String place );
	public void SQLRelationPI(int illnes, int patient);
}
