package talk;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegisterResponse extends Response {
	
    public RegisterResponse() {
        super();
        
    }
    
	public RegisterResponse (String userid, String command, String answer) {
		super (userid, command, answer, "");
	}
		
	public RegisterResponse (String userid, String command, String answer, String reason) {
		super (userid, command, answer, reason);
	}

}
