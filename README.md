# pokedex_REST
This is a simple REST API for Pokedex using [![Spring][Spring.js]][Spring-url]
All pokemon data taken from https://pokeapi.co

# REST API

## Get list of pokemon names

### Request

`GET /pokemons/`

  'curl -i http://localhost:8080/pokemons'
  
### Response

  'HTTP/1.1 200
  Vary: Origin
  Vary: Access-Control-Request-Method
  Vary: Access-Control-Request-Headers
  Content-Type: application/json
  Transfer-Encoding: chunked
  Date: Mon, 03 Apr 2023 10:56:58 GMT
  
  [list of pokemon names]'
  
## Get pokemon details

### Request

`GET /pokemon/{name}`

  'curl -i http://localhost:8080/pokemon/pikachu'
  
### Response
  
'HTTP/1.1 200
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 03 Apr 2023 10:59:01 GMT

{"name":"pikachu","spriteUrl":"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/25.png","types":["electric"],"stats":{"specialDefense":50,"defense":40,"attack":55,"hp":35,"specialAttack":50,"speed":90}}'

[Spring.js]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/
