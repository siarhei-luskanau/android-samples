package siarhei.luskanau.example.dagger.model

class CommonHelloService {

    fun sayHello(): String = HELLO

    companion object {
        const val HELLO = "Hello from CommonHelloService"
    }
}
