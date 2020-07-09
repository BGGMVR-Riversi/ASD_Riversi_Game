package edu.miu.cs.cs525.reversi.algorithm ;

import edu.miu.cs.cs525.reversi.common.*;

public class MiniMax extends Thread
{
    int CUT_OFF ;
    int ME ;
    BoardInfo b ;
    String[] possibleMoves ;
    public float[] moves ;

    public MiniMax( int iCutOff, int player, BoardInfo b, String[] possibleMoves )
    {
        CUT_OFF = iCutOff ;
        ME = player ;
        this.b = b ;
        this.possibleMoves = possibleMoves ;
        moves = new float[possibleMoves.length] ;
    }

    public float Evaluate( BoardInfo bs )
    {
        int mp = bs.getPieceCount( ME ) ;
        int op = 64 - bs.getPieceCount( BoardInfo.EMPTY ) - mp ;
        if( bs.turn == BoardInfo.GAME_OVER ) {
            if( mp > op ) {
                return +100 + mp - op ;
            }
            else if( mp == op ) {
                return 0 ;
            }
            else {
                return -100 + mp - op ;
            }
        }
        // else : evaluate the board position
        // *********************
        int OPP = BoardInfo.getOpponent( ME ) ;
        float eval = bs.countStablePieces( ME ) - bs.countStablePieces( OPP ) ;
        eval += ( bs.countFrontier( OPP ) - bs.countFrontier( ME ) ) / 8.0 ;
        return eval ;
        // *********************
    }

    public float MaxValue( BoardInfo bs, int n, float alpha, float beta )
    {
        if( bs.turn == BoardInfo.GAME_OVER || n == CUT_OFF ) {
            return Evaluate( bs ) ;
        }
        String poss ;
        String[] pl ;
        int i, t ;
        poss = bs.getPossibleMoves() ;
        pl = poss.split( "," ) ;
        for( i = 0 ; i < pl.length ; i++ ) {
            t = bs.performMove( new Location( pl[i] ) ) ;
            if( t == 0 || t == 2 ) {
                alpha = Math.max( alpha, MinValue( bs, n + 1, alpha, beta ) ) ;
            }
            else if( t == 1 ) {
                //System.out.print( ":" ) ;
                //System.out.println( bs.getStandardFormGame() ) ;
                alpha = Math.max( alpha, MaxValue( bs, n + 1, alpha, beta ) ) ;
            }
            bs.takeBackOneMove() ;
            if( alpha >= beta ) {
                return beta ;
            }
        }
        return alpha ;
    }

    public float MinValue( BoardInfo bs, int n, float alpha, float beta )
    {
        if( bs.turn == BoardInfo.GAME_OVER || n == CUT_OFF ) {
            return Evaluate( bs ) ;
        }
        String poss ;
        String[] pl ;
        int i, t ;
        poss = bs.getPossibleMoves() ;
        pl = poss.split( "," ) ;
        for( i = 0 ; i < pl.length ; i++ ) {
            t = bs.performMove( new Location( pl[i] ) ) ;
            if( t == 0 || t == 2 ) {
                beta = Math.min( beta, MaxValue( bs, n + 1, alpha, beta ) ) ;
            }
            else if( t == 1 ) {
                //System.out.print( ":" ) ;
                //System.out.println( bs.getStandardFormGame() ) ;
                beta = Math.min( beta, MinValue( bs, n + 1, alpha, beta ) ) ;
            }
            bs.takeBackOneMove() ;
            if( beta <= alpha ) {
                return alpha ;
            }
        }
        return beta ;
    }

    public void run()
    {
        int i ;
        System.out.println( "Start Running MiniMax Thread..." ) ;
        for( i = 0 ; i <possibleMoves.length ; i++ ) {
            b.performMove( new Location( possibleMoves[i] ) ) ;
            moves[i] = MinValue( b, 1, -3000, +3000 ) ;
            b.takeBackOneMove() ;
            System.out.println( "i = " + i + " Eval for " + possibleMoves[i] + " : " + moves[i] ) ;
        }
        System.out.println( "Running MiniMax Thread Finished." ) ;
    }

}
