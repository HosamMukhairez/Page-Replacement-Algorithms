/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.cio.kurumsal.JavaFX_PageReplacement_Project;

import static com.cio.kurumsal.JavaFX_PageReplacement_Project.App.stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Properties;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class FXMLController {

    int frame_size = 0;
    int capacity = 0;
    String reference_string[];
    String burst_time[];
    int quantum;

    @FXML
    TextField fileField;

    @FXML
    TextArea resulTextArea;

    public void readPropertiesFile(String filePath) {
        try {
            //FileReader reader = new FileReader("configuration_file.properties");
            FileReader reader = new FileReader(filePath);
            Properties p = new Properties();
            p.load(reader);
            burst_time = p.getProperty("burst_time").split(" ");
            quantum = Integer.parseInt(p.getProperty("quantum"));
            frame_size = Integer.parseInt(p.getProperty("frame_size"));
            capacity = Integer.parseInt(p.getProperty("capacity"));
            reference_string = p.getProperty("reference_string").split(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void getGeneralJVMInfo() throws IOException {
        // get the runtime object associated with the current Java application
        Runtime runtime = Runtime.getRuntime();

        long freeMemory = runtime.freeMemory();
        System.out.println("Free memory in JVM (bytes): " + freeMemory);

        long maxMemory = runtime.maxMemory();
        System.out.println("Max memory in JVM (bytes): " + maxMemory);

        long totalMemory = runtime.totalMemory();
        System.out.println("Total memory in JVM (bytes): " + totalMemory);
    }

    @FXML
    private void switchToFIFO() throws IOException {
        readPropertiesFile(fileField.getText());
        resulTextArea.setText(Arrays.toString(reference_string) + "\nPage Faults:\n" + new Fifo().pageFaults(reference_string, reference_string.length, capacity));
    }

    @FXML
    private void switchToSecondchanceFIFO() throws IOException {
        readPropertiesFile(fileField.getText());
        resulTextArea.setText(Arrays.toString(reference_string) + "\nPage Faults:\n" + new SecondChance().printHitsAndFaults(reference_string, frame_size));
    }

    @FXML
    private void switchToClock() throws IOException {
        readPropertiesFile(fileField.getText());
        resulTextArea.setText(Arrays.toString(reference_string) + "\nPage Faults:\n" + new ClockReplacement().ClockPaging(frame_size, reference_string.length, reference_string));
    }

    @FXML
    private void switchToLRU() throws IOException {
        readPropertiesFile(fileField.getText());
        resulTextArea.setText(Arrays.toString(reference_string) + "\nPage Faults:\n" + new LeastRecentlyUsed().pageFaults(reference_string, reference_string.length, capacity));
    }

    @FXML
    private void switchToRR() throws IOException {
        readPropertiesFile(fileField.getText());
        resulTextArea.setText(Arrays.toString(reference_string) + "\n" + new RoundRobin().findavgTime(reference_string, reference_string.length, burst_time, quantum));
    }

    @FXML
    private void switchToProcessesList() throws IOException {
        try {
            String processesListData = "";
            String process;
            // getRuntime: Returns the runtime object associated with the current Java application.
            // exec: Executes the specified string command in a separate process.
            Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((process = input.readLine()) != null) {
                System.out.println(process); // <-- Print all Process here line
                processesListData += process + "\n";
                // by line
            }
            input.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Process PID List");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(processesListData)));
            alert.showAndWait();//*/

            /*List<ProcessInfo> processesList = JProcesses.getProcessList();

            for (final ProcessInfo processInfo : processesList) {
                System.out.println("Process PID: " + processInfo.getPid());
                System.out.println("Process Name: " + processInfo.getName());
                System.out.println("Process Time: " + processInfo.getTime());
                System.out.println("User: " + processInfo.getUser());
                System.out.println("Virtual Memory: " + processInfo.getVirtualMemory());
                System.out.println("Physical Memory: " + processInfo.getPhysicalMemory());
                System.out.println("CPU usage: " + processInfo.getCpuUsage());
                System.out.println("Start Time: " + processInfo.getStartTime());
                System.out.println("Priority: " + processInfo.getPriority());
                System.out.println("Full command: " + processInfo.getCommand());
                System.out.println("------------------");
            }//*/
 /*MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

            System.out.println(String.format("Initial memory: %.2f GB", (double) memoryMXBean.getHeapMemoryUsage().getInit() / 1073741824));

            System.out.println(String.format("Used heap memory: %.2f GB", (double) memoryMXBean.getHeapMemoryUsage().getUsed() / 1073741824));

            System.out.println(String.format("Max heap memory: %.2f GB", (double) memoryMXBean.getHeapMemoryUsage().getMax() / 1073741824));

            System.out.println(String.format("Committed memory: %.2f GB", (double) memoryMXBean.getHeapMemoryUsage().getCommitted() / 1073741824));//*/
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    @FXML
    private void switchToBrowse() throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        fileField.setText(selectedFile.getPath());
    }
}
