package data.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Transactions {
    @Id
    private Integer customer_id;

    private Date tr_datetime;

    private Integer mcc_code;

    private Integer tr_type;

    private Integer amount;

    private Integer term_id;
}