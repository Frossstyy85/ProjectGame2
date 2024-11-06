import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Entity resident;
    Entity burglar;

    @BeforeEach
    void resetEntity(){
        resident = Entity.createResident();
        burglar = Entity.createBurglar();
    }

    @Test
    void isConscious(){
        assertTrue(burglar.isConscious() && resident.isConscious());
    }

    @Test
    void isNotConscious(){

        burglar.takeHit(burglar.getHealth());
        resident.takeHit(resident.getHealth());

        assertFalse(resident.isConscious() && burglar.isConscious());
    }


    @Test
    void takeExpectedDamage(){

        int expectedHealth = resident.getHealth() - burglar.getDamage();

        burglar.punch(resident);

        assertEquals(resident.getHealth(), expectedHealth);
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Testing completed");
    }

}