package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;
    private State state;

    private enum State {
        CHOOSING_ACTION,
        CHOOSING_COFFEE_TYPE,
        FILLING_WATER,
        FILLING_MILK,
        FILLING_COFFEE_BEANS,
        FILLING_CUPS
    }

    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.coffeeBeans = 120;
        this.cups = 9;
        this.money = 550;
        this.state = State.CHOOSING_ACTION;
    }

    public void process(String input) {
        switch (state) {
            case CHOOSING_ACTION:
                handleAction(input);
                break;
            case CHOOSING_COFFEE_TYPE:
                handleCoffeeType(input);
                break;
            case FILLING_WATER:
                water += Integer.parseInt(input);
                state = State.FILLING_MILK;
                System.out.print("Write how many ml of milk you want to add: ");
                break;
            case FILLING_MILK:
                milk += Integer.parseInt(input);
                state = State.FILLING_COFFEE_BEANS;
                System.out.print("Write how many grams of coffee beans you want to add: ");
                break;
            case FILLING_COFFEE_BEANS:
                coffeeBeans += Integer.parseInt(input);
                state = State.FILLING_CUPS;
                System.out.print("Write how many disposable cups you want to add: ");
                break;
            case FILLING_CUPS:
                cups += Integer.parseInt(input);
                state = State.CHOOSING_ACTION;
                break;
        }
    }

    private void handleAction(String action) {
        switch (action) {
            case "buy":
                state = State.CHOOSING_COFFEE_TYPE;
                System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                break;
            case "fill":
                state = State.FILLING_WATER;
                System.out.print("Write how many ml of water you want to add: ");
                break;
            case "take":
                System.out.println("I gave you $" + money);
                money = 0;
                break;
            case "remaining":
                displayState();
                break;
            case "exit":
                System.exit(0);
                break;
        }
    }

    private void handleCoffeeType(String type) {
        if (type.equals("back")) {
            state = State.CHOOSING_ACTION;
            return;
        }

        int waterNeeded = 0, milkNeeded = 0, beansNeeded = 0, cost = 0;

        switch (type) {
            case "1": // espresso
                waterNeeded = 250;
                beansNeeded = 16;
                cost = 4;
                break;
            case "2": // latte
                waterNeeded = 350;
                milkNeeded = 75;
                beansNeeded = 20;
                cost = 7;
                break;
            case "3": // cappuccino
                waterNeeded = 200;
                milkNeeded = 100;
                beansNeeded = 12;
                cost = 6;
                break;
            default:
                System.out.println("Invalid choice");
                state = State.CHOOSING_ACTION;
                return;
        }

        if (water >= waterNeeded && milk >= milkNeeded && coffeeBeans >= beansNeeded && cups >= 1) {
            water -= waterNeeded;
            milk -= milkNeeded;
            coffeeBeans -= beansNeeded;
            cups--;
            money += cost;
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            if (water < waterNeeded) {
                System.out.println("Sorry, not enough water!");
            }
            if (milk < milkNeeded) {
                System.out.println("Sorry, not enough milk!");
            }
            if (coffeeBeans < beansNeeded) {
                System.out.println("Sorry, not enough coffee beans!");
            }
            if (cups < 1) {
                System.out.println("Sorry, not enough cups!");
            }
        }

        state = State.CHOOSING_ACTION;
    }

    private void displayState() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (coffeeMachine.state == State.CHOOSING_ACTION) {
                System.out.print("Write action (buy, fill, take, remaining, exit): ");
            }
            String input = scanner.nextLine();
            coffeeMachine.process(input);
        }
    }
}