package aggregates.Objects;

import java.time.OffsetDateTime;

public class ObjectDTO {
    private int id;
    private String name;
    private Integer typeId;
    private Integer createdBy;
    private OffsetDateTime lastModification;
    private boolean deleted;

    public ObjectDTO(int id, String name, Integer typeId, Integer createdBy, OffsetDateTime lastModification) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.createdBy = createdBy;
        this.lastModification = lastModification;
        this.deleted = false;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getTypeId() { return typeId; }
    public void setTypeId(Integer typeId) { this.typeId = typeId; }

    public Integer getCreatedBy() { return createdBy; }
    public void setCreatedBy(Integer createdBy) { this.createdBy = createdBy; }

    public OffsetDateTime getLastModification() { return lastModification; }
    public void setLastModification(OffsetDateTime lastModification) { this.lastModification = lastModification; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}
