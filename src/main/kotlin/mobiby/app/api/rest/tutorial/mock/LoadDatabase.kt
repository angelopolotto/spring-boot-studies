package mobiby.app.api.rest.tutorial.mock

import mobiby.app.api.rest.tutorial.entity.Employee
import mobiby.app.api.rest.tutorial.entity.Order
import mobiby.app.api.rest.tutorial.entity.Status
import mobiby.app.api.rest.tutorial.repository.EmployeeRepository
import mobiby.app.api.rest.tutorial.repository.OrderRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class LoadDatabase {
    var log = LoggerFactory.getLogger(LoadDatabase::class.java)!!

    @Bean
    fun initDatabase(repository: EmployeeRepository, orderRepository: OrderRepository): CommandLineRunner {
        return CommandLineRunner {
            log.info("Preloading " + repository.save(Employee("Bilbo", "Baggins", "burglar")))
            log.info("Preloading " + repository.save(Employee("Frodo", "Baggins", "thief")))

            orderRepository.save(Order("MacBook Pro", Status.COMPLETED))
            orderRepository.save(Order("iPhone", Status.IN_PROGRESS))

            orderRepository.findAll().forEach {
                log.info("Preloaded $it")
            }
        }
    }
}