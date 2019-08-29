package mobiby.app.api.rest.tutorial.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Employee(var firstName: String?, var lastName: String?, var role: String) {
    @Id
    @GeneratedValue
    var id: Long? = null
    var name: String
        get() = "$firstName $lastName"
        set(value) {
            val parts = value.split(" ")
            firstName = parts[0]
            lastName = parts[1]
        }
}