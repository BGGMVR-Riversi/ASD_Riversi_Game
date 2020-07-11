package main.java.edu.miu.cs.cs525.reversi.common ;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;
import main.java.edu.miu.cs.cs525.reversi.monitor.ShowCurrentPlayer;

public class BoardInfo
{
    public static int ROW_COUNT = 8 ;
    public static int COL_COUNT = 8 ;
    public static int PLAYER_BLACK = 1 ;
    public static int PLAYER_WHITE = 31 ;
    public static int EMPTY = 0 ;
    public static int NO_GAME = -1 ;
    public static int GAME_OVER = -2 ;

    // Board :
    public int[][] b = new int[ROW_COUNT][COL_COUNT] ;

    // Which Player has Turn ? ( PLAYER_BLACK , PLAYER_WHITE , NO_GAME , GAME_OVER )
    public int turn ;

    // Move History :
    public static int MAX_MOVES = ROW_COUNT * COL_COUNT-4 ;
    public Location[] moveHistory = new Location[MAX_MOVES+2] ;
    public int[] turnHistory = new int[MAX_MOVES+2] ;
    public int[][][] boardHistory = new int[MAX_MOVES][ROW_COUNT][COL_COUNT] ;
    public int moveCount ;
    public int validMoveCount ;

    public BoardInfo()
    {
        int i ;
        for( i = 0 ; i < MAX_MOVES ; i++ ) {
            moveHistory[i] = new Location() ;
        }
        makeEmpty() ;
    }

    public BoardInfo( BoardInfo src )
    {
        this() ;
        copyBoard( src.b, this.b ) ;
        turn = src.turn ;
        int i ;
        for( i = 0 ; i < MAX_MOVES ; i++ ) {
            moveHistory[i].set( src.moveHistory[i] ) ;
            turnHistory[i] = src.turnHistory[i] ;
            copyBoard( src.boardHistory[i], this.boardHistory[i] ) ;
        }
        moveCount = src.moveCount ;
        validMoveCount = src.validMoveCount ;
    }
    
    public void changePlayer (ShowCurrentPlayer showCurrentPlayer){
        ReversiSingleton.showCurrentPlayer = showCurrentPlayer;
    }

    public static void copyBoard( int[][] src, int[][] dest )
    {
        int i, j ;
        for( i = 0 ; i < ROW_COUNT ; i++ ) {
            for( j = 0 ; j < COL_COUNT ; j++ ) {
                dest[i][j] = src[i][j] ;
            }
        }
    }

    public void makeEmpty()
    {
        int i, j ;
        for( i = 0 ; i < ROW_COUNT ; i++ ) {
            for( j = 0 ; j < COL_COUNT ; j++ ) {
                b[i][j] = 0 ;
            }
        }
        moveCount = 0 ;
        validMoveCount = 0 ;
        turn = NO_GAME ;
    }

    public void initBoard()
    {
        makeEmpty() ;
        b[3][3] = PLAYER_WHITE ;
        b[3][4] = PLAYER_BLACK ;
        b[4][4] = PLAYER_WHITE ;
        b[4][3] = PLAYER_BLACK ;
        turn = PLAYER_BLACK ;
    }

    private void goInADir( AnimationMatrix am, int rS, int cS, int rD, int cD )
    {
        int a = ( turn == PLAYER_BLACK ? -1 : +1 ) ;
        int rN = rS + rD ;
        int cN = cS + cD ;
        if( rN < 0 || rN >= ROW_COUNT || cN < 0 || cN >= COL_COUNT || b[rN][cN] == turn || b[rN][cN] == EMPTY ) {
            return ;
        }
        am.set( rN, cN, a ) ;
        goInADir( am, rN, cN, rD, cD ) ;
    }

    private boolean checkInADir( int rS, int cS, int rD, int cD )
    {
        int rN = rS + rD ;
        int cN = cS + cD ;
        if( rN < 0 || rN >= ROW_COUNT || cN < 0 || cN >= COL_COUNT || b[rN][cN] == EMPTY ) {
            return false ;
        }
        else if( b[rN][cN] == turn && b[rS][cS] == turn ) {
            return false ;
        }
        else if( b[rN][cN] == turn ) {
            return true ;
        }
        else {
            return checkInADir( rN, cN, rD, cD ) ;
        }
    }

    public void changeTurn()
    {
        if( turn == PLAYER_BLACK ) {
            turn = PLAYER_WHITE ;
        }
        else {
            turn = PLAYER_BLACK ;
        }
    }

