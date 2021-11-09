package com.newlecture.game.ui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.newlecture.game.entity.Background;
import com.newlecture.game.entity.Entity;
import com.newlecture.game.entity.Fighter;

public class GameCanvas extends Canvas implements Runnable{

	private Fighter fighter;
	private List<Entity> entities;

	private static GameCanvas instance;
	private Thread gameThread;
	private int direction;
	private boolean isFiring;
	private int fireInterval;

	public static GameCanvas getInstance() {
		if(instance == null)
			instance = new GameCanvas();
		return instance;
	}

	private GameCanvas() {
		fighter = new Fighter();
		entities = new ArrayList<Entity>();

		entities.add(new Background());
		entities.add(fighter);

		gameThread = new Thread(this);
		gameThread.start();

		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				fighter.move(e.getX(), e.getY());
			}
		});

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fighter.move(e.getX(),e.getY());
			}
		});

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
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
				case KeyEvent.VK_SPACE:
					isFiring = false;
					break;
				default:
					break;
				}

				fighter.setDirection(direction);
			}
			@Override
			public void keyPressed(KeyEvent e) {
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
				case KeyEvent.VK_SPACE:
					isFiring = true;
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
		System.out.println("paint");
		Image buf = this.createImage(getWidth(), getHeight());

		for(Entity entity : entities)
			entity.draw(buf.getGraphics());

		g.drawImage(buf, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		System.out.println("update");
		paint(g);
	}

	@Override
	public void run() {
		while(true) {

			if(isFiring){
				if(fireInterval == 0)
					entities.add(fighter.fire());

				fireInterval++;
				fireInterval %= 5;
			}
			
			Iterator<Entity> iterator = entities.iterator();
			Entity entity;
			while(iterator.hasNext()){
				entity = iterator.next();

				if(entity.outSideOfBounds())
					iterator.remove();
				else
					entity.update();
			}
			System.out.println("repaint");
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
