package com.example.laboration3joakimmoberg.Model;

import com.example.laboration3joakimmoberg.Shapes.Shape;
import com.example.laboration3joakimmoberg.Shapes.ShapeType;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import java.util.Stack;

public class Model {

    public Property<Color> getColor = new SimpleObjectProperty<>(Color.RED);
    public StringProperty getText = new SimpleStringProperty("50");
    ObjectProperty<ShapeType> currentShapeType = new SimpleObjectProperty<>(ShapeType.CIRCLE);
    public Stack<ObsShape> undoHistory = new Stack<>();
    public Stack<ObsShape> redoHistory = new Stack<>();

    static ObservableList<ObsShape> shapes = FXCollections.observableArrayList(param -> new Observable[]{param.colorProperty()});

    public ShapeType getCurrentShapeType() {
        return currentShapeType.get();
    }

    public ObjectProperty<ShapeType> currentShapeTypeProperty() {
        return currentShapeType;
    }

    public ObservableList<? extends Shape> getShapes() {
        return shapes;
    }


    public void selectMode(double x, double y) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (x <= shapes.get(i).getX() + shapes.get(i).getSize() / 2
                    && x >= shapes.get(i).getX() - shapes.get(i).getSize() / 2
                    && y <= shapes.get(i).getY() + shapes.get(i).getSize() / 2
                    && y >= shapes.get(i).getY() - shapes.get(i).getSize() / 2) {
                shapes.get(i).setColor(getColor.getValue());
                shapes.get(i).setSize(Double.parseDouble(getText.get()));

                return;
            }

        }
    }

    public void undoShape() {

        ObsShape removedShape = undoHistory.lastElement();
        redoHistory.push(removedShape);
        shapes.remove(shapes.size() - 1);

        Shape lastRedo = redoHistory.lastElement();
        lastRedo.setColor(removedShape.getColor());
        undoHistory.pop();


    }

    public void redoShape() {
        if (!redoHistory.empty()) {
            ObsShape shape = redoHistory.lastElement();
            Color color = shape.getColor();

            redoHistory.pop();
            undoHistory.push(shape);
            shapes.add(shape);

            Shape lastUndo = undoHistory.lastElement();
            lastUndo.setColor(color);

        }
    }

    public void addShape(Shape shape) {
        var oShape = new ObsShape(shape);
        shapes.add(oShape);
        undoHistory.push(oShape);
    }
}

class ObsShape extends Shape {
    Shape shape;
    ObjectProperty<Color> color = new SimpleObjectProperty<>();
    ObjectProperty<String> size = new SimpleObjectProperty<>();


    public ObsShape(Shape shape) {
        super(shape.getColor(), shape.getX(), shape.getY(), shape.getSize());
        this.shape = shape;
        color.set(shape.getColor());
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    @Override
    public Color getColor() {
        return color.get();
    }

    @Override
    public void setColor(Color color) {
        shape.setColor(color);
        this.color.set(color);
    }

    public void setSize(double size) {
        shape.setSize(size);
        this.size.set(String.valueOf(size));
    }


    @Override
    public void drawShape(GraphicsContext context) {
        this.shape.drawShape(context);
    }

    @Override
    public String toSVG() {
        return this.shape.toSVG();
    }

}