package main.java.edu.miu.cs.cs525.reversi.monitor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;
import main.java.edu.miu.cs.cs525.reversi.action_adapters.BoardView_this_mouseAdapter;
import main.java.edu.miu.cs.cs525.reversi.action_adapters.ReversiActionEventFactory;
import main.java.edu.miu.cs.cs525.reversi.common.AnimationMatrix;
import main.java.edu.miu.cs.cs525.reversi.common.BoardInfo;
import main.java.edu.miu.cs.cs525.reversi.common.BoardMatrix;
import main.java.edu.miu.cs.cs525.reversi.common.GeneralPlayer;
import main.java.edu.miu.cs.cs525.reversi.common.Location;
import main.java.edu.miu.cs.cs525.reversi.mediator.BoardEnum;

public class BoardView extends JPanel {

	private static final long serialVersionUID = 1L;
	public BoardInfo board = BoardInfo.boardInfoFactory1(); // Game Board Information
	public AnimationMatrix am = AnimationMatrix.animationMatrixFactory1(); // Animation Direction for each cell : -1 , 0 , +1
	public int animationSpeed = 5;
	public GeneralPlayer playerBPointer;
	public GeneralPlayer playerWPointer;
	public boolean gamePaused = true;
	public ImageIcon pieces = new ImageIcon();
	public ImageIcon border = new ImageIcon();
	public Timer timer;
	public Timer pauseBeforeMoveTimer;
	public JLabel statusBar = new JLabel();
	public JLabel lblScoreBlack = new JLabel();
	public JLabel lblScoreWhite = new JLabel();
	TitledBorder titledBorder1;
	BorderLayout borderLayout1 = new BorderLayout();
	public MoveList mlPointer;
	public JFrame parent;
	public int deltaX = 0, deltaY = 0;
	public int boardBorder;
	
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(200, 200, 200));
		g.setFont(new Font("Default", 1, 12));
		int x, y, sx, sy, b;
		BoardMatrix m = BoardMatrix.boardMatrixFactory1();
		if (!timer.isRunning()) {
			m = board.getGainMatrix();
		}
		g.drawImage(border.getImage(), deltaX, deltaY, this);
		for (int i = 0; i < BoardEnum.ROW_COUNT.value(); i++) {
			for (int j = 0; j < BoardEnum.COL_COUNT.value(); j++) {
				x = deltaX + 31 + j * 60;
				y = deltaY + 31 + i * 60;
				b = board.board[i][j];
				sx = (b % 8) * 60;
				sy = (b / 8) * 60;
				g.drawImage(pieces.getImage(), x, y, x + 60, y + 60, sx, sy, sx + 60, sy + 60, this);
				if (!timer.isRunning()) {
					b = m.get(i, j);
					if (b != 0 && b != -1) {
						g.drawString("" + b, x + 25, y + 35);
					}
				}
			}
		}
	}

	public BoardView(MoveList ml, JFrame parent, int boardBorderSelector) {
		try {
			boardBorder = boardBorderSelector;
			mlPointer = ml;
			this.parent = parent;
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		ReversiActionEventFactory.getActionPerformed("BoardView_Timer_Action");
		timer = new Timer(50, ReversiActionEventFactory.ac.initializeInstance(this));

		ReversiActionEventFactory.getActionPerformed("BoardView_PBMTimer_Action");
		pauseBeforeMoveTimer = new Timer(100, ReversiActionEventFactory.ac.initializeInstance(this));

		this.addMouseListener(BoardView_this_mouseAdapter.mouseAdapterFactory(this));
		pieces = new ImageIcon(MainForm.class.getResource("../images/standard-1.png"));
		border = new ImageIcon(MainForm.class.getResource("../images/540px-board-" + boardBorder + ".png"));
		setLayout(borderLayout1);
		titledBorder1 = new TitledBorder("");
		statusBar.setBorder(titledBorder1);
		statusBar.setText(" ");
		add(statusBar, BorderLayout.SOUTH);
	}

	/*
	 * public void updatePassTurn(BoardInfo board) { NetworkPlayer.getMove1(board);
	 * }
	 */
	public void updateTurn() {
		if (mlPointer != null) {
			mlPointer.updateMoveList(board);
		}
		if (board.turn == BoardEnum.NO_GAME.value()) {
			statusBar.setText("To Start New Game choose Game | New.");
			return;
		}
		String s = board.getTurnString();
		s += " ( ";
		lblScoreBlack.setText("Score: " + board.getPieceCount(BoardEnum.PLAYER_BLACK.value()));
		lblScoreBlack.setForeground(ReversiSingleton.getAqua());
		ReversiSingleton.getLeftPane().add(lblScoreBlack);

		lblScoreWhite.setText("Score: " + board.getPieceCount(BoardEnum.PLAYER_WHITE.value()));
		lblScoreWhite.setForeground(ReversiSingleton.getAqua());
		ReversiSingleton.getRightPane().add(lblScoreWhite);

		ReversiSingleton.getLeftPane().add(ReversiSingleton.getBlackTurn());
		ReversiSingleton.getRightPane().add(ReversiSingleton.getWhiteTurn());
		ReversiSingleton.getWinner().setVisible(false);
		ReversiSingleton.getGameOverLabel().setVisible(false);
		s += " ) ";
		statusBar.setText(s);
		if (gamePaused) {
			return;
		}
		if (board.turn == BoardEnum.PLAYER_BLACK.value() && playerBPointer != null) {

			pauseBeforeMoveTimer.start();
		} else if (board.turn == BoardEnum.PLAYER_WHITE.value() && playerWPointer != null) {
			pauseBeforeMoveTimer.start();
		}
		/*if ((ComputerPlayer.counterComputer!=NetworkPlayer.counterNetwork+1)) {
			System.out.println(" counterComputer " + ComputerPlayer.counterComputer);
			System.out.println(" counterNetwork " + NetworkPlayer.counterNetwork);
			updatePassTurn(board);
		}*/

	}

	public void startMove(Location move) {
		if (gamePaused) {
			return;
		}
		if (move.row < 0 || move.row > 7 || move.column < 0 || move.column > 7) {
			return;
		}
		if (!board.isValidMove(move.row, move.column)) {
			System.err.println("Please select a valid move! Col: " + move.column + " Row: " + move.row);
			return;
		}
		am = board.calculateMoveAnimation(move.row, move.column);
		repaint();
		timer.start();
	}

	public void this_mouseClicked(MouseEvent e) {
		if (timer.isRunning()) {
			return;
		}
		if (board.turn == BoardEnum.NO_GAME.value() || board.turn == BoardEnum.GAME_OVER.value()) {
			statusBar.setText("To Start New Game choose Game | New.");
			return;
		}
		int r, c, br, bc;
		r = (e.getY() - 30 - deltaY) / 60;
		c = (e.getX() - 30 - deltaX) / 60;
		br = (e.getY() - 30 - deltaY) % 60;
		bc = (e.getX() - 30 - deltaX) % 60;
		if (Math.abs(br - 30) > 28 || Math.abs(bc - 30) > 28) {
			return;
		}
		if (board.turn == BoardEnum.PLAYER_BLACK.value() && playerBPointer == null) {
			startMove(Location.locationFactory3(r, c));
		} else if (board.turn == BoardEnum.PLAYER_WHITE.value() && playerWPointer == null) {
			startMove(Location.locationFactory3(r, c));
		}
	}

	public void PBMTimer_actionPerformed(ActionEvent e) {
		pauseBeforeMoveTimer.stop();
		if (board.turn == BoardEnum.PLAYER_BLACK.value() && playerBPointer != null) {
			startMove(playerBPointer.getMove(board));
		} else if (board.turn == BoardEnum.PLAYER_WHITE.value() && playerWPointer != null) {
			startMove(playerWPointer.getMove(board));
		}
	}

	public void timer_actionPerformed(ActionEvent e) {
		int count = am.perform(board, animationSpeed);
		repaint();
		if (count == BoardEnum.ROW_COUNT.value() * BoardEnum.COL_COUNT.value()) {
			timer.stop();
			if (board.correctTurn() == 2) {
				String s = "Game Over";
				int p1 = board.getPieceCount(BoardEnum.PLAYER_BLACK.value());
				int p2 = board.getPieceCount(BoardEnum.PLAYER_WHITE.value());
				s += " ( ";
				s += p1 + " : " + p2;
				lblScoreBlack.setText("Score: " + p1);
				lblScoreWhite.setText("Score: " + p2);
				s += " ) ";
				if (p1 == p2 && board.turn == BoardEnum.PLAYER_BLACK.value()) {
					s += " It's a Draw ! ";
					playerBPointer.getMove(board);

				} else if (p1 == p2 && board.turn == BoardEnum.PLAYER_WHITE.value()) {
					s += " It's a Draw ! ";
					playerWPointer.getMove(board);

				} else if (p1 > p2) {
					s += " Black is Winner ! ";
					ReversiSingleton.setCurrentPlayer(new BlackPlayer());
					ReversiSingleton.getCurrentPlayer().displayWinner();
					playerBPointer.getMove(board);
				} else {
					s += " White is Winner ! ";
					ReversiSingleton.setCurrentPlayer(new WhitePlayer());
					ReversiSingleton.getCurrentPlayer().displayWinner();
					playerWPointer.getMove(board);
				}
				statusBar.setText(s);
				if (mlPointer != null) {
					mlPointer.updateMoveList(board);
				}

			} else {
				updateTurn();
			}
		}
	}
}
