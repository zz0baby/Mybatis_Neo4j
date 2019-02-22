package mapper;

import java.util.List;

import beans.AgtNum;
import beans.Jjc;
import beans.Loan;

public interface ScoreMapper {

	public List<Jjc> countJjc(String label) throws Exception;
	public List<AgtNum> countAgt(String label) throws Exception;
	public List<Loan> countLoan(String label) throws Exception;
	
	public List<Jjc> betweenJjc() throws Exception;
	public List<AgtNum> betweenDjc() throws Exception;
	public List<AgtNum> betweenAccount() throws Exception;
	public List<Loan> betweenLoan() throws Exception;
}
