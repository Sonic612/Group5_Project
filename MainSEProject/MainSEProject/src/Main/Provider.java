package Main;

/** 
* Creates a provider object and defines setters and getters for interacting with it. 
*  
* @author Lan Le
*/

public class Provider {
	private int provID;
	private String provName;
	private String provStAddr;
	private String provCity;
	private String provSt;
	private int provZip;
	
	public Provider(int provID, String provName, String provStAddr, String provCity, String provSt, int provZip) {
		super();
		this.provID = provID;
		this.provName = provName;
		this.provStAddr = provStAddr;
		this.provCity = provCity;
		this.provSt = provSt;
		this.provZip = provZip;
	}

	public int getProvID() {
		return provID;
	}

	public void setProvID(int provID) {
		this.provID = provID;
	}

	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public String getProvStAddr() {
		return provStAddr;
	}

	public void setProvStAddr(String provStAddr) {
		this.provStAddr = provStAddr;
	}

	public String getProvCity() {
		return provCity;
	}

	public void setProvCity(String provCity) {
		this.provCity = provCity;
	}

	public String getProvSt() {
		return provSt;
	}

	public void setProvSt(String provSt) {
		this.provSt = provSt;
	}

	public int getProvZip() {
		return provZip;
	}

	public void setProvZip(int provZip) {
		this.provZip = provZip;
	}

}