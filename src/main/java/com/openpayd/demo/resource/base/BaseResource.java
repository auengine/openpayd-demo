package com.openpayd.demo.resource.base;

import com.openpayd.demo.config.constants.ApiErrors;
import com.openpayd.demo.exception.base.RuntimeExceptionBase;
import com.openpayd.demo.model.dto.output.ExceptionDTO;
import org.springframework.util.StringUtils;

import javax.swing.text.html.Option;
import javax.ws.rs.core.Response;
import java.util.Optional;

public abstract class  BaseResource {



    private Optional<ApiErrors>  getApiErrors(Optional<Exception> e) {
        if(e.isPresent() && e.get() instanceof RuntimeExceptionBase) {
            return  ((RuntimeExceptionBase) e.get()).getApiErrors();
        }
        return  Optional.of(ApiErrors.GENERIC_ERROR);
    }

    protected Response error(Response.Status status, Exception e) {
        return error(status, Optional.ofNullable(null),Optional.of(e));
    }

    protected Response error(Response.Status status, Optional<ApiErrors> apiErrors,Optional<Exception> e) {
        ExceptionDTO exceptionDto = new ExceptionDTO();

        if (!apiErrors.isPresent()) {
            apiErrors = this.getApiErrors(e);
        }
        String message=apiErrors.get().getErrorMessage();
        if(apiErrors.get()==ApiErrors.GENERIC_ERROR && e.isPresent()&& !StringUtils.isEmpty(e.get().getMessage())){
            message=e.get().getMessage();
        }

        exceptionDto.setMessage(message);
        exceptionDto.setCode(apiErrors.get().getErrorCode());
        return this.status(status, exceptionDto);
    }


    protected Response status(Response.Status status, Object entity) {
        return Response.status(status).entity(entity).build();
    }

}
