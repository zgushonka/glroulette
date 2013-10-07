package bets;

import org.nr.roulette.exceptions.ValidationException;

public class ColumnBet extends Bet {
		
	private static final int RATE = 2;

	public ColumnBet (int number, int stake) throws ValidationException {
		super (RATE, number, stake);
	}
	

	@Override
	protected int calcBetCode(int number) {
		// shift to zero and switch column
		int column = (number - 1) % 3;
		return column;
	}
	
    public int hashCode() {
        return 3000 + getBetCode();
    }	

}

//	1/3 numbers bet in line