    public boolean isValidMove( int r, int c )
    {
        int iD, jD, dCount = 0 ;
        if( turn == NO_GAME || turn == GAME_OVER || b[r][c] != EMPTY ) {
            return false ;
        }
        b[r][c] = turn ;
        for( iD = -1 ; iD <= +1 ; iD++ ) {
            for( jD = -1 ; jD <= +1 ; jD++ ) {
                if( iD == 0 && jD == 0 ) {
                    continue ;
                }
                if( checkInADir( r, c, iD, jD ) ) {
                    dCount++ ;
                }
            }
        }
        b[r][c] = EMPTY ;
        if( dCount == 0 ) {
            return false ;
        }
        else {
            return true ;
        }
    }

    public AnimationMatrix calculateMoveAnimation( int r, int c )
    {
        AnimationMatrix anim = new AnimationMatrix() ;
        int iD, jD, dCount = 0 ;

        if( turn == NO_GAME || turn == GAME_OVER || b[r][c] != EMPTY ) {
            return anim ;
        }
        copyBoardToHistory( moveCount ) ;
        b[r][c] = turn ;
        for( iD = -1 ; iD <= +1 ; iD++ ) {
            for( jD = -1 ; jD <= +1 ; jD++ ) {
                if( iD == 0 && jD == 0 ) {
                    continue ;
                }
                if( checkInADir( r, c, iD, jD ) ) {
                    dCount++ ;
                    goInADir( anim, r, c, iD, jD ) ;
                }
            }
        }
        if( dCount == 0 ) {
            b[r][c] = EMPTY ;
            return new AnimationMatrix() ;
        }
        moveHistory[moveCount].c = c ;
        moveHistory[moveCount].r = r ;
        turnHistory[moveCount] = turn ;
//        System.out.println( moveHistory[moveCount].getStandardForm() ) ;
        moveCount++ ;
        validMoveCount = moveCount ;
        // changing turn for next move
        changeTurn() ;
        return anim ;
    }

    public int performMove( int r, int c )
    {
        // Performs a move with a trick :
        // calculates move animation & then performs animation at once !
        AnimationMatrix am = calculateMoveAnimation( r, c ) ;
        am.perform( this, 30 ) ;
        return correctTurn() ;
    }

    public int performMove( Location l )
    {
        return performMove( l.r, l.c ) ;
    }

    public String getTurnString()
    {
        if( turn == PLAYER_BLACK ) {
        	changePlayer(ReversiSingleton.blackPlayer);
            ReversiSingleton.showCurrentPlayer.display();
            return "Black's Turn" ;
        }
        else if( turn == PLAYER_WHITE ) {
        	changePlayer(ReversiSingleton.whitePlayer);
            ReversiSingleton.showCurrentPlayer.display();
            return "White's Turn" ;
        }
        else if( turn == NO_GAME ) {
            return "" ;
        }
        else if( turn == GAME_OVER ) {
            return "Game Over !!" ;
        }
        else {
            return "" ;
        }
    }

    public int getPieceCount( int player )
    {
        int c = 0 ;
        int i, j ;
        for( i = 0 ; i < ROW_COUNT ; i++ ) {
            for( j = 0 ; j < COL_COUNT ; j++ ) {
                if( b[i][j] == player ) {
                    c++ ;
                }
            }
        }
        return c ;
    }

    private int countInADir( int rS, int cS, int rD, int cD )
    {
        int rN = rS + rD ;
        int cN = cS + cD ;
        if( rN < 0 || rN >= ROW_COUNT || cN < 0 || cN >= COL_COUNT || b[rN][cN] == EMPTY ) {
            return 0 ;
        }
        else if( b[rN][cN] == turn && b[rS][cS] == turn ) {
            return 0 ;
        }
        else if( b[rN][cN] == turn ) {
            return 0 ;
        }
        else {
            return 1 + countInADir( rN, cN, rD, cD ) ;
        }
    }

    public BoardMatrix getGainMatrix()
    {
        BoardMatrix m = new BoardMatrix() ;
        int r, c, iD, jD ;

        if( turn == NO_GAME || turn == GAME_OVER ) {
            return m ; // No Game
        }
        for( r = 0 ; r < ROW_COUNT ; r++ ) {
            for( c = 0 ; c < COL_COUNT ; c++ ) {
                if( b[r][c] != EMPTY ) {
                    m.set( r, c, -1 ) ;
                }
                else {
                    b[r][c] = turn ;
                    for( iD = -1 ; iD <= +1 ; iD++ ) {
                        for( jD = -1 ; jD <= +1 ; jD++ ) {
                            if( iD == 0 && jD == 0 ) {
                                continue ;
                            }
                            if( checkInADir( r, c, iD, jD ) ) {
                                m.set( r, c, m.get( r, c ) + countInADir( r, c, iD, jD ) ) ;
                            }
                        }
                    }
                    b[r][c] = EMPTY ;
                }
            }
        }
        return m ;
    }

