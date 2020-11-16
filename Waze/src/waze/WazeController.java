/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waze;

import com.sun.prism.paint.LinearGradient;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import waze.algoritmos.Dijkstra;
import waze.algoritmos.Floyd;
import waze.util.Matriz;
import waze.util.Vertice;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class WazeController implements Initializable {

    
    @FXML
    private ImageView mapa;
    @FXML
    private CheckBox btnVertices;
    @FXML
    private CheckBox btnlineas;
    
    
    @FXML
    private Button btnChoque;
    @FXML
    private Button btnTrafico;
    @FXML
    private Button btnCerrarCalle;
    
    

    private List<Object> lineasSeleccionadas = new ArrayList<Object>();
    private List<Object> verticesSeleccionados = new ArrayList<Object>();
    
    @FXML
    private CheckBox cbxFloyd;
    @FXML
    private CheckBox cbxDijkstra;

    

    private List<Circle> circles = new ArrayList();
    private List<Circle> vertices = new ArrayList();
    private List<Line> lines = new ArrayList();
    @FXML
    private Label lblPunto;
    @FXML
    private Button btnCancelar;
    
    private String modo = "";
    private Matriz matriz = new Matriz();
    @FXML
    private Button btnComenzar;
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Circle v, v8, v9, v33, v34, v10, v46, v48, v45, v35, v36, v32, v43, v11, v37, v39, v38, 
            v49, v7, v42, v54, v50, v65, v64, v6, v66, v30, v5, v4, v3, v29, v28, v2, v1, v12,
            v31, v41, v27, v26, v44, v47, v63, v72, v57, v55, v25, v60, v58, v59, v24, v61, v84,
            v62, v73, v74, v53, v83, v51, v40, v13, v14, v69, v52, v68, v67, v71, v76, v75, v23,
            v82, v21, v22, v20, v81, v80, v70, v77, v19, v79, v78, v18, v15, v16, v17, v56, v85, 
            v86, v87, v88, v89, v90;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        addVertices();
        dibujarLineas();
        
        Image image = new Image(getClass().getResourceAsStream("/imagenes/choque.png"));
        ImageView imgView = new ImageView(image);
        imgView.setFitHeight(50);
        imgView.setFitWidth(50);
        btnChoque.setGraphic(imgView);
        
        image = new Image(getClass().getResourceAsStream("/imagenes/trafico.png"));
        imgView = new ImageView(image);
        imgView.setFitHeight(50);
        imgView.setFitWidth(50);
        btnTrafico.setGraphic(imgView);
        
        image = new Image(getClass().getResourceAsStream("/imagenes/calleCerrada.png"));
        imgView = new ImageView(image);
        imgView.setFitHeight(50);
        imgView.setFitWidth(50);
        btnCerrarCalle.setGraphic(imgView);
        
        btnlineas.setSelected(true);
        btnVertices.setSelected(true);
        setVerticesVisible();
        
        lblPunto.setVisible(false);
        btnCancelar.setVisible(false);
        
        btnComenzar.setDisable(true);
        
        
        
    }    
    
    

    @FXML
    private void clickLine(MouseEvent event) {
        Line l = (Line)(event.getSource());
        l.setStroke(Color.ORANGE);
    }
    @FXML
    private void clickCircle(MouseEvent event) {
        Circle c = (Circle)event.getSource();
        
        
        if(modo.equals("seleccionarRuta")){
            modo = "puntoInicio";
            vertices.add(c);
            c.setFill(Color.PINK);
            lblPunto.setText("Seleccione el punto de llegada");
            return;
        }
        if(modo.equals("puntoInicio")){
            modo = "puntoLlegada";
            vertices.add(c);
            c.setFill(Color.PINK);
            lblPunto.setText("");
        }
        if(modo.equals("puntoLlegada")){
            btnComenzar.setDisable(false);
        }
    }  
    
    @FXML
    private void actLineas(ActionEvent event) {
        setLineasVisible();
    } 

    @FXML
    private void actVertices(ActionEvent event) {
        setVerticesVisible();
    }
    private void setLineasVisible(){
        lines.forEach(x->{
            x.setVisible(btnlineas.isSelected());
        });
    }
    
    private void setVerticesVisible(){
        circles.forEach(x->{
            x.setVisible(btnVertices.isSelected());
        });
    }
    
    Matriz m = new Matriz();
    Dijkstra d = new Dijkstra();
    Floyd f = new Floyd();
    GrafoMatriz g = new GrafoMatriz();
    @FXML
    private void actChoque(ActionEvent event) {
        //d.dijkstra(m.getMatriz(), 1, 10);
        f.floyd(91, g, 1, 10);
    }

    @FXML
    private void actTrafico(ActionEvent event) {
    }

    @FXML
    private void actCerrarCalle(ActionEvent event) {
    }
    
    @FXML
    private void actDijkstra(ActionEvent event){
        cbxFloyd.setSelected(false);
        seleccionarRuta();
    }
    
    @FXML
    private void actFloyd(ActionEvent event){
        cbxDijkstra.setSelected(false);
        seleccionarRuta();
    }
    
    private int cont = 0;
    private void addVertices(){

        circles.add(v); circles.add(v1); circles.add(v2); circles.add(v3); circles.add(v4); circles.add(v5); circles.add(v6);
        circles.add(v7); circles.add(v8); circles.add(v9); circles.add(v10); circles.add(v11); circles.add(v12);
        circles.add(v13); circles.add(v14); circles.add(v15); circles.add(v16); circles.add(v17); circles.add(v18);
        circles.add(v19); circles.add(v20); circles.add(v21); circles.add(v22); circles.add(v23); circles.add(v24);
        circles.add(v25); circles.add(v26); circles.add(v27); circles.add(v28); circles.add(v29); circles.add(v30);
        circles.add(v31); circles.add(v32); circles.add(v33); circles.add(v34); circles.add(v35); circles.add(v36);
        circles.add(v37); circles.add(v38); circles.add(v39); circles.add(v40); circles.add(v41); circles.add(v42);
        circles.add(v43); circles.add(v44); circles.add(v45); circles.add(v46); circles.add(v47); circles.add(v48);
        circles.add(v49); circles.add(v50); circles.add(v51); circles.add(v52); circles.add(v53); circles.add(v54);
        circles.add(v55); circles.add(v56); circles.add(v57); circles.add(v58); circles.add(v59); circles.add(v60);
        circles.add(v61); circles.add(v62); circles.add(v63); circles.add(v64); circles.add(v65); circles.add(v66);
        circles.add(v67); circles.add(v68); circles.add(v69); circles.add(v70); circles.add(v71); circles.add(v72);
        circles.add(v73); circles.add(v74); circles.add(v75); circles.add(v76); circles.add(v77); circles.add(v78);
        circles.add(v79); circles.add(v80); circles.add(v81); circles.add(v82); circles.add(v83); circles.add(v84);
        circles.add(v85); circles.add(v86); circles.add(v87); circles.add(v88); circles.add(v89); circles.add(v90);
        
        
        circles.forEach(x-> {
            x.setId(String.valueOf(cont));
            cont++;
        });
    }

    @FXML
    private void actCancelar(ActionEvent event) {
        if(modo.equals("puntoInicio")){
            vertices.get(0).setFill(Color.WHITE);
            vertices.clear();
            modo = "seleccionarRuta";
            lblPunto.setText("Seleccione el punto de partida");  
        }
        if(modo.equals("puntoLlegada")){
            if(vertices.get(1) != null){
                vertices.get(1).setFill(Color.WHITE);
                vertices.remove(1);
                modo = "puntoInicio";
                lblPunto.setText("Seleccione el punto de llegada");
                btnComenzar.setDisable(true);
            }
        }
    }
    
    private void seleccionarRuta(){
        lblPunto.setVisible(true);
        lblPunto.setText("Seleccione el punto de partida");
        btnCancelar.setVisible(true);
        modo = "seleccionarRuta";
    }
    
    private void dibujarLineas(){
        int[][] m = matriz.getMatriz();
        double startX, startY, endX, endY;
        int noCamino = matriz.getNoCamino();
        int cont = 0;
        for(int i=0; i<91; i++){
            for(int j=0; j<91; j++){
                if(m[i][j] != noCamino){
                    cont++;
                    System.out.println(m[i][j]);
                    startX = circles.get(i).getLayoutX();
                    startY = circles.get(i).getLayoutY();
                    endX = circles.get(j).getLayoutX();
                    endY = circles.get(j).getLayoutY();
                    
                    Line l = new Line();
                    l.setId(String.valueOf(cont));
                    l.setStartX(startX);
                    l.setStartY(startY);
                    l.setEndX(endX);
                    l.setEndY(endY);
                    l.setStrokeWidth(10);
                    l.setStroke(Color.DARKCYAN);
                    l.setStrokeLineCap(StrokeLineCap.ROUND);
                    anchorPane.getChildren().add(l);
                    
                    lines.add(l);
                }
            }
        }
        circles.forEach(x-> {
            x.toFront();
        });
    }

    @FXML
    private void actComenzar(ActionEvent event) {
    }
}
   
