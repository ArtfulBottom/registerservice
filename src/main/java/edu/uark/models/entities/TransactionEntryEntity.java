package edu.uark.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import edu.uark.dataaccess.entities.BaseEntity;
import edu.uark.models.api.TransactionEntry;
import edu.uark.models.entities.fieldnames.TransactionEntryFieldNames;
import edu.uark.models.enums.TransactionClassification;
import edu.uark.models.repositories.TransactionEntryRepository;

public class TransactionEntryEntity extends BaseEntity<TransactionEntryEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.transactionId = ((UUID) rs.getObject(TransactionEntryFieldNames.TRANSACTION_ID));
		this.productId = ((UUID) rs.getObject(TransactionEntryFieldNames.PRODUCT_ID));
		this.quantity = rs.getInt(TransactionEntryFieldNames.QUANTITY);
		this.unitPrice = rs.getBigDecimal(TransactionEntryFieldNames.UNIT_PRICE);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(TransactionFieldNames.CASHIER_ID, this.cashierId);
		record.put(TransactionFieldNames.TOTAL_AMOUNT, this.totalAmount);
		record.put(TransactionFieldNames.CLASSIFICATION, this.classification.getValue());
		record.put(TransactionFieldNames.CREATED_ON, Timestamp.valueOf(this.createdOn));
		record.put(TransactionFieldNames.REFERENCE_ID, this.referenceId);
		
		return record;
	}
    
    private UUID cashierId;
    public UUID getCashierId() {
    	return this.cashierId;
    }
    public TransactionEntryEntity setCashierId(UUID cashierId) {
    	if (!this.cashierId.equals(cashierId)) {
    		this.cashierId = cashierId;
    		this.propertyChanged(TransactionFieldNames.CASHIER_ID);
    	}
    	
    	return this;
    }
    
    private int totalAmount;
    public int getTotalAmount() {
    	return this.totalAmount;
    }
    public TransactionEntryEntity setTotalAmount(int totalAmount) {
    	if (this.totalAmount != totalAmount) {
    		this.totalAmount = totalAmount;
    		this.propertyChanged(TransactionFieldNames.TOTAL_AMOUNT);
    	}
    	
    	return this;
    }
    
    
    private TransactionClassification classification;
    public TransactionClassification getClassification() {
    	return this.classification;
    }
    public TransactionEntryEntity setClassification(TransactionClassification classification) {
    	if (this.classification != classification) {
    		this.classification = classification;
    		this.propertyChanged(TransactionFieldNames.CLASSIFICATION);
    	}
    	
    	return this;
    }
    
    private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	
	private UUID referenceId;
	public UUID getReferenceId() {
		return this.referenceId;
	}
	public TransactionEntryEntity setReferenceId(UUID referenceId) {
		if (!this.referenceId.equals(referenceId)) {
			this.referenceId = referenceId;
			this.propertyChanged(TransactionFieldNames.REFERENCE_ID);
		}
		
		return this;
	}
	
	public Transaction synchronize(Transaction apiTransaction) {
		this.setCashierId(apiTransaction.getCashierId());
		this.setTotalAmount(apiTransaction.getTotalAmount());
		this.setClassification(apiTransaction.getClassification());
		this.setReferenceId(apiTransaction.getReferenceId());
		
		apiTransaction.setCreatedOn(this.createdOn);
		
		return apiTransaction;
	}
	
	public TransactionEntryEntity() {
		super(new TransactionEntryRepository());
		
		this.cashierId = new UUID(0, 0);
		this.totalAmount = 0;
		this.classification = TransactionClassification.NOT_DEFINED;
		this.createdOn = LocalDateTime.now();
		this.referenceId = new UUID(0, 0);
	}
	
	public TransactionEntryEntity(UUID id) {
		super(id, new TransactionEntryRepository());
		
		this.cashierId = new UUID(0, 0);
		this.totalAmount = 0;
		this.classification = TransactionClassification.NOT_DEFINED;
		this.createdOn = LocalDateTime.now();
		this.referenceId = new UUID(0, 0);
	}

	public TransactionEntryEntity(Transaction apiTransaction) {
		super(apiTransaction.getId(), new TransactionEntryRepository());
		
		this.cashierId = apiTransaction.getCashierId();
		this.totalAmount = apiTransaction.getTotalAmount();
		this.classification = apiTransaction.getClassification();
		this.referenceId = apiTransaction.getReferenceId();

		this.createdOn = LocalDateTime.now();
	}
}
