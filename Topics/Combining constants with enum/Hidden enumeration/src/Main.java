public class Main {

    public static void main(String[] args) {
        int counter = 0;

        try {
            // Dynamically load the Secret enum
            Class<?> secretClass = Class.forName("Secret");
            Object[] enumConstants = secretClass.getEnumConstants();

            // Iterate through the constants and count those starting with "STAR"
            for (Object constant : enumConstants) {
                if (constant.toString().startsWith("STAR")) {
                    counter++;
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Enum Secret not found.");
        }

        System.out.println(counter);
    }
}

// Sample enum for inspiration (uncomment for local testing and comment it back before submission)
/*
enum Secret {
    STAR, CRASH, START, // ...
}
*/