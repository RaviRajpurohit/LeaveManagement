package com.practice;

public class Employee {

    private long empId;
    private String name;
    private int leavesTaken;
    private int availableLeaves;

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public long getEmpId() {
        return empId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAvailableLeaves(int availableLeaves) {
        this.availableLeaves = availableLeaves;
    }

    public int getAvailableLeaves() {
        return availableLeaves;
    }

    public void setLeavesTaken(int leavesTaken) {
        this.leavesTaken = leavesTaken;
    }

    public int getLeavesTaken() {
        return leavesTaken;
    }

    /**
     * Method will update the leave taken and available leaves as per request.
     *
     * @param appliedLeaves - number of leaves.
     * @return total available leaves.
     */
    public int applyForLeave(int appliedLeaves) {
        this.availableLeaves -= appliedLeaves;
        this.leavesTaken += appliedLeaves;
        return availableLeaves;
    }
}
