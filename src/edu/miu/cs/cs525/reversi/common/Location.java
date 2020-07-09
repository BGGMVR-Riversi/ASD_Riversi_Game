package edu.miu.cs.cs525.reversi.common ;

public class Location
{
    public int r, c ;

    public Location()
    {
        r = 0 ;
        c = 0 ;
    }

    public Location( Location src )
    {
        r = src.r ;
        c = src.c ;
    }

    public Location( int ir, int ic )
    {
        r = ir ;
        c = ic ;
    }

    public void set( Location src )
    {
        r = src.r ;
        c = src.c ;
    }

    public void set( int ir, int ic )
    {
        r = ir ;
        c = ic ;
    }

    public void set( String standardForm )
    {
        switch( standardForm.charAt( 0 ) ) {
            case 'A':
                c = 0 ;
                break ;
            case 'B':
                c = 1 ;
                break ;
            case 'C':
                c = 2 ;
                break ;
            case 'D':
                c = 3 ;
                break ;
            case 'E':
                c = 4 ;
                break ;
            case 'F':
                c = 5 ;
                break ;
            case 'G':
                c = 6 ;
                break ;
            case 'H':
                c = 7 ;
                break ;
        }
        r = ( int ) ( standardForm.charAt( 1 ) ) - 49 ;
    }

    public Location( String standardForm )
    {
        set( standardForm ) ;
    }

    public String getStandardForm()
    {
        String s = "" ;
        switch( c ) {
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
        s = s + ( r + 1 ) ;
        return s ;
    }

    public static Location add( Location x , Location y )
    {
        Location result = new Location(x);
        result.r += y.r ;
        result.c += y.c ;
        return result;
    }
}
