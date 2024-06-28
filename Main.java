import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Aluno> listaDeAlunos = new ArrayList<>();
        String linha;
        
        // Leitura do arquivo alunos.csv
        try (BufferedReader br = new BufferedReader(new FileReader("alunos.csv"))) {
            br.readLine(); // Ignorar a primeira linha (cabeçalho)
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                int matricula = Integer.parseInt(dados[0]);
                String nome = dados[1];
                double nota = Double.parseDouble(dados[2].replace(",", "."));
                Aluno aluno = new Aluno(matricula, nome, nota);
                listaDeAlunos.add(aluno);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Processamento dos dados
        int totalAlunos = listaDeAlunos.size();
        int aprovados = 0;
        int reprovados = 0;
        double menorNota = Double.MAX_VALUE;
        double maiorNota = Double.MIN_VALUE;
        double somaNotas = 0;

        for (Aluno aluno : listaDeAlunos) {
            double nota = aluno.getNota();
            if (nota >= 6.0) {
                aprovados++;
            } else {
                reprovados++;
            }
            if (nota < menorNota) {
                menorNota = nota;
            }
            if (nota > maiorNota) {
                maiorNota = nota;
            }
            somaNotas += nota;
        }

        double mediaGeral = somaNotas / totalAlunos;

        // Gravação do arquivo resumo.csv
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("resumo.csv"))) {
            bw.write("Quantidade de alunos na turma: " + totalAlunos + "\n");
            bw.write("Quantidade de aprovados: " + aprovados + "\n");
            bw.write("Quantidade de reprovados: " + reprovados + "\n");
            bw.write("Menor nota: " + menorNota + "\n");
            bw.write("Maior nota: " + maiorNota + "\n");
            bw.write("Média geral: " + mediaGeral + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

