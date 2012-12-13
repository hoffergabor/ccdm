package hu.iboard.coandco.datamagic.vo.user;


public class UserVO implements java.io.Serializable

{
	private static final long serialVersionUID = -5158747749727210562L;
	
	public static final String WORKER = "DM_WORKER";
	public static final String PARTNER = "DM_PARTNER";
	public static final String GUEST = "DM_GUEST";
	public static final String SALES = "DM_SALES";
	public static final String ADMIN = "DM_ADMIN";
	public static final String RENTER = "DM_RENTER";
	
	private Integer referenceId;
	private String userType;
	private Boolean web_pu;

	public UserVO() {
	}

	public UserVO(String userName, String userPassword, int referenceId,
			String userType, boolean web_pu) {
		this.referenceId = referenceId;
		this.userType = userType;
		this.web_pu = web_pu;
	}

	public Integer getReferenceId() {
		return this.referenceId;
	}

	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Boolean getWeb_pu() {
		return this.web_pu;
	}

	public void setWeb_pu(Boolean web_pu) {
		this.web_pu = web_pu;
	}
}