    public BoardMatrix getPossibleMovesMatrix()
    {
        BoardMatrix m = getGainMatrix() ;
        int i, j ;
        for( i = 0 ; i < m.nr ; i++ ) {
            for( j = 0 ; j < m.nc ; j++ ) {
                if( m.get( i, j ) > 0 ) {
                    m.set( i, j, 1 ) ;
                }
                else {
                    m.set( i, j, 0 ) ;
                }
            }
        }
        return m ;
    }

    public int correctTurn()
    {
        BoardMatrix m = getGainMatrix() ;
        if( m.getMax() > 0 ) {
            return 0 ; // No Correction
        }
        changeTurn() ;
        m = getGainMatrix() ;
        if( m.getMax() > 0 ) {
            return 1 ; // 1 Correction
        }
        turn = GAME_OVER ;
        return 2 ; // Game is Over
    }

    // Move History methods :

    public void copyBoardToHistory( int k )
    {
        copyBoard( this.b, this.boardHistory[k] ) ;
    }

    public void copyHistoryToBoard( int k )
    {
        copyBoard( this.boardHistory[k], this.b ) ;
    }

    public boolean takeBackOneMove()
    {
        if( moveCount <= 0 ) {
            return false ;
        }
        moveCount-- ;
        copyHistoryToBoard( moveCount ) ;
        turn = turnHistory[moveCount] ;
        return true ;
    }

    public void takeBackAllMoves()
    {
        if( validMoveCount <= 0 ) {
            return ;
        }
        moveCount = 0 ;
        copyHistoryToBoard( moveCount ) ;
        turn = turnHistory[moveCount] ;
    }

    public boolean redoOneMove()
    {
        if( validMoveCount <= 0 ) {
            return false ;
        }
        if( moveCount == validMoveCount ) {
            return false ;
        }
        if( moveCount == validMoveCount - 1 ) {
            performMove( moveHistory[moveCount].r, moveHistory[moveCount].c ) ;
        }
        else {
            moveCount++ ;
            copyHistoryToBoard( moveCount ) ;
            turn = turnHistory[moveCount] ;
        }
        return true ;
    }

    public void redoAllMoves()
    {
        if( validMoveCount <= 0 ) {
            return ;
        }
        moveCount = validMoveCount - 1 ;
        copyHistoryToBoard( moveCount ) ;
        turn = turnHistory[moveCount] ;
        redoOneMove() ;
    }

    public String getStandardFormGame()
    {
        String s = "" ;
        int i ;
        for( i = 0 ; i <moveCount ; i++ ) {
            s = s + moveHistory[i].getStandardForm() ;
            s = s + " " ;
        }
        return s ;
    }

    public void setStandardFormGame( String s )
    {
        String[] sa = s.split( " " ) ;
        int i ;
        initBoard() ;
        for( i = 0 ; i < sa.length ; i++ ) {
            if( sa[i].length() == 2 ) {
                performMove( new Location( sa[i].toUpperCase() ) ) ;
            }
        }
    }

    public void showBoard()
    {
        System.out.println( "Current Board Position :" ) ;
        System.out.println( "  A B C D E F G H" ) ;
        int i, j ;
        for( i = 0 ; i < ROW_COUNT ; i++ ) {
            System.out.print( ( i + 1 ) + " " ) ;
            for( j = 0 ; j < COL_COUNT ; j++ ) {
                if( b[i][j] == EMPTY ) {
                    System.out.print( "- " ) ;
                }
                else if( b[i][j] == PLAYER_BLACK ) {
                    System.out.print( "X " ) ;
                }
                else if( b[i][j] == PLAYER_WHITE ) {
                    System.out.print( "O " ) ;
                }
                else {
                    System.out.print( "? " ) ;
                }
            }
            System.out.println() ;
        }
    }

    public String getPossibleMoves()
    {
        String s = "" ;
        BoardMatrix m = getGainMatrix() ;
        int i, j ;
        for( i = 0 ; i < ROW_COUNT ; i++ ) {
            for( j = 0 ; j < COL_COUNT ; j++ ) {
                if( m.get( i, j ) > 0 ) {
                    Location l = new Location( i, j ) ;
                    s = s + l.getStandardForm() + "," ;
                }
            }
        }
        if( s.length() > 0 ) {
            s = s.substring( 0, s.length() - 1 ) ;
        }
        return s ;
    }

