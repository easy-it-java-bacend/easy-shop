package kg.marketplace.easyshop.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
@NoArgsConstructor
public class BaseEntityAudit extends BaseEntity {

    @Column(name = "date_created")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected Date dateCreated;

    @Column(name = "date_updated")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected Date dateUpdated;

    @Column(name = "created_by")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createdBy;

    @Column(name = "updated_by")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String updatedBy;
}
