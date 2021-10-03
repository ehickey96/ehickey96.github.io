//CS-499 Category One - User Object
//Elijah Hickey
//9.13.21

public class User {
	//simple user object
	private String uFNam;
	private String uLNam;
	private String uUsernam;
	private int uPwd;

	User(){
		
	}
	User(String tFNam, String tLNam, String tUserNam, int tPwd){
		uFNam = tFNam;
		uLNam = tLNam;
		uUsernam = tUserNam;
		uPwd = tPwd;
	}
	
	public String getUserFirstName() {
		return uFNam;
	}
	public String getUserLastName() {
		return uLNam;
	}
	public String getUserUsername() {
		return uUsernam;
	}
	public int getUserPassword() {
		return uPwd;
	}
	
	public void setUserFirstName(String tVar) {
		uFNam = tVar;
		
	}
	
	public void setUserLastName(String tVar) {
		uLNam = tVar;
		
	}
	
	public void setUserUsername(String tVar) {
		uUsernam = tVar;
		
	}
	
	public void setUserPassword(int tVar) {
		uPwd = tVar;
		
	}
	
}



