package Jdbc;

import java.sql.*;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JdbcConnection {
    public static void main(String[] args) throws JsonProcessingException {


        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "postgres";

        //////////////////////////////////////////////////////////////////////////
        String deleteTable= "DROP TABLE payments";
        String deleteTable2= "DROP TABLE users";

        String createPaymentsTableSQL = "CREATE TABLE IF NOT EXISTS payments ("
                + "id SERIAL PRIMARY KEY,"
                + "payment_id INT REFERENCES users(id),"
                + "dbtr_nm VARCHAR(100)," //Nombre del deudor
                + "debtor VARCHAR(100)," //Iban del deudor
                + "dbtr_ctry VARCHAR(100)," // País del deudor.
                + "cdtr_nm VARCHAR(100)," //Nombre del creditor
                + "creditor VARCHAR(100)," //Iban del creditor
                + "amount DECIMAL(10, 2),"
                + "cre_dt_tm TIMESTAMP"//Fecha y hora de creación
                + ");";

        String createUsersTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id SERIAL PRIMARY KEY,"
                + "name VARCHAR(20)"
                + ");";
        String updateUsers = "UPDATE users "+
                "SET code = 'CL01' WHERE name= 'Carlos Lopez';";

        String insertPaymentData = "INSERT INTO payments (payment_id, dbtr_nm, debtor, dbtr_ctry, cdtr_nm, creditor, amount, cre_dt_tm) " +
                "VALUES (5, 'Susana Gomez', 'ES4502337943000620000000', 'ES', 'Martinez Augusto ', 'ES4502337943000620001122', 4531.31, NOW());";
        String insertUserData= "INSERT INTO users (name) VALUES ('Silvia Jimenez');";

        String verifyIban = "SELECT * from payments where creditor = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Connected to the PostgreSQL database!");

            PreparedStatement preparedStatement = connection.prepareStatement(verifyIban);

            try (Statement statement = connection.createStatement()) {
                // Ejecuta la sentencia SQL para crear la tabla
/*
                String parameter = "ES5300490418450200051332";
                preparedStatement.setString(1,parameter);
                ResultSet resultSet = preparedStatement.executeQuery();
                ObjectMapper objectMapper = new ObjectMapper();

                    while (resultSet.next()) {
                        String column1Value = resultSet.getString("payment_id");
                        String column2Value = resultSet.getString("dbtr_nm");
                        String column3Value = resultSet.getString("debtor");
                        String column4Value = resultSet.getString("dbtr_ctry");
                        String column5Value = resultSet.getString("cdtr_nm");
                        String column6Value = resultSet.getString("creditor");
                        String column7Value = resultSet.getString("amount");
                        String column8Value = resultSet.getString("cre_dt_tm");


                        String json = objectMapper.writeValueAsString(Map.of("payment_id", column1Value, "dbtr_nm", column2Value,"debtor", column3Value,
                                "dbtr_ctry", column4Value,"cdtr_nm", column5Value,"creditor", column6Value,"amount", column7Value,"cre_dt_tm", column8Value));

                        System.out.println(json);

                    }*/


                statement.execute(insertPaymentData);

                //statement.execute(createPaymentsTableSQL);
               // statement.execute(updateUsers);

            }
        } catch (SQLException e) {
            System.out.println("Connection failure or error creating table: " + e.getMessage());
        }
    }
}