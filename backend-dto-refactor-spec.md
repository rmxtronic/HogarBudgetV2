# Specification: DTO Refactoring to Fix Jackson Serialization Bug (403/500)

## 1. Contexto e Causa Raiz
**Objetivo:** Resolver a exceção disparada na serialização JSON que está retornando um falso `403 Forbidden` (ou `500 Internal Server Error`).
**Causa:** Os controllers estão retornando entidades JPA diretamente para a camada web. Quando o Jackson tenta serializar as entidades, ele tropeça no campo `@ManyToOne(fetch = FetchType.LAZY) Usuario`, tentando acessá-lo fora de um contexto transacional do banco de dados.
**Solução:** Isolar a camada de banco de dados da camada web. Os controllers e services devem sempre retornar Data Transfer Objects (DTOs), nunca as entidades JPA "cruas".

## 2. Atualização dos DTOs (Data Transfer Objects)
**Objetivo:** Garantir que todos os DTOs de resposta possuam um construtor que receba a entidade JPA e mapeie seus atributos.

* **Verificar e atualizar os seguintes DTOs:**
    * `DatosEgresoCategoria.java`
    * `DatosIngresoFijo.java`
    * `DatosIngresoVariable.java`
* **Ação Requerida:** Se não existir, adicionar um construtor que aceite a entidade correspondente.
    * *Exemplo:* `public DatosEgresoCategoria(EgresoCategoria categoria) { this.id = categoria.getId(); ... }`

## 3. Refatoração da Camada Service
**Objetivo:** Mapear as entidades JPA para DTOs antes de devolver os dados para o Controller.

* **Arquivos a modificar:**
    * `EgresoCategoriaService.java`
    * `IngresoFijoService.java`
    * `IngresoVariableService.java`
* **Ação Requerida nas operações de Leitura (GET/List):**
    * Ao realizar um `findByUsuario`, utilizar a API de Streams do Java para converter o resultado.
    * *Exemplo de implementação (Listas):*
        ```java
        return repository.findByUsuario(usuario).stream()
            .map(DatosEgresoCategoria::new)
            .toList();
        ```
    * *Exemplo de implementação (Paginação - Page):*
        ```java
        return repository.findByUsuario(usuario, pageable)
            .map(DatosIngresoFijo::new);
        ```

## 4. Refatoração da Camada Controller
**Objetivo:** Ajustar as assinaturas dos métodos para refletirem os novos tipos de retorno dos Services.

* **Arquivos a modificar:**
    * `EgresoCategoriaController.java` (Atenção às linhas 49-53 aprox.)
    * `IngresoFijoController.java` (Atenção às linhas 56-60 aprox.)
    * `IngresoVariableController.java` (Métodos de listagem GET)
* **Ação Requerida:** * Trocar o tipo de retorno genérico nas assinaturas dos métodos e no `ResponseEntity`.
    * Substituir retornos como `ResponseEntity<List<EgresoCategoria>>` por `ResponseEntity<List<DatosEgresoCategoria>>`.
    * Substituir retornos como `ResponseEntity<Page<IngresoFijo>>` por `ResponseEntity<Page<DatosIngresoFijo>>`.
