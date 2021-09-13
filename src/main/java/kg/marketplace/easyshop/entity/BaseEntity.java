package kg.marketplace.easyshop.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected Long id;

    @Column
    protected boolean archived;

    @Column
    protected boolean deleted;

    @Column
    protected Date dateCreated;

    @Column
    protected Date dateUpdated;


}
