package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class EmployeeDao implements Dao<Employee,Long> {

    private final Optional<Connection> connection;
    public EmployeeDao() {
        this.connection = DbConnection.getConnection();
    }

    private static final Logger LOGGER =
            Logger.getLogger(EmployeeDao.class.getName());



    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        Optional<Employee> Employee = Optional.empty();
        String sql = "SELECT * FROM Employee WHERE Employee_id = " + id;

        try (Statement statement = connection.get().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            if(resultSet.next()) {
                Long employeeId= resultSet.getLong("employee_id");
                Long departmentId = resultSet.getLong("department_id");
                String jobTitle = resultSet.getString("job_title");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Gender gender = Gender.valueOf(resultSet.getString("gender").toUpperCase(Locale.ROOT));
                Date dateOfBirth = resultSet.getDate("date_of_birth");
                Employee = Optional.of(
                        new Employee(employeeId, firstName,gender,departmentId,jobTitle,lastName,dateOfBirth ));

                LOGGER.log(Level.INFO, "Found {0} in database", Employee.get());
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return Employee;

    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        String sql = "SELECT * FROM Employee";

        try (Statement statement = connection.get().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while(resultSet.next()) {
                Long employeeId= resultSet.getLong("employee_id");
                Long departmentId = resultSet.getLong("department_id");
                String jobTitle = resultSet.getString("job_title");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Gender gender = Gender.valueOf(resultSet.getString("gender").toUpperCase(Locale.ROOT));
                Date dateOfBirth = resultSet.getDate("date_of_birth");

                employee = new Employee(employeeId, firstName,gender,departmentId,jobTitle,lastName,dateOfBirth );
                employees.add(employee);

                LOGGER.log(Level.INFO, "Found {0} in database", employee);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    @Override
    public Optional<Long> saveEmployee(Employee employee) {
        String sql = "INSERT INTO employee" +
                    "(\"first_name\",\"last_name\",\"department_id\",\"gender\",\"date_of_birth\",\"job_title\") " +
                    "VALUES ('" +
                    employee.getFirstName() + "','" +
                    employee.getLastName() + "','" +
                    employee.getDepartmentId() + "','" +
                    employee.getGender() + "','" +
                    employee.getDateOfBirth() + "','" +
                    employee.getJobTitle() + "');";
        try (Statement statement = connection.get().createStatement()) {
            statement.executeQuery(sql);
            LOGGER.log(Level.INFO, "Added to database", employee);
        }
              catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        return Optional.empty();
    }

    @Override
    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET "
                + "first_name='" + employee.getFirstName() + "',"
                + "last_name='" + employee.getLastName() + "',"
                + "department_id=" + employee.getDepartmentId() + ","
                + "gender='" + employee.getGender() + "',"
                + "date_of_birth='"+employee.getDateOfBirth() + "',"
                + "job_title='"+employee.getJobTitle() + "' "
                + "WHERE employee_id="+employee.getEmployeeId()+";";
        System.out.println(sql);
        try (Statement statement = connection.get().createStatement()) {
            statement.executeQuery(sql);
            LOGGER.log(Level.INFO, "Added to database", employee);
        }
        catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        String sql = "DELETE FROM employee WHERE employee_id=" + id;
        try (Statement statement = connection.get().createStatement()) {
            statement.executeQuery(sql);
            LOGGER.log(Level.INFO, "Deleted from database with id = ", id);
        }
        catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
