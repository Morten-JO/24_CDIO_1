package data;

import java.util.List;

import exception.DALException;

public interface IOperatorDAO {
	OperatorDTO getOperator(int ID) throws DALException;
	List<OperatorDTO> getOperatorList() throws DALException;
	void createOperator(OperatorDTO opr) throws DALException;
	void updateOperator(OperatorDTO opr) throws DALException;
}
