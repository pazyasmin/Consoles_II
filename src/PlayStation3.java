import java.util.Random;
import java.util.Scanner;

public class PlayStation3 extends Console implements IPlayStation3, IGameLoader
{
	private boolean _move; 
	private int [] _moveCoordinates;

	public PlayStation3()
	{
		super("PlayStation 3 Super Slim", "Sony", /* releaseDate,*/ false, true, "PlayStation 3", 1.0, 500.00);
		//this._releaseDate = s_releaseDate;
		this._move = false;
		this._moveCoordinates = new int [3];
		this.setPSMoveCoordinates(0, 0, 0);
	}
	
	public PlayStation3 (String model, String manufacturer, /* releaseDate,*/ boolean power, boolean ethernetCard, 
			String platform, double softwareVersion, double internalStorage, 
			boolean psMove)
	{
	    super(model, manufacturer,/* releaseDate,*/ power, ethernetCard,
	    		platform, softwareVersion, internalStorage);
	    this.setPSMove(psMove);
	    this._moveCoordinates = new int [3];
	    this.setPSMoveCoordinates(0, 0, 0);
	}
	
	public PlayStation3(PlayStation3 p)
	{
        super(p);
        this._move = p._move;
        this._moveCoordinates = new int [3];
        this._moveCoordinates[0] = p._moveCoordinates[0];  //X
		this._moveCoordinates[1] = p._moveCoordinates[1];; //Y
		this._moveCoordinates[2] = p._moveCoordinates[2];; //Z
    }
	
	
	 public String toString()
	 {
	        StringBuilder info = new StringBuilder();
	        
	        info.append( super.toString() );
	        info.append("PS Move Status: ");
	        if (this._move){
	            info.append("ON\n");
	            info.append("XYZ Coordinates: (" + this._moveCoordinates[0] + ", " 
	                    + this._moveCoordinates[1] + ", " + this._moveCoordinates[3] + ")\n");
	        }
	        else info.append("OFF\n");
	        
	        return info.toString();
	    }
//x------------------x------------------x------------------x------------------x------------------x//
//Implementação dos métodos da interface do PlayStation 3.
@Override
public void motionSensing()
{
	  System.out.println("Scanning PlayStation 3 for motion sensing device...");
	  if (!this._move)
	  {
	  	System.out.println("No motion sensing devices have been found. \nWould you like to connect your PlayStation 3 to a PS Move device now?<Y/N>");
		
	  	Scanner scanner = new Scanner(System.in);
		  if(scanner.next().equalsIgnoreCase("y")||scanner.next().equalsIgnoreCase("yes")) 
		  {
		      System.out.println("Connecting PS Move...");
		      this.setPSMove(true);
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
		  System.out.println("Your PlayStation 3 is connected to a PS Move device. \nInitializing PS Move....");
		  System.out.println("Fetching position...\nMapping coordinates...");
		  int[] randomInt = new int[3];
		  Random rnd = new Random();
		  for (int i = 0; i < 3; i++)
		  {
			  randomInt[i] = rnd.nextInt(100); 
		  }
		  this.setPSMoveCoordinates(randomInt[0], randomInt[1], randomInt[2]);	  
		  System.out.println("X: "+ this.getPSMoveX() + ", Y: "+ this.getPSMoveY() +", Z: " + this.getPSMoveZ());
		  
		  System.out.println("Would you like to disconnect your PS Move from your PlayStation3?<Y/N>");
	  
		  Scanner scanner = new Scanner(System.in);
		  if(scanner.next().equalsIgnoreCase("y")||scanner.next().equalsIgnoreCase("yes")) 
		  {
		      System.out.println("Disconnecting PS Move...");
		      this.setPSMove(false);
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
public final void setPSMove(boolean psMove)
{
	if (psMove)
	{
		if (!this._move)
        {
        	this._move = true;
        	System.out.println("Your PS Move has been connected.");
        }
		else 
       	System.out.println("Your PS Move is already connected.");
	}
	else
	{
		if (this._move)
	    {
			this._move = false;
        	System.out.println("Your PS Move has been disconnected.");
	    }
		else
			System.out.println("Your PS Move is already disconnected.");
		 }
}
  
 @Override
 public final void setPSMoveCoordinates(int x, int y, int z)
 {
	 this._moveCoordinates[0] = x;
	 this._moveCoordinates[0] = y;
	 this._moveCoordinates[0] = z;
 }

 @Override
 public int getPSMoveX()
 {
	 return this._moveCoordinates[0];
 }
 
 @Override
 public int getPSMoveY()
 {
	 return this._moveCoordinates[1];
 }
 
 @Override
 public int getPSMoveZ()
 {
	 return this._moveCoordinates[2];
 }	 
 @Override
 public boolean getPSMove()
 {
	 return this._move;
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
            buttons = new String []{"PS Guide", "Start", "Select", "A", "B", "X", "Y",
                "L1", "R1", "L2", "R2", 
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
        System.out.println("-PlayStation 3-");
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
            	Game game = new Game("God of War 3","PlayStation 3", 80.00);
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
            	Game game = new Game("God of War 3","PlayStation 3", 80.00);
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
                this.setPSMove(true);
                break;
            }
            case 8:
            {
                this.setPSMove(false);
                break;
            }
            case 9:
            {
                motionSensing();
                break;
            }
            case 10:
            {
            	Game game = new Game("God of War 3","PlayStation 3", 80.00);
                this.uninstallGame(game);
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
    this.setPSMove(false);
    System.out.println("Shutting down PlayStation 3. Please wait...");
    this.setPower(false);
    System.out.println("Your PlayStation3 has been turned off.");
 }
 
 @Override
 public void startScreen()
 {
	 if (!this._power)
	    {
		 	this.setPower(true);
		 	System.out.println("\t-PlayStation 3-");    
		 	System.out.println("Loading PlayStation 3. Please wait..."); 
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
