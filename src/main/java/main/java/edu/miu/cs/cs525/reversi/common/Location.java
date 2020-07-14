package main.java.edu.miu.cs.cs525.reversi.common ;

import main.java.edu.miu.cs.cs525.reversi.utils.Convert;
import main.java.edu.miu.cs.cs525.reversi.utils.ConvertToInt;
import main.java.edu.miu.cs.cs525.reversi.utils.ConvertToString;

public class Location
{
    public int row, column ;
    Convert convert;

    public Location()
    {
        row = 0 ;
        column = 0 ;
    }

    public Location( Location src )
    {
        row = src.row ;
        column = src.column ;
    }

    public Location( int ir, int ic )
    {
        row = ir ;
        column = ic ;
    }

    public void set( Location src )
    {
        row = src.row ;
        column = src.column ;
    }

    public void set( int ir, int ic )
    {
        row = ir ;
        column = ic ;
    }

    public void set( String standardForm )
    {
    	convert = new ConvertToInt();
    	column = convert.stringToInt(standardForm.charAt( 0 ));
        row = (int)(standardForm.charAt( 1 )) - 49 ;
    }

    public Location( String standardForm )
    {
        set( standardForm ) ;
    }

    public String getStandardForm()
    {
        String s = "" ;
        convert = new ConvertToString();
        s = convert.intToString(column);
        s = s + ( row + 1 ) ;
        return s ;
    }

    public static Location add( Location x , Location y )
    {
        Location result = new Location(x);
        result.row += y.row ;
        result.column += y.column ;
        return result;
    }
}
