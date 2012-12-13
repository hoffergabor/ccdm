package hu.iboard.coandco.datamagic.generated;

// Generated Aug 11, 2009 4:42:50 PM by Hibernate Tools 3.2.4.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "USERS")
public class Users implements java.io.Serializable {

	public static final String DM_WORKER = "DM_WORKER";
	public static final String DM_PARTNER = "DM_PARTNER";
	
	private int userId;
	private String userName;
	private String userPassword;
	private int referenceId;
	private String userType;

	public Users() {
	}

	public Users(String userName, String userPassword, int referenceId,
			String userType) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.referenceId = referenceId;
		this.userType = userType;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "UserId", unique = true, nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "UserName")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "UserPwd")
	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "RefId")
	public int getReferenceId() {
		return this.referenceId;
	}

	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	@Column(name = "UserType", length = 20)
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
