import java.util.*;

class Cliente {
    private String cpf;
    private String nome;
    private String dataNascimento;
    private String dataCadastro;

    public Cliente(String cpf, String nome, String dataNascimento, String dataCadastro) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
    }

    public String getCpf() { return cpf; }

    @Override
    public String toString() {
        return "CPF: " + cpf + ", Nome: " + nome + ", Data de Nascimento: " + dataNascimento + ", Data de Cadastro: " + dataCadastro;
    }
}

class Imovel {
    private String codigo;
    private String tipo;
    private String dataCadastro;
    private String endereco;
    private String status;

    public Imovel(String codigo, String tipo, String dataCadastro, String endereco, String status) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.dataCadastro = dataCadastro;
        this.endereco = endereco;
        this.status = status;
    }

    public String getCodigo() { return codigo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Tipo: " + tipo + ", Endereço: " + endereco + ", Status: " + status + ", Data de Cadastro: " + dataCadastro;
    }
}

class Venda {
    private String dataVenda;
    private String cpfCliente;
    private String codigoImovel;
    private double valorVenda;
    private String statusVenda;

    public Venda(String dataVenda, String cpfCliente, String codigoImovel, double valorVenda, String statusVenda) {
        this.dataVenda = dataVenda;
        this.cpfCliente = cpfCliente;
        this.codigoImovel = codigoImovel;
        this.valorVenda = valorVenda;
        this.statusVenda = statusVenda;
    }

    public String getCpfCliente() { return cpfCliente; }
    public String getCodigoImovel() { return codigoImovel; }
    public String getStatusVenda() { return statusVenda; }
    public void setStatusVenda(String statusVenda) { this.statusVenda = statusVenda; }
    public String getDataVenda() { return dataVenda; }
    public double getValorVenda() { return valorVenda; }

    @Override
    public String toString() {
        return "Data da Venda: " + dataVenda + ", CPF do Cliente: " + cpfCliente + ", Código do Imóvel: " + codigoImovel + ", Valor da Venda: R$ " + valorVenda + ", Status: " + statusVenda;
    }
}

public class SistemaImobiliaria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Imovel> imoveis = new ArrayList<>();
        ArrayList<Venda> vendas = new ArrayList<>();
        double totalVendas = 0;

        int opcao;
        do {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("1. Cadastro de Imóveis");
            System.out.println("2. Cadastro de Clientes");
            System.out.println("3. Cadastro de Vendas");
            System.out.println("4. Cancelamento de Vendas");
            System.out.println("5. Relatório de Vendas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Código do Imóvel: ");
                    String codigo = scanner.nextLine();
                    System.out.print("Tipo (Casa/Apartamento): ");
                    String tipo = scanner.nextLine();
                    System.out.print("Data de Cadastro: ");
                    String dataCadastro = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Status (Disponível/Vendido): ");
                    String status = scanner.nextLine();
                    imoveis.add(new Imovel(codigo, tipo, dataCadastro, endereco, status));
                    break;
                case 2:
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Data de Nascimento: ");
                    String dataNascimento = scanner.nextLine();
                    System.out.print("Data de Cadastro: ");
                    String dataCadastroCliente = scanner.nextLine();
                    clientes.add(new Cliente(cpf, nome, dataNascimento, dataCadastroCliente));
                    break;
                case 3:
                    System.out.print("Data da Venda: ");
                    String dataVenda = scanner.nextLine();
                    System.out.print("CPF do Cliente: ");
                    String cpfCliente = scanner.nextLine();
                    System.out.print("Código do Imóvel: ");
                    String codigoImovel = scanner.nextLine();
                    System.out.print("Valor da Venda: ");
                    double valorVenda = Double.parseDouble(scanner.nextLine().replace(",", "."));
                    scanner.nextLine();
                    vendas.add(new Venda(dataVenda, cpfCliente, codigoImovel, valorVenda, "Realizada"));
                    totalVendas += valorVenda;
                    break;
                case 4:
                    System.out.print("CPF do Cliente: ");
                    String cpfCancel = scanner.nextLine();
                    System.out.print("Data da Venda: ");
                    String dataCancel = scanner.nextLine();
                    System.out.print("Código do Imóvel: ");
                    String codCancel = scanner.nextLine();
                    
                    for (Venda venda : vendas) {
                        if (venda.getCpfCliente().equals(cpfCancel) && venda.getDataVenda().equals(dataCancel) && venda.getCodigoImovel().equals(codCancel)) {
                            venda.setStatusVenda("Cancelada");
                            System.out.println("Venda cancelada com sucesso.");
                        }
                    }
                    break;
                case 5:
                    System.out.print("Filtrar vendas por data: ");
                    String filtroData = scanner.nextLine();
                    double totalFiltrado = 0;

                    for (Venda venda : vendas) {
                        if (venda.getDataVenda().equals(filtroData)) {
                            System.out.println(venda);
                            totalFiltrado += venda.getValorVenda();
                        }
                    }
                    System.out.println("Total de vendas no período: R$ " + totalFiltrado);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
