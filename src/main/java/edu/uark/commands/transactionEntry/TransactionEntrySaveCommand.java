package edu.uark.commands.transactionEntry;


import java.util.UUID;

import edu.uark.commands.ResultCommandInterface;
import edu.uark.models.api.TransactionEntry;
import edu.uark.models.api.enums.TransactionApiRequestStatus;
import edu.uark.models.entities.TransactionEntryEntity;
import edu.uark.models.repositories.TransactionEntryRepository;
import edu.uark.models.repositories.interfaces.TransactionEntryRepositoryInterface;

public class TransactionEntrySaveCommand implements ResultCommandInterface<TransactionEntry>{
	@Override
	public TransactionEntry execute() {
		System.out.println(this.getApiTransacitonEntry().getQuantity());
		if(this.apiTransactionEntry.getTransactionId() == null || this.getApiTransacitonEntry().getTransactionId().equals(new UUID(0,0)))
		{
			return (new TransactionEntry()).setApiRequestStatus(TransactionApiRequestStatus.INVALID_INPUT).
					setApiRequestMessage("TransactionID fields may not be empty.");
		}
		
		TransactionEntryEntity transactionEntryEntity = this.transactionEntryRepository.get(this.apiTransactionEntry.getId());
		if(transactionEntryEntity != null)
		{
			this.apiTransactionEntry = transactionEntryEntity.synchronize(this.apiTransactionEntry);
		}
		else 
		{
			transactionEntryEntity = new TransactionEntryEntity(this.apiTransactionEntry);
		}
		
		transactionEntryEntity.save();
		if ((new UUID(0, 0)).equals(this.apiTransactionEntry.getId())) 
		{
			this.apiTransactionEntry.setId(transactionEntryEntity.getId());
		}
		
		return this.apiTransactionEntry;
	}
	
	//Properties
		private TransactionEntry apiTransactionEntry;
		public TransactionEntry getApiTransacitonEntry() {
			return this.apiTransactionEntry;
		}
		public TransactionEntrySaveCommand setApiTransactionEntry(TransactionEntry apiTransactionEntry) {
			this.apiTransactionEntry = apiTransactionEntry;
			return this;
		}
		
		private TransactionEntryRepositoryInterface transactionEntryRepository;
		public TransactionEntryRepositoryInterface getTransactionEntryRepository() {
			return this.transactionEntryRepository;
		}
		public TransactionEntrySaveCommand setTransactionEntryRepository(TransactionEntryRepositoryInterface transactionEntryRepository) {
			this.transactionEntryRepository = transactionEntryRepository;
			return this;
		}
		
		public TransactionEntrySaveCommand() {
			this.transactionEntryRepository = new TransactionEntryRepository();
		}

}
