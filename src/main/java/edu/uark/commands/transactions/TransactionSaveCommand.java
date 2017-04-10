package edu.uark.commands.transactions;

import java.util.UUID;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.models.api.Transaction;
import edu.uark.models.api.enums.TransactionApiRequestStatus;
import edu.uark.models.entities.TransactionEntity;
import edu.uark.models.repositories.TransactionRepository;
import edu.uark.models.repositories.interfaces.TransactionRepositoryInterface;

public class TransactionSaveCommand implements ResultCommandInterface<Transaction> {
	@Override
	public Transaction execute() {
		if (this.apiTransaction.getCashierId() == null || this.getApiTransaciton().getCashierId().equals(new UUID(0, 0))) {
			return (new Transaction()).setApiRequestStatus(TransactionApiRequestStatus.INVALID_INPUT).
						setApiRequestMessage("CashierId field may not be empty.");
		}
		
		TransactionEntity transactionEntity = this.transactionRepository.get(this.apiTransaction.getId());
		if (transactionEntity != null) {
			this.apiTransaction = transactionEntity.synchronize(this.apiTransaction);
		} else {
			transactionEntity = new TransactionEntity(this.apiTransaction);
		}
		
		transactionEntity.save();
		if ((new UUID(0, 0)).equals(this.apiTransaction.getId())) {
			this.apiTransaction.setId(transactionEntity.getId());
		}
		
		return this.apiTransaction;
	}
	
	//Properties
	private Transaction apiTransaction;
	public Transaction getApiTransaciton() {
		return this.apiTransaction;
	}
	public TransactionSaveCommand setApiTransaction(Transaction apiTransaction) {
		this.apiTransaction = apiTransaction;
		return this;
	}
	
	private TransactionRepositoryInterface transactionRepository;
	public TransactionRepositoryInterface getTransactionRepository() {
		return this.transactionRepository;
	}
	public TransactionSaveCommand setTransactionRepository(TransactionRepositoryInterface transactionRepository) {
		this.transactionRepository = transactionRepository;
		return this;
	}
	
	public TransactionSaveCommand() {
		this.transactionRepository = new TransactionRepository();
	}
}
