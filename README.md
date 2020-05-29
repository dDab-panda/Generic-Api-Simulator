# Generic-Api-Simulator
To simulate Web APIs via simple configuration

# About the Project

Most commonly, applications depend on external APIs. The developing and testing of the application 
is hindered when any one of these APIs is not available or the use of these APIs is restricted.

This project enables simulation of Rest APIs through configuration written in JSON format.

# Built With

* Java 8
* Spring
* Maven

# Usage

There are two kinds of configuration files that you need to provide as input to this application.
These files need to be in JSON format.

1. config.json
2. Magic Data files

Sample configuraton files are already included in the project. They are located at "application/src/main/resources/"
Edit the configuration files according to the API you want to simulate and the responses you want to receive.

Finally, run the JAR to start you simulator.

To test your application, send requests to your endpoints prefixed by "/simulator" so that your request is received by
the simulator.

You can view the configuration file you have loaded by hitting the "/config" endpoint 
and the configuration map created by this application at "/configmap" (No "/simulator" prefix).

# Further features

* Proper validation rules to be defined and validators added for configuration files and incoming requests. 
Currently, no implementation to throw detailed error messages is present.
* Currently, only the simulation of Rest APIs through JSON configuration is supported.
Support can be extended to SOAP APIs (and other APIs) and also configurations written in .xml (and other formats)
by adding appropriate validators, request marshaling and handlers. 
* UI for writing configuration files.
