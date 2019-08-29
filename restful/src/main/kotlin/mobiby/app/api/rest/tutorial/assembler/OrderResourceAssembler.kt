package mobiby.app.api.rest.tutorial.assembler

import mobiby.app.api.rest.tutorial.controller.OrderController
import mobiby.app.api.rest.tutorial.entity.Order
import mobiby.app.api.rest.tutorial.entity.Status
import org.springframework.hateoas.Resource
import org.springframework.hateoas.ResourceAssembler
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Component
class OrderResourceAssembler : ResourceAssembler<Order, Resource<Order>> {
    override fun toResource(order: Order): Resource<Order> {
        //unconditional links to single-item resource and aggregate root
        val orderResource = Resource(order,
                linkTo(methodOn(OrderController::class.java).one(order.id!!)).withSelfRel(),
                linkTo(methodOn(OrderController::class.java).all()).withRel("orders"))

        //conditional links based on state of order
        if (order.status == Status.IN_PROGRESS) {
            orderResource.add(
                    linkTo(methodOn(OrderController::class.java).cancel(order.id!!)).withRel("cancel")
            )
            orderResource.add(
                    linkTo(methodOn(OrderController::class.java).complete(order.id!!)).withRel("complete")
            )
        }

        return orderResource
    }
}