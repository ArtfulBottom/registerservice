package edu.uark.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.models.api.enums.TransactionApiRequestStatus;
import edu.uark.models.entities.TransactionEntity;
import edu.uark.models.entities.fieldnames.TransactionEntryFieldNames;
import edu.uark.models.enums.TransactionClassification;

/*TODO update all fields within this class to match TransactionEntryEntity */
public class TransactionEntry {
	private UUID id;
	public UUID getId() {
		return this.id;
	}
	public TransactionEntry setId(UUID id) {
		this.id = id;
		return this;
	}
	
	private UUID transactionId;
    public UUID getTransactionId() {
    	return this.transactionId;
    }
    public TransactionEntry setCashierId(UUID cashierId) {
    	this.cashierId = cashierId;
    	return this;
    }
    
    private UUID productId;
    private int totalAmount;
    public int getTotalAmount() {
    	return this.totalAmount;
    }
    public TransactionEntry setTotalAmount(int totalAmount) {
    	this.totalAmount = totalAmount;
    	return this;
    }
    
    private TransactionClassification classification;
    public TransactionClassification getClassification() {
    	return this.classification;
    }
    public TransactionEntry setClassification(TransactionClassification classification) {
    	this.classification = classification;
    	return this;
    }
    
    private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	public TransactionEntry setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
		return this;
	}
	
	private UUID referenceId;
	public UUID getReferenceId() {
		return this.referenceId;
	}
	public TransactionEntry setReferenceId(UUID referenceId) {
		this.referenceId = referenceId;
		return this;
	}
	
	private TransactionApiRequestStatus apiRequestStatus;
	public TransactionApiRequestStatus getApiRequestStatus() {
		return this.apiRequestStatus;
	}
	public TransactionEntry setApiRequestStatus(TransactionApiRequestStatus apiRequestStatus) {
		if (this.apiRequestStatus != apiRequestStatus) {
			this.apiRequestStatus = apiRequestStatus;
		}
		
		return this;
	}
	
	private String apiRequestMessage;
	public String getApiRequestMessage() {
		return this.apiRequestMessage;
	}
	public TransactionEntry setApiRequestMessage(String apiRequestMessage) {
		if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {
			this.apiRequestMessage = apiRequestMessage;
		}
		
		return this;
	}
	
	public TransactionEntry() {
		this.transactionId = new UUID(0, 0);
		this.totalAmount = 0;
		this.classification = TransactionClassification.NOT_DEFINED;
		this.createdOn = LocalDateTime.now();
		this.referenceId = new UUID(0, 0);
		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionApiRequestStatus.OK;\
		
		this.transactionId = ((UUID) rs.getObject(TransactionEntryFieldNames.TRANSACTION_ID));
		this.productId = ((UUID) rs.getObject(TransactionEntryFieldNames.PRODUCT_ID));
		this.quantity = rs.getInt(TransactionEntryFieldNames.QUANTITY);
		this.unitPrice = rs.getBigDecimal(TransactionEntryFieldNames.UNIT_PRICE);
	}
	
	public TransactionEntry(TransactionEntity transactionEntity) {
		this.cashierId = transactionEntity.getCashierId();
		this.totalAmount = transactionEntity.getTotalAmount();
		this.classification = transactionEntity.getClassification();
		this.createdOn = transactionEntity.getCreatedOn();
		this.referenceId = transactionEntity.getReferenceId();

		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionApiRequestStatus.OK;
	}
}
