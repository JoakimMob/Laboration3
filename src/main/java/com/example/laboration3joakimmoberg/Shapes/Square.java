package com.example.laboration3joakimmoberg.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {
    public Square(Color color, double x, double y, double size) {
        super(color,x, y, size);
    }

    @Override
    public void drawShape(GraphicsContext context) {
        context.setFill(getColor());
        context.fillRect(getX()-getSize()/2,getY()-getSize()/2,getSize(),getSize());
    }



    public String toSVG() {
        String color= "#"+getColor().toString().substring(2,10);
        return "<rect x=\"" + getX() + "\" y=\"" + getY() + "\" width=\"" + getSize() + "\" height=\""
                + getSize() + "\" fill=\"" + color + "\" />";
    }
}

