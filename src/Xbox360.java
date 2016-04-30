import java.util.Scanner;
import java.util.Random;

public class Xbox360 extends Console implements IDevice, IXbox360
{
	private boolean _kinect;
	private int [] 	_kinectCoordinates;

public Xbox360()
{
	super();
	this._platform = Platforms.XBOX360;
	this._kinect = false;
	this._kinectCoordinates = new int [3];
	this.setKinectCoordinates(0, 0, 0);
}

public Xbox360 (boolean power, String model, String manufacturer, Date releaseDate, boolean ethernetCard, 
		Platforms platform, double softwareVersion, double internalStorage, 
		boolean kinect)
{
    super(power, model, manufacturer, releaseDate, ethernetCard, 
    	  platform, softwareVersion, internalStorage);
    this._kinect = false;
    this._kinectCoordinates = new int [3];
    this.setKinectCoordinates(0, 0, 0);
}

public Xbox360(Xbox360 x)
{
    super(x);
    this._kinect = x._kinect;
    this._kinectCoordinates = new int [3];
    this._kinectCoordinates[0] = x._kinectCoordinates[0]; //X
	this._kinectCoordinates[1] = x._kinectCoordinates[1]; //Y
	this._kinectCoordinates[2] = x._kinectCoordinates[1]; //z
} 

// Implementação de métodos da interface IXbox360.
@Override
public void motionSensing_ON()
{
	  System.out.println("Scanning Xbox 360 components for motion sensing device...");
	  if (!this._kinect)
	  {
	  	System.out.println("No kinect devices have been found.\nWould you like to connect your Xbox 360 to a Kinect device now?<Y/N>");
		
	  	Scanner input = new Scanner(System.in);
	  	String str = input.nextLine();
	  	String yes = "Yes";
	  	String no = "No"; 
		input.close();
		  if(str.equalsIgnoreCase(yes) || str.equalsIgnoreCase("Y")) 
		  {
		      System.out.println("Connecting kinect device...");
		      this.setKinect(true); 
		      motionSensing_ON();
		  } 
		  else 
			  if(str.equalsIgnoreCase(no) || str.equalsIgnoreCase("N"))
			  {
				  
			 
				System.out.println("Maybe next time.");
		  		
			  }
		  		return;	  
	  }
	  else
	  {
		  System.out.println("Your Xbox 360 is connected to a Kinect device. \nInitializing kinect....");
		  System.out.println("Fetching position...\nMapping coordinates...");
		  int[] randomInt = new int[3];
		  Random rnd = new Random();
		  for (int i = 0; i < 3; i++)
		  {
			  randomInt[i] = rnd.nextInt(100); 
		  }
		  this.setKinectCoordinates(randomInt[0], randomInt[1], randomInt[2]);	  
		  
		  System.out.println("X: "+ this.getKinectX() + ", Y: "+ this.getKinectY() +", Z: " + this.getKinectZ());
	  }
}


@Override
public void motionSensing_OFF()
{
	 if (this._kinect)
	 {
		  System.out.println("Would you like to disconnect your Kinect device?<Y/N>");
	  
		  Scanner input = new Scanner(System.in);
		  if(input.next().equalsIgnoreCase("y")||input.next().equalsIgnoreCase("yes")) 
		  {
		      System.out.println("Disconnecting kinect...");
		      this.setKinect(false);
		  } 
		  else 
			  if(input.next().equalsIgnoreCase("n")||input.next().equalsIgnoreCase("no")) 
				  System.out.println("Maybe next time.");
			  else 
				  System.out.println("Invalid option.");				  
		  input.close();
	 }
	 else
		 System.out.println("No kinect devices are connected.");
		 
}
		

@Override
public final void setKinect(boolean kinect)
{
	if (kinect)
	{
		if (!this._kinect)
        {
        	this._kinect = true;
        	System.out.println("Your kinect has been connected.");
        }
		else 
       	System.out.println("Your kinect is already connected.");
	}
	else
	{
		if (this._kinect)
	    {
			this._kinect = false;
        	System.out.println("Your kinect has been disconnected.");
	    }
		else
			System.out.println("Your kinect is already disconnected.");
	 }
}
 
