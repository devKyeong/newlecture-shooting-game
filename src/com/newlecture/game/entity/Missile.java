package com.newlecture.game.entity;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.newlecture.game.ui.GameCanvas;

public class Missile implements Entity{

    private static Image img;
    private double x;
    private double y;

    private int speed;
    
    static {
        img = Toolkit.getDefaultToolkit().getImage("res/images/missile.png");
    }

    public boolean outSideOfBounds() {
        return ( 0 >= x || x >= 500 ) || (0 >= y || y >= 700);
    }

    public Missile(double x, double y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed+10;
    }


    public void update() {
        if(!outSideOfBounds())
            y -= speed;
    }

    public void draw(Graphics g) {
        GameCanvas canvas = GameCanvas.getInstance();

        int width = img.getWidth(canvas);
        int height = img.getHeight(canvas);

        int x = (int)this.x - (width/2);
        int y = (int)this.y - (height/2);
        
        g.drawImage(img, x, y, canvas);
    }
    

}
