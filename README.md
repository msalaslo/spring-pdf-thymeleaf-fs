# The Spring-pdf-thymeleaf-fs Project

## PDF

This project is an example to generate PDF from HTML as template.
The test consists of PDF generation using an HTML template to which we load i18n property files and include dynamic values through an input JSON. 
The template includes a CSS style sheet, a specific font, a bar code, an image and is fragmented into footer and header. (this text is modifiable through i18n properties files)

HTML template:employee-template.html, footer.html, header.html
Image: SD-logo.png
Style: style.css
i18n: employee-template_en.properties, employee-template_es_ES.properties
font: Code39.ttf

HTML Template and dependent files are included in src/main/templates

## Author: Miguel Salas López miguel.salas@securitasdirect.es

### Relevant Articles:
- [PDF Conversions in Java](https://www.baeldung.com/pdf-conversions-java)
- [Creating PDF Files in Java](https://www.baeldung.com/java-pdf-creation)
- [Generating PDF Files Using Thymeleaf](https://www.baeldung.com/thymeleaf-generate-pdf)

## Technical Stack:

- Java 11+
- Maven 3.6+
- Spring boot 2.2.5.RELEASE
    - Spring Boot Actuator for exposing management endopoints
- Spring cloud Hoxton.SR3
    - Spring Cloud Sleuth for distributed tracing
- Lombok abstraction
- Mapstruct for bean mapping
- Open API documentation (available at /swagger-ui.html)
- REST API model validation 
- Cucumber and Spring Boot test for test
- Logback for logging
    - Log patterns for local and cloud SPRING profiles includes Sleuth headers

## Installation
This application is configured with two SPRING profiles:
- "local": For local dev environments.
- "cloud": For dockerized environments, where application properties are set by ENV variables.

Test on the browser via SWAGGER
-------------------

```sh
http://localhost:8080/swagger-ui.html
```

```sh
To generate a PDF
URL: http://localhost:8080/employees?lang=en&templateName=employee-template
HTTP METHOD: POST
REQUEST BODY
    {
        "firstName": "Name",
        "lastName": "LastName",
        "street": "Priegola 12",
        "zipCode": 28925,
        "city": "Madrid"
    }
RESPONSE:
	content-type: application/pdf
	context-length: size in bytes of the generated file
```
