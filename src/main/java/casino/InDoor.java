package casino;

import org.nr.roulette.exceptions.ValidationException;

import talk.BetRequest;
import talk.BetResponse;
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
		
//		BetResponse (String userid, String command, String answer, String tableType, int stake, int number, String betType)
		
		BetResponse response = null;
		String answer = null;
		
		try {
			if ( request.getBetType() == "StrightBet" ) {
				
			}
			
			
			// TODO remove the stub below
			if (request.getBetType().equals("Please remove this stub"))
			{ throw new ValidationException("stub"); }
			
			
		}
		catch (ValidationException vex) {
			
		}
		
		response = new BetResponse(	request.getUserid(),
				"bet request",
				answer,
				request.getTableType(),
				request.getStake(),
				request.getNumber(),
				request.getBetType());
		
		return null;
	}
	
	public static Response processRegisterRequest(RegisterRequest request){
		System.out.println( "Register request received from "+request.getPlayerName() );
		
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
		}
		catch (ValidationException vex) {
			userid = NO_ID;
			answer = ANSWER_BAD;
			String reason = vex.getMessage();
			response = new RegisterResponse (userid, command, answer, reason );
		}
		
		//update
//		OperationResult regPlayerResult = croupie_need_to_be_created.registerPlayer(player);
		OperationResult regPlayerResult = Croupie.newInstance().registerPlayer(player);
		
		if ( regPlayerResult == OperationResult.PLAYER_REGISTERED) {
			System.out.println( "Player successfully registered: "+request.getPlayerName() );
			
			userid = player.getId().toString();
			answer = ANSWER_OK;
			response = new RegisterResponse (userid, command, answer);
		}
		else if ( regPlayerResult == OperationResult.PLAYER_ALREADY_REGISTERED ) {
			userid = player.getId().toString();
			answer = ANSWER_BAD;
			String reason = "Player already registered";
			response = new RegisterResponse (userid, command, answer, reason );
		}
				
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
	
	
	

