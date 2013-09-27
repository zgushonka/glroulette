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
    
    //  list of registered players
    private Set<Player> players = new HashSet<Player>();
    
    //  list of bets for next Spin
    private List<Bet> bets = new ArrayList<Bet>();
    
    //  binding bet to player
    private Map<UUID, UUID> betsToPlayer = new HashMap<UUID, UUID>();
    
    public OperationResult registerPlayer(Player player)
    {
        return players.add(player) ?
                OperationResult.PLAYER_REGISTERED :
                OperationResult.PLAYER_ALREADY_REGISTERED;
    }
    
    public void flushAllPlayers ()
    {
        players.clear();
        bets.clear();
        betsToPlayer.clear();
    }   
    
    
    protected void addBet(Bet bet, Player player)
    {
        // TODO check bet for unique
        bets.add(bet);
        betsToPlayer.put( bet.getId(), player.getId() );        
    }
    
    
    
    public OperationResult processBet() {
        return OperationResult.BET_WRONG;
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
