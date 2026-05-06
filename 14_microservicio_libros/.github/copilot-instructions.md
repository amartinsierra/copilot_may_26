Cuando te pidan crear componentes de un microservicio,
deberás tener en cuenta las siguientes directrices:
#arquitectura
- Inyección por constructor
- DTOs implementados con records
- Separación entidades de DTOs
- Mapeado entre entidades y DTO con Mapstruct
#service
- Separar interfaz y clase de implementación
- La clase siempre anotada con @Service
- Los tipos de devolución DTO y tamibien los de entrada en caso de altas y modificaciones
- En la implementación se interacciona con repository y se mapea entidad/dto
#repository
- Utiliza Spring Data JPA
- Intenta utilizar Query keywords para nombres de métodos
- En caso de usar query, emplea JPQL
#controller
- Utiliza los convenios habituales para crear un API REST
- Los endpoint siempre devolverán ResponseEntity con código de estado adecuado en cada caso 
#general
- Cuando te hagan referencia al uso de skill, debes tener en cuenta que se encuentran
en alguna subcarpeta dentro de la carpeta .github/skills del proyecto