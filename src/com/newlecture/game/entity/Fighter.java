package com.newlecture.game.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.newlecture.game.ui.GameCanvas;


public class Fighter {

	private Image img;
	private int imgIndex;
	private int imgIndexDuration;

	private double x;
	private double y;

	private double vx;
	private double vy;

	private int distance;
	private int speed;
	private int direction;

	public static final int MOVE_NONE = 0b0000; //0
	public static final int MOVE_UP = 0b0001; //1
	public static final int MOVE_RIGHT = 0b0010; //2
	public static final int MOVE_DOWN = 0b0100; //4
	public static final int MOVE_LEFT = 0b1000; //8


	public Fighter() {
		this(200,500);
	}

	public Fighter(int x, int y) {

		img = Toolkit
				.getDefaultToolkit()
				.getImage("res/images/fighter.png");
		imgIndex = 3;

		distance = 1;
		speed = 5;
		direction = MOVE_NONE;
		this.x = x;
		this.y = y;

	}

	public void move(int dx, int dy) {

		double w = dx-x;
		double h = dy-y;

		distance = (int) Math.sqrt(w*w + h*h);

		//x좌표의 단위거리가 나옴. x좌표의 차이(x축 거리)를
		//실제 총 거리(피타고라스 빗변 길이)로 나누면 단위 거리 도출
		if(distance >= speed) {
			vx = w/distance * speed;
			vy = h/distance * speed;
		}

	}

	public void update() {

		if((vx < 0 || (direction & MOVE_LEFT) == MOVE_LEFT) &&  imgIndex > 0 && imgIndexDuration == 0) imgIndex--;
		else if((vx > 0 || (direction & MOVE_RIGHT) == MOVE_RIGHT) && imgIndex < 6 && imgIndexDuration == 0) imgIndex++;
		else if((vx == 0 && (direction & MOVE_LEFT) != MOVE_LEFT) && imgIndex < 3 && imgIndexDuration == 0) imgIndex++;
		else if((vx == 0 && (direction & MOVE_RIGHT) != MOVE_RIGHT) && imgIndex > 3 && imgIndexDuration == 0) imgIndex--;
		else if(vx == 0 && direction == MOVE_NONE) imgIndex = 3;

		imgIndexDuration++;
		imgIndexDuration %= 3;

		/**
		 * 마우스
		 */
		if(distance < speed) {
			vx = vy = 0;
			distance = 1;
		}else {
			//distance는 계속 줄어들고 1이 될때까지, 그 때까지 단위거리(백터) 만큼 더해줘.
			y += vy;
			x += vx;
			distance -= speed;
		}
		/*if(distance >= speed) distance -= speed;
		else vx = vy = 0;*/


		/**
		 * 키보드
		 */
		//배타적이지 않아야함 -> 위로도 가고, 아래로도 가고.. 누적되어야함. -> if else가 아닌 if를 통해 조건에 맞으면 수행하도록 -> 누적 가능
		if((direction & MOVE_UP) == MOVE_UP)
			y -= speed;
		if((direction & MOVE_RIGHT) == MOVE_RIGHT)
			x += speed;
		if((direction & MOVE_DOWN) == MOVE_DOWN)
			y += speed;
		if((direction & MOVE_LEFT) == MOVE_LEFT)
			x -= speed;

	}



	public void draw(Graphics g) {

		GameCanvas canvas = GameCanvas.getInstance();

		int width = img.getWidth(canvas)/7;
		int height = img.getHeight(canvas);

		int offsetX = width/2;
		int offsetY = height/2;

		int limitUp = offsetY;
		int limitRight = canvas.getWidth()-offsetX;
		int limitDown = canvas.getHeight()-offsetY;
		int limitLeft = offsetX;

		if(limitUp > y) y = limitUp;
		if(limitRight < x) x = limitRight;
		if(limitDown < y) y = limitDown;
		if(limitLeft > x) x = limitLeft;

		int x = (int)this.x;
		int y = (int)this.y;

		int sx1 = imgIndex*width;
		int sy1 = 0;
		int sx2 = sx1+width;
		int sy2 = sy1+height;

		int dx1 = x-offsetX;
		int dy1 = y-offsetY;
		int dx2 = dx1+width;
		int dy2 = dy1+height;

		g.drawImage(img,
				dx1, dy1, dx2, dy2,
				sx1, sy1, sx2, sy2,
				canvas);
		//g.drawRect(x-offsetX, y-offsetY,width,height);
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}
