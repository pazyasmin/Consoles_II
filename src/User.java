
public class User 
{
	 private String _name;
	 private String _gamertag;
	 
	 private static int s_characterLimit = 25;
	 
  
    public User()
    {
        this._name = "John Doe";
        this._gamertag = "johndoegamertag";
    }
    
    public User(String name, String gamertag)
    {
        this.setName(name);
        this.setGamertag(gamertag);
    }
    
    public User(User u)
    {
        this._name = u._name;
        this._gamertag = u._gamertag;
    }
    
    public String getName()
    {
        return this._name;
    }
    
    public String getGamertag()
    {
        return this._gamertag;
    }
    
    public final void setName(String name)
    {
        if (name.length() < 5)
        {
            System.out.println("Name must be at least 5 characters long. ");
        }
        else 
        {
        	if (name.length() > s_characterLimit)
        	{
        		System.out.println ("Name has too many characters");
				name = name.substring(0, s_characterLimit);
        	}
        	this._name = name;
        }
    }
    
    public final void setGamertag( String gamertag )
    {
    	if (gamertag.length() < 5)
        {
            System.out.println("Gamertag must be at least 5 characters long. ");
            System.out.println("Assigning default name...");
            this._name = "360noscope";
        }
        else 
        {
        	if (gamertag.length() > s_characterLimit)
        	{
        		System.out.println ("Name has too many characters");
				gamertag = gamertag.substring(0, s_characterLimit);
        	}
        	this._gamertag = gamertag;
        }
    }
    
    @Override
    public String toString()
    {
        StringBuilder info = new StringBuilder();
        
        info.append("\nName: " + this._name + ".\n");
        info.append("Gamertag: " + this._gamertag + ".\n");
        
        return info.toString();
	}
	
}
