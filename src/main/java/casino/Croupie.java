package casino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.nr.roulette.exceptions.ValidationException;

import bets.Bet;

public class Croupie {

    private static Croupie myOwnCroupieWithBlackJack = null;
    private static Integer manualSpinNumber = null;
    private static Roulette roulette = null;

    private Croupie() {
    }

    // Singleton
    public synchronized static Croupie newInstance() {
        if (myOwnCroupieWithBlackJack == null) {
            myOwnCroupieWithBlackJack = new Croupie();
            roulette = new Roulette();
            manualSpinNumber = new Integer(0);
        }
        return myOwnCroupieWithBlackJack; // and whores
    }

    // list of bets for next Spin
    private List<Bet> bets = new ArrayList<Bet>();

    // map of registered players
    private Map<UUID, Player> players = new HashMap<UUID, Player>();

    // binding bet to player
    private Map<UUID, UUID> betsToPlayer = new HashMap<UUID, UUID>();

    // Player BetCodes Set
    private Map<UUID, Set<Bet>> playerBets = new HashMap<UUID, Set<Bet> >();

    public OperationResult registerPlayer(Player player) {
        players.put(player.getId(), player);

        // Create and Assign Set of BetCodes
        Set<Bet> betsSet = new HashSet<Bet>();
        playerBets.put(player.getId(), betsSet);
        return OperationResult.PLAYER_REGISTERED;
    }

    public OperationResult registerBet(Bet bet, UUID playerId) {
        // - check player is registered
        if (!players.containsKey(playerId)) {
            throw new NullPointerException("Player with ID=" + playerId + " is not registered");
        }

        // - check bet unique for player
        if (isBetNewForPlayer(bet, playerId)) {
            addBet(bet, playerId);
            return OperationResult.BET_OK;
        }
        // else
        return OperationResult.BET_WRONG;
    }

    private boolean isBetNewForPlayer(Bet bet, UUID playerId) {

    	for (UUID id : playerBets.keySet()) {
            System.out.println(id);
            System.out.println(playerBets.get(id));
        }
        
        Set<Bet> betsSet = playerBets.get(playerId);
        boolean betExist = betsSet.contains(bet);
        
        boolean betIsNew = !betExist;
        return betIsNew;
    }

    public void flushAllPlayers() {
        players.clear();
        flushAllBets();
    }

    private void flushAllBets() {
        bets.clear();
        betsToPlayer.clear();
        //playerBets.clear();
        for (UUID id : playerBets.keySet())
        {
            playerBets.get(id).clear();
        }
        
    }

    protected void addBet(Bet bet, UUID playerId) {
        UUID betID = bet.getId();

        bets.add(bet);
        betsToPlayer.put(betID, playerId);
        playerBets.get(playerId).add(bet);

        players.get(playerId).incrementBetCount();
    }

    private boolean enableManualSpin;

    public boolean isManualSpin() {
        return enableManualSpin;
    }

    public void setManualSpin(boolean manualSpin, Integer number) {
        this.enableManualSpin = manualSpin;
        manualSpinNumber = number;
    }

    public boolean isPasswordValidForUserId(String userid, String password) {
        if (players.containsKey(UUID.fromString(userid))) {
            return players.get(UUID.fromString(userid)).isPasswordOk(password);
        }
        return false;
    }

    public void checkPlayerExists(String name) throws ValidationException {
        for (Player aPlayer : players.values()) {
            if (aPlayer.getName().equals(name)) {
                throw new ValidationException("The player with name " + name + " already exists. Please choose another name");
            }
        }

    }

    public void performGameMove() {
        // select win number
        int winNumber = 0;

        // winNumber = isManualSpin() ? manualSpinNumber :
        // roulette.performSpin();
        if (enableManualSpin) {
            if (manualSpinNumber != null) {
                winNumber = manualSpinNumber;
            } else
                winNumber = roulette.performSpin();
        } else {
            winNumber = roulette.performSpin();
        }

        // proceed all bets
        for (Bet currentBet : bets) {

            // calculate win/loose coins
            int betResult = currentBet.calcBetResult(winNumber);

            // store bet player
            UUID currentPlayerId = betsToPlayer.get(currentBet.getId());
            Player currentPlayer = players.get(currentPlayerId);

            currentPlayer.applyBetResult(betResult);

            boolean betWins = betResult > 0;
            if (betWins) {
                currentPlayer.incrementWinBetCount();
            }
        }

        // clear bet arrays
        flushAllBets();
        // ? betCodes.clear

    }

}
