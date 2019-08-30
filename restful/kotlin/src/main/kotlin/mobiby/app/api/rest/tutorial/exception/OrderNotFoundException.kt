package mobiby.app.api.rest.tutorial.exception

class OrderNotFoundException(id: Long) : RuntimeException("Could not find order $id")
