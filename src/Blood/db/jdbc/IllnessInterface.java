package Blood.db.jdbc;

import Blood.db.jpa.FunctionsDB;
import Blood.db.pojos.Illnes;

public interface IllnessInterface extends FunctionsDB<Illnes> {
	public void SQLRelationIC(int illnesid, int cellid, int level, String hl);
	public void SQLRelationIM(int molId,int illId, int level, String hL);
	public void SQLRelationIS(int sympId,int illId, String place);
}
