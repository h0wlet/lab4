import java.io.IOException;

public class Main {
    public void main(String[] args) throws IOException {
        TransportCompany company = new TransportCompany("conf.cfg");
        company.start();
        int var = 0;
        while (var != '\n') {
            try {
                var = System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        company.stop();
    }
}
