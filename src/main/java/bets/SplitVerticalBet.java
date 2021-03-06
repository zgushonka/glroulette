package bets;

import org.nr.roulette.exceptions.ValidationException;

public class SplitVerticalBet extends SmallSectorBet {
	
	private static final int RATE = 17;
	
	public SplitVerticalBet (int number, int stake) throws ValidationException {
		super (RATE, number, stake);
	}
	

	@Override
	protected int calcBetCode(int number) {
		return correctStreet(number);
	}
	
	
	@Override
	protected boolean compareBetWithWin () {
		
		boolean betWin = ( 	numberMatchWith(NO_SHIFT) ||
							numberMatchWith(VERTICAL_SHIFT) );
		
		return betWin;
	}
	
	
    public int hashCode() {
        return 11000 + getBetCode();
    }


	

}

//	2 numbers bet vertical (e.g. 1+4, 20+23)
