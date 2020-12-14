# dasa-medical-request
Este projeto trata-se de cadastro do pedido médico para solicitação de exames. Foram utilizadas as informações do médico, paciente(seus respectivos documentos e endereço)

# PASSOS PARA RODAR O PROJETO NA MÁQUINA
1 - Baixar este projeto na sua máquina local(assumindo que tenhas todo ambiente configurado - java 11, IDE da sua preferência, Maven, banco de Dados Mysql e docker instalado)
2 - Para rodar o projeto, foi customizado a porta http://localhost:8001 para evitar conflito com outras coisas que talvez estejam usando a porta padrão :8080.
  2.1 - Pode rodar o mesmo projeto via swagger para ver toda documentação todos as descrições de cada end-point: http://localhost:8001-swagger-ui.html
3 - Existem 3 tabelas relacionadas entre si, mas de uma forma independente, pode fazer o CRUD do mêdico e paciente diretamente sem que prejudique a sua relação para com o pedido(Delete e Update para dados de pacientes e médicos que não foram cadastrados via pedido, caso contrário, seria necessário uma exclusão via pedido primeiro).
  3.1 - Os Endpoins foram agrupados via tags(Médico, Paciente e Pedido)
4 - Todos os id's são de preenchimento opcional na hora de criação, pois é gerado automaticamente e é usado CRM do Médico e Documentos do Paciente para evitar duplicidade dos dados na base.
  4.1 Se não usar id do médico ou paciente, será usado o id que acabou de ser gerado quando aquele médico ou paciente informado no payload do pedido for cadastrado, caso contrário, ele recupera o médico via crm e paciente via seus documentos para fazer chavemanto.
  
 # PARA GERAR A IMAGEM DOCKER É SEGUIR SEGUINTES PASSOS:
 1 - mvn package
      O comando mvn package cria um novo arquivo .jar dentro da pasta target que será usado para a imagem docker.
      Após executar o comando, já podes consultar em nossas imagens docker local se a imagem do nosso projeto foi criada.
      Execute o seguinte comando para listar as imagens docker disponíveis em seu sistema.
      
 2 - docker images
  Para testar o projeto com imagem é digitar o seguinte comando
 
 3 - docker run -p 8080:8001 {seu_docker_id}/medical-request:0.0.1-SNAPSHOT
 
  
 # Observação
 Queria colocar a autenticação do usuário via spring security jwt, criar teste Unitários, mas tive que fazer este projeto no domingo devido o motivo explicado ao Mazzatech.
Isto é tudo que consegui resolver agora.
