package com.assertion;

import junit.framework.TestCase;

public class EmailAddressTestCase extends TestCase {
	
	public void testRequiredEmailAddress() {
		assertEmailAddressCreationFailsThrowing(null, EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("  ", EmailAddress.EMAIL_ADDRESS_INVALID);
	}
	
	public void testInvalidEmailAddress() {
		assertEmailAddressCreationFailsThrowing("john", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("@john", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("john@", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("john saunders@yahoo.com", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsaunders.@yahoo.com", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing(".johnsaunders@yahoo.com", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsa..unders@yahoo.com", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsa@unders@yahoo.com", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsa@undersyahoo.com@", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("@johnsa@undersyahoo.com@", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsa\"unders@yahoo.com", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsa(unders@yahoo.com", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsau[nders@yahoo.com", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsaunders@yahoo", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsaunders@yahoo.comm", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsaunders@yahoo.c2m", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsaunders@yahoo.c2m.ar", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsaunders@yahoo.com.a", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsaunders@yahoo.com.a2", EmailAddress.EMAIL_ADDRESS_INVALID);
		assertEmailAddressCreationFailsThrowing("johnsaunders@yahoo.net.33", EmailAddress.EMAIL_ADDRESS_INVALID);
	}
	
	public void testEqualsEmailAddress() {
		assertTrue(EmailAddress.fromString("sebaszipp@gmail.com").equals(EmailAddress.fromString("sebaszipp@gmail.com")));
		assertTrue(EmailAddress.fromString(" sebaszipp@gmail.com").equals(EmailAddress.fromString("sebaszipp@gmail.com")));
		assertTrue(EmailAddress.fromString("sebaszipp@gmail.com ").equals(EmailAddress.fromString("sebaszipp@gmail.com")));
		assertTrue(EmailAddress.fromString("SeBaSZipp@gmail.com").equals(EmailAddress.fromString("SEBASZIPP@gmail.com")));
	}
	
	public void testValidEmailAddress() {
		try {
			EmailAddress.fromString("sebaszipp@gmail.com");
			EmailAddress.fromString("sebaszipp@gmail.net");
			EmailAddress.fromString("sebaszipp@gmail.org");
			EmailAddress.fromString("sebaszipp@yahoo.com.ar");
			EmailAddress.fromString("sebastian.oliveri@yahoo.com.ar");
			EmailAddress.fromString("sebastian.oliveri^deBuenosAires@yahoo.com.ar");
			EmailAddress.fromString("sebastian.oliveri@yahoo.com.ar");
		} catch(RuntimeException exception) {
			fail();
		}
	}
	
	private void assertEmailAddressCreationFailsThrowing(String aString, String failureMessage) {
		try {
			EmailAddress.fromString(aString);
			fail();
		} catch (RuntimeException exception) {
			assertEquals(failureMessage, exception.getMessage());
		}		
	}	
	
}
