/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dtu.example.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileReader {


    public List<String> loadEmployees() throws IOException {
        return loadEmployees(getClass().getClassLoader().getResourceAsStream("initials.txt"));
    }


    public List<String> loadEmployees(InputStream input) throws IOException {
        List<String> initials = new ArrayList<>();

        if (input == null) {
            throw new IllegalArgumentException("File not found!");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.strip();
                if (line.length() == 4) {
                    initials.add(line);
                }
            }
        }

        return initials;
    }
}
