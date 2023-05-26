import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void constructorThrowsExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10.0, 100.0));
    }

    @Test
    void constructorThrowsExceptionWithExpectedMessageWhenNameIsNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10.0, 100.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void constructorThrowsExceptionWhenNameIsBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10.0, 100.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void constructorThrowsExceptionWithExpectedMessageWhenNameIsBlank(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 10.0, 100.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorThrowsExceptionWhenSpeedIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("MyHorse", -10.0, 100.0));
    }

    @Test
    void constructorThrowsExceptionWithExpectedMessageWhenSpeedIsNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("MyHorse", -10.0, 100.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorThrowsExceptionWhenDistanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("MyHorse", 10.0, -100.0));
    }

    @Test
    void constructorThrowsExceptionWithExpectedMessageWhenDistanceIsNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("MyHorse", 10.0, -100.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameReturnsExpectedValue() {
        String expectedName = "MyHorse";
        Horse horse = new Horse(expectedName, 10.0, 100.0);
        String actualName = horse.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    void getSpeedReturnsExpectedValue() {
        double expectedSpeed = 10.0;
        Horse horse = new Horse("MyHorse", expectedSpeed, 100.0);
        double actualSpeed = horse.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    void getDistanceReturnsExpectedValue() {
        double expectedDistance = 100.0;
        Horse horse = new Horse("MyHorse", 10.0, expectedDistance);
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void getDistanceReturnsZeroWithTwoParameterConstructor() {
        double expectedDistance = 0.0;
        Horse horse = new Horse("MyHorse", 10.0);
        double actualDistance = horse.getDistance();
        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void moveCallsGetRandomDoubleAndUpdatesDistance() {
        double speed = 10.0;
        double distance = 100.0;
        double randomDouble = 0.5;

        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDouble);

            Horse horse = new Horse("MyHorse", speed, distance);
            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            double expectedDistance = distance + speed * randomDouble;
            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}