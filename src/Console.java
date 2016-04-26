import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public abstract class Console extends Device 
{
	protected static final int s_maxControllers = 4;
	
	protected String _platform;
	protected double _softwareVersion;
	protected boolean _internetConnection; 
	protected double[] _storage; // Internal, used and free spaces.
	protected ArrayList<Game> _games;
	protected ArrayList<Controller> _controllers;
	protected ArrayList<User> _users; 
	protected int _numUsers = 0; 
	protected int _numGames = 0; 
	protected int _numControllers = 0;

	
public Console() 
{
	super();
	this.setPlatform("*Platform*");
	this._softwareVersion = 1.00;
	this._internetConnection = false;
	this._storage = new double[3];
	this._storage[0] = 500.00; 
	this._storage[1] = 0.00; 
	this._storage[2] = 500.00; //Internal Storage/Used Storage/Free Storage
	this._controllers = new ArrayList<>();
	this._games = new ArrayList<>();
	this._users = new ArrayList<>();
}

public Console(String model, String manufacturer, /*Date releaseDate, */ boolean power, boolean ethernetCard, 
		String platform, double softwareVersion, double internalStorage)
{
	super(model, manufacturer, /*releaseDate,*/ power, ethernetCard);
	this.setPlatform(platform);
	this._softwareVersion = softwareVersion;
	this._storage[0] = internalStorage; 
	this._storage[1] = 0.00; 
	this._storage[2] = internalStorage;
	this._controllers = new ArrayList<>();
	this._games = new ArrayList<>();
	this._users = new ArrayList<>();
}

public Console(Console c)
{
	super(c);
	this._platform = c._platform;
	this._softwareVersion = c._softwareVersion;
	this._storage[0] = c._storage[0];
	this._storage[1] = c._storage[1];
	this._storage[2] = c._storage[2];
    this._games= new ArrayList<>();
    this._controllers = new ArrayList<>(s_maxControllers);
	this._controllers = c._controllers;
	this._games = c._games;
	this._numControllers = c._numControllers;
	this._numGames = c._numGames;
	this._users = c._users;
    this._numUsers = c._numUsers;
	
}

@Override
public String toString(){
    StringBuilder info = new StringBuilder();
    
    info.append(super.toString());
    info.append("Platform: " + this._platform + ".\n");
    info.append("Software Version: " + this._softwareVersion + ".\n");
    info.append("Storage Capacity: \nTotal: " + this._storage[1] + "GB, Used: "
            + this._storage[2] + "GB, Free: "+this._storage[3] + "GB.");
    info.append("Games installed: " + this._numGames + ".\n");
    info.append("Plugged controllers: " + this._numControllers + ".\n");
    
    return info.toString();
}

//x------------------x------------------x------------------x------------------x------------------x//
//Platform.
public final void setPlatform(String platform)
{
	if (platform.length() > s_maxLength)
		this._platform = platform.substring(0, s_maxLength);
	else 
	if ("\0".equals(platform))
		this._platform = "**Platform**"; 
		else
			this._platform = platform;		
	this._platform = platform;
}

public String getPlatform()
{
	return this._platform;
}

//Software Version.	
public double getSoftwareVersion()
{
	return this._softwareVersion;
}

//Storage.
public double getInternalSpace()
{
	return this._storage[0];
}

public final void setUsedSpace(double space)
{
	if (Math.abs(space) > 1)
	{
		this._storage[1] += space;
		setFreeSpace(-1*space); 
	}
	else
		System.out.println ("\nMemory error.");
}
	
public double getUsedSpace()
{
	return this._storage[1];
}

public final void setFreeSpace(double space)
{
	this._storage[2] += space;
}

public double getFreeSpace()
{
	return this._storage[2];
} 	

//Format hard drive.
public void format()
{
	this._storage[3] = this._storage[0];
	this._storage[2] = 0.00;
	this._users.clear();
	this._controllers.clear();
	this._games.clear();
	
}
//x------------------x------------------x------------------x------------------x------------------x//
 //User.
 public void createUser()
 {
	System.out.println("Enter your name: ");
	Scanner input = new Scanner(System.in);
	
	String name = input.nextLine();
	name = name.replaceAll("&(?!;)[^]*_-", "");
	
	System.out.println("Enter your gamertag: ");
	Scanner input2 = new Scanner(System.in);
	
	String gamertag = input2.nextLine();
	gamertag = gamertag.replaceAll("&(?!;)[^]*", "");
	
	User newUser = new User(name, gamertag);
	
	input.close();
	input2.close();
	
	boolean itExists = false;
	for (User users : _users)
	{
	    if (users.equals(newUser))
	    {
	        System.out.println("User already exists.");
	        itExists = true;
	        break;
	    }
	}
	
	if (!itExists)
	{
        this._numUsers++;
        _users.add(newUser);
        System.out.println("User successfully created.");
    }
}
 
public void createUser(User user)
{
	if(!this._users.contains(user))
	{
		this._numUsers++;
		this._users.add(user);
		System.out.println("The user '" + user.getName() + "' was successfully created.");
	}
	else
		System.out.println("Error. The user '" + user.getName() + "' already exists.");
 	
}

 public void deleteUser()
 {	 
    if ( this._numUsers > 0 ) 
    {
	    System.out.println("\nWhich of the following would you like to delete? \n");
	    int i = 1;
	    	for ( User u : this._users )
	    	{
	    		System.out.println (i +  " - " + u);
	    		i++;
	    	}
	    	System.out.println("> ");
	    	Scanner scanner = new Scanner(System.in);
	    	int op;
	    
	    while(true)
	    {
	        if (scanner.hasNextInt())
	            op = scanner.nextInt();
	        else
	        {
	            System.out.println("Please enter a valid option:\n ");
	            scanner.next();
	            continue;
	        }
	        break;
	    }
	    
	    op -= 1;
	    if(op < 0 || op > this._numUsers - 1)
	    {
	        System.out.println("Invalid user.");
	    }
	    else
	    {
	        System.out.print("\nDelete " + _users.get(op).getName() + "? [Y/N]\n");
	        Scanner scanner2 = new Scanner(System.in);
	        if(scanner2.next().equalsIgnoreCase("y")||scanner2.next().equalsIgnoreCase("yes")) 
	        {
	        	this._users.remove(op);
	            this._numUsers--;
	            System.out.println("User removed successfully.");
	        }
	        else
	            System.out.println("Maybe next time.");
	        scanner.close();
	        scanner2.close();                
	    }
    }
    else
    	System.out.println("There are no users yet.");
}
 
public void deleteUser(User user)
{
   	if(!this._users.contains(user))
   		System.out.println("Error. User not found.");
   	else
   	{
   		this._numUsers--;
   		this._users.remove(user);
   		System.out.println("The user '" + user.getName() + "' was successfully deleted.");
   	}
}	  

//Internet.
public void connectToTheInternet()
{
	if (this._ethernetCard)
		if (!this._internetConnection)
			this._internetConnection = true;
		else
			System.out.println("\nYour console is already connected to the internet.");
	else 
		System.out.println ("\nYour console does not support online functionality.");
			
}		
//Update.
public void update()
{
 	double latest = 0.15;
 	this.connectToTheInternet();
 	if (this._internetConnection)
 	{
  	Random randomGenerator = new Random();  
  	boolean randomBool = randomGenerator.nextBoolean();
  	if (randomBool)
      {
  		System.out.print("\nThere are new updates available. Press enter to proceed. ");
			Scanner enter = new Scanner(System.in);
			enter.nextLine();
			System.out.println("Updating system...");
			enter.close();
			this._softwareVersion += latest;
			System.out.println("Your system has been updated. The current version is "+ this.getSoftwareVersion());
      }
      else
      	System.out.print("\nThere are no updates available.");      
 	}
 	else
     System.out.print("\nInternet connection failed.");
}
		
//Game. 
public void installGame(Game game)
{
	if(this._games.contains(game))
   		 System.out.println("Action denied. The game '" + game.getTitle() + "' is already installed.");
	else
	{
		if (!game.equals(this.getPlatform()))
			System.out.println("The game '" + game.getTitle() + "' could not be installed. It is not compatible with your system.");
		else
		{	 
			if (this._storage[2] < game.getSize())
				System.out.println("The game '" + game.getTitle() + "' could not be installed. Not enough storage space.");
			else
			{
				 this._numGames++;
				 this._games.add(game);
				 this.setUsedSpace(game.getSize());
		    		 System.out.println("The game '" + game.getTitle() + "' was successfully installed!");
			}
		}
	}
}
   
public void uninstallGame(Game game)
{
   	if(!this._games.contains(game))
   		System.out.println("Error. The specified game was not found.");
   	else
   	{
		this.setUsedSpace( -1*( game.getSize() ) );
		this._numGames--;
		this._games.remove(game);
		System.out.println("The game '" + game.getTitle() + "' was successfully uninstalled!");
	}
}
   
  
//Controllers.
public void removeController()
{
	if (this._numControllers == 0)
		System.out.println("No controllers have been found.");
	else
	{	
		System.out.println ("Which controller would you like to remove? " + "(1 - " + this._numControllers + ")");
		int i = 1;
		for(Controller c : this._controllers)
		{
			System.out.println (i + " - " + c);
			i++;
		}
		
		Scanner port = new Scanner(System.in);
		int op;
		while(true)
		{
			
			if (port.hasNextInt())
				op = port.nextInt();
			else
			{
				System.out.println ("Please enter a valid port:\n");
				port.next();
				continue;
			}
			break;
		}

		port.close();
		op -= 1;
		
		if (!(op < 0 && op > this._numControllers-1))
		{
		    System.out.println(_controllers.get(op));
		    System.out.println("Remove controller? <Y/N> ");		    
		    Scanner op2 = new Scanner(System.in);
            if(op2.next().equalsIgnoreCase("y")||op2.next().equalsIgnoreCase("yes")) 
            {
		        _controllers.remove(op);
		        this._numControllers--;
		        System.out.println("Controller removed successfully.");
            }
            else System.out.println("Maybe next time.");	    			
            	op2.close();						
		}
	}
}


 public void displayControllers()
 {
	 if (this._numControllers == 0 )
	 {
		 System.out.println("No controllers have been found.");
     } 
	 
    int i = 1;
    for (Controller controller : _controllers) 
    {
        System.out.println ( i + " - " + controller );
        i++;
    }
 }
 	
 public void displayUsers()
 {
 	if (this._numUsers == 0)
 		System.out.println ("No users have been found.");
 	else
 	{
 		int i = 1;
 		for (User u : _users)
 		{
 			System.out.println (i + " - " + u);
 			i++;
 		}
 	}
 }
 
//Abstract Methods.
public abstract void menu();
public abstract void insertController();
public abstract void startScreen();

}

