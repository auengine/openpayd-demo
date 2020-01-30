package com.openpayd.demo.resource;

import com.openpayd.demo.config.constants.ApiErrors;
import com.openpayd.demo.exception.RatePairNotFound;
import com.openpayd.demo.resource.base.BaseResource;
import com.openpayd.demo.service.IRateService;
import com.openpayd.demo.mapper.IRateMapper;
import com.openpayd.demo.model.dto.external.RatePairDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Optional;


@Validated
@RestController
@Path("/api/rates")
@Slf4j
public class ExchangeRateResource extends BaseResource {

  @Autowired
  private IRateService rateService;

  @Autowired
  IRateMapper rateMapper;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{base}/{symbol}")
  public Response getCurrencyRate(@PathParam("base") @Size(max = 3,min = 2) String base,
                                   @PathParam("symbol")  @Size(max = 3,min = 2) String symbol) {
    try {
      RatePairDTO result= rateService.getLatestPair( Optional.of(base), Optional.of(symbol));
      return Response.ok( rateMapper.rateToRateDto(result)).build();

    }catch (RatePairNotFound e ) {
        e.setApiErrors(ApiErrors.INVALID_REQUEST_PARAMETERS);
        return error(Response.Status.BAD_REQUEST, e);
    }catch (Exception e ){
        log.error(ApiErrors.GENERIC_ERROR.getErrorMessage(),e);
        return error(Response.Status.INTERNAL_SERVER_ERROR, e);
    }



  }
}
