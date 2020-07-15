package main.java.edu.miu.cs.cs525.reversi.utils;

import javax.swing.ImageIcon;

public class ImageClass {
	
	ImageIcon firstImg = new ImageIcon();
	ImageIcon prevImg = new ImageIcon();
	ImageIcon pauseImg = new ImageIcon();
	ImageIcon playImg = new ImageIcon();
	ImageIcon nextImg = new ImageIcon();
	ImageIcon lastImg = new ImageIcon();
	ImageIcon newGameImg = new ImageIcon();
	ImageIcon aboutImg = new ImageIcon();
	ImageIcon exitGameImg = new ImageIcon();
	ImageIcon newGameIcon = new ImageIcon();
	ImageIcon exitGameIcon = new ImageIcon();
	ImageIcon aboutIcon = new ImageIcon();
	ImageIcon playersIcon = new ImageIcon();
	ImageIcon speedIcon = new ImageIcon();
	
	public ImageIcon getFirstImg() {
		firstImg = new ImageIcon(ImageClass.class.getResource("../images/first.png"));
		return firstImg;
	}
	
	public ImageIcon getPrevImg() {
		prevImg = new ImageIcon(ImageClass.class.getResource("../images/prev.png"));
		return prevImg;
	}
	
	public ImageIcon getPauseImg() {
		pauseImg = new ImageIcon(ImageClass.class.getResource("../images/pause.png"));
		return pauseImg;
	}
	
	public ImageIcon getPlayImg() {
		playImg = new ImageIcon(ImageClass.class.getResource("../images/play.png"));
		return playImg;
	}
	
	public ImageIcon getNextImg() {
		nextImg = new ImageIcon(ImageClass.class.getResource("../images/next.png"));
		return nextImg;
	}
	
	public ImageIcon getLastImg() {
		lastImg = new ImageIcon(ImageClass.class.getResource("../images/last.png"));
		return lastImg;
	}
	
	public ImageIcon getNewGameIcon() {
		newGameIcon = new ImageIcon(ImageClass.class.getResource("../images/new.png"));
		return newGameIcon;
	}
	
	public ImageIcon getExitGameIcon() {
		exitGameIcon = new ImageIcon(ImageClass.class.getResource("../images/quit.png"));
		return exitGameIcon;
	}
	
	public ImageIcon getAboutGameIcon() {
		aboutIcon = new ImageIcon(ImageClass.class.getResource("../images/about.png"));
		return aboutIcon;
	}
	
	public ImageIcon getPlayersIcon() {
		playersIcon = new ImageIcon(ImageClass.class.getResource("../images/players.png"));
		return playersIcon;
	}
	
	public ImageIcon getSpeedIcon() {
		speedIcon = new ImageIcon(ImageClass.class.getResource("../images/eyes.png"));
		return speedIcon;
	}
	
}
