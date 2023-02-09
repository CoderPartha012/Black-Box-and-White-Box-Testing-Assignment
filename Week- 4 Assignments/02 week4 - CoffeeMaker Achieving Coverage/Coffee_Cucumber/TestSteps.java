/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modified 20171114 by Ian De Silva -- Updated to adhere to coding standards.
 * 
 */
package edu.ncsu.csc326.coffeemaker;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc326.coffeemaker.CoffeeMaker;
import edu.ncsu.csc326.coffeemaker.UICmd.AddInventory;
import edu.ncsu.csc326.coffeemaker.UICmd.CheckInventory;
import edu.ncsu.csc326.coffeemaker.UICmd.ChooseRecipe;
import edu.ncsu.csc326.coffeemaker.UICmd.ChooseService;
import edu.ncsu.csc326.coffeemaker.UICmd.Command;
import edu.ncsu.csc326.coffeemaker.UICmd.DescribeRecipe;
import edu.ncsu.csc326.coffeemaker.UICmd.InsertMoney;
import edu.ncsu.csc326.coffeemaker.UICmd.Reset;
import edu.ncsu.csc326.coffeemaker.UICmd.TakeMoneyFromTray;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

/**
 * Contains the step definitions for the cucumber tests.  This parses the 
 * Gherkin steps and translates them into meaningful test steps.
 */
public class TestSteps {
	
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;
	private CoffeeMakerUI coffeeMakerMain; 
	private CoffeeMaker coffeeMaker;
	private RecipeBook recipeBook;
	private Inventory invent;
	
	private void initialize() {
		recipeBook = new RecipeBook();
		coffeeMaker = new CoffeeMaker(recipeBook, new Inventory());
		coffeeMakerMain = new CoffeeMakerUI(coffeeMaker);
	}
	
    @Given("^an empty recipe book$")
    public void an_empty_recipe_book() throws Throwable {
        initialize();
        recipe5 = new Recipe();
        recipe4 = new Recipe();
    }


    @Given("a default recipe book")
	public void a_default_recipe_book() throws Throwable {
    	initialize();
    	
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
		
		//Set up for r5 (added by MWW)
		recipe5 = new Recipe();
		recipe5.setName("Super Hot Chocolate");
		recipe5.setAmtChocolate("6");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("1");
		recipe5.setAmtSugar("1");
		recipe5.setPrice("100");

		recipeBook.addRecipe(recipe1);
		recipeBook.addRecipe(recipe2);
		recipeBook.addRecipe(recipe3);
		recipeBook.addRecipe(recipe4);
		
	}
    @Given("my default recipe book")
   	public void my_default_recipe_book() throws Throwable {
       	initialize();
       	
   		//Set up for r1
   		recipe1 = new Recipe();
   		recipe1.setName("Coffee");
   		recipe1.setAmtChocolate("0");
   		recipe1.setAmtCoffee("3");
   		recipe1.setAmtMilk("1");
   		recipe1.setAmtSugar("1");
   		recipe1.setPrice("50");
   		
   		//Set up for r2
   		recipe2 = new Recipe();
   		recipe2.setName("Mocha");
   		recipe2.setAmtChocolate("20");
   		recipe2.setAmtCoffee("3");
   		recipe2.setAmtMilk("1");
   		recipe2.setAmtSugar("1");
   		recipe2.setPrice("75");
   		
   		//Set up for r3
   		recipe3 = new Recipe();
   		recipe3.setName("Latte");
   		recipe3.setAmtChocolate("0");
   		recipe3.setAmtCoffee("3");
   		recipe3.setAmtMilk("3");
   		recipe3.setAmtSugar("1");
   		recipe3.setPrice("100");
   		
   		
   		recipeBook.addRecipe(recipe1);
   		recipeBook.addRecipe(recipe2);
   		recipeBook.addRecipe(recipe3);
   		
   		}
       @Given("my basic recipe book")
      	public void my_basic_recipe_book() throws Throwable {
          	initialize();
          	
      		//Set up for r1
      		recipe1 = new Recipe();
      		recipe1.setName("Coffee");
      		recipe1.setAmtChocolate("0");
      		recipe1.setAmtCoffee("3");
      		recipe1.setAmtMilk("1");
      		recipe1.setAmtSugar("1");
      		recipe1.setPrice("50");
      		
      		//Set up for r4
      		recipe4 = new Recipe();
      		recipe4.setName("Hot Chocolate");
      		recipe4.setAmtChocolate("16");
      		recipe4.setAmtCoffee("0");
      		recipe4.setAmtMilk("1");
      		recipe4.setAmtSugar("1");
      		recipe4.setPrice("65");
      	
      		recipeBook.addRecipe(recipe1);
      		recipeBook.addRecipe(recipe4);
      		
      		invent = new Inventory();
      		
      	}
       
