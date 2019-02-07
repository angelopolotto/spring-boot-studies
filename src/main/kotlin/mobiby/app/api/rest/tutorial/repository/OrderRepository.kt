package mobiby.app.api.rest.tutorial.repository

import mobiby.app.api.rest.tutorial.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>