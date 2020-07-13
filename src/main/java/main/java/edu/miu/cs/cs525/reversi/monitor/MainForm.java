package main.java.edu.miu.cs.cs525.reversi.monitor;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;

import main.java.edu.miu.cs.cs525.reversi.ReversiSingleton;
import main.java.edu.miu.cs.cs525.reversi.action_adapters.BoardViewToolBarActionAdapter;
import main.java.edu.miu.cs.cs525.reversi.action_adapters.ChoosePlayerOkActionAdapter;
import main.java.edu.miu.cs.cs525.reversi.action_adapters.MainFormMenuActionAdapter;
import main.java.edu.miu.cs.cs525.reversi.action_adapters.MainFormWindowAdapter;
import main.java.edu.miu.cs.cs525.reversi.network.NetworkPlayer;

public class MainForm extends JFrame {
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	JMenuBar menuBar = new JMenuBar();
	JMenu menuGame = new JMenu("Game");
	JMenu menuOptions = new JMenu("Options");
	JMenuItem menuGameNew = new JMenuItem(" New");
	JMenuItem menuGameExit = new JMenuItem(" Exit");
	JMenu menuPlayers = new JMenu(" Players");
	JMenu menuBlackPlayer = new JMenu(" Black ");
	JMenu menuWhitePlayer = new JMenu(" White ");

	JRadioButtonMenuItem menuBlackPlayerHuman = new JRadioButtonMenuItem("Human");
	JRadioButtonMenuItem menuBlackPlayerComputer = new JRadioButtonMenuItem("Computer");
	JRadioButtonMenuItem menuWhitePlayerHuman = new JRadioButtonMenuItem("Human");
	JRadioButtonMenuItem menuWhitePlayerComputer = new JRadioButtonMenuItem("Computer");

	ButtonGroup BGroup = new ButtonGroup(); // Black Player Group
	ButtonGroup WGroup = new ButtonGroup(); // White Player Group
	JMenu menuHelp = new JMenu("Help");
	JMenuItem menuHelpAbout = new JMenuItem(" About");
	BorderLayout borderLayout1 = new BorderLayout();
	BoardView contents;
	JMenu menuSpeed = new JMenu(" Speed");
	JRadioButtonMenuItem menuSpeedItems[] = new JRadioButtonMenuItem[6];
	ButtonGroup SGroup = new ButtonGroup(); // Animation Speed Group
	ImageIcon newGameIcon = new ImageIcon();
	ImageIcon exitGameIcon = new ImageIcon();
	ImageIcon aboutIcon = new ImageIcon();
	ImageIcon playersIcon = new ImageIcon();
	ImageIcon speedIcon = new ImageIcon();
	MoveList ml;
	JCheckBoxMenuItem menuShowMoveList = new JCheckBoxMenuItem(" Show Move List");

	JButton firstButton = new JButton();
	JButton prevButton = new JButton();
	JButton pauseButton = new JButton();
	JButton nextButton = new JButton();
	JButton lastButton = new JButton();
	JToolBar toolBar = new JToolBar();
	ImageIcon firstImg = new ImageIcon();
	ImageIcon prevImg = new ImageIcon();
	ImageIcon pauseImg = new ImageIcon();
	ImageIcon playImg = new ImageIcon();
	ImageIcon nextImg = new ImageIcon();
	ImageIcon lastImg = new ImageIcon();
	ImageIcon newGameImg = new ImageIcon();
	ImageIcon aboutImg = new ImageIcon();
	ImageIcon exitGameImg = new ImageIcon();

	JLabel lblTeam4 = new JLabel("Team 4");
	JLabel lblOrForBlack = new JLabel("or");
	JLabel lblBlackPlayer = new JLabel("Black Player");
	JLabel lblOtherTeam = new JLabel("Other Team");
	JLabel lblOrForWhite = new JLabel("or");
	JLabel lblWhitePlayer = new JLabel("White Player");

