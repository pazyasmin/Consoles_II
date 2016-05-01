/*
* 
*/
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;

public abstract class Console extends Device  
{
	protected static final int s_maxControllers = 4;
	protected static final int s_maxUsers = 12;
	
	protected Platforms _platform;
	protected double _softwareVersion;
	protected boolean _internetConnection; 
	protected double[] _storage; 
	protected Game[] _availableGames = {
			new Game("Halo 5", Platforms.XBOX360, 50.00),
			new Game("Gears of War", Platforms.XBOX360, 34.00),
			new Game("Killer Instinct", Platforms.XBOX360, 61.00),
			new Game("Sonic Generations", Platforms.XBOX360, 20.00),
			new Game("God of War 3", Platforms.PLAYSTATION3, 54.00),
			new Game("Bayonetta", Platforms.PLAYSTATION3, 50.00),
			new Game("Metal Gear Solid", Platforms.PLAYSTATION3, 40.00),
			new Game("Dead Space 3", Platforms.PLAYSTATION3, 60.00),
	};
	protected int _numAvailableGames = 8;
	protected ArrayList<User> _users;
	protected ArrayList<Game> _games;
	protected ArrayList<Controller> _controllers;
	protected int _numUsers = 0; 
	protected int _numGames = 0; 
	protected int _numControllers = 0;

	
public Console() 
{
	super();
	this.setPlatform(Platforms.UNSPECIFIED);
	this._softwareVersion = 1.00;
	this._internetConnection = false;
	this._storage = new double[3];
	this._storage[0] = 500.00; //Internal Storage
	this._storage[1] = 0.00;   //Used Storage
	this._storage[2] = 500.00; //Free Storage
	this._users = new ArrayList<>();
	this._controllers = new ArrayList<>();
	this._games = new ArrayList<>();
}

public Console(boolean power, String model, String manufacturer, boolean ethernetCard, 
		Platforms platform, double softwareVersion, double internalStorage)
{
	super(power, model, manufacturer, ethernetCard);
	this.setPlatform(platform);
	this._softwareVersion = softwareVersion;
	this.setAvailableStorage(internalStorage);
	this._controllers = new ArrayList<>();
	this._games = new ArrayList<>();
	this._users = new ArrayList<>();
	
}

public Console(boolean power, String model, String manufacturer, Date releaseDate, boolean ethernetCard, 
		Platforms platform, double softwareVersion, double internalStorage)
{
	super(power, model, manufacturer, releaseDate, ethernetCard);
	this.setPlatform(platform);
	this._softwareVersion = softwareVersion;
	this._storage = new double[3];
	this.setAvailableStorage(internalStorage);
	this._storage[0] = internalStorage;
	this._storage[1] = 0.00;
	this._storage[0] = internalStorage;
	this._controllers = new ArrayList<>();
	this._games = new ArrayList<>();
	this._users = new ArrayList<>();
	
}

public Console(Console c)
{
	super(c);
	
	this._platform = c._platform;
	this._softwareVersion = c._softwareVersion;
	for (int i = 0; i < 3; i++)
		this._storage[i] = c._storage[i];
	this._games= new ArrayList<>();
    this._controllers = new ArrayList<>(s_maxControllers);
    this._users = new ArrayList<>(s_maxUsers);
	this._controllers = c._controllers;
	this._numControllers = c._numControllers;
	this._users = c._users;
    this._numUsers = c._numUsers;
	this._games = c._games;
	this._numGames = c._numGames;
	
}


//Métodos abstratos.
public abstract void menu();
public abstract void insertController();
public abstract void motionSensing_ON();
public abstract void motionSensing_OFF();


// Plataforma.
public final void setPlatform(Platforms platform)
{
	this._platform = platform;
}

public Platforms getPlatform()
{
	return this._platform;
}

// Versão de Software.	
public final void setSoftwareVersion(double softwareVersion)
{
	if (softwareVersion<0.01)
		this._softwareVersion = 1.0;
	else
		this._softwareVersion = softwareVersion;
}

public double getSoftwareVersion()
{
	return this._softwareVersion;
}

// Capacidade de Armazenamento.
public final void setAvailableStorage(double internalStorage)
{
	this._storage[0] += internalStorage;
	this._storage[2] += internalStorage;
}

public double getInternalStorage()
{
	return this._storage[0];
}

public final void setUsedStorage(double space)
{
	if (Math.abs(space) > 1)
	{
		this._storage[1] += space;
		this.setFreeStorage(-1*space); 
	}
	else
		System.out.println ("Memory error.");
}
	
public double getUsedStorage()
{
	return this._storage[1];
}

public final void setFreeStorage(double space)
{
	this._storage[2] += space;
}

public double getFreeStorage()
{
	return this._storage[2];
} 	

public Game[] getAvailableGames()
{
	return this._availableGames;
}

// Formatar hard drive.
public void format()
{
	this._storage[2] = this._storage[0];
	this._storage[1] = 0.00;
	this._users.clear();
	this._controllers.clear();
	this._games.clear();
	this._numUsers = 0;
	this._numControllers = 0;
	this._numGames = 0;
	
}



// User.
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
	
