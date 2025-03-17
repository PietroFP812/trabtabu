package newpack;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Dado {
    private int numeroAtual;
    private Random random = new Random();

    public int rolar() {
        numeroAtual = random.nextInt(6) + 1; // Simula um dado de 6 lados
        return numeroAtual;
    }
}

class Tabuleiro {
    private int numeroCasas;

    public Tabuleiro(int numeroCasas) {
        this.numeroCasas = numeroCasas;
    }

    public int getNumeroCasas() {
        return numeroCasas;
    }
}

class Jogador {
    private int casaAtual;
    private int meuNumero;

    public Jogador(int meuNumero) {
        this.meuNumero = meuNumero;
        this.casaAtual = 0;
    }

    public void jogar(Dado dado, Tabuleiro tabuleiro) {
        int valorDado = dado.rolar();
        casaAtual += valorDado;
        if (casaAtual > tabuleiro.getNumeroCasas()) {
            casaAtual = tabuleiro.getNumeroCasas();
        }
        System.out.println("Jogador " + meuNumero + " rolou " + valorDado + " e está na casa " + casaAtual);
    }

    public int getCasaAtual() {
        return casaAtual;
    }

    public int getMeuNumero() {
        return meuNumero;
    }
}

class Jogo {
    private Tabuleiro meuTabuleiro;
    private ArrayList<Jogador> meusJogadores;
    private Dado meuDado;
    private boolean jogoTerminado;

    public Jogo(int numeroCasas, int numeroJogadores) {
        meuTabuleiro = new Tabuleiro(numeroCasas);
        meusJogadores = new ArrayList<>();
        meuDado = new Dado();
        jogoTerminado = false;
        for (int i = 1; i <= numeroJogadores; i++) {
            meusJogadores.add(new Jogador(i));
        }
    }

    public void proximaJogada() {
        if (jogoTerminado) {
            System.out.println("O jogo já terminou!");
            return;
        }
        
        for (Jogador jogador : meusJogadores) {
            jogador.jogar(meuDado, meuTabuleiro);
            if (jogador.getCasaAtual() == meuTabuleiro.getNumeroCasas()) {
                System.out.println("\nJogador " + jogador.getMeuNumero() + " venceu o jogo!");
                jogoTerminado = true;
                return;
            }
        }
    }

    public void informarPosicoes() {
        for (Jogador jogador : meusJogadores) {
            System.out.println("Jogador " + jogador.getMeuNumero() + " está na casa " + jogador.getCasaAtual());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o número de casas do tabuleiro: ");
        int numeroCasas = scanner.nextInt();
        System.out.print("Digite o número de jogadores: ");
        int numeroJogadores = scanner.nextInt();
        
        Jogo jogo = new Jogo(numeroCasas, numeroJogadores);
        
        int opcao;
        do {
            System.out.println("\nMENU");
            System.out.println("1 - Iniciar nova partida");
            System.out.println("2 - Executar jogada");
            System.out.println("3 - Informar posições");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    jogo = new Jogo(numeroCasas, numeroJogadores);
                    System.out.println("Nova partida iniciada!");
                    break;
                case 2:
                    jogo.proximaJogada();
                    break;
                case 3:
                    jogo.informarPosicoes();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        
        scanner.close();
    }
}