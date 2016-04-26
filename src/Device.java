
public abstract class Device 
{
	public static final int s_maxLength = 20;
	
	protected String  _model; 
	protected String  _manufacturer;
	//protected Date 	  _releaseDate;
	protected boolean _ethernetCard;
	protected boolean _power; 	

public Device()
{
	this._model = "*Model*";
	this._manufacturer = "*Manufacturer*";
	//this._releaseDate = new Date(1,1,1999);
	this._power = false;
}

public Device(String model, String manufacturer, /*Date releaseDate,*/ boolean power, boolean ethernetCard)
{
	this.setModel(model);
	this.setManufacturer(manufacturer);
	//this._releaseDate = releaseDate; 
	this._power = power;
	this._ethernetCard = ethernetCard;
}

public Device (Device dev)
{
	this._model = dev._model;
	//this._releaseDate = dev._releaseDate;
	this._power = dev._power;
}

@Override
public String toString()
{
	return "\nPower" + this._power + "\nModel: " + this._model + "\nManufacturer: "+ this._manufacturer /*"\nRelease Date: " + this._releaseDate*/;
}

//Model.
public final void setModel(String model)
{
	if (model.length() > s_maxLength)
		this._model = model.substring(0, s_maxLength);
	else 
	if ("\0".equals(model))
		this._model = "**Model**"; 
		else
			this._model = model;		
}
public String getModel()
{
	return _model;
}
//Manufacturer.
public final void setManufacturer(String manufacturer)
{
	if (manufacturer.length() > s_maxLength)
		this._manufacturer = manufacturer.substring(0, s_maxLength);
	else 
	if ("\0".equals(manufacturer))
		this._manufacturer = "**Manufacturer**"; 
		else
			this._manufacturer = manufacturer;		
}

public String getManufacturer()
{
	return _manufacturer;
}
/*	
 	//Release Date.
	public final void setReleaseDate(int day, int month, int year)
	{
		this._releaseDate = new Date(day, month, year);
	}
	
	public Date getReleaseDate()
	{
		return this._releaseDate;
	}

*/	

//Power.
public final void setPower(boolean power)
{
	 if (!this._power)
	 {
		 if (power)
		 {
			 this._power = true;
			 System.out.println("Your device has been turned on.");
		 }
		 else
			System.out.println("\nYour device is already turned off.");
	}		
	else			
		if (!power)
		{
			System.out.println("Turning off device...\nYour device has been turned off successfully."); 
		}
		else
		{
			 System.out.println("\nYour device is already turned on.\nRestarting..."); 
		     this._power = false;
		     try 
		     {
		    	 Thread.sleep(4000);                
		     }catch(InterruptedException ex) 
		     {
		        Thread.currentThread().interrupt();
		     }
		     this._power = true;
		     System.out.println("Your device has been restarted!\n");
		}				
}
	
public boolean getPower()
{
    return this._power;
}

}
