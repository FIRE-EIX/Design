package Dao;

import java.sql.SQLException;

class AddManagementTest {

    @org.junit.jupiter.api.Test
    void addManagement() throws SQLException {
        new AddManagement().getConniction();
    }
}