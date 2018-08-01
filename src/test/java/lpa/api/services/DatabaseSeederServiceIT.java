package lpa.api.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.documents.core.User;
import lpa.api.repositories.core.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class DatabaseSeederServiceIT {

    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void testUserSeedDatabase() {
        // this.databaseSeederService.deleteAllAndCreateAdmin();
        // this.databaseSeederService.seedDatabase("tpv-db-test.yml");
        User user = userRepository.findByusername("666666001");
        assertNotNull(user);
        assertEquals("u001", user.getName());
        assertEquals("u001@gmail.com", user.getEmail());
        //assertEquals("66666600L", user.getDni());
        assertTrue(user.getRoles().length >= 2);
    }
    
}
