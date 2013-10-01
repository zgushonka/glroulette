package casino;

import java.util.UUID;

import org.nr.roulette.exceptions.ValidationException;

import bets.*;
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
	
	final static String BET_COMMAND = "NO_ID";
	final static String REGISTER_COMMAND = "register";
	
	public static Response processBetRequest (BetRequest request) {
		System.out.println("Bet request received "+request);
		
//		BetResponse (String userid, String command, String answer, String tableType, int stake, int number, String betType)
		
		String userid = request.getUserid();
		String command = BET_COMMAND;
		String answer = null;
		BetResponse response = null;
		
		
		//	- create object Bet
		//	- send bet to croupie
		Bet bet = null;
		
		try {
			if ( request.getBetType() == "StrightBet" ) {
				bet = new StrightBet(request.getNumber(), request.getStake() );
			}
			else if ( request.getBetType() == "ColourBet" ) {
				bet = new ColourBet(request.getNumber(), request.getStake() );
			}
			else if ( request.getBetType() == "ColumnBet" ) {
				bet = new ColumnBet(request.getNumber(), request.getStake() );
			}
			else if ( request.getBetType() == "CornerBet" ) {
				bet = new CornerBet(request.getNumber(), request.getStake() );
			}
			else if ( request.getBetType() == "DozenBet" ) {
				bet = new DozenBet(request.getNumber(), request.getStake() );
			}
			else if ( request.getBetType() == "HalfBet" ) {
				bet = new HalfBet(request.getNumber(), request.getStake() );
			}
			else if ( request.getBetType() == "LineBet" ) {
				bet = new LineBet(request.getNumber(), request.getStake() );
			}
			else if ( request.getBetType() == "ParityBet" ) {
				bet = new ParityBet(request.getNumber(), request.getStake() );
			}
			else if ( request.getBetType() == "SplitHorizontalBet" ) {
				bet = new SplitHorizontalBet(request.getNumber(), request.getStake() );
			}
			else if ( request.getBetType() == "SplitVerticalBet" ) {
				bet = new SplitVerticalBet(request.getNumber(), request.getStake() );
			}
			else if ( request.getBetType() == "StreetBet" ) {
				bet = new StreetBet(request.getNumber(), request.getStake() );
			}
			else {
				//	ERORR
				System.out.println( "Wrong Bet Type: "+request.getBetType()+ " from player: " +request.getPlayerName() );
			}
			
			
			// TODO remove the stub below
			if (request.getBetType().equals("Please remove this stub"))
			{ throw new ValidationException("stub"); }
			
			
		}
		catch (ValidationException vex) {
			answer = ANSWER_BAD;
			String reason = vex.getMessage();
			return response = new BetResponse (userid, command, answer, reason );
		}
		
		// if we a here then Bet created successfully
		UUID playerId = UUID.fromString(request.getUserid());
		OperationResult regBetResult = Croupie.newInstance().registerBet(bet, playerId);
		
		
		response = new BetResponse(	request.getUserid(),
				"bet request",
				answer,
				request.getTableType(),
				request.getStake(),
				request.getNumber(),
				request.getBetType());
		
		return response;
	}
	
	public static Response processRegisterRequest(RegisterRequest request){
		System.out.println( "Register request received from "+request.getPlayerName() );
						
		// answer Strings
		String userid;
		String command = REGISTER_COMMAND;
		String answer;
				
		Player player = null;
		RegisterResponse response = null;
		
		try {
			String nameFromRequest = request.getPlayerName();
			String passwordFromRequest = request.getPlayerPassword();
			
			player = new Player(nameFromRequest, passwordFromRequest);
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
			String reason = "Error: Player already registered";
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
	
	
	

