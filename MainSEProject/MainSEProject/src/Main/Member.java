package Main;

/**
 * @author jmilinsk
 *
 */
public class Member {
	
	private int memID;
	private String memFName;
	private String memLName;
	private String memStAddr;
	private String memCity;
	private String memSt;
	private int memZip;
	private boolean isActive;
	private boolean isSusp;
	
	/**
	 * @param memID
	 * @param memFName
	 * @param memLName
	 * @param memStAddr
	 * @param memCity
	 * @param memSt
	 * @param memZip
	 * @param isActive
	 * @param isSusp
	 * @param optStatusDesc
	 */
	public Member(int memID, String memFName, String memLName, 
			String memStAddr,String memCity, String memSt, int memZip, 
			boolean isActive, boolean isSusp){
	
		this.memID = memID;
		this.memFName = memFName;
		this.memLName = memLName;
		this.memStAddr = memStAddr;
		this.memCity = memCity;
		this.memSt = memSt;
		this.memZip = memZip;
		this.isActive = isActive;
		this.isSusp = isSusp;
	}
	
	public int getMemID() {
		return memID;
	}
	public void setMemID(int memID) {
		this.memID = memID;
	}
	public String getMemFName() {
		return memFName;
	}
	public void setMemFName(String memFName) {
		this.memFName = memFName;
	}
	public String getMemLName() {
		return memLName;
	}
	public void setMemLName(String memLName) {
		this.memLName = memLName;
	}
	public String getMemStAddr() {
		return memStAddr;
	}
	public void setMemStAddr(String memStAddr) {
		this.memStAddr = memStAddr;
	}
	public String getMemCity() {
		return memCity;
	}
	public void setMemCity(String memCity) {
		this.memCity = memCity;
	}
	public String getMemSt() {
		return memSt;
	}
	public void setMemSt(String memSt) {
		this.memSt = memSt;
	}
	public int getMemZip() {
		return memZip;
	}
	public void setMemZip(int memZip) {
		this.memZip = memZip;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isSusp() {
		return isSusp;
	}
	public void setSusp(boolean isSusp) {
		this.isSusp = isSusp;
	}

}