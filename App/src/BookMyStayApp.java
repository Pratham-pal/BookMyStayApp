import java.util.HashMap;
import java.util.Map;

/**
 * ===============================================================
 * UseCase4RoomSearch
 * ===============================================================
 *
 * Demonstrates read-only room search using centralized inventory.
 *
 * @version 4.0
 */


/* ================= ABSTRACT CLASS ================= */
abstract class Room {

    protected String roomType;
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(String roomType, int numberOfBeds, int squareFeet, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: ₹" + pricePerNight);
    }
}


/* ================= ROOM TYPES ================= */
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 250, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 400, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 750, 5000.0);
    }
}


/* ================= INVENTORY ================= */
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0); // simulate unavailable room
    }

    // Read-only access
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Expose full map (read-only usage expected)
    public Map<String, Integer> getAllInventory() {
        return inventory;
    }
}


/* ================= SEARCH SERVICE ================= */
class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Displays only available rooms (read-only operation)
     */
    public void searchAvailableRooms(Room[] rooms) {

        System.out.println("---- Available Rooms ----\n");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            // Defensive check (filter unavailable rooms)
            if (available > 0) {
                room.displayRoomDetails();
                System.out.println("Available: " + available + "\n");
            }
        }
    }
}


/* ================= MAIN CLASS ================= */
public class BookMyStayApp{

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println(" Book My Stay Application ");
        System.out.println(" Hotel Booking System v4.0 ");
        System.out.println("====================================\n");

        // Initialize domain objects
        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize search service
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Perform search (read-only)
        searchService.searchAvailableRooms(rooms);

        System.out.println("Search completed. No data was modified.");
    }
}



