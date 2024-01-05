## Drone Management System using Docker Compose
Introduction
This project showcases the implementation of a Drone Management System utilizing Docker Compose. Drones, with their potential to revolutionize transportation, are the focal point. Similar to how mobile phones transformed personal communication, drones have the capability to revolutionize traditional transportation infrastructure, particularly in locations with challenging access.

## Task Description
The project involves a fleet of 10 drones, each capable of carrying small loads, with a specific focus on delivering medications. Each drone is characterized by the following attributes:

Serial Number: A unique identifier for each drone (maximum 100 characters).
Model: The drone's model, categorized as Lightweight, Middleweight, Cruiserweight, or Heavyweight.
Weight Limit: The maximum weight the drone can carry (500 grams maximum).
Battery Capacity: The remaining battery capacity, expressed as a percentage.
State: The operational state of the drone, which can be IDLE, LOADING, LOADED, DELIVERING, DELIVERED, or RETURNING.
How to Run
## To run the Drone Management System, follow these steps:

Make sure you have Docker and Docker Compose installed on your machine.

## Prerequisites

- Java JDK (version 17)
- Maven 

## Clone the repository:

                 git clone https://github.com/GaneshThapab7/transportation_task.git

 1:Navigate to the project directory:
   
                 cd transportation
 2:Build the project with Maven:

                 mvn clean install




Navigate to the project directory:
bash
Copy code
cd drone-management-system
Run Docker Compose:                 
      
         docker-compose up -d


This command will start the PostgreSQL database for storing drone information and the Drone Management System application.


## Data base
  Check if the DATABASE drone is created in ur postgres if not 

          sudo  docker exec -it postgres_drone psql -U postgres -P ganesh
          CREATE DATABASE drone;
          sudo docker-compose up -d




## Access the applicationswagger Doc:

Open your web browser and go to

                            http://localhost:9092/swagger-ui/index.html

to interact with the Drone Management System swagger Doc.

Usage
Once the system is running, you can perform various operations such as:

View the list of drones and their details.
Add new drones with unique serial numbers.
Update the state of each drone based on its current activity.
Monitor the battery capacity and weight limit of each drone.
The application provides a user-friendly interface for managing and monitoring the drone fleet effectively.

Conclusion
This Docker Compose-based Drone Management System demonstrates the potential of drones in transportation and logistics. It enables efficient tracking and management of the drone fleet, offering a glimpse into the future of streamlined and innovative delivery solutions.