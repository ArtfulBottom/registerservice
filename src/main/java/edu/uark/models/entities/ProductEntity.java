package edu.uark.models.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.dataaccess.entities.BaseEntity;
import edu.uark.models.api.Product;
import edu.uark.models.entities.fieldnames.ProductFieldNames;
import edu.uark.models.repositories.ProductRepository;

public class ProductEntity extends BaseEntity<ProductEntity> {
	@Override
	protected void fillFromRecord(ResultSet rs) throws SQLException {
		this.lookupCode = rs.getString(ProductFieldNames.LOOKUP_CODE);
		this.count = rs.getInt(ProductFieldNames.COUNT);
		this.createdOn = rs.getTimestamp(ProductFieldNames.CREATED_ON).toLocalDateTime();
		this.price = rs.getDouble(ProductFieldNames.PRICE);
		this.active = rs.getBoolean(ProductFieldNames.ACTIVE);
	}

	@Override
	protected Map<String, Object> fillRecord(Map<String, Object> record) {
		record.put(ProductFieldNames.LOOKUP_CODE, this.lookupCode);
		record.put(ProductFieldNames.COUNT, this.count);
		record.put(ProductFieldNames.CREATED_ON, Timestamp.valueOf(this.createdOn));
		record.put(ProductFieldNames.PRICE, this.price);
		record.put(ProductFieldNames.ACTIVE, this.active);
		
		return record;
	}

	private String lookupCode;
	public String getLookupCode() {
		return this.lookupCode;
	}
	public ProductEntity setLookupCode(String lookupCode) {
		if (!StringUtils.equals(this.lookupCode, lookupCode)) {
			this.lookupCode = lookupCode;
			this.propertyChanged(ProductFieldNames.LOOKUP_CODE);
		}
		
		return this;
	}

	private int count;
	public int getCount() {
		return this.count;
	}
	public ProductEntity setCount(int count) {
		if (this.count != count) {
			this.count = count;
			this.propertyChanged(ProductFieldNames.COUNT);
		}
		
		return this;
	}
	
	private double price;
	public double getPrice() {
		return this.price;
	}
	public ProductEntity setPrice(double price) {
		if (this.price != price) {
			this.price = price;
			this.propertyChanged(ProductFieldNames.PRICE);
		}

		return this;
	}

	private boolean active;
	public boolean getActive() {
		return this.active;
	}
	public ProductEntity setActive(boolean active) {
		if (this.active != active) {
			this.active = active;
			this.propertyChanged(ProductFieldNames.ACTIVE);
		}

		return this;
	}

	private LocalDateTime createdOn;
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	
	public Product synchronize(Product apiProduct) {
		this.setCount(apiProduct.getCount());
		this.setLookupCode(apiProduct.getLookupCode());
		this.setActive(apiProduct.getActive());
		this.setPrice(apiProduct.getPrice());
		apiProduct.setCreatedOn(this.createdOn);
		
		return apiProduct;
	}

	public ProductEntity() {
		super(new ProductRepository());
		this.active = false;
		this.price = 0.0;
		this.count = -1;
		this.lookupCode = StringUtils.EMPTY;
		this.createdOn = LocalDateTime.now();
	}
	
	public ProductEntity(UUID id) {
		super(id, new ProductRepository());
		this.active = false;
		this.price = 0.0;
		this.count = -1;
		this.lookupCode = StringUtils.EMPTY;
		this.createdOn = LocalDateTime.now();
	}

	public ProductEntity(Product apiProduct) {
		super(apiProduct.getId(), new ProductRepository());
		this.active = apiProduct.getActive();
		this.price = apiProduct.getPrice();
		this.count = apiProduct.getCount();
		this.lookupCode = apiProduct.getLookupCode();
		this.createdOn = LocalDateTime.now();
	}
}