	// Construct the frame
	public MainForm() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Component initialization
	private void jbInit() throws Exception {
		newGameIcon = new ImageIcon(MainForm.class.getResource("../images/new.png"));
		exitGameIcon = new ImageIcon(MainForm.class.getResource("../images/quit.png"));
		aboutIcon = new ImageIcon(MainForm.class.getResource("../images/about.png"));
		playersIcon = new ImageIcon(MainForm.class.getResource("../images/players.png"));
		speedIcon = new ImageIcon(MainForm.class.getResource("../images/eyes.png"));
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(borderLayout1);
		this.setResizable(false);
		this.setSize(new Dimension(636 + 60, 543 + 95));
		this.setTitle("Java Othello / Reversi");
		this.addWindowListener(new MainFormWindowAdapter(this));
		menuGameNew.setIcon(newGameIcon);
		menuGameNew.addActionListener(new MainFormMenuActionAdapter(this, "GameNew"));
		menuGameExit.setIcon(exitGameIcon);
		menuGameExit.addActionListener(new MainFormMenuActionAdapter(this, "GameExit"));
		menuBlackPlayerComputer.addActionListener(new MainFormMenuActionAdapter(this, "BlackPlayerComputer"));
		menuBlackPlayerHuman.addActionListener(new MainFormMenuActionAdapter(this, "BlackPlayerHuman"));
		menuWhitePlayerComputer.addActionListener(new MainFormMenuActionAdapter(this, "WhitePlayerComputer"));
		menuWhitePlayerHuman.addActionListener(new MainFormMenuActionAdapter(this, "WhitePlayerHuman"));
		menuHelpAbout.setIcon(aboutIcon);
		menuShowMoveList.addActionListener(new MainFormMenuActionAdapter(this, "ShowMoveList"));
		menuShowMoveList.setState(true);

		WGroup.add(menuWhitePlayerComputer);
		WGroup.add(menuWhitePlayerHuman);
		menuWhitePlayerComputer.setSelected(true);
		menuBlackPlayerComputer.setSelected(true);
		BGroup.add(menuBlackPlayerComputer);
		BGroup.add(menuBlackPlayerHuman);
		menuSpeedItems[0] = new JRadioButtonMenuItem("1.0 x");
		menuSpeedItems[1] = new JRadioButtonMenuItem("2.0 x");
		menuSpeedItems[2] = new JRadioButtonMenuItem("3.0 x");
		menuSpeedItems[3] = new JRadioButtonMenuItem("5.0 x", true);
		menuSpeedItems[4] = new JRadioButtonMenuItem("10.0 x");
		menuSpeedItems[5] = new JRadioButtonMenuItem("No Animation");
		menuSpeedItems[0].addActionListener(new MainFormMenuActionAdapter(this, "SpeedItems", 1));
		menuSpeedItems[1].addActionListener(new MainFormMenuActionAdapter(this, "SpeedItems", 2));
		menuSpeedItems[2].addActionListener(new MainFormMenuActionAdapter(this, "SpeedItems", 3));
		menuSpeedItems[3].addActionListener(new MainFormMenuActionAdapter(this, "SpeedItems", 5));
		menuSpeedItems[4].addActionListener(new MainFormMenuActionAdapter(this, "SpeedItems", 10));
		menuSpeedItems[5].addActionListener(new MainFormMenuActionAdapter(this, "SpeedItems", 30));
		menuHelpAbout.addActionListener(new MainFormMenuActionAdapter(this, "HelpAbout"));
		menuGame.add(menuGameNew);
		menuGame.add(menuGameExit);
		menuBlackPlayer.add(menuBlackPlayerComputer);
		menuBlackPlayer.add(menuBlackPlayerHuman);
		menuWhitePlayer.add(menuWhitePlayerComputer);
		menuWhitePlayer.add(menuWhitePlayerHuman);
		menuPlayers.add(menuBlackPlayer);
		menuPlayers.add(menuWhitePlayer);
		menuHelp.add(menuHelpAbout);
		for (int i = 0; i < menuSpeedItems.length; i++) {
			SGroup.add(menuSpeedItems[i]);
			menuSpeed.add(menuSpeedItems[i]);
		}
		menuPlayers.setIcon(playersIcon);
		menuSpeed.setIcon(speedIcon);
		menuBar.add(menuGame);
		menuOptions.add(menuPlayers);
		menuOptions.add(menuSpeed);
		menuOptions.add(menuShowMoveList);
		menuBar.add(menuOptions);
		menuBar.add(menuHelp);
		this.setJMenuBar(menuBar);
		firstImg = new ImageIcon(MainForm.class.getResource("../images/first.png"));
		prevImg = new ImageIcon(MainForm.class.getResource("../images/prev.png"));
		pauseImg = new ImageIcon(MainForm.class.getResource("../images/pause.png"));
		playImg = new ImageIcon(MainForm.class.getResource("../images/play.png"));
		nextImg = new ImageIcon(MainForm.class.getResource("../images/next.png"));
		lastImg = new ImageIcon(MainForm.class.getResource("../images/last.png"));
		firstButton.setEnabled(false);
		firstButton.setToolTipText("First Move");
		firstButton.addActionListener(new BoardViewToolBarActionAdapter(this, "firstButton"));
		prevButton.setEnabled(false);
		prevButton.setToolTipText("Previous Move");
		prevButton.addActionListener(new BoardViewToolBarActionAdapter(this, "prevButton"));
		nextButton.setEnabled(false);
		nextButton.setToolTipText("Next Move");
		nextButton.addActionListener(new BoardViewToolBarActionAdapter(this, "nextButton"));
		lastButton.setEnabled(false);
		lastButton.setToolTipText("Last Move");
		lastButton.addActionListener(new BoardViewToolBarActionAdapter(this, "lastButton"));
		pauseButton.setEnabled(false);
		pauseButton.setToolTipText("Pause Game");
		pauseButton.addActionListener(new BoardViewToolBarActionAdapter(this, "pauseButton"));
		firstButton.setIcon(firstImg);
		prevButton.setIcon(prevImg);
		pauseButton.setIcon(pauseImg);
		nextButton.setIcon(nextImg);
		lastButton.setIcon(lastImg);

		toolBar.add(firstButton);
		toolBar.add(prevButton);
		toolBar.add(pauseButton);
		toolBar.add(nextButton);
		toolBar.add(lastButton);

		lblTeam4.setForeground(ReversiSingleton.getAqua());
		lblOrForBlack.setForeground(ReversiSingleton.getAqua());
		lblBlackPlayer.setForeground(ReversiSingleton.getAqua());
		lblWhitePlayer.setForeground(ReversiSingleton.getAqua());
		lblOrForWhite.setForeground(ReversiSingleton.getAqua());
		lblOtherTeam.setForeground(ReversiSingleton.getAqua());

		ReversiSingleton.getLeftPane().add(lblTeam4);
		ReversiSingleton.getLeftPane().add(lblOrForBlack);
		ReversiSingleton.getLeftPane().add(lblBlackPlayer);
		ReversiSingleton.getRightPane().add(lblOtherTeam);
		ReversiSingleton.getRightPane().add(lblOrForWhite);
		ReversiSingleton.getRightPane().add(lblWhitePlayer);

		contentPane.add(toolBar, BorderLayout.NORTH);
		ml = new MoveList("Move List", this);
		contents = new BoardView(ml, this, 1);
		contentPane.add(ReversiSingleton.getLeftPane(), BorderLayout.WEST);
		contentPane.add(contents, BorderLayout.CENTER);
		contentPane.add(ReversiSingleton.getRightPane(), BorderLayout.EAST);
	}

