package casino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import bets.Bet;


public class Croupie {
    
    private static Croupie myOwnCroupieWithBlackJack = null;
    
    private Croupie() {}
    
    // Singleton
    public synchronized static Croupie newInstance() {
        if (myOwnCroupieWithBlackJack == null)
            myOwnCroupieWithBlackJack = new Croupie();
        return myOwnCroupieWithBlackJack;
    }

    
    //  list of bets for next Spin
    private List<Bet> bets = new ArrayList<Bet>();
    
    //  list of registered players
    private Set<Player> players = new HashSet<Player>();
        
    //  binding bet to player
    private Map<UUID, UUID> betsToPlayer = new HashMap<UUID, UUID>();
    
    //  Player BetCodes Set
    private Map< UUID, Set<Integer> > playerBets = new HashMap< UUID, Set<Integer> > ();
    
    
    
    public OperationResult registerPlayer(Player player)
    {	
    	boolean playerIsNew = players.add(player);
    	
    	if (playerIsNew) {
    		//	Create and Assign Set of BetCodes
    		Set<Integer> betCodesSet = new HashSet<Integer>();
    		playerBets.put(player.getId(), betCodesSet);
    		return OperationResult.PLAYER_REGISTERED;
    	}
    	// else
        return OperationResult.PLAYER_ALREADY_REGISTERED;
    }
    
    
    
    public OperationResult registerBet(Bet bet, UUID playerId)
    {
		//	- check bet unique for player    	
    	if (isBetNewForPlayer (bet, playerId) ) {
    		addBet(bet, playerId);
    		return OperationResult.BET_OK;
    	}
    	//	else
    	return OperationResult.BET_WRONG;
    }

    
    private boolean isBetNewForPlayer(Bet bet, UUID playerId) {
    	return !playerBets.get(playerId).contains(bet.getBetCode());
	}

	public void flushAllPlayers ()
    {
        players.clear();
        bets.clear();
        betsToPlayer.clear();
        playerBets.clear();
    }   
    
    
    protected void addBet(Bet bet, UUID playerId)
    {
    	UUID betID = bet.getId();
    	Integer betCode = bet.getBetCode();
    	
        bets.add(bet);
        betsToPlayer.put(betID, playerId);
        playerBets.get(playerId).add(betCode);
    }
    
    
    private boolean enableManualSpin;   
    
    public boolean isManualSpin()
    {
        return enableManualSpin;
    }

    public void setManualSpin(boolean manualSpin)
    {
        this.enableManualSpin = manualSpin;
    }
    
    
/*  
    protected void playGame () {
        //
        Iterator<Bets> 
    }
/*  */
    
    // all TODO
    // force roulette Spin
        //  with time period 5s             - prime table
        //  by user command "all bets done" - test table
    
    // calculate wins
    
}
