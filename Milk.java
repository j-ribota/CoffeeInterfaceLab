public class Milk extends CoffeeDecorator {
    /**
     * Allows for override of the Coffee methods
     * @param coffee
     */
    public Milk(Coffee coffee) {
        super(coffee);
    }

    /**
     * this adds milk to the coffee object created in the main
     * @param coffee is the user created coffee
     */
    public void addTopping(Coffee coffee) {
        coffee = new Milk(coffee);
    }

    /**
     * This method creates a string out of the users order updated to the added topping
     * @return it returns the string containing the order
     */
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + " with milk";
    }


    /**
     * This method adds to the price of the users order depending on modifications
     * @return it returns the order price
     */
    @Override
    public double Cost()
    {
        return this.coffee.Cost() + 0.15;
    }
}
