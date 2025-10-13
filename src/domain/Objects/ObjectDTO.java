package domain.Objects;

import java.time.OffsetDateTime;

public class ObjectDTO {
    private int id;
    private String name;
    private Integer typeId;
    private Integer createdBy;
    private OffsetDateTime lastModification;
    private boolean isDeleted;

    public ObjectDTO(int id, String name, Integer typeId, Integer createdBy, OffsetDateTime lastModification) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.createdBy = createdBy;
        this.lastModification = lastModification;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Integer getTypeId() { return typeId; }
    public Integer getCreatedBy() { return createdBy; }
    public OffsetDateTime getLastModification() { return lastModification; }
    public boolean isDeleted() { return isDeleted; }
}
