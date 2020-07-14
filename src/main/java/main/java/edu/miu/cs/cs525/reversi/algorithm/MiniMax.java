package main.java.edu.miu.cs.cs525.reversi.algorithm ;

import java.util.logging.Logger;

import main.java.edu.miu.cs.cs525.reversi.common.*;
import main.java.edu.miu.cs.cs525.reversi.mediator.BoardEnum;

public class MiniMax extends Thread
{
	Logger logger =	Logger.getLogger(MiniMax.class.getSimpleName());
    int CUT_OFF ;
    int ME ;
    BoardInfo board ;
    String[] possibleMoves ;
    public float[] moves ;
    
    public MiniMax(BoardInfo board) {
    	this.board=board;
    }
    public MiniMax( int iCutOff, int player, BoardInfo board, String[] possibleMoves )
    {
        CUT_OFF = iCutOff ;
        ME = player ;
        this.board = board ;
        this.possibleMoves = possibleMoves ;
        moves = new float[possibleMoves.length] ;
    }

	public int getOpponent(int player) {
		if (player == BoardEnum.PLAYER_BLACK.value()) {
			return BoardEnum.PLAYER_WHITE.value();
		} else {
			return BoardEnum.PLAYER_BLACK.value();
		}
	}
	
    public float Evaluate( BoardInfo bs )
    {
        int mp = bs.getPieceCount( ME ) ;
        int op = 64 - bs.getPieceCount( BoardEnum.EMPTY.value() ) - mp ;
        if( bs.turn == BoardEnum.GAME_OVER.value() ) {
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
        int OPP = getOpponent( ME ) ;
        float eval = bs.countStablePieces( ME ) - bs.countStablePieces( OPP ) ;
        eval += ( bs.countFrontier( OPP ) - bs.countFrontier( ME ) ) / 8.0 ;
        return eval ;
        // *********************
    }
	public String getPossibleMoves() {
		String s = "";
		BoardMatrix m = board.getGainMatrix();
		int i, j;
		for (i = 0; i < BoardEnum.ROW_COUNT.value(); i++) {
			for (j = 0; j < BoardEnum.COL_COUNT.value(); j++) {
				if (m.get(i, j) > 0) {
					Location l = new Location(i, j);
					s = s + l.getStandardForm() + ",";
				}
			}
		}
		if (s.length() > 0) {
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}
    public float MaxValue( BoardInfo bs, int n, float alpha, float beta )
    {
        if( bs.turn == BoardEnum.GAME_OVER.value() || n == CUT_OFF ) {
            return Evaluate( bs ) ;
        }
        String poss ;
        String[] pl ;
        int i, t ;
        poss = getPossibleMoves() ;
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
        if( bs.turn == BoardEnum.GAME_OVER.value() || n == CUT_OFF ) {
            return Evaluate( bs ) ;
        }
        String poss ;
        String[] pl ;
        int i, t ;
        poss = getPossibleMoves() ;
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
        logger.info("Start Running MiniMax Thread..." );
        for( i = 0 ; i <possibleMoves.length ; i++ ) {
            board.performMove( new Location( possibleMoves[i] ) ) ;
            moves[i] = MinValue( board, 1, -3000, +3000 ) ;
            board.takeBackOneMove() ;
            System.out.println( "i = " + i + " Eval for " + possibleMoves[i] + " : " + moves[i]  );
        }
        logger.info("Running MiniMax Thread Finished."  );
    }

}
