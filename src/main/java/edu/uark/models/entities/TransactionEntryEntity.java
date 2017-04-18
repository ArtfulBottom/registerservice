package edu.uark.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import edu.uark.dataaccess.entities.BaseEntity;
import edu.uark.models.api.TransactionEntry;
import edu.uark.models.entities.fieldnames.TransactionEntryFieldNames;
import edu.uark.models.repositories.TransactionEntryRepository;

public class TransactionEntryEntity extends BaseEntity<TransactionEntryEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.transactionId = ((UUID) rs.getObject(TransactionEntryFieldNames.TRANSACTION_ID));
		this.productId = ((UUID) rs.getObject(TransactionEntryFieldNames.PRODUCT_ID));
		this.quantity = rs.getInt(TransactionEntryFieldNames.QUANTITY);
		this.unitPrice = rs.getFloat(TransactionEntryFieldNames.UNIT_PRICE);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(TransactionEntryFieldNames.TRANSACTION_ID, this.transactionId);
		record.put(TransactionEntryFieldNames.PRODUCT_ID, this.productId);
		record.put(TransactionEntryFieldNames.QUANTITY, this.quantity);
		record.put(TransactionEntryFieldNames.UNIT_PRICE, this.unitPrice);
		
		return record;
	}
    
    private UUID transactionId;
    public UUID getTransactionId() {
    	return this.transactionId;
    }
    public TransactionEntryEntity setTransactionId(UUID transactionId) {
    	if (!this.transactionId.equals(transactionId)) {
    		this.transactionId = transactionId;
    		this.propertyChanged(TransactionEntryFieldNames.TRANSACTION_ID);
    	}
    	
    	return this;
    }
    
    private UUID productId;
    public UUID getProductId() {
    	return this.productId;
    }
    public TransactionEntryEntity setProductId(UUID productId) {
    	if (!this.productId.equals(transactionId)) {
    		this.productId = transactionId;
    		this.propertyChanged(TransactionEntryFieldNames.PRODUCT_ID);
    	}
    	
    	return this;
    }
    
    private int quantity;
    public int getQuantity() {
    	return this.quantity;
    }
    public TransactionEntryEntity setQuantity(int quantity) {
    	if (this.quantity != quantity) {
    		this.quantity = quantity;
    		this.propertyChanged(TransactionEntryFieldNames.QUANTITY);
    	}
    	
    	return this;
    }
    
    private double unitPrice;
    public double getUnitPrice() {
    	return this.unitPrice;
    }
    public TransactionEntryEntity setUnitPrice(double unitPrice) {
    	if (this.unitPrice != unitPrice) {
    		this.unitPrice = unitPrice;
    		this.propertyChanged(TransactionEntryFieldNames.UNIT_PRICE);
    	}
    	
    	return this;
    }
	
	public TransactionEntry synchronize(TransactionEntry apiTransactionEntry) {
		this.setTransactionId(apiTransactionEntry.getTransactionId());
		this.setProductId(apiTransactionEntry.getProductId());
		this.setQuantity(apiTransactionEntry.getQuantity());
		this.setUnitPrice(apiTransactionEntry.getUnitPrice());
		
		return apiTransactionEntry;
	}
	
	public TransactionEntryEntity() {
		super(new TransactionEntryRepository());
		
		this.transactionId = new UUID(0, 0);
		this.productId = new UUID(0, 0);
		this.quantity = 0;
		this.unitPrice = 0.00;
	}
	
	public TransactionEntryEntity(UUID id) {
		super(id, new TransactionEntryRepository());
		
		this.transactionId = new UUID(0, 0);
		this.productId = new UUID(0, 0);
		this.quantity = 0;
		this.unitPrice = 0.00;
	}

	public TransactionEntryEntity(TransactionEntry apiTransactionEntry) {
		super(apiTransactionEntry.getId(), new TransactionEntryRepository());
		
		this.setTransactionId(apiTransactionEntry.getTransactionId());
		this.setProductId(apiTransactionEntry.getProductId());
		this.setQuantity(apiTransactionEntry.getQuantity());
		this.setUnitPrice(apiTransactionEntry.getUnitPrice());
	}
}
