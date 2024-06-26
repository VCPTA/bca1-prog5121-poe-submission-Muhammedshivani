/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_final_st10305875;

import javax.swing.JOptionPane;
import java.util.*;

/**
 *
 * @author lab_services_student
 */ 
public class Login {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String registerUser;
    private String registerPass;
    private int taskCount = 0;
    private List<String> developers = new ArrayList<>();
    private List<String> taskNames = new ArrayList<>();
    private List<String> taskIDs = new ArrayList<>();
    private List<Integer> taskDurations = new ArrayList<>();
    private List<String> taskStatuses = new ArrayList<>();
    
public Login() {
    }

    public void menu() {
        String[] options = {"Register", "Login", "Exit"};
        String choice = (String) JOptionPane.showInputDialog(null, "Login Management System Menu", "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        switch (choice) {
            case "Register":
                registerUser();
                break;
            case "Login":
                adminLogin();
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                menu();
                break;
        }
    }

    private void registerUser() {
        firstName = JOptionPane.showInputDialog(null, "Enter Your First Name");
        lastName = JOptionPane.showInputDialog(null, "Enter Your Last Name");
        registerUser = JOptionPane.showInputDialog(null, "Create Username", "Enter Your Username: ");
        registerPass = JOptionPane.showInputDialog(null, "Create Password", "Enter Your Password: ");
        username = registerUser;
        password = registerPass;
        JOptionPane.showMessageDialog(null, "Registration Successful! You can now login.");
        menu();
    }

    private void adminLogin() {
        String inputUsername = JOptionPane.showInputDialog(null, "Enter Your Username:");
        String inputPassword = JOptionPane.showInputDialog(null, "Enter Your Password:");
        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            JOptionPane.showMessageDialog(null, "Welcome " + firstName + " " + lastName + "! It Is Great To See You Again.");
            boolean keepRunning = true;
            while (keepRunning) {
                String[] loggedInOptions = {"Add Tasks", "Show Task Status", "Quit"};
                String loggedInChoice = (String) JOptionPane.showInputDialog(null, "Logged In Menu", "Menu", JOptionPane.PLAIN_MESSAGE, null, loggedInOptions, loggedInOptions[0]);
                switch (loggedInChoice) {
                    case "Add Tasks":
                        addTasks();
                        break;
                    case "Show Task Status":
                        showTaskStatus();
                        break;
                    case "Quit":
                        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Quit", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            keepRunning = false;
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                        break;
                }
            }
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
            menu();
        }
    }

    private void addTasks() {
        int numberOfTasks = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of tasks you wish to enter:"));
        for (int i = 0; i < numberOfTasks; i++) {
            String taskName = JOptionPane.showInputDialog(null, "Enter Task Name:");
            String taskDescription = JOptionPane.showInputDialog(null, "Enter Task Description (max 50 characters):");
            while (taskDescription.length() > 50) {
                taskDescription = JOptionPane.showInputDialog(null, "Description should not exceed 50 characters. Please re-enter Task Description:");
            }
            if (taskDescription.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No description was added. Task not captured.");
                continue;
            }
            String author = JOptionPane.showInputDialog(null, "Enter Author of the Task:");
            int duration = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Task Duration (in hours):"));
            String[] statuses = {"To Do", "Doing", "Done"};
            String selectedStatus = (String) JOptionPane.showInputDialog(null, "Select Task Status", "Task Status", JOptionPane.PLAIN_MESSAGE, null, statuses, statuses[0]);
            taskCount++;
            String taskID = generateTaskID(taskName, taskCount, lastName);

            developers.add(author);
            taskNames.add(taskName);
            taskIDs.add(taskID);
            taskDurations.add(duration);
            taskStatuses.add(selectedStatus);

            StringBuilder taskInfo = new StringBuilder("Task ID: " + taskID + "\n");
            taskInfo.append("Name: ").append(taskName).append("\n");
            taskInfo.append("Description: ").append(taskDescription).append("\n");
            taskInfo.append("Status: ").append(selectedStatus).append("\n");
            taskInfo.append("Author: ").append(author).append("\n");
            taskInfo.append("Duration: ").append(duration).append(" hours\n");
            JOptionPane.showMessageDialog(null, "Task successfully captured!\n\n" + taskInfo.toString());
        }
    }

    private void showTaskStatus() {
        String[] statuses = {"To Do", "Doing", "Done"};
        String selectedStatus = (String) JOptionPane.showInputDialog(null, "Select Task Status", "Task Status", JOptionPane.PLAIN_MESSAGE, null, statuses, statuses[0]);
        StringBuilder taskList = new StringBuilder("Tasks in " + selectedStatus + ":\n");
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskStatuses.get(i).equals(selectedStatus)) {
                taskList.append("Task Name: ").append(taskNames.get(i)).append("\n");
                taskList.append("Developer: ").append(developers.get(i)).append("\n");
                taskList.append("Duration: ").append(taskDurations.get(i)).append(" hours\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, taskList.toString());
    }

    private String generateTaskID(String taskName, int taskNumber, String lastName) {
        return taskName.substring(0, Math.min(taskName.length(), 3)).toUpperCase() + ":" + taskNumber + ":" + lastName.substring(Math.max(0, lastName.length() - 3)).toUpperCase();
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.menu();
    }
}

