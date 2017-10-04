
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uit.edu.project.infrastructure;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thawaye
 */
public class ConnectionPoolManager {

    // Normally, we should not open and then close Connection. In our ordinary approach,
    // we open connection then run the SQL statements then we close it. But this approach is
    // not good for performance because establishing the connection is very expensive task in JVM
    // and network. So, we use connection pooling.
    // You can read more at : https://stackoverflow.com/questions/16093054/why-we-need-a-connection-pooling-for-jdbc
    // In my code, I use c3p0 connection pooling.
    private final static ComboPooledDataSource COMBO_POOLED_DATA_SOURCE = new ComboPooledDataSource();

    static {
        try {

            COMBO_POOLED_DATA_SOURCE.setDriverClass("com.mysql.jdbc.Driver");
            COMBO_POOLED_DATA_SOURCE.setUser("root");
            COMBO_POOLED_DATA_SOURCE.setPassword("new_password");
            COMBO_POOLED_DATA_SOURCE.setJdbcUrl("jdbc:mysql://localhost:3306/hospital");

            COMBO_POOLED_DATA_SOURCE.setMinPoolSize(5);
            COMBO_POOLED_DATA_SOURCE.setAcquireRetryAttempts(5);
            COMBO_POOLED_DATA_SOURCE.setAcquireIncrement(5);
            COMBO_POOLED_DATA_SOURCE.setMaxIdleTime(600);
            COMBO_POOLED_DATA_SOURCE.setIdleConnectionTestPeriod(300);
            COMBO_POOLED_DATA_SOURCE.setMaxPoolSize(10);
            COMBO_POOLED_DATA_SOURCE.setMaxStatements(50);

        } catch (PropertyVetoException ex) {
            Logger.getLogger(ConnectionPoolManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return COMBO_POOLED_DATA_SOURCE.getConnection();
    }
}
