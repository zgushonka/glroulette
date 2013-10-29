package bets;

import org.nr.roulette.exceptions.ValidationException;

public class LineBet extends SmallSectorBet {

	private static final int RATE = 5;

	public LineBet (int number, int stake) throws ValidationException {
		super (RATE, number, stake);
	}
	

	@Override
	protected int calcBetCode(int number) {
		
		int betNumber = number;
		betNumber = correctStreet(betNumber);
		// shift to zero and divide to line count
		int lineHeadNumber = (number - 1) / 3;
		return lineHeadNumber;
	}
	
	@Override
	protected boolean compareBetWithWin () {
		
		boolean betWin = false;
		for (int i = 0; i < 6; i++) {
			if ( numberMatchWith(i) ) {
				betWin = true;
			}
		}
		
		return betWin;
	}
	
    public int hashCode() {
        return 7000 + getBetCode();
    }
	
}

//	6 numbers bet
