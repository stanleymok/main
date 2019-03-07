package seedu.address.model.apparel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.SHIRT1;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.ApparelBuilder;

public class ApparelTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Apparel apparel = new ApparelBuilder().build();
        thrown.expect(UnsupportedOperationException.class);
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(SHIRT1.isSameApparel(SHIRT1));

        // null -> returns false
        assertFalse(SHIRT1.isSameApparel(null));

        // different phone and email -> returns false
        Apparel editedAlice = new ApparelBuilder(SHIRT1).withColor(VALID_PHONE_BOB).withClothingType(VALID_EMAIL_BOB).build();
        assertFalse(SHIRT1.isSameApparel(editedAlice));

        // different name -> returns false
        editedAlice = new ApparelBuilder(SHIRT1).withName(VALID_NAME_BOB).build();
        assertFalse(SHIRT1.isSameApparel(editedAlice));

        // same name, same phone, different attributes -> returns true
        editedAlice = new ApparelBuilder(SHIRT1).withClothingType(VALID_EMAIL_BOB).build();
        assertTrue(SHIRT1.isSameApparel(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new ApparelBuilder(SHIRT1).withColor(VALID_PHONE_BOB).build();
        assertTrue(SHIRT1.isSameApparel(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
        editedAlice = new ApparelBuilder(SHIRT1).build();
        assertTrue(SHIRT1.isSameApparel(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Apparel aliceCopy = new ApparelBuilder(SHIRT1).build();
        assertTrue(SHIRT1.equals(aliceCopy));

        // same object -> returns true
        assertTrue(SHIRT1.equals(SHIRT1));

        // null -> returns false
        assertFalse(SHIRT1.equals(null));

        // different type -> returns false
        assertFalse(SHIRT1.equals(5));

        // different apparel -> returns false
        assertFalse(SHIRT1.equals(BOB));

        // different name -> returns false
        Apparel editedAlice = new ApparelBuilder(SHIRT1).withName(VALID_NAME_BOB).build();
        assertFalse(SHIRT1.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new ApparelBuilder(SHIRT1).withColor(VALID_PHONE_BOB).build();
        assertFalse(SHIRT1.equals(editedAlice));

        // different email -> returns false
        editedAlice = new ApparelBuilder(SHIRT1).withClothingType(VALID_EMAIL_BOB).build();
        assertFalse(SHIRT1.equals(editedAlice));

        // different address -> returns false
        editedAlice = new ApparelBuilder(SHIRT1).build();
        assertFalse(SHIRT1.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new ApparelBuilder(SHIRT1).build();
        assertFalse(SHIRT1.equals(editedAlice));
    }
}
