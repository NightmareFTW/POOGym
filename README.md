# POOGym — Fase 1

Aplicação de consola em Java para gerir sócios, instrutores, actividades e
marcações de um ginásio. Esta versão implementa as funcionalidades da Fase 1
do enunciado e usa classes, encapsulamento, herança simples, métodos e
colecções, sem polimorfismo, classes abstractas ou ficheiros.

## Executar

Com o JDK instalado, na pasta do projecto:

```powershell
javac -encoding UTF-8 -d out src\*.java
java -cp out Main
```

Ao iniciar, são carregados seis sócios, três instrutores, oito actividades e
seis marcações de demonstração. Os novos registos existem apenas enquanto a
aplicação está em execução.

## Funcionalidades

1. Registar sócios individuais e empresa.
2. Registar instrutores.
3. Registar aulas de grupo e sessões de personal training, com os custos do
   enunciado.
4. Listar sócios e os seus dados.
5. Listar actividades e os seus dados.
6. Criar marcações, aplicando o desconto e verificando a capacidade e
   duplicados.
7. Listar marcações, custos, poupanças e total facturado.
8. Resumir os tipos de sócios e actividades.
9. Identificar a actividade mais popular.
10. Calcular o total gasto e poupado por um sócio.

O desconto individual usa o ano corrente do computador: 10% para menores de
18 anos e 15% a partir dos 65 anos. Os restantes sócios individuais não têm
desconto; os sócios empresa têm 20%.

## Diagrama de classes

O ficheiro [diagrama-classes.puml](diagrama-classes.puml) contém o diagrama em
formato PlantUML e pode ser exportado para PDF com uma ferramenta PlantUML.

## Organização

- `Socio`, `SocioIndividual` e `SocioEmpresa`: dados e descontos dos sócios.
- `Instrutor`: dados dos instrutores.
- `Atividade`, `AulaGrupo` e `SessaoPersonalTraining`: actividades e custos.
- `Marcacao`: associação entre um sócio e uma actividade.
- `POOGym`: colecções e operações sobre os registos.
- `Main`: menu e interacção com o utilizador.

Esta entrega não inclui os requisitos da Fase 2 (persistência em ficheiros,
ordenação, faturação por categoria, mais poupado, polimorfismo ou classes
abstractas), que dependem de matéria posterior.
