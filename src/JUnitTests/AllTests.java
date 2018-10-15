package JUnitTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({})
public class AllTests {

	@Test
	public void shouldBeTrueDUMMYTEST() {
		
		Assert.assertEquals(2, 2);
		Assert.assertNotSame(2,3);
		
	}
	
	
}
