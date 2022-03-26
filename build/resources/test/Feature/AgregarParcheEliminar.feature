Feature: Actualizar y eliminar datos
  yo como cliente registrado
  deseo Acrtualizar y eliminar datos en la pagina
  para validar el funcionamiento de los servicios


  Scenario: Actualizar Datos
    Given el usuario esta en la plataforma y desea actualizar datos
    When el usuario logra actualizar los datos
    Then el usuario obtendra un codigo de respuesta exitosa y podra ver sus datos actualizados

  Scenario: Eliminar Datos
    Given el usuario esta en la plataforma y desea eliminar datos
    When el usuario logra eliminar los datos
    Then el usuario obtendra un codigo de respuesta