import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Principal {
    public static void main(String[] args) {

        Scanner leitura = new Scanner(System.in);

        ArrayList<Cliente> todosClientes = new ArrayList<>();
        ArrayList<Motor> oficina = new ArrayList<>();

        carregarDados(todosClientes, oficina);
        int opcao = -1;

        while (opcao != 0) {
            try {
                System.out.println("\n===SISTEMA SEVERO ELETROMOTORES===");
                System.out.println("1. Cadastrar Novo Cliente");
                System.out.println("2. Cadastrar Novo Motor");
                System.out.println("3. Listar Clientes");
                System.out.println("4. Listar Motores");
                System.out.println("5. Remover Motor Cadastrado");
                System.out.println("6. Buscar por Nome de Cliente");
                System.out.println("7. Buscar Motor por Dados");
                System.out.println("0. Sair");

                opcao = leitura.nextInt();
                leitura.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("ERRO! Digite apenas os números das opções(0 a 7).");
                leitura.nextLine();
                opcao = -1;
                continue;
            }
            switch (opcao) {
                case 1:

                    System.out.println("Opção 1: Cadastrar Cliente");
                    System.out.println("Nome do cliente:");
                    String nomeCliente = leitura.nextLine();

                    System.out.println("Telefone: ");
                    String telCliente = leitura.nextLine();

                    Cliente cliente1 = new Cliente(nomeCliente, telCliente);
                    todosClientes.add(cliente1);
                    break;

                case 2:

                    if (todosClientes.isEmpty()) {
                        System.out.println("Erro! Você precisa cadastrar um cliente (opção 1) antes de cadastrar um motor.");
                        break;
                    }

                    System.out.println("Tipo do Motor: (1)Monofásico | (2) Trifásico");
                    int tipoOp = leitura.nextInt();
                    leitura.nextLine();
                    String tipo = (tipoOp == 1) ? "Monofásico" : "Trifásico";

                    String dadosForca = "";
                    String dadosPartida = "";
                    String fForca = "";
                    String fPartida = "";

                    if (tipo.equals("Monofásico")) {
                        System.out.println("===DADOS MONOFÁSICO===");
                        System.out.println("Fio da Força: ");
                        fForca = leitura.nextLine();
                        System.out.println("Digite as voltas da FORÇA (ex: 80##60##40: ");
                        dadosForca = leitura.nextLine();
                        System.out.println("Fio da Partida: ");
                        fPartida = leitura.nextLine();
                        System.out.println("Digite as voltas da PARTIDA (ex: 80##60##40: ");
                        dadosPartida = leitura.nextLine();
                    } else {
                        System.out.println("===DADOS TRIFÁSICO===");
                        System.out.println("Digite a quantidade de voltas e o fio (ex: 45 voltas fio 21): ");
                        dadosForca = leitura.nextLine();
                        dadosPartida = "N/A";
                    }

                    System.out.println("===Vinculando Motor a um Cliente===");
                    for (int i = 0; i < todosClientes.size(); i++) {
                        System.out.println("[" + i + "]" + todosClientes.get(i).getNome());
                    }

                    int indice = -1;
                    boolean indiceValido = false;

                    while (!indiceValido) {
                        indice = lerNumeroSeguro(leitura, "Digite o número do cliente: ");

                        if (indice >= 0 && indice < todosClientes.size()) {
                            indiceValido = true;
                        } else {
                            System.out.println("Esse cliente não existe. escolha um número entre 0 e " + (todosClientes.size() - 1));
                        }
                    }

                    Cliente clienteSelecionado = todosClientes.get(indice);

                    System.out.println("Cadastrar Motor");
                    System.out.println("Marca: ");
                    String marcaMotor = leitura.nextLine();

                    System.out.println("Modelo: ");
                    String modeloMotor = leitura.nextLine();

                    System.out.println("Potência: ");
                    String potenciaMotor = leitura.nextLine();

                    System.out.println("Voltagem: ");
                    String voltagemMotor = leitura.nextLine();

                    int rpmMotor = lerNumeroSeguro(leitura, "Rpm: ");
                    leitura.nextLine();

                    Motor novoMotor = new Motor(clienteSelecionado, marcaMotor, modeloMotor, potenciaMotor, voltagemMotor, rpmMotor, tipo, dadosForca, dadosPartida, fForca, fPartida);
                    oficina.add(novoMotor);
                    salvarDados(oficina);
                    System.out.println("Motor Cadastrado para " + clienteSelecionado.getNome());
                    break;

                case 3:

                    System.out.println("===CLIENTES CADASTRADOS===");
                    if (todosClientes.isEmpty()) {
                        System.out.println("Sem clientes Cadastrados.");
                    } else {
                        for (Cliente c : todosClientes) {
                            System.out.println("Nome: " + c.getNome() + "| Telefone: " + c.getTelefone());
                        }
                    }
                    break;

                case 4:

                    System.out.println("===MOTORES CADASTRADOS===");
                    if (oficina.isEmpty()) {
                        System.out.println("Sem Motores Cadastrados");
                    } else {
                        for (Motor m : oficina) {
                            m.exibirInformacoes();
                        }
                    }
                    break;

                case 5:

                    System.out.println("===REMOVER MOTOR===");
                    if (oficina.isEmpty()) {
                        System.out.println("Não há motores na oficina.");
                    } else {
                        for (int i = 0; i < oficina.size(); i++) {
                            System.out.println("[" + i + "]" + oficina.get(i).getModelo() + " - Cliente: " + oficina.get(i).getProprietario().getNome());
                        }
                        int idRemover = -1;
                        boolean idRemoverValido = false;

                        while (!idRemoverValido) {
                            idRemover = lerNumeroSeguro(leitura, "Digite o número do motor para remover: ");

                            if (idRemover >= 0 && idRemover < oficina.size()) {
                                idRemoverValido = true;
                            } else {
                                System.out.println("Esse motor não existe. Escolha um número entre 0 e " + (oficina.size() - 1));
                            }
                        }
                        Motor removido = oficina.remove(idRemover);
                        salvarDados(oficina);
                        System.out.println("Motor removido com sucesso!");
                    }
                    break;

                case 6:

                    System.out.println("Digite o nome do cliente para buscar: ");
                    String nomeBusca = leitura.nextLine();
                    boolean encontrado = false;

                    System.out.println("===RESULTADOS DA BUSCA===");
                    for (Motor m : oficina) {
                        if (m.getProprietario().getNome().equalsIgnoreCase(nomeBusca)) {
                            m.exibirInformacoes();
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Nenhum motor encontrado para o cliente: " + nomeBusca);
                    }
                    break;

                case 7:

                    System.out.println("===BUSCA MOTOR POR DADOS===");
                    System.out.println("Preencha os dados para filtrar(ou aperte ENTER para ignorar algum campo.");

                    System.out.println("Marca: ");
                    String fMarca = leitura.nextLine();

                    System.out.println("Modelo: ");
                    String fModelo = leitura.nextLine();

                    System.out.println("Potencia: ");
                    String fPotencia = leitura.nextLine();

                    System.out.println("Voltagem: ");
                    String fVoltagem = leitura.nextLine();

                    System.out.println("RPM: ");
                    String fRpmStr = leitura.nextLine();

                    System.out.println("===RESULTADOS DA BUSCA===");
                    boolean algumEncontrado = false;

                    for (Motor m : oficina) {

                        boolean bateMarca = fMarca.isEmpty() || m.getMarca().equalsIgnoreCase(fMarca);
                        boolean bateModelo = fModelo.isEmpty() || m.getModelo().equalsIgnoreCase(fModelo);
                        boolean batePotencia = fPotencia.isEmpty() || m.getPotencia().equalsIgnoreCase(fPotencia);
                        boolean bateVoltagem = fVoltagem.isEmpty() || m.getVoltagem().equalsIgnoreCase(fVoltagem);
                        boolean bateRpm = fRpmStr.isEmpty() || String.valueOf(m.getRpm()).equalsIgnoreCase(fRpmStr);

                        if (bateMarca && bateModelo && batePotencia && bateVoltagem && bateRpm) {
                            m.exibirInformacoes();
                            algumEncontrado = true;
                        }
                    }
                    if (!algumEncontrado) {
                        System.out.println("Nenhum motor correspondente aos dados informados.");
                    }
                    break;


                case 0:

                    System.out.println("Encerrando Sistema.");
                    break;

                default:
                    System.out.println("Opção Inválida. Tente novamente.");
                    break;
            }
        }
    }

    public static void salvarDados(ArrayList<Motor> motores) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("oficina.txt"))) {
            for (Motor m : motores) {
                writer.println(m.getProprietario().getNome() + ";" + m.getProprietario().getTelefone() + ";" + m.getMarca() + ";" + m.getModelo() + ";" + m.getPotencia() + ";" + m.getVoltagem() + ";" + m.getRpm() + ";" + m.getTipo() + ";" + m.getEsquemaForca() + ";" + m.getEsquemaPartida() + ";" + m.getFioForca() + ";" + m.getFioPartida());
            }
            System.out.println("Dados salvos no arquivo 'oficina.txt'!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public static void carregarDados(ArrayList<Cliente> clientes, ArrayList<Motor> motores) {
        File arquivo = new File("oficina.txt");
        if (!arquivo.exists()) return;

        try (Scanner leitorArquivo = new Scanner(arquivo)) {
            while (leitorArquivo.hasNextLine()) {
                String linha = leitorArquivo.nextLine().trim();
                if (linha.isEmpty()) continue;
                String[] partes = linha.split(";");

                if (partes.length == 12) {
                    Cliente c = new Cliente(partes[0], partes[1]);
                    clientes.add(c);

                    Motor m = new Motor(c, partes[2], partes[3], partes[4], partes[5], Integer.parseInt(partes[6]), partes[7], partes[8], partes[9], partes[10], partes[11]);
                    motores.add(m);
                }
            }
            System.out.println(motores.size() + " registros carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        }
    }

    public static int lerNumeroSeguro(Scanner leitura, String mensagem) {
        while (true) {
            try {
                System.out.println(mensagem);
                int num = leitura.nextInt();
                leitura.nextLine();
                return num;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Digite um número válido!");
                leitura.nextLine();
            }
        }
    }
}