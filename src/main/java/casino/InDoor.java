package casino;

import talk.BetRequest;
import talk.RegisterRequest;

public class InDoor { 
	
	public static void takeObject (BetRequest request) {
		System.out.println("Bet request received "+request);
	}
	
	public static void takeObject(RegisterRequest request){
		System.out.println("Register request received "+request);
		
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
	
	
	

