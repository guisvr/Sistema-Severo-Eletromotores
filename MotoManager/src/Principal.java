import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Principal {
    public static void main(String[] args) {

        ArrayList<Cliente> todosClientes = new ArrayList<>();
        ArrayList<Motor> oficina = new ArrayList<>();
        Scanner leitura = new Scanner(System.in);

        // Identifica o local do projeto para não errar a pasta
        String diretorioUsuario = System.getProperty("user.dir");
        System.out.println("Diretório de Trabalho: " + diretorioUsuario);

        // Carregar os dados antes de iniciar o menu
        carregarDados(todosClientes, oficina);

        int opcao = -1;

        while (opcao != 0) {
            try {
                System.out.println("\n===SISTEMA SEVERO ELETROMOTORES===");
                System.out.println("1. Cadastrar Novo Cliente");
                System.out.println("2. Cadastrar Novo Motor");
                System.out.println("3. Listar Clientes");
                System.out.println("4. Listar Motores");
                System.out.println("5. Remover Registro Cadastrado");
                System.out.println("6. Buscar por Nome de Cliente");
                System.out.println("7. Buscar Motor por Dados");
                System.out.println("8. Editar Cliente");
                System.out.println("0. Sair e Salvar");

                System.out.print("Escolha uma opção: ");
                opcao = leitura.nextInt();
                leitura.nextLine(); // Limpar buffer
            } catch (java.util.InputMismatchException e) {
                System.out.println("ERRO! Digite apenas os números das opções (0 a 8).");
                leitura.nextLine();
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("Opção 1: Cadastrar Cliente");
                    System.out.print("Nome do cliente: ");
                    String nomeCliente = leitura.nextLine();
                    System.out.print("Telefone: ");
                    String telCliente = leitura.nextLine();

                    Cliente cliente1 = new Cliente(nomeCliente, telCliente);
                    todosClientes.add(cliente1);
                    System.out.println("Cliente adicionado à lista temporária.");
                    break;

                case 2:
                    if (todosClientes.isEmpty()) {
                        System.out.println("Erro! Você precisa cadastrar um cliente antes.");
                        break;
                    }

                    System.out.println("Tipo do Motor: (1) Monofásico | (2) Trifásico");
                    int tipoOp = leitura.nextInt();
                    leitura.nextLine();
                    String tipo = (tipoOp == 1) ? "Monofásico" : "Trifásico";

                    String dadosForca = "", dadosPartida = "", fForca = "", fPartida = "";

                    if (tipo.equals("Monofásico")) {
                        System.out.println("===DADOS MONOFÁSICO===");
                        System.out.print("Fio da Força: ");
                        fForca = leitura.nextLine();
                        System.out.print("Voltas da FORÇA: ");
                        dadosForca = leitura.nextLine();
                        System.out.print("Fio da Partida: ");
                        fPartida = leitura.nextLine();
                        System.out.print("Voltas da PARTIDA: ");
                        dadosPartida = leitura.nextLine();
                    } else {
                        System.out.println("===DADOS TRIFÁSICO===");
                        System.out.print("Quantidade de voltas e o fio: ");
                        dadosForca = leitura.nextLine();
                        dadosPartida = "N/A";
                        fForca = "N/A";
                        fPartida = "N/A";
                    }

                    System.out.println("=== Vincular Motor a um Cliente ===");
                    for (int i = 0; i < todosClientes.size(); i++) {
                        System.out.println("[" + i + "] " + todosClientes.get(i).getNome());
                    }

                    int indice = -1;
                    boolean indiceValido = false;
                    while (!indiceValido) {
                        indice = lerNumeroSeguro(leitura, "Digite o número do cliente: ");
                        if (indice >= 0 && indice < todosClientes.size()) {
                            indiceValido = true;
                        } else {
                            System.out.println("Número inválido.");
                        }
                    }

                    Cliente clienteSelecionado = todosClientes.get(indice);

                    System.out.print("Marca: ");
                    String marcaMotor = leitura.nextLine();
                    System.out.print("Modelo: ");
                    String modeloMotor = leitura.nextLine();
                    System.out.print("Potência: ");
                    String potenciaMotor = leitura.nextLine();
                    System.out.print("Voltagem: ");
                    String voltagemMotor = leitura.nextLine();
                    int rpmMotor = lerNumeroSeguro(leitura, "Rpm: ");

                    Motor novoMotor = new Motor(clienteSelecionado, marcaMotor, modeloMotor, potenciaMotor, voltagemMotor, rpmMotor, tipo, dadosForca, dadosPartida, fForca, fPartida);
                    oficina.add(novoMotor);
                    salvarDados(oficina); // Salva logo após cadastrar
                    System.out.println("Motor Cadastrado e Salvo!");
                    break;

                case 3:
                    System.out.println("=== CLIENTES NA MEMÓRIA ===");
                    if (todosClientes.isEmpty()) System.out.println("Vazio.");
                    else for (Cliente c : todosClientes) System.out.println(c.getNome() + " - " + c.getTelefone());
                    break;

                case 4:
                    System.out.println("=== MOTORES NA OFICINA ===");
                    if (oficina.isEmpty()) System.out.println("Vazio.");
                    else for (Motor m : oficina) m.exibirInformacoes();
                    break;

                case 5:
                    System.out.println("=== REMOVER REGISTRO ===");
                    if (oficina.isEmpty()) {
                        System.out.println("Não há registros.");
                    } else {
                        for (int i = 0; i < oficina.size(); i++) {
                            System.out.println("[" + i + "] " + oficina.get(i).getProprietario().getNome() + " | " + oficina.get(i).getModelo());
                        }
                        int index = lerNumeroSeguro(leitura, "Índice para remover: ");
                        if (index >= 0 && index < oficina.size()) {
                            oficina.remove(index);
                            salvarDados(oficina);
                            System.out.println("Removido!");
                        }
                    }
                    break;

                case 8:
                    System.out.println("=== EDITAR CLIENTE ===");
                    if (oficina.isEmpty()) {
                        System.out.println("Nada para editar.");
                        break;
                    }
                    for (int i = 0; i < oficina.size(); i++) {
                        System.out.println("[" + i + "] " + oficina.get(i).getProprietario().getNome());
                    }
                    int idEdit = lerNumeroSeguro(leitura, "Índice: ");
                    if (idEdit >= 0 && idEdit < oficina.size()) {
                        Motor m = oficina.get(idEdit);
                        System.out.print("Novo Nome: ");
                        m.getProprietario().setNome(leitura.nextLine());
                        System.out.print("Novo Telefone: ");
                        m.getProprietario().setTelefone(leitura.nextLine());
                        salvarDados(oficina);
                        System.out.println("Editado!");
                    }
                    break;

                case 0:
                    System.out.println("Salvando dados finais e saindo...");
                    salvarDados(oficina);
                    return;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static void salvarDados(ArrayList<Motor> lista) {
        // Tenta salvar tanto na raiz quanto dentro de MotoManager para garantir
        try (PrintWriter writer = new PrintWriter(new FileWriter("oficina.txt", false))) {
            for (Motor m : lista) {
                writer.println(
                        m.getProprietario().getNome() + ";" +       // 0
                                m.getProprietario().getTelefone() + ";" +   // 1
                                m.getMarca() + ";" +                        // 2
                                m.getModelo() + ";" +                       // 3
                                m.getPotencia() + ";" +                     // 4
                                m.getVoltagem() + ";" +                     // 5
                                m.getRpm() + ";" +                          // 6
                                m.getTipo() + ";" +                         // 7
                                m.getEsquemaForca() + ";" +                 // 8
                                m.getEsquemaPartida() + ";" +               // 9
                                m.getFioForca() + ";" +                     // 10
                                m.getFioPartida()                           // 11
                );
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public static void carregarDados(ArrayList<Cliente> clientes, ArrayList<Motor> motores) {
        File arquivo = new File("oficina.txt");
        if (!arquivo.exists()) return;

        try (Scanner leitor = new Scanner(arquivo)) {
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine().trim();
                if (linha.isEmpty()) continue;
                String[] p = linha.split(";");

                if (p.length == 12) {
                    Cliente c = new Cliente(p[0], p[1]);
                    // Adicionamos o cliente na lista de clientes se ele não estiver lá
                    boolean jaExiste = false;
                    for (Cliente existente : clientes) {
                        if (existente.getNome().equals(c.getNome())) jaExiste = true;
                    }
                    if (!jaExiste) clientes.add(c);

                    Motor m = new Motor(c, p[2], p[3], p[4], p[5],
                            Integer.parseInt(p[6].trim()), p[7], p[8],
                            p[9], p[10], p[11]);
                    motores.add(m);
                }
            }
            System.out.println(motores.size() + " motores carregados!");
        } catch (Exception e) {
            System.out.println("Erro ao carregar: Arquivo pode estar corrompido.");
        }
    }

    public static int lerNumeroSeguro(Scanner leitura, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int num = leitura.nextInt();
                leitura.nextLine();
                return num;
            } catch (Exception e) {
                System.out.println("Digite um número válido!");
                leitura.nextLine();
            }
        }
    }
}