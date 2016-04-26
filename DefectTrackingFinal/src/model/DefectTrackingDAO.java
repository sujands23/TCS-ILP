package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList;
import model.Defect;
import model.ProductStock;
import model.conn.DBConnection;

public class DefectTrackingDAO 
	{
		Connection conn=DBConnection.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;

/*********************************View products corresponding to a particular ORDER ID*********************************/	
		public ArrayList<ProductStock> lodgeComplaint(ProductStock psObj) throws SQLException
		{
			
			pstmt=conn.prepareStatement("select ORDER_ID from PRODUCT_STOCK_TBL where ORDER_ID = ?");
			pstmt.setString(1, psObj.getOrderId());
			int check=pstmt.executeUpdate();
			
			if(check!=0)
			{
				pstmt=conn.prepareStatement("select PRODUCT_CODE,PRODUCTMODEL_ID , PRODUCT_NAME  from PRODUCT_STOCK_TBL where ORDER_ID= ? ");
				pstmt.setString(1,psObj.getOrderId());
				ProductStock ps;
				ArrayList<ProductStock> psList=new ArrayList<ProductStock>();
				ResultSet rs=pstmt.executeQuery();		
				while(rs.next())
				{
					ps=new ProductStock();
					ps.setProductCode(rs.getString(1));
					ps.setProductModelId(rs.getString(2));
					ps.setProductName(rs.getString(3));
					ps.setOrderId(psObj.getOrderId());
					psList.add(ps);
				}
				return psList;
			}
			else 
			{
				System.out.println("Please enter a valid Order_ID");
							 pstmt.close();
							 conn.close();
							 return null;
				}
	}
		
		
	/*********************************Inserting values into DEFECT_PRODUCT_ORDER*********************************/
		public ArrayList<ProductStock> addDefect(ArrayList<Defect> tempList) throws SQLException
		{
			boolean flag=true;
			String str;		
			ProductStock ps;
			Random r=new Random();
			PreparedStatement pstmt1;
			ResultSet rs1;
			PreparedStatement pstmt2;
			ResultSet rs2;
			int n=0;
			int z=0;
			int l=0;
			
			String str5[];
			ArrayList<ProductStock> psList=new ArrayList<ProductStock>();
			for(int i=0;i<tempList.size();i++)
			{
					pstmt2=conn.prepareStatement("select count(DEFECT_PRODUCT_CODE) from DEFECT_PRODUCT_ORDER ");
					rs2=pstmt2.executeQuery();
					while(rs2.next())
					{
						n=rs2.getInt(1);
					}
					System.out.println(n);
					if(n!=0)
					{
						pstmt1=conn.prepareStatement("select DEFECT_PRODUCT_CODE from DEFECT_PRODUCT_ORDER");
						rs1=pstmt1.executeQuery();
						str5=new String[n];
						z=0;
						while(rs1.next())
						{
							str5[z]=rs1.getString(1);
							++z;	
						}
						System.out.println(z);
						for(int k=0;k<n;k++)
						{
							if(tempList.get(i).getDefectProCode().equalsIgnoreCase(str5[k]))
							{
								l=1;
								break;
							}
						}
						System.out.println(l);
						
					}
					if(l==0)
					{
						ps=new ProductStock();
						str="D"+(r.nextInt(1000));
						Random k=new Random();
						//String str1="OR"+(k.nextInt(10));
						
						pstmt=conn.prepareStatement("insert into DEFECT_PRODUCT_ORDER values(?,?,?,?,?,?)");
						pstmt.setString(1, tempList.get(i).getOrder_id());
						pstmt.setString(2, tempList.get(i).getDefectProCode());
						pstmt.setString(3, "null");
						pstmt.setString(4, str);
						pstmt.setString(5, tempList.get(i).getPm_id());
						//pstmt.setString(6, str1);
						pstmt.setString(6, "Defected");
						pstmt.executeUpdate();
						ps.setProductCode(tempList.get(i).getDefectProCode());
						ps.setDefectId(str);
						psList.add(ps);
					}
			}
			if(l==0)
			{
			return psList;
			}
			else return null;
		} 	 			 
			
				//end of addDefect() method
		
		
/*********************************Displays the list of defected products*********************************/
		public ArrayList<Defect> viewIM() throws SQLException
	  	{
			pstmt=conn.prepareStatement("select DEFECT_PRODUCT_CODE , ORDER_ID , DEFECT_ID , PRODUCTMODEL_ID , DEFECT_STATUS from DEFECT_PRODUCT_ORDER ");
				ArrayList<Defect> dList=new ArrayList<Defect>();
				Defect d;
				ResultSet rs=pstmt.executeQuery();
				while(rs.next())
					{
					d=new Defect();
					d.setDefectProCode(rs.getString(1));
					d.setOrder_id(rs.getString(2));
					d.setDefectID(rs.getString(3));
					d.setPm_id(rs.getString(4));
					d.setDefectStatus(rs.getString(5));
					dList.add(d);
					}
				pstmt.close();
				conn.close();
					return dList;
			}  	//end of viewIM() method
		
		
		/*********************************Place new order for a defected product*********************************/
		public void placeNewOrder(ArrayList<ProductStock> ps) throws SQLException
		{
			ArrayList<String> pm_id=new ArrayList<String>();
			ArrayList<ProductStock> p = new ArrayList<ProductStock>();
			ProductStock pstck;
			boolean flag=true;
			pstmt=conn.prepareStatement("select PRODUCT_CODE,PRODUCTMODEL_ID,PRODUCT_NAME,PRODUCT_STATUS,ORDER_ID,PRODUCTMODEL_PRICE from PRODUCT_STOCK_TBL");
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				pstck= new ProductStock();
				pstck.setProductCode(rs.getString(1));
				pstck.setProductStatus(rs.getString(4));
				pstck.setPrice(rs.getDouble(6));
				pm_id.add(rs.getString(4));
				pstck.setProductName(rs.getString(3));
				p.add(pstck);
			}
			
			int index=0;
				for(int i=0;i<ps.size();i++)
				{			
					for(int k=0;k<pm_id.size();k++)
						{
							if(pm_id.get(k).equalsIgnoreCase(ps.get(i).getProductModelId()) && p.get(k).getProductStatus().equalsIgnoreCase("Available"))
							{
								index=k;
								break;
							}
						}
					
					pstmt=conn.prepareStatement("insert into ORDER_TBL values (?,?,?,?)");
					Random r=new Random();
					String rnd="OR"+(r.nextInt(1000));
					pstmt.setString(1,rnd);
					pstmt.setString(2,"ret200" );
					pstmt.setDate(3, new java.sql.Date(System.currentTimeMillis()));

					//pstmt.setString(3, "01-Nov-2020");
					pstmt.setDouble(4,p.get(index).getPrice() );
					pstmt.executeQuery();

					/*			
					pstmt=conn.prepareStatement("update DEFECT_PRODUCT_ORDER set NEW_ORDER_ID = ? where DEFECT_PRODUCT_CODE=?");
					pstmt.setString(1, rnd);
					pstmt.setString(2, ps.get(i).getProductCode());		
					pstmt.executeUpdate();
					*/
			
					pstmt=conn.prepareStatement("update DEFECT_PRODUCT_ORDER set DEFECT_STATUS = 'Allocated' where DEFECT_PRODUCT_CODE=?");
					pstmt.setString(1, ps.get(i).getProductCode());		
					pstmt.executeUpdate();
					
					pstmt=conn.prepareStatement("update PRODUCT_STOCK_TBL set ORDER_ID=? , PRODUCT_STATUS='Allocated' where PRODUCT_CODE = ?");
					pstmt.setString(1, rnd);
					pstmt.setString(2, p.get(index).getProductCode());
					pstmt.executeUpdate();
			
					/*
					pstmt=conn.prepareStatement("update DEFECT_PRODUCT_ORDER set REPLACEMENT_PRODUCT_CODE = ? where NEW_ORDER_ID=?" );
					pstmt.setString(1,p.get(index).getProductCode());
					pstmt.setString(2, rnd);
					pstmt.executeUpdate();
					*/	
			
			
				}	
		}				  //end of placeNewOrder() method
			
