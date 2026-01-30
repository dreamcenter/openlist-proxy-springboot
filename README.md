# OpenList-Proxy [SpringBoot Version]

## Config
You can config the application.yml, set the token and address.
```yml
server:
  port: 5244
proxy:
  token: openlist-2590f023-c612-***
  address: https://domain.com
```

## What proxy path should I set in openlist?

You should set the proxy path to:

```text
https://your-proxy-address/endpoint
```

Don't forget to add `/endpoint` at the end.

Make sure your proxy address has a valid SSL certificate,
otherwise, the download will be blocked by the browser.

## How to download file?
You can use idm or aria2 to download. 
Just copy the download url from openlist and then paste it to idm or aria2.

If you have ssl certificate, you can use download directly.

## How to run the application?
Use command to run the application. (You need java17+ environment on the machine)
```shell
java -jar openlist-proxy-version.jar -Xms50m -Xmx50m
```