	input.close();
	input2.close();
	
	User newUser = new User(name, gamertag);
	
	boolean itExists = false;
	for (User users : _users)
	{
	    if (users.equals(newUser))
	    {
	    	System.out.println("Error. The user '" + name + "' already exists.");
	    	itExists = true;
	        break;
	    }
	}
	
	if (!itExists)
	{
        this._numUsers++;
        _users.add(newUser);
        System.out.println(("The user '" + name + "' was successfully created."));
    }
}
 

 public void deleteUser()
 {	 
    if (!(this._numUsers > 0)) 
    	System.out.println("Error. There are no users registered yet.");
    else
    {
	    System.out.println("Which of the following would you like to delete? \n");

	    int i = 1;
    	for ( User u : this._users )
    	{
    		System.out.println (i +  " - " + u);
    		i++;
    	}

    	Scanner input = new Scanner(System.in);
    	int op;

	    while(true)
	    {
	        if (input.hasNextInt())
	            op = input.nextInt();
	        else
	        {
	            System.out.println("Please enter a valid option:\n ");
	            input.next();
	            continue;
	        }
	        break;
	    }
	    
	    op -= 1;
	    if(op < 0 || op > this._numUsers - 1)
	    {
	        System.out.println("Error. The specified user is invalid.");
	    }
	    else
	    {
	        System.out.println("Delete " + _users.get(op).getName()  + "? [Y/N]\n");
	        Scanner input2 = new Scanner(System.in);
	        
	        if(input2.next().equalsIgnoreCase("y") || input2.next().equalsIgnoreCase("yes")) 
	        {
	        	this._users.remove(op);
	            this._numUsers--;
	            System.out.println("User removed successfully.");
	        }
	        else
	            System.out.println("Maybe next time.");
	        
	        input.close();
	        input2.close();                
	    }
    }

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


// Connect to the internet.
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
		
// Update system.
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
	  		System.out.println("There are new updates available. The current software version is "+ this.getSoftwareVersion() +". \nPress enter to proceed. ");
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();
			System.out.println("Updating system...");
			this._softwareVersion += latest;
			System.out.println("Your system has been updated. The current version is "+ this.getSoftwareVersion());
			scanner.close();
	  	}
	  	else
	      	System.out.println("There are no updates available.");      
 	}
 	else
     System.out.println("Internet connection failed.");
}


//Jogar de alguma mídia de jogo (cds, blurays, cartuchos, etc).
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


