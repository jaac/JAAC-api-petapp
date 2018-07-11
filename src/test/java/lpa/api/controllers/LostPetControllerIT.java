package lpa.api.controllers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import lpa.api.dtos.LostPetMinimumDto;

public class LostPetControllerIT {
	
	@Autowired
	private LostPetController lostPetController;
	
	@Test
	public void testFindLostPetAll() {
		List<LostPetMinimumDto> lostPetList = lostPetController.readLostPetAll();
		assertEquals("[{id:" + lostPetList.get(0)
				+ "name:nala,found:true,street:C/alemania,date:1528934400000,image:https://firebasestorage.googleapis.com/v0/b/lostpet-28e26.appspot.com/o/0dejzdn5p546?alt=media&token=6490c07c-68ea-482e-9deb-a642b17f718e},{name:nala,found:true,street:C/alemania,date:1528934400000,image:https://firebasestorage.googleapis.com/v0/b/lostpet-28e26.appspot.com/o/0dejzdn5p546?alt=media&token=6490c07c-68ea-482e-9deb-a642b17f718e}]",
				lostPetList);
	}
}
