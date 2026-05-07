/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dtu.superPlanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
;

public class FileEmployeeRepository implements EmployeeRepository{

    private Map<String, Employee> employees ;
    private boolean skippedLines;

    public FileEmployeeRepository(InputStream input) throws IOException{
        skippedLines = false;
        employees = loadEmployees(input);
    }


    public Set<String> getEmployeeInitials() {
        return employees.keySet();
    }

    private final Map<String, Employee> loadEmployees(InputStream input) throws IOException {
        Map<String, Employee> loadedEmployees = new TreeMap<>();

        if (input == null) {
            throw new IllegalArgumentException("Input file cannot be null");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String initials;
            while ((initials = reader.readLine()) != null) {
                initials = initials.strip();
                if (initials.length() == 4) {
                    loadedEmployees.put(initials, new Employee(initials));
                } else {
                    skippedLines = true;
                }
            }
        } catch (IOException ex) {
            throw new IOException("Failed reading input file");
        }
        return loadedEmployees;
    }


    @Override
    public Employee get(String initials) {
        return employees.get(initials);
    }


    @Override
    public boolean contains(String initials) {
        return employees.containsKey(initials);
    }


    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public void addEmployee(String initials) {
        employees.put(initials, new Employee(initials));
    }


    public boolean getSkippedLines() {
        return skippedLines;
    }
}
