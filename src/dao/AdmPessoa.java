package dao;

import model.Pessoa;

import java.io.*;
import java.util.HashSet;
import java.util.Set;


public class AdmPessoa {

    private File arquivo;

    public AdmPessoa() {
        arquivo = new File("arquivo.ser");

        if(!arquivo.exists()) {
            try{
                arquivo.createNewFile();
            }catch (IOException e){
                System.out.println("Erro ao criar arquivo");
            }
        }
    }

    public Set<Pessoa> getPessoas() {
        if (arquivo.length() > 0) {
            try (FileInputStream inputStream = new FileInputStream(arquivo);
                 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
                return (Set<Pessoa>) objectInputStream.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Falha ao ler arquivo: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Classe não encontrada: " + e.getMessage());
            }
        }
        return new HashSet<>();
    }

    public boolean salvar(Pessoa pessoa){
        Set<Pessoa> pessoas = getPessoas();
        if(pessoas.add(pessoa)){
            try (FileOutputStream outputStream = new FileOutputStream(arquivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){
                objectOutputStream.writeObject(pessoas);
                return true;
            } catch (FileNotFoundException e){
                System.out.println("Arquivo não encontrado: " + e.getMessage());
            } catch (IOException e){
                System.out.println("Falha ao escrever arquivo: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean excluir(Pessoa pessoa){
        Set<Pessoa> pessoas = getPessoas();
        if(pessoas.remove(pessoa)){
            try{
                FileOutputStream outputStream = new FileOutputStream(arquivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(pessoas);
                return true;
            } catch (FileNotFoundException e){
                System.out.println("Arquivo não encontrado: " + e.getMessage());
            } catch (IOException e){
                System.out.println("Falha ao excluir arquivo: " + e.getMessage());
            }
        }
        return false;
    }

    public Pessoa buscarPessoa(String email) {
        Set<Pessoa> pessoas = getPessoas();
        for (Pessoa p : pessoas) {
            if (p.getEmail().equals(email)) {
                return p;
            }
        }
        return null;
    }

}
