package main.java.edu.miu.cs.cs525.reversi.utils;

public class ConvertToInt implements Convert{

	@Override
	public int stringToInt(char c) {
		int result = 0;
        switch( c ) {
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

	@Override
	public String intToString(int n) {
		return null;
	}

}