       @When("^user press the key ([-+]?\\d+)")
       public void user_press_the_key(int keyPressed) throws Throwable {
       	Command cmd = new ChooseService(keyPressed);
       	coffeeMakerMain.UI_Input(cmd);
       }
       @When("^user press the reset$")
       public void user_press_the_reset() throws Throwable {
       	Command cmd = new Reset();
       	coffeeMakerMain.UI_Input(cmd);
       }
       @When("^user provide a valid recipe ingredients")
   	public void user_provide_coffee_ammount() throws Throwable {
       	recipe4 = new Recipe();
       	recipe4.setName("Hot Chocolate");
       	recipe4.setAmtChocolate("4");
       	recipe4.setAmtCoffee("0");
       	recipe4.setAmtMilk("1");
       	recipe4.setAmtSugar("1");
       	recipe4.setPrice("65");
   		}
       @When("^user press enter$")
       public void user_press_enter() throws Throwable {
       	Command cmd = new DescribeRecipe(recipe4);
       	coffeeMakerMain.UI_Input(cmd);
       }
       @When("^user provide a invalid value for any recipe materials, it should be error$")
       public void user_provide_a_invalid_value_for_any_recipe_materials_it_should_be_error() throws Throwable {
   		//For Coffee
   		try {
   			recipe5.setAmtCoffee("-1");
   	        fail("Units of coffee must be a positive integer");
   		}catch(RecipeException e) {}
   		try {
   		recipe5.setAmtCoffee("b");
   		   fail("Units of coffee must be a positive integer");
   		}catch(RecipeException e) {}
   		// For Milk 
   		try {
   	       recipe5.setAmtMilk("-1");
   	       fail("Units of coffee must be a positive integer");
   		}catch(RecipeException e) {}
   		
   		try {
   		   recipe5.setAmtMilk("b");
   		   fail("Units of coffee must be a positive integer");
   		}catch(RecipeException e) {}
   		//For Sugar 
   		try {
   			recipe5.setAmtSugar("-1");
   		    fail("Units of coffee must be a positive integer");
   		}catch(RecipeException e) {}
   		try {
   			recipe5.setAmtSugar("b");
   			fail("Units of coffee must be a positive integer");
   		}catch(RecipeException e) {}
   		// For Chocolate 
   		try {
   			recipe5.setAmtChocolate("-1");
   			fail("Units of coffee must be a positive integer");
   		}catch(RecipeException e) {}
   		try {
   			recipe5.setAmtChocolate("b");
   			fail("Units of coffee must be a positive integer");
   		}catch(RecipeException e) {}
   		// For price
   		try {
   			recipe5.setPrice("-1");
   			fail("Units of coffee must be a positive integer");
   		}catch(RecipeException e) {}
   		try {
   			recipe5.setPrice("b");
   			fail("Units of coffee must be a positive integer");
   		}catch(RecipeException e) {}
       }
       
