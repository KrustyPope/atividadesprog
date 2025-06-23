import javax.swing.*;
import java.io.File;

public class NavegadorDeArquivos {
    private File diretorioAtual;

    public NavegadorDeArquivos() {
        // Define o diretório inicial como o diretório de trabalho atual
        this.diretorioAtual = new File(System.getProperty("user.dir"));
    }

    public void iniciar() {
        while (true) {
            String[] opcoes = {"Listar", "Entrar", "Voltar", "Sair"};
            int escolha = JOptionPane.showOptionDialog(null, 
                    "Navegador de Arquivos\nDiretório atual: " + diretorioAtual.getAbsolutePath(),
                    "Menu Principal", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.INFORMATION_MESSAGE, 
                    null, 
                    opcoes, 
                    opcoes[0]);

            switch (escolha) {
                case 0: // Listar
                    listarConteudo();
                    break;
                case 1: // Entrar
                    entrarEmSubdiretorio();
                    break;
                case 2: // Voltar
                    voltar();
                    break;
                case 3: // Sair
                    JOptionPane.showMessageDialog(null, "Saindo do navegador de arquivos.");
                    return;
                default:
                    break;
            }
        }
    }

    private void listarConteudo() {
        File[] arquivos = diretorioAtual.listFiles();
        if (arquivos != null && arquivos.length > 0) {
            StringBuilder lista = new StringBuilder("Conteúdo do diretório:\n");
            for (File arquivo : arquivos) {
                lista.append(arquivo.getName()).append(arquivo.isDirectory() ? " [Diretório]" : " [Arquivo]").append("\n");
            }
            JOptionPane.showMessageDialog(null, lista.toString());
        } else {
            JOptionPane.showMessageDialog(null, "O diretório está vazio.");
        }
    }

    private void entrarEmSubdiretorio() {
        String subdiretorio = JOptionPane.showInputDialog("Digite o nome do subdiretório para entrar:");
        if (subdiretorio != null) {
            File novoDiretorio = new File(diretorioAtual, subdiretorio);
            if (novoDiretorio.isDirectory()) {
                diretorioAtual = novoDiretorio;
            } else {
                JOptionPane.showMessageDialog(null, "Erro: O caminho não é um diretório válido.");
            }
        }
    }

    private void voltar() {
        if (diretorioAtual.getParentFile() != null) {
            diretorioAtual = diretorioAtual.getParentFile();
        } else {
            JOptionPane.showMessageDialog(null, "Você já está no diretório raiz.");
        }
    }

    public static void main(String[] args) {
        NavegadorDeArquivos navegador = new NavegadorDeArquivos();
        navegador.iniciar();
    }
}
