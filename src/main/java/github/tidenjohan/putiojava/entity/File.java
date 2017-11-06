package github.tidenjohan.putiojava.entity;

public class File extends BaseEntity {
    private final long id;

    public File(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
