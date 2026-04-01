import java.util.LinkedList;
import java.util.Queue;

/**
 * ===============================================================
 * UseCase5BookingRequestQueue
 * ===============================================================
 *
 * Demonstrates booking request handling using FIFO Queue.
 *
 * @version 5.0
 */


/* ================= RESERVATION CLASS ================= */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}


/* ================= BOOKING QUEUE ================= */
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    /**
     * Add booking request (enqueue)
     */
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added for " + reservation.getGuestName());
    }

    /**
     * Display all queued requests (FIFO order)
     */
    public void displayQueue() {

        System.out.println("\n---- Booking Request Queue ----");

        if (queue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Reservation r : queue) {
            r.displayReservation();
        }
    }

    /**
     * Peek next request (no removal)
     */
    public Reservation peekNextRequest() {
        return queue.peek();
    }
}


/* ================= MAIN CLASS ================= */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println(" Book My Stay Application ");
        System.out.println(" Hotel Booking System v5.0 ");
        System.out.println("====================================\n");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulating incoming booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Diana", "Single Room"));

        // Display queue (FIFO order preserved)
        bookingQueue.displayQueue();

        // Peek next request (without removing)
        System.out.println("\nNext request to process:");
        Reservation next = bookingQueue.peekNextRequest();

        if (next != null) {
            next.displayReservation();
        }

        System.out.println("\nNote: No inventory changes are made at this stage.");
    }
}