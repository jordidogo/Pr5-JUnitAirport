package es.ull.passengers;

import es.ull.flights.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Passengers Testing")
public class PassengerTest {

    private Passenger passenger;

    @BeforeEach
    void setUp() {
        String countryCode = "ES";
        passenger = new Passenger("1", "Jorge", countryCode);
    }

    @Test
    @DisplayName("Class Getters")
    void testFlightGetters() {
        assertAll("Verify all attributes are set correctly",
                () -> assertEquals("1", passenger.getIdentifier(), "Passenger ID"),
                () -> assertEquals("Jorge", passenger.getName(), "Passenger Name"),
                () -> assertEquals("ES", passenger.getCountryCode(), "Passenger Country Code"),
                () -> assertEquals("Passenger Jorge with identifier: 1 from ES" , passenger.toString())
        );
    }

    @Test
    @DisplayName("Joining a flight")
    void testJoiningFlight() {
        Flight flight = new Flight("CD8230", 1);
        Flight emptyFlight = new Flight("CD8233", 0);
        assertAll("Verify we can join a flight",
                () -> assertDoesNotThrow(()-> passenger.joinFlight(flight), "We can join a flight"),
                () -> assertEquals(flight, passenger.getFlight(), "The flight is set correctly"),
                () -> assertThrows(RuntimeException.class, () -> passenger.joinFlight(emptyFlight), "We cannot join a flight")
        );
    }
}
