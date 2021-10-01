# exchange-api

The aim of this project is to develop a simple foreign exchange backend application. 

This application provides following functionalities:

It returns exchange rates for currency pairs with a GET call on:

```sh
/exchange-api/exchangeRates?currencyPair=EURUSD
```

It saves exchange transactions with a POST call on:

```sh
/exchange-api/exchanges
```

It returns an exhange transaction with an ID with a GET call on:

```sh
/exchange-api/exchanges/{id}
```

Finally, you can query exchage transaction by using conversion date with a GET call on:

```sh
/exchange-api/exchanges?conversionDate=2021-09-30
```

In case of an error, HTTP Status Codes 400 and 401 are used.

This application is using a H2 database.

Spring Framework and Fixer API are used.

