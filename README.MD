## Role-Assigner

An application configured to autoassign roles with one kafka producer and one kafka consumer.
This application works as a remote microservice for another application

## Scope


The scope of this pet project was to implement autoassigning roles
as an outside microservice for a [@MeltonSmith's](https://github.com/MeltonSmith) project [HYIP test](https://github.com/MeltonSmith/hyipTest)

##Structure

Service works in 3 simple stages

1. After connection to kafka server, application listens to
   `topicToListen` which is configured in `application.yml`
   

2. Application gets user information from the topic, stores user's id,
   then assignes roles at at random with ```decideRole()``` method;
   

3. Application sends user's id and role to the `processedUsers` topic
which is configured in `application.yml`
   
   
  ###Many thanks to @MeltonSmith for providing kafka server and advice 