package ru.rutmiit.repositories;

import ru.rutmiit.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("SELECT e FROM Employee AS e WHERE CONCAT(e.firstName, ' ', e.lastName) = :fullName")
    Employee findEmployeeByFullName(String fullName);

    @Modifying
    @Transactional
    @Query("DELETE FROM Employee AS e WHERE CONCAT(e.firstName, ' ', e.lastName) = :fullName")
    void deleteEmployeeByFullName(String fullName);
}
