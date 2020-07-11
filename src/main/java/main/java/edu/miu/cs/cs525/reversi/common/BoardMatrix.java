package main.java.edu.miu.cs.cs525.reversi.common ;

import java.util.Random ;

public class BoardMatrix
{
    public static int nr = BoardInfo.ROW_COUNT ;
    public static int nc = BoardInfo.COL_COUNT ;
    private int[][] mat = new int[nr][nc] ;

    public BoardMatrix()
    {
        int i, j ;
        for( i = 0 ; i < nr ; i++ ) {
            for( j = 0 ; j < nc ; j++ ) {
                mat[i][j] = 0 ;
            }
        }
    }

    public BoardMatrix( BoardMatrix src )
    {
        int i, j ;
        for( i = 0 ; i < nr ; i++ ) {
            for( j = 0 ; j < nc ; j++ ) {
                mat[i][j] = src.mat[i][j] ;
            }
        }
    }

    public BoardMatrix( int[][] src )
    {
        int i, j ;
        for( i = 0 ; i < nr ; i++ ) {
            for( j = 0 ; j < nc ; j++ ) {
                mat[i][j] = src[i][j] ;
            }
        }
    }

    public void set( int i, int j, int a )
    {
        mat[i][j] = a ;
    }

    public int get( int i, int j )
    {
        return mat[i][j] ;
    }

    public void print()
    {
        int i, j ;
        for( i = 0 ; i < nr ; i++ ) {
            for( j = 0 ; j < nc ; j++ ) {
                System.out.print( mat[i][j] + "\t" ) ;
            }
            System.out.println() ;
        }
        System.out.println() ;
    }

    public int getMax()
    {
        int max = mat[0][0] ;
        int i, j ;
        for( i = 0 ; i < nr ; i++ ) {
            for( j = 0 ; j < nc ; j++ ) {
                if( mat[i][j] > max ) {
                    max = mat[i][j] ;
                }
            }
        }
        return max ;
    }

    public Location getMaxLoc()
    {
        Location l = new Location() ;
        int max = mat[0][0] ;
        int i, j ;
        for( i = 0 ; i < nr ; i++ ) {
            for( j = 0 ; j < nc ; j++ ) {
                if( mat[i][j] > max ) {
                    max = mat[i][j] ;
                    l.r = i ;
                    l.c = j ;
                }
            }
        }
        return l ;
    }

    public BoardMatrix multiply( BoardMatrix b )
    {
        BoardMatrix r = new BoardMatrix() ;
        int i, j ;
        for( i = 0 ; i < nr ; i++ ) {
            for( j = 0 ; j < nc ; j++ ) {
                r.mat[i][j] = mat[i][j] * b.mat[i][j] ;
            }
        }
        return r ;
    }

    public static BoardMatrix getRandomMatrix( int min, int max )
    {
        BoardMatrix m = new BoardMatrix() ;
        Random rg = new Random( System.currentTimeMillis() ) ;
        int i, j ;
        for( i = 0 ; i < nr ; i++ ) {
            for( j = 0 ; j < nc ; j++ ) {
                m.mat[i][j] = min + rg.nextInt( max ) ;
            }
        }
        return m ;
    }

}
