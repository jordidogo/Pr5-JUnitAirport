package es.ull.flights;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Flight Testing")
public class FlightTest {

    private Flight flight;
    private Flight flight2;
    private Passenger passenger;
    private Passenger passenger2;
    private final String flightNumber = "CD8230";
    private final String flightNumber2 = "CD8330";

    @BeforeEach
    void setUp() {
        flight = new Flight(flightNumber, 1);
        flight2 = new Flight(flightNumber2, 30);
        passenger = new Passenger("1", "Jorge", "ES");
        passenger2 = new Passenger("2", "Ruyman", "ES");
    }

    @Test
    @DisplayName("Class Getters")
    void testFlightGetters() {
        assertAll("Verify all attributes are set correctly",
                () -> assertEquals(flightNumber, flight.getFlightNumber(), "Flight Number"),
                () -> assertTrue(flight.addPassenger(passenger), "Adding a passenger"),
                () -> assertEquals(1, flight.getNumberOfPassengers(), "Number of passengers"),
                () -> assertEquals(flightNumber2, flight2.getFlightNumber(), "Flight Number"),
                () -> assertTrue(flight2.addPassenger(passenger2), "Adding a passenger"),
                () -> assertEquals(1, flight2.getNumberOfPassengers(), "Number of passengers")
        );
    }

    @Test
    @DisplayName("Adding passengers")
    void testAddingPassengers() {
        Passenger noSeatForHim = new Passenger("2", "Ruyman", "ES");
        assertAll("Verify we can add passengers if there are available seats",
                () -> assertTrue(flight.addPassenger(passenger), "We can add a passenger"),
                () -> assertThrows(RuntimeException.class, ()-> flight.addPassenger(noSeatForHim), "We cannot add a passenger")
        );
    }

    @Test
    @DisplayName("Removing passengers")
    void testRemovingPassengers() {
        assertAll("Verify we can remove passengers",
                () -> assertTrue(flight.addPassenger(passenger), "Adding a passenger"),
                () -> assertTrue(flight.removePassenger(passenger), "We can remove him"),
                () -> assertEquals(0, flight.getNumberOfPassengers(), "No passengers on the flight")
        );
    }
    
    @Test
    @DisplayName("We can verify flight information and flight passengers")
    public void testAttributesFlight() {
        assertAll("Verify conditions for a flight",
                () -> assertEquals("CD8230", flight.getFlightNumber()),
                () -> assertEquals("CD8330", flight2.getFlightNumber()),
                () -> assertEquals(0, flight.getNumberOfPassengers()),
                () -> assertEquals(0, flight2.getNumberOfPassengers())
        );
    }
}
