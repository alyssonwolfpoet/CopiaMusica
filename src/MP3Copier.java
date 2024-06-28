import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class MP3Copier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o caminho completo do arquivo MP3: ");
        String mp3FilePath = scanner.nextLine();

        System.out.print("Digite o número de cópias desejadas: ");
        int numCopies = scanner.nextInt();

        // Extrai o nome do arquivo sem a extensão
        String fileName = new File(mp3FilePath).getName();
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.'));

        // Copia o arquivo original para as cópias renomeadas
        for (int i = 1; i <= numCopies; i++) {
            String newFileName = baseName + "_copia" + i + extension;
            Path targetPath = Path.of(newFileName);

            try {
                Files.copy(Path.of(mp3FilePath), targetPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Cópia " + i + " criada com sucesso: " + targetPath);
            } catch (IOException e) {
                System.err.println("Erro ao criar cópia " + i + ": " + e.getMessage());
            }
        }

        scanner.close();
    }
}
