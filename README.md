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

The default port is 5244, you can change it in application.yml.

## How to download file?
You can use idm or aria2 to download. Just copy the url from openlist and then paste it to idm or aria2.

## How to run the application?
Use command to run the application. (You need java17+ environment on the machine)
```shell
java -jar openlist-proxy-version.jar -Xms50m -Xmx50m
```