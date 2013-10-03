package talk;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SpinResponse extends Response {
	
    public SpinResponse() {
        super();
        
    }
    
	public SpinResponse (String userid, String command, String answer) {
		super (userid, command, answer, "");
	}
		
	public SpinResponse (String userid, String command, String answer, String reason) {
		super (userid, command, answer, reason);
	}

}
