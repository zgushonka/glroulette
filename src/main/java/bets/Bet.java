package bets;

import java.util.UUID;

import org.nr.roulette.exceptions.ValidationException;


//	Base Class for all Bets
public abstract class Bet  {

	//	returns stake*rate if win
	//	OR  
	//	-stake if lose
	public int calcBetResult (int winningNumber) {
		
		boolean betWin = checkForWin (winningNumber);
		
		//	evaluate win coins
		int betResult = 0;
		
		if (betWin) {
			betResult = stake * rate;
		} else {
			betResult = stake * -1;		// loose stake
		}
		
		return betResult;
	}	
	

	
	private UUID id = UUID.randomUUID();
	
	public UUID getId() {
		return this.id;
	}
	
	
	
//	stake to win ratio
	private final int rate;
	
//	Constructor
	protected Bet (int rate, int number, int stake) throws ValidationException {
		this.rate = rate;
		createBet (number, stake);
	}
	
	
	private void createBet (int number, int stake) throws ValidationException {
		setStake(stake);
		setBet(number);		
	}	
	
	
	
	private int stake;
	
	private void setStake(int stake) throws ValidationException {
		checkStakeValidity(stake);		
		this.stake = stake;
	}
	
	
	
	//	storage for bet number
	private int number;
	//	betCode used for win decision
	private int betCode;
	
	private void setBet(int number) throws ValidationException {
		checkNumberValidity(number);
		
		this.number = number;
		this.betCode = calcBetCode(number);
	}
	
	protected int getNumber() {
		return number;
	 }
	
	public int getBetCode() {
		return betCode;
	}
	
	
	//	win predicate
	private int winningNumber;
	
	//	did we win? True or False
	private boolean checkForWin(int winningNumber) {
		
		// here we can check WinNumber for Valid Value if necessary
		
		this.winningNumber = winningNumber; 
	
		boolean	betWin = compareBetWithWin();
		return betWin;
	}
		
	protected int getWinningNumber() {
		return winningNumber;
	}


	//	same for all bets, except Split and Corner
	protected boolean compareBetWithWin() {
		return getBetCode() == calcBetCode( getWinningNumber() );
	}
	
	
	
	//	calculate bet number for each bet type
	abstract protected int calcBetCode(int number);
	
	
	
	//	stake must be less than 10 coins
	private static final int MIN_STAKE = 1;
	private static final int MAX_STAKE = 10;
	
	private void checkStakeValidity (int stake) throws ValidationException  {
		if ( (MIN_STAKE <= stake) && (stake <= MAX_STAKE) ) {
			// in range - OK
		} else {
			// out of range
			throw new ValidationException("Stake is out of range.");
		}
	}

	
	
	//	numbers in roulette is between 1 and 36
	//	zero disabled
	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 36;
	
	protected void checkNumberValidity (int number) throws ValidationException {
		if ( (MIN_NUMBER <= number) && (number <= MAX_NUMBER) ) {
			// in range - OK
		} else {
			// out of range
			throw new ValidationException("Bet number is out of range.");
		}
	}
	


	@Override
	public boolean equals (Object candidate){
//		boolean sameClass = ( bet.getClass() ==  this.getClass() );
//		boolean samebetCode = ( this.getBetCode() == bet.getBetCode() );
		//return sameClass && samebetCode;
	    if (candidate instanceof Bet)
	    {
	        return ((Bet) candidate).hashCode() == this.hashCode();
	    }
	    return false;
	}

	/*
	public int hashCode() {
        return new HashCodeBuilder(17, 31).
            append(this.getClass()).
            append(this.getBetCode()).
            toHashCode();
    }
	*/
}











