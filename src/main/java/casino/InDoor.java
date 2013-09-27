package casino;

import org.nr.roulette.exceptions.ValidationException;

import talk.BetRequest;
import talk.RegisterRequest;
import talk.RegisterResponse;
import talk.Response;

	

public class InDoor {
	
    //nr update
    
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
		
		Player player = null;
		RegisterResponse response = null;
		
		try {
			player = new Player(name, password);
						
			userid = player.getId().toString();
			answer = ANSWER_OK;
			response = new RegisterResponse (userid, command, answer);
		}
		catch (ValidationException vex) {
			userid = NO_ID;
			answer = ANSWER_BAD;
			response = new RegisterResponse (userid, command, answer, vex.getMessage() );
		}
		
		OperationResult regPlayerResult = croupie_need_to_be_created.registerPlayer(player);
		
		
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
	
	
	

