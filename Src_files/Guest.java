import java.io.Serializable;
/**
 * The Guest class is the main class to get and set the details
 * of the guest
 */
public class Guest implements Serializable{
	/**
	 * Name of the guest
	 */
	private String name;
	/**
	 * Address of the guest
	 */
	private String address;
	/**
	 * Credit card details of the guest
	 */
	private GuestCreditCard creditCard;
	/**
	 * Original country of the guest
	 */
	private String country;
	/**
	 * Gender of the guest. Only 3 gender which is Male, Female and Others
	 */
	private String gender;
	/**
	 * The type of identity the guest provide. Can be Driving License, NRIC or Passport
	 */
	private GuestIdentity identity;
	/**
	 * The identity number of the guest
	 */
	private String identityNo;
	/**
	 * Nationality of the guest
	 */
	private String nationality;
	/**
	 * Contact number of the guest
	 */
	private String contact;
	/**
	 * Determine whether the guest is an adult
	 */
	private boolean adult; 
	
	/**
	 * Enumeration of the 3 guest identity types: NRIC, Driving License or Passport
	 */
	static enum GuestIdentity {
	NRIC,
	Driving_License,
	Passport
	}

	public Guest() {};
	
	/**
	 * Returns the name of the guest
	 * @return String
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of the guest
	 * @param name (String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns the address of the guest
	 * @return String
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Sets the address of the guest
	 * @param address (String)
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Returns the origin country of the guest
	 * @return String
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * Sets the origin country of the guest
	 * @param country (String)
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/** 
	 * Returns the gender of the guest
	 * @return String
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * Sets the gender of the guest
	 * @param gender (String)
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * Returns the identity type of the guest
	 * @return GuestIdentity
	 */
	public GuestIdentity getIdentity() {
		return identity;
	}
	/**
	 * Sets the identity type of the guest
	 * @param identity (String)
	 */
	public void setIdentity(GuestIdentity identity) {
		this.identity = identity;
	}
	/**
	 * Returns the identity number of the guest
	 * @return String
	 */
	public String getIdentityNo() {
		return identityNo;
	}
	/**
	 * Sets the identity number of the guest
	 * @param identityNo (String)
	 */
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	/**
	 * Returns the nationality of the guest
	 * @return String
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * Sets the nationality of the guest
	 * @param nationality (String)
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * Returns the contact number of the guest
	 * @return String
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * Sets the contact number of the guest
	 * @param contact (String)
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/** 
	 * Returns the credit card details of the guest
	 * @return GuestCreditCard
	 */
	public GuestCreditCard getCreditCard() {
		return creditCard;
	}
	/**
	 * Sets the credit card details of the guest
	 * @param creditCard (GuestCreditCard)
	 */
	public void setCreditCard(GuestCreditCard creditCard) {
		this.creditCard = creditCard;
	}
	/**
	 * Returns whether the guest is an adult
	 * @return boolean (True or False)
	 */
	public boolean isAdult() {
		return adult;
	}
	/**
	 * Sets whether the guest is an adult
	 * @param adult (boolean)
	 */
	public void setAdult(boolean adult) {
		this.adult = adult;
	}
}
