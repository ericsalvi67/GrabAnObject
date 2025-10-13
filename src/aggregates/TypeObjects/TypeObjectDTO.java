package aggregates.TypeObjects;

import java.time.OffsetDateTime;

public class TypeObjectDTO {
    private int id;
    private String typeName;
    private String description;
    private OffsetDateTime lastModification;
    private boolean deleted;

    public TypeObjectDTO(int id, String typeName, String description, OffsetDateTime lastModification) {
        this.id = id;
        this.typeName = typeName;
        this.description = description;
        this.lastModification = lastModification;
        this.deleted = false;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public OffsetDateTime getLastModification() { return lastModification; }
    public void setLastModification(OffsetDateTime lastModification) { this.lastModification = lastModification; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}
