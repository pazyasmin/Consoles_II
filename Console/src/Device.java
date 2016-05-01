public abstract class Device implements IDevice
{

	protected String  _model; 
	protected String  _manufacturer;
	protected Date 	  _releaseDate;
	protected boolean _ethernetCard = false;
	protected boolean _power = false; 	

public Device()
{
	this._model = "MODEL";
	this._manufacturer = "MANUFACTURER";
	this._releaseDate = new Date(1,1,1999);
}

public Device(boolean power, String model, String manufacturer, boolean ethernetCard)
{
	this._power = power;
	this.setModel(model);
	this.setManufacturer(manufacturer);
	this._ethernetCard = ethernetCard;
}


public Device(boolean power, String model, String manufacturer, Date releaseDate, boolean ethernetCard)
{
	this._power = power;
	this.setModel(model);
	this.setManufacturer(manufacturer);
	this._releaseDate = releaseDate;
	this._ethernetCard = ethernetCard;
}

public Device (Device dev)
{
	this._power = dev._power;
	this._model = dev._model;
	this._manufacturer = dev._manufacturer;
	this._releaseDate = dev._releaseDate;
	this._ethernetCard = dev._ethernetCard;
}

// Model.
public final void setModel(String model)
{
	if (model.length() > 25)
		this._model = model.substring(0, 25);
	else 
	if (!"\0".equals(model))
		this._model = model;
	else
		System.out.println ("Field cannot be empty.");
}

public String getModel()
{
	return _model;
}

// Manufacturer.
public final void setManufacturer(String manufacturer)
{
	if (manufacturer.length() > 25)
	{
		System.out.println ("Manufacturer has too many characters");
		this._manufacturer = manufacturer.substring(0, 25);
	}
	else 
	if (!"\0".equals(manufacturer))
		this._manufacturer = manufacturer;
	else
		System.out.println ("Field cannot be empty.");
}

public String getManufacturer()
{
	return _manufacturer;
}

	
// Release Date.
public final void setReleaseDate(int day, int month, int year)
{
	this._releaseDate = new Date(day, month, year);
}

public Date getReleaseDate()
{
	return this._releaseDate;
}

// Power.
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
			 System.out.println("\nYour device is already turned on."); 	
}
	
public boolean getPower()
{
    return this._power;
}

// toString.
@Override
public String toString()
{
	return "\n-System Info- \nPower: " + this._power + "\nModel: " + this._model + "\nManufacturer: "+ this._manufacturer +"\nRelease Date: " 
			+ this._releaseDate + "\nEthernet Card: " + this._ethernetCard;
}

}
