
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;
import domain.Chorbi;
import domain.Likes;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class LikesServiceTest extends AbstractTest {

	// The SUT -------------------------------------------------------------
	@Autowired
	private LikesService	likesService;

	@Autowired
	private ChorbiService	chorbiService;


	// Teoria pagina 107 y 108
	// Tests ---------------------------------------------------------------
	//- An actor who is authenticated must be able to:
	//	o Post a comment on another actor, on an offer, or a request

	//An actor who is authenticated as a chorbi must be able to:
	//	o Unlike a user he has liked

	@Test
	public void driverUnlike() {
		final Object testingData[][] = {
			{		// Borrado correcta de un Likes.
				"chorbi1", "chorbi1", null
			}, {	// Borrado erronea de un Likes: distinto usuario.
				"chorbi1", "chorbi2", IllegalArgumentException.class
			}, {	// Borrado err�nea de un Likes: sin autenticar.
				"chorbi1", null, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.templateUnlike((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	// Templates ----------------------------------------------------------

	protected void templateUnlike(final String username, final String username2, final Class<?> expected) {
		Class<?> caught;
		caught = null;

		try {

			this.authenticate(username);
			final Chorbi chorbi = this.chorbiService.findOne(this.extract("chorbi2"));
			final Likes comment = this.likesService.create(chorbi);
			final Likes result = this.likesService.save(comment);
			this.authenticate(username2);
			this.likesService.delete(result);
			this.unauthenticate();

			this.likesService.flush();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