	public void continueGame() {
		pauseButton.setIcon(pauseImg);
		firstButton.setEnabled(false);
		prevButton.setEnabled(false);
		nextButton.setEnabled(false);
		lastButton.setEnabled(false);
		pauseButton.setToolTipText("Pause Game");
		contents.gamePaused = false;
		contents.updateTurn();
	}

	public void pauseGame() {
		pauseButton.setIcon(playImg);
		firstButton.setEnabled(true);
		prevButton.setEnabled(true);
		nextButton.setEnabled(true);
		lastButton.setEnabled(true);
		pauseButton.setToolTipText("Continue Game");
		contents.gamePaused = true;
	}

	public void newGame() {
		contents.board.initBoard();
		repaint();
		pauseButton.setEnabled(true);
		continueGame();
	}

	public void pauseButton_actionPerformed(ActionEvent e) {
		if (pauseButton.getToolTipText().equals("Pause Game")) {
			pauseGame();
		} else {
			continueGame();
		}
	}

	public void prevButton_actionPerformed(ActionEvent e) {
		if (contents.timer.isRunning()) {
			return;
		}
		contents.board.takeBackOneMove();
		contents.updateTurn();
		repaint();
	}

	public void nextButton_actionPerformed(ActionEvent e) {
		if (contents.timer.isRunning()) {
			return;
		}
		contents.board.redoOneMove();
		contents.updateTurn();
		repaint();
	}

