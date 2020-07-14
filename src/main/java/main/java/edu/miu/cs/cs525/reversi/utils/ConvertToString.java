package main.java.edu.miu.cs.cs525.reversi.utils;

public class ConvertToString implements Convert{

	@Override
	public int stringToInt(char s) {
		return 0;
	}

	@Override
	public String intToString(int n) {
		String result = "" ;
        switch( n ) {
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

}
