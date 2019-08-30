package mobiby.app.api.rest.tutorial.controller

import mobiby.app.api.rest.tutorial.assembler.OrderResourceAssembler
import mobiby.app.api.rest.tutorial.entity.Order
import mobiby.app.api.rest.tutorial.entity.Status
import mobiby.app.api.rest.tutorial.exception.OrderNotFoundException
import mobiby.app.api.rest.tutorial.repository.OrderRepository
import org.springframework.hateoas.Resource
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.Resources
import org.springframework.hateoas.VndErrors
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
class OrderController(val orderRepository: OrderRepository,
                      val assembler: OrderResourceAssembler) {
    @GetMapping("/orders")
    fun all(): Resources<Resource<Order>> {
        val orders = orderRepository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList())
        return Resources(orders,
                linkTo(methodOn(OrderController::class.java).all()).withSelfRel())
    }

    @GetMapping("/orders/{id}")
    fun one(@PathVariable id: Long): Resource<Order> {
        return assembler.toResource(
                orderRepository.findById(id)
                        .orElseThrow { OrderNotFoundException(id) }
        )
    }

    @PostMapping("/orders")
    fun newOrder(@RequestBody order: Order): ResponseEntity<Resource<Order>> {
        order.status = Status.IN_PROGRESS
        val newOrder = orderRepository.save(order)

        return ResponseEntity.created(
                linkTo(methodOn(OrderController::class.java).one(newOrder.id!!)).toUri())
                .body(assembler.toResource(newOrder))
    }

    @DeleteMapping("/orders/{id}/cancel")
    fun cancel(@PathVariable id: Long): ResponseEntity<ResourceSupport> {
        val order = orderRepository.findById(id).orElseThrow { OrderNotFoundException(id) }

        if (order.status == Status.IN_PROGRESS) {
            order.status = Status.CANCELLED
            return ResponseEntity.ok(assembler.toResource(orderRepository.save(order)))
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(
                        VndErrors.VndError("Method not allowed",
                                "You can't cancel an order that is in the ${order.status} status")
                )
    }

    @PutMapping("/orders/{id}/complete")
    fun complete(@PathVariable id: Long): ResponseEntity<ResourceSupport> {
        val order = orderRepository.findById(id).orElseThrow { OrderNotFoundException(id) }

        if (order.status == Status.IN_PROGRESS) {
            order.status = Status.COMPLETED
            return ResponseEntity.ok(assembler.toResource(orderRepository.save(order)))
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(
                        VndErrors.VndError("Method not allowed",
                                "You can't complete an order that is in the ${order.status} status")
                )
    }
}