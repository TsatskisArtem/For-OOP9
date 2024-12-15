package lab9;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit5.testcase.AssertJSwingJUnitTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.table.DefaultTableModel;
import static org.junit.jupiter.api.Assertions.*;

public class OOPlab9Test extends AssertJSwingJUnitTestCase {

    private FrameFixture window;
    private OOPlab9 app;
    private DefaultTableModel model;

    @BeforeEach
    void setUp() {
        app = GuiActionRunner.execute(() -> {
            OOPlab9 oopl = new OOPlab9();
            oopl.show();
            return oopl;
        });
        window = new FrameFixture(robot(), app.bookList);
        model = (DefaultTableModel) app.books.getModel();
    }

    @Test
    void testDeleteBook() {
        int initialRowCount = model.getRowCount();
        window.table().selectRows(0);
        window.button("Удалить").click();
        window.optionPane().requireMessage("Книга удалена.").ok();
        assertEquals(initialRowCount - 1, model.getRowCount());
    }

    @Test
    void testGetBookList() {
        String[][] expectedData = {
            {"Война и мир", "Лев Толстой", "Arial", "Нет"},
            {"1984", "Джордж Оруэлл", "Calibri", "Да"},
            {"Прощай оружие!", "Эрнест Хемингуэй", "Garamond", "Нет"},
            {"Убить пересмешника", "Харпер Ли", "Fraktur", "Да"},
            {"На дороге", "Джек Керуак", "Papyrus", "Нет"}
        };

        for (int i = 0; i < expectedData.length; i++) {
            for (int j = 0; j < expectedData[i].length; j++) {
                assertEquals(expectedData[i][j], model.getValueAt(i, j));
            }
        }
    }

    @Test
    void testSaveData() {
        window.button("Сохранить").click();
        window.optionPane().requireMessage("Данные сохранены.").ok();
    }
}