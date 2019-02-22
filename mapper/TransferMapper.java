package mapper;

import java.util.List;

import beans.OwnBean;
import beans.TransferBean;

public interface TransferMapper {

	public List<TransferBean> degreeC() throws Exception;

	public List<TransferBean> betweenC() throws Exception;

	public List<OwnBean> own() throws Exception;
}
