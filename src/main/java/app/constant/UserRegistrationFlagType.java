package app.constant;

public enum UserRegistrationFlagType {

	RUSER("RUSER"),
	MEMBER("MEMBER");
	
	private String type;
	
	UserRegistrationFlagType(String type){
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
