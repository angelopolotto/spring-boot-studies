package mobiby.app.api.rest.tutorial.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "CUSTOMER_ORDER")
data class Order(var description: String, var status: Status) {
    @Id
    @GeneratedValue
    var id: Long? = null
}