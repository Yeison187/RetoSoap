Feature: Full country info
  Como usuario de DataFlex
  necesito consultar con el código ISO del pais
  para poder obtener la informacion completa de un pais.

  Scenario: Código ISO valido
    Given que el usuario necesita consultar la información completa de un pais, con el código ISO CO
    When el usuario de ejecuta una consulta
    Then el ususario debería obtener toda la informacion del pais

  Scenario: Código ISO invalido
    Given que el usuario ingresa un código ISO no valido FALSE
    When el usuario de ejecuta una consulta
    Then el ususario debería obtener como respuesta: Country not found in the database

