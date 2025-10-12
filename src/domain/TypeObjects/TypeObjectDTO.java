package domain.TypeObjects;

import java.time.OffsetDateTime;

public class TypeObjectDTO {
    private final int id;
    private final String typeName;
    private final String description;
    private final OffsetDateTime lastModification;

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
}
