package talk;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BetResponse extends Response {
	
	public BetResponse() {
	    super();
	    
	};
	
    public BetResponse (String userid, 
                        String command, 
                        String answer, 
                        String tableType, 
                        int stake, 
                        int number, 
                        String betType, 
                        String reason) {
        
		super (userid, command, answer, reason);
		setStake(stake);
		setTableType(tableType);
		setNumber(number);
		setBetType(betType);
	}
	
	public String getTableType() {
		return tableType;
	}
	protected void setTableType(String tableType) {
		this.tableType = tableType;
	}
	
	public int getStake() {
		return stake;
	}
	protected void setStake(int stake) {
		this.stake = stake;
	}
	
	public int getNumber() {
		return number;
	}
	protected void setNumber(int number) {
		this.number = number;
	}

	public String getBetType() {
		return betType;
	}
	protected void setBetType(String betType) {
		this.betType = betType;
	}

	@XmlElement
	private String tableType;
	
	@XmlElement
	private int stake;
	
	@XmlElement
	private int number;
	
	@XmlElement
	private String betType;
	
}
