# LeaveManagement 
****

<h3>Problem Statement</h3>
<p>
  Company XYZ is managing its leave module by maintaining 2 CSV files in their &quot;Leaves&quot;
directory
  <ul>
    <li>EmployeeData.csv</li>
    <li>Leaves.csv</li>
  </ul>
Employees&#39; data present in EmployeeData.csv file in below format -
<table>
  <tr>
    <th>empId</th><th>name</th><th>leavesTaken</th><th>availableLeaves</th>
  </tr>
  <tr>
    <td>1</td><td>Mayank</td><td>2</td><td>18</td>
  </tr>
  <tr>
    <td>2</td><td>Keyur</td><td>5</td><td>15</td>
  </tr>
  <tr>
    <td>3</td><td>Rahim</td><td>18</td><td>2</td>
  </tr>
</table></br>
An employee can apply for leave by adding an entry in the Leaves.csv file in below format.
<table>
  <tr>
    <th>empId</th><th>appliedLeaves</th>
  </tr>
  <tr>
    <td>1</td><td>4</td>
  </tr>
  <tr>
    <td>2</td><td>3</td>
  </tr>
</table></br>
The program will initially read data from the EmployeeData.csv file and continuously monitor
Leaves.csv.</br>
When any employee has applied for leaves then perform below operations every minute -
The program will initially read data from the EmployeeData.csv file and <b>continuously monitor</b>
Leaves.csv.</br>
When any employee has applied for leaves then perform below operations every minute - 
<ol>
  <li>Check whether the employee is eligible for the leave(s) or not based on available Leaves
    value 
    <ol>
      <li> If the employee is eligible for the leave then print in the console: &lt;name&gt; is
eligible for the leave.
      <li> If the employee is not eligible for the leave then print in the console: &lt;name&gt; is
not eligible for the leave.</li>
    </ol>
  </li>
  <li> if the employee is eligible for the leave, then remove the entry from Leaves.csv and
update the &quot;leavesTaken&quot; and &quot;available Leaves&quot; in EmployeeData.csv.</li>
  <li> If the employee is not eligible for the leave then remove the entry from Leaves.csv.</li>
    </ol>
</p>
  
<h3>Solution Approach</h3>
<h5>Keywords</h5>
<ul>
  <li>Reading from File System [Super CSV](http://super-csv.github.io/super-csv/index.html) </li>
  <li>Schedule the program [Timer Task](https://docs.oracle.com/javase/7/docs/api/java/util/TimerTask.html) </li>
</ul>
<h5>Approach</h5>
<ul>
  <li>Application must schedule on time frequency</li>
  <li>Application should not create new CSV files, must be use old file and update if needed</li>
</ul>
<h5>Algorithm</h5>
<ul>
  <li>Create a cron job to run program after time frequency</li>
  <li>First read the Leaves.csv if any leave application found then read the EmployeeData.csv else skip the processing</li>
  <li>Schedule frequency should be user configurable.</li>
</ul>
