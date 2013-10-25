package edu.cgu.ist303.db;

import java.util.ArrayList;
import java.util.List;

public class ServiceCategoryDSTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ServiceCategoryDataSource scDS = new ServiceCategoryDataSource();
         List<ServiceCategory> scList = new ArrayList<ServiceCategory>(); 
         scList = scDS.getAllServiceCategory();
        
         scDS.close();
         for(ServiceCategory sc:scList)
         {
           System.out.println(sc.getId() + " " + sc.getCategory() + " " + sc.getUnit_cost() + " " + sc.getDuration() +
        		   " " + sc.getFk_service_id());
 		}
	}

}
