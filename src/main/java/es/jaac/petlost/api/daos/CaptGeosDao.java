package es.jaac.petlost.api.daos;

import java.util.List;

import es.jaac.petlost.api.entities.CaptGeos;

public interface CaptGeosDao extends GenericDao<CaptGeos, Integer>{

	List<CaptGeos> findCaptGeosById(int captGeoId);
	List<CaptGeos> findCaptGeosByUserId(int userId);
}
