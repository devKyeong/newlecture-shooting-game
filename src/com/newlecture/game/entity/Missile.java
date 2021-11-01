package com.newlecture.game.entity;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import com.newlecture.game.ui.GameCanvas;

public class Missile {

    private static Image img;
    private double x;
    private double y;

    private int speed;
    private boolean isHit;
    
    static {
        img = Toolkit.getDefaultToolkit().getImage("res/images/missile.png");
    }

    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isHit() {
        return isHit;
    }

    public Missile(double x, double y, int speed) {
        

        this.x = x;
        this.y = y;
        this.speed = speed+10;
    }


    public void update() {
        if(y <= 0)
            setHit(true);
        else y -= speed;
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
