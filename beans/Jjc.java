package beans;

public class Jjc {
	private String PARTY_ID;
	private String AGT_NUM;
	private String CURR_TERM_MAIN_ACCT_AGT_NUM;
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

	public String getCURR_TERM_MAIN_ACCT_AGT_NUM() {
		return CURR_TERM_MAIN_ACCT_AGT_NUM;
	}

	public void setCURR_TERM_MAIN_ACCT_AGT_NUM(
			String cURR_TERM_MAIN_ACCT_AGT_NUM) {
		CURR_TERM_MAIN_ACCT_AGT_NUM = cURR_TERM_MAIN_ACCT_AGT_NUM;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String toString() {
		return PARTY_ID + "\t" + AGT_NUM + "\t" + CURR_TERM_MAIN_ACCT_AGT_NUM
				+ "\t" + score + "\n";
	}
}
