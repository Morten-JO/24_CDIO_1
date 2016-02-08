package main;

import java.util.List;

public interface IOperatorDAO {
	OperatorDTO getOperator(int ID) throws DALException;
	List<OperatorDTO> getOperatorList() throws DALException;
	void createOperator(OperatorDTO opr) throws DALException;
	void updateOperator(OperatorDTO opr) throws DALException;
}
