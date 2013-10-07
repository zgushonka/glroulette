package bets;

import java.util.HashSet;

import org.nr.roulette.exceptions.ValidationException;

public class StrightBet extends Bet {

    private static final int RATE = 35;

    public StrightBet(int number, int stake) throws ValidationException {
        super(RATE, number, stake);
    }

    @Override
    protected int calcBetCode(int number) {
        return number;
    }

    @Override
    public int hashCode() {
        return (int) 1000 + getBetCode();
    }

    public static void main(String[] args) throws ValidationException {

        StrightBet sb1 = new StrightBet(7, 10);
        StrightBet sb2 = new StrightBet(7, 10);
        HashSet<StrightBet> bets = new HashSet<StrightBet>();

        bets.add((StrightBet) sb1);
        bets.add((StrightBet) sb2);

        System.out.println(sb1.equals(sb2));
        System.out.println(bets.size());

    }

}
