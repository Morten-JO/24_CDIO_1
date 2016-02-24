	package data;

import java.util.ArrayList;
import java.util.List;
import exception.DALException;

public class OperatorDAO implements IOperatorDAO{
	private List<OperatorDTO> operatorList = new ArrayList<OperatorDTO>();
	 private OperatorDTO currentUser;
	
	public OperatorDTO getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(OperatorDTO currentUser) {
		this.currentUser = currentUser;
	}

	public OperatorDAO() {
		OperatorDTO sysadmin = new OperatorDTO("sysadmin", "10");
		sysadmin.setPassword("Abc02324");
		OperatorDTO opr1 = new OperatorDTO("Test1", "1231231231");
		OperatorDTO opr2 = new OperatorDTO("Test2", "1231231232");
		OperatorDTO opr3 = new OperatorDTO("Test3", "1231231233");

		operatorList.add(sysadmin);
		operatorList.add(opr1);
		operatorList.add(opr2);
		operatorList.add(opr3);

	}

	@Override
	public OperatorDTO getOperator(int ID) throws DALException {
		//need check if id is in array
		return operatorList.get(ID);
	}

	@Override
	public List<OperatorDTO> getOperatorList() throws DALException {
		return operatorList;
	}

	@Override
	public void createOperator(OperatorDTO opr) throws DALException {
		if (opr != null)
		operatorList.add(opr);		
	}

	@Override
	public void updateOperator(OperatorDTO opr) throws DALException {
		//locating index where ID matches (IDs might not appear incremental after user deletion)
		for(OperatorDTO o: operatorList){
			if(o.getID() == opr.getID())
				//set matching list opr to updated opr-obj
				o = opr;
		}
	}

}
