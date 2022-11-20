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
    void checkIfUndoHistoryIsUpdatedWhenAddingNewShape(){

        Model.shapes.clear();
        model.addShape(circleShape);

        assertEquals(1,model.undoHistory.size());

    }
    @Test
    void checkIfRedoHistoryIsUpdatedWhenUndoShapeIsUsed(){

        Model.shapes.clear();
        model.addShape(circleShape);
        model.addShape(circleShape);
        model.undoShape();

        assertEquals(1,model.redoHistory.size());
    }


}