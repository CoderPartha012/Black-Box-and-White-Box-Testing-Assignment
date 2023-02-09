Feature: CoffeeMakerFeature

In this feature, we are going to test the user stories and use cases for the CoffeeMaker
Example.  We have provided a CoffeeMakerMain.java file that you can use to examine the 
modal behavior in the coffee maker and issue UI commands to use it, so that we can 
adequately test the user stories.

Hints: to catch the mistakes, you might need to add out of range cases for 
recipes and amounts of ingredients.  Also, be sure to check machine state
after running the user story:
  - Are the amounts of ingredients correct?
  - Is the system in the right mode?
  - Is the status what you expect?
  - Is the change produced correct?
  - etc.

Scenario Outline: Waiting State
      Priority: 1 Story Points: 2
      If the Coffee Maker is not in use it waits for user input. There are six different 
      options of user input: 1) add recipe, 2) delete a recipe, 3) edit a recipe, 
      4) add inventory, 5) check inventory, and 6) purchase beverage.
      
      For this scenario, what we will do is try each of the six user inputs and make sure 
      that the coffee maker ends up in the appropriate mode.  This would be a good place
      for a scenario outline with a table that described user inputs and expected final states.
      
      You might also want to try a couple of exceptional values to see what the 
      Coffee Maker does.
      
   Given a default recipe book
   # add more here! 
   When user press the key <Key>
   Then the status is <Status>
   And the mode is <Mode>
  
   Examples:
   | Key  | Mode               | Status | 
   |  1   |  ADD_RECIPE        |  OK    |
   |  2   |  DELETE_RECIPE     |  OK    |
   |  3   |  EDIT_RECIPE       |  OK    |
   |  4   |  ADD_INVENTORY     |  OK    |
   |  5   |  CHECK_INVENTORY   |  OK    |
   |  6   |  PURCHASE_BEVERAGE |  OK    |
   |  8   |       WAITING      |  OK    |  
   
Scenario: Add a Recipe
      Priority: 1 Story Points: 2

      Only three recipes may be added to the CoffeeMaker. A recipe consists of a name, 
      price, units of coffee, units of milk, units of sugar, and units of chocolate. 
      Each recipe name must be unique in the recipe list. Price must be handled as an 
      integer. A status message is printed to specify if the recipe was successfully 
      added or not. Upon completion, the CoffeeMaker is returned to the waiting state.   
      
      For this scenario, you should try to add a recipe to the recipe book, and check to
      see whether the coffee maker returns to the Waiting state.  
      
   Given an empty recipe book
   # add more here!  
   When user press the key 1
   	And user provide a valid recipe ingredients
   	And user press enter 
   	Then the status is OK
   	And the mode is WAITING
    
   
Scenario Outline: Delete a Recipe
      Priority: 2 Story Points: 1

      A recipe may be deleted from the CoffeeMaker if it exists in the list of recipes in the
      CoffeeMaker. The recipes are listed by their name. Upon completion, a status message is
      printed and the Coffee Maker is returned to the waiting state.  

  Given my basic recipe book
   # add more here!  
   When user press the key 2
    And user select a recipe <Recipe> from the list
    Then the status is <Status>
    And the mode is <Mode>
   Examples:
   |Recipe|Status|Mode|
   |0|OK|WAITING|
   |1|OK|WAITING|
   |2|OUT_OF_RANGE|WAITING|
   |-2|OUT_OF_RANGE|WAITING|
    
   

Scenario: Edit a Recipe
      Priority: 2 Story Points: 1

      A recipe may be edited in the CoffeeMaker if it exists in the list of recipes in the
      CoffeeMaker. The recipes are listed by their name. After selecting a recipe to edit, the user
      will then enter the new recipe information. A recipe name may not be changed. Upon
      completion, a status message is printed and the Coffee Maker is returned to the waiting
      state.  

   Given a default recipe book
   # add more here!  
   When user press the key 3
   And user select a recipe 1 from the list
   And user provide a valid recipe ingredients
   And user press enter
   Then the status is OK
   And the mode is WAITING
    
   
Scenario: Add Inventory
      Priority: 1 Story Points: 2

      Inventory may be added to the machine at any time from the main menu, and is added to
      the current inventory in the CoffeeMaker. The types of inventory in the CoffeeMaker are
      coffee, milk, sugar, and chocolate. The inventory is measured in integer units. Inventory
      may only be removed from the CoffeeMaker by purchasing a beverage. Upon completion, a
      status message is printed and the CoffeeMaker is returned to the waiting state.   
      
   Given a default recipe book
   # add more here!   
   
Scenario Outline: Check Inventory 
      Priority: 2 Story Points: 1

      Inventory may be checked at any time from the main menu. The units of each item in the
      inventory are displayed. Upon completion, the Coffee Maker is returned to the waiting state.  
      
   Given a default recipe book
   # add more here!   
   When user press the key 4
   And user provide inventory ammount <coffeeUnit> <milkUnit> <sugarUnit> <chocolateUnit>
   #Then The material will be <added>
   Then the status is <status>
   And the mode is WAITING

   Examples: 
 
