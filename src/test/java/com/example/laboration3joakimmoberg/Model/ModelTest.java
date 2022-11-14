package com.example.laboration3joakimmoberg.Model;

import com.example.laboration3joakimmoberg.Shapes.Shape;
import com.example.laboration3joakimmoberg.Shapes.ShapeType;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    Shape circle = Shape.createShape(ShapeType.CIRCLE,Color.RED,30,50,50);
    ObsShape circleShape = new ObsShape(circle);
    Model model=new Model();



    @Test
    void checkIfSpecificShapeIsCreated(){
        Model.shapes.clear();
        Model.shapes.add(circleShape);
        assertEquals(circleShape, Model.shapes.get(0));
    }

    @Test
    void checkIfUndoHistoryIsUpdatedWhenAddingNewShape(){

        Model.shapes.clear();
        model.addShape(circleShape);

        assertEquals(1,model.undoHistory.size());

    }
}