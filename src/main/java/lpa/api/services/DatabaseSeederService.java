package lpa.api.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import lpa.api.documents.core.Role;
import lpa.api.documents.core.User;
import lpa.api.repositories.core.HealthConditionRepository;
import lpa.api.repositories.core.LostPetRepository;
import lpa.api.repositories.core.PetCommentsRepository;
import lpa.api.repositories.core.PetTypeRepository;
import lpa.api.repositories.core.UserRepository;

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
	private HealthConditionRepository healthConditionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LostPetRepository lostPetRepository;

	@Autowired
	private PetTypeRepository petTypeRepository;

	@Autowired
	private PetCommentsRepository petCommentsRepository;

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

		this.healthConditionRepository.save(lpaGraph.getHealthConditionList());
		this.petTypeRepository.save(lpaGraph.getTypeList());
		this.userRepository.save(lpaGraph.getUserList());
		this.lostPetRepository.save(lpaGraph.getLostPetList());
		this.petCommentsRepository.save(lpaGraph.getCommentsList());
		// -----------------------------------------------------------------------

		Logger.getLogger(this.getClass()).warn("------------------------- Seed: " + ymlFileName + "-----------");
	}

	public void deleteAllAndCreateAdmin() {
		Logger.getLogger(this.getClass()).warn("------------------------- delete All And Create Admin-----------");
		// Delete Repositories -----------------------------------------------------
		this.healthConditionRepository.deleteAll();
		this.petTypeRepository.deleteAll();
		this.lostPetRepository.deleteAll();
		this.petCommentsRepository.deleteAll();
		this.userRepository.deleteAll();

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
