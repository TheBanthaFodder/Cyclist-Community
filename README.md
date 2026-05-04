# Cyclist Community – Deliverable 4

## Contributors:
* MEHURZEL
* WAMORGA4
* ZHDANIE1
* MCLONGO
* [add names]

Video Link:
[ADD FINAL VIDEO LINK HERE]

---

## Overview

This project implements a bike racing management system that allows organizers, racers, and administrators to manage race events, registrations, and category progression within a single application.

The system supports:

* Race creation and management
* Racer registration and license validation
* Race results tracking
* Automatic category upgrades based on performance

This deliverable represents the **final implementation** of the system, building on previous designs and incorporating feedback from earlier deliverables.

---

## How to Run

Compile the project:

```bash
javac *.java
```

Run the program:

```bash
java Main
```
Or 

## Compilation:
```bash
gradle build
```

## Running the Program:
```bash
gradle runApp
```
## Notes:

---

## System Features

### Racer Features

* Sign up and create an account
* Register for races
* Purchase a license
* View race details
* Receive category upgrades
* Submit race reviews

### Organizer Features

* Create races
* Manage race details
* Set registration limits
* Enter race results

### Administrator Features

* Manage user accounts
* Manage licenses
* Access and modify system data

---

## Architecture

The system follows an MVC (Model-View-Controller) architecture.

### Model

Contains all business logic and core system classes:

* Race
* Racer
* Category
* RaceRegistration
* RaceResult
* RaceLicense

### View

Handles user interaction:

* CLI-based interface for input/output
* Displays system responses and error messages

### Controller

Handles system logic and coordination:

* Processes user input from the view
* Interacts with model classes
* Returns results to the view

This structure ensures separation of concerns and supports maintainability.

---

## Design Patterns Implemented

### Strategy Pattern

The Strategy pattern is used to dynamically handle different user types.

* `UserStrategy` defines the common interface
* `Racer`, `Organizer`, and `Administrator` implement the interface
* `UserContext` selects the correct behavior at runtime

This allows flexible handling of user-specific logic without modifying existing code.

---

### Observer Pattern

The Observer pattern is used for category upgrades and notifications.

* `Subject` manages observers
* `Category` acts as the subject
* `Racer` implements the observer interface

When race results are recorded and upgrade conditions are met, racers are automatically updated and notified.

---

## Implementation Notes

* The system is implemented as a single program (no networking)
* A CLI is used for interaction instead of a full GUI
* Core workflows from the activity diagram have been implemented
* Design patterns are integrated into the working code
* The system demonstrates functional behavior for key use cases

---

## Constraints

* Single-program execution (no client/server architecture)
* Role-based access control is enforced
* Sensitive data handling is simplified
* Category upgrades are automated
* GUI is represented as a design prototype only

---

## Testing

Testing was performed manually through the CLI.

### Tested Scenarios:

* User account creation
* Race registration with and without valid license
* Race capacity limits
* Race result entry
* Category upgrade after podium threshold
* Error handling for invalid inputs

---

## Summary

This project successfully demonstrates:

* A complete MVC-based system
* Integration of Strategy and Observer design patterns
* A working implementation of core system functionality
* Alignment with system requirements and design principles
