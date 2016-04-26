
public class Game 
{
	public String _title;
	public String _platform;
	public double _size; 

	public Game()
	{
		this._title = "JOGO X";
		this._platform = "PLATAFORMA X";
		this._size = 0.00;
	}
	
	public Game(String title, String platform, double size)
	{
		this.setTitle(title);;
		this.setPlatform(platform);
		this.setSize(size);;
	}
	
	
	public Game(Game g)
	{
		this._title = g._title;
		this._platform = g._platform;
		this._size = g._size;
	}
	
	public final void setTitle(String title)
	{
		if (!("\0".equals(title)))
			this._title = title;	
	}
	
	public String getTitle()
	{
		return this._title;
	}
	
	public final void setPlatform(String platform)
	{
		if (!("\0".equals(platform)))
			this._platform = platform;	
	}
	
	public String getPlatform()
	{
		return this._platform;
	}
	
	public final void setSize(double size)
	{
		if (!("\0".equals(size)))
			this._size = size;	
	}
	
	public double getSize()
	{
		return this._size;
	}
	
	@Override	
	public String toString()
	{
	    StringBuilder info = new StringBuilder(100);
	    return info.append("\nTitle:").append(this._title)
	          .append("\nPlatform:").append(this._platform)
	          .append("\nSize:").append(this._size)
	          .toString();
	}

}

