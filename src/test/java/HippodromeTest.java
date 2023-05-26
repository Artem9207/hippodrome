import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {
    @Test
    void constructorThrowsExceptionWhenHorsesIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void constructorThrowsExceptionWithExceptionMessageWhenHorsesIsNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorThrowsExceptionWhenHorsesIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
    }

    @Test
    void constructorThrowsExceptionWithExceptionMessageWhenHorsesIsEmpty() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesReturnsSameListAsConstructor() {
        List<Horse> originalHorses = createListOfHorses(30);
        Hippodrome hippodrome = new Hippodrome(originalHorses);
        List<Horse> retrievedHorses = hippodrome.getHorses();
        assertIterableEquals(originalHorses, retrievedHorses);
    }

    @Test
    void moveCallsMoveMethodOnAllHorses() {
        List<Horse> mockedHorses = createMockedHorses(50);
        Hippodrome hippodrome = new Hippodrome(mockedHorses);
        hippodrome.move();
        for (Horse horse : mockedHorses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinnerReturnsHorseWithMaxDistance() {
        Horse horse1 = new Horse("Horse 1", 10.0, 100.0);
        Horse horse2 = new Horse("Horse 2", 15.0, 150.0);
        Horse horse3 = new Horse("Horse 3", 12.0, 200.0);

        List<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();

        assertEquals(horse3, winner);
    }




    private List<Horse> createListOfHorses(int count) {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            horses.add(new Horse("Horse " + (i + 1), 0.0, 0.0));
        }
        return horses;
    }

    private List<Horse> createMockedHorses(int count) {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Horse horse = mock(Horse.class);
            horses.add(horse);
        }
        return horses;
    }

}