package model;



import java.util.ArrayList;

public class ProductStock 
{
	/*ProductStock class contains getter and setter functions for variables Product_code,
	 Productmodel_id, Product_name,Product_status,Order_id and Product_price */


		private String productCode,defectId , productModelId , productName , productStatus , orderId,custId;
		public String getCustId() {
			return custId;
		}
		
		public void setCustId(String custId) {
			this.custId = custId;
		}

		public String getDefectId() {
			return defectId;
		}

		public void setDefectId(String defectId) {
			this.defectId = defectId;
		}

		private double price;
		private int qty;
		private int count;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getQty() {
			return qty;
		}

		public void setQty(int qty) {
			this.qty = qty;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public String getProductCode() {
			return productCode;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		public String getProductModelId() {
			return productModelId;
		}

		public void setProductModelId(String productModelId) {
			this.productModelId = productModelId;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getProductStatus() {
			return productStatus;
		}

		public void setProductStatus(String productStatus) {
			this.productStatus = productStatus;
		}

		public String getOrderId() {
			return orderId;
		}

		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		private int quantity;
		
		
	}			//end of ProductStock class



