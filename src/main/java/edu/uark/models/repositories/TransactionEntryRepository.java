package edu.uark.models.repositories;

import java.sql.SQLException;
import java.util.UUID;

import edu.uark.dataaccess.repository.BaseRepository;
import edu.uark.dataaccess.repository.DatabaseTable;
import edu.uark.dataaccess.repository.helpers.SQLComparisonType;
import edu.uark.dataaccess.repository.helpers.where.WhereClause;
import edu.uark.dataaccess.repository.helpers.where.WhereContainer;
import edu.uark.models.entities.TransactionEntryEntity;
import edu.uark.models.entities.fieldnames.TransactionEntryFieldNames;
import edu.uark.models.repositories.interfaces.TransactionEntryRepositoryInterface;

public class TransactionEntryRepository extends BaseRepository<TransactionEntryEntity> implements TransactionEntryRepositoryInterface {
	@Override
	public TransactionEntryEntity byTransactionId(UUID transactionId) {
		return this.firstOrDefaultWhere(
			new WhereContainer(
				(new WhereClause()).
					table(this.primaryTable).
					fieldName(TransactionEntryFieldNames.TRANSACTION_ID).
					comparison(SQLComparisonType.EQUALS)
			),
			(ps) -> {
				try {
					ps.setObject(1, transactionId);
				} catch (SQLException e) {}

				return ps;
			}
		);
	}
	
	@Override
	public TransactionEntryEntity createOne() {
		return new TransactionEntryEntity();
	}
	
	public TransactionEntryRepository() {
		super(DatabaseTable.TRANSACTION_ENTRY);
	}
}