|coffeeUnit|milkUnit| sugarUnit|chocolateUnit|added |status|
|      5   |     2  |    1     |        3    |  1   |    OK      |
|      5   |    -2  |    1     |        3    |  0   |OUT_OF_RANGE|
|      5   |     2  |   -1     |        3    |  0   |OUT_OF_RANGE|
|      5   |     2  |    1     |       -3    |  0   |OUT_OF_RANGE|
   
   
Scenario Outline: Purchase Beverage
      Priority: 1 Story Points: 2

      The user selects a beverage and inserts an amount of money. The money must be an
      integer. If the beverage is in the RecipeBook and the user paid enough money the
      beverage will be dispensed and any change will be returned. The user will not be able to
      purchase a beverage if they do not deposit enough money into the CoffeeMaker. A user's
      money will be returned if there is not enough inventory to make the beverage. Upon
      completion, the Coffee Maker displays a message about the purchase status and is
      returned to the main menu.  
      
   Given my basic recipe book
   # add more here!   
	When user press the key 6
   And user provide money <Money>
   And user select a recipe <Recipe> from the list
   Then the status is <Status>
   And the mode is <Mode>
   And the money in machin will be 0
   Examples: 
      | Money | Recipe | Status             | Mode    |
      |   100 |      0 | OK                 | WAITING |
      |    49 |      0 | INSUFFICIENT_FUNDS | WAITING |
      |    50 |      0 | OK                 | WAITING |
      |  100  |      1 | INSUFFICIENT_FUNDS | WAITING |
      |  150  |     -1 |   OUT_OF_RANGE     | WAITING |
      |  150  |     2  |   OUT_OF_RANGE     | WAITING |
      |  -50  |     0  |   INSUFFICIENT_FUNDS    | WAITING |
	

# Add scenarios from the Use Cases here.  These can be Cucumber versions of the unit 
Scenario: Check Reset Mode
	Given my default recipe book
   	When user press the reset
   	Then the status is OK
  	And the mode is WAITING 
  	
Scenario: Add a Recipe with invalid values
	Given an empty recipe book
	When user press the key 1
   	And user provide a invalid value for any recipe materials, it should be error

Scenario: Add a Recipe with no name
	Given an empty recipe book
	When user press the key 1
   	And user provide a valid recipe without name
   	And user press enter 
   	Then the status is OK
   	And the mode is WAITING
   	
Scenario: Add a More than three Recipes 
 	Given my default recipe book
 	When user press the key 1
 	And user provide a valid recipe ingredients
 	And user press enter
   	Then the status is RECIPE_NOT_ADDED
   	And the mode is WAITING
   	
Scenario: Add a recipe with same name
 	Given my basic recipe book
 	When user press the key 1
 	And user provide a valid recipe ingredients
 	And user press enter
   	Then the status is RECIPE_NOT_ADDED
   	And the mode is WAITING
   	
Scenario: check equality of two recipe
 	Given my basic recipe book
 	When user press the key 1
 	And user provide a new recipe ingredients
 	And user press enter
   	Then the system should detect the recipe exist
   	And the status is RECIPE_NOT_ADDED
   	And the mode is WAITING	
   	
   	
Scenario: Add Invalid Coffee Inventory
   Given a default recipe book
   When user press the key 4
   When user type the invalid coffee ammount to add
   Then the status is OUT_OF_RANGE
   And the mode is WAITING
   
Scenario: Add Invalid Chocolate Inventory
   Given a default recipe book
   When user press the key 4
   When user type the invalid chocolate ammount to add
   Then the status is OUT_OF_RANGE
   And the mode is WAITING
   
Scenario: Add Invalid Milk Inventory
   Given a default recipe book
   When user press the key 4
   When user type the invalid milk ammount to add
   Then the status is OUT_OF_RANGE
   And the mode is WAITING

 Scenario: Add Invalid Sugar Inventory
   Given a default recipe book
   When user press the key 4
   When user type the invalid sugar ammount to add
   Then the status is OUT_OF_RANGE
   And the mode is WAITING 
   
Scenario: Check Inventory 
   Given a default recipe book
   When user press the key 5
   Then the inventory should be true
   And the status is OK
   And the mode is WAITING
   
   
Scenario: check how recipe listed
   Given my basic recipe book
   When user press the key 3
   Then the status is OK
   And the mode is EDIT_RECIPE
   And user will see the recipes
   
Scenario Outline: check how recipe check availabilty of materials
   Given my basic recipe book
   When user add some coffee <coffeeUnit> to inventory
   And user add some milk <milkUnit> to inventory
   And user add some sugar <sugarUnit> to inventory
   And user add some chocolate <chocolateUnit> to inventory
   Then the system will check inventory avialibility as <result>
  
Examples:
   
|coffeeUnit|milkUnit| sugarUnit|chocolateUnit|result|
|      5   |     2  |    1     |        3    | 1|
|     -5   |     2  |    1     |        3    | 0|
|      5   |    -2  |    1     |        3    | 0|
|      5   |     2  |   -1     |        3    | 0|
|      5   |     2  |   -1     |       -3    | 0 |

# tests that were required for course 1, or can be more direct expressions of the use
# case tests found in the Requirements-coffeemaker.pdf file. 