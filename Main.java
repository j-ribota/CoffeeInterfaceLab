/**
 * Lab 6
 * allows user inputted orders and adds a file writer
 * CS160-1
 * 06-17-2022
 * @author Jillian Ribota
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileWriter;
public class Main
{
    /**
     * This is the main class which runs the Cafe application to create coffees and edit information
     * @param args
     */
    public static void main(String[] args)
    {
        /**
         * This stack will hold all the orders created overtime to be added to the log
         */
        Stack<String> stack = new Stack<>();

        Scanner cafeApplication = new Scanner(System.in);
        System.out.println("Cafe Application Running...");
        int input = 0;
        while(input != 1) {
            printOptionsMenu();
            switch (cafeApplication.nextLine()) {
                case "1":
                    int[] inventory = inventoryReader();
                    System.out.println("Current items in the inventory: ");
                    System.out.println("Black Coffee = " + inventory[0]);
                    System.out.println("Milk = " + inventory[1]);
                    System.out.println("HotWater = " + inventory[2]);
                    System.out.println("Espresso = " + inventory[3]);
                    System.out.println("Sugar = " + inventory[4]);
                    System.out.println("WhippedCream = " + inventory[5]);
                    break;
                case "2":
                    ArrayList<Coffee> orders = new ArrayList<>(createOrder());
                    for(int i = 0; i < orders.size(); i++) {
                        stack.push(orders.get(i).printCoffee());
                    }
                    ArrayList<String> items = new ArrayList<>();
                    ArrayList<Double> price = new ArrayList<>();
                    for(int i = 0; i < orders.size(); i++)
                    {
                        items.add(orders.get(i).printCoffee());
                        price.add(orders.get(i).Cost());
                    }
                    System.out.println(PrintOrder(items,price));
                    break;
                case "3":
                    int[] upInventory = inventoryReader();
                    inventoryWriter(upInventory);
                    break;
                case "4":
                    logWriter(stack);
                    break;
                case "5":
                    int[] updateFinalInventory = inventoryReader();
                    inventoryWriter(updateFinalInventory);
                    logWriter(stack);
                    input = 1;
                    break;
                default:
                    System.out.println("Invalid selection. Please Try Again");
            }
        }
    }

    /**
     * This function prints out the order for the customer with names and prices of the items
     * @param item holds the coffees the user ordered
     * @param price holds the prices of the coffees ordered
     * @return returns a string containing the customers receipt
     */
    public static String PrintOrder(ArrayList<String> item, ArrayList<Double> price)
    {
        Iterator<String> itemIt = item.iterator();
        Iterator<Double> priceIt = price.iterator();
        StringBuilder str = new StringBuilder();
        int numItem = 1;

       str.append("RECEIPT\n");
       while(itemIt.hasNext()) // prints out each order with both th coffee and the price
       {
           str.append("Item " + numItem + ": " + itemIt.next() + " | Cost: ");
           double cPrice = priceIt.next();
           String uPrice = String.format("%.2f", cPrice);
           str.append(uPrice + "\n");
           numItem++;
       }
       str.append("TOTAL COST OF ORDER: ");
       double totalCost = 0.0;
       for(int j = 0; j < price.size(); j++) //iterates over entire price array list to add up all the prices
       {
           totalCost = totalCost + price.get(j);
       }
       str.append(totalCost);
       return str.toString();
    }

    /**
     * This method is used to create the coffees for the user
     * @return it returns an arraylist holding all of the coffees the user wishes to order and their modifications if any
     */
    public static ArrayList<Coffee> createOrder()
    {
       Scanner userFeedback = new Scanner(System.in);
       ArrayList<Coffee> coffeeOrder = new ArrayList<>();
       int[] inventoryNums = inventoryReader();
       if(inventoryNums[0] == 0)
       {
         throw new ArithmeticException("Out of Coffee. Visit us later.");

       }
       else {
           Coffee basicCoffee = new BasicCoffee();
           inventoryNums[0] = inventoryNums[0] - 1;
           System.out.println("What size coffee would you like to create?");
           if(userFeedback.nextLine().equals("Small"))
           {
               basicCoffee = new SmallSize(basicCoffee);
           } else if (userFeedback.nextLine().equals("Medium")) {
               basicCoffee = new MediumSize(basicCoffee);
           } else if (userFeedback.nextLine().equals("Large")) {
               basicCoffee = new LargeSize(basicCoffee);
           }
           System.out.println("Coffee has been created. Select toppings for the first coffee:");
           int in = 0;
           while (in != 1) {
               System.out.println("Enter the following values to add toppings: 1-Milk 2-Hot Water 3-Espresso 4-Sugar 5-Whipped Cream, e-Complete Order");
               switch (userFeedback.nextLine()) {
                   case "1": {
                       if(inventoryNums[1] != 0) {
                           basicCoffee = new Milk(basicCoffee);
                           basicCoffee.addTopping(basicCoffee);
                           inventoryNums[1] = inventoryNums[1] - 1;
                       }
                       else {
                           System.out.println("Out of milk. Try a different toppings.");
                       }
                       break;
                   }
                   case "2": {
                       if(inventoryNums[2] != 0) {
                           basicCoffee = new HotWater(basicCoffee);
                           basicCoffee.addTopping(basicCoffee);
                           inventoryNums[2] = inventoryNums[2] - 1;
                       }
                       else {
                           System.out.println("Out of hot water. Try a different toppings.");
                       }
                       break;
                   }
                   case "3": {
                       if(inventoryNums[3] != 0) {
                           basicCoffee = new Espresso(basicCoffee);
                           basicCoffee.addTopping(basicCoffee);
                           inventoryNums[3] = inventoryNums[3] - 1;
                       }
                       else {
                           System.out.println("Out of espresso. Try a different toppings.");
                       }
                       break;
                   }
                   case "4": {
                       if(inventoryNums[4] != 0) {
                           basicCoffee = new Sugar(basicCoffee);
                           basicCoffee.addTopping(basicCoffee);
                           inventoryNums[4] = inventoryNums[4] - 1;
                       }
                       else {
                           System.out.println("Out of sugar. Try a different toppings.");
                       }
                       break;
                   }
                   case "5": {
                       if(inventoryNums[5] != 0) {
                           basicCoffee = new WhippedCream(basicCoffee);
                           basicCoffee.addTopping(basicCoffee);
                           inventoryNums[5] = inventoryNums[5] - 1;
                       }
                       else {
                           System.out.println("Out of whipped cream. Try a different toppings.");
                       }
                       break;
                   }
                   case "e": {
                       in = 1;
                       break;
                   }
                   default: {
                       System.out.println("Invalid Input");
                   }
               }
           }
           coffeeOrder.add(basicCoffee);
           System.out.println("Do you want to add another coffee to this order? - yes or no");
           if(userFeedback.nextLine().equals("yes"))
           {
               createOrder();
           }
           return coffeeOrder;
       }
    }

    /**
     * This method reads the inventory to keep track of the inventory numbers of each item and topping in the shop
     * @return this returns and array with the inventory numbers
     */
    public static int[] inventoryReader()
    {
        int[] inventoryNums = new int[6];
     try(BufferedReader fileReader = new BufferedReader(new FileReader("inventory.txt")))
     {
         String curLine;
         int i = 0;
         while((curLine = fileReader.readLine()) != null)
         {
             System.out.println(curLine);
             String checkStr = curLine.substring(curLine.indexOf("=") + 1);
             inventoryNums[i] = Integer.parseInt(checkStr);
             i++;

         }
     }catch(IOException e)
     {
        System.out.println("There was an error in reading the inventory.");
     }
     return inventoryNums;
    }

    /**
     * This method is used to clean up the main method and prints the options the user can choose from in the Cafe Application
     */
    public static void printOptionsMenu()
    {
        System.out.println("Press 1 : Read Inventory");
        System.out.println("Press 2 : Create Coffee Order");
        System.out.println("Press 3 : Update Inventory");
        System.out.println("Press 4 : Update log file");
        System.out.println("Press 5 : Exit the application");
    }

    /**
     * This program writes out the inventory with updated inventory  numbers
     * @param arr holds the inventory numbers from inventoryWriter
     */
    public static void inventoryWriter(int[] arr)
    {
       try
       {
        FileWriter writer = new FileWriter("inventory.txt", false);
          writer.write("Current items in the inventory: \n");
           writer.write("Black Coffee = " + arr[0] + "\n");
           writer.write("Milk = " + arr[1]+ "\n");
           writer.write("HotWater = " + arr[2]+ "\n");
           writer.write("Espresso = " + arr[3]+ "\n");
           writer.write("Sugar = " + arr[4]+ "\n");
           writer.write("WhippedCream = " + arr[5]+ "\n");

           System.out.println("Successfully updated the inventory");
       }catch(IOException e)
       {
           System.out.println("There was an error in updating the inventory.");
       }
    }

    /**
     * This method updates the logWriter which keeps track of customer orders and their date and time for the logs
     * @param orders is a stack containing all the orders that have been created overtime
     */
    public static void logWriter(Stack<String> orders)
    {
        try {
            FileWriter myWriter = new FileWriter("LogFile.txt", true);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            myWriter.write("\n\nWriting orders from stack " + formatter.format(date));
            if(orders.empty() != true)
            {
                while(orders.empty() != true)
                {
                    myWriter.write(orders.pop());
                }
                System.out.println("Successfully updated the log file");
            }
            else {
                System.out.println("Nothing to log. Stack is empty");
            }

        }catch(IOException e)
        {
            System.out.println("There was an error in updating the logFile.");
        }
    }
}
