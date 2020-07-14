package main.java.edu.miu.cs.cs525.reversi.action_adapters;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;
import main.java.edu.miu.cs.cs525.reversi.monitor.About;
import main.java.edu.miu.cs.cs525.reversi.monitor.ChoosePlayerType;
import main.java.edu.miu.cs.cs525.reversi.monitor.ComputerPlayer;
import main.java.edu.miu.cs.cs525.reversi.network.NetworkPlayer;

public class MenuTemplate extends AbstractMenu {
	
	AbstractBoardView ab = new BoardViewTemplate();

	@Override
	void menuGameNew_actionPerformed(ActionEvent e) {
		ab.newGame();
	}

	@Override
	void menuGameExit_actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	@Override
	void menuHelpAbout_actionPerformed(ActionEvent e) {
		About dlg = new About(ReversiSingleton.getMainForm());
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = ReversiSingleton.getMainForm().getSize();
		Point loc = ReversiSingleton.getMainForm().getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.show();
	}

	@Override
	void processWindowEvent(ActionEvent e) {
//		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			menuGameExit_actionPerformed(null);
		}
	}

	@Override
	void menuBlackPlayerComputer_actionPerformed(ActionEvent e) {
		ChoosePlayerType dlg = new ChoosePlayerType(ReversiSingleton.getMainForm(), "Choose Black Player", true);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = ReversiSingleton.getMainForm().getSize();
		Point loc = ReversiSingleton.getMainForm().getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.show();
		if (dlg.playerType == dlg.COMPUTER_PLAYER) {
			ReversiSingleton.getBoardView().playerBPointer = new ComputerPlayer();
			ReversiSingleton.getMenuBlackPlayerComputer().setText("Computer");

		} else if (dlg.playerType == dlg.NET_PLAYER) {
			ReversiSingleton.getBoardView().playerBPointer = new NetworkPlayer(dlg.hostAddress, dlg.portNumber, dlg.portNumber2);
			try {
				String id = ((NetworkPlayer) ReversiSingleton.getBoardView().playerBPointer).identify();
				ReversiSingleton.getMenuBlackPlayerComputer().setText(id + " @ ( " + dlg.hostAddress + ":" + dlg.portNumber + " )");
//				ReversiSingleton.txtTeam4=dlg.hostAddress ;
			} catch (Exception exc) {
			}
		} else if (ReversiSingleton.getBoardView().playerBPointer == null) {
			//Here we need to put the old code
			ReversiSingleton.getMenuBlackPlayerComputer().setSelected(true);
			ReversiSingleton.getMenuBlackPlayerComputer().setEnabled(true);
			ReversiSingleton.getBoardView().playerBPointer = new ComputerPlayer();
			dlg.radioComputerPlayer_actionPerformed(e);
			dlg.radioComputerPlayer.setSelected(true);
			ActionEventFactory.getActionPerformed("ChoosePlayerOkAction");
			dlg.radioComputerPlayer.addActionListener(ActionEventFactory.ac.initializeInstance(dlg));
			dlg.radioComputerPlayer.doClick();
		}
		ReversiSingleton.getBoardView().updateTurn();
	}

	@Override
	void menuBlackPlayerHuman_actionPerformed(ActionEvent e) {
		ReversiSingleton.getMenuBlackPlayerHuman().setText("Human");
	}

	@Override
	void menuWhitePlayerComputer_actionPerformed(ActionEvent e) {
		ChoosePlayerType dlg = new ChoosePlayerType(ReversiSingleton.getMainForm(), "Choose White Player", true);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = ReversiSingleton.getMainForm().getSize();
		Point loc = ReversiSingleton.getMainForm().getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.show();
		if (dlg.playerType == dlg.COMPUTER_PLAYER) {
			ReversiSingleton.getBoardView().playerWPointer = new ComputerPlayer();
			ReversiSingleton.getMenuWhitePlayerComputer().setText("Computer");

		} else if (dlg.playerType == dlg.NET_PLAYER) {
			ReversiSingleton.getBoardView().playerWPointer = new NetworkPlayer(dlg.hostAddress, dlg.portNumber, dlg.portNumber2);
			try {
				String id = ((NetworkPlayer) ReversiSingleton.getBoardView().playerWPointer).identify();
				ReversiSingleton.getMenuWhitePlayerComputer().setText(id + " @ ( " + dlg.hostAddress + ":" + dlg.portNumber + " )");
			} catch (Exception exc) {
			}
		} else if (ReversiSingleton.getBoardView().playerWPointer == null) {
			//Here we need to put the old code
			ReversiSingleton.getMenuWhitePlayerComputer().setSelected(true);
//			ReversiSingleton.getMenuWhitePlayerComputer().setEnabled(true);
			ReversiSingleton.getBoardView().playerWPointer = new ComputerPlayer();
			dlg.radioComputerPlayer_actionPerformed(e);
			dlg.radioComputerPlayer.setSelected(true);
			ActionEventFactory.getActionPerformed("ChoosePlayerOkAction");
			dlg.radioComputerPlayer.addActionListener(ActionEventFactory.ac.initializeInstance(dlg));
			dlg.radioComputerPlayer.doClick();

		}
		ReversiSingleton.getBoardView().updateTurn();
	}

	@Override
	void menuWhitePlayerHuman_actionPerformed(ActionEvent e) {
		//Here we need to put the old code
		ReversiSingleton.getMenuWhitePlayerHuman().setText("Human");
	}

	@Override
	void menuSpeedItems_actionPerformed(ActionEvent e, int n) {
		ReversiSingleton.getBoardView().animationSpeed = n;
	}

	@Override
	void menuShowMoveList_actionPerformed(ActionEvent e) {
		ReversiSingleton.getMoveList().setVisible(ReversiSingleton.getMenuShowMoveList().getState());
		ReversiSingleton.getMainForm().repaint();
	}

}
