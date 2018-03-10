## Laboratorio 1 : Creación de un wrapper sobre el API de Streaming de Twitter

La versión de partida de este repositorio es REST. 
Se puede ver desplegada en [https://sleepy-reaches-22911.herokuapp.com/](https://sleepy-reaches-22911.herokuapp.com/).

### Preparación

* Finalizar [la práctica 0](https://github.com/UNIZAR-62227-TMDAD/lab0-twitter-rest) (o estar en ello)
* Entender cómo funciona el mecanismo que ofrece Twitter para [filtrar mensajes en tiempo real](https://developer.twitter.com/en/docs/tweets/filter-realtime/overview).
* Entender cómo Spring Social Twitter 1.1.0 da [soporte](https://docs.spring.io/spring-social-twitter/docs/1.1.0.RELEASE/reference/htmlsingle/).
* Investigar un poco el protocolo [WebSocket](https://www.websocket.org/) y cómo se usa el protocolo [STOMP](http://jmesnil.net/stomp-websocket/doc/) para el intercambio de mensajes embebido en *Websocket*.
* Fijarse en cómo Spring [da soporte en el lado del servidor](https://spring.io/guides/gs/messaging-stomp-websocket/) a *STOMP sobre Websocket* con una solución que espera el uso de [SockJS](https://github.com/sockjs) y [Stomp.js](https://www.npmjs.com/package/@stomp/stompjs) en el lado del cliente. 

### Objetivos

* Funcionamiento en el lado del servidor:
	* Configurar un broker de mensajes que soporte *STOMP sobre WebSocket*.
	* Reescribir con los menores cambios posibles el método *SearchController.search* para recibir mensajes desde el cliente solicitando una búsqueda.
	* Reescribir *TwitterLookupService* para que las operaciones de streaming que ofrece *TwitterTemplate* (máximo 10 streams abiertos, solución concurrente). 
* Funcionamiento en el lado del cliente:
   * Conectarse con *SockJS* al endpoint *Websocket*.
   * Crear un cliente *STOMP* sobre dicha conexión. 
   * Las búsquedas se envían usando el cliente *STOMP*, suscribiendose al canal de resultados.
   * Una nueva búsqueda cancela la suscripción anterior.

