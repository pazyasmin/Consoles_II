import java.util.ArrayList;

public class Controller 
{
	private static final int s_maxButtons = 20;
	
	private boolean		_wireless;
	private ArrayList<String> _buttons;
	private int _numButtons = 0;
	
	public Controller()
	{
		this._buttons = new ArrayList<>(s_maxButtons);
		this._wireless = false;
	}
	
	public Controller(Controller co)
	{
		this._buttons = new ArrayList<>(s_maxButtons);
		this._wireless = co._wireless;
		this._numButtons = co._numButtons;
	}
	
	  public Controller (boolean wireless, String[] buttons)
	  {
        this._buttons = new ArrayList<>(s_maxButtons);
        this.setButtons(buttons);
        this.setWireless(wireless);
	  }
	  
	  public void setWireless(boolean wireless)
	  {
		  this._wireless = wireless;
	  }
	  
	  public boolean getWireless()
	  {
		  return this._wireless;
	  } 
	  
	  public final void setButtons(String [] _buttons)
	  {
	        if (_buttons.length < s_maxButtons)
	        {
	        	for (String b : _buttons)
	        	{
                    this._buttons.add(b);
                    this._numButtons++;
	            }
	        }
	  }
	 
	  public ArrayList<String> getButtons()
	  {
		  return _buttons;
	  }
	  
	  @Override
	  public String toString()
	  {
	        StringBuilder info = new StringBuilder();
	       
	        if (this._wireless) 
	        	info.append("\nWireless on.\n");
	        else 
	        	info.append("\nWireless off.\nButtons: ");
	        
	        String delim = "";
	        
        	for (String b : _buttons)
        	{
                info.append(delim).append(b);
                delim = ",";
        	}
	       return info.toString();
	 }
	  
}


