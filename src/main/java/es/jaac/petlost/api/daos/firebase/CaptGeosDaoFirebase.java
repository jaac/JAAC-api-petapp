package es.jaac.petlost.api.daos.firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.jaac.petlost.api.daos.CaptGeosDao;
import es.jaac.petlost.api.entities.CaptGeos;

public class CaptGeosDaoFirebase extends GenericDaoFirebase<CaptGeos> implements CaptGeosDao{

	public CaptGeosDaoFirebase() {
		this.setMap(new HashMap<Integer,CaptGeos>());
	}
	@Override
	public List<CaptGeos> findCaptGeosById(int captGeoId) {
		List<CaptGeos> cg = this.findAll(); 
		List<CaptGeos> list = new ArrayList<>();
		
		for(CaptGeos cgx : cg) {
			if(cgx.getId() == captGeoId) {
				list.add(cgx);
			}
			
		}
		return list;
	}

	@Override
	protected Integer getId(CaptGeos entity) {
		return entity.getId();
	}

	@Override
	protected void setId(CaptGeos entity, int id) {
		entity.setId(id);
	}

}
