package edu.cgu.ist303.db;

import java.util.ArrayList;
import java.util.List;

public class ServiceTypeCategoryDSTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ServiceTypeCategoryDataSource scDS = new ServiceTypeCategoryDataSource();
         List<ServiceTypeCategory> stcList = new ArrayList<ServiceTypeCategory>(); 
         stcList = scDS.getAllServiceTypeCategory();
        
         scDS.close();
         for(ServiceTypeCategory stc:stcList)
         {
           System.out.println(stc.getId() + " " + stc.getCategory() + " " + stc.getUnit_cost() + " " + stc.getDuration() +
        		   " " + stc.getFk_service_type_id());
 		}
	}

}
