## CrudClient

Crud de cliente usando Java - Spring Boot com banco de dados em memória (H2).

> Seeding do BD.

> Tratamento de erros, ultilizando a resposta HTTP correta!

### Entidade - Cliente
![Cliente](https://github.com/ophsr/crudClient/blob/master/ReadMeAssets/ClientClass.png)

### Metodos com requisição GET: 
**FindAll (Paginado e Parametrizado):** 
- Exemplo de requisição:```/clients?page=0&linesPerPage=12&direction=ASC&orderBy=name```
  
**FindById:**
- Exemplo de requisição: ```/clients/1```
  
### Metodos com requisição POST: 
**Insert**
- Exemplo de requisição:```/clients```
- Exemplo do Body:
```
{
  "name": "Maria Silva",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20T10:30:00Z",
  "children": 2
}
```

### Metodos com requisição PUT: 
**Update**
- Exemplo de requisição:```/clients/1```
- Exemplo do Body:
```
{
  "name": "Maria Silva",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20T10:30:00Z",
  "children": 2
}
```

### Metodos com requisição DELETE
**Delete**
- Exemplo de requisição: ```/clients/1```

