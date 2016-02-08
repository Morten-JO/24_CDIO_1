package main;

import java.util.List;

public interface IOperatorDAO {
	Operator getOperator(int ID) throws DALException;
	List<Operator> getOperatorList() throws DALException;
	void createOperator(Operator opr) throws DALException;
	void updateOperator(Operator opr) throws DALException;
}
