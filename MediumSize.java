public class MediumSize extends CoffeeDecorator{

    protected Coffee coffee;


    /**
     * Allows for override of the Coffee methods
     * @param coffee
     */
    public MediumSize(Coffee coffee) {
        super(coffee);
    }


    /**
     * this method is not necessary for this specific code but is written to change nothing as it is needed to be an extension of the coffee decorator class
     * @param coffee
     */
    @Override
    public void addTopping(Coffee coffee) {
    }


    /**
     * This method creates a string out of the users order updated to the size
     * @return it returns the string containing the order
     */
    @Override
    public String printCoffee() {
        return this.coffee.printCoffee() + " size medium";
    }


    /**
     * This method adds to the price of the users order depending on modifications
     * @return it returns the order price
     */
    @Override
    public double Cost()
    {
        return this.coffee.Cost() + 1.00;
    }
}
