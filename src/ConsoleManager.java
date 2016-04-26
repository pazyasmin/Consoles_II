import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleManager 
{
	static int n = 2;
	
	public static void main(String[] args) 
	{	 
		int numPSList = 0;
		ArrayList<Xbox360> xboxList = new ArrayList<Xbox360>(); 
		ArrayList<PlayStation3> psList = new ArrayList<PlayStation3>(); 
			
			for (int i=0; i < n;i++)
			{
				xboxList.add(new Xbox360());
			}
			
			for (int i=0; i < n;i++)
			{
				psList.add(new PlayStation3());
			}
		
        Scanner in = new Scanner(System.in);
      
        System.out.println("-Console Manager-");
        System.out.println("Please enter your choice: ");
        System.out.println("1. Access Xbox array."); 
        System.out.println("2. Access PlayStation array."); 
        System.out.println("3. Exit Console Manager."); 
        System.out.println("Invalid choice.");
        int choice=in.nextInt();
        in.close();
	        switch (choice) 
	        {
	            case 1:   	
	            {      	
	            	System.out.println("Enter object index: ");
	            	try
	            	{	
		                Scanner i = new Scanner(System.in);
		                int index=i.nextInt();
			                if (xboxList.isEmpty())
			                	 System.out.println("Empty xbox array.");
			                else 
			                {
			                	xboxList.get(index).startScreen();
			                }
		                i.close();
		        	}
		        	catch(ArrayIndexOutOfBoundsException e)
		        	{
		        		System.out.println ("Index out  of bounds");
		        	}
	            	break;
	        	}
	            case 2: 
	            {
	            	System.out.println("Enter object index: ");
	            	try
	            	{	
		            	Scanner i = new Scanner(System.in);
		                int index=i.nextInt();
		                
		                if (index < numPSList)
			            {
			                if (psList.isEmpty())
			                	 System.out.println("Empty PS array.");
			                else
			                {
			                	psList.get(index).startScreen();
			                }
			            }
		                else
		                	System.out.println("Invalid index.");
		                i.close();
	            	}
	            	catch(ArrayIndexOutOfBoundsException e)
	            	{
	            		System.out.println ("Index out  of bounds");
	            	}
	            	break;
	        	}
	            case 3: 
	            	break;
	            default: 
	            	System.out.println("Invalid choice.");
	            	
	        }
    }
}
 

