
````markdown
# 💸 MyFinance

> Plataforma Inteligente para Gerenciamento Financeiro Pessoal

MyFinance é uma aplicação web desenvolvida como Trabalho de Conclusão de Curso (TCC) com o objetivo de auxiliar usuários na gestão de suas finanças pessoais por meio de recursos de inteligência artificial e da aplicação do modelo financeiro 50/30/20. A plataforma foca na privacidade do usuário, não exigindo conexão com contas bancárias, e oferece recomendações personalizadas para o planejamento financeiro.

---

## 🚀 Tecnologias Utilizadas

- **Frontend**: [React](https://reactjs.org/) (JavaScript)
- **Backend**: [Spring Boot](https://spring.io/projects/spring-boot) (Java)
- **Banco de Dados**: [PostgreSQL](https://www.postgresql.org/)
- **IA/API**: Python + Flask + Scikit-learn + Pandas
- **Visualização de Dados**: [Google Charts](https://developers.google.com/chart)

---

## 🧠 Inteligência Artificial

A plataforma utiliza três modelos principais com base em **Random Forest**:

1. **Análise de Risco** – Classifica padrões de gasto como saudáveis ou problemáticos.
2. **Redistribuição Geral** – Sugere proporções ideais de gastos com base na renda.
3. **Alocação de Subcategorias** – Define valores ideais para subcategorias dentro do modelo 50/30/20.

Além disso, a funcionalidade **Economia Planejada** permite definir metas específicas de poupança que influenciam a redistribuição orçamentária.

---

## 📊 Funcionalidades

- ✅ Cadastro e categorização de transações financeiras
- 📈 Relatórios dinâmicos com gráficos interativos (pizza e barras)
- 🧮 Geração de planos financeiros com IA
- 🔄 Recomendações automáticas para equilíbrio financeiro
- 📉 Identificação de riscos orçamentários
- 🎯 Ajustes conforme metas de economia planejada

---

## 🖥️ Interface

A aplicação possui três páginas principais:

- **Relatórios**: Painel interativo com gráficos de distribuição de gastos.
- **Lançamentos**: Registro e edição de transações (CRUD completo).
- **Planos**: Geração de planos financeiros personalizados com base no modelo 50/30/20 e uso da IA.

---

## 🏗️ Arquitetura

- Backend e frontend comunicam-se via APIs REST.
- IA está desacoplada em um microserviço com Flask, mantendo modularidade e facilidade de manutenção.
- Os modelos de machine learning são treinados com dados sintéticos baseados em diretrizes financeiras reais e armazenados em arquivos `.pkl`.

---

## 🎯 Objetivos

- Promover **educação financeira** acessível
- Reduzir o **endividamento** através de ajustes orçamentários guiados por IA
- Incentivar a **formação de reservas financeiras**
- Democratizar o acesso a práticas de planejamento econômico eficaz

---

## 📌 Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/myfinance.git
````

2. Instale as dependências do frontend e backend:

   * **Frontend (React):**

     ```bash
     cd frontend
     npm install
     npm start
     ```
   * **Backend (Spring Boot):**
     Configure o banco de dados PostgreSQL no `application.properties` e rode a aplicação.

3. API de IA (Flask):

   ```bash
   cd ai_service
   pip install -r requirements.txt
   python app.py
   ```

---

## 🧪 Testes

* Testes unitários e de integração implementados no backend.
* IA validada por meio de validação cruzada e simulações com perfis sintéticos.

---

## 📚 Referências

* Modelo 50/30/20 (WARREN; TYAGI, 2005)
* Pressman & Maxim – Engenharia de Software
* Financial Literacy Around the World (S\&P, 2014)
* Dados IBGE/POF 2018 e médias salariais do G1

---

## 🧑‍💻 Autores

* Pedro Araújo Rodrigues
* João Paulo Lima de Souza

Orientador: Prof. Ricardo Alves Moraes
Centro Universitário de Brasília – CEUB

---

## 📅 Conclusão

MyFinance busca consolidar-se como uma ferramenta prática e educativa, capacitando o usuário a tomar decisões financeiras mais inteligentes, autônomas e sustentáveis.

---

```

```
