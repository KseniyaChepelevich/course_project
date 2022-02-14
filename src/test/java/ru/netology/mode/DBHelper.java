package ru.netology.mode;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBHelper {
    private final QueryRunner runner = new QueryRunner();
    private Properties prop = prop();
    private final Connection conn = getConnect();

    private Properties prop() {
        Properties properties = new Properties();
        try (InputStream is = DBHelper.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    @SneakyThrows
    private Connection getConnect() {
        return DriverManager.getConnection(
                prop.getProperty("spring.datasource.url"),
                prop.getProperty("spring.datasource.username"),
                prop.getProperty("spring.datasource.password")
        );
    }

    public static class DeleteInfo {
        @SneakyThrows
        public static void deletingData() {
            var deleteFromPaymentEntity = "DELETE FROM payment_entity;";
            var deleteFromCreditRequestEntity = "DELETE FROM credit_request_entity;";
            var deleteFromOrderEntity = "DELETE FROM order_entity;";

            try (
                    var conn = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/aqa-shop", "aqa", "mypass"
                    );
                    var deleteStmt = conn.createStatement();
            ) {

                var paymentEntity = deleteStmt.executeUpdate(deleteFromPaymentEntity);
                var creditRequestEntity = deleteStmt.executeUpdate(deleteFromCreditRequestEntity);
                var orderEntity = deleteStmt.executeUpdate(deleteFromOrderEntity);
            }
        }
    }


    @SneakyThrows
    public String getPaymentStatus() {
        var status = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, status, new ScalarHandler<>());
    }

//    @SneakyThrows
//    public String getPaymentAmount() {
//        var status = "SELECT amount FROM payment_entity";
//        return status;
//    }

    @SneakyThrows
    public String getCreditStatus() {
        var status = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, status, new ScalarHandler<>());
    }

//"SELECT status FROM payment_entity JOIN order_entity ON order_entity.payment_id = payment_entity.transaction_id ORDER BY created DESC LIMIT 1;";

    @SneakyThrows
    public String getCreditId() {
        var creditId = "SELECT credit_id FROM order_entity ORDER BY created DESC LIMIT 1";
        return runner.query(conn, creditId, new ScalarHandler<>());
    }

    @SneakyThrows
    public String getTransactionId() {
        var transactionId = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, transactionId, new ScalarHandler<>());
    }

    @SneakyThrows
    public String getPaymentId() {
        var paymentId = "SELECT payment_id FROM order ORDER BY created DESC LIMIT 1;";
        return runner.query(conn, paymentId, new ScalarHandler<>());
    }

//    @SneakyThrows
//    public String getPayment() {
//        var paymentId = "SELECT payment_id, status FROM payment_entity JOIN order_entity ON payment_entity.payment_id = order_entity.payment_id;";
//    }

}
