package com.newlecture.game.entity;
import java.awt.Graphics;

public interface Entity {
    void draw(Graphics g);
    void update();
    boolean outSideOfBounds();
    
}
