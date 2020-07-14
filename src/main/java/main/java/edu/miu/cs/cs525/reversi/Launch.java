package main.java.edu.miu.cs.cs525.reversi;

import javax.swing.UIManager;

import main.java.edu.miu.cs.cs525.reversi.monitor.MainForm;

//import edu.miu.cs.cs525.reversi.monitor.MainForm;

import java.awt.*;

public class Launch {
	// Main method
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		ReversiSingleton.getInstance();
		new Launch();
	}

	boolean packFrame = false;

	
	private Launch() {
//		MainForm frame = new MainForm();
		// Validate frames that have preset sizes
		// Pack frames that have useful preferred size info, e.g. from their layout
		if (packFrame) {
			ReversiSingleton.getMainForm().pack();
		} else {
			ReversiSingleton.getMainForm().validate();
		}
		// Center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = ReversiSingleton.getMainForm().getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		ReversiSingleton.getMainForm().setLocation((screenSize.width - frameSize.width - 185), (screenSize.height - frameSize.height) / 2);
		ReversiSingleton.getMainForm().setVisible(true);
	}

}
