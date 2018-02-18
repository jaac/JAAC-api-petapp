package es.jaac.petlost.api.daos.firebase;

import es.jaac.petlost.api.daos.CaptGeosDao;

import es.jaac.petlost.api.daos.DaoFactory;
import es.jaac.petlost.api.daos.UserDao;

public class DaoFirebaseFactory extends DaoFactory{

	private UserDao userDao;

	private CaptGeosDao captGeosDao;
	
	@Override
	public UserDao getUserDao() {
		if(userDao == null) {
			userDao = new UserDaoFirebase();
		}
		return userDao;
	}

	@Override
	public CaptGeosDao getCaptGeosDao() {
		if(captGeosDao ==null) {
			captGeosDao = new CaptGeosDaoFirebase();
		}
		return captGeosDao;
	}


	
}
