// Importe as classes do pacote dao
import dao.EmpresaDAO;

// Importe as classes do pacote models
import models.Empresa;

import java.util.List;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmpresaDAO empresaDAO = new EmpresaDAO();

        int opcao;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar Empresa");
            System.out.println("2. Editar Empresa");
            System.out.println("3. Listar Empresas");
            System.out.println("4. Excluir Empresa");
            System.out.println("5. Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    // Cadastro de Empresa
                    System.out.print("Nome da empresa: ");
                    String nome = scanner.nextLine();

                    System.out.print("CNPJ da empresa: ");
                    String cnpj = scanner.nextLine();

                    System.out.print("Endereço da empresa: ");
                    String endereco = scanner.nextLine();

                    System.out.print("Telefone da empresa: ");
                    String telefone = scanner.nextLine();

                    Empresa novaEmpresa = new Empresa(0, nome, cnpj, endereco, telefone);
                    empresaDAO.cadastrarEmpresa(novaEmpresa);

                    System.out.println("Empresa cadastrada com sucesso.");
                    break;

                case 2:
                    // Edição de Empresa
                    System.out.print("ID da empresa a ser editada: ");
                    int empresaId = scanner.nextInt();
                    scanner.nextLine();

                    Empresa empresaParaEditar = empresaDAO.consultarEmpresaPorId(empresaId);

                    if (empresaParaEditar != null) {
                        System.out.print("Novo nome da empresa: ");
                        String novoNome = scanner.nextLine();

                        System.out.print("Novo CNPJ da empresa: ");
                        String novoCnpj = scanner.nextLine();

                        System.out.print("Novo endereço da empresa: ");
                        String novoEndereco = scanner.nextLine();

                        System.out.print("Novo telefone da empresa: ");
                        String novoTelefone = scanner.nextLine();

                        empresaParaEditar.setNome(novoNome);
                        empresaParaEditar.setCnpj(novoCnpj);
                        empresaParaEditar.setEndereco(novoEndereco);
                        empresaParaEditar.setTelefone(novoTelefone);

                        empresaDAO.atualizarEmpresa(empresaParaEditar);
                        System.out.println("Empresa atualizada com sucesso.");
                    } else {
                        System.out.println("Empresa não encontrada com o ID especificado.");
                    }
                    break;

                case 3:
                    // Listar Empresas
                    List <Empresa> empresas = empresaDAO.listarEmpresas();

                    for (Empresa empresa : empresas) {
                        System.out.println("ID: " + empresa.getId());
                        System.out.println("Nome: " + empresa.getNome());
                        System.out.println("CNPJ: " + empresa.getCnpj());
                        System.out.println("Endereço: " + empresa.getEndereco());
                        System.out.println("Telefone: " + empresa.getTelefone());
                        System.out.println();
                    }
                    break;

                case 4:
                    // Exclusão de Empresa
                    System.out.print("ID da empresa a ser excluída: ");
                    int empresaIdExcluir = scanner.nextInt();
                    scanner.nextLine();

                    empresaDAO.removerEmpresa(empresaIdExcluir);
                    System.out.println("Empresa excluída com sucesso.");
                    break;

                case 5:
                    System.out.println("Saindo do programa.");
                    break;

                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        } while (opcao != 5);

        scanner.close();
    }
}