/*****************************************************Track the status of the defected product*********************************************/

		public Defect viewDefect(String defectId) throws SQLException
		{
			PreparedStatement pstmt1;
			ResultSet rs1;
			PreparedStatement pstmt2;
			ResultSet rs2;
			String str[];
			int n=0;
			boolean flag=false;
			/*
			pstmt2=conn.prepareStatement("select count(DEFECT_ID) from DEFECT_PRODUCT_ORDER");
			rs2=pstmt2.executeQuery();
			while(rs2.next())
			{
				n=rs2.getInt(1);
			}
			*/
			str=new String[n];
			pstmt1=conn.prepareStatement("select DEFECT_ID from DEFECT_PRODUCT_ORDER");
			rs1=pstmt1.executeQuery();
			int z=0;
			while(rs1.next())
			{
				str[z]=rs1.getString(1);
			}
			for(int i=0;i<n;i++)
			{
				if(str[i]==defectId)
				{
					break;
				}
				else
					flag=false;
			}
			System.out.println("inside");
			pstmt=conn.prepareStatement("select DEFECT_PRODUCT_CODE from DEFECT_PRODUCT_ORDER where DEFECT_ID=?");
			pstmt.setString(1,defectId );
			rs=pstmt.executeQuery();
			Defect defectObj=new Defect();
			while(rs.next())
			{
				defectObj.setDefectProCode(rs.getString(1));
			}
				
			pstmt=conn.prepareStatement("select DEFECT_STATUS from DEFECT_PRODUCT_ORDER where DEFECT_ID=? ");
			pstmt.setString(1,defectId );
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				defectObj.setDefectStatus(rs.getString(1));
			}	
			if(flag==true)
				return defectObj;
			else
				return null;
		}
