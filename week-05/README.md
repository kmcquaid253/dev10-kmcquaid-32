# Don't Wreck My Home Plan

Don't Wreck My House is similar to Airbnb. A guest chooses a place to stay for a specific
date range. If the host location is available, it may be reserved by the guest. Reserved
locations are not available to other guests during reserved dates
_______________________________________________________________________________________________________________________
## High-Level Requirements

- The administrator may view existing reservations for a host.
- The administrator may create a reservation for a guest with a host.
- The administrator may edit existing reservations.
- The administrator may cancel a future reservation.


## Package/ Class Overview
_______________________________________________________________________________________________________________________
```
data
├───reservations
│            │
│            └───[files Names].csv                                                                                                            -- All reservation files will go here
│
│       
└───guests.csv
└───hosts.csv
src
├───main
│   └───java
│   │   └───learn
│   │        └───dwmh
│   │            │   App.java                                                                                                                 -- app entry point
│   │            │
│   │            ├───data
│   │            │       DataException.java                                                                                                   -- data layer custom exception
│   │            │                       ├─── public DataAccessException(String message) 
│   │            │                       └─── public DataAccessException( String message, Throwable innerException )
│   │            │ 
│   │            │ 
│   │            │       ReservationFileRepository.java                                                                                       -- concrete repository
│   │            │                                   ├─── public List<Reservation> findAll()
│   │            │                                   ├─── List<Reservation> findByHost()
│   │            │                                   ├─── public Reservation add(Reservation toAdd)
│   │            │                                   ├─── public boolean update(Reservation updated)
│   │            │                                   ├─── public boolean delete()
│   │            │                                   ├─── private writeAllReservation(List<Reservationl> toWrite)                             <- Needed to add Reservation, writes the whole List
│   │            │                                   ├─── private Reservation convertLineToReservation(String line)                           <- Needed for find all AKA deserialize
│   │            │                                   └─── public Reservation getReservationByDates()                                          <- This method will help with checking that 
│   │            │                                                                                                                               there are no duplicate dates
│   │            │       ReservationRepository.java          -- repository interface
│   │            │
│   │            │       HostFileRepository.java             -- concrete repository
│   │            │                   ├─── public List<Host> findAll()
│   │            │                   ├─── public Host findByEmail(String email)
│   │            │                   └─── private Host convertLineToHost(String line)           <- Needed for findAll AKA deserialize
│   │            │                   
│   │            │       HostRepository.java                                                    -- repository interface
│   │            │
│   │            │       GuestFileRepository                                                    -- concrete repository
│   │            │                  ├─── public List<Guest> findAll()
│   │            │                  ├─── public Guest findByEmail(String email)
│   │            │                  └─── private Guest convertLineToGuest(String line)          <- Needed for findAll AKA deserialize
│   │            │
│   │            │       GuestRepository                                                        -- repository interface
│   │            │
│   │            │
│   │            ├───domain
│   │            │       Result.java                                                            -- domain result for handling success/failure
│   │            │                ├─── getPayload()
│   │            │                ├─── setPayload()
│   │            │                ├─── boolean isSuccess()
│   │            │                ├─── addErrorMessage(String message){}
│   │            │                └─── List<String> getMessages()
│   │            │                                          
│   │            │       ReservationService.java                                                -- Reservation validation/rules
│   │            │                       ├─── Result <Reservation> addReservation()
│   │            │                       ├─── Result <Reservation> update () 
│   │            │                       ├─── validate(Reservation reservation)
│   │            │                       ├─── validateNulls(Reservation reservation)            <- validation in order to add a reservation, goes along with addReservation()
│   │            │                       ├─── viewByEmail(String hostEmail)                     <- in order to view a reservation you need the host email
│   │            │                       ├─── deleteById()
│   │            │                       └──  Result<List<Reservation>> findByHost()            <- Host of email required in order to view
│   │            │
│   │            │       HostService.java
│   │            │                   └─── public List<Host> findByEmail (String email) 
│   │            │                   
│   │            │       GuestService.java
│   │            │                └─── public List<Guest> findByEmail (String email) 
│   │            │
│   │            │
│   │            ├───models
│   │            │       Reservation.java                    -- reservation model
│   │            │       Host.java                           -- Host model
│   │            │       Guest.java                          -- Guest model
│   │            │
│   │            └───ui  ConsoleIO                           -- All input methods
│   │                         ├─── print(String message) 
│   │                         ├─── println(String message) 
│   │                         ├─── readString(String prompt) 
│   │                         ├─── readRequiredString(String prompt) 
│   │                         ├─── readInt(String prompt) 
│   │                         ├─── readInt(String prompt, int min, int max) 
│   │                         ├─── readLocalDate(String prompt) 
│   │                         └─── readBoolean(String prompt) 
│   │
│   │                         
│   │                    InvalidMenuChoiceException                                                         -- Menu custom exception
│   │                           ├─── InvalidMenuChoiceException(String message)
│   │                           └─── InvalidMenuChoiceException(String message, Throwable innerException)
│   │ 
│   │                    MainMenuChoice                                                                     -- enum representing the menu choices 
│   │
│   │                    Controller.java                                                                    -- UI controller
│   │                               ├─── run() 
│   │                               └─── runMenu()
│   │
│   │                    View.java                                                                          -- all console input/output
│   │                            ├─── displayMenu() 
│   │                            ├─── displayReservationSummary() 
│   │                            ├─── displayReservations() 
│   │                            ├─── getNewReservationDetails() 
│   │                            ├─── displayHeader(String message) 
│   │                            ├─── enterToContinue() 
│   │                            ├─── getStartDate() 
│   │                            └─── getEndDate() 
│   │
│   │
│   │
│   └───resources
│           data.properties
│
│
│
│
│
│
└───test
        └───java
                └───learn
                        └───dwmh
                                ├───data
                                │     ReservationFileRepositoryTest.java            -- ReservationFileRepository tests
                                │     ReservationRepositoryDouble.java              -- helps tests the service, implements ReservationRepository
                                │     HostFileRepositoryTest.java
                                │     HostRepositoryDouble.java
                                │     GuestFileRepositoryTest.java
                                └───  GuestRepositoryDouble.java
                                │ 
                                │ 
                                └───domain
                                        ReservationServiceTest.java                 -- ReservationService tests
                                        HostServiceTest.java
                                        GuestServiceTest.java
```

