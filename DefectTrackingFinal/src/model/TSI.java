package model;




import java.sql.SQLException;
import java.util.ArrayList;
//import com.tcs.tsi.model.dao.CustDAO;
import model.DefectTrackingDAO;
/*import com.tcs.tsi.model.dao.LoginDAO;
import com.tcs.tsi.model.dao.OrderDAO;
import com.tcs.tsi.model.dto.CustInfo;*/
import model.Defect;
//import com.tcs.tsi.model.dto.Login;
//import com.tcs.tsi.model.dto.Order;
import model.ProductStock;
//import com.tcs.tsi.model.dto.ViewProducts;

public class TSI
{	
	/*********************************View products corresponding to a particular ORDER ID*********************************/	
	public ArrayList<ProductStock> lodgeComplaint(ProductStock psObj) throws SQLException
	{
		ArrayList<ProductStock> temp;
		DefectTrackingDAO defectTrackingObj=new DefectTrackingDAO();
		temp=defectTrackingObj.lodgeComplaint(psObj);
		return temp;
	}
	
	
	/*********************************Inserting values into DEFECT_PRODUCT_ORDER*********************************/
	
	public ArrayList<ProductStock> addDefect(ArrayList<Defect> tempList) throws SQLException
	{
		DefectTrackingDAO dtObj=new DefectTrackingDAO();
		ArrayList<ProductStock> psList=dtObj.addDefect(tempList);
		return psList;
	}
	
	
	/*********************************Displays the list of defected products*********************************/
	public ArrayList<Defect> viewIM() throws SQLException
	{
		DefectTrackingDAO defectTrackingObj=new DefectTrackingDAO();
		ArrayList <Defect> temp=defectTrackingObj.viewIM();
		return temp;
	}
	
	/*********************************Place new order for a defected product*********************************/
	public void placeNewOrder(ArrayList<ProductStock> ps) throws SQLException
	{
		DefectTrackingDAO defectTrackingObj=new DefectTrackingDAO();
		defectTrackingObj.placeNewOrder(ps);
	}
/****************************************view Defect*********************************************************/	
	public Defect viewDefect(String defectId) throws SQLException
	{
		DefectTrackingDAO dtObj=new DefectTrackingDAO();
		Defect defectObj=dtObj.viewDefectRetailer(defectId);
		return defectObj;
	}
	

	public ArrayList<Defect> viewDefectInvMan() throws SQLException{
		DefectTrackingDAO dtObj = new DefectTrackingDAO();
		ArrayList<Defect> defectList=dtObj.viewDefectInvMan();
		return defectList;
	}

}



