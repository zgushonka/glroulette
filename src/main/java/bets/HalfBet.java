package bets;

import org.nr.roulette.exceptions.ValidationException;

public class HalfBet extends Bet {	
	
	private static final int RATE = 1;

	public HalfBet (int number, int stake) throws ValidationException {
		super (RATE, number, stake);
	}

	@Override
	protected int calcBetCode(int number) {
		if (number < 19) {
			return 0;
		}
		return 1;
	}
	
    public int hashCode() {
        return 6000 + getBetCode();
    }

}

//	half numbers bet