## Methods Needed
### **App.java**
_______________________________________________________________________________________________________________________

- @ComponentScan & @PropertySource() goes here, before the class
- Since I am going to be doing Spring Annotations I will need my annotations here

  -  Pass the App.class as a constructor argument
  - Set 'context.getBean' (Works the same as the XML document)

- Include the run method from the controller


### data/ ReservationRepository.java
_______________________________________________________________________________________________________________________

(Interface of ReservationFileRepository) <- Same with HostFileRepository and GuestFileRepository

Will have all methods as ReservationFile Repository


### models/ Reservation.java
_______________________________________________________________________________________________________________________
#### Fields:
- int id,
- LocalDate start,
- LocalDate end,
- BigDecimal total,
- Guest guest,
- Host host,


- _Getters and setters_

### models/ Host.java
_______________________________________________________________________________________________________________________
#### Fields:

- String id,
- String lastName,
- String firstName,
- BigDecimal standardRate,
- BigDecimal weekendRate,
- String email,
- String phone,
- String address,
- String city,
- String stateCode,
- String postalCode,
- BigDecimal weekendRate,
- BigDecimal standardRate


_Getters and setters_

### models/ Guest.java
_______________________________________________________________________________________________________________________
#### Fields:

- int id,
- String lastName,
- String firstName,
- String email,
- String phone,
- String stateCode


- _Getters and setters_



_______________________________________________________________________________________________________________________

## Validations
### View Reservations for Host
_______________________________________________________________________________________________________________________

- Don't allow the app to crash if there's no host or reservation
  - **If host not found** display a message
  - **If host has no reservation** display a message
- Sort reservations in a meaningful way



### Make a reservation
_______________________________________________________________________________________________________________________

- Guest, host, and start and end dates are required.
- The guest and host must already exist in the "database". Guests and hosts cannot be created.
- The start date must come before the end date.
- The reservation may never overlap existing reservation dates.
- The start date must be in the future.


### Edit a reservation
_______________________________________________________________________________________________________________________

- Guest, host, and start and end dates are required.
- The guest and host must already exist in the "database". Guests and hosts cannot be created.
- The start date must come before the end date.
- The reservation may never overlap existing reservation dates.


### Cancel a Reservation
_______________________________________________________________________________________________________________________

- Cannot cancel a reservation that's in the past.

_______________________________________________________________________________________________________________________
## User Stories
_______________________________________________________________________________________________________________________

**Menu:**
1. As an admin I should be able to click on any of the menu options without trouble.


**View**
1. As an admin I should be able to enter a host email to view reservations for the host.
2. As an admin I should be able to view reservations of selected host.
3. As an admin I should be able to go back and re-enter host if I inputted the incorrect email.
4. As an admin I should be able to go back to main menu if no host is found

**Make a reservation**
1. As an admin I should be able to enter a guest & host email to make a reservation.
2. As an admin I should be able to see existing reservations of that host/location.
3. As an admin I should be able to enter start/end date of the reservation I want to make.
4. As an admin I should NOT be allowed to make reservations with a past start date.
5. As an admin I should NOT be allowed to make a reservation with an end date that's before the start date.
6. As an admin I should NOT be allowed to make a reservation with the same dates of an existing reservation, 1 day overlap = fine.
7. As an admin I should be allowed to input 'n'(no) if I change my mind about making the reservation.
8. As an admin I should be able to view a summary of the reservation.
9. As an admin I should be able to view if the reservation was successfully created.


**Edit a reservation**
1. As an admin I should be allowed to enter guest & host email to lookup reservation that I want to edit.
2. As an admin I should be able to view & select reservation I want to edit by ID.
3. As an admin I should be able to press enter on the fields I DO NOT want to edit.
4. As an admin I should be allowed to edit the dates to the new dates.
5. As an admin I should not be able to edit to a date of an existing reservation.
6. As an admin I should be allowed to confirm the new updates before updating.
7. As an admin I should be able to see a new summary of my new reservation.
8. As an admin I should be allowed to view if my reservation was successfully updated.

**Cancel a reservation**
1. As an admin I should be allowed to enter guest & host email to lookup reservation that I want to delete.
2. As an admin I should be able to view & select reservation I want to delete by ID.
3. As an admin I should be allowed to cancel a future reservation.
4. As an admin I should NOT be allowed to cancel past reservations.


_______________________________________________________________________________________________________________________
## Technical Requirements
_______________________________________________________________________________________________________________________

- Spring dependency injection configured with either XML or annotations.
- All financial math must use BigDecimal.
- Dates must be LocalDate, never strings.
- All file data must be represented in models in the application.
- **Reservation identifiers are unique per host, not unique across the entire application**. Effectively, the combination
  of a reservation identifier and a host identifier is required to uniquely identify a reservation.