package com.openpayd.demo.clientproxy;

import com.openpayd.demo.model.dto.external.RatePairDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

public interface IExternalRateResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    RatePairDTO getLatestPair(@QueryParam("base") String base, @QueryParam("symbols") String symbols);
}
