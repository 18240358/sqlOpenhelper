package buil;

import java.sql.ResultSet;
import java.util.Map;

public interface sqlDao {
	/**
	 * select
	 * @param Sql
	 * @param values
	 * @return
	 */
	public ResultSet selectexecute(String Sql,Map<String,String> values);//²é
	/**
	 * insert
	 * @param Sql
	 * @param values
	 * @return
	 */
	public int insertexecute(String Sql,String[] arg);
	
}
