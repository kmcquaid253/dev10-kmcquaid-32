# Sustainable Foraging
___________________________________________________________________________________________________________________________________________________________________
## What does this application do?
___________________________________________________________________________________________________________________________________________________________________
Tracks foragers, forageable items, and successful forages.


___________________________________________________________________________________________________________________________________________________________________
### Key Definitions
___________________________________________________________________________________________________________________________________________________________________

**Forager:**

- Person who searches for harvest and wild plants and fungi.

 - _Fields:_ firstName, lastName, state, id

**Item:**

- A wild plant or fungi.

- _Fields:_ name, category, dollarPerKillogram

**Category**

Has **four** general item groups:

1. edible (good to eat)
2. medicinal (treats illness)
3. inedible (unpleasant to eat, may make you sick, but not poisonous)
4. or poisonous (causes illness or death).

**Forage**

- A successful foraging venture linking a forager to the item they found.
- _Fields:_ id, date, forager, item, kilogram
- **NOTE:** "Successful" here means that an item was found. That item could be worthless because it's inedible or poisonous.

___________________________________________________________________________________________________________________________________________________________________

## High Level Requirements 
___________________________________________________________________________________________________________________________________________________________________

### Working Features
___________________________________________________________________________________________________________________________________________________________________
**[FIX]** = Means needs fixing

### 5. Add an Item 
*Notes while running it:* 

1. Program successfully **DID NOT** let me continue if you did not input a value, stated: " [INVALID] Value is required"
2. **DID NOT** allow me to enter duplicate names
3. All item categories **DID NOT ALLOW** Dollars/kg be less than $0 and/or be greater than $7500
4. Item ID seemed to be a system- generated unique sequential integer

_SUMMARY:_  No issues found

### 2. View Items

*Notes while running it:*
1. All Items and their corresponding fields _(id, name, category, dollars/kg)_ displayed correctly.

_SUMMARY:_  No issues found

### 1. View Foragers By Date
*Notes while running it:*
1. **Did not** allow null date, re prompted to enter date
2. Did not crash when inputted a date in the suture BUT did let me know that - no forages found -

_SUMMARY:_  No issues found

### 3. Add a Forage**[FIX]**
1. Allowed me to enter duplicate the combination of date, Item, and Forager
2. Everything else worked fine

_SUMMARY:_  **[FIX]** ADD restrictions to Forage Service that doesn't allow duplicates

- NOTE: The combination of date, Item, and Forager cannot be duplicated. (Can't forage the same item on the same day. It should be tracked as one Forage.)

___________________________________________________________________________________________________________________________________________________________________

### Incomplete Features
___________________________________________________________________________________________________________________________________________________________________

### 4. Add a Forager **[FIX]**
*Notes while running it:*
1. Has **NOT** been implemented yet
2. Did not crash when chose to add a forager

_SUMMARY:_  **[FIX]**

1. Finish the **ForagerService**
2. Create: **private void addForage() throws DataException {** in the Controller


**NOTE**

- First name is required.
- Last name is required.
- State is required.
- The combination of first name, last name, and state cannot be duplicated.
- Forager ID is a system-generated GUID (globally unique identifier).

___________________________________________________________________________________________________________________________________________________________________

## Technical Requirements 
___________________________________________________________________________________________________________________________________________________________________

### 6. Report: Kilograms of Item **[FIX]**

- Currently shows as not implemented
- Create a report that displays the kilograms of each Item collected on one day.
- Generate the kilogram report with streams and total value/category report with loops and intermediate variables
- keep the ultimate goal in mind: using data to create accurate reports.
- Create tests.


### 7. Report: Item Category Value
- Currently shows as not implemented
- Create a report that displays the kilograms of each Item collected on one day.
- Create tests.

___________________________________________________________________________________________________________________________________________________________________

### My Approach
___________________________________________________________________________________________________________________________________________________________________

1. Fix Add a forage, add new tests to make sure changes work
2. Implement Add a forager. I noticed that majority of the data is already there but will have to add to the service and create new tests
2. Implement 6. Report: Kilograms of Item <- I will try and implement with streams 
3. Implement 7. Report: Item Category Value <- I will try and implement with loops
4. Once I have all of the steps above completed I will configure Spring DI with annotations.