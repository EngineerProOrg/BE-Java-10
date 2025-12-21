
* Run DLQ Listener
./gradlew bootRun --args='--myapp.runDLQ.enabled=true'

* Reset RabbitMQ:

rabbitmqctl stop_app
rabbitmqctl reset    # Be sure you really want to do this!
rabbitmqctl start_app
