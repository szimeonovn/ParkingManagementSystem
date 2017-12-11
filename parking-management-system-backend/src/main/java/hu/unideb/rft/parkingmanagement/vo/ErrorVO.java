package hu.unideb.rft.parkingmanagement.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorVO {

    public ErrorVO(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

}
