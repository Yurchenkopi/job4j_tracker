package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TrackerTest {
    @Test
    public void whenTestFindById() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        Item item = tracker.add(bug);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenTestFindAll() {
        Tracker tracker = new Tracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        Item result = tracker.findAll().get(0);
        assertThat(result.getName(), is(first.getName()));
    }

    @Test
    public void whenTestFindByNameCheckArrayLength() {
        Tracker tracker = new Tracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        tracker.add(new Item("First"));
        tracker.add(new Item("Second"));
        tracker.add(new Item("First"));
        List<Item> result = tracker.findByName(first.getName());
        assertThat(result.size(), is(3));
    }

    @Test
    public void whenTestFindByNameCheckSecondItemName() {
        Tracker tracker = new Tracker();
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        tracker.add(new Item("First"));
        tracker.add(new Item("Second"));
        tracker.add(new Item("First"));
        List<Item> result = tracker.findByName(second.getName());
        assertThat(result.get(1).getName(), is(second.getName()));
    }

    @Test
    public void whenReplace() {
        Tracker tracker = new Tracker();
        Item bug = new Item();
        bug.setName("Bug");
        tracker.add(bug);
        int id = bug.getId();
        Item bugWithDesc = new Item();
        bugWithDesc.setName("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }

    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item bug = new Item();
        bug.setName("Bug");
        tracker.add(bug);
        int id = bug.getId();
        tracker.delete(id);
        assertNull(tracker.findById(id));
    }

    @Test
    public void whenCreateItem() {
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        Output out = new ConsoleOutput();
        Tracker tracker = new Tracker();
        List<UserAction> actions = Arrays.asList(
                new CreateAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem()  {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Output out = new ConsoleOutput();
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "New item name", "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new EditAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem()  {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Output out = new ConsoleOutput();
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new DeleteAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        assertNull(tracker.findById(item.getId()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        Tracker tracker = new Tracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu:" + System.lineSeparator()
                        + "0. Exit Program" + System.lineSeparator()
                        + "=== Exit Program ===" + System.lineSeparator()
        ));
    }

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(one.getId()), replaceName, "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new EditAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
                        + "=== Edit item ===" + ln
                        + "???????????? ???????????????? ??????????????." + ln
                        + "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        ));
    }

    @Test
    public void whenFindAllActionTestOutputAndHaveItemsIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test1"));
        Item item2 = tracker.add(new Item("test2"));
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new ShowAllAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Show all items ===" + ln
                        + item1 + ln
                        + item2 + ln
                        + "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        ));
    }

    @Test
    public void whenFindByNameActionTestOutputAndHaveItemsIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("test1"));
        Item item2 = tracker.add(new Item("Needed Item"));
        Input in = new StubInput(
                new String[] {"0", "Needed Item", "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindItemByNameAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Find item by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Find items by name ===" + ln
                        + item2 + ln
                        + "Menu:" + ln
                        + "0. Find item by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        ));
    }

    @Test
    public void whenFindByIdActionTestOutputAndHaveItemsIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item[] arrItem = {tracker.add(new Item("test1")),
                          tracker.add(new Item("Needed Item"))
                         };
        Input in = new StubInput(
                new String[] {"0", String.valueOf(arrItem[1].getId()), "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindItemByIdAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Find item by id ===" + ln
                        + arrItem[1] + ln
                        + "Menu:" + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        ));
    }

    @Test
    public void whenFindAllActionTestOutputAndNoItemsIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new ShowAllAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Show all items ===" + ln
                        + "?????????????????? ?????? ???? ???????????????? ????????????" + ln
                        + "Menu:" + ln
                        + "0. Show all items" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        ));
    }

    @Test
    public void whenFindByNameActionTestOutputAndNoItemsIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Input in = new StubInput(
                new String[] {"0", "Needed Item", "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindItemByNameAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Find item by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Find items by name ===" + ln
                        + "???????????? ?? ????????????: Needed Item ???? ??????????????." + ln
                        + "Menu:" + ln
                        + "0. Find item by name" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        ));
    }

    @Test
    public void whenFindByIdActionTestOutputAndNoItemsIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Input in = new StubInput(
                new String[] {"0", "99", "1"}
        );
        List<UserAction> actions = Arrays.asList(
                new FindItemByIdAction(out),
                new ExitAction(out)
        );
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Find item by id ===" + ln
                        + "???????????? ?? ?????????????????? id: 99 ???? ??????????????." + ln
                        + "Menu:" + ln
                        + "0. Find item by id" + ln
                        + "1. Exit Program" + ln
                        + "=== Exit Program ===" + ln
        ));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"7", "0"}
        );
        Tracker tracker = new Tracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction(out));
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                        "Menu:" + ln
                                + "0. Exit Program" + ln
                                + "Wrong input, you can select: 0 .. 0" + ln
                                + "Menu:" + ln
                                + "0. Exit Program" + ln
                                + "=== Exit Program ===" + ln
                )
        );
    }

    @Test
    public void whenSortByName() {
        Tracker tracker = new Tracker();
        List<Item> items = Arrays.asList(new Item(2, "test2"),
                                    new Item(4, "test4"),
                                    new Item(3, "test3"),
                                    new Item(1, "test1"));
        List<Item> expected = Arrays.asList(new Item(1, "test1"),
                                            new Item(2, "test2"),
                                            new Item(3, "test3"),
                                            new Item(4, "test4"));
        Collections.sort(items, new ItemAscByName());
        assertThat(items, is(expected));
    }

    @Test
    public void whenSortByNameRev() {
        Tracker tracker = new Tracker();
        List<Item> items = Arrays.asList(new Item(2, "test2"),
                new Item(4, "test4"),
                new Item(3, "test3"),
                new Item(1, "test1"));
        List<Item> expected = Arrays.asList(new Item(4, "test4"),
                new Item(3, "test3"),
                new Item(2, "test2"),
                new Item(1, "test1"));
        Collections.sort(items, new ItemDescByName());
        assertThat(items, is(expected));
    }
}

