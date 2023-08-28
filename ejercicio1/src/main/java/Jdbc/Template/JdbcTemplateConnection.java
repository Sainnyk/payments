package Jdbc.Template;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.formacion.ejercicio1.DTO.PaymentDTO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.List;

@Repository
public class JdbcTemplateConnection {
    //Crea un contexto de aplicación empleando JdbcConfig como clase de configuración
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);


    private final PaymentRowMapper paymentRowMapper;
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateConnection(PaymentRowMapper paymentRowMapper, JdbcTemplate jdbcTemplate) {
        this.paymentRowMapper = paymentRowMapper;
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<PaymentDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM payments", new PaymentRowMapper());
    }

    public List<PaymentDTO> findByIban(String creditor) {
        return jdbcTemplate.query("SELECT * FROM payments WHERE creditor LIKE ?",
                (rs, rowNum) -> paymentRowMapper.mapRow(rs, rowNum), creditor);
    }

    public void addPayment(){
        String insertPaymentData = "INSERT INTO payments (payment_id, dbtr_nm, debtor, dbtr_ctry, cdtr_nm, creditor, amount, cre_dt_tm) " +
                "VALUES (5, 'Susana Gomez', 'ES4502337943000620000000', 'ES', 'Carlos Lopez ', 'ES4502337943000620006699', 45.31, NOW());";
        jdbcTemplate.execute(insertPaymentData);
    }
    public void deletePayment(){
        String deletePaymentData="DELETE from payments where id = 7";
        jdbcTemplate.execute(deletePaymentData);
    }
    public static void main(String[] args) throws JsonProcessingException {

        JdbcConfig config = new JdbcConfig();
        DataSource dataSource = config.postgresDataSource();

        JdbcTemplateConnection jdbcTemplateConnection = new JdbcTemplateConnection(new PaymentRowMapper(),new JdbcTemplate(dataSource));
        //System.out.println(jdbcTemplateConnection.findByIban("ES5300490418450200051333"));

        System.out.println(jdbcTemplateConnection.findAll());
    }
}

