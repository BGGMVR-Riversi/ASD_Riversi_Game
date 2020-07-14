package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.event.ActionEvent;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;
import main.java.edu.miu.cs.cs525.reversi.memento.Board;
import main.java.edu.miu.cs.cs525.reversi.memento.History;
import main.java.edu.miu.cs.cs525.reversi.utils.ImageClass;

public class BoardViewTemplate extends Abstract {
	
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
			if (History.getInstance().size("redo") > 0) {
				System.out.println("!!Clearing all redo history!!");
				History.getInstance().clearRedoHistory();
			}
		}
	}

	@Override
	void prevButton_actionPerformed(ActionEvent e) {
		if (ReversiSingleton.getBoardView().timer.isRunning()) {
			return;
		}
		ReversiSingleton.getBoardView().board.takeBackOneMove();
		if (History.getInstance().size("undo") <= 0) {
			System.out.println("Nothing to undo from Memento");
		} else {
			Board.getInstance().restore(History.getInstance().popFromUndoList());
		}
		System.out.println("undo from Memento ==========> " + Board.getInstance().getMove());
		ReversiSingleton.getBoardView().updateTurn();
		ReversiSingleton.getMainForm().repaint();
	}

	@Override
	void nextButton_actionPerformed(ActionEvent e) {
		if (ReversiSingleton.getBoardView().timer.isRunning()) {
			return;
		}
		ReversiSingleton.getBoardView().board.redoOneMove();
		if (History.getInstance().size("redo") <= 0) {
			System.out.println("Nothing to redo from Memento");
		} else {
			Board.getInstance().restore(History.getInstance().popFromRedoList());
		}
		System.out.println("redo from Memento ==========> " + Board.getInstance().getMove());
		ReversiSingleton.getBoardView().updateTurn();
		ReversiSingleton.getMainForm().repaint();
	}

	@Override
	void firstButton_actionPerformed(ActionEvent e) {
		if (ReversiSingleton.getBoardView().timer.isRunning()) {
			return;
		}
		ReversiSingleton.getBoardView().board.takeBackAllMoves();
		if (History.getInstance().size("undo") <= 0) {
			System.out.println("Nothing to undo from Memento");
		} else {
			History.getInstance().undoAllFromUndoList();
		}
		System.out.println("========== undoALL from Memento ==========");
		
		ReversiSingleton.getBoardView().updateTurn();
		ReversiSingleton.getMainForm().repaint();
	}

	@Override
	void lastButton_actionPerformed(ActionEvent e) {
		if (ReversiSingleton.getBoardView().timer.isRunning()) {
			return;
		}
		ReversiSingleton.getBoardView().board.redoAllMoves();
		if (History.getInstance().size("redo") <= 0) {
			System.out.println("Nothing to redo from Memento");
		} else {
			History.getInstance().redoAllFromRedoList();
		}
		System.out.println("========== redoALL from Memento ==========");
		
		ReversiSingleton.getBoardView().updateTurn();
		ReversiSingleton.getMainForm().repaint();
	}
}
