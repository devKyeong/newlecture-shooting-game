package com.newlecture.game.ui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import com.newlecture.game.entity.Background;
import com.newlecture.game.entity.Fighter;

public class GameCanvas extends Canvas implements Runnable{

	private Fighter fighter;
	private Background background;
	private static GameCanvas instance;
	private Thread gameThread;
	private int direction;

	public static GameCanvas getInstance() {
		if(instance == null)
			instance = new GameCanvas();
		return instance;
	}

	private GameCanvas() {
		background = new Background();
		fighter = new Fighter();

		gameThread = new Thread(this);
		gameThread.start();

		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				//System.out.println("mouse Dragged");
				fighter.move(e.getX(), e.getY());
			}
		});

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println("mouse Clicked");
				fighter.move(e.getX(),e.getY());
			}
		});

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				//System.out.println("key Released");
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					direction ^= Fighter.MOVE_LEFT;
					break;
				case KeyEvent.VK_UP:
					direction ^= Fighter.MOVE_UP;
					break;
				case KeyEvent.VK_RIGHT:
					direction ^= Fighter.MOVE_RIGHT;
					break;
				case KeyEvent.VK_DOWN:
					direction ^= Fighter.MOVE_DOWN;
					break;
				default:
					break;
				}

				fighter.setDirection(direction);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("key Pressed");

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					direction |= Fighter.MOVE_LEFT;
					break;
				case KeyEvent.VK_UP:
					direction |= Fighter.MOVE_UP;
					break;
				case KeyEvent.VK_RIGHT:
					direction |= Fighter.MOVE_RIGHT;
					break;
				case KeyEvent.VK_DOWN:
					direction |= Fighter.MOVE_DOWN;
					break;
				default:
					break;
				}

				fighter.setDirection(direction);
			}
		});
	}

	@Override
	public void paint(Graphics g) {

		Image buf = this.createImage(getWidth(), getHeight());
		background.draw(buf.getGraphics());
		fighter.draw(buf.getGraphics());

		g.drawImage(buf, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void run() {
		while(true) {
			background.update();
			fighter.update();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
