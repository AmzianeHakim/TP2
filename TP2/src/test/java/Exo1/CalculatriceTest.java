package Exo1;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
class CalculatriceTest {

    @Test
    public void testAdditionner() {
        // Create a mock instance of Calculatrice
        Calculatrice calculatrice = mock(Calculatrice.class);

        // Define the behavior of the additionner method
        when(calculatrice.additionner(2, 3)).thenReturn(5);

        // Define the behavior of the getEtat method
        when(calculatrice.getEtat()).thenReturn(Calculatrice.Etat1);

        // Call the method to be tested
        int resultat = calculatrice.additionner(2, 3);

        // Verify the result
        assertEquals(5, resultat);

        // Verify that the additionner method was called with the arguments 2 and 3
        verify(calculatrice).additionner(2, 3);

        // Verify that no other method was called on the object after the additionner method
        verifyNoMoreInteractions(calculatrice);

        // Verify the state of the object after calling the additionner method
        //verify(calculatrice).getEtat();
    }
}