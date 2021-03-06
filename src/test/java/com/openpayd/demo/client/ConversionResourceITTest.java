package com.openpayd.demo.client;

import com.openpayd.demo.model.dto.input.ConversionInputDTO;
import com.openpayd.demo.model.dto.output.ConversionOutputDTO;
import com.openpayd.demo.model.dto.output.ConversionOutputPageDTO;
import com.openpayd.demo.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConversionResourceITTest {

    private static final String postUrl = "/api/conversion/convert";
    private static final String listUrl = "/api/conversion/list";
    private final double amount = 123.1;
    private final String base = "USD";
    private final String symbol = "EUR";

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void shouldConvert() throws Exception {
        //Given
        ConversionInputDTO conversionInputDTO = sampleInput();
        ResponseEntity<ConversionOutputDTO> result = postItem(conversionInputDTO, postUrl);
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatusCodeValue());
        assertNotNull(result.getBody().transactionId);
    }


    @Test
    public void shouldConversionListByTransactionId() throws Exception {
        //Given
        ConversionInputDTO conversionInputDTO = sampleInput();
        ResponseEntity<ConversionOutputDTO> result = postItem(conversionInputDTO, postUrl);
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatusCodeValue());
        UriComponentsBuilder url = urlBuilder(listUrl, Optional.ofNullable(null), Optional.ofNullable(null), Optional.ofNullable(result.getBody().transactionId), Optional.ofNullable(null));


        ResponseEntity<ConversionOutputPageDTO> listResult = listItems(url.build().toUriString());
        assertEquals(Response.Status.OK.getStatusCode(), listResult.getStatusCodeValue());
        assertThat(listResult.getBody().getItems()).hasSize(1);
        assertEquals(listResult.getBody().getItems().get(0).transactionId, result.getBody().transactionId);


    }


    @Test
    public void shouldConversionListByDate() throws Exception {
        //Given
        int pageCount = 3;
        int pageSize = 5;
        int queryPage = 1;
        List<ConversionOutputDTO> insertedItems = sendItems(pageSize * pageCount);
        UriComponentsBuilder url = urlBuilder(listUrl, Optional.of(queryPage), Optional.of(pageSize),
                Optional.ofNullable(null), Optional.of(DateUtils.today_yyyyDASHMMDASHdd()));


        ResponseEntity<ConversionOutputPageDTO> listResult = listItems(url.build().toUriString());
        assertEquals(Response.Status.OK.getStatusCode(), listResult.getStatusCodeValue());
        assertThat(listResult.getBody().getItems()).hasSize(pageSize);
        assertEquals(listResult.getBody().page,queryPage);
    }


    public List<ConversionOutputDTO> sendItems(int size) {
        ConversionInputDTO conversionInputDTO = sampleInput();
        List<ConversionOutputDTO> insertedItems = new ArrayList<ConversionOutputDTO>();
        for (int i = 0; i < size; i++) {
            ResponseEntity<ConversionOutputDTO> result = postItem(conversionInputDTO, postUrl);
            assertEquals(Response.Status.OK.getStatusCode(), result.getStatusCodeValue());
            insertedItems.add(result.getBody());
        }
        return insertedItems;
    }

    private UriComponentsBuilder urlBuilder(String url,
                                            Optional<Integer> page,
                                            Optional<Integer> size,
                                            Optional<String> transactionId,
                                            Optional<String> date
    ) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(url);
        if (page.isPresent()) {
            builder.queryParam("page", page.get());
        }
        if (size.isPresent()) {
            builder.queryParam("size", size.get());
        }
        if (transactionId.isPresent()) {
            builder.queryParam("transactionId", transactionId.get());
        }
        if (date.isPresent()) {
            builder.queryParam("date", date.get());
        }
        return builder;
    }


    public ResponseEntity<ConversionOutputDTO> postItem(ConversionInputDTO conversionInputDTO, String url) {
        HttpEntity<ConversionInputDTO> entity = new HttpEntity<>(conversionInputDTO, null);
        ResponseEntity<ConversionOutputDTO> result = restTemplate.
                exchange(url, HttpMethod.POST, entity, ConversionOutputDTO.class);
        return result;
    }


    public ResponseEntity<ConversionOutputPageDTO> listItems(String url) {
        ResponseEntity<ConversionOutputPageDTO> result = restTemplate.
                exchange(url, HttpMethod.GET, null, ConversionOutputPageDTO.class);
        return result;
    }

    public ConversionInputDTO sampleInput() {
        //Given
        ConversionInputDTO conversionInputDTO = new ConversionInputDTO();
        conversionInputDTO.setAmount(amount);
        conversionInputDTO.setBase(base);
        conversionInputDTO.setSymbol(symbol);
        return conversionInputDTO;
    }


}
