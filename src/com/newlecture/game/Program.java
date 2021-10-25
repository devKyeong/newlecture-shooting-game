package com.newlecture.game;

import java.awt.Canvas;
import javax.swing.JFrame;

import com.newlecture.game.ui.GameCanvas;

public class Program {

	public static void main(String[] args) {
		// 1. JFrame 형식의 객체 win을 생성하고 화면 출력을 위해 setVisible() 메소드에 true 값을 전달하여 호출하라.
		JFrame win = new JFrame();
		win.setVisible(true);

		// 2. win 객체의 초기 상태가 맘에 안든다. 크기를 변경하는 setSize 메소드를 통해 크기(width : 500, height : 700) 설정하라.
		//		닫기 버튼을 누르면 프로그램이 종료되도록 setDefaultCloseOperation 메소드를 통해 JFrame.EXIT_ON_CLOSE 설정하라.

		win.setSize(500, 700);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 3. Canvas 객체 canvas를 생성하고 그것을 win 객체의 add() 메소드를 통해서 win 객체의 구성으로 포함시키자.
		//Canvas canvas = new Canvas();
		//win.add(canvas);

		Canvas canvas = GameCanvas.getInstance();
		win.add(canvas);
		canvas.requestFocus();


		// 4. canvas 객체의 화면에 그림을 그리기 위해서 canvas의 getGraphics() 메소드를 통해서 그림을 그리는 도구를 얻자.

	}

}
