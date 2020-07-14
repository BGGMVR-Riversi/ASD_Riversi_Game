package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;
import main.java.edu.miu.cs.cs525.reversi.utils.ImageClass;

public class BoardViewTemplate extends AbstractBoardView {
	
	ImageClass imgClass = new ImageClass();
	
	@Override
	public void pauseGame() {
		ReversiSingleton.getPauseButton().setIcon(imgClass.getPlayImg());
		ReversiSingleton.getFirstButton().setEnabled(true);
		ReversiSingleton.getPrevButton().setEnabled(true);
		ReversiSingleton.getNextButton().setEnabled(true);
		ReversiSingleton.getLastButton().setEnabled(true);
		ReversiSingleton.getPauseButton().setToolTipText("Continue Game");
		ReversiSingleton.getBoardView().gamePaused = true;
	}

	@Override
	public void newGame() {
		ReversiSingleton.getBoardView().board.initBoard();
		ReversiSingleton.getMainForm().repaint();
		ReversiSingleton.getPauseButton().setEnabled(true);
		continueGame();
	}

	@Override
	public void continueGame() {
		ReversiSingleton.getPauseButton().setIcon(imgClass.getPauseImg());
		ReversiSingleton.getFirstButton().setEnabled(false);
		ReversiSingleton.getPrevButton().setEnabled(false);
		ReversiSingleton.getNextButton().setEnabled(false);
		ReversiSingleton.getLastButton().setEnabled(false);
		ReversiSingleton.getPauseButton().setToolTipText("Pause Game");
		ReversiSingleton.getBoardView().gamePaused = false;
		ReversiSingleton.getBoardView().updateTurn();
	}

	@Override
	void pauseButton_actionPerformed(ActionEvent e) {
		if (ReversiSingleton.getPauseButton().getToolTipText().equals("Pause Game")) {
			pauseGame();
		} else {
			continueGame();
		}
	}

	@Override
	void prevButton_actionPerformed(ActionEvent e) {
		if (ReversiSingleton.getBoardView().timer.isRunning()) {
			return;
		}
		ReversiSingleton.getBoardView().board.takeBackOneMove();
		ReversiSingleton.getBoardView().updateTurn();
		ReversiSingleton.getMainForm().repaint();
	}

	@Override
	void nextButton_actionPerformed(ActionEvent e) {
		if (ReversiSingleton.getBoardView().timer.isRunning()) {
			return;
		}
		ReversiSingleton.getBoardView().board.redoOneMove();
		ReversiSingleton.getBoardView().updateTurn();
		ReversiSingleton.getMainForm().repaint();
	}

	@Override
	void firstButton_actionPerformed(ActionEvent e) {
		if (ReversiSingleton.getBoardView().timer.isRunning()) {
			return;
		}
		ReversiSingleton.getBoardView().board.takeBackAllMoves();
		ReversiSingleton.getBoardView().updateTurn();
		ReversiSingleton.getMainForm().repaint();
	}

	@Override
	void lastButton_actionPerformed(ActionEvent e) {
		if (ReversiSingleton.getBoardView().timer.isRunning()) {
			return;
		}
		ReversiSingleton.getBoardView().board.redoAllMoves();
		ReversiSingleton.getBoardView().updateTurn();
		ReversiSingleton.getMainForm().repaint();
	}
}
