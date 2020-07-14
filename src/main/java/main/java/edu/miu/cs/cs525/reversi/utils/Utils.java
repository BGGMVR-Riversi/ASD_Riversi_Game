
package main.java.edu.miu.cs.cs525.reversi.utils;

public class Utils {
    public String intToString(int input )
    {
        String result = "" ;
        switch( input ) {
            case 0:
                result = "A" ;
                break ;
            case 1:
                result = "B" ;
                break ;
            case 2:
                result = "C" ;
                break ;
            case 3:
                result = "D" ;
                break ;
            case 4:
                result = "E" ;
                break ;
            case 5:
                result = "F" ;
                break ;
            case 6:
                result= "G" ;
                break ;
            case 7:
                result = "H" ;
                break ;
        }
        return result ;
    }

    public int charToInt( char input )
    {
        int result = 0;
        switch( input ) {
            case 'A':
                result = 0 ;
                break ;
            case 'B':
                result = 1 ;
                break ;
            case 'C':
                result = 2 ;
                break ;
            case 'D':
                result = 3 ;
                break ;
            case 'E':
                result = 4 ;
                break ;
            case 'F':
                result = 5 ;
                break ;
            case 'G':
                result = 6 ;
                break ;
            case 'H':
                result = 7 ;
                break ;
        }
        return result;
    }
//    private boolean isEmpty( int i, int j )
//    {
//        if( i < 0 || i >= ROW_COUNT || j < 0 || j >= COL_COUNT ) {
//            return false ;
//        }
//        else if( b[i][j] == EMPTY ) {
//            return true ;
//        }
//        else {
//            return false ;
//        }
//    }
//
//    public int countFrontier( int player )
//    {
//        int f = 0 ;
//        int i, j ;
//        for( i = 0 ; i < ROW_COUNT ; i++ ) {
//            for( j = 0 ; j < COL_COUNT ; j++ ) {
//                if( b[i][j] == player ) {
//                    if( isEmpty( i + 1, j ) ) {
//                        f++ ;
//                    }
//                    if( isEmpty( i - 1, j ) ) {
//                        f++ ;
//                    }
//                    if( isEmpty( i, j + 1 ) ) {
//                        f++ ;
//                    }
//                    if( isEmpty( i, j - 1 ) ) {
//                        f++ ;
//                    }
//                    if( isEmpty( i + 1, j + 1 ) ) {
//                        f++ ;
//                    }
//                    if( isEmpty( i + 1, j - 1 ) ) {
//                        f++ ;
//                    }
//                    if( isEmpty( i - 1, j + 1 ) ) {
//                        f++ ;
//                    }
//                    if( isEmpty( i - 1, j - 1 ) ) {
//                        f++ ;
//                    }
//                }
//            }
//        }
//        return f ;
//    }
}
