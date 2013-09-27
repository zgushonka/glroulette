package casino;

import talk.BetRequest;
import talk.BetResponse;
import talk.RegisterRequest;
import talk.RegisterResponse;
import talk.Response;

public class RequestProcessorStub { 
	
	public static Response processBetRequest (BetRequest request) {
		BetResponse response = new BetResponse("some_user_id", "bet", "OK", "normal", 100, 30, "number");
		//response = new BetResponse("some_user_id", "bet", "some error description", "normal", 100, 30, "number");
		return response;
	}
	
	public static Response processRegisterRequest(RegisterRequest request){
		RegisterResponse response = new RegisterResponse("some_user_id", "register", "OK");
		//response = new RegisterResponse("some_user_id", "register", "Some trouble has happened. Go and look for the reason in some source :-)");
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
	
	
	

