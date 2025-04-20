import java.io.*;

public static void main() {
    String outputDir = "D:\\Videos\\Download";

    try (BufferedReader urlReader = new BufferedReader(new FileReader("D:\\Projects\\java\\Control\\src\\urls"))) {
        String url;
        while ((url = urlReader.readLine()) != null) {
            if (!url.trim().isEmpty()) {
                downloadVideo(url.trim(), outputDir);
            }
        }
    } catch (IOException e) {
        System.err.println(e.getMessage());
    }
}

public static void downloadVideo(String url, String outputDir) {
    try {
        String command = "yt-dlp -f \"bestvideo[ext=mp4]+bestaudio[ext=m4a]/best[ext=mp4]\" --recode-video mp4 --merge-output-format mp4 -o \"" + outputDir + "\\%(title)s.%(ext)s\" " + url;

        ProcessBuilder builder = new ProcessBuilder("cmd", "/c", command);
        builder.redirectErrorStream(true);
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
                System.out.println(line);
        }

        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Download completed.");
        } else {
            System.err.println(" Download Failed.");
        }

    } catch (Exception e) {
        System.err.println(e.getMessage());
    }
}
