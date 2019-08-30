package mobiby.app.api.rest.tutorial.controller

import mobiby.app.api.rest.tutorial.assembler.EmployeeResourceAssembler
import mobiby.app.api.rest.tutorial.entity.Employee
import mobiby.app.api.rest.tutorial.exception.EmployeeNotFoundException
import mobiby.app.api.rest.tutorial.repository.EmployeeRepository
import org.springframework.hateoas.Resource
import org.springframework.hateoas.Resources
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.net.URISyntaxException
import java.util.stream.Collectors


@RestController
class EmployeeController(val repository: EmployeeRepository, val assembler: EmployeeResourceAssembler) {
    @GetMapping("/employees")
    fun all(): Resources<Resource<Employee>> {
        val employees = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList())

        return Resources(employees,
                linkTo(methodOn(EmployeeController::class.java).all()).withSelfRel()
        )
    }

    @PostMapping("/employees")
    @Throws(URISyntaxException::class)
    fun newEmployee(@RequestBody newEmployee: Employee): ResponseEntity<*> {
        val resource = assembler.toResource(repository.save(newEmployee))
        return ResponseEntity
                .created(URI(resource.id.expand().href))
                .body(resource)
    }

    @GetMapping("/employees/{id}")
    fun one(@PathVariable id: Long): Resource<Employee> {
        val employee = repository.findById(id)
                .orElseThrow { EmployeeNotFoundException(id) }

        return assembler.toResource(employee)
    }

    @PutMapping("/employees/{id}")
    @Throws(URISyntaxException::class)
    fun replaceEmployee(@RequestBody newEmployee: Employee, @PathVariable id: Long): ResponseEntity<*> {
        val updatedEmployee = repository.findById(id).map { employee ->
            employee.name = newEmployee.name
            employee.role = newEmployee.role
            repository.save(employee)
        }
        .orElseGet {
            newEmployee.id = id
            repository.save(newEmployee)
        }

        val resource = assembler.toResource(updatedEmployee)

        return ResponseEntity
                .created(URI(resource.id.expand().href))
                .body(resource)
    }

    @DeleteMapping("/employees/{id}")
    fun deleteEmployee(@PathVariable id: Long): ResponseEntity<*> {
        repository.deleteById(id)
        return ResponseEntity.noContent().build<Any>()
    }
}