package com.newlecture.game.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.newlecture.game.ui.GameCanvas;

public class Background {

	private Image img;
	private int width;
	private int height;
	private double y;

	private int speed;

	public Background() {
		img = Toolkit.getDefaultToolkit().getImage("res/images/space.jpg"); //비동기 로드

		//동기는 ImageIO를 이용.
		y = -(1200 - 700);
		width = 500;
		height = 500*1200/360;

		speed = 2;
	}

	public void moveDown() {

	}

	public void update() {
		y += speed;
	}

	public void draw(Graphics g) {
		GameCanvas canvas = GameCanvas.getInstance();
		int y = (int)this.y;

		g.drawImage(img, 0, y, width, height, canvas);
	}
}