	public void firstButton_actionPerformed(ActionEvent e) {
		if (contents.timer.isRunning()) {
			return;
		}
		contents.board.takeBackAllMoves();
		contents.updateTurn();
		repaint();
	}

	public void lastButton_actionPerformed(ActionEvent e) {
		if (contents.timer.isRunning()) {
			return;
		}
		contents.board.redoAllMoves();
		contents.updateTurn();
		repaint();
	}

	public void menuGameNew_actionPerformed(ActionEvent e) {
		newGame();
	}

	// File | Exit action performed
	public void menuGameExit_actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	// Help | About action performed
	public void menuHelpAbout_actionPerformed(ActionEvent e) {
		About dlg = new About(this);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.show();
	}

	// Overridden so we can exit when window is closed
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			menuGameExit_actionPerformed(null);
		}
	}

	public void menuBlackPlayerComputer_actionPerformed(ActionEvent e) {
		ChoosePlayerType dlg = new ChoosePlayerType(this, "Choose Black Player", true);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.show();
		if (dlg.playerType == dlg.COMPUTER_PLAYER) {
			contents.playerBPointer = new ComputerPlayer();
			menuBlackPlayerComputer.setText("Computer");

		} else if (dlg.playerType == dlg.NET_PLAYER) {
			contents.playerBPointer = new NetworkPlayer(dlg.hostAddress, dlg.portNumber, dlg.portNumber2);
			try {
				String id = ((NetworkPlayer) contents.playerBPointer).identify();
				menuBlackPlayerComputer.setText(id + " @ ( " + dlg.hostAddress + ":" + dlg.portNumber + " )");
			} catch (Exception exc) {
			}
		} else if (contents.playerBPointer == null) {
			//Here we need to put the old code
			menuBlackPlayerComputer.setSelected(true);
			menuBlackPlayerComputer.setEnabled(true);
			contents.playerBPointer = new ComputerPlayer();
			dlg.radioComputerPlayer_actionPerformed(e);
			dlg.radioComputerPlayer.setSelected(true);
			dlg.radioComputerPlayer.addActionListener(new ChoosePlayerOkActionAdapter(dlg));
			dlg.radioComputerPlayer.doClick();
		}
		contents.updateTurn();
	}
	public void menuBlackPlayerHuman_actionPerformed(ActionEvent e) {
		menuBlackPlayerHuman.setText("Human");
	}

	public void menuWhitePlayerComputer_actionPerformed(ActionEvent e) {
		ChoosePlayerType dlg = new ChoosePlayerType(this, "Choose White Player", true);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.show();
		if (dlg.playerType == dlg.COMPUTER_PLAYER) {
			contents.playerWPointer = new ComputerPlayer();
			menuWhitePlayerComputer.setText("Computer");

		} else if (dlg.playerType == dlg.NET_PLAYER) {
			contents.playerWPointer = new NetworkPlayer(dlg.hostAddress, dlg.portNumber, dlg.portNumber2);
			try {
				String id = ((NetworkPlayer) contents.playerWPointer).identify();
				menuWhitePlayerComputer.setText(id + " @ ( " + dlg.hostAddress + ":" + dlg.portNumber + " )");
			} catch (Exception exc) {
			}
		} else if (contents.playerWPointer == null) {
			//Here we need to put the old code
			menuWhitePlayerComputer.setSelected(true);
			menuWhitePlayerComputer.setEnabled(true);
			contents.playerWPointer = new ComputerPlayer();
			dlg.radioComputerPlayer_actionPerformed(e);
			dlg.radioComputerPlayer.setSelected(true);
			dlg.radioComputerPlayer.addActionListener(new ChoosePlayerOkActionAdapter(dlg));
			dlg.radioComputerPlayer.doClick();

		}
		contents.updateTurn();
	}

	public void menuWhitePlayerHuman_actionPerformed(ActionEvent e) {
		menuWhitePlayerHuman.setText("Human");
	}

	public void menuSpeedItems_actionPerformed(ActionEvent e, int n) {
		contents.animationSpeed = n;
	}

	public void menuShowMoveList_actionPerformed(ActionEvent e) {
		ml.setVisible(menuShowMoveList.getState());
		repaint();
	}

	public void this_windowOpened(WindowEvent e) {
		Dimension frmSize = getSize();
		Point l = this.getLocationOnScreen();
		ml.setLocation(l.x + frmSize.width, l.y);
		ml.show();
		this.toFront();
	}
}

