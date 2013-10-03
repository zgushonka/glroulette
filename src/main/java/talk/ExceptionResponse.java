package talk;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExceptionResponse extends Response {
	
    public ExceptionResponse() {
        super();
        
    }
    
	public ExceptionResponse (String userid, String command, String answer) {
		super (userid, command, answer, "");
	}
		
	public ExceptionResponse (String userid, String command, String answer, String reason) {
		super (userid, command, answer, reason);
	}

}
