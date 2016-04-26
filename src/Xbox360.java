import java.util.Scanner;
import java.util.Random;

public class Xbox360 extends Console implements IXbox360, IGameLoader
{
	private boolean _kinect; 
	private int [] _kinectCoordinates;
	
	public Xbox360()
	{
		super("Xbox 360 Premium", "Microsoft", /*releaseDate, */ false, true, "Xbox 360", 1.0, 250.00);
		//this._releaseDate = s_releaseDate;
		this._kinect = false;
		this._kinectCoordinates = new int [3];
		this.setKinectCoordinates(0, 0, 0);
	}
	
	public Xbox360 (String model, String manufacturer, /*releaseDate, */ boolean power, boolean ethernetCard, 
			String platform, double softwareVersion, double internalStorage, 
			boolean kinect)
	{
	    super(model, manufacturer, /*releaseDate,*/ power, ethernetCard, 
	    		platform, softwareVersion, internalStorage);
	    this.setKinect(false);
	    this._kinectCoordinates = new int [3];
	    this.setKinectCoordinates(0, 0, 0);
	}
	
	public Xbox360(Xbox360 x)
	{
        super(x);
        this._kinect = x._kinect;
        this._kinectCoordinates = new int [3];
        this._kinectCoordinates[0] = x._kinectCoordinates[0]; //X
		this._kinectCoordinates[1] = x._kinectCoordinates[1];; //Y
		this._kinectCoordinates[2] = x._kinectCoordinates[2];; //Z
    }
	
	 @Override
	 public String toString()
	 {
	    StringBuilder info = new StringBuilder();
	    
	    info.append( super.toString() );
	    info.append("Kinect Status: ");
	    if (this._kinect)
	    {
	        info.append("ON\n");
	        info.append("XYZ Coordinates: (" + this._kinectCoordinates[0] + ", " 
	                + this._kinectCoordinates[1] + ", " + this._kinectCoordinates[3] + ")\n");
	    }
	    else info.append("OFF\n");
	    
	    return info.toString();
	}

//x------------------x------------------x------------------x------------------x------------------x//	
//Implementação de métodos da interface IXbox360
	 
@Override
public void motionSensing()
{
	  System.out.println("Scanning Xbox 360 for motion sensing device...");
	  if (!this._kinect)
	  {
	  	System.out.println("No motion sensing devices have been found.\nWould you like to connect your Xbox 360 to a Kinect device now?<Y/N>");
		
	  	Scanner scanner = new Scanner(System.in);
		  if(scanner.next().equalsIgnoreCase("y")||scanner.next().equalsIgnoreCase("yes")) 
		  {
		      System.out.println("Connecting kinect...");
		      this.setKinect(true);
		  } 
		  else 
			  if(scanner.next().equalsIgnoreCase("n")||scanner.next().equalsIgnoreCase("no")) 
				  System.out.println("Maybe next time.");
			  else 
				  System.out.println("Invalid option.");				  
		 scanner.close();		 
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
		  
		  System.out.println("Would you like to disconnect your Kinect from your Xbox360?<Y/N>");
	  
		  Scanner scanner = new Scanner(System.in);
		  if(scanner.next().equalsIgnoreCase("y")||scanner.next().equalsIgnoreCase("yes")) 
		  {
		      System.out.println("Disconnecting kinect...");
		      this.setKinect(false);
		  } 
		  else 
			  if(scanner.next().equalsIgnoreCase("n")||scanner.next().equalsIgnoreCase("no")) 
				  System.out.println("Maybe next time.");
			  else 
				  System.out.println("Invalid option.");				  
		 scanner.close();
	  }
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

//x------------------x------------------x------------------x------------------x------------------x//	
//Implementação de métodos da interface IGameLoader	 
//Play from media
@Override
public void play(Game game)  
{
	  if (!game.equals(this.getPlatform()))
		 System.out.println("Action denied. The game '" + game.getTitle() + "' is not compatible with your system.");
	  else
	  {
	   System.out.println("Loading game from media. Please wait...");
	   System.out.println("You're now playing "+ game.getTitle() + "!");
	   System.out.println("Press enter to quit game. ");
	   Scanner enter = new Scanner(System.in);
	   enter.nextLine();
	   enter.close();
	   System.out.println("Exiting game...");
	     
	  }
}
    
//Play from Hard Drive
@Override
public void play()
{
	if ( this._numControllers == 0 ) 
		System.out.println("Error. You can't play a game with no controllers.");
	    else
	    	if (_numGames == 0 )
	    		System.out.println("Error. There are currently no games installed on your hard drive.");
	    	else
	    	{
	    		System.out.println("Which of the following would you like to play?");
	
		        int i = 1;
		        for (Game gameList : _games) 
		        {
		            System.out.println( i + " - " + gameList.getTitle());
		            i++;
		        }
		        	
		        System.out.println("\nEnter an option: ");	
		        Scanner num = new Scanner(System.in);
		        int option=-1;
		        while(option<=0 || option > _numGames || !(num.hasNextInt()))
		        {
		   		 option = num.nextInt();
		   		 if(option < 0 || option > _numGames || !(num.hasNextInt()))
		   			 System.out.println ("Please enter a valid option: ");
		        }
		   		
		        num.close();
		        System.out.println("Loading game. Please wait...");
		        System.out.println("You're now playing "+ _games.get(option-1).getTitle() + "!");
		        System.out.println("Press enter to quit game. ");
		        Scanner enter = new Scanner(System.in);
		        enter.nextLine();
		        System.out.println("Quitting game...");
		        enter.close();        
	    }	
}

@Override
public void displayGames()
{
	 
	if (this._numGames == 0)
		System.out.println ("No games have been found.");
	else
	{
		int i = 1;
		for (Game game : _games)
		{
			System.out.println (i + " - " + game);
			i++;
		}
	}

}
//x------------------x------------------x------------------x------------------x------------------x//	
//Implementação dos métodos da classe abstrata Console.
@Override
public void insertController()
{
	  System.out.println("Would you like to insert a controller?<Y/N>");
	  Scanner scanner = new Scanner(System.in);
	  
	  if(scanner.next().equalsIgnoreCase("y")||scanner.next().equalsIgnoreCase("yes")) 
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
           
           Scanner scanner2 = new Scanner(System.in);
           if(scanner.next().equalsIgnoreCase("y")||scanner.next().equalsIgnoreCase("yes")) 
 		  	{	            
           	newController.setWireless(true);
 		  	}
           else
           	newController.setWireless(false);
           
 		  	scanner2.close();
           
 		  	this._controllers.add(newController);
           this._numControllers++;
           System.out.println("Controller "+ this._controllers.size() + "found!");
		 }
	  }
	  else 
		  System.out.println("Operation cancelled.");
	  
