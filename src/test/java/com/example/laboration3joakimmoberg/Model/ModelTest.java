package com.example.laboration3joakimmoberg.Model;

import com.example.laboration3joakimmoberg.Shapes.Shape;
import com.example.laboration3joakimmoberg.Shapes.ShapeType;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    Shape circle = Shape.createShape(ShapeType.CIRCLE,Color.RED,30,50,50);
    Shape square = Shape.createShape(ShapeType.SQUARE,Color.RED,30,50,50);
    ObsShape circleShape = new ObsShape(circle);
    ObsShape squareShape = new ObsShape(square);


    @Test
    void checkIfShapeIsAddedToList(){

        Model.shapes.clear();
        Model.shapes.add(circleShape);
        Model.shapes.add(squareShape);
        assertEquals(2, Model.shapes.size());

    }


    @Test
    void checkIfSpecificShapeIsCreated(){
        Model.shapes.clear();
        Model.shapes.add(circleShape);
        assertEquals(circleShape, Model.shapes.get(0));
    }
}