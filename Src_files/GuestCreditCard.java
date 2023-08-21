import java.io.Serializable;
/**
 * The GuestCreditCard class is the main class to get and set the details
 * of the credit card provided by the guest
 */
public class GuestCreditCard implements Serializable {
	/**
	 * Name on the credit card
	 */
	private String name;
	/**
	 * Billing address of the guest
	 */
	private String billingAdd;
	/**
	 * Last four digits of the credit card number
	 */
	private String last4Digits;
	/**
	 * Expiry date of the credit card
	 */
	private String expiryDate;
	/**
	 * Name of the bank 
	 */
	private String bankName;
	/**
	 * Returns the name on the credit card
	 * @return String
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name on the credit card
	 * @param name (String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns the billing address of the guest
	 * @return String
	 */
	public String getBillingAdd() {
		return billingAdd;
	}
	/**
	 * Sets the billing address of the guest
	 * @param billingAdd (String)
	 */
	public void setBillingAdd(String billingAdd) {
		this.billingAdd = billingAdd;
	}
	/**
	 * Returns the last four digits of the credit card number
	 * @return String
	 */
	public String getLast4Digits() {
		return last4Digits;
	}
	/**
	 * Sets the last four digits of the credit card number
	 * @param last4Digits (String)
	 */
	public void setLast4Digits(String last4Digits) {
		this.last4Digits = last4Digits;
	}
	/**
	 * Returns the expiry date of the credit card
	 * @return String
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * Sets the expiry date of the credit card
	 * @param expiryDate (String)
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	/**
	 * Returns the bank name of the credit card
	 * @return String
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * Sets the bank name of the credit card
	 * @param bankName (String)
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
