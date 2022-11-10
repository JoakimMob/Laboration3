package com.example.laboration3joakimmoberg.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape {
    private final double x;
    private final double y;

    private Color color;

    private double size;

    public Shape(Color color,double x, double y, double size) {
        this.color=color;
        this.x = x;
        this.y = y;
        this.size = size;

    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void drawShape(GraphicsContext context);
    public static Shape createShape(ShapeType type, Color color, double x, double y, double size){
        return switch (type) {
            case CIRCLE -> new Circle(color,x, y, size);
            case SQUARE -> new Square(color, x, y, size);
        };
    }

    public double getSize() {
        return size;
    }

    public abstract String toSVG();
}