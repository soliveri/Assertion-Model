package com.assertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.ListUtils;

import com.booleanexp.Context;
import com.google.common.primitives.Chars;



public class EmailAddress {

	public static final String EMAIL_ADDRESS_INVALID = "The email address is not in a valid format";
	
	private static List<Character> validEmailCharacters;
	
	private static Set<String> knownDomains;
	
	static {
		initializeValidEmailCharacters();
		initializeKnownDomains();
	}

	private static void initializeKnownDomains() {
		knownDomains = new HashSet<String>();
		knownDomains.add("com");
		knownDomains.add("net");
		knownDomains.add("org");
		knownDomains.add("edu");
		knownDomains.add("int");
		knownDomains.add("mil");
		knownDomains.add("gov");
		knownDomains.add("arpa");
		knownDomains.add("biz");
		knownDomains.add("aero");
		knownDomains.add("name");
		knownDomains.add("coop");
		knownDomains.add("info");
		knownDomains.add("pro");
		knownDomains.add("museum");
	}

	private static void initializeValidEmailCharacters() {
		validEmailCharacters = new ArrayList<Character>();
		validEmailCharacters.add(Character.valueOf('!'));
		validEmailCharacters.add(Character.valueOf('#'));
		validEmailCharacters.add(Character.valueOf('$'));
		validEmailCharacters.add(Character.valueOf('%'));
		validEmailCharacters.add(Character.valueOf('&'));
		validEmailCharacters.add(Character.valueOf('\''));
		validEmailCharacters.add(Character.valueOf('*'));
		validEmailCharacters.add(Character.valueOf('+'));
		validEmailCharacters.add(Character.valueOf('-'));
		validEmailCharacters.add(Character.valueOf('/'));
		validEmailCharacters.add(Character.valueOf('='));
		validEmailCharacters.add(Character.valueOf('?'));
		validEmailCharacters.add(Character.valueOf('^'));
		validEmailCharacters.add(Character.valueOf('_'));
		validEmailCharacters.add(Character.valueOf('`'));
		validEmailCharacters.add(Character.valueOf('{'));
		validEmailCharacters.add(Character.valueOf('|'));
		validEmailCharacters.add(Character.valueOf('}'));
		validEmailCharacters.add(Character.valueOf('~'));
		validEmailCharacters.add(Character.valueOf('.'));
		validEmailCharacters.add(Character.valueOf(')'));
	}	
	
	private String string;
	
	public EmailAddress(String anEmailAddressAsString) {
		this.assertIsValid(anEmailAddressAsString);
		this.string = anEmailAddressAsString.toLowerCase().trim();
	}

	private void assertIsValid(String anEmailAddressAsString) {
		this.assertIsNotBlank(anEmailAddressAsString);
		final String anEmailAddressAsTrimmedString = anEmailAddressAsString.trim();
		this.assertHasNotWhitespacesInBetween(anEmailAddressAsTrimmedString);
		this.assertAtCharacterIsValid(anEmailAddressAsTrimmedString);
		this.assertAccountNameIsValid(anEmailAddressAsTrimmedString);
		this.assertDomainIsValid(anEmailAddressAsTrimmedString);
	}

	private void assertHasNotWhitespacesInBetween(final String anEmailAddressAsString) {
		if (anEmailAddressAsString.indexOf(" ") != -1) {
			throw new RuntimeException(EMAIL_ADDRESS_INVALID);
		}
//		ExpressionAssertion.isNotIncludedInDescribedBy(" ", anEmailAddressAsString, EMAIL_ADDRESS_INVALID).assertTrue();
	}

	private void assertIsNotBlank(String anEmailAddressAsString) {
		ExpressionAssertion.notEmptyStringAsConstantDescribedBy(anEmailAddressAsString, EMAIL_ADDRESS_INVALID).assertTrue();
	}

	private void assertDomainIsValid(final String anEmailAddressAsString) {
		String emailAddressDomain = anEmailAddressAsString.substring(anEmailAddressAsString.indexOf("@") + 1, anEmailAddressAsString.length());
		this.assertEmailSectionIsValidFor(emailAddressDomain);
		this.assertTopLevelDomainIsValid(emailAddressDomain);
	}

	private void assertTopLevelDomainIsValid(String emailAddressDomain) {
		String topLevelDomain = emailAddressDomain.substring(emailAddressDomain.indexOf(".") + 1, emailAddressDomain.length());
		if (topLevelDomain.indexOf(".") != -1) {//com.ar
			String countryCode = topLevelDomain.substring(topLevelDomain.indexOf(".") + 1, topLevelDomain.length());
			ExpressionAssertion.isEqualToDescribedBy(countryCode.length(), 2, EMAIL_ADDRESS_INVALID).assertTrue();
			ExpressionAssertion.isEveryCharOfContainedIn(countryCode, Chars.asList("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()), EMAIL_ADDRESS_INVALID).assertTrueIn(new Context());
			topLevelDomain = topLevelDomain.substring(0, topLevelDomain.indexOf(".")); 
		}
		ExpressionAssertion.isInDescribedBy(topLevelDomain, knownDomains, EMAIL_ADDRESS_INVALID).assertTrue();
	}

	private void assertAccountNameIsValid(final String anEmailAddressAsString) {
		String accountName = anEmailAddressAsString.substring(0, anEmailAddressAsString.indexOf("@"));
		this.assertEmailSectionIsValidFor(accountName);
	}
	
	private void assertAtCharacterIsValid(final String anEmailAddressAsString) {
		AssertionRunner.INSTANCE.run(Arrays.asList(new Assertion[] {
				ExpressionAssertion.isIncludedInDescribedBy("@", anEmailAddressAsString, EMAIL_ADDRESS_INVALID),
				ExpressionAssertion.notStartsWithDescribedBy(anEmailAddressAsString, "@", EMAIL_ADDRESS_INVALID),
				ExpressionAssertion.notEndsWithDescribedBy(anEmailAddressAsString, "@", EMAIL_ADDRESS_INVALID),
				ExpressionAssertion.isNotIncludedInDescribedBy("@", anEmailAddressAsString.substring(anEmailAddressAsString.indexOf("@") + 1, anEmailAddressAsString.length()), EMAIL_ADDRESS_INVALID)
		}));
	}

	private void assertEmailSectionIsValidFor(String anAccountName) {
		AssertionRunner.INSTANCE.run(Arrays.asList(new Assertion[] {
			ExpressionAssertion.notStartsWithDescribedBy(anAccountName, ".", EMAIL_ADDRESS_INVALID),
			ExpressionAssertion.notEndsWithDescribedBy(anAccountName, ".", EMAIL_ADDRESS_INVALID),
			ExpressionAssertion.isNotIncludedInDescribedBy("..", anAccountName, EMAIL_ADDRESS_INVALID),
			ExpressionAssertion.isEveryCharOfContainedIn(anAccountName, ListUtils.union(Chars.asList("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789".toCharArray()), validEmailCharacters), EMAIL_ADDRESS_INVALID)
		}));
	}	

	public static EmailAddress fromString(String anEmailAddressAsString) {
		return new EmailAddress(anEmailAddressAsString);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (string.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailAddress other = (EmailAddress) obj;
		return this.string.equals(other.string);
	}

}
