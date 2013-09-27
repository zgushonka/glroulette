package casino;

import talk.BetRequest;
import talk.RegisterRequest;
import talk.RegisterResponse;
import talk.Response;

	

public class InDoor {
	
	final static String ANSWER_OK = "OK";
	final static String ANSWER_BAD = "Fail";
	final static String NO_ID = "NO_ID";
	
	public static Response processBetRequest (BetRequest request) {
		System.out.println("Bet request received "+request);
		
		
		
		
		
		return null;
	}
	
	public static Response processRegisterRequest(RegisterRequest request){
		System.out.println("Register request received "+request);
		
		String name = request.getPlayerName();
		String password = request.getPlayerPassword();
		
		// answer Strings
		String userid;
		String command = "register";
		String answer;
		
		try {
			Player player = new Player(name, password);
						
			userid = player.getId().toString();
			answer = ANSWER_OK;			
		}
		catch () {
			userid = NO_ID;
			answer = ANSWER_BAD;
		}
		
		RegisterResponse response = new RegisterResponse (userid, command, answer);
		
		
		return response;
	}
		
	
	/*
			commands:
			 user commands:
				- register Player
					set Player Name
					set Password
 	/*	*/
		
	/*
				- all bets done, perform spin (test table)
					UUID
					password
	/*	*/
		
	/*
				- take Bet from user
					UUID
					password
					table type
					bet type
					stake
					number
	/*	*/
		
	/*		
			 administrator commands:
				- deleteAllPlayers
					administrator UUID
					password
	/*	*/
		
	}
	
	
	

