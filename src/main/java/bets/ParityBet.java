package bets;

import org.nr.roulette.exceptions.ValidationException;

public class ParityBet extends Bet {
	
	private static final int RATE = 1;

	public ParityBet (int number, int stake) throws ValidationException {
		super (RATE, number, stake);
	}
	
	
	@Override
	protected int calcBetCode (int number) {
		return number & 1;
		// even = 0
		// odd  = 1
	}
	
    public int hashCode() {
        return 8000 + getBetCode();
    }
	
}
