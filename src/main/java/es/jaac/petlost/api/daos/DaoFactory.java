package es.jaac.petlost.api.daos;

public abstract class DaoFactory {

	private static DaoFactory Factory = null;

	public static DaoFactory getFactory() {
		assert Factory != null;
		return Factory;
	}

	public static void setFactory(DaoFactory factory) {
		DaoFactory.Factory = factory;
	}
	
	public abstract UserDao getUserDao();
	public abstract CaptGeosDao getCaptGeosDao();

}
