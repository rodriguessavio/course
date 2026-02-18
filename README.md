# 📘 Instruções do Projeto

## Estrutura, regras e requisitos da API

A API deve conter as seguintes funcionalidades:

- Criação de um novo curso  
- Listagem de todos os cursos  
- Atualização de um curso pelo id  
- Remover um curso pelo id  

---

## 🧱 Estrutura do Curso

Antes das rotas, vamos entender as propriedades que um curso deve possuir:

| Campo | Descrição |
|------|-----------|
| **id** | Identificador único de cada curso |
| **name** | Nome do curso |
| **category** | Categoria do curso |
| **active** | Define se o curso está ativo ou não |
| **created_at** | Data de criação do curso |
| **updated_at** | Data da última atualização do curso |

---

## 🚦 Rotas

### 🔹 POST `/cursos`
Deve ser possível criar um curso no banco de dados enviando os campos:

- name  
- category  
- professor  

via **body** da requisição.

Campos preenchidos automaticamente:
- id
- created_at
- updated_at

---

### 🔹 GET `/cursos`
Deve ser possível listar todos os cursos salvos no banco de dados.

Também deve ser possível filtrar cursos pelos campos:

- name  
- category  

---

### 🔹 PUT `/cursos/:id`
Deve ser possível atualizar um curso pelo **id**.

No body da requisição, deve ser enviado apenas um dos campos:

- name  
- category  
- professor  

Se for enviado apenas um campo, os demais não devem ser alterados.

⚠️ Caso o campo **active** seja enviado, ele deve ser ignorado nesta rota, pois a atualização dele deve ser feita exclusivamente pela rota PATCH.

---

### 🔹 DELETE `/cursos/:id`
Deve ser possível remover um curso pelo **id**.

---

### 🔹 PATCH `/cursos/:id/active`
Essa rota serve para alternar o status do curso entre:

- `true`
- `false`

---

## 💡 Dicas

- `@CreationTimestamp` define automaticamente o valor de **created_at** no momento da criação da entidade.
- `@UpdateTimestamp` define automaticamente o valor de **updated_at** no momento da atualização da entidade.

---

## 🚀 Indo Além (Desafios Extras)

Sugestões de melhorias:

- Validar se **name**, **category** e **professor** estão presentes no body da rota POST.
- Criar **exceptions customizadas** para tratamento de erros.
- Implementar um handler global de exceções.
- Adicionar mensagens de erro padronizadas.

---
