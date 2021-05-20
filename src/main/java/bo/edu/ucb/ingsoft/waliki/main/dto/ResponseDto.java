package bo.edu.ucb.ingsoft.waliki.main.dto;

public class ResponseDto {
    private boolean status;
    private Object response;
    private String message;

    public ResponseDto(boolean status, Object response, String message) {
        this.status = status;
        this.response = response;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }


    public Object getResponse() {
        return response;
    }

    public String getMessage() {
        return message;
    }

}