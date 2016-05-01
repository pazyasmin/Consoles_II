//import java.util.ArrayList;
//import java.util.Scanner;
//import javax.swing.JDialog;
import javax.swing.JOptionPane;
public class Main 
{
	public static Console con;
	
	final static int s_maxConsoles = 4;
	public static Console[] consoles = new Console[s_maxConsoles];

	
	public static void main(String[] args) 
	{

	        for(int i=0; i<4; i++)
	        {
	        	
	        	String str = JOptionPane.showInputDialog(null,"Which of the following group of consoles would you like to use?\n" + 
	        	"1) Xbox360 S\n" + "2) PlayStation 3 Super Slim\n");
	
		        int op = Integer.parseInt( str.trim() );       
		        switch (op)
		        {
					case 1:
					{
						try{	
			  				Date date = new Date(10,06,2013);
							consoles[i]= new Xbox360(false, "Xbox 360 S", "Microsoft", date, true, Platforms.XBOX360, 4.0, 320.00, false);
		        		}
		                catch (ArrayIndexOutOfBoundsException e){
		                	System.out.println("Sorry, that index is out of bounds.");
		                    break;
		                }         
						break;
					}
					
					case 2:						
					{
						try
						{	
							Date date = new Date(4,10,2012);
							consoles[i] = new PlayStation3(false, "PlayStation 3 Super Slim", "Sony", date, true, Platforms.PLAYSTATION3, 1.0, 500.00, false);
						}
		                catch (ArrayIndexOutOfBoundsException e){
		                	System.out.println("Sorry, that index is out of bounds.");
		                    break;
		                }						
						break;
					}
					default: 
					{
						System.out.println("Exiting...");
						break;
					}
		        }
	     	
	        }
	        
	        consoles[0].power_ON();
	    	
	}
	
		public static void menu(Console[] consoles)
		{
			
			for(int i=0; i<s_maxConsoles; i++)
			{
																									
				if(consoles[i] instanceof Xbox360)
				{
					((Xbox360) consoles[i]).power_ON();
					((Xbox360) consoles[i]).menu();
				}
				else if(consoles[i] instanceof PlayStation3)
				{
					((PlayStation3) consoles[i]).power_ON();
					((PlayStation3) consoles[i]).menu();
	
				}
											
		}
	}
}
	/*private static void instancingConsoles() 
	{
	// TODO Auto-generated method stub
		Date date1 = new Date(10,06,2013);
		Date date2 = new Date(22,11,2005);
		Date date3 = new Date(11,11,2006);
		Date date4 = new Date(4,10,2012);
		
		
		this.xb360.add(new Xbox360(false, "Xbox 360 S", "Microsoft", date1, true, Platforms.XBOX360, 4.0, 320.00, false));   
		this.xb360.add(new Xbox360(false, "Xbox 360 Premium", "Microsoft", date2, true, Platforms.XBOX360, 1.0, 80.00, false));     
		this.ps3.add(new PlayStation3(false, "PlayStation 3 60GB", "Sony", date3, true, Platforms.PLAYSTATION3, 1.0, 60.00, false));
		this.ps3.add(new PlayStation3(false, "PlayStation 3 Super Slim", "Sony", date4, true, Platforms.PLAYSTATION3, 1.0, 500.00, false));

		
		}*/

