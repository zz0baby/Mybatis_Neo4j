package beans;

public class Loan {
	private String PARTY_ID;
	private String LOAN_ACCT_NUM_AGT_NUM;
	private int score;

	public String getPARTY_ID() {
		return PARTY_ID;
	}

	public void setPARTY_ID(String pARTY_ID) {
		PARTY_ID = pARTY_ID;
	}

	public String getLOAN_ACCT_NUM_AGT_NUM() {
		return LOAN_ACCT_NUM_AGT_NUM;
	}

	public void setLOAN_ACCT_NUM_AGT_NUM(String lOAN_ACCT_NUM_AGT_NUM) {
		LOAN_ACCT_NUM_AGT_NUM = lOAN_ACCT_NUM_AGT_NUM;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String toString() {
		return PARTY_ID + "\t" + LOAN_ACCT_NUM_AGT_NUM + "\t" + score
				+ "\n";
	}
}
