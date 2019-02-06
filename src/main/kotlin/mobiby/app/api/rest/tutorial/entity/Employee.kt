package mobiby.app.api.rest.tutorial.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Employee(var name: String, var role: String) {
    @Id
    @GeneratedValue
    var id: Long? = null
}