package lpa.api.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.annotation.PostConstruct;

import lpa.api.repositories.core.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import lpa.api.documents.core.Role;
import lpa.api.documents.core.User;

@Service
public class DatabaseSeederService {

	@Value("${lpa.admin.username}")
	private String username;

	@Value("${lpa.admin.name}")
	private String name;

	@Value("${lpa.admin.password}")
	private String password;

	@Value("${lpa.databaseSeeder.ymlFileName:#{null}}")
	private Optional<String> ymlFileName;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LostPetRepository lostPetRepository;

	@Autowired
	private SpeciesRepository speciesRepository;

	@Autowired
	private PetCommentsRepository petCommentsRepository;

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private BreedRepository breedRepository;

	@Autowired
	private SpeciesBreedRepository specieBreedBreedRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private LocationRepository locationRepository;

	@PostConstruct
	public void seedDatabase() {
		if (ymlFileName.isPresent()) {
			this.deleteAllAndCreateAdmin();
			try {
				this.seedDatabase(ymlFileName.get());
			} catch (IOException e) {
				Logger.getLogger(this.getClass()).error("File " + ymlFileName + " doesn't exist or can't be opened");
			}
		} else {
			this.createAdminIfNotExist();
		}
	}

	public void seedDatabase(String ymlFileName) throws IOException {
		assert ymlFileName != null && !ymlFileName.isEmpty();
		Yaml yamlParser = new Yaml(new Constructor(DatabaseGraph.class));
		InputStream input = new ClassPathResource(ymlFileName).getInputStream();
		DatabaseGraph lpaGraph = (DatabaseGraph) yamlParser.load(input);

		// Save Repositories -----------------------------------------------------

		this.breedRepository.save(lpaGraph.getBreedList());
		this.speciesRepository.save(lpaGraph.getSpeciesList());
		this.imageRepository.save(lpaGraph.getImageList());
		this.locationRepository.save(lpaGraph.getLocationList());
		this.userRepository.save(lpaGraph.getUserList());
		this.petRepository.save(lpaGraph.getPetList());
		this.lostPetRepository.save(lpaGraph.getLostPetList());
		this.petCommentsRepository.save(lpaGraph.getCommentsList());
		this.userProfileRepository.save(lpaGraph.getUserProfileList());
		this.specieBreedBreedRepository.save(lpaGraph.getSpeciesBreedList());
		// -----------------------------------------------------------------------

		Logger.getLogger(this.getClass()).warn("------------------------- Seed: " + ymlFileName + "-----------");
	}

	public void deleteAllAndCreateAdmin() {
		Logger.getLogger(this.getClass()).warn("------------------------- delete All And Create Admin-----------");
		// Delete Repositories -----------------------------------------------------
		this.speciesRepository.deleteAll();
		this.locationRepository.deleteAll();
		this.lostPetRepository.deleteAll();
		this.petCommentsRepository.deleteAll();
		this.userRepository.deleteAll();
		this.userProfileRepository.deleteAll();
		this.createAdminIfNotExist();
		// -----------------------------------------------------------------------
	}

	public void createAdminIfNotExist() {
		if (this.userRepository.findByusername(this.username) == null) {
			User user = new User(this.username, this.username, this.password);
			user.setRoles(new Role[] { Role.ADMIN });
			this.userRepository.save(user);
		}
	}
}
