# Specification: Backend Authentication & Data Isolation

## 1. Database Schema Updates (Priority Zero)
**Objetivo:** Preparar o banco de dados para suportar múltiplos usuários (Multi-tenancy) e garantir o isolamento de dados.

*   **Criar tabela `usuarios`:**
    *   Campos obrigatórios: `id` (Primary Key, auto-increment/UUID), `nome` (String), `email` (String, Unique), `senha` (String, hash BCrypt).
*   **Alterar tabelas existentes (`transacoes` e `categorias`):**
    *   Adicionar coluna `usuario_id`.
    *   Configurar `usuario_id` como Foreign Key referenciando `usuarios(id)`.
    *   *Nota de migração:* Definir uma estratégia para os dados existentes (ex: atribuir a um usuário "admin" padrão ou limpar o banco de testes).

## 2. Spring Security & JWT Implementation
**Objetivo:** Proteger a API e gerenciar o acesso sem estado (stateless).

*   **Configuração de Segurança:**
    *   Integrar o `Spring Security`.
    *   Configurar o `BCryptPasswordEncoder` como o bean padrão de hash de senhas. Nenhuma senha deve ser salva em texto plano.
    *   Desabilitar CSRF (apropriado para APIs REST com JWT) e configurar a política de sessão como `STATELESS`.
*   **Gerenciamento de JWT:**
    *   Implementar um `JwtService` responsável por gerar e validar tokens JSON Web Tokens.
    *   Criar um `JwtAuthenticationFilter` para interceptar requisições, extrair o token do cabeçalho `Authorization: Bearer <token>`, validá-lo e definir o contexto de segurança (SecurityContextHolder).

## 3. Authentication Endpoints
**Objetivo:** Permitir o registro e login de usuários.

*   **`POST /api/auth/register`:**
    *   Payload: `{ "nome": "...", "email": "...", "senha": "..." }`.
    *   Ação: Verificar se o email já existe. Criptografar a senha com BCrypt. Salvar o novo usuário.
    *   Retorno: Status 201 Created.
*   **`POST /api/auth/login`:**
    *   Payload: `{ "email": "...", "senha": "..." }`.
    *   Ação: Autenticar credenciais. Se válidas, gerar JWT contendo o `id` do usuário como subject/claim.
    *   Retorno: Status 200 OK com payload `{ "token": "<jwt_string>" }`.

## 4. Data Isolation (Authorization & Filtering)
**Objetivo:** Garantir que usuários acessem apenas seus próprios dados.

*   **Extração de Identidade:**
    *   O controlador ou serviço deve ser capaz de recuperar o `usuario_id` do usuário atualmente autenticado a partir do `SecurityContextHolder`.
*   **Refatoração de Repositórios/Serviços:**
    *   **Todos** os endpoints existentes (ex: `GET /api/transacoes`, `POST /api/categorias`) devem ser refatorados.
    *   Atualizar as queries SQL/JPA para incluir obrigatoriamente a cláusula `WHERE usuario_id = ?`.
    *   *Exemplo de regra:* Uma requisição para buscar transações deve compor e executar internamente algo equivalente a `SELECT * FROM transacoes WHERE usuario_id = :id_do_jwt`.