    public int countCornerStablePieces( int player, Location corner, int rMAx, int cMax )
    {
        int i, j, c = 0, rm = 0, cm = 0, rec = 0 ;
        boolean e ;
        Location dir = new Location( ( corner.r <= ROW_COUNT / 2 ? +1 : -1 ), ( corner.c <= COL_COUNT / 2 ? +1 : -1 ) ) ;
        if( b[corner.r][corner.c] == player ) {
            c++ ;
            i = corner.r + dir.r ;
            e = false ;
            while( !e ) {
                if( dir.r == +1 && i >= rMAx ) {
                    break ;
                }
                if( dir.r == -1 && i <= rMAx ) {
                    break ;
                }
                if( b[i][corner.c] == player ) {
                    c++ ;
                    rec = 1 ;
                    rm = i ;
                }
                else {
                    e = true ;
                }
                i = i + dir.r ;
            }
            j = corner.c + dir.c ;
            e = false ;
            while( !e ) {
                if( dir.c == +1 && j >= cMax ) {
                    break ;
                }
                if( dir.c == -1 && j <= cMax ) {
                    break ;
                }
                if( b[corner.r][j] == player ) {
                    c++ ;
                    rec++ ;
                    cm = j ;
                }
                else {
                    e = true ;
                }
                j = j + dir.c ;
            }
            if( rec > 1 ) {
                Location newCorner = new Location() ;
                newCorner.r = corner.r + dir.r ;
                newCorner.c = corner.c + dir.c ;
                c += countCornerStablePieces( player, newCorner, rm, cm ) ;
            }
        }
        return c ;
    }

    private int countRepetitions( int player, Location corner1, Location corner2, int n )
    {
        int c = 0 ;
        Location dir = new Location() ;
        Location iter = new Location() ;
        if( corner1.r == corner2.r ) {
            dir.set( 0, 1 ) ;
        }
        else {
            dir.set( 1, 0 ) ;
        }
        for( iter.set( corner1 ) ; iter.r <= corner2.r && iter.c <= corner2.c ; iter = Location.add( iter, dir ) ) {
            if( b[iter.r][iter.c] == player ) {
                c++ ;
            }
            else {
                return 0 ;
            }
        }
        if( n < 3 ) {
            int tmp = dir.r ;
            dir.r = dir.c ;
            dir.c = tmp ;
            if( corner2.r == 7 && corner2.c == 7 ) {
                dir.r = -dir.r ;
                dir.c = -dir.c ;
            }
            tmp = countRepetitions( player, Location.add( corner1, dir ), Location.add( corner2, dir ), n + 1 ) ;
            if( tmp > 0 ) {
                c += tmp - 2 * n ;
            }
        }
        return c ;
    }

    public int countStablePieces( int player )
    {
        int sum = 0 ;
        sum += countCornerStablePieces( player, new Location( 0, 0 ), ROW_COUNT, COL_COUNT ) ;
        sum += countCornerStablePieces( player, new Location( 0, 7 ), ROW_COUNT, -1 ) ;
        sum += countCornerStablePieces( player, new Location( 7, 0 ), -1, COL_COUNT ) ;
        sum += countCornerStablePieces( player, new Location( 7, 7 ), -1, -1 ) ;
        sum -= countRepetitions( player, new Location( 0, 0 ), new Location( 0, 7 ), 1 ) ;
        sum -= countRepetitions( player, new Location( 0, 0 ), new Location( 7, 0 ), 1 ) ;
        sum -= countRepetitions( player, new Location( 0, 7 ), new Location( 7, 7 ), 1 ) ;
        sum -= countRepetitions( player, new Location( 7, 0 ), new Location( 7, 7 ), 1 ) ;
        return sum ;
    }

    public static int getOpponent( int player )
    {
        if( player == PLAYER_BLACK ) {
            return PLAYER_WHITE ;
        }
        else {
            return PLAYER_BLACK ;
        }
    }

    private boolean isEmpty( int i, int j )
    {
        if( i < 0 || i >= ROW_COUNT || j < 0 || j >= COL_COUNT ) {
            return false ;
        }
        else if( b[i][j] == EMPTY ) {
            return true ;
        }
        else {
            return false ;
        }
    }

    public int countFrontier( int player )
    {
        int f = 0 ;
        int i, j ;
        for( i = 0 ; i < ROW_COUNT ; i++ ) {
            for( j = 0 ; j < COL_COUNT ; j++ ) {
                if( b[i][j] == player ) {
                    if( isEmpty( i + 1, j ) ) {
                        f++ ;
                    }
                    if( isEmpty( i - 1, j ) ) {
                        f++ ;
                    }
                    if( isEmpty( i, j + 1 ) ) {
                        f++ ;
                    }
                    if( isEmpty( i, j - 1 ) ) {
                        f++ ;
                    }
                    if( isEmpty( i + 1, j + 1 ) ) {
                        f++ ;
                    }
                    if( isEmpty( i + 1, j - 1 ) ) {
                        f++ ;
                    }
                    if( isEmpty( i - 1, j + 1 ) ) {
                        f++ ;
                    }
                    if( isEmpty( i - 1, j - 1 ) ) {
                        f++ ;
                    }
                }
            }
        }
        return f ;
    }

}
