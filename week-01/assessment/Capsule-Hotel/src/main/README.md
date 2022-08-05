# Capsule Hotel Plan

## High Level Requirements

1. On start up, the application prompts the administrator for the hotel's capacity. The capacity determines how many capsules are available.
2. The administrator may book a guest in an unoccupied numbered capsule.
3. The administrator may check out a guest from an occupied capsule.
4. The administrator may view guests and their capsule numbers in groups of 11. 



### Program Start
* Display welcome message (call displayWelcome()).
* On start prompt the user for a required positive number of rooms (loop until the user gets it right).
* Initialize a String[] of rooms (really this is just guest names)
* Display a main menu (call mainMenu() ) in a loop until the user chooses to exit.
 





### Check In
* Create a method for 'checkIn()'
* Inside method:
  * Ask guest to input name
  * Ask guest to input capsule number
* Create a loop to check if capsule is taken then have user input a different capsule number
* if capsule is not taken then assign that capsule to user
* I will need to assign capsule to user by assigning the name to the index of the String[] of rooms 
* Finally, output a success message if capsule was checked into.



### Check Out
* Create a method for 'checkout()'
* Ask user to enter capsule (int) they want to check out
* Create a loop that loops through this request in case they enter a capsule that is unoccupied.
* Some loops I am thinking about using for this action :
  * do
  * if
* Finally, if they enter an occupied capsule, output success message. 


### View Guests
* Create a 'viewGuest()' method
* Have user input capsule number they want to view
* Create a loop that accesses the String[] index to display the data of the room they selected
* Set conditions to the loop that will show the 11 capsule closest to that capsule (5 smaller and 5 larger) 
* Loop I am thinking of using for this:
  * for




## Tasks
* implement displayWelcome() (20 minutes)
* implement getIntFromUser() (25 minutes)
* initialize String[] for rooms (15 minutes)
* implement mainMenu() (1.5 hours)
* implement checkIn() (3 hours)
* implement checkOut() (3 hours)
* implement viewGuest(2 hours)
