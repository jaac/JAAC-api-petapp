package lpa.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lpa.api.documents.core.LostPet;
import lpa.api.repositories.core.LostPetRepository;

@Service
public class MapService {
	@Autowired
	private LostPetRepository lostPetRepository;

	private List<String> listMatchByLatLong;

	public List<String> getLatLongMatchListByLatLong(double radius, BoundingCoordinatesService bCService,
			double distance) {

		this.listMatchByLatLong = new ArrayList<>();

		BoundingCoordinatesService boundingCoordinates[] = bCService.boundingCoordinates(distance, radius);

		boolean meridian180WithinDistance = boundingCoordinates[0].getLongitudeInRadians() > boundingCoordinates[1]
				.getLongitudeInRadians();
	

	
		return listMatchByLatLong;

	}
}