       @When("^user provide a valid recipe without name")
      	public void user_provide_coffee_ammount_without_name() throws Throwable {
          	//Set up for r1
          	//Set up for r4
          	recipe4 = new Recipe();
          	recipe4.setName(null);
          	recipe4.setAmtChocolate("4");
          	recipe4.setAmtCoffee("0");
          	recipe4.setAmtMilk("1");
          	recipe4.setAmtSugar("1");
          	recipe4.setPrice("65");
      	}
       @When("^user provide a new recipe ingredients$")
       public void user_provide_a_new_recipe_ingredients() throws Throwable {
       	recipe5 = new Recipe();
      		recipe5.setName("Coffee");
      		recipe5.setAmtChocolate("0");
      		recipe5.setAmtCoffee("3");
      		recipe5.setAmtMilk("1");
      		recipe5.setAmtSugar("1");
      		recipe5.setPrice("50");
       }
       @Then("^the system should detect the recipe exist$")
        public void the_system_should_detect_the_recipe_exist() throws Throwable {
        	assertTrue(coffeeMakerMain.coffeeMaker.getRecipes()[0].equals(recipe5));
        }
       @When("^user select a recipe ([-+]?\\d+) from the list$")
       public void user_select_a_recipe_Reipe_from_the_list(int recipeSelected) throws Throwable {
   		try {
   			Command cmd = new ChooseRecipe(recipeSelected);
   	  		coffeeMakerMain.UI_Input(cmd);
   		}catch(ArrayIndexOutOfBoundsException ignored) {}
       }
       @When("^user provide inventory ammount ([+-]?\\d+) ([+-]?\\d+) ([+-]?\\d+) ([+-]?\\d+)$")
       public void user_provide_inventory_ammount(int arg1, int arg2, int arg3, int arg4) throws Throwable {
   		Command cmd = new AddInventory(arg1, arg2, arg3, arg4);
   		coffeeMakerMain.UI_Input(cmd);	
   	}
   	@When("^user type the invalid coffee ammount to add$")
       public void user_type_the_invalid_ammount_of_material_to_add() throws Throwable {
   		try {
   			Command cmd = new AddInventory(-10, 10, 10, 10);
   			coffeeMakerMain.UI_Input(cmd);
   			coffeeMakerMain.coffeeMaker.addInventory("m", "10", "10", "10");
   			fail("Units of coffee must be a positive integer");
   		}catch(InventoryException e) {}
       }
   	@When("^user type the invalid milk ammount to add$")
       public void user_type_the_negative_milk_ammount_to_add() throws Throwable {
       	
   		try {
   			Command cmd = new AddInventory(10, -10, 10, 10);
   			coffeeMakerMain.UI_Input(cmd);
   			coffeeMakerMain.coffeeMaker.addInventory("10", "m", "10", "10");
   			fail("Units of coffee milk be a positive integer");
   		 }catch(InventoryException e) {}
       }
       @When("^user type the invalid sugar ammount to add$")
       public void user_type_the_negative_sugar_ammount_to_add() throws Throwable {
   		try {
   			Command cmd = new AddInventory(10, 10, -10, 10);
   			coffeeMakerMain.UI_Input(cmd);
   			coffeeMakerMain.coffeeMaker.addInventory("10", "10", "m", "10");
   			fail("Units of coffee milk be a positive integer");
   		 }catch(InventoryException e) {}
       }
   		@When("^user type the invalid chocolate ammount to add$")
       public void user_type_the_negative_xhocolate_ammount_to_add() throws Throwable {
   		try {
   			Command cmd = new AddInventory(10, 10, 10, -10);
   			coffeeMakerMain.UI_Input(cmd);
   			coffeeMakerMain.coffeeMaker.addInventory("10", "10", "10", "m");
   			fail("Units of coffee milk be a positive integer");
   		 }catch(InventoryException e) {}
       }
   		@When("^user provide money ([+-]?\\d+)$")
   	    public void user_provide_money(int insertedMoney) throws Throwable {
   			Command cmd = new InsertMoney(insertedMoney);
   		  	coffeeMakerMain.defaultCommands(cmd);
   	        
   	    }
   		@Then("^the inventory should be true$")
   	    public void the_inventory_should_be_true() throws Throwable {
   	    	Command cmd = new CheckInventory();
   			coffeeMakerMain.UI_Input(cmd);
   	    	String r = "Coffee: 15\nMilk: 15\nSugar: 15\nChocolate: 15\n";
   	        assertEquals(r, coffeeMakerMain.coffeeMaker.checkInventory().toString());
   	    }
   	    @Then("^the money in machin will be (\\d+)$")
   	    public void the_money_in_machin_will_be(int arg1) throws Throwable {
   			Command cmd = new TakeMoneyFromTray();
   			coffeeMakerMain.defaultCommands(cmd);
   			assertEquals(coffeeMakerMain.getMoneyInTray(), coffeeMakerMain.moneyInTray);
   	    }
   	    @Then("^user will see the recipes$")
	    public void user_will_see_the_recipes() throws Throwable {
	        String s ="1. Coffee\n2. Hot Chocolate\n";
	        coffeeMakerMain.displayRecipes();
	        assertEquals("Coffee", coffeeMakerMain.getRecipes()[0].getName());
	        assertEquals("Hot Chocolate", coffeeMakerMain.getRecipes()[1].getName());
	    }
   	 @When("^user add some coffee ([-+]?\\d+) to inventory$")
     public void user_add_some_coffee_to_inventory(String arg1) throws Throwable {
         // Write code here that turns the phrase above into concrete actions
  		
  		invent.setCoffee(Integer.parseInt(arg1));
     }

     @When("^user add some chocolate ([-+]?\\d+) to inventory$")
     public void user_add_some_chocolate_to_inventory(String arg1) throws Throwable {
         // Write code here that turns the phrase above into concrete actions
     	invent.setChocolate(Integer.parseInt(arg1));
     }

     @When("^user add some milk ([-+]?\\d+) to inventory$")
     public void user_add_some_milk_to_inventory(String arg1) throws Throwable {
         // Write code here that turns the phrase above into concrete actions
     	invent.setMilk(Integer.parseInt(arg1));
     }

     @When("^user add some sugar ([-+]?\\d+) to inventory$")
     public void user_add_some_sugar_to_inventory(String arg1) throws Throwable {
         // Write code here that turns the phrase above into concrete actions
     	invent.setSugar(Integer.parseInt(arg1));
     }
      @Then("^the system will check inventory avialibility as (\\d+)$")
      public void the_system_will_check_inventory_avialibility(String arg1) throws Throwable {
          // Write code here that turns the phrase above into concrete actions
     	 boolean s = Boolean.parseBoolean(arg1);
     	 assertEquals(s, invent.enoughIngredients(recipe4));
      }
      @Then("the status is ([A-Za-z_]*)")
 	 public void the_status_is(String theStatus) throws Throwable{
 		assertEquals(theStatus, coffeeMakerMain.status.name());
 	 }  
 	 @Then("the mode is ([A-Za-z_]*)")
 	 public void the_mode_is(String theMode) throws Throwable{
 		assertEquals(theMode, coffeeMakerMain.mode.name().toString());
 	 } 

}
