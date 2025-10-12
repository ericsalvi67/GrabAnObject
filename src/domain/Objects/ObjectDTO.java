package domain.Objects;

import java.time.OffsetDateTime;

public class ObjectDTO {
    private final int id;
    private final String name;
    private final Integer typeId;
    private final Integer createdBy;
    private final OffsetDateTime lastModification;

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
}
