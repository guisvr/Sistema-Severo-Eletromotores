public class Motor {
    private Cliente proprietario;
    private String marca;
    private String modelo;
    private String potencia;
    private String voltagem;
    private int rpm;
    private String tipo;
    private String esquemaForca;
    private String esquemaPartida;
    private String fioForca;
    private String fioPartida;

    public Motor(Cliente proprietario, String marca, String modelo, String potencia, String voltagem, int rpm, String tipo, String esquemaForca, String esquemaPartida, String fioForca, String fioPartida) {
        this.proprietario = proprietario;
        this.marca = marca;
        this.modelo = modelo;
        this.potencia = potencia;
        this.voltagem = voltagem;
        this.rpm = rpm;
        this.tipo = tipo;
        this.esquemaForca = esquemaForca;
        this.esquemaPartida = esquemaPartida;
        this.fioForca = fioForca;
        this.fioPartida = fioPartida;

    }

    public Cliente getProprietario() {
        return proprietario;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPotencia() {
        return potencia;
    }

    public String getVoltagem() {
        return voltagem;
    }

    public int getRpm() {
        return rpm;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEsquemaForca() {
        return esquemaForca;
    }

    public String getEsquemaPartida() {
        return esquemaPartida;
    }

    public String getFioForca() {
        return fioForca;
    }

    public String getFioPartida() {
        return fioPartida;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public void setVoltagem(String voltagem) {
        this.voltagem = voltagem;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public void setProprietario(Cliente proprietario) {
        this.proprietario = proprietario;
    }

    public void exibirInformacoes() {
        System.out.println("======================================");
        System.out.println("FICHA TÉCNICA - " + tipo.toUpperCase());
        System.out.println("Equipamento: " + marca + "|" + modelo + "|" + potencia + "|" + voltagem + "|" + rpm);
        System.out.println("Proprietário: " +proprietario.getNome());
        System.out.println("--------------------------------------");

        if (tipo.equalsIgnoreCase("Monofásico")) {
            System.out.println(">>>FORÇA<<<");
            System.out.println("FIO: " + fioForca);
            desenharArcos(esquemaForca);

            System.out.println(">>>PARTIDA<<<");
            System.out.println("FIO: " + fioPartida);
            desenharArcos(esquemaPartida);
        } else {
            System.out.println(">>>DADOS MOTOR TRIFÁSICO<<<");
            System.out.println(esquemaForca.replace("##", "\n"));
        }
        System.out.println("=====================================");
    }

    private void desenharArcos(String dados) {
        if (dados == null || dados.trim().isEmpty() || dados.equals("N/A")) {
            System.out.println("Sem dados informados.");
            return;
        }
        String[] arcos = dados.split("##");
        for (String voltas : arcos) {
            System.out.println("      [ " + voltas.trim() + " ]");
        }
    }
}
