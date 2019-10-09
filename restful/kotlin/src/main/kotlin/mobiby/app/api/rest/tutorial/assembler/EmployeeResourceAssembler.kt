package mobiby.app.api.rest.tutorial.assembler

import mobiby.app.api.rest.tutorial.controller.EmployeeController
import mobiby.app.api.rest.tutorial.entity.Employee
import org.springframework.hateoas.Resource
import org.springframework.hateoas.ResourceAssembler
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Component
class EmployeeResourceAssembler : ResourceAssembler<Employee, Resource<Employee>> {
    override fun toResource(employee: Employee): Resource<Employee> {
        return Resource(employee,
                linkTo(methodOn(EmployeeController::class.java).one(employee.id!!)).withSelfRel(),
                linkTo(methodOn(EmployeeController::class.java).all()).withRel("employees"))
    }
}