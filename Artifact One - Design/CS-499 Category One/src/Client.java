//CS-499 Category One - Client Object
//Elijah Hickey
//9.13.21
public class Client {

//basic client object. 
//Later iterations could include more info
	private int clientNum;
	private String clientNam;
	private int clientServ;
	
	Client(){
		
	}
	
	Client(int tClientNum, String tClientNam, int tClientServ){
		clientNum = tClientNum;
		clientNam = tClientNam;
		clientServ = tClientServ;
	}
	
	public int getClientNum() {
		return clientNum;
	}
	
	public String getClientNam() {
		return clientNam;
	}
	
	public int getClientServ() {
		return clientServ;
	}
	
	public void setClientNum(int tVar) {
		clientNum = tVar;
	}
	
	public void setClientNam(String tVar) {
		clientNam = tVar;
	}
	
	public void setClientServ(int tVar) {
		clientServ = tVar;
	}
	  	
	//used when displayInfo is called from main() in the driver class
	public void printClientData() {
		System.out.println(clientNum + ". " + clientNam + " selected option " + clientServ );
	}
}


