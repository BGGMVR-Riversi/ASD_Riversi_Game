package main.java.edu.miu.cs.cs525.reversi.monitor;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import main.java.edu.miu.cs.cs525.reversi.action_adapters.ReversiActionEventFactory;
import main.java.edu.miu.cs.cs525.reversi.mediator.BoardEnum;

public class ChoosePlayerType extends JDialog {
	private static final long serialVersionUID = 1L;
	JPanel panel1 = new JPanel();
	JLabel lblHost = new JLabel();
	JLabel lblPort = new JLabel();
	JLabel lblPort2 = new JLabel();
	JTextField txtHost = new JTextField();
	JTextField txtPort = new JTextField();
	JTextField txtPort2 = new JTextField();
	JButton cmdOK = new JButton();
	JRadioButton radioNetPlayer = new JRadioButton();
	public JRadioButton radioComputerPlayer = new JRadioButton();
	ButtonGroup bgroup = new ButtonGroup();
	public int playerType;
	public String hostAddress = "";
	public int portNumber;
	public int portNumber2;
	JButton cmdCancel = new JButton();
	GridBagLayout gridBagLayout1 = new GridBagLayout();

	public ChoosePlayerType(Frame frame, String title, boolean modal) {
		super(frame, title, modal);
		try {
			jbInit();
			pack();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ChoosePlayerType() {
		this(null, "", false);
	}

	private void jbInit() throws Exception {
		panel1.setLayout(gridBagLayout1);
		lblHost.setFont(new java.awt.Font("Default", 1, 12));
		lblHost.setText("Host Address :");
		lblPort.setFont(new java.awt.Font("Default", 1, 12));
		lblPort.setText("Port :");
		txtHost.setText("localhost");
		lblPort2.setFont(new java.awt.Font("Default", 1, 12));
		lblPort2.setText("Port2 :");
		txtPort.setText("");
		txtPort2.setText("");
		cmdOK.setText("OK");
		ReversiActionEventFactory.getActionPerformed("ChoosePlayerOkAction");
		cmdOK.addActionListener(ReversiActionEventFactory.ac.initializeInstance(this));
		radioNetPlayer.setText("Network Player :");
		ReversiActionEventFactory.getActionPerformed("ChoosePlayerRadioNetPlayerAction");
		radioNetPlayer.addActionListener(ReversiActionEventFactory.ac.initializeInstance(this));
		radioComputerPlayer.setSelected(true);
		radioComputerPlayer.setText("Computer Player");
		cmdCancel.setActionCommand("Close");
		cmdCancel.setText("Close");
		ReversiActionEventFactory.getActionPerformed("ChoosePlayerRadioComputerPlayerAction");
		cmdCancel.addActionListener(ReversiActionEventFactory.ac.initializeInstance(this));
		getContentPane().add(panel1);
		panel1.add(radioNetPlayer, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(20, 10, 0, 0), 0, 0));
		panel1.add(radioComputerPlayer, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
		panel1.add(cmdCancel, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(20, 0, 20, 0), 0, 0));
		panel1.add(cmdOK, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(20, 0, 20, 0), 0, 0));
		panel1.add(txtHost, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 10, 5, 30), 30, 2));
		panel1.add(txtPort, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 30), 10, 2));
		panel1.add(txtPort2, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 30), 10, 2));
		panel1.add(lblHost, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 30, 0, 0), 0, 0));
		panel1.add(lblPort, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		panel1.add(lblPort2, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		bgroup.add(radioNetPlayer);
		bgroup.add(radioComputerPlayer);
		ReversiActionEventFactory.getActionPerformed("ChoosePlayerRadioComputerPlayerAction");
		radioComputerPlayer.addActionListener(ReversiActionEventFactory.ac.initializeInstance(this));
	}

	@SuppressWarnings("deprecation")
	public void cmdOK_actionPerformed(ActionEvent e) {
		if (radioComputerPlayer.isSelected()) {
			playerType = BoardEnum.COMPUTER_PLAYER.value();

		} else {
			if (txtHost.getText().equals("")) {
				txtHost.requestFocus();
				return;
			} else if (txtPort.getText().equals("") || txtPort2.getText().equals("")) {
				txtPort.requestFocus();
				txtPort2.requestFocus();
				return;
			} else {
				try {
					portNumber = Integer.parseInt(txtPort.getText());
					portNumber2 = Integer.parseInt(txtPort2.getText());
				} catch (NumberFormatException ex) {
					txtPort.selectAll();
					txtPort.requestFocus();
					return;
				}
				playerType = BoardEnum.NET_PLAYER.value();
				hostAddress = txtHost.getText();
			}
		}
		this.hide();
	}

	public void radioNetPlayer_actionPerformed(ActionEvent e) {
		txtHost.setEnabled(true);
		txtPort.setEnabled(true);
		txtPort2.setEnabled(true);
		lblHost.setEnabled(true);
		lblPort.setEnabled(true);
	}

	public void radioComputerPlayer_actionPerformed(ActionEvent e) {
		radioComputerPlayer.setSelected(true);
		txtHost.setEnabled(false);
		txtPort.setEnabled(false);
		txtPort2.setEnabled(false);
		lblHost.setEnabled(false);
		lblPort.setEnabled(false);
	}

	public void cmdCancel_actionPerformed(ActionEvent e) {
		playerType = BoardEnum.NO_SELECTION.value();
		this.dispose();
	}
}
