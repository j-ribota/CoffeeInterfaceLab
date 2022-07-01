/**
 * This class allows for the subclasses of modifications to the coffees to be used and applied
 * it creates the base structure for what each addition should do to the coffee
 */
public abstract class CoffeeDecorator implements Coffee {

    protected Coffee coffee;

    /**
     * Creates the base method for the subclasses to follow
     * @return
     */
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }


    /**
     * Creates the base method for the subclasses to follow
     * @return
     */
    public  abstract void addTopping(Coffee coffee);

    /**
     * Creates the base method for the subclasses to follow
     * @return
     */
    public String printCoffee() {
        return this.coffee.printCoffee();
    }

    /**
     * Creates the base method for the subclasses to follow
     * @return
     */
    public abstract double Cost();
}
