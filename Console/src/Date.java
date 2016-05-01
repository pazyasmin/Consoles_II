public class Date 
{
	
	private int _day;
	private int _month;
	private int _year;


public Date()
{
    this._day = 1;
    this._month = 1;
    this._year = 1900;
}


public Date(int day, int month, int year)
{
    this.setYear(year);
    this.setMonth(month);
    this.setDay(day);
}


public Date(Date d)
{
	this._day = d._day;
    this._month = d._month;
    this._year = d._year;
}



public final void setYear(int year)
{
		if (year < 0)
			System.out.println("Error: The specified year is invalid.");
		else
	        this._year = year;
}

public int getYear()
{
	return this._year;
}

public final void setMonth(int month)
{
	if (month < 1 || month > 12)
		System.out.println("Error: The specified month is invalid.");
	else
		this._month = month;
}

public int getMonth()
{
	return this._month;
}

public final void setDay(int day)
{
	if (day > 0 && validateMonth(day)) 
		this._day = day;
	else
		System.out.println("Error: The specified day is invalid.");
}

public int getDay()
{
	return this._day;
}

public final void setDate(int day, int month, int year)
{
	this.setYear(year);
	this.setMonth(month);
	this.setDay(day);
}



public boolean validateMonth(int day)
{
	int [] daysPerMonth;
	    daysPerMonth = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,30, 31};
	
	if (day <= daysPerMonth[this._month])
	{
		if (leapYear(day)) 
			return true;
		else 
			if (this._month != 2) 
				return true;
	}
	return false;
}
	
public boolean leapYear(int day)
{
    return (this._month == 2 && day == 29 && (this._year % 400 == 0 ||
	       (this._year % 4 == 0 && this._year % 100 != 0)));
}

@Override
//The reason people override the ToString() method is to have a default string representation 
//of your object, usually for display to the user or in a log or console
public String toString()
{return String.format("%d/%d/%d", _day, _month, _year);}

}
	