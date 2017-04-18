package edu.uark.models.api;

import java.util.UUID;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import edu.uark.models.api.enums.TransactionApiRequestStatus;
import edu.uark.models.entities.TransactionEntryEntity;

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
    public TransactionEntry setTransactionId(UUID transactionId) {
    	this.transactionId = transactionId;
    	return this;
    }
    
    private UUID productId;
    public UUID getProductId() {
    	return this.productId;
    }
    public TransactionEntry setProductId(UUID productId) {
    	this.productId = productId;
    	return this;
    }
    
    private int quantity;
    public int getQuantity() {
    	return this.quantity;
    }
    public TransactionEntry setQuantity(int quantity) {
    	this.quantity = quantity;
    	return this;
    }
    
    private double unitPrice;
    public double getUnitPrice() {
    	return this.unitPrice;
    }
    public TransactionEntry setUnitPrice(double unitPrice) {
    	this.unitPrice = unitPrice;
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
		this.id = new UUID(0, 0);
		this.transactionId = new UUID(0, 0);
		this.productId = new UUID(0,0);
		this.quantity = 0;
		this.unitPrice = 0.00;
		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionApiRequestStatus.OK;
	}
	
	public TransactionEntry(TransactionEntryEntity transactionEntryEntity) {
		this.id = transactionEntryEntity.getTransactionId();
		this.productId = transactionEntryEntity.getProductId();
		this.quantity = transactionEntryEntity.getQuantity();
		this.unitPrice = transactionEntryEntity.getUnitPrice();

		this.apiRequestMessage = StringUtils.EMPTY;
		this.apiRequestStatus = TransactionApiRequestStatus.OK;
	}
}
