package DesignInterview;

import java.sql.Time;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
class Train{
    int trainId;
    String trainName;
    String source;
    String destination;
    int totalSeats;
    int seatAvailable;

    public Train(int trainId, String trainName, String source, String destination, int totalSeats, int seatAvailable) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.seatAvailable = seatAvailable;
    }

    boolean checkAvailability(int requiredSeats){
        return seatAvailable >= requiredSeats;
    }

    void bookSeats(int requiredSeats){
        if(checkAvailability(requiredSeats)){
            totalSeats -= requiredSeats;
            System.out.println("Seats are booked Successfully");
        }else{
            System.out.println("Sorry No Seats are available");
        }
    }

    void cancelSeats(int noOfSeats){
        totalSeats += noOfSeats;
        System.out.println("Seats are cancelled Successfully");
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getSeatAvailable() {
        return seatAvailable;
    }

    public void setSeatAvailable(int seatAvailable) {
        this.seatAvailable = seatAvailable;
    }
}

class Booking{
    int bookingId;
    Train train;
    int bookedSeats;
    String bookingTime;
    Passenger passenger;

    Booking(Train train, int bookingId, int bookedSeats, String bookingTime, Passenger passenger){
        this.train = train;
        this.bookingId = bookingId;
        this.bookedSeats = bookedSeats;
        this.bookingTime = bookingTime;
        this.passenger = passenger;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(int bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

}

class Passenger{

    private String name;
    private int age;
    private int contact;

    Passenger(String name, int age, int contact){
        this.name = name;
        this.age = age;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }


}
class Railway_Booking_System {

    private static List<Train> trains = new ArrayList<>();
    private static List<Booking> bookingList = new ArrayList<>();
    private static int bookingId = 1;

    public static void main(String[] args) {
        trains.add(new Train(01, "Vaigai", "Chennai", "Trivandrum", 100, 80));
        trains.add(new Train(02, "Express", "Kerala", "Chennai", 50, 20));

        Scanner scanner = new Scanner(System.in);
        int choice;

        do{
            System.out.println();
            System.out.println("Railways Booking System");
            System.out.println("1. Book Ticket");
            System.out.println("2. Checking Availability");
            System.out.println("3. Cancellation");
            System.out.println("4. Prepare Chart");
            System.out.println("0. Exit");
            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    bookTicket(scanner);
                    break;
                case 2:
                    checkAvailabililty(scanner);
                    break;
                case 3:
                    cancellation(scanner);
                    break;
                case 4:
                    prepareChart(scanner);
                    break;
                case 0:
                    System.out.println("Exiting Railway Reservation System");
                    break;
                default:
                    System.out.println("Invalid Choice, Please Enter valid Choice");
                    break;
            }
        }while(choice != 0);

    }

    private static void prepareChart(Scanner scanner) {
        System.out.print("Enter Booking Id : ");
        int bookingId = scanner.nextInt();
        for (Booking booking :  bookingList){
            if (booking.getBookingId() == bookingId){
                Train train = booking.getTrain();
                System.out.println("Booking Id : " + booking.getBookingId() + " , ");
                System.out.println("Train Id : " + train.getTrainId() + " , ");
                System.out.println("Train Name : " + train.getTrainName() + " , ");
                System.out.println("Source : " + train.getSource() + " , ");
                System.out.println("Destination : " + train.getDestination() + " , ");
            }
        }
        System.out.println();
    }

    private static void bookTicket(Scanner scanner) {

        int trainId = checkAvailabililty(scanner);
        Train train = getTrainById(trainId);
        System.out.print("Enter Number of Seats to book : ");
        int requiredSeats = scanner.nextInt();
        List<Passenger> passengers = new ArrayList<>();
        for(int idx=0; idx<requiredSeats; idx++){
            Passenger passenger;
            String name;
            int age, contact;
            System.out.print("Enter Passenger Name : ");
            name = scanner.next();
            System.out.print("Enter Passenger Age : ");
            age = scanner.nextInt();
            System.out.print("Enter Passenger Contact : ");
            contact = scanner.nextInt();
            passenger = new Passenger(name, age, contact);
            passengers.add(passenger);
            Booking newBooking = new Booking(train, bookingId++, 1, "05:28", passenger);
            bookingList.add(newBooking);
        }
        train.bookSeats(requiredSeats);
        System.out.println();
        displayBookings();
    }

    private static int displayAvailableTrains(Scanner scanner){
        int size = trains.size();
        System.out.println();
        System.out.println("Available Trains are listed below");
        for(int idx=0; idx<size; idx++){
            Train temp = trains.get(idx);
                System.out.println();
                System.out.print(" Train Id : " + temp.getTrainId() + " , ");
                System.out.print(" Train Name : " + temp.getTrainName() + " , ");
                System.out.print(" Source : " + temp.getSource() + " , ");
                System.out.print(" Destination : " + temp.getDestination() + " , ");
                System.out.print(" Total Seats : " + temp.getTotalSeats() + " , ");
                System.out.println(" Available Seats : " + temp.getSeatAvailable());

        }

        scanner = new Scanner(System.in);
        System.out.print("Choose the train : ");
        int trainId;
        trainId = scanner.nextInt();
        System.out.println();
        return trainId;
    }

    private static int checkAvailabililty(Scanner scanner){
        System.out.println("Available Trains:");
        int trainId = displayAvailableTrains(scanner);
        Train selectedTrain = getTrainById(trainId);

        // Check seat availability
        if (selectedTrain != null) {
            System.out.print("Enter the number of seats to check availability: ");
            int seatsToCheck = scanner.nextInt();
            boolean isAvailable = selectedTrain.checkAvailability(seatsToCheck);
            System.out.println("Seats " + (isAvailable ? "available" : "not available") + " for booking.");
            return trainId;
        } else {
            System.out.println("Invalid Train ID.");
        }
        System.out.println();
        return 0;
    }



    private static void displayBookings(){
        for (Booking booking : bookingList){
            System.out.print("Booking Id : " + booking.getBookingId() + " , ");
            System.out.print("Train Name : " + booking.getTrain().getTrainName() + " , ");
            System.out.print("Passenger Name : " + booking.getPassenger().getName() + " , ");
            System.out.print("Passenger Age : " + booking.getPassenger().getAge() + " , ");
            System.out.print("Passenger Contact : " + booking.getPassenger().getContact() + " , ");
            System.out.println();
        }
    }

    private static void cancellation(Scanner scanner){
        System.out.print("Enter Booking Id to cancel : ");
        displayBookings();
        int bookingId = scanner.nextInt();
        Booking bookingToCancel = getBookingById(bookingId);
        if (bookingToCancel != null){
            Train train = bookingToCancel.getTrain();
            train.cancelSeats(bookingToCancel.getBookedSeats());
            bookingList.remove(bookingToCancel);
        }else{
            System.out.println("Please Enter Valid Booking Id");
        }
    }

    private static Train getTrainById(int trainId){
        for(Train train : trains){
            if(train.getTrainId() == trainId) return train;
        }
        return null;
    }

    private static Booking getBookingById(int bookingId){
        for(Booking booking : bookingList){
            if(booking.getBookingId() == bookingId) return booking;
        }
        return null;
    }

}
