package main;

import java.util.ArrayList;
import java.util.List;

public class OperatorDAO implements IOperatorDAO{
	DataAccessMySQL dataAccess = new DataAccessMySQL();
	List<Operator> operatorList = new ArrayList<Operator>();
	
	public OperatorDAO() {
		Operator opr1 = new Operator("Test1", "1231231231");
		Operator opr2 = new Operator("Test2", "1231231232");
		Operator opr3 = new Operator("Test3", "1231231233");
		Operator sysadmin = new Operator("sysadmin", "sysadmin");
		operatorList.add(opr1);
		operatorList.add(opr2);
		operatorList.add(opr3);
		operatorList.add(sysadmin);

	}

	@Override
	public Operator getOperator(int ID) throws DALException {
		//need check if id is in array
		return operatorList.get(ID);
	}

	@Override
	public List<Operator> getOperatorList() throws DALException {
		return operatorList;
	}

	@Override
	public void createOperator(Operator opr) throws DALException {
		operatorList.add(opr);
		
	}

	@Override
	public void updateOperator(Operator opr) throws DALException {
		//locating index where ID matches (IDs might not appear incremental after user deletion)
		for(Operator o: operatorList){
			if(o.getID() == opr.getID())
				//set matching list opr to updated opr-obj
				o = opr;
		}
	}

}
