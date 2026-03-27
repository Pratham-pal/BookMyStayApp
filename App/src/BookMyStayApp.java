
    import java.util.HashMap;
import java.util.Map;

    /**
     * ===============================================================
     * UseCase3InventorySetup
     * ===============================================================
     *
     * Demonstrates centralized inventory management using HashMap.
     *
     * @version 3.1
     */


    /* ================= ABSTRACT CLASS ================= */
    abstract class Room {

        protected int numberOfBeds;
        protected int squareFeet;
        protected double pricePerNight;

        public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
            this.numberOfBeds = numberOfBeds;
            this.squareFeet = squareFeet;
            this.pricePerNight = pricePerNight;
        }

        public void displayRoomDetails() {
            System.out.println("Beds: " + numberOfBeds);
            System.out.println("Size: " + squareFeet + " sqft");
            System.out.println("Price per night: ₹" + pricePerNight);
        }
    }


    /* ================= ROOM TYPES ================= */
    class SingleRoom extends Room {
        public SingleRoom() {
            super(1, 250, 1500.0);
        }
    }

    class DoubleRoom extends Room {
        public DoubleRoom() {
            super(2, 400, 2500.0);
        }
    }

    class SuiteRoom extends Room {
        public SuiteRoom() {
            super(3, 750, 5000.0);
        }
    }


    /* ================= INVENTORY CLASS ================= */
    class RoomInventory {

        // Centralized storage
        private Map<String, Integer> inventory;

        /**
         * Constructor initializes inventory with default values
         */
        public RoomInventory() {
            inventory = new HashMap<>();

            // Initialize availability
            inventory.put("Single Room", 5);
            inventory.put("Double Room", 3);
            inventory.put("Suite Room", 2);
        }

        /**
         * Get availability of a room type
         */
        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        /**
         * Update availability safely
         */
        public void updateAvailability(String roomType, int newCount) {
            inventory.put(roomType, newCount);
        }

        /**
         * Display full inventory
         */
        public void displayInventory() {
            System.out.println("---- Current Room Inventory ----");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
            System.out.println();
        }
    }


    /* ================= MAIN CLASS ================= */
    public class BookMyStayApp {

        public static void main(String[] args) {

            System.out.println("====================================");
            System.out.println(" Book My Stay Application ");
            System.out.println(" Hotel Booking System v3.1 ");
            System.out.println("====================================\n");

            // Create room objects (domain)
            Room single = new SingleRoom();
            Room doub = new DoubleRoom();
            Room suite = new SuiteRoom();

            // Initialize centralized inventory
            RoomInventory inventory = new RoomInventory();

            // Display room details + availability
            System.out.println("Single Room:");
            single.displayRoomDetails();
            System.out.println("Available: " + inventory.getAvailability("Single Room") + "\n");

            System.out.println("Double Room:");
            doub.displayRoomDetails();
            System.out.println("Available: " + inventory.getAvailability("Double Room") + "\n");

            System.out.println("Suite Room:");
            suite.displayRoomDetails();
            System.out.println("Available: " + inventory.getAvailability("Suite Room") + "\n");

            // Show centralized inventory
            inventory.displayInventory();

            // Example update
            System.out.println("Updating Single Room availability...\n");
            inventory.updateAvailability("Single Room", 4);

            // Display updated inventory
            inventory.displayInventory();

            System.out.println("Application terminated successfully.");
        }
    }




