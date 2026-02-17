# ⚡ Sistema Severo Eletromotores

Este é um sistema de gerenciamento técnico e administrativo desenvolvido para a **Severo Eletromotores**, minha empresa especializada em manutenção e rebobinamento de motores elétricos.

O software foi criado para resolver uma dor real do dia a dia: a organização de dados técnicos de rebobinamento (esquemas de força e partida, bitolas de fios e voltas) que antes eram registrados apenas em papel.

##  Funcionalidades

- **Cadastro de Clientes e Motores:** Vinculação direta entre o proprietário e o equipamento.
- **Banco de Dados Técnico:** Suporte para motores **Monofásicos** e **Trifásicos**.
- **Visualização de Arcos:** Lógica de exibição que simula o esquema de grupos de bobinas para facilitar o trabalho na bancada.
- **Persistência de Dados:** Salvamento e carregamento automático via arquivo de texto (`.txt`), garantindo que os dados não se percam ao fechar o programa.
- **Blindagem de Dados:** Tratamento de exceções (try-catch) para evitar que o programa feche por erros de digitação.
- **Busca Avançada:** Filtros por marca, modelo, potência e RPM para consulta rápida de dados de rebobinamento.

##  Tecnologias Utilizadas

- **Java 17+**: Linguagem principal utilizada para a lógica de objetos.
- **Manipulação de Arquivos (IO)**: Para armazenamento persistente de dados.
- **Scanner & Exception Handling**: Para uma interface de console segura e robusta.

##  Como o Sistema Funciona (Lógica Técnica)

O sistema diferencia a entrada de dados conforme o tipo de motor:
- **Motores Monofásicos:** Solicita bitola e voltas para os grupos de **Força** e **Partida**.
- **Motores Trifásicos:** Foca no esquema de ligação e dados de fio único/duplo conforme a necessidade.

Os dados são estruturados em um padrão de 12 colunas separado por `;` (ponto e vírgula), permitindo uma leitura rápida e eficiente pelo código.

##  Como Executar

1. Certifique-se de ter o JDK instalado em sua máquina.
2. Clone o repositório:
   ```bash
   git clone [https://github.com/SEU_USUARIO/Sistema-Severo-Eletromotores.git](https://github.com/SEU_USUARIO/Sistema-Severo-Eletromotores.git)