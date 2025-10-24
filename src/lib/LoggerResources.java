package lib;

import db.DataBaseConnectionManager;
import db.DataBaseException;

public class LoggerResources {

   public void error(String type, String query, String exception) throws DataBaseException {
      DataBaseConnectionManager conn = new DataBaseConnectionManager(1, "postgres", "postgres", "postgres");

      String sql = "INSERT INTO exception_logs (type, query, exception) " +
                  "VALUES ('" + type + "','" + query + "','" + exception + "')";

		try {
			conn.runSQL(sql);
		} catch (DataBaseException e) {
			throw new RuntimeException();
		} finally {
			conn.closeConnection();
		}
   }  
}
