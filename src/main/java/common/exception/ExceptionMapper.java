package common.exception;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class ExceptionMapper {

    public record ErrorResponse(String error, int status) {}

    @ServerExceptionMapper
    public RestResponse<ErrorResponse> mapBadRequest(BadRequestException exception) {
        return RestResponse.status(
                Response.Status.BAD_REQUEST,
                new ErrorResponse(exception.getMessage(), 400)
        );
    }

    @ServerExceptionMapper
    public RestResponse<ErrorResponse> mapIllegalState(IllegalStateException ex) {
        return RestResponse.status(
                Response.Status.INTERNAL_SERVER_ERROR,
                new ErrorResponse(ex.getMessage(), 500)
        );
    }
}
