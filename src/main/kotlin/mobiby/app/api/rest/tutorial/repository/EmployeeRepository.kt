package mobiby.app.api.rest.tutorial.repository

import mobiby.app.api.rest.tutorial.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long>