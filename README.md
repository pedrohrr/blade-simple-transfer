# blade-simple-transfer

REST api builded upon [Blade|https://github.com/lets-blade/blade] which simulates transfers between accounts from the same Bank with different currencies.

## Libraries | API
* [Blade](https://github.com/lets-blade/blade): MVC framework
* [Anima](https://github.com/biezhi/anima): ORM
* [Excahnge Rates](https://exchangeratesapi.io/): Exchange Rates API

## Give it a try

### Execute with
```bash
mvn clean package -DskipTests
java -jar simple-transfer.jar --server.port=9000
```

### API Schema
```
http://localhost:9000/
```
The schema can be loaded using [Swagger Editor](https://swagger.io/tools/swagger-editor/).
Both [json](https://github.com/pedrohrr/blade-simple-transfer/src/resource/main/resouces/templates/schema.json) and [yaml](https://github.com/pedrohrr/blade-simple-transfer/src/resource/main/resouces/templates/schema.yaml) schemas can be found in 'resources/templates'.
