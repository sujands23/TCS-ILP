package controller;
import model.TSI;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.tcs.tsi.model.TSI;
import model.Defect;
import model.ProductStock;

		public class DefectTrackingController extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    
	    public DefectTrackingController() 
	    {
	        super();
	        
	    }

		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
		HttpSession session=request.getSession(); 
		String action=request.getParameter("action");
/*******************************************************************(Retailer)Lodging a Complaint**********************************************************************/
			//lodgeComplaint page is opened to enter order id
		if(action.equals("lodgeComplaint"))//value is got from d1.jsp(LodgeComplaint.jsp)
		{
			String order_id=request.getParameter("order_id");
			ProductStock psObj=new ProductStock();
			psObj.setOrderId(order_id);
			TSI tsi=new TSI();
			try 
			{
				ArrayList<ProductStock> psList=tsi.lodgeComplaint(psObj);
				if(psList==null)
				{
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/d3.jsp");//enter valid order id
					rd.forward(request, response);	
				}
				else
				{
					session.setAttribute("temp", psList);
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/d2.jsp");//display the ordered products and select the defected products
					rd.forward(request, response);
				} 
			}
			catch (SQLException e) 
			{
				System.out.println(e.getMessage());
			}
		}
		
/******************************************************************(Retailer)Viewing Lodged Complaints*****************************************************/
		
		
		else if(action.equals("ComplaintLodged"))//value is got from d2.jsp(Complaint.jsp)
		{
			String[] checkbox=(request.getParameterValues("product"));
			int n[]=new int[checkbox.length];
			ArrayList<ProductStock> arr=(ArrayList<ProductStock>)session.getAttribute("temp");
			ArrayList<ProductStock> temp2=new ArrayList<ProductStock>();
			for(int i=0;i<checkbox.length;i++)
				{
					n[i]=Integer.parseInt(checkbox[i]);
				}		
			for(int i=0;i<checkbox.length;i++)
				{
					temp2.add(arr.get(n[i]));
					System.out.println(checkbox[i]+"\t"+arr.get(i).getProductCode());	
				}
			Defect d;
			TSI tsi=new TSI();
			ArrayList<Defect> dList=new ArrayList<Defect>();
			for(int i=0;i<temp2.size();i++)
				{
					d=new Defect();
					d.setDefectProCode(temp2.get(i).getProductCode());
					d.setPm_id(temp2.get(i).getProductModelId());
					d.setOrder_id(temp2.get(i).getOrderId());
					System.out.println(temp2.get(i).getProductName());
					dList.add(d);
				}
			try 
			{
							
				ArrayList<ProductStock> temp1=tsi.addDefect(dList);
				if(temp1==null)
					{
						RequestDispatcher rd=getServletContext().getRequestDispatcher("/d5.jsp");//The complaint has already been lodged
						rd.forward(request, response);
					}
				else
					{
						session.setAttribute("temp",temp1 );
						RequestDispatcher rd=getServletContext().getRequestDispatcher("/d4.jsp");//Your complaint has been lodged successfully and defect id is generated
						rd.forward(request, response);
					}
			} 
			catch (SQLException e) 
				{
					e.printStackTrace();
				}	
						
			}
		
		/********************************************************(Retailer)Tracking Defective Products*****************************************************/
		
		else if(action.equals("defectTracking"))//value is got from d10.jsp(DefectTrackingRetailer.jsp)
		{
			String defectId=request.getParameter("defectId");
			TSI tsi=new TSI();
			try 
			{
				Defect defectStatus=tsi.viewDefect(defectId);
				
				if(defectStatus==null)
				{
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/d12.jsp");//Please enter a valid defect ID
					rd.forward(request, response);	
				}
				else
				{
				session.setAttribute("defectStatus", defectStatus);
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/d11.jsp");//The status for the product(Product code) is( Defected/Allocated )
				rd.forward(request, response);
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		
/**********************************************************(Inventory Manager)Viewing Defective Products by IM***********************************************/
		else if (action.equals("im"))//value is got from d8.jsp(LodgeComplaint.jsp)
		{
			TSI tsi=new TSI();
			try 
			{
				ArrayList<Defect> dList=tsi.viewIM();
				session.setAttribute("s",dList );
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/d9.jsp");//Table to allocate new products(IMManView.jsp)
				rd.forward(request, response);
			} 
			catch (SQLException e) 
				{
					e.printStackTrace();
				}
		}
/*************************************************(Inventory Manager)Placing Order for defect products**************************************/
		
		else if(action.equals("imNewOrder"))//value got from d9.jsp(InvManView.jsp)
		{
			String[] checkbox=(request.getParameterValues("im"));
			int n[]=new int[checkbox.length];
			ArrayList<Defect> arr=(ArrayList<Defect>)session.getAttribute("s");
			ArrayList<Defect> temp2=new ArrayList<Defect>();
			for(int i=0;i<checkbox.length;i++)
			{
				n[i]=Integer.parseInt(checkbox[i]);
			}		
			for(int i=0;i<checkbox.length;i++)
			{
				temp2.add(arr.get(n[i]));
				System.out.println(checkbox[i]+"\tnew order for \t"+arr.get(n[i]).getDefectProCode());	
			}
			ProductStock p;
			TSI tsi=new TSI();
			ArrayList<ProductStock> pList=new ArrayList<ProductStock>();
			for(int i=0;i<temp2.size();i++)
				{
					p=new ProductStock();			
					p.setProductCode(temp2.get(i).getDefectProCode());
					p.setProductModelId(temp2.get(i).getPm_id());
					pList.add(p);
				}
			try 
			{
				tsi.placeNewOrder(pList);
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/success1.jsp");//your order has been successfully placed
				rd.forward(request, response);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}

/*******************************************************(Inventory Manager)Button to view Defect Log******************************************************************/		
		else if(action.equals("defectTrackingInvMan"))//value is got from DefectTrackingInvMan.jsp
		{
			TSI tsi=new TSI();
			try 
			{
				ArrayList<Defect>defectList= tsi.viewDefectInvMan();
				session.setAttribute("defectDisplay", defectList);
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/d7.jsp");//Displays Defect Log (DefectList.jsp)
				rd.forward(request, response);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		
		}
/*************************************************************************************************************************************************************/
/***************************************************************Placing an order for new Product by IM(not used)******************************************/
		
		else if(action.equals("newOrder"))
		{
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/OrderPlaced.jsp");
			rd.forward(request, response);	
		}
	}	                           
}
	                               


