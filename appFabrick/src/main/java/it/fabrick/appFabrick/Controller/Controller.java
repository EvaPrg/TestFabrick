package it.fabrick.appFabrick.Controller;


import it.fabrick.appFabrick.Model.Conto;
import it.fabrick.appFabrick.Repository.ContoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transaction;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{accountId}/balance")
    public Balance getBalance(@PathVariable("accountId") String accountId ){

        String url = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/balance";
        url = url.replace("{accountId}", accountId);

        HttpEntity<String> entity = new HttpEntity<>("body", getHeaders());
        ResponseEntity<BalanceResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, BalanceResponse.class);

        return response.getBody().getPayload();
    }

    @GetMapping("/{accountId}/transactions")
    public List<Transaction> getTransactions(@PathVariable("accountId") String accountId , @RequestParam("fromAccountingDate") String fromAccountingDate, @RequestParam("toAccountingDate") String toAccountingDate) {

        String url = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts?accountId={accountId}&fromAccountingDate={fromAccountingDate}&toAccountingDate={toAccountingDate}";
        url = url.replace("{accountId}", accountId);
        url = url.replace("{fromAccountingDate}", fromAccountingDate);
        url = url.replace("{toAccountingDate}", toAccountingDate);

        HttpEntity<String> entity = new HttpEntity<>("body", getHeaders());
        ResponseEntity<TransactionsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, TransactionsResponse.class);
        List<Transaction> transactions = response.getBody().getPayload().getList();

        return response.getBody().getPayload().getList();
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        return headers;
    }

}