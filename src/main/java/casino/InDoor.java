package casino;

import talk.BetRequest;
import talk.RegisterRequest;
import talk.Response;

public class InDoor { 
	
	public static Response processBetRequest (BetRequest request) {
		System.out.println("Bet request received "+request);
		return null;
	}
	
	public static Response processRegisterRequest(RegisterRequest request){
		System.out.println("Register request received "+request);
		return null;
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
	
	
	

