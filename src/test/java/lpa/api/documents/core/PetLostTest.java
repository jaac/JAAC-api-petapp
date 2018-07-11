package lpa.api.documents.core;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import lpa.api.documents.core.Pet;
import lpa.api.documents.core.Image;
import lpa.api.documents.core.Location;
import lpa.api.documents.core.Breed;
import lpa.api.documents.core.HealthCondition;
import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.LostWay;
import lpa.api.documents.core.Report;
import lpa.api.documents.core.PetType;
import lpa.api.documents.core.User;

public class PetLostTest {

	@Test
	public void addPetLostUser() {
		User user = new User("629842", "jaac", "5644", "info@jaaccorp.es");
		assertEquals("info@jaaccorp.es", user.getEmail());
	}

	@Test
	public void createLostPet() {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy"); // Quoted "Z" to indicate UTC, no timezone offset
		df.setTimeZone(tz);
		String nowDate = df.format(new Date());
		User user = new User("629842", "jaac", "5644", "info@jaaccorp.es");
		double coordinantes[] = new double[2];
		coordinantes[0] = 222;
		coordinantes[1] = 332;
		Location dogLocation = new Location(coordinantes);
		PetType petType = new PetType("Dog");
		HealthCondition healthCondition = new HealthCondition("Healthy");
		Image[] petImages = new Image[2];
		petImages[0] = new Image("image.jpg", "image.jpg", "image.jpg");
		Pet dog = new Pet(petType, petImages, "Doggy", "Male");
		LostPet dogLost = new LostPet(false, dogLocation, "Dog found in Central Park", healthCondition, dog, user);
		assertEquals("LostPet[ null ,active=true, found=false, date=" + nowDate
				+ ", petLocation=Location [null, latitude=173, longitude=5655, country=null, city=null, state=null, locality=null, street=null, street_number=0, formatted_address=null, postalCode=0], description=Dog found in Central Park, lostWay=null, pet=Pet[null, petType=PetType[ id= null, name= Dog, breeds=null ], imgs=[Lapi.documents.core.Image;@2b80d80f, name=Doggy, gender=Male, age=null, breed=null, hairColor=null, eyesColor=null], healthCondition=HealthCondition[ id= null, name= Healthy], gratification=false, report=null]",
				dogLost.toString());
	}

	@Test
	public void createLostPetReport() {
		User user = new User("629842", "jaac", "5644", "info@jaaccorp.es");
		double coordinantes[] = new double[2];
		coordinantes[0] = 222;
		coordinantes[1] = 332;
		Location dogLocation = new Location(coordinantes);
		PetType petType = new PetType("Dog");
		Breed[] breeds = new Breed[2];
		breeds[0] = new Breed("Pibull");
		breeds[1] = new Breed("German Sheperd");
		petType.setBreed(breeds);
		HealthCondition healthCondition = new HealthCondition("Healthy");
		Image[] petImages = new Image[2];
		petImages[0] = new Image("image.jpg", "image.jpg", "image.jpg");
		Pet dog = new Pet(petType, petImages, "Doggy", "Male");
		// set Breed
		dog.setBreed(breeds[0]);
		//
		LostPet dogLost = new LostPet(false, dogLocation, "Dog found in Central Park", healthCondition, dog, user);
		Report[] petReportList = new Report[2];
		petReportList[0] = new Report("This is a Fake alert!, that is my dog!", user);
		dogLost.setPetReport(petReportList);
		assertEquals("Report[null, detail=This is a Fake alert!, that is my dog!, userID=629842]",
				dogLost.getPetReportList()[0].toString());
		System.out.println(dogLost.getPet().getBreed().getName());
	}

	@Test
	public void createLostPetLostWay() {
		User user = new User("629842", "jaac", "5644", "info@jaaccorp.es");
		double coordinantes[] = new double[2];
		coordinantes[0] = 222;
		coordinantes[1] = 332;
		Location dogLocation = new Location(coordinantes);
		PetType petType = new PetType("Dog");
		Breed[] breeds = new Breed[2];
		breeds[0] = new Breed("Pibull");
		breeds[1] = new Breed("German Sheperd");
		petType.setBreed(breeds);
		HealthCondition healthCondition = new HealthCondition("Healthy");
		Image[] petImages = new Image[2];
		petImages[0] = new Image("image.jpg", "image.jpg", "image.jpg");
		Pet dog = new Pet(petType, petImages, "Doggy", "Male");
		// set Breed
		dog.setBreed(breeds[0]);
		//
		LostPet dogLost = new LostPet(false, dogLocation, "Dog found in Central Park", healthCondition, dog, user);
		LostWay lost_way = new LostWay("Lost in the Street");
		dogLost.setLostWay(lost_way);
		assertEquals("Lost in the Street", dogLost.getLostWay().getName());
		System.out.println(dogLost.getPet().getBreed().getName());
	}

}
