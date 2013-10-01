package talk;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CommandResponse extends Response {
	
	public CommandResponse (String userid, String command, String answer) {
		super (userid, command, answer, "");
	}	

}
