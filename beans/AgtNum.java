package beans;

public class AgtNum {
	private String PARTY_ID;
	private String AGT_NUM;
	private int score;

	public String getPARTY_ID() {
		return PARTY_ID;
	}

	public void setPARTY_ID(String pARTY_ID) {
		PARTY_ID = pARTY_ID;
	}

	public String getAGT_NUM() {
		return AGT_NUM;
	}

	public void setAGT_NUM(String aGT_NUM) {
		AGT_NUM = aGT_NUM;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String toString() {
		return PARTY_ID + "\t" + AGT_NUM + "\t" + score + "\n";
	}

}
