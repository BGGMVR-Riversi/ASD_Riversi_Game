package main.java.edu.miu.cs.cs525.reversi.common ;

public class Location
{
    public int row, column ;

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
        switch( standardForm.charAt( 0 ) ) {
            case 'A':
                column = 0 ;
                break ;
            case 'B':
                column = 1 ;
                break ;
            case 'C':
                column = 2 ;
                break ;
            case 'D':
                column = 3 ;
                break ;
            case 'E':
                column = 4 ;
                break ;
            case 'F':
                column = 5 ;
                break ;
            case 'G':
                column = 6 ;
                break ;
            case 'H':
                column = 7 ;
                break ;
        }
        row = ( int ) ( standardForm.charAt( 1 ) ) - 49 ;
    }

    public Location( String standardForm )
    {
        set( standardForm ) ;
    }

    public String getStandardForm()
    {
        String s = "" ;
        switch( column ) {
            case 0:
                s = "A" ;
                break ;
            case 1:
                s = "B" ;
                break ;
            case 2:
                s = "C" ;
                break ;
            case 3:
                s = "D" ;
                break ;
            case 4:
                s = "E" ;
                break ;
            case 5:
                s = "F" ;
                break ;
            case 6:
                s = "G" ;
                break ;
            case 7:
                s = "H" ;
                break ;
        }
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
