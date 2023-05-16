import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the x and y axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Create the bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Example Bar Graph");

        // Define the data series
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.getData().add(new XYChart.Data<>("Category 1", 10));
        dataSeries.getData().add(new XYChart.Data<>("Category 2", 20));
        dataSeries.getData().add(new XYChart.Data<>("Category 3", 15));
        dataSeries.getData().add(new XYChart.Data<>("Category 4", 5));
        dataSeries.getData().add(new XYChart.Data<>("Category 5", 30));

        // Add the data series to the bar chart
        barChart.getData().add(dataSeries);

        // Create the scene and add the bar chart to it
        Scene scene = new Scene(barChart, 400, 300);

        // Set the stage's title and scene
        primaryStage.setTitle("Bar Graph Example");
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
