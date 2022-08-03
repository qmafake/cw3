package data.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Tr_types {
    @Id
    private Integer tr_type;

    private String tr_description;
}
