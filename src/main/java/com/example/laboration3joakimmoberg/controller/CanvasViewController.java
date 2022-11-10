package com.example.laboration3joakimmoberg.controller;

import com.example.laboration3joakimmoberg.Model.Model;
import com.example.laboration3joakimmoberg.SaveSvg;
import com.example.laboration3joakimmoberg.Shapes.Shape;
import com.example.laboration3joakimmoberg.Shapes.ShapeType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;

import javax.imageio.ImageIO;
import java.io.File;

public class CanvasViewController {
    public Canvas canvas;
    public GraphicsContext context;
    public ChoiceBox<ShapeType> choiceBox;
    public ColorPicker colorPicker;
    public TextField textField;
    public Button undoBtn;
    public Button redoBtn;
    public ToggleButton selectBtn;

    Model model = new Model();
    ObservableList<ShapeType> shapeTypesList = FXCollections.observableArrayList(ShapeType.values());

    public void initialize() {
        context = canvas.getGraphicsContext2D();
        choiceBox.setItems(shapeTypesList);
        choiceBox.valueProperty().bindBidirectional(model.currentShapeTypeProperty());
        model.getShapes().addListener(this::listChanged);
        textField.textProperty().bindBidirectional(model.getText);
        colorPicker.valueProperty().bindBidirectional(model.getColor);
    }


    private void listChanged(Observable observable) {
        var context = canvas.getGraphicsContext2D();
        for (Shape s : model.getShapes()) {
            s.drawShape(context);
        }
    }

    @FXML
    void saveImage() {
        SaveSvg.save(model);

        try {
            Image snapShot= canvas.snapshot(null,null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapShot, null), "png", new File("paint.png"));
        } catch (Exception e) {
            System.out.println("Failed to save " + e);
        }

    }
    public void onExit() {
        Platform.exit();
    }

    public void canvasClicked(MouseEvent mouseEvent) {

        if (selectBtn.isSelected()) {
            model.selectMode(context, mouseEvent);
        } else {

            //Create shape
            Shape shape = Shape.createShape(model.getCurrentShapeType(), model.getColor.getValue(), mouseEvent.getX(),
                    mouseEvent.getY(), Double.parseDouble(model.getText.getValue()));
            //Add shape to models list of shapes
            model.addShape(shape);

        }
    }


    public void onUndoBtn(ActionEvent actionEvent) {
        model.undoShape(context);
    }

    public void onRedoBtn(ActionEvent actionEvent) {
        model.redoShape(context);
    }
}