	  scanner.close();
}


@Override
public void menu()
{
	 int op = 0;
       Scanner scanner = new Scanner(System.in);
       do
       {
           System.out.println("-Xbox 360-");
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
           System.out.println("9 - Motion sensing.");
           System.out.println("10 - Uninstall game.");
           System.out.println("11 - Remove controller.");
           System.out.println("12 - Delete user.");
           System.out.println("13 - Display games.");
           System.out.println("14 - Display users.");
           System.out.println("15 - Exit.");	   	            
           System.out.print(" > ");
           
       
           while(true)
           {
               if (scanner.hasNextInt())
                   op = scanner.nextInt();
               else
               {
                   System.out.print("Please enter a valid option.\n > ");
                   scanner.next();
                   continue;
               }
               break;
           }
           
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
               		Game game = new Game("Halo","Xbox 360",120.00);
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
               	   Game game = new Game("Halo","Xbox 360",120.00);
                   this.installGame(game);;
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
                   this.setKinect(true);
                   break;
               }
               case 8:
               {
                   this.setKinect(false);
                   break;
               }
               case 9:
               {
                   motionSensing();
                   break;
               }
               case 10:
               {
            	   Game game = new Game("Halo","Xbox 360",120.00);
                   this.uninstallGame(game);;
                   break;
               }
               case 11:
               {
                   this.removeController();
                   break;
               }
               case 12:
               {
                   this.deleteUser();
                   break;
               }
               case 13:
               {
                   this.displayGames();;
                   break;
               }
               case 14:
               {
                   this.displayUsers();
                   break;
               }
               case 15:
               {
                   this.format();
                   break;
               }
               default:
               {
                   if (op != 16)
                   {
                   	throw new IllegalArgumentException("\nInvalid option.\n");
                   }
               }
           }
       }while(op != 15);
       
       scanner.close();
       this.setKinect(false);
       System.out.println("Shutting down Xbox 360. Please wait...");
       this.setPower(false);
       System.out.println("Your Xbox360 has been turned off.");
}


@Override
public void startScreen()
{
	 if (!this._power)
	 {
		 this.setPower(true);
	 	System.out.println("\t-XBOX 360-");    
	 	System.out.println("Loading Xbox 360. Please wait..."); 
	 	System.out.println("Scanning components...");    
	 	System.out.println(this);
	    	motionSensing();
	        menu();
	 }
	 else
		this.setPower(true);
}
//
}