// Jogar jogos instalados no hard drive.
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
		        	
		        System.out.println("Enter an option: ");	
		        Scanner input = new Scanner(System.in);
		        int option;
		        while(true)
		        {
		        	if (input.hasNextInt())
		        		option = input.nextInt();
		        	else
		        	{
		        		System.out.println("Error. Enter a valid option.");
		        		input.next();
		        		continue;
		        	}
		        	break;
		        }
		        
		        input.close();
		        
		   		 if(option < 0 || option > _numGames)
		   			System.out.println("The game is not installed.");
		   		  else		
		   			{
				        System.out.println("Loading game. Please wait...");
				        System.out.println("You're now playing "+ _games.get(option-1).getTitle() + "!");
				        System.out.println("Press enter to quit game. ");
				        Scanner enter = new Scanner(System.in);
				        enter.nextLine();
				        System.out.println("Quitting game...");
					        enter.close();        
			   		}
			    }	
}

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

public Game menuAvailableGames()
{
	int i;
	System.out.println ("Available Games");
	for (i = 0; i < this._numAvailableGames; i++)
		System.out.println("Option " +(i+1) + " - "+ this._availableGames[i].getTitle());
	
	String str = JOptionPane.showInputDialog(null,"Choose one of the options.\n", "Menu PlayStation 3", JOptionPane.INFORMATION_MESSAGE);;
  	int op = Integer.parseInt(str.trim());       
	return (this._availableGames[op]);

}

public void installGame(Game game)
{
	if(this._games.contains(game))
   		 System.out.println("Action denied. The game '" + game.getTitle() + "' is already installed.");
	else
	{
		if (!game.getPlatform().equals(this.getPlatform()) )
			System.out.println("The game '" + game.getTitle() + "' could not be installed. It is not compatible with your system.");
		else
		{	 
			if (this._storage[2] < game.getSize())
				System.out.println("The game '" + game.getTitle() + "' could not be installed. Not enough storage space.");
			else
			{
				 this._numGames++;
				 this._games.add(game);
				 this.setUsedStorage(game.getSize());
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
		this.setUsedStorage( -1*( game.getSize() ) );
		this._numGames--;
		this._games.remove(game);
		System.out.println("The game '" + game.getTitle() + "' was successfully uninstalled.");
	}
}
   
  
//Controllers.
public void removeController()
{
	if (this._numControllers == 0)
		System.out.println("No controllers have been found.");
	else
	{	
		int usrInput=0;
		boolean done = false;
		
		while(!done)
	    {	 
			System.out.println ("Which controller would you like to remove? " + "(1 - " + this._numControllers + ")");
			int i = 1;
			for(Controller c : this._controllers)
			{
				System.out.println (i + " - " + c);
				i++;
			}
			  Scanner sc = new Scanner(System.in);
			
			  try
			  {
			     usrInput=sc.nextInt();
			  }
			  catch(InputMismatchException exception)
			  {
			    System.out.println("This is not an integer.");
			  }
			  
			  if (usrInput <= 0 || usrInput > s_maxControllers)
			  {
				  System.out.println("This is not a valid option.");
			  }
			  else
				  done = true;
		sc.close();
		int op = usrInput;
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
 
@Override
public String toString()
{
     StringBuilder info = new StringBuilder();
     
     info.append(super.toString());
     info.append("\nPlatform: " + this._platform + ".\n");
     info.append("Software Version: " + this._softwareVersion + ".\n");
     info.append("Storage Capacity: \nTotal: " + this._storage[0] + "GB, Used: "
             + this._storage[1] + "GB, Free: "+this._storage[2] + "GB.\n");
     info.append("Games installed: " + this._numGames + ".\n");
     info.append("Plugged controllers: " + this._numControllers + ".\n");
     
     return info.toString();
}
 
public int posNum(final Scanner scan) 
{
    int input = 0;
    boolean error = false;
    if (scan.hasNext()) {
        if (scan.hasNextInt()) {
            input = scan.nextInt();
            error = input <= 0;
        } else {
            scan.next();
            error = true;
        }
    }
    while (error) {
        System.out.print("Invalid input. Please reenter: ");
        if (scan.hasNextInt()) {
            input = scan.nextInt();
            error = input <= 0;
        } else {
            if (scan.hasNext())
                scan.next();
            error = true;
        }
    }
    return input;
}
}

