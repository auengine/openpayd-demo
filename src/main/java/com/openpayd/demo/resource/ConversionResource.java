package com.openpayd.demo.resource;

import com.openpayd.demo.config.constants.ApiErrors;
import com.openpayd.demo.exception.ConversionNotFound;
import com.openpayd.demo.exception.IllegalInputParameter;
import com.openpayd.demo.exception.RatePairNotFound;
import com.openpayd.demo.mapper.IConversionMapper;
import com.openpayd.demo.model.Conversion;
import com.openpayd.demo.model.dto.input.ConversionInputDTO;
import com.openpayd.demo.model.dto.output.ConversionOutputPageDTO;
import com.openpayd.demo.resource.base.BaseResource;
import com.openpayd.demo.service.IConversionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;


@RestController
@Path("/api/conversion")
@Slf4j
public class ConversionResource   extends BaseResource {

    @Autowired
    private IConversionService conversionService;

    @Autowired
    IConversionMapper conversionMapper;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/convert")
    public Response convert(ConversionInputDTO input) {
        try {
            Conversion result = conversionService.calculate(input);
            return Response.ok(conversionMapper.convert(result)).build();

        } catch (RatePairNotFound e) {
            e.setApiErrors(ApiErrors.INVALID_REQUEST_PARAMETERS);
            return error(Response.Status.BAD_REQUEST, e);
        } catch (Exception e) {
            log.error(ApiErrors.GENERIC_ERROR.getErrorMessage(), e);
            return error(Response.Status.INTERNAL_SERVER_ERROR, e);
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response conversionList(
            @DefaultValue("0") @QueryParam("page") int page, @DefaultValue("10") @QueryParam("size") int pageSize,
            @QueryParam("transactionId") String transactionId, @QueryParam("date") String date) {
        try {
            List<Conversion> result = conversionService.listConversions(Optional.ofNullable(transactionId), Optional.ofNullable(date), page, pageSize);
            ConversionOutputPageDTO pagedOutput=new ConversionOutputPageDTO();
            pagedOutput.setItems(conversionMapper.convert(result));
            pagedOutput.setPage(page);
            pagedOutput.setPageSize(pageSize);
            return Response.ok(pagedOutput).build();

            //more filexible then Controller Advice
        } catch (ConversionNotFound e ) {
            e.setApiErrors(ApiErrors.INVALID_TRANSACTION_ID_OR_DATE);
            return error(Response.Status.BAD_REQUEST, e);
        }catch (IllegalInputParameter e ) {
            e.setApiErrors(ApiErrors.INVALID_REQUEST_PARAMETERS);
            return error(Response.Status.BAD_REQUEST, e);
        }catch (DateTimeParseException e) {
            log.error(ApiErrors.INVALID_DATE_FROMAT.getErrorMessage(), e);
            return error(Response.Status.BAD_REQUEST, e);        }
        catch (Exception e) {
            log.error(ApiErrors.GENERIC_ERROR.getErrorMessage(), e);
            return error(Response.Status.INTERNAL_SERVER_ERROR, e);
        }
    }

}