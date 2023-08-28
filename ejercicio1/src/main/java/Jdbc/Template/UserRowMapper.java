package Jdbc.Template;

import com.formacion.ejercicio1.DTO.PaymentDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<PaymentDTO> {

    @Override
    public PaymentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PaymentDTO(rs.getLong("id"), rs.getInt("payment_id"), rs.getString("dbtr_nm"),
                rs.getString("debtor"), rs.getString("dbtr_ctry"), rs.getString("cdtr_nm"),
                rs.getString("creditor"), rs.getBigDecimal("amount"), rs.getTimestamp("cre_dt_tm"));

    }
}
