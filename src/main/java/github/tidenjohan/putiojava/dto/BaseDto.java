package github.tidenjohan.putiojava.dto;

public class BaseDto {
    public final String status;

    public BaseDto(String status) {
       this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
