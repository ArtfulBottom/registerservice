package edu.uark.models.repositories.interfaces;

import java.util.UUID;

import edu.uark.dataaccess.repository.BaseRepositoryInterface;
import edu.uark.models.entities.TransactionEntryEntity;

public interface TransactionEntryRepositoryInterface extends BaseRepositoryInterface<TransactionEntryEntity> {
	TransactionEntryEntity byTransactionId(UUID transactionId);
}
