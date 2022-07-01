public class Espresso extends CoffeeDecorator {

    protected Coffee coffee;


    /**
     * Allows for override of the Coffee methods
     * @param coffee
     */
    public Espresso(Coffee coffee) {
        super(coffee);
    }

    /**
     * This adds espresso to the user created coffee
     * @param coffee is the user created coffee
     */
    public void addTopping(Coffee coffee) {
        coffee = new Espresso(coffee);
    }


    /**
     * This method creates a string out of the users order updated to the added topping
     * @return it returns the string containing the order
     */
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + " with espresso";
    }


    /**
     * This method adds to the price of the users order depending on modifications
     * @return it returns the order price
     */
    @Override
    public double Cost()
    {
        return this.coffee.Cost() + 0.35;
    }
}
