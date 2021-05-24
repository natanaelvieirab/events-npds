
# Events - API
Events é um projeto proposto por [Igor Claudino](https://github.com/igorclaudino) com o intuito de desmostrar na prática o uso do Spring boot.

## Sobre o projeto

O projeto constitui em um sistema no qual pode ser criado eventos e suas respectivas programações.
O projote é constituido de duas partes:

 * O frontend foi desenvolvido em React , o seu código pode ser encontrado aqui: [events-frontend](https://github.com/natanaelvieirab/events-npds-front). 
 * O backend foi desenvolvido em [Spring boot](https://start.spring.io/).
	
## Diagrama 
O projeto é constituido da seguinte forma:

![](./imgs/Events_NPDS.png)

## Clonando o projeto
Para clonar o projeto e importa-lo no Eclipse (IDE), faça:
 * Clone o projeto em sua maquina;
 * No Eclipse, va em **File > Import > Maven > Existing Maven Projects **;
 * Clique em **Browse**, navegue até onde a pasta onde foi clonado o projeto e clique em **Abrir**;
 * Clique em **Finish** para finalizar a importação;
 
## Execultando o projeto
Para execultar o projeto procure pelo arquivo `EventsNpdsApplication.java`, que se encontra na pasta/pacote `br.ufc.crateus.events` e clique em `Run`.

A API estara rodando no endereço `http://localhost:8080`.

## Acessando API

Você deve usar as seguintes rotas para acessar a API:

### Authenticate
Para acessar as rotas é necessário ter um **token**, para consegui-lo, acesse a rota `/api/authenticate` com o seguindo body:

```
{
	"username":"admin",
	"password": "admin"
}
```

Será retornado o seguinte valor: 

```
{
	"token":"<token>"
}
```
Sendo `<token>`, o token gerado.

### Events

#### POST
Para criar um novo evento acesse a rota `/api/events` com os seguintes valores no **body**:

```
{
	 "name": "Treinamento para o Estágio",
	 "description": "Treinamento para nivelar os conhecimentos sobre as tecnologias usadas no estágio",
	 "organizer": "NPDS",
	 "localization": "Virtual, via Discord",
    "beginDate": "2021-05-17",
	 "endDate": "2021-05-29"
}
```

#### GET
Para visualizar os eventos cadastrados acesse a rota `/api/events?pageNumber=0&pageSize=4`, no qual:
 * **pageNumber** define o número página que será exibida;
 * **pageSize** define o quantidade de registros que será exibida por página.
 
 **ATENÇÃO**: estes valores só podem ser números.
 
 Será retornado um array com os eventos, no qual eles seguem este padrão:

``` 
[
	{
	  "id": 1,
	  "name": "Treinamento para o Estágio",
	  "description": "Treinamento para nivelar os conhecimentos sobre as tecnologias usadas no estágio",
	  "organizer": "NPDS",
	  "localization": "Virtual, via Discord",
	  "beginDate": 1621209600000,
	  "endDate": 1621555200000
	}
]
```
**OBS.**: exemplo da estrutura de um evento que será retornada.

#### GET:id
Para visualizar um determinado evento acesse a rota `/api/events/:id`. Sendo `:id` o número de identificação corresponde ao evento.

Exemplo de retorno: 

``` 
{
	  "id": 1,
	  "name": "Treinamento para o Estágio",
	  "description": "Treinamento para nivelar os conhecimentos sobre as tecnologias usadas no estágio",
	  "localization": "Virtual, via Discord",
	  "beginDate": 1621209600000,
	  "endDate": 1621555200000
}
```

#### GET > name
Para buscar um determinado evento evento acesse a rota `/api/events/?name=treinamento&pageNumber=0&pageSize=10`. 
 * **name** onde será definido o texto que será buscado;
 * **pageNumber** define o número página que será exibida;
 * **pageSize** define o quantidade de registros que será exibida por página.

Exemplo de retorno, quando buscado por "treinamento": 

``` 
[
	{
		  "id": 1,
		  "name": "Treinamento para o Estágio",
		  "description": "Treinamento para nivelar os conhecimentos sobre as tecnologias usadas no estágio",
		  "localization": "Virtual, via Discord",
		  "beginDate": 1621209600000,
		  "endDate": 1621555200000
	}
]
```

**Atenção** : a busca é realizada nas propriedades : name, description e organizer; 

### Schedules

#### POST
Para criar uma nova programação para um evento acesse a rota `/api/events/:id/schedule` com os seguintes valores no **body**:

```
{	
	"name":"Spring boot",
	"description": "Nivelando conhecimentos do Spring boot",
	"presenter":"Igor Claudino",
	"localization":"Remoto via Discord",
	"beginDate":"2021-05-20",
	"endDate":"2021-05-21",
	"beginTime":"08:00",
	"endTime":"11:00",
	"workload":3
}
```

Sendo `:id` o número de identificação do evento, no qual a programação pertence.

#### GET
Para visualizar os eventos cadastrados acesse a rota `http://localhost:8080/api/events/:id/schedule?pageNumber=1&pageSize=4`, no qual:
 * **:id** o número de identificação do evento, no qual a programação pertence.
 * **pageNumber** define o número página que será exibida;
 * **pageSize** define o quantidade de registros que será exibida por página.
 
 ```
 [
	  {
	    "id": 1,
	    "name": "Spring boot",
	    "description": "nivelando conhecimentos do Spring boot",
	 	"presenter":"Igor Claudino",
	 	"localization":"Remoto via Discord",
	    "beginDate": 1621209600000,
	    "endDate": 1621555200000,
	    "beginTime": "08:00",
	    "endTime": "11:00",
	    "workload": 3,
	    "event": {
  	         "id": 1,
	  	  	  "name": "Treinamento para o Estágio",
	  	   	  "description": "Treinamento para nivelar os conhecimentos sobre as tecnologias usadas no estágio",
	  	   	  "localization": "Virtual, via Discord",
	  	   	  "beginDate": 1621209600000,
	  	   	  "endDate": 1621555200000
	    }
	  }
  ]
 ```