/********************************************************************************************************************************************/
		public Defect viewDefectRetailer(String defectId) throws SQLException
		{
			PreparedStatement pstmt1;
			ResultSet rs1;
			PreparedStatement pstmt2;
			ResultSet rs2;
			//boolean f=true;

			pstmt1=conn.prepareStatement("select DEFECT_ID from DEFECT_PRODUCT_ORDER where DEFECT_ID = ?");
			pstmt1.setString(1, defectId);
			int check=pstmt1.executeUpdate();
			
		
			if(check!=0)
			{
				
				pstmt=conn.prepareStatement("select DEFECT_PRODUCT_CODE from DEFECT_PRODUCT_ORDER where DEFECT_ID=?");
				pstmt.setString(1,defectId );
				rs=pstmt.executeQuery();
				Defect defectObj=new Defect();
				while(rs.next())
				{
					defectObj.setDefectProCode(rs.getString(1));
				}
			
				pstmt=conn.prepareStatement("select DEFECT_STATUS from DEFECT_PRODUCT_ORDER where DEFECT_ID=? ");
				pstmt.setString(1,defectId );
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					defectObj.setDefectStatus(rs.getString(1));
				}	
		
				return defectObj;
			}
			else 
			{	
				//f=false;
				return null;
			}
			
		
	}
	
/********************************************************View Defect Log Inventory Manager*******************************************************/
		
		public ArrayList<Defect> viewDefectInvMan() throws SQLException
		{
			pstmt=conn.prepareStatement("select ORDER_ID,DEFECT_PRODUCT_CODE,REPLACEMENT_PRODUCT_CODE,DEFECT_ID , PRODUCTMODEL_ID  , DEFECT_STATUS  from DEFECT_PRODUCT_ORDER  ");
			Defect defectObj;
			ArrayList< Defect> defectList=new ArrayList<Defect>();
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				defectObj=new Defect();
				defectObj.setOrder_id(rs.getString(1));
				defectObj.setDefectProCode(rs.getString(2));
				defectObj.setReplaceProCode(rs.getString(3));
				defectObj.setDefectID(rs.getString(4));
				defectObj.setPm_id(rs.getString(5));
				//defectObj.setNewOrderId(rs.getString(6));
				defectObj.setDefectStatus(rs.getString(6));
				defectList.add(defectObj);
			}
		

		return defectList;
		
		}




	}		     //end of class DefectTrackingDAO
		



