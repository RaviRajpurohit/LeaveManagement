package com.practice;

import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class LeaveManager {

    private static final Logger logger = Logger.getLogger(LeaveManager.class.getName());

    CellProcessor[] employeeProcessors = new CellProcessor[]{
            new ParseLong(), // for empid
            new NotNull(), // for name
            new ParseInt(), // for leave applied
            new ParseInt() // for available leaves
    };

    CellProcessor[] leaveProcessors = new CellProcessor[]{
            new ParseLong(), // for empid
            new ParseInt() // for leave applied
    };


    private Map<Long, Employee> readEmployees() throws IOException {
        try (ICsvBeanReader beanReader = new CsvBeanReader(new FileReader("../leave-management/src/main/resources/EmployeeData.csv"),
                CsvPreference.STANDARD_PREFERENCE)) {

            String[] header = beanReader.getHeader(true);

            Map<Long, Employee> employeeMap = new HashMap<>(beanReader.getLineNumber());

            Employee employee;
            while ((employee = beanReader.read(Employee.class, header, employeeProcessors)) != null) {
                employeeMap.put(employee.getEmpId(), employee);
            }
            return employeeMap;
        }
    }

    private List<LeaveApplication> readAppliedLeaves() throws IOException {
        try (ICsvBeanReader beanReader = new CsvBeanReader(new FileReader("../leave-management/src/main/resources/Leaves.csv"),
                CsvPreference.STANDARD_PREFERENCE)) {

            String[] header = beanReader.getHeader(true);

            List<LeaveApplication> applications = new ArrayList<>(beanReader.getLineNumber());

            LeaveApplication leaveApplication;
            while ((leaveApplication = beanReader.read(LeaveApplication.class, header, leaveProcessors)) != null) {
                applications.add(leaveApplication);
            }
            return applications;
        }
    }

    /**
     * Method to manage leaves.
     */
    public void manageLeaves() throws IOException {
        logger.entering(LeaveManager.class.getName(), "manageLeaves");

        List<LeaveApplication> applications = this.readAppliedLeaves();

        if (applications.isEmpty()) {
            //no application applied yet.
            logger.info("No leaves applies from any employee.");
            return;
        }

        Map<Long, Employee> employees = this.readEmployees();

        for (LeaveApplication application : applications) {
            long empId = application.getEmpId();
            int appliedLeaves = application.getAppliedLeaves();

            Employee employee = employees.get(empId);
            if (employee.getAvailableLeaves() >= appliedLeaves) {
                logger.info(employee.getName() + " is eligible for the leave.");
                System.out.println(employee.getName() + " is eligible for the leave.");
                employee.applyForLeave(appliedLeaves);
            } else {
                logger.info(employee.getName() + " is not eligible for the leave.");
                System.out.println(employee.getName() + " is not eligible for the leave.");
            }
        }

        this.updateFiles(employees);
        logger.exiting(LeaveManager.class.getName(), "manageLeaves");
    }

    private void updateFiles(Map<Long, Employee> employees) throws IOException {
        try (ICsvBeanWriter beanWriter = new CsvBeanWriter(new FileWriter("../leave-management/src/main/resources/EmployeeData.csv"),
                CsvPreference.STANDARD_PREFERENCE)) {
            String[] header = {"empId", "name", "leavesTaken", "availableLeaves"};
            beanWriter.writeHeader(header);

            for (Employee employee : employees.values()) {
                beanWriter.write(employee, header, employeeProcessors);
            }
        }

        try (ICsvBeanWriter beanWriter = new CsvBeanWriter(new FileWriter("../leave-management/src/main/resources/Leaves.csv"),
                CsvPreference.STANDARD_PREFERENCE)) {
            String[] header = {"empId", "appliedLeaves"};
            beanWriter.writeHeader(header);

        }
    }

}
