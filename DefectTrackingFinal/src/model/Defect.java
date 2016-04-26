package model;


/**
This class contains getters and setters for variables order_id ,cust_id, name , address , phone,  email
**/

public class Defect 
{
	
	
	private String defectID,defectProCode , ReplaceProCode , Order_id,pm_id,defectStatus,newOrderId;

public String getNewOrderId() {
		return newOrderId;
	}

	public void setNewOrderId(String newOrderId) {
		this.newOrderId = newOrderId;
	}

public String getDefectStatus() {
		return defectStatus;
	}

	public void setDefectStatus(String defectStatus) {
		this.defectStatus = defectStatus;
	}

public String getPm_id() 
	{
	return pm_id;
	}

public void setPm_id(String pmId) 
	{
	pm_id = pmId;
	}

public String getDefectID()
	{
	return defectID;
	}

public void setDefectID(String defectID)
	{
	this.defectID = defectID;
	}

public String getDefectProCode()
	{
	return defectProCode;
	}

public void setDefectProCode(String defectProCode) 
	{
	this.defectProCode = defectProCode;
	}

public String getReplaceProCode() 
	{
	return ReplaceProCode;
	}

public void setReplaceProCode(String replaceProCode)
	{
	ReplaceProCode = replaceProCode;
	}

public String getOrder_id()
	{
	return Order_id;
	}

public void setOrder_id(String orderId)
	{
	Order_id = orderId;
	}

}          // end of Defect class
 