package talk;

public abstract class Response {
	
//	respond to actions
//		RegisterResponse, BetResponse, CommandResponse
	
	public Response (String userid, String command, String answer, String reason) {
		setUserid(userid);
		setCommand(command);
		setAnswer(answer);
		setReason(reason);
	}

	public String getUserid() {
		return userid;
	}

	protected void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	
	
	
	public String getAnswer() {
		return answer;
	}

	protected void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCommand() {
		return command;
	}

	protected void setCommand(String command) {
		this.command = command;
	}

	public String getReason() {
		return reason;
	}

	protected void setReason(String reason) {
		this.reason = reason;
	}

	private String userid;
	private String answer;
	private String reason;
	private String command;
	
}
