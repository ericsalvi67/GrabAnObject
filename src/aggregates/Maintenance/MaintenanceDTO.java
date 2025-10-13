package aggregates.Maintenance;

import java.time.OffsetDateTime;

public class MaintenanceDTO {
    private int id;
    private int objectId;
    private String description;
    private Integer performedBy;
    private OffsetDateTime lastModification;
    private boolean deleted;

    public MaintenanceDTO(int id, int objectId, String description, Integer performedBy, OffsetDateTime lastModification) {
        this.id = id;
        this.objectId = objectId;
        this.description = description;
        this.performedBy = performedBy;
        this.lastModification = lastModification;
        this.deleted = false;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getObjectId() { return objectId; }
    public void setObjectId(int objectId) { this.objectId = objectId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getPerformedBy() { return performedBy; }
    public void setPerformedBy(Integer performedBy) { this.performedBy = performedBy; }

    public OffsetDateTime getLastModification() { return lastModification; }
    public void setLastModification(OffsetDateTime lastModification) { this.lastModification = lastModification; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}
