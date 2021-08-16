# Autômatos finitos determinísticos, não determinísticos e com movimentos vazios

O código a seguir busca reconhecer autômatos finitos determinísticos, não determinísticos e com movimentos vazios através de arquivos de texto, sendo os arquivos a seguir

### Automato.txt
Neste arquivo a primeira linha deve conter os estados iniciais do autômato separados por vírgula, exemplo: 1,2,3
A segunda linha deve conter os estados finais do autômato separados por vírgula, exemplo: 4,5,6
E a terceira linha deve conter a seguinte estrutura: (q0|a|q1),(q1|b|q2), abre parênteses o nome do estado, depois | seguido do caractere a ser lido para a mudança de estado mais um | o estado para qual a mudança ocorre e fecha parênteses. E para cada transição adicionada separa com virgula. Sem o uso de espaços.

### Entrada.txt
Aqui cada linha do arquivo deve conter uma palavra a ser processada pelo autômato

### Saída.txt
Este arquivo será criado automaticamente pelo programa gerando as saídas "Aceito" caso sua palavra for reconhecida pelo autômato, ou "Rejeita" caso sua palavra não for reconhecida pelo autômato. 
Para cada teste realizado esse arquivo deve ser apagado antes do próximo teste
