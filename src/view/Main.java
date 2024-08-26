package view;

import dao.AdmPessoa;
import model.Pessoa;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final AdmPessoa admPessoa = new AdmPessoa();
    public static void main(String[] args) {

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar uma Pessoa");
            System.out.println("2. Listar todas as Pessoas");
            System.out.println("3. Remover uma Pessoa pelo e-mail");
            System.out.println("4. Sair");

            int escolha = sc.nextInt();
            sc.nextLine();

            switch (escolha) {
                case 1:
                    salvarPessoa();
                    break;
                case 2:
                    listarPessoas();
                    break;
                case 3:
                    excluirPessoa();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    private static void salvarPessoa(){
        System.out.println("Digite o nome do Pessoa: ");
        String nome = sc.nextLine();
        System.out.println("Digite o seu e-mail: ");
        String email = sc.nextLine();

        Pessoa pessoa = new Pessoa(nome, email);
        if (admPessoa.salvar(pessoa)) {
            System.out.println("Pessoa salvo com sucesso!");
        } else {
            System.out.println("Não foi possivel salvar uma Pessoa!");
        }
    }

    private static void listarPessoas(){
        admPessoa.getPessoas().forEach(System.out::println);
    }

    private static void excluirPessoa() {
        System.out.print("Digite o e-mail da pessoa a ser deletada: ");
        String email = sc.nextLine();

        Pessoa pessoa = new Pessoa("", email); // Nome não importa para a exclusão
        if (admPessoa.excluir(pessoa)) {
            System.out.println("Pessoa excluída com sucesso, se existia.");
        } else {
            System.out.println("Não foi possível excluir a pessoa. Verifique se o e-mail está correto.");
        }
    }
}
