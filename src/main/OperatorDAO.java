package main;

import java.util.ArrayList;
import java.util.List;

public class OperatorDAO implements IOperatorDAO{
	DataAccessMySQL dataAccess = new DataAccessMySQL();
	List<OperatorDTO> operatorList = new ArrayList<OperatorDTO>();
	
	public OperatorDAO() {
		OperatorDTO opr1 = new OperatorDTO("Test1", "1231231231");
		OperatorDTO opr2 = new OperatorDTO("Test2", "1231231232");
		OperatorDTO opr3 = new OperatorDTO("Test3", "1231231233");
		OperatorDTO sysadmin = new OperatorDTO("sysadmin", "sysadmin");
		operatorList.add(opr1);
		operatorList.add(opr2);
		operatorList.add(opr3);
		operatorList.add(sysadmin);

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
