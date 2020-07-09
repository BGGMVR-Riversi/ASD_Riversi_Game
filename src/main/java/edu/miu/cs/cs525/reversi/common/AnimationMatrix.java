package edu.miu.cs.cs525.reversi.common ;

public class AnimationMatrix
{
    public static int nr = BoardInfo.ROW_COUNT ;
    public static int nc = BoardInfo.COL_COUNT ;
    private int[][] mat = new int[nr][nc] ;

    public AnimationMatrix()
    {
        int i, j ;
        for( i = 0 ; i < nr ; i++ ) {
            for( j = 0 ; j < nc ; j++ ) {
                mat[i][j] = 0 ;
            }
        }
    }

    public AnimationMatrix( AnimationMatrix src )
    {
        int i, j ;
        for( i = 0 ; i < nr ; i++ ) {
            for( j = 0 ; j < nc ; j++ ) {
                mat[i][j] = src.mat[i][j] ;
            }
        }
    }

    public void set( int i, int j, int a )
    {
        if( a == -1 || a == 0 || a == +1 ) {
            mat[i][j] = a ;
        }
    }

    public int get( int i, int j )
    {
        return mat[i][j] ;
    }

    public int perform( BoardInfo board, int animationSpeed )
    {
        int i, j, a, b, count = 0 ;
        for( i = 0 ; i < nr ; i++ ) {
            for( j = 0 ; j < nc ; j++ ) {
                a = get( i, j ) ;
                b = board.b[i][j] ;
                if( a == +1 && b < 31 ) {
                    board.b[i][j] += a * animationSpeed ;
                }
                else if( a == +1 && b == 31 ) {
                    count++ ;
                }
                else if( a == -1 && b > 1 ) {
                    board.b[i][j] += a * animationSpeed ;
                }
                else if( a == -1 && b == 1 ) {
                    count++ ;
                }
                else {
                    count++ ;
                }
            }
        }
        return count ;
    }

}
