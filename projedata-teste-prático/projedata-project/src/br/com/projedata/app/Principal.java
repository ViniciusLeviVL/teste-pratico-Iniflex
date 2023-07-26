package br.com.projedata.app;

import br.com.projedata.modelos.Funcionario;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        DecimalFormat separadorDeMilhar = new DecimalFormat("###,###.##");

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), BigDecimal.valueOf(2284.38), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Gerente"));

        // 3.2 - Remover o funcionário “João” da lista.
        String nome = "João";
        for (Funcionario funcionario : funcionarios ) {
            if (funcionario.getNome() == nome) {
                funcionarios.remove(funcionarios.indexOf(funcionario)); 
                System.out.println("O funcionário " + nome + " foi removido da lista de funcionários.");
                break;
            }
        }

        /*
        3.3 - Imprimir todos os funcionários com todas suas informações.
        • informação de data deve ser exibido no formato dd/mm/aaaa;
        • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
        */
        System.out.println("Lista de Funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome() +
                    " | Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                    " | Salário: " + separadorDeMilhar.format(funcionario.getSalario()) +
                    " | Função: " + funcionario.getFuncao());
        }

        // 3.4 - Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        for (Funcionario funcionario : funcionarios) {   
            funcionario.setSalario(funcionario.getSalario().multiply(BigDecimal.valueOf(1.1)));
        }

        // 3.5 - Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            funcionariosPorFuncao.putIfAbsent(funcao, new ArrayList<>());
            funcionariosPorFuncao.get(funcao).add(funcionario);
        }

        // 3.6 - Imprimir os funcionários, agrupados por função.
        System.out.println("\nFuncionários por Função:");
        for (String funcao : funcionariosPorFuncao.keySet()) {
            System.out.println("Função: " + funcao);
            List<Funcionario> funcionariosDaFuncao = funcionariosPorFuncao.get(funcao);
            for (Funcionario funcionario : funcionariosDaFuncao) {
                System.out.println("- " + funcionario.getNome());
            }
        }

        // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("\nFuncionários com aniversário em outubro e dezembro:");
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println(funcionario.getNome() + " - Data do aniversário: "  + funcionario.getDataNascimento().getDayOfMonth() + "/" + mesAniversario);
            }
        }

        // 3.9 - Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        System.out.println("\nFuncionário com a maior idade:");
        Funcionario funcionarioMaisVelho = funcionarios.get(0);
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento().isBefore(funcionarioMaisVelho.getDataNascimento())) {
                funcionarioMaisVelho = funcionario;
            }
        }
        int idadeFuncionarioMaiorIdade = LocalDate.now().getYear() - funcionarioMaisVelho.getDataNascimento().getYear();
        System.out.println("Nome: " + funcionarioMaisVelho.getNome() + "\nIdade: " + idadeFuncionarioMaiorIdade + " anos");

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética.
        funcionarios.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }

        // 3.11 - Imprimir o total dos salários dos funcionários.
        System.out.println("\nTotal dos salários dos funcionários:");
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }
        System.out.println("Total: " + separadorDeMilhar.format(totalSalarios));

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        System.out.println("\nSalários em relação ao salário mínimo:");
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 1, RoundingMode.HALF_UP);
            System.out.println(funcionario.getNome() + ": " + salariosMinimos + " salário(s) mínimo(s)");
        }
    }
}