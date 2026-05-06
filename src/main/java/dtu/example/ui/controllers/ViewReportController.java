package dtu.example.ui.controllers;

import java.io.IOException;
import java.util.Map;

import dtu.example.ui.ReportAware;
import dtu.superPlanner.Report;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

public class ViewReportController extends ProjectManagementAwareController implements ReportAware{

    @FXML
    private PieChart timeOverviewPieChart;
    @FXML
    private Label reportNameLabel;
    
    @FXML
    private StackedBarChart<String, Number> activityBarChart;


    private void setChartData(Report report) {
        if (report == null) {
            return;
        }

        timeOverviewPieChart.getData().clear();

        timeOverviewPieChart.getData().addAll(
            new PieChart.Data("Time left", report.getTimeLeft()),
            new PieChart.Data("Time spent", report.getTimeSpent())
        );
    }

    private void setBarChart(Report report) {
        activityBarChart.getData().clear();
        activityBarChart.layout();
        XYChart.Series<String, Number> timeSpent = new XYChart.Series<>();
        XYChart.Series<String, Number> timeLeft = new XYChart.Series<>();
        timeSpent.setName("Time spent");
        timeLeft.setName("Time left");
        for (Map.Entry<Integer, Report.ReportEntry> entry : report.getEntries().entrySet()) {
            Report.ReportEntry reportEntry = entry.getValue();
            timeSpent.getData().add(new XYChart.Data<>(entry.getKey() + " - " + reportEntry.name(), reportEntry.timeSpent()));
            timeLeft.getData().add(new XYChart.Data<>(entry.getKey() + " - " + reportEntry.name(), reportEntry.timeLeft()));
        }
        activityBarChart.getData().add(timeLeft);
        activityBarChart.getData().add(timeSpent);
    }

    private void setReportName(String name) {
        reportNameLabel.setText("Report: " + name);
    }

    public void setReport(Report report) {
        setChartData(report);
        setBarChart(report);
        setReportName(report.getProjectName());
    }

    @FXML
    public void onExit() throws IOException{
        navigator.changeScene("project_list");
    }
}