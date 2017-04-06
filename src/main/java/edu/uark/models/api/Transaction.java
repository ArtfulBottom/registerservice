package edu.uark.models.api;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.models.api.enums.TransactionApiRequestStatus;
import edu.uark.models.entities.TransactionEntity;
import edu.uark.models.enums.TransactionClassification;

public class Transaction {
	private UUID id;
	public UUID getId() {
		return this.id;
	}
	public Transaction setId(UUID id) {
		this.id = id;
		return this;
	}
	
	private UUID cashierId;
    public UUID getCashierId() {
    	return this.cashierId;
    }
    public Transaction setCashierId(UUID cashierId) {
    	this.cashierId = cashierId;
    	return this;
    }
    
    private int totalAmount;
    public int getTotalAmount() {
    	return this.totalAmount;
    }
    public Transaction setTotalAmount(int totalAmount) {
    	this.totalAmount = totalAmount;
    	return this;
    }
    
    private TransactionClassification classification;
    public TransactionClassification getClassification() {
    	return this.classification;
    }
    public Transaction setClassification(TransactionClassification classification) {
    	this.classification = classification;
    	return this;
    }
    
    private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	public Transaction setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
		return this;
	}
	
	private UUID referenceId;
	public UUID getReferenceId() {
		return this.referenceId;
	}
	public Transaction setReferenceId(UUID referenceId) {
		this.referenceId = referenceId;
		return this;
	}
	
	private TransactionApiRequestStatus apiRequestStatus;
	public TransactionApiRequestStatus getApiRequestStatus() {
		return this.apiRequestStatus;
	}
	public Transaction setApiRequestStatus(TransactionApiRequestStatus apiRequestStatus) {
		if (this.apiRequestStatus != apiRequestStatus) {
			this.apiRequestStatus = apiRequestStatus;
		}
		
		return this;
	}
	
	private String apiRequestMessage;
	public String getApiRequestMessage() {
		return this.apiRequestMessage;
	}
	public Transaction setApiRequestMessage(String apiRequestMessage) {
		if (!StringUtils.equalsIgnoreCase(this.apiRequestMessage, apiRequestMessage)) {
			this.apiRequestMessage = apiRequestMessage;
		}
		
		return this;
	}
	
	public Transaction() {
		this.cashierId = new UUID(0, 0);
		this.totalAmount = 0;
		this.classification = TransactionClassification.NOT_DEFINED;
		this.createdOn = LocalDateTime.now();
		this.referenceId = new UUID(0, 0);
		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionApiRequestStatus.OK;
	}
	
	public Transaction(TransactionEntity transactionEntity) {
		this.cashierId = transactionEntity.getCashierId();
		this.totalAmount = transactionEntity.getTotalAmount();
		this.classification = transactionEntity.getClassification();
		this.createdOn = transactionEntity.getCreatedOn();
		this.referenceId = transactionEntity.getReferenceId();

		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionApiRequestStatus.OK;
	}
}
