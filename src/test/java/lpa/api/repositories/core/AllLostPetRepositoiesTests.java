package lpa.api.repositories.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LostPetRepositoryIT.class, CommentsRepositoryIT.class, UserRepositoryIT.class })
public class AllLostPetRepositoiesTests {

}
