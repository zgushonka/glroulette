package casino;

import java.util.UUID;

import org.nr.roulette.exceptions.ValidationException;

public class Player {
	
	private final String name;
	private final String password;
	private final UUID id = UUID.randomUUID();
	private int money;
	
	private int betCount;
	private int winBetCount;
	
	public String getPassword()
	{
	    return password;
	}
	
	
	public Player(String name, String password)  throws ValidationException{
		
	    // Ask croupie if this player has already been created with other UUID
	    //Croupie.newInstance().checkPlayerExists(name);
	    
		checkNameString(name);
		checkPasswordString(password);
		
		this.name = name;
		this.password = password;
		money = 0;
		betCount = 0;
		winBetCount = 0;
	}
	
	
	//TODO request is processed incorrectly when user name or user password are absent or null or empty strings: need to check them in request
	private void checkNameString (String name) throws ValidationException {
	    if (name == null ) {
            throw new ValidationException("Name should not be null");
        }
	    if (name.length() < 4) {
			throw new ValidationException("Name is too short. The length is " + name.length() + " and should be > 3");
		}
	}
	
	private void checkPasswordString (String password)  throws ValidationException {
		if (password == null ) {
            throw new ValidationException("Password should not be null");
        }
	    if (password.length() < 6) {
			throw new ValidationException("Password is too short. The length is " + name.length() + " and should be > 5");
		}
	}

	
	public String getName() {
		return name;
	}

	public UUID getId() {
		return id;
	}

	public int getMoney() {
		return money;
	}
	
	
	// package access level (for Croupie)
	protected void applyBetResult(int result) {
		this.money += result;
	}
	

	private boolean isAuthentified = false;
	
	public boolean isPasswordOk (String password) {
		isAuthentified = ( this.password.equals(password) );
		return isAuthentified;
	}


	public int getBetCount() {
		return betCount;
	}
	
	protected void incrementBetCount() {
		betCount++;
	}


	public int getWinBetCount() {
		return winBetCount;
	}
	
	protected void incrementWinBetCount() {
		winBetCount++;
	}	
	
}
