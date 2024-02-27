package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MemTrackerMockTest {

    @Test
    public void whenItemWasUpdatedSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        EditAction replaceAction = new EditAction(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);

        replaceAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Edit item ===" + ln
                        + "Заявка изменена успешно." + ln
        );
    }

    @Test
    public void whenItemWasNotUpdatedSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        EditAction replaceAction = new EditAction(output);

        Input input = mock(Input.class);

        replaceAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Edit item ===" + ln
                        + "Ошибка замены заявки." + ln
        );
    }

    @Test
    public void whenItemWasDeletedSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Item for delete action"));
        DeleteAction deleteAction = new DeleteAction(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Delete item ===" + ln
                        + "Заявка удалена успешно." + ln
        );
    }

    @Test
    public void whenItemWasNotDeletedSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Item for delete action"));
        DeleteAction deleteAction = new DeleteAction(output);

        Input input = mock(Input.class);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "=== Delete item ===" + ln
                        + "Ошибка удаления заявки." + ln
        );
    }

    @Test
    public void whenItemWasFoundByIdSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("Item for findById action");
        tracker.add(item);
        FindItemByIdAction findItemByIdAction = new FindItemByIdAction(output);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        findItemByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        String expected = String.format("=== Find item by id ===%s%s%s", ln, item, ln);

        assertThat(output.toString()).isEqualTo(expected);
    }

    @Test
    public void whenItemWasNotFoundByIdSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Item for findById action"));
        FindItemByIdAction findItemByIdAction = new FindItemByIdAction(output);
        int searchedId = 5;

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(searchedId);

        findItemByIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        String expected = String.format(
                "=== Find item by id ===%sЗаявка с введенным id: %d не найдена.%s",
                ln, searchedId, ln
        );

        assertThat(output.toString()).isEqualTo(expected);
    }

    @Test
    public void whenItemWasFoundByNameSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("Item for findByName action");
        tracker.add(item);
        FindItemByNameAction findItemByNameAction = new FindItemByNameAction(output);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(item.getName());

        findItemByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        String expected = String.format("=== Find items by name ===%s%s%s", ln, item, ln);

        assertThat(output.toString()).isEqualTo(expected);
    }

    @Test
    public void whenItemWasNotFoundByNameSuccessfully() {
        Output output = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Item for findByName action"));
        FindItemByNameAction findItemByNameAction = new FindItemByNameAction(output);
        String searchedItemName = "Searched item";

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(searchedItemName);

        findItemByNameAction.execute(input, tracker);

        String ln = System.lineSeparator();
        String expected = String.format(
                "=== Find items by name ===%sЗаявки с именем: %s не найдены.%s",
                ln, searchedItemName, ln
        );

        assertThat(output.toString()).isEqualTo(expected);
    }
}