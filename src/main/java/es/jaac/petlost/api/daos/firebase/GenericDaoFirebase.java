package es.jaac.petlost.api.daos.firebase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import es.jaac.petlost.api.daos.GenericDao;

public abstract class GenericDaoFirebase<T> implements GenericDao<T, Integer> {

	private Map<Integer, T> map;
	
	private int id;
	
	public GenericDaoFirebase() {
		id = 1;
	}
	
	protected void setMap(Map<Integer, T> map) {
		this.map = map;
	}
	
	@Override
	
	public void create(T entity){
		map.put(id, entity);
		this.setId(entity,id);
		id++;
	}
	
	@Override
	public T read(Integer id) {
		return map.get(id);
	}
	
	@Override
	public void update(T entity) {
		if(map.containsKey(this.getId(entity))) {
			map.put(this.getId(entity), entity);
		}
	}
	
	@Override
	
	public void deleteById(Integer id) {
		map.remove(id);
	}
	
	@Override
	
	public List<T> findAll(){
		return new ArrayList<>(map.values()); 
	}
	
	protected abstract Integer getId(T entity);

	protected abstract void setId(T entity, int id);
}
