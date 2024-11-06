import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Resident resident;
    Burglar burglar;

    @BeforeEach
    void resetEntity(){
        resident = new Resident(10,10);
        burglar = new Burglar(10,10);
    }

    @Test
    void isConscious(){
        assertTrue(resident.isConscious());
        assertTrue(burglar.isConscious());
    }

    @Test
    void isNotConscious(){
        resident.punch(burglar);
        burglar.punch(resident);

        assertFalse(resident.isConscious());
        assertFalse(burglar.isConscious());
    }

    @Test
    void takeDamage(){

        Entity attacker = resident;
        Entity defender = burglar;

        int expectedHealth = defender.getHealth() - attacker.getDamage();

        attacker.punch(defender);

        assertEquals(defender.getHealth(), expectedHealth);
    }




}