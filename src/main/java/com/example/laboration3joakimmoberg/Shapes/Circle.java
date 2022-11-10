package com.example.laboration3joakimmoberg.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {
    public Circle(Color color,double x, double y, double size) {
        super(color,x, y, size);
    }

    @Override
    public void drawShape(GraphicsContext context) {
        context.setFill(getColor());
        context.fillOval(getX()-getSize()/2,getY()-getSize()/2,getSize(),getSize());
    }

    @Override
    public String toSVG() {
        String color= "#"+ getColor().toString().substring(2,10);
        return "<circle cx=\""+getX()+"\" cy=\""+getY()+"\" r=\""+ getSize()/2+"\" fill=\""+ color+"\" />";
    }
}
