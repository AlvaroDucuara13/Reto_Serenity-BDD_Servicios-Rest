Feature: obtencion de datos
  yo como cliente registrado
  deseo consultar mis datos en la pagina
  para validar mi informacion


  Scenario: Obtener datos de usuario
    Given el usuario esta en la pagina y desea ver su informacion
    When el usuario realiza la accion para obtener su informacion
    Then el usuario lograra visualizar una lista con sus datos

  Scenario: Agregar comentarios
    Given el usuario esta en la pagina y desea anexar una serie de datos para registrar un comentario
    When el usuario realiza la accion
    Then el usuario lograra visualizar sus datos registrados y el comentario