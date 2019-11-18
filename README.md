# currency-exchange

### From Spring initializer

* Web
* Actuator
* dev tools
* config client
* jpa
* H2 Db


#### JDBC URL
http://localhost:8000/h2-console

JDBC URL: jdbc:h2:mem:testdb

# Running multiple instances

Goto Run -> Edit Configurations and duplicate the spring boot profile

![pic](./vmArgument2.png)

Set the VM Options as follows 
```shell script
-Dserver.port=8001
```

This will override the server.port value set in the application.properties file
![pic](./vmArgument.png)

Run the two instances together