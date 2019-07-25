# NYJI_OO_CarPark_Challenge

### Overview

This repository contains my solution to the LinkedIn Learning course [Nail Your Java Interview - OO Challenge](https://www.linkedin.com/learning/nail-your-java-interview/716810). The following sections of this README file outlines the challenge and my solution.

### Challenge
 
  - Design a Parking Lot using Object Oriented principles.
 
### Requirements

 - Able to tell when a spot is available or unavailable.
 - Spaces can be regular or handicap.
 - Any car can park in a regular space.
 - Cars with a handicap identifier can park in handicap spaces.
 - If handicap spaces are available, handicap cars should park in handicap spaces first.
 - Each parking spot has a size: small, medium and large.
 - There are three types of vehicles: small(bikes), medium(cars) and large(trucks).
 - A small vehicle can park in a small, medium or large spot.
 - A medium vehicle can park in a medium or large spot.
 - A large vehicle can park in a large spot.
  
### Assumptions
 
 - For this exercise:
    - No need to cater for vehicles leaving their spots.
    - No need to implement each method fully, but you should get a sense of how this 
      application would work in code, and how you could design it most effectively.
     
### Output
  
 - Summary of parking lot status.
 - List of where each car parked.
 - List of parking spots left. 

### Design

 - Class Diagram available at **vpproject/diagrams/ParkingLotApp-ClassDiagram.jpg** in the code repository and displayed below.
 - Maven Folder Structure implemented.
 - JUnit testing implemented.

### Class Diagram

![Class Diagram](/vpproject/diagrams/ParkingLotApp-ClassDiagram.jpg "Class Diagram")
