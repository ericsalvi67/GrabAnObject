package domain.Users;

import java.time.OffsetDateTime;

public class UsersDTO {

    private int id;
    private String name;
    private String email;
    private OffsetDateTime lastModification;
    private boolean deleted;

    public UsersDTO() {}

    public UsersDTO(int id, String name, String email, OffsetDateTime lastModification) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lastModification = lastModification;
        this.deleted = false;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public OffsetDateTime getLastModification() { return lastModification; }
    public void setLastModification(OffsetDateTime lastModification) { this.lastModification = lastModification; }

    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
}