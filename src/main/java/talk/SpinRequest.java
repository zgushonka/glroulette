package talk;

public class SpinRequest extends Request {
	
	public SpinRequest (String userId, String playerName, String newPassword) {
	    setUserid(userId);
	    setPlayerName(playerName);
		setPlayerPassword(newPassword);		
		
	}
}
