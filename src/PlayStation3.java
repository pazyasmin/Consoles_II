import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayStation3 extends Console implements IDevice, IPlayStation3
{
	private boolean _PSMove; 
	private int [] _PSMoveCoordinates;

public PlayStation3()
{
	super();
	this._platform = Platforms.PLAYSTATION3;
	this._PSMove = false;
	this._PSMoveCoordinates = new int [3];
	this.setPSMoveCoordinates(0, 0, 0);
}
	
public PlayStation3 (boolean power, String model, String manufacturer, Date releaseDate, boolean ethernetCard, 
		Platforms platform, double softwareVersion, double internalStorage, 
		boolean psMove)
{
	super(power, model, manufacturer, releaseDate, ethernetCard, 
    		platform, softwareVersion, internalStorage);
    this._PSMove = psMove;
    this._PSMoveCoordinates = new int [3];
    this.setPSMoveCoordinates(0, 0, 0);
}
	
public PlayStation3(PlayStation3 p)
{
    super(p);
    this._PSMove = p._PSMove;
    this._PSMoveCoordinates = new int [3];
    this._PSMoveCoordinates[0] = p._PSMoveCoordinates[0];  //X
	this._PSMoveCoordinates[1] = p._PSMoveCoordinates[1];; //Y
	this._PSMoveCoordinates[2] = p._PSMoveCoordinates[2];; //Z
}

//Implementação dos métodos da interface do PlayStation 3.
@Override
public void motionSensing_ON()
{
  
	  System.out.println("Scanning PlayStation 3 components for motion sensing device...");
	  if (!this._PSMove)
	  {
	  	System.out.println("No PlayStation Move devices have been found.\nWould you like to connect your PlayStation 3 to a PlayStation Move device now?<Y/N>");
		
	  	Scanner input = new Scanner(System.in);
	  	String str = input.nextLine();
	  	String yes = "Yes";
	  	String no = "No"; 
		  if(str.equalsIgnoreCase(yes) || str.equalsIgnoreCase("Y")) 
		  {
		      System.out.println("Connecting PlayStation Move device...");
		      this.setPSMove(true); 
		      this.motionSensing_ON();
		  } 
		  else
			  if(str.equalsIgnoreCase(no) || str.equalsIgnoreCase("N")) 
		  {
			  System.out.println("Maybe next time.");
			  input.close();	
			  return;
		  }else;
		  input.close();		 
	  }
	  else
	  {
		  System.out.println("Your PlayStation 3 is connected to a PlayStation Move device. \nInitializing PlayStation Move....");
		  System.out.println("Fetching position...\nMapping coordinates...");
			int[] numbers = new int[3];       
			   
		    for(int i = 0; i < 3; i++) 
		    {
		      numbers[i] = (int)(Math.random()*100 + 1);
		      System.out.println(numbers[i]);
		    }
		    
		  this.setPSMoveCoordinates(numbers);	  
		 
		  System.out.println("X: "+ this.getPSMoveX() + ", Y: "+ this.getPSMoveY() +", Z: " + this.getPSMoveZ());
	  }
}

@Override
public void motionSensing_OFF()
{
	 if (this._PSMove)
	 {
		  System.out.println("Would you like to disconnect your PlayStation Move device?<Y/N>");
	  
		  Scanner input = new Scanner(System.in);
		  if(input.next().equalsIgnoreCase("y")||input.next().equalsIgnoreCase("yes")) 
		  {
		      System.out.println("Disconnecting PlayStation Move...");
		      this.setPSMove(false);
		  } 
		  else
				  System.out.println("Maybe next time.");				  
		  input.close();
	 }
	 else
		 System.out.println("No PlayStation Move devices are connected.");
		 
}
		
		
@Override
public final void setPSMove(boolean psMove)
{
	if (psMove)
	{
		if (!this._PSMove)
        {
        	this._PSMove = true;
        	System.out.println("Your PS Move has been connected.");
        }
		else 
       	System.out.println("Your PS Move is already connected.");
	}
	else
	{
		if (this._PSMove)
	    {
			this._PSMove = false;
        	System.out.println("Your PS Move has been disconnected.");
	    }
		else
			System.out.println("Your PS Move is already disconnected.");
		 }
}
  
 public final void setPSMoveCoordinates(int[] xyz)
 {
	 this._PSMoveCoordinates = xyz;
 }

 @Override
 public int getPSMoveX()
 {
	 return this._PSMoveCoordinates[0];
 }
 
 @Override
 public int getPSMoveY()
 {
	 return this._PSMoveCoordinates[1];
 }
 
 @Override
 public int getPSMoveZ()
 {
	 return this._PSMoveCoordinates[2];
 }	 
 @Override
 public boolean getPSMove()
 {
	 return this._PSMove;
 }  


//Implementação dos métodos da classe abstrata Console.
 @Override
 public void insertController()
 {
	  System.out.println("Would you like to insert a controller?<Y/N>");
	  Scanner input = new Scanner(System.in);
	  
	  if(input.next().equalsIgnoreCase("y")||input.next().equalsIgnoreCase("yes")) 
	  {
		  
	   	 if ( this._numControllers == s_maxControllers )
	            System.out.println("\nAll controller ports are currently in use.");
		 else
		 {
            Controller newController = new Controller();
            String [] buttons;
            buttons = new String []{"PS Guide", "Start", "Select", "A", "B", "X", "Y",
                "L1", "R1", "L2", "R2", 
                "D Pad", "Left Thumbstick", "Right Thumbstick"};
  		  	newController.setButtons(buttons);
            
            System.out.print("Is it wireless? <Y/N>");
            
            Scanner input2 = new Scanner(System.in);
            if(input.next().equalsIgnoreCase("y")||input.next().equalsIgnoreCase("yes")) 
  		  	{	            
            	newController.setWireless(true);
  		  	}
            else
            	newController.setWireless(false);
            
  		  	input2.close();
            
  		  	this._controllers.add(newController);
            this._numControllers++;
            System.out.println("Controller "+ this._controllers.size() + "found!");
		 }
	  }
	  else 
		  System.out.println("Operation cancelled.");		  
	  input.close();
 }
 	
		 
 @Override
 public void menu()
 {
	int op1 = 0; 
    do
    {
    	
    	System.out.println("\n-PlayStation 3-");
        System.out.println("\nChoose one of the following options:\n");
        System.out.println("0 - System info.");
        System.out.println("1 - Play game.");
        System.out.println("2 - Insert controller.");
        System.out.println("3 - Create user.");
        System.out.println("4 - Install game.");
        System.out.println("5 - Play game from hard drive.");
        System.out.println("6 - Update System.");
        System.out.println("7 - Connect PS Move");
        System.out.println("8 - Disconnect PS Move");
        System.out.println("9 - Uninstall game.");
        System.out.println("10 - Remove controller.");
        System.out.println("11 - Delete user.");
        System.out.println("12 - Display games.");
        System.out.println("13 - Display users.");
        System.out.println("14 - Turn off PlayStation 3.");
        System.out.println("15 - Exit.");        
       
        int usrInput=0;
    	boolean done = false;
    	while(!done)
        {	 
    		  Scanner sc = new Scanner(System.in);
    		
    		  try
    		  {
    		    System.out.println("Please, enter one of the above options.\n");
    		    usrInput=sc.nextInt();
    		  }
    		  catch(InputMismatchException exception)
    		  {
    		    System.out.println("This is not an integer.");
    		  }
    		  
    		  if (usrInput < 0 || usrInput > 16)
    		  {
    			  System.out.println("This is not a valid option.");
    		  }
    		  else
    			  done = true;
    		  
    		  sc.close();
        }	  
        op1 = usrInput;
        switch(op1)
        {
            case 0:
            {
            	System.out.println("-System Info-");
            	System.out.println(this);
                break;
            }
            case 1:
            {
            	Game game = this.menuAvailableGames();
                this.play(game);
                break;
            }
            case 2:
            {
                insertController();
                break;
            }
            case 3:
            {
                this.createUser();;
                break;
            }
            case 4:
            {
            	Game game = this.menuAvailableGames();
                this.installGame(game);
                break;
            }
            case 5:
            {
                this.play();
                break;
            }
            case 6:
            {
            	this.update();
                break;
            }
            case 7:
            {
                this.motionSensing_ON();
                break;
            }
            case 8:
            {
                this.motionSensing_OFF();
                break;
            }
            case 9:
            {
            	Game game = this.menuAvailableGames();
                this.uninstallGame(game);
                break;
            }
            case 10:
            {
            	this.removeController();
                break;
            }
            case 11:
            {
            	this.deleteUser();;
                break;
            }
            case 12:
            {
                this.deleteUser();
                break;
            }
            case 13:
            {
                this.displayUsers();
                break;
            }
            case 14:
            {
            	this.power_OFF();
                break;
            }
            
            default:
            {
                if (op1 != 15)
                {
                	throw new IllegalArgumentException("\nInvalid option.\n");
                }
            }
        }
    }while(op1 != 15);
    

 }
 
//Implementação dos métodos da interface IDevice
@Override
public void power_ON()
{
	if (!this._power)
	{
		System.out.println("\t-PLAYSTATION 3-");    
	 	System.out.println("Loading PlayStation 3. Please wait..."); 
	 	System.out.println("Scanning components...");    
	 	System.out.println(this);
	   // motionSensing_ON();
	    menu();
	}
	else
	{
		System.out.println("Your PlayStation 3 is already turned on.\nRestarting..."); 
      this._power = false;
      power_ON();
      System.out.println("Your PlayStation 3 has been restarted!\n");
  }
}


@Override
public void power_OFF()
{
	if (_power)
  {
		System.out.println("Shutting down PlayStation 3. Please wait...");
		this._power = false;
		System.out.println("Your PlayStation 3 has been turned off."); 
  }
  else
  	System.out.println("Your PlayStation 3 is already turned off."); 
}

 

@Override
public String toString()
{
        StringBuilder info = new StringBuilder();
        
        info.append( super.toString() );
        info.append("PS Move Status: ");
        if (this._PSMove){
            info.append("ON\n");
            info.append("XYZ Coordinates: (" + this._PSMoveCoordinates[0] + ", " 
                    + this._PSMoveCoordinates[1] + ", " + this._PSMoveCoordinates[3] + ")\n");
        }
        else info.append("OFF\n");
        
        return info.toString();
}

@Override
public void setPSMoveCoordinates(int x, int y, int z) 
{
 
	this._PSMoveCoordinates[0] = x;
	this._PSMoveCoordinates[1] = y;
	this._PSMoveCoordinates[2] = z;
	
 
}

}
