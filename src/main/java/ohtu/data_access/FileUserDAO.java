
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ohtu.data_access.UserDao;
import ohtu.domain.User;

public class FileUserDAO implements UserDao {

    private List<User> kayttajat;
    private String nimi;

    public void FileUserDao(String tiedosto) throws FileNotFoundException {
        nimi = tiedosto;
        kayttajat = new ArrayList<User>();
        Scanner scanner = new Scanner(new File(tiedosto));

    }

    public List<User> listAll() {
        return kayttajat;
    }

    public User findByName(String name) {
        for (User kayttaja : kayttajat) {
            if (kayttaja.getUsername().equals(name)) {
                return kayttaja;
            }
        }

        return null;
    }

    public void add(User user) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(this.nimi, true);
            writer.write(user.getUsername() + "," + user.getPassword() + "\n");
            kayttajat.add(user);
        } catch (IOException ex) {
            Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(FileUserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
