package dao;

public class dataAccess {
	private static String db = "mysql";

	public static IGuitar createGuitarDao() {
		IGuitar result = null;
		switch (db) {
		case "sqlite":
			result = new sqliteDaoImpl.GuitarImpl();
			break;
		case "mysql":
			result = new mysqlDaoImpl.GuitarImpl();
			break;
		}
		return result;
	}

	

}
