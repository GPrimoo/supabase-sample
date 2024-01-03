# Exemplo de uso do Supabase em um aplicativo Android

Este projeto é um exemplo de como utilizar o Supabase em um aplicativo Android, utilizando a arquitetura MVVM e a biblioteca Supabase para Kotlin.

## Descrição

O Supabase é uma plataforma open-source que oferece uma solução completa para desenvolvimento de aplicativos com banco de dados PostgreSQL e autenticação. Este projeto é um exemplo simples de como integrar o Supabase em um aplicativo Android, demonstrando o processo de configuração e uso básico.

## Recursos

- Integração do Supabase em um aplicativo Android
- Configuração do ambiente Supabase e obtenção das credenciais necessárias
- Exemplo de consulta ao banco de dados utilizando a biblioteca Supabase para Kotlin
- Utilização do padrão arquitetural MVVM para separação de responsabilidades

## Pré-requisitos

Antes de executar o projeto, você precisa ter os seguintes requisitos instalados:

- Android Studio: https://developer.android.com/studio
- JDK 8 ou superior: https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

## Configuração

1. Clone o repositório para a sua máquina local:
`git clone https://github.com/GPrimoo/supabase-sample.git`

2. Abra o projeto no Android Studio.

3. Faça o download das dependências do Gradle.

4. Crie uma conta no Supabase: https://supabase.io/

5. Crie um novo projeto no Supabase e obtenha as credenciais de autenticação e conexão com o banco de dados.

6. Crie uma tabela chamada `contacts` e os seguintes campos:
   
|    Nome    |           Tipo           |   Formato   | 
| ---------- | ------------------------ | ----------- |
|    name    |          bigint          |     int8    |
|  telephone |           text           |     text    |
|    email   |           text           |     text    |
| created_at | timestamp with time zone | timestamptz |

Remova o RLS da tabela ou crie uma política liberando o acesso para todos usuários.

8. Abra o arquivo `Supabase.kt` localizado em `app/src/main/java/com/gprimo/supabase_sample/data/supabase/Supabase.kt` e substitua as constantes `SUPABASE_URL` e `SUPABASE_KEY` pelas suas próprias credenciais.

9. Agora você está pronto para executar o aplicativo Android e explorar o exemplo de uso do Supabase.

## Contribuição

Contribuições são bem-vindas! Se você tiver sugestões, melhorias ou correções, fique à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo LICENSE para obter mais informações.