 @Override
 public boolean getKinect()
 {
	 return this._kinect;
 }
 
 @Override
 public final void setKinectCoordinates(int x, int y, int z)
 {
	 this._kinectCoordinates[0] = x;
	 this._kinectCoordinates[0] = y;
	 this._kinectCoordinates[0] = z;
 }

 @Override
 public int getKinectX()
 {
	 return this._kinectCoordinates[0];
 }
 
 @Override
 public int getKinectY()
 {
	 return this._kinectCoordinates[1];
 }
 
 @Override
 public int getKinectZ()
 {
	 return this._kinectCoordinates[2];
 }


   
// Implementação dos métodos da classe abstrata Console.
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
           buttons = new String []{"Xbox Guide", "Start", "Select", "A", "B", "X", "Y",
               "LB", "RB", "LT", "RT", 
               "D Pad", "Left Thumbstick", "Right Thumbstick"};
 		  	newController.setButtons(buttons);
           
           System.out.print("Is it wireless? <Y/N>");
           
           Scanner input2 = new Scanner(System.in);
           if(input2.next().equalsIgnoreCase("y")||input.next().equalsIgnoreCase("yes")) 
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
	int op = 0;
  
    do
       {
    	   
           System.out.println("\n\n-Xbox 360-");
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
           System.out.println("14 - Turn off Xbox.");
           System.out.println("15 - Exit.");
           Scanner scanner = new Scanner(System.in);
           op = posNum(scanner);
       		         
           switch(op)
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
                   motionSensing_ON();
                   break;
               }
               case 8:
               {
                   motionSensing_OFF();
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
                   this.deleteUser();
                   break;
               }
               case 12:
               {
                   this.displayGames();;
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
                   if (op != 15)
                   {
                   	throw new IllegalArgumentException("\nInvalid option.\n");
                   }
               }
           }
       }while(op != 15);
           
       
}

// Implementação dos métodos da interface IDevice
@Override
public void power_ON()
{
	if (!this._power)
	{
		System.out.println("\t-XBOX 360-");    
	 	System.out.println("Loading Xbox 360. Please wait..."); 
	 	System.out.println("Scanning components...");    
	 	System.out.println(this);
	    motionSensing_ON();
	    menu();
	}
	else
    {
		System.out.println("Your Xbox 360 is already turned on.\nRestarting..."); 
        this._power = false;
        power_ON();
        System.out.println("Your Xbox 360 has been restarted!\n");
    }
}


@Override
public void power_OFF()
{
	if (_power)
    {
		System.out.println("Shutting down Xbox 360. Please wait...");
		this._power = false;
		System.out.println("Your Xbox 360 has been turned off."); 
    }
    else
    	System.out.println("Your Xbox 360 is already turned off."); 
}


/* "The reason people override the ToString() method is to have a default string representation 
 * of your object, usually for display to the user or in a log or console".
*/
@Override
public String toString()
{
   StringBuilder info = new StringBuilder();
   
   info.append( super.toString() );
   info.append("Kinect Status: ");
   if (this._kinect)
   {
       info.append("ON\n");
       info.append("Kinect Coordinates: (" + this._kinectCoordinates[0] + ", " + this._kinectCoordinates[1] + ", " + this._kinectCoordinates[2] + ")\n");
   }
   else info.append("OFF\n");
   
   return info.toString();
}

@Override
//Connect to the internet.
public void connectToTheInternet()
{
	if (this._ethernetCard)
		if (!this._internetConnection)
			this._internetConnection = true;
		else
			System.out.println("Your console is already connected to the internet.");
	else 
		System.out.println ("Your console does not support online functionality.");
			
}		

}