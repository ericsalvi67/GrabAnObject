package domain.TypeObjects;

import java.time.OffsetDateTime;

public class TypeObjectDTO {
    private int id;
    private String typeName;
    private String description;
    private OffsetDateTime lastModification;
    private boolean isDeleted;

    public TypeObjectDTO(int id, String typeName, String description, OffsetDateTime lastModification) {
        this.id = id;
        this.typeName = typeName;
        this.description = description;
        this.lastModification = lastModification;
    }

    public int getId() { return id; }
    public String getTypeName() { return typeName; }
    public String getDescription() { return description; }
    public OffsetDateTime getLastModification() { return lastModification; }
    public boolean isDeleted() { return isDeleted; }
}
