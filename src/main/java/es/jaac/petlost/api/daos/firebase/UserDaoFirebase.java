package es.jaac.petlost.api.daos.firebase;

import java.util.HashMap;

import es.jaac.petlost.api.daos.UserDao;
import es.jaac.petlost.api.entities.User;

public class UserDaoFirebase extends GenericDaoFirebase<User> implements UserDao {

	public UserDaoFirebase() {
		this.setMap(new HashMap<Integer, User>());
	}
	
	@Override
	protected Integer getId(User entity) {
		return entity.getId();
	}

	@Override
	protected void setId(User entity, int id) {
		entity.setId(id);
	}

	

}
