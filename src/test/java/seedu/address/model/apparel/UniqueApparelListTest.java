package seedu.address.model.apparel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.apparel.exceptions.DuplicatePersonException;
import seedu.address.model.apparel.exceptions.PersonNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class UniqueApparelListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final UniqueApparelList uniqueApparelList = new UniqueApparelList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueApparelList.contains(null);
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueApparelList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueApparelList.add(ALICE);
        assertTrue(uniqueApparelList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueApparelList.add(ALICE);
        Apparel editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueApparelList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueApparelList.add(null);
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueApparelList.add(ALICE);
        thrown.expect(DuplicatePersonException.class);
        uniqueApparelList.add(ALICE);
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueApparelList.setApparel(null, ALICE);
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueApparelList.setApparel(ALICE, null);
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        thrown.expect(PersonNotFoundException.class);
        uniqueApparelList.setApparel(ALICE, ALICE);
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueApparelList.add(ALICE);
        uniqueApparelList.setApparel(ALICE, ALICE);
        UniqueApparelList expectedUniqueApparelList = new UniqueApparelList();
        expectedUniqueApparelList.add(ALICE);
        assertEquals(expectedUniqueApparelList, uniqueApparelList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueApparelList.add(ALICE);
        Apparel editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueApparelList.setApparel(ALICE, editedAlice);
        UniqueApparelList expectedUniqueApparelList = new UniqueApparelList();
        expectedUniqueApparelList.add(editedAlice);
        assertEquals(expectedUniqueApparelList, uniqueApparelList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueApparelList.add(ALICE);
        uniqueApparelList.setApparel(ALICE, BOB);
        UniqueApparelList expectedUniqueApparelList = new UniqueApparelList();
        expectedUniqueApparelList.add(BOB);
        assertEquals(expectedUniqueApparelList, uniqueApparelList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueApparelList.add(ALICE);
        uniqueApparelList.add(BOB);
        thrown.expect(DuplicatePersonException.class);
        uniqueApparelList.setApparel(ALICE, BOB);
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueApparelList.remove(null);
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        thrown.expect(PersonNotFoundException.class);
        uniqueApparelList.remove(ALICE);
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueApparelList.add(ALICE);
        uniqueApparelList.remove(ALICE);
        UniqueApparelList expectedUniqueApparelList = new UniqueApparelList();
        assertEquals(expectedUniqueApparelList, uniqueApparelList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueApparelList.setApparels((UniqueApparelList) null);
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueApparelList.add(ALICE);
        UniqueApparelList expectedUniqueApparelList = new UniqueApparelList();
        expectedUniqueApparelList.add(BOB);
        uniqueApparelList.setApparels(expectedUniqueApparelList);
        assertEquals(expectedUniqueApparelList, uniqueApparelList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        uniqueApparelList.setApparels((List<Apparel>) null);
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueApparelList.add(ALICE);
        List<Apparel> apparelList = Collections.singletonList(BOB);
        uniqueApparelList.setApparels(apparelList);
        UniqueApparelList expectedUniqueApparelList = new UniqueApparelList();
        expectedUniqueApparelList.add(BOB);
        assertEquals(expectedUniqueApparelList, uniqueApparelList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Apparel> listWithDuplicateApparels = Arrays.asList(ALICE, ALICE);
        thrown.expect(DuplicatePersonException.class);
        uniqueApparelList.setApparels(listWithDuplicateApparels);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        uniqueApparelList.asUnmodifiableObservableList().remove(0);
    }
}
