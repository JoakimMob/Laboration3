package com.example.laboration3joakimmoberg.controller;

import com.example.laboration3joakimmoberg.Model.Model;
import com.example.laboration3joakimmoberg.SaveSvg;
import com.example.laboration3joakimmoberg.Shapes.Shape;
import com.example.laboration3joakimmoberg.Shapes.ShapeType;
import javafx.fxml.FXML;

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
            Image snapShot = canvas.snapshot(null, null);
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
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            model.selectMode(x, y);
            clearCanvas();
            for (Shape s : model.getShapes()) {
                s.drawShape(context);
            }
        } else {
            Shape shape = Shape.createShape(model.getCurrentShapeType(), model.getColor.getValue(), mouseEvent.getX(),
                    mouseEvent.getY(), Double.parseDouble(model.getText.getValue()));
            model.addShape(shape);

        }
    }


    public void onUndoBtn(ActionEvent actionEvent) {
        if (!model.undoHistory.empty()) {
            clearCanvas();
            model.undoShape();
            for (int i = 0; i < model.undoHistory.size(); i++) {
                Shape shape = model.undoHistory.elementAt(i);
                shape.drawShape(context);
            }
        }

    }

    public void onRedoBtn(ActionEvent actionEvent) {
        model.redoShape();
    }

    public void clearCanvas() {
        context.clearRect(0, 0, 600, 600);
    